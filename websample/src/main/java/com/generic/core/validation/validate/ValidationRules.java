package com.generic.core.validation.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generic.core.validation.functions.CheckNumeric;
import com.generic.core.validation.functions.LengthLessThan;
import com.generic.core.validation.functions.ValidationFunction;

public class ValidationRules {

	//Location Excel Sheet
	public static Map<String, List<ValidationFunction>> locationRules = new HashMap<String, List<ValidationFunction>>();
	
	//private String cityName;
	//private String areaName;
	//private String landmark;
	static {
		List<ValidationFunction> cityNameListRules = new ArrayList<ValidationFunction>();
		cityNameListRules.add(new LengthLessThan(10));
		
		List<ValidationFunction> areaNameListRules = new ArrayList<ValidationFunction>();
		areaNameListRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> landmarkListRules = new ArrayList<ValidationFunction>();
		landmarkListRules.add(new LengthLessThan(15));
		landmarkListRules.add(new CheckNumeric());
		
		locationRules.put("cityName", cityNameListRules);
		locationRules.put("areaName", areaNameListRules);
		locationRules.put("landmark", landmarkListRules);
		
	}
}
