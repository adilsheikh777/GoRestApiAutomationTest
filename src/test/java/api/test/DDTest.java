package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {

	@Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String name, String email, String gender, String status)
	{
		User payload = new User();
		
		payload.setName(name);
	    payload.setEmail(email);
	    payload.setGender(gender);
	    payload.setStatus(status);
	    
	    Response response = UserEndPoints.postUser(payload);
	     
	    response.then().log().body();
	    
	    Assert.assertEquals(response.statusCode(), 201);
	}
	
}
