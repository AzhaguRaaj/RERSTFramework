package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable
	{
		//Code to get place ID for delete API
		//It should execute only when place ID is null
		
		stepDefinitions sDef = new stepDefinitions();
		if(stepDefinitions.place_id==null)
		{	
			sDef.add_place_payload_with("Raaj","French","Asia");
			sDef.user_calls_something_with_post_http_request("addPlaceAPI", "POST");
			sDef.verify_place_id_creaated_maps_to_using("Raaj","getPlaceAPI");
		}
	}
}