package com.generic.core.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.generic.core.model.entities.Location;
import com.generic.core.utilities.UtilConstants;
import com.generic.rest.dto.LocationDto;

public class LocationCache {

	private static LocationCache locationCache;
	private LocationCache(){}
	
	public static LocationCache getInstance() {
		if(locationCache == null) {
			synchronized (LocationCache.class) {
				if(locationCache == null)
					locationCache = new LocationCache();
			}
			return locationCache;
		}
		return locationCache;
	}
	//karnataka ->(bangalore->(marathalli->[4th block, 5th block]))
	private static Map<String, Map<String, Map<String, Set<LocationDto>>>> stateBag = new HashMap<String, Map<String,Map<String,Set<LocationDto>>>>();
	
	private Map<String, String> locationMap = new HashMap<String, String>();
	protected void updateCache(List<Location> locations) {
		fillLocationMap(locations);
		fillStateBag(locations);
	}
	
	protected void fillLocationMap(List<Location> locations) {
		
		for(Location aLocation : locations) {
			locationMap.put(aLocation.getLocationId(), aLocation.getLocationName());
		}
	}
	
	protected void fillStateBag(List<Location> locations) {
		
		for(Location aLocation : locations) {
			if(!aLocation.getLocationId().startsWith(UtilConstants.LOCATION_SUB_AREA_PREFIX))
				continue;
			
			Location subArea = aLocation;
			Location area = aLocation.getParentLocation();
			Location city = area.getParentLocation();
			Location state = city.getParentLocation();
			
			if(stateBag.containsKey(state.getLocationId())) { 			//check if the state is present
				Map<String, Map<String, Set<LocationDto>>> aCityBag = stateBag.get(state.getLocationId());
				if(aCityBag.containsKey(city.getLocationId())) { 		//check if the city is present
					Map<String, Set<LocationDto>> aAreaBag = aCityBag.get(city.getLocationId());
					Set<LocationDto> aSubAreaList = aAreaBag.get(area.getLocationId());
					if(aSubAreaList == null) {							//check if the area is present			
						aSubAreaList = new HashSet<LocationDto>();
						aSubAreaList.add(new LocationDto(subArea.getLocationId(), subArea.getLocationName()));
						aAreaBag.put(area.getLocationId(), aSubAreaList);
					} else {
						aSubAreaList.add(new LocationDto(subArea.getLocationId(), subArea.getLocationName()));
					}
				} else { 		// No city added yet so add city
					
					Map<String, Set<LocationDto>> aAreaBag = new HashMap<String, Set<LocationDto>>();
					Set<LocationDto> aSubAreaList = new HashSet<LocationDto>();
					
					aSubAreaList.add(new LocationDto(subArea.getLocationId(), subArea.getLocationName()));
					aAreaBag.put(area.getLocationId(), aSubAreaList);
					aCityBag.put(city.getLocationId(), aAreaBag);
				}
			} else {			// No State added yet
				Map<String, Map<String, Set<LocationDto>>> aCityBag = new HashMap<String, Map<String,Set<LocationDto>>>();
				Map<String, Set<LocationDto>> aAreaBag = new HashMap<String, Set<LocationDto>>();
				Set<LocationDto> aSubAreaList = new HashSet<LocationDto>();
				
				aSubAreaList.add(new LocationDto(subArea.getLocationId(), subArea.getLocationName()));
				aAreaBag.put(area.getLocationId(), aSubAreaList);
				aCityBag.put(city.getLocationId(), aAreaBag);
				stateBag.put(state.getLocationId(), aCityBag);
			}
			
		}
	}

	public Map<String, Map<String, Map<String, Set<LocationDto>>>> getStateBag() {
		return stateBag;
	}

	public Map<String, String> getLocationMap() {
		return locationMap;
	}
	
	
	
}
