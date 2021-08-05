package resources;

import java.util.ArrayList;
import java.util.List;

import pojoclasses.AddPlacePojo;
import pojoclasses.LocationPojo;

public class TestData {

	public AddPlacePojo addPlaceJson(String name,String language,String address) {
		AddPlacePojo ap = new AddPlacePojo();
		List<String> types = new ArrayList<String>();
		types.add("Peak");
		types.add("Peek");
		LocationPojo lp = new LocationPojo();
		lp.setLat(-18.0001);
		lp.setLng(32.33333);
		ap.setAccuracy(12);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setLocation(lp);
		ap.setName(name);
		ap.setPhone_number("+(91) 8696821212");
		ap.setTypes(types);
		ap.setWebsite("www.linkedin.com");
		return ap;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
	

}