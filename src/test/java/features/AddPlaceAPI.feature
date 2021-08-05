Feature: Validating Place API's

	@Addplace @Regression
	Scenario Outline: Validating if place is successfully added
		Given Add place payload with "<name>" "<language>" "<address>"
		When User Calls "addPlaceAPI" with "POST" http request
		Then API call got success with status code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And verify place_Id created maps to "<name>" using "getPlaceAPI"
		
	Examples: 
				| name   | language | address      |
				| Jayesh | Hindi    | Near Punjpur |
				| Karan  | Wagdi    | Near Baroda  |
				
	@Deleteplace @Regression			
	Scenario: Verify if Delete Functionality is working
		Given DeletePlace Payload
		When User Calls "deletePlaceAPI" with "POST" http request
		Then API call got success with status code 200
		And "status" in response body is "OK"