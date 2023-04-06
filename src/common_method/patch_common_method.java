package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class patch_common_method {
	
	public static int responsestatuscode(String baseuri,String resource,String requestBody)
	{
		RestAssured.baseURI=baseuri;
		int responsestatuscode=given().header("Content-Type","application/json").body(requestBody).when().patch(resource).then().extract().statusCode();
		return responsestatuscode;
	}
	public static String responseBody(String baseuri,String resource,String requestBody)
	{
		RestAssured.baseURI=baseuri;
		String responseBody=given().header("Content-Type","application/json").body(requestBody).when().patch(resource).then().extract().response().asString();
		return responseBody;
	}

}
