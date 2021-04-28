package es.urjc.code.daw.library.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.urjc.code.daw.library.book.LineBreaker;

@DisplayName("Tests unitarios para el desarrollo guiado por pruebas")
public class LineBreakerTest {
	LineBreaker lineBreaker;
	
	@Test
	@DisplayName("Comprobar que cuando entra un string vacio sale un string vacio")
	void givenEmptyString_whenLineBreaker_thenReturnEmptyString() {
		//Given
		String input = "";
		//When
		String output = LineBreaker.breakText(input, 2);
		//Then
		String expected = "";
		assertEquals(expected,output);
	}
	
	@Test
	@DisplayName("Comprobar que cuando entra un string que no hace falta partir sale igual")
	void givenTestStringAnd4Characters_whenLineBreaker_thenReturnSameString() {
		//Given
		String input = "test";
		//When
		String output = LineBreaker.breakText(input, 4);
		//Then
		String expected = "test";
		assertEquals(expected,output);
	}
	
	@Test
	@DisplayName("Comprobar que cuando entra un string que no hace falta partir sale igual")
	void givenTestStringAnd5Characters_whenLineBreaker_thenReturnSameString() {
		//Given
		String input = "test";
		//When
		String output = LineBreaker.breakText(input, 5);
		//Then
		String expected = "test";
		assertEquals(expected,output);
	}
	
	@Test
	@DisplayName("Comprobar que cuando entra un string con dos palabras sale cortado en dos")
	void givenTestWSTestStringAnd4Characters_whenLineBreaker_thenReturnBrokenString() {
		//Given
		String input = "test test";
		//When
		String output = LineBreaker.breakText(input, 4);
		//Then
		String expected = "test\ntest";
		assertEquals(expected,output);
	}
	
	@Test
	@DisplayName("Comprobar que cuando entra un string con dos palabras sale cortado en dos")
	void givenTestWSTestStringAnd5Characters_whenLineBreaker_thenReturnBrokenString() {
		//Given
		String input = "test test";
		//When
		String output = LineBreaker.breakText(input, 5);
		//Then
		String expected = "test\ntest";
		assertEquals(expected,output);
	}
	
	@Test
	@DisplayName("Comprobar que cuando entra un string con dos palabras sale cortado en dos")
	void givenTestWSTestStringAnd6Characters_whenLineBreaker_thenReturnBrokenString() {
		//Given
		String input = "test test";
		//When
		String output = LineBreaker.breakText(input, 6);
		//Then
		String expected = "test\ntest";
		assertEquals(expected,output);
	}
	
	
	@Test
	@DisplayName("Comprobar que cuando entra un string con cuatro palabras sale cortado en dos")
	void givenTestWSTestStringAnd9Characters_whenLineBreaker_thenReturnBrokenString() {
		//Given
		String input = "test test test test";
		//When
		String output = LineBreaker.breakText(input, 9);
		//Then
		String expected = "test test\ntest test";
		assertEquals(expected,output);
	}
	
	@Test
	@DisplayName("Comprobar que cuando entra un string con 2  espacios sale cortado en dos con espacios unicos")
	void givenTestStringWithTwoConsecutiveSpacesBetweenWordsAnd4Chars_whenLineBreaker_thenReturnBrokenString() {
		//Given
		String input = "test  test";
		//When
		String output = LineBreaker.breakText(input, 4);
		//Then
		String expected = "test\ntest";
		assertEquals(expected,output);
	}
	
	@Test
	@DisplayName("Comprobar que cuando entra un string con 3 espacios sale cortado en dos con espacios unicos")
	void givenTestStringWithTwoConsecutiveSpacesBetweenWordsAnd6Chars_whenLineBreaker_thenReturnBrokenString() {
		//Given
		String input = "test   test";
		//When
		String output = LineBreaker.breakText(input, 6);
		//Then
		String expected = "test\ntest";
		assertEquals(expected,output);
	}
	
	@Test
	@DisplayName("Comprobar que cuando entra un string sin espacios y dos palabras sale cortado en dos")
	void givenTestStringWithoutSpacesAndTwoWords_whenLineBreaker_thenReturnBrokenString() {
		//Given
		String input = "testtest";
		//When
		String output = LineBreaker.breakText(input, 5);
		//Then
		String expected = "test-\ntest";
		assertEquals(expected,output);
	}
	
	@Test
	@DisplayName("Comprobar que cuando entra un string sin espacios y tres palabras sale cortado en tres")
	void givenTestStringWithoutSpacesAndThreeWords_whenLineBreaker_thenReturnBrokenString() {
		//Given
		String input = "testtesttest";
		//When
		String output = LineBreaker.breakText(input, 5);
		//Then
		String expected = "test-\ntest-\ntest";
		assertEquals(expected,output);
	}
	
	@Test
	@DisplayName("Comprobar que cuando entra un string con palabras mas grandes que la longitud de linea el corte funciona")
	void givenTestStringWithWordsLongerThanGivenLength_whenLineBreaker_thenReturnBrokenString() {
		//Given
		String input = "test test";
		//When
		String output = LineBreaker.breakText(input, 3);
		//Then
		String expected = "te-\nst\nte-\nst";
		assertEquals(expected,output);
	}
	
	
	
	
	
	
	
}
