package com.generic.core.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generic.core.model.entities.Size;
import static com.generic.core.utilities.Util.*;
import com.generic.core.utilities.UtilConstants;

public class SizeCache {

	private static SizeCache sizeCache = new SizeCache();
	
	private SizeCache() {}
	
	public static SizeCache getInstance(){
		return sizeCache;
	}
	private Map<String, String[]> sizeQuantity = new HashMap<String, String[]>();
	
	protected void updateCache(List<Size> sizes) {
		
		for(Size size : sizes) {
			String permissibleValues = size.getPermissibleValues();
			String[] permissibleValueArray = formatPermissibleValues(permissibleValues);
			sizeQuantity.put(size.getSizeId() , permissibleValueArray);
		}
	}
	
	
	private String[] formatPermissibleValues(String permissibleValues) {
		
		String[] permissibleValueArray = permissibleValues.split(UtilConstants.DELIMETER_BAR);
		
		if(permissibleValueArray.length == 3 && permissibleValueArray[1].equals(UtilConstants.TWO_DOTS)) {
			String unit = stringPartOfIntegerString(permissibleValueArray[0]);
			int firstNumber = integerPartOfString(permissibleValueArray[2]);
			int secondNumber =  integerPartOfString(permissibleValueArray[0]);
			int start = min(firstNumber, secondNumber);
			int end =  max(firstNumber, secondNumber);
			int length = end - start;
			String[] resultArray = new String[length + 1];
			for(int i=0;i<=length;i++) {
				int count = start + i;
				String result = count + unit;
				resultArray[i] = result;
			}
			return resultArray;
		}
		return permissibleValueArray;
	}

	public Map<String, String[]> getSizeQuantity() {
		return sizeQuantity;
	}


}
