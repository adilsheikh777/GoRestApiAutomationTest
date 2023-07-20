package api.endpoints;

// this class contains URL's
//https://gorest.co.in/public/v2/users  works for create user
//https://gorest.co.in/public/v2/users/{{user_Id}} works for view,update,delete user
public class Routes {

	//base url 
	public static String base_url="https://gorest.co.in/public/v2";
	
	//request url's
	public static String post_url=base_url + "/users";
	public static String get_url=base_url + "/users/{userId}";
	public static String update_url=base_url + "/users/{userId}";
	public static String delete_url=base_url + "/users/{userId}";
	public static String view_url=base_url + "/users";
	
	//bearer Token we use for all requests
	public static String bearerToken="0df71eba255e146b6f1c4acb55c4e8d54bd97e80043e825c5202a1d5c84393c4";
}
