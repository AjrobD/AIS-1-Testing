package es.urjc.code.daw.library.test.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAPITest {
	
	@LocalServerPort
    int port;
	
	@BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

	@Test
	public void givenRestAPI_whenABookIsAdded_thenGETGivesTheBookBack() {
		//Given
    	Response response1 =
    	given().
    		contentType(ContentType.JSON).
            body("{ \"title\" : \"Robin Hood\", \"description\": \"Trata sobre un arquero que... \" }").
        when().
        	post("/api/books/").thenReturn();
    	
    	int id = from(response1.asString()).getInt("id");
    	
    	//When
    	given().
    		contentType(ContentType.JSON).
    	when().
    		get("/api/books/{id}",id).
		//Then
    	then().
    		statusCode(200).
    		body("id",equalTo(id),
    				"description",containsString("arquero"));	
	}
}
	
