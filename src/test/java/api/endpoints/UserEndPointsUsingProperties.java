package api.endpoints;

import java.util.ResourceBundle;

import api.payload.User;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsUsingProperties {

	//method for getting url's from properties file
	static ResourceBundle getUrl()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");  //load properties file
		return routes;  
	}
	
	public static Response postUser(User payload)
	{
		String post_url = getUrl().getString("post_url");
		Response response = given()
									.header("Authorization", "Bearer "+Routes.bearerToken)
					                .contentType(ContentType.JSON)
					                .body(payload)
					        .when()
					                .post(post_url);
		return response;
	}
	
	public static Response getUser(int id)
	{
		String get_url = getUrl().getString("get_url");
		Response response = given()
                .header("Authorization", "Bearer "+Routes.bearerToken)
                .pathParam("userId", id)
            .when()
                .get(get_url);
        return response;
	}
	
	public static Response updateUser(int id, User payload)
	{
		String update_url = getUrl().getString("update_url");
		Response response = given()
                .header("Authorization", "Bearer "+Routes.bearerToken)
                .pathParam("userId", id)
                .contentType(ContentType.JSON)
                .body(payload)
            .when()
                .put(update_url);
        return response;
	}
	
	public static Response deleteUser(int id)
	{
		String delete_url = getUrl().getString("delete_url");
		Response response = given()
                .header("Authorization", "Bearer "+Routes.bearerToken)
                .pathParam("userId", id)
            .when()
                .delete(delete_url);
        return response;
	}
	
	public static Response viewAllUser()
	{
		String view_url = getUrl().getString("view_url");
		Response response = given()
                .header("Authorization", "Bearer "+Routes.bearerToken)
            .when()
                .get(view_url);
        return response;
	}
}
