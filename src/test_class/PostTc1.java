package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.CommonMethodUtilities;
import common_method.common_method_api;
import io.restassured.path.json.JsonPath;
import request_repository.Post_Request_Repository;



public class PostTc1 
{
	@Test
	public static void orchestrator() throws IOException
	{
		String responseBody = "";
		int responsestatuscode = 0;
		String baseuri = Post_Request_Repository.baseuri();
		String resource = Post_Request_Repository.resource();
		String requestBody = Post_Request_Repository.requestBody();
		
		for(int i=0 ; i<5 ; i++)
		{
			
			 responsestatuscode = common_method_api.responsestatuscode_extracter(baseuri, resource, requestBody);
			if (responsestatuscode == 201)
			{
				 responseBody = common_method_api.responseBody_extractor(baseuri, resource, requestBody);
				responseBodyValidator(responseBody);
				break;
			}
			else
				{
					System.out.println("correct status code is not found in the iteration" +1);
				}
			
			}
			CommonMethodUtilities.evidenceFileCreator("PostTc1",requestBody, responseBody);
			Assert.assertEquals(responsestatuscode, 201);
	       }
			public static void responseBodyValidator(String responseBody)
			{
				//create jsonPath object to extract responseBody parameters
				JsonPath jsp = new JsonPath(responseBody);
				
				//Extact responseBody parameters
				String res_name = jsp.getString("name");
//				System.out.println(res_name);
				String res_job = jsp.getString("job");
//			System.out.println(res_job);
				String res_id = jsp.getString("id");
//			System.out.println(res_id);
				String res_createdAt = jsp.getString("createdAt");
//			System.out.println(res_createdAt);
				
				
				
				//validate responseBody parameters
				Assert.assertEquals(res_name,"morpheus");
				Assert.assertEquals(res_job,"leader");
				Assert.assertNotNull(res_id,"assertion error , id parameter is null");
				
				//extract date from createdAt parameter
				String actual_date = res_createdAt.substring(0,10);
				String current_date = LocalDate.now().toString(); // create a date object
				Assert.assertEquals(actual_date,current_date);
				
				
				
				
			}	
		
		
		
		}
	
	


