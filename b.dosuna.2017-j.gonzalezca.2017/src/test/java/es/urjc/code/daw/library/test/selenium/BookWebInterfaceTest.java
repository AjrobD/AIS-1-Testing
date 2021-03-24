package es.urjc.code.daw.library.test.selenium;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
class BookWebInterfaceTest {
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
	void whenBookIsAdded_thenBookCanBeFound() {
		//Given
		driver.get("http://localhost:"+this.port+"/");
		
		//When
		driver.findElement(By.xpath("//button")).click();
		
		driver.findElement(By.name("title")).sendKeys("Hansel y Gretel");
		driver.findElement(By.name("description")).sendKeys("Cuento sobre dos niños en un bosque");
	
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		List<WebElement> buttonList = driver.findElements(By.xpath("//button"));
		buttonList.get(2).click();
		
		//Then
		assertNotNull(driver.findElement(By.partialLinkText("Hansel")));
	
	}
	
	@Test
	@DisplayName("Borrar un libro y comprobar que no existe")
	void whenBookIsDeleted_thenCannotBeFound() {
		
		//Given
		driver.get("http://localhost:"+this.port+"/");
		driver.findElement(By.xpath("//button")).click();	
		driver.findElement(By.name("title")).sendKeys("Las mil y una noches");
		driver.findElement(By.name("description")).sendKeys("recopilación medieval de cuentos de Oriente Medio");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//When
		List<WebElement> buttonList = driver.findElements(By.xpath("//button"));
		buttonList.get(0).click();
		
		//Then
		List<WebElement> webElements = driver.findElements(By.partialLinkText("books"));
		List<String> listaStr = new ArrayList<>();
		for (WebElement elem: webElements)
			listaStr.add(elem.getText());
		boolean bookFound = driver.findElements(By.partialLinkText("books"))
                .stream()
                .anyMatch(element -> element.getText().equalsIgnoreCase("Las mil y una noches"));
        assertTrue(!bookFound, "Page shouldn't contain the removed book");
	}

}
