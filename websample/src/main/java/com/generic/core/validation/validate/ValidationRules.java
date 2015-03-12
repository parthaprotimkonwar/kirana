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
	public static Map<String, List<ValidationFunction>> shopItemRules = new HashMap<String, List<ValidationFunction>>();
	public static Map<String, List<ValidationFunction>> sizeRules = new HashMap<String, List<ValidationFunction>>();
	public static Map<String, List<ValidationFunction>> categoryRules = new HashMap<String, List<ValidationFunction>>();
	
	
	static {
		List<ValidationFunction> areaIdListRules = new ArrayList<ValidationFunction>();
		areaIdListRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> areaNameListRules = new ArrayList<ValidationFunction>();
		areaNameListRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> landmarkIdListRules = new ArrayList<ValidationFunction>();
		landmarkIdListRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> landmarkNameListRules = new ArrayList<ValidationFunction>();
		landmarkNameListRules.add(new LengthLessThan(20));
		
		locationRules.put("areaId", areaIdListRules);
		locationRules.put("areaName", areaNameListRules);
		locationRules.put("landmarkId", landmarkIdListRules);
		locationRules.put("landmarkName", landmarkNameListRules);
	}
	
	static {
		List<ValidationFunction> shopIdListRules = new ArrayList<ValidationFunction>();
		shopIdListRules.add(new LengthLessThan(20));
		
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
		
		List<ValidationFunction> tagsRules = new ArrayList<ValidationFunction>();
		tagsRules.add(new LengthLessThan(100));
		
		shopsRules.put("shopId", shopIdListRules);
		shopsRules.put("shopName", shopNameListRules);
		shopsRules.put("shopAddress", shopAddressListRules);
		shopsRules.put("email", emailListRules);
		shopsRules.put("phoneNumber", phoneNumberRules);
		shopsRules.put("landmarksForDelivery", landmarksForDeliveryRules);
		shopsRules.put("shopType", shopTypeRules);
		shopsRules.put("ownerName", ownerNameRules);
		shopsRules.put("tags", tagsRules);
	}
	
	
	static {
		List<ValidationFunction> itemIdRules = new ArrayList<ValidationFunction>();
		itemIdRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> brandRules = new ArrayList<ValidationFunction>();
		brandRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> descriptionRules = new ArrayList<ValidationFunction>();
		descriptionRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> imageNameRules = new ArrayList<ValidationFunction>();
		imageNameRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> itemNameRules = new ArrayList<ValidationFunction>();
		itemNameRules.add(new LengthLessThan(10));
		
		List<ValidationFunction> categoryIdRules = new ArrayList<ValidationFunction>();
		categoryIdRules.add(new LengthLessThan(10));
		
		itemsRules.put("itemId", itemIdRules);
		itemsRules.put("brand", brandRules);
		itemsRules.put("description", descriptionRules);
		itemsRules.put("imageName", imageNameRules);
		itemsRules.put("itemName", itemNameRules);
		itemsRules.put("categoryId", categoryIdRules);
	}
	
	
	static {
		// ITEM_ID SIZE_ID PRICE DISCOUNT STATUS SHOP_ID
		
		List<ValidationFunction> itemIdRules = new ArrayList<ValidationFunction>();
		itemIdRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> sizeIdRules = new ArrayList<ValidationFunction>();
		sizeIdRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> priceRules = new ArrayList<ValidationFunction>();
		priceRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> discountRules = new ArrayList<ValidationFunction>();
		discountRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> statusRules = new ArrayList<ValidationFunction>();
		statusRules.add(new LengthLessThan(10));
		
		List<ValidationFunction> shopIdRules = new ArrayList<ValidationFunction>();
		shopIdRules.add(new LengthLessThan(10));
		
		shopItemRules.put("itemId", itemIdRules);
		shopItemRules.put("sizeId", sizeIdRules);
		shopItemRules.put("price", priceRules);
		shopItemRules.put("discount", discountRules);
		shopItemRules.put("status", statusRules);
		shopItemRules.put("shopId", shopIdRules);
	}
	
	static {
		//QuantityId	QuantityName	PermissibleValues	Unit
		
		List<ValidationFunction> quanntityIdRules = new ArrayList<ValidationFunction>();
		quanntityIdRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> quantityNameRules = new ArrayList<ValidationFunction>();
		quantityNameRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> permissibleValuesRules = new ArrayList<ValidationFunction>();
		permissibleValuesRules.add(new LengthLessThan(20));
		
		List<ValidationFunction> unitRules = new ArrayList<ValidationFunction>();
		unitRules.add(new LengthLessThan(20));
		
		sizeRules.put("quantityId", quanntityIdRules);
		sizeRules.put("quantityName", quantityNameRules);
		sizeRules.put("permissibleValues", permissibleValuesRules);
		sizeRules.put("unit", unitRules);
	}
	
	static {
		// CategoryId CategoryName ParentCategory
		/*private String categoryId;
		private String categoryName;
		private String parentCategory;*/
		
		List<ValidationFunction> categoryIdRules = new ArrayList<ValidationFunction>();
		categoryIdRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> categoryNameRules = new ArrayList<ValidationFunction>();
		categoryNameRules.add(new LengthLessThan(200));
		
		List<ValidationFunction> parentCategoryRules = new ArrayList<ValidationFunction>();
		parentCategoryRules.add(new LengthLessThan(20));
		
		categoryRules.put("quantityId", categoryIdRules);
		categoryRules.put("quantityName", categoryNameRules);
		categoryRules.put("permissibleValues", parentCategoryRules);
	}
}
