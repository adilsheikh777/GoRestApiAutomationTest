package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//this class use for CRUD Operations
public class UserEndPoints {
   
	
	public static Response postUser(User payload)
	{
		Response response = given()
				                .header("Authorization", "Bearer "+Routes.bearerToken)
				                .contentType(ContentType.JSON)
				                .body(payload)
				            .when()
				                .post(Routes.post_url);
		return response;
	}
	
	public static Response getUser(int id)
	{
		Response response = given()
                .header("Authorization", "Bearer "+Routes.bearerToken)
                .pathParam("userId", id)
            .when()
                .get(Routes.get_url);
        return response;
	}
	
	public static Response updateUser(int id, User payload)
	{
		Response response = given()
                .header("Authorization", "Bearer "+Routes.bearerToken)
                .pathParam("userId", id)
                .contentType(ContentType.JSON)
                .body(payload)
            .when()
                .put(Routes.update_url);
        return response;
	}
	
	public static Response deleteUser(int id)
	{
		Response response = given()
                .header("Authorization", "Bearer "+Routes.bearerToken)
                .pathParam("userId", id)
            .when()
                .delete(Routes.delete_url);
        return response;
	}
	
	public static Response viewAllUser()
	{
		Response response = given()
                .header("Authorization", "Bearer "+Routes.bearerToken)
            .when()
                .get(Routes.view_url);
        return response;
	}
}
