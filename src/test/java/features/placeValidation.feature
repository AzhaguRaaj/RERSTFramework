Feature: Validating Place APIs

@AddPlace @Regression
Scenario Outline: Verifying if place is added successfully using add Place API
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "addPlaceAPI" with "Post" http request
	Then The response in the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
Examples:
	|name      |language   |address    |
	|Raaj      |Tamil      |Bengaluru  |
#	|RaajA     |English    |TamilNadu  |

@DeletePlace @Regression
Scenario: Verify if delete place is working
	
	Given Delete Place Payload
	When User calls "deletePlaceAPI" with "POST" http request
	Then The response in the API call is success with status code 200
	And "status" in response body is "OK"