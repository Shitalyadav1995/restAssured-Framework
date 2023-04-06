package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class common_method_api {
	public static int responsestatuscode_extracter( String baseuri , String resource , String requestBody)
	{
	   RestAssured.baseURI=baseuri;
	   int responsestatuscode_extracter = given().header("Content-Type","application/json").body(requestBody).when().post(resource).then().extract().statusCode();
	   return responsestatuscode_extracter;
	   
	 
	    }
		public static String responseBody_extractor( String baseuri , String resource , String requestBody)
		{
		   RestAssured.baseURI=baseuri;
		   String  responsestatuscode_extractor= given().header("Content-Type","application/json").body(requestBody).when().post(resource).then().extract().response().asString();
		   return  responsestatuscode_extractor;
	
		   
		}  	
}

