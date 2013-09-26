
package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {
    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        
        driver.get("http://localhost:8080");
        newUser(driver);
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click(); 
        
        System.out.println("==");
        
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys("jussi");
        element = driver.findElement(By.name("password"));
        element.sendKeys("qwerty1");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
        
    }

    private static void newUser(WebDriver driver) {
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click(); 
        
        System.out.println("==");
        
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys("jussi");
        element = driver.findElement(By.name("password"));
        element.sendKeys("qwerty1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("qwerty1");
        
        
        element = driver.findElement(By.name("add"));
        element.submit();
        element = driver.findElement(By.linkText("back to home"));
        element.click();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
    }
}
