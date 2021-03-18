package es.urjc.code.daw.library.test.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Tests E2E de la API REST")
public class RestAPITest {
	
	@LocalServerPort
    int port;
	
	@BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

	@Test
	@DisplayName("Comprobar que al introducir un libro podemos recuperarlo")
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
	
	@Test
	@DisplayName("Comprobar que al borrar un libro no podemos recuperarlo")
	public void givenRestAPI_whenABookIsDeleted_thenGETDoesNotGiveTheBookBack() {
		//Given
    	Response response2 =
    	given().
    		contentType(ContentType.JSON).
            body("{ \"title\" : \"La colmena\", \"description\": \"En época de plena posguerra... \" }").
        when().
        	post("/api/books/").thenReturn();
    	
    	int id = from(response2.asString()).getInt("id");
    	
    	//When
    	given().
    		contentType(ContentType.JSON).
    	when().
    		delete("/api/books/{id}",id).
		//Then
    	then().
    		statusCode(200);
    	when()
			.get("/api/books/{id}",id).
		then()
			.statusCode(404);
	}
}
	
