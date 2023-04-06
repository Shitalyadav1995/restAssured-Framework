package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;


public class put_common_method {
	
	public static int responsestatuscode(String baseuri,String resource,String requestBody)
	{
		RestAssured.baseURI=baseuri;
		int responsestatuscode = given().header("Content-Type","application/json").body(requestBody).when().put(resource).then().extract().statusCode();
		return responsestatuscode;
	}
    public static String responseBody_extractor(String baseuri,String resource,String requestBody)
    {
    	RestAssured.baseURI=baseuri;
    	String responseBody=given().header("Content-Type","application/json").body(requestBody).when().put(resource).then().extract().asString();
    	return responseBody;
    }
}
