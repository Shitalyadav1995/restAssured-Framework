package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.CommonMethodUtilities;
import common_method.put_common_method;
import io.restassured.path.json.JsonPath;
import request_repository.Put_Request_Repository;

public class PutTc1 {
	@Test
	
		public static void orchestrator() throws IOException
		{
			
		   String responseBody="";
		   int responsestatuscode=0;
		   String baseuri=Put_Request_Repository.baseuri();
		   String resource=Put_Request_Repository.resource();
		   String requestBody=Put_Request_Repository.requestBody();
		   
		   for(int i = 0; i<5; i++)
		   {
			   responsestatuscode=put_common_method.responsestatuscode(baseuri, resource, requestBody);
			   if(responsestatuscode == 200)
			   {
				   responseBody=put_common_method.responseBody_extractor(baseuri, resource, requestBody);
				   responseBodyValidator(responseBody);
				   break;
			   }
			   else
			   {
				   System.out.println("correct status code is not found in the iteration" +1);
			   }
		   }
			   CommonMethodUtilities.evidenceFileCreator("PutTc1", requestBody, responseBody);
			   Assert.assertEquals(responsestatuscode, 200);
		      }
		     public static void responseBodyValidator(String responseBody)
		     
		     {
		    	 //create jsonPath object to extract responseBody parameters
		    	 JsonPath jsp = new JsonPath(responseBody);
		    	 
		    	 //Extract responseBody parameters
		    	 String res_name=jsp.getString("name");
//		    	 System.out.println(res_name);
	         	 String res_job=jsp.getString("job");
//		    	 System.out.println(res_job);
		    	 String res_updatedAt=jsp.getString("updatedAt");
//	    	     System.out.println(res_updatedAt);
		    	 
		    	 //validate responseBody parameters
		    	 Assert.assertEquals(res_name,"morpheus");
		    	 Assert.assertEquals(res_job,"zion resident");
		   
		    	 
		    	 //extract date from updatedAt parameter
		    	 String actual_date=res_updatedAt.substring(0,10);
		    	 String current_date=LocalDate.now().toString();   //create a date object
		    	 Assert.assertEquals(actual_date,current_date);
		     }
		
	}


