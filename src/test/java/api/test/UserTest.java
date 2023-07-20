package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;

import io.restassured.response.Response;

public class UserTest {

	
	Faker faker;
	User payload;
	Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		faker = new Faker();
	    payload = new User();
	    logger = LogManager.getLogger(this.getClass());
	    
	    //generate the data from faker class and put into our request data
	    
	    payload.setName(faker.name().fullName());
	    payload.setEmail(faker.internet().safeEmailAddress());
	    payload.setGender("Male");
	    payload.setStatus("inactive");
	}
	@Test(priority = 1)
	public  void createUser(ITestContext context)
	{
		logger.info("--------creating user--------");
		
	    Response response = UserEndPoints.postUser(payload);
	    
	    Assert.assertEquals(response.statusCode(), 201);
	    
	    response.then().log().all();
	    
	    int id = response.jsonPath().getInt("id");
	    
	    System.out.println("generated id is  " + id);
	    
	    context.setAttribute("userId", id);
	    
	    logger.info("--------user is created--------");
	}
	
	@Test(priority = 2)
	public void ViewUser(ITestContext context)
	{
		logger.info("--------reading user info--------");
		
		int id = (Integer) context.getAttribute("userId");
		
		System.out.println("this is the id we get from context  " + id);
		
		Response response = UserEndPoints.getUser(id);
		
		response.then().log().body();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("--------user info displayed--------");
	}
	
	@Test(priority = 3)
	public void updatingUser(ITestContext context)
	{
		logger.info("--------updaating user--------");
		
		int id = (Integer) context.getAttribute("userId");
		
		System.out.println("this is the id we get from context  " + id);
		
		//change some data to update the user
		
		payload.setName(faker.name().fullName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setStatus("active");
		
		Response response = UserEndPoints.updateUser(id, payload);
		
		response.then().log().body();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("--------user updated--------");
	}
	
	@Test(priority = 4)
	public void deletingUser(ITestContext context)
	{
		logger.info("--------deleting user--------");
		
		int id = (Integer) context.getAttribute("userId");
		
		System.out.println("this is the id we get from context  " + id);
		
	
		
		Response response = UserEndPoints.deleteUser(id);
		
		response.then().log().body();
		
		Assert.assertEquals(response.statusCode(), 204);
		
		logger.info("--------user deleted--------");
	}
	
	
	@Test(priority = 5)
	public void getAllUsers(ITestContext context)
	{
		logger.info("--------viewing all user--------");
		
		Response response = UserEndPoints.viewAllUser();
		
		response.then().log().body();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("--------all users displayed--------");
	}
}
