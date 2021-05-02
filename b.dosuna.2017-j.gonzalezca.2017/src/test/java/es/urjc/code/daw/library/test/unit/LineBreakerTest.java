package es.urjc.code.daw.library.test.unit;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import es.urjc.code.daw.library.book.LineBreaker;

@DisplayName("Tests unitarios para el desarrollo guiado por pruebas")
class LineBreakerTest {
	LineBreaker lineBreaker;
	
	@ParameterizedTest(name = "Test del ejemplo {index}")
	@MethodSource("values")
	void absoluteTest(String input, int lineLength, String output) {
	
		//When
		String brokenLine = LineBreaker.breakLine(input, lineLength);
		
		//Then
		assertEquals(brokenLine,output);
	}
	
	public static Collection<Object[]> values() {
	    
		Object[][] data = {
			{""					   , 2, 						 ""},
			{"test"				   , 4, 					 "test"},
			{"test"				   , 5, 					 "test"},
			{"test test"		   , 4, 			   "test\ntest"},
			{"test test"	  	   , 5, 			   "test\ntest"},
			{"test test"		   , 6, 			   "test\ntest"},
			{"test test test test" , 9, 	 "test test\ntest test"},
			{"test  test"		   , 4, 			   "test\ntest"},
			{"test   test"		   , 6, 			   "test\ntest"},
			{"testtest"			   , 5, 			  "test-\ntest"},
			{"testtesttest"		   , 5, 	   "test-\ntest-\ntest"},
			{"test test"		   , 3, 		 "te-\nst\nte-\nst"},
			{"test 1234567 test"   , 6,    "test\n12345-\n67\ntest"},
			{"123456789"		   , 3, 	   "12-\n34-\n56-\n789"}	
		};

	    return Arrays.asList(data);
	}

}

