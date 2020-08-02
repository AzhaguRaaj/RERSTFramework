package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.addPlaceReq;
import pojo.location;

public class testDataBuild {
	
	public addPlaceReq addPlacePayoutLoad(String name, String language, String address)
	{
		//Create Object to access request data from addPlacereq class
		
				addPlaceReq obj = new addPlaceReq();
				obj.setAccuracy(50);
				obj.setAddress(address);
				obj.setLanguage(language);
				obj.setName(name);
				obj.setWebsite("www.Test.com");
				obj.setPhone_number("+91 (92123)900000");
				List<String> typeList = new ArrayList<String>();
				typeList.add("Test Park");
				typeList.add("New Test");
				obj.setTypes(typeList);
				location loc = new location();
				loc.setLat(-10.12324);
				loc.setLng(-13.2344);
				obj.setLocation(loc);
				return obj;
	}
	
	public String deletePlacePayoutLoad(String place_id)
	{
		return "{ \r\n   \"place_id\": \""+place_id+"\" \r\n}"; 
	}

}