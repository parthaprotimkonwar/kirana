package com.generic.core.validation.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.generic.core.onboarding.exceldto.ExcelLocationDto;
import com.generic.core.validation.functions.ValidationFunction;
import com.generic.rest.dto.ResponseDto;

public class GenericValidation {

	private final Class aClass;										//Class defination
	private final Map<String, List<ValidationFunction>> rules;		//Load Rules
	private List<String> classAttributes = new ArrayList<String>();	//Load attributes
	private List<? extends Object> items;							//All items present in the excel sheet
	private List<ResponseDto> responseMessage = new ArrayList<ResponseDto>();	// Response message
	
	public GenericValidation(Class aClass, List<? extends Object> items, Map<String, List<ValidationFunction>> rules) {
		this.aClass = aClass;
		this.rules = rules;
		this.items = items;
		generateMethodsOfClass();
	}
	
	/**
	 * generate values for @classAttributes
	 */
	private void generateMethodsOfClass() {
		Field[] fields = aClass.getDeclaredFields();
		for(Field aField : fields) {
			classAttributes.add(aField.getName());
		}
	}
	
	public List<ResponseDto> validate() throws IllegalArgumentException, IllegalAccessException {
		if(items == null)
			return null;
		int objectNumber = 1;
		for(Object anObject : items) {
			List<ResponseDto> result = validateAnItem(anObject, objectNumber);
			responseMessage.addAll(result);
			objectNumber++;
		}
		return responseMessage;
	}
	
	private List<ResponseDto> validateAnItem(Object anObject, int objectNumber) throws IllegalArgumentException, IllegalAccessException {
		List<ResponseDto> listOfResponse = new ArrayList<ResponseDto>();
		Field[] fields = anObject.getClass().getDeclaredFields();
		// Iterates between all the fields and validates them
		for(Field aField : fields) {
			aField.setAccessible(true);
			String fieldName = aField.getName();
			String fieldValue = aField.get(anObject).toString();
			List<ValidationFunction> validationRulesForField = rules.get(fieldName);
			
			// validates one field based on all rules to be applied.
			for(ValidationFunction aValidationFn : validationRulesForField) {
				ResponseDto response = aValidationFn.validate(fieldName , fieldValue, objectNumber);
				if(response != null)
					listOfResponse.add(response);
			}
		}
		return listOfResponse;
	}
	
	public List<ResponseDto> getResponseMessage() {
		return responseMessage;
	}
}