package com.generic.core.validation.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generic.core.validation.functions.CheckEmail;
import com.generic.core.validation.functions.CheckNumeric;
import com.generic.core.validation.functions.LengthLessThan;
import com.generic.core.validation.functions.ValidationFunction;

public class ValidationRules {

	//Location Excel Sheet
	public static Map<String, List<ValidationFunction>> locationRules = new HashMap<String, List<ValidationFunction>>();
	public static Map<String, List<ValidationFunction>> shopsRules = new HashMap<String, List<ValidationFunction>>();
	public static Map<String, List<ValidationFunction>> itemsRules = new HashMap<String, List<ValidationFunction>>();
	
	//private String cityName;
	//private String areaName;
	//private String landmark;
	static {
		List<ValidationFunction> cityNameListRules = new ArrayList<ValidationFunction>();
		cityNameListRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> areaNameListRules = new ArrayList<ValidationFunction>();
		areaNameListRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> landmarkListRules = new ArrayList<ValidationFunction>();
		landmarkListRules.add(new LengthLessThan(20));
		landmarkListRules.add(new CheckNumeric());
		
		locationRules.put("cityName", cityNameListRules);
		locationRules.put("areaName", areaNameListRules);
		locationRules.put("landmark", landmarkListRules);
	}
	
	static {
		List<ValidationFunction> shopNameListRules = new ArrayList<ValidationFunction>();
		shopNameListRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> shopAddressListRules = new ArrayList<ValidationFunction>();
		shopAddressListRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> emailListRules = new ArrayList<ValidationFunction>();
		emailListRules.add(new LengthLessThan(100));
		emailListRules.add(new CheckEmail());
		
		List<ValidationFunction> phoneNumberRules = new ArrayList<ValidationFunction>();
		phoneNumberRules.add(new LengthLessThan(15));
		phoneNumberRules.add(new CheckNumeric());
		
		List<ValidationFunction> landmarksForDeliveryRules = new ArrayList<ValidationFunction>();
		landmarksForDeliveryRules.add(new LengthLessThan(255));
		
		List<ValidationFunction> shopTypeRules = new ArrayList<ValidationFunction>();
		shopTypeRules.add(new LengthLessThan(255));
		
		List<ValidationFunction> ownerNameRules = new ArrayList<ValidationFunction>();
		ownerNameRules.add(new LengthLessThan(20));
		
		shopsRules.put("shopName", shopNameListRules);
		shopsRules.put("shopAddress", shopAddressListRules);
		shopsRules.put("email", emailListRules);
		shopsRules.put("phoneNumber", phoneNumberRules);
		shopsRules.put("landmarksForDelivery", landmarksForDeliveryRules);
		shopsRules.put("shopType", shopTypeRules);
		shopsRules.put("ownerName", ownerNameRules);
	}
	
	static {
		List<ValidationFunction> itemNameRules = new ArrayList<ValidationFunction>();
		itemNameRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> descriptionRules = new ArrayList<ValidationFunction>();
		descriptionRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> categoryRules = new ArrayList<ValidationFunction>();
		categoryRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> priceRules = new ArrayList<ValidationFunction>();
		priceRules.add(new LengthLessThan(20));
		priceRules.add(new CheckNumeric());
		
		List<ValidationFunction> unitRules = new ArrayList<ValidationFunction>();
		unitRules.add(new LengthLessThan(10));
		
		List<ValidationFunction> discountRules = new ArrayList<ValidationFunction>();
		discountRules.add(new LengthLessThan(10));
		
		List<ValidationFunction> statusRules = new ArrayList<ValidationFunction>();
		statusRules.add(new LengthLessThan(20));

		List<ValidationFunction> quantityValuesRules = new ArrayList<ValidationFunction>();
		quantityValuesRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> imageNameRules = new ArrayList<ValidationFunction>();
		imageNameRules.add(new LengthLessThan(100));
		
		List<ValidationFunction> brandRules = new ArrayList<ValidationFunction>();
		brandRules.add(new LengthLessThan(100));
		
		itemsRules.put("itemName", itemNameRules);
		itemsRules.put("description", descriptionRules);
		itemsRules.put("category", categoryRules);
		itemsRules.put("price", priceRules);
		itemsRules.put("unit", unitRules);
		itemsRules.put("discount", discountRules);
		itemsRules.put("status", statusRules);
		itemsRules.put("quantityValues", quantityValuesRules);
		itemsRules.put("imageName", imageNameRules);
		itemsRules.put("brand", brandRules);
	}
}
