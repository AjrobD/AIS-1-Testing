package es.urjc.code.daw.library.test.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

import es.urjc.code.daw.library.Application;
import io.github.bonigarcia.wdm.WebDriverManager;


@SpringBootTest(
classes = Application.class, 
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@DisplayName("Tests E2E de la interfaz web")
public class SeleniumTest {
	@LocalServerPort
	int port;
	private WebDriver driver;
	
	@BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
	}
	
	@AfterEach
	public void teardown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
	@Test
	@DisplayName("Incluir un nuevo libro y comprobar que se ha creado")
	public void whenBookIsAdded_thenBookBeFound() {
		
	}
	
	@Test
	@DisplayName("Borrar un libro y comprobar que no existe")
	public void whenBookIsDeleted_thenCannotBeFound() {
		
		//Given
		driver.get("http://localhost:"+this.port+"/");
		String bookName= "Las mil y una noches";
		//aqui falta la logica de añadir un libro, en cuanto eso este se sustituye "casi sin querer" por bookName en el resto del codigo

		//When
		driver.findElement(By.linkText("CASI SIN QUERER")).click();
		List<WebElement> buttonList = driver.findElements(By.xpath("//button"));
		buttonList.get(0).click();
		
		//Then
		List<WebElement> webElements = driver.findElements(By.partialLinkText("books"));
		List<String> listaStr = new ArrayList<>();
		for (WebElement elem: webElements)
			listaStr.add(elem.getText());
		boolean bookFound = driver.findElements(By.partialLinkText("books"))
                .stream()
                .anyMatch(element -> element.getText().equalsIgnoreCase("Casi sin querer"));
        assertTrue(!bookFound, "Page shouldn't contain the removed book"); //should fail if book is on repository
	}

}
