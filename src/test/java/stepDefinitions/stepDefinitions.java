package stepDefinitions;


import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.Utils;
import resources.testDataBuild;

@RunWith(Cucumber.class)
public class stepDefinitions extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	testDataBuild data = new testDataBuild();	
	

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws Throwable {
    	
    	RestAssured.baseURI = "https://rahulshettyacademy.com";
		
    	// Object created to access data from resources.testDataBuild.java
			
				 		 
		res=given().spec(requestSpecification())
				.body(data.addPlacePayoutLoad(name, language, address));
        
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_something_with_post_http_request(String resource, String method) throws Throwable {
    			
    			 APIResources APIresource = APIResources.valueOf(resource);
    			
    			 if(method.equalsIgnoreCase("Post"))
    			 	 response =res.when().post(APIresource.getResource());
    			 else if(method.equalsIgnoreCase("Get"))
    				 response =res.when().get(APIresource.getResource());
    			 
    }

    @Then("^The response in the API call is success with status code 200$")
    public void the_response_in_the_api_call_is_success_with_status_code_200() throws Throwable {
    	
    	assertEquals(response.getStatusCode(),200);
    	
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String keyValue, String expectedValue) throws Throwable {
          	
    	assertEquals(getJsonPath(response,keyValue),expectedValue);
    }
    
    //Validating Place Id with getPlaceID method
   	@Then("verify place_id created maps to {string} using {string}")
   	public void verify_place_id_creaated_maps_to_using(String expectedName, String resource) throws Throwable
   	{
   		place_id = getJsonPath(response,"place_id");
   		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
   		user_calls_something_with_post_http_request(resource,"GET");
   		String actualName = getJsonPath(response,"name");
   		assertEquals(expectedName,actualName);
    	    
    }
   	
   	@Given("Delete Place Payload")
   	public void Delete_Place_Payload() throws IOException
   	{
   		res = given().spec(requestSpecification()).body(data.deletePlacePayoutLoad(place_id));
   	}



    

}