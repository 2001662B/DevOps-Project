package com.sddevops.DevOpsProject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import necessary Selenium WebDriver classes
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class NewTest {
    // declare Selenium WebDriver
    private WebDriver webDriver;

    @Test

    // shows all details of the users on dashboard
    public void checkTitle() {
        // Load website as a new page
        webDriver.navigate().to("http://localhost:8090/DevOpsProject/MovieServlet/dashboard");

        // Assert the title to check that we are indeed in the correct website
        Assert.assertEquals(webDriver.getTitle(), "List of Movies");

        System.out.println("title: " + webDriver.getTitle());

        // Retrieve link using it's class name and click on it
        webDriver.findElement(By.className("btnbtn-success")).click();

        // Assert the new title to check that the title contain Wikipedia and the button
        // had successfully bring us to the new page
        Assert.assertTrue(webDriver.getTitle().contains("Create a Movie"));
        System.out.println("new title: " + webDriver.getTitle());
    }

    @Test
    // add profile page form
    public void AddMovie() {
        webDriver.navigate().to("http://localhost:8090/DevOpsProject/createMovie.jsp");

        Assert.assertEquals(webDriver.getTitle(), "Create a Movie");

        System.out.println("title: " + webDriver.getTitle());

        WebElement title = webDriver.findElement(By.xpath("/html/body/form/input[1]"));

	        title.sendKeys("test title");

        WebElement director = webDriver.findElement(By.xpath("/html/body/form/input[2]"));

        director.sendKeys("test director");
        
        WebElement releaseDate = webDriver.findElement(By.xpath("/html/body/form/input[3]"));

        releaseDate.sendKeys("2022-02-06");
        
        WebElement plot = webDriver.findElement(By.xpath("/html/body/form/input[4]"));

        plot.sendKeys("test plot");

        webDriver.findElement(By.className("submitMovie")).click();
        webDriver.findElement(By.className("createMovie")).click();

        Assert.assertTrue(webDriver.getTitle().contains("List of Movies"));
        System.out.println("new title: " + webDriver.getTitle());

    }

    @Test
    public void Edit() {
        webDriver.navigate().to("http://localhost:8090/DevOpsProject/MovieServlet/dashboard");

        Assert.assertEquals(webDriver.getTitle(), "List of Movies");

        System.out.println("title: " + webDriver.getTitle());

        webDriver.findElement(By.xpath("/html/body/div/div/table/tbody/tr[1]/td[6]/a[1]")).click();

        WebElement title2 = webDriver.findElement(By.xpath("/html/body/div/div/div/form/fieldset[1]/input"));

        title2.sendKeys(Keys.chord(Keys.CONTROL, "a"), "title1");

        WebElement director2 = webDriver.findElement(By.xpath("/html/body/div/div/div/form/fieldset[2]/input"));

        director2.sendKeys(Keys.chord(Keys.CONTROL, "a"), "director1");
        
        WebElement releaseDate2 = webDriver.findElement(By.xpath("/html/body/div/div/div/form/fieldset[3]/input"));

        releaseDate2.sendKeys(Keys.chord(Keys.CONTROL, "a"), "releaseDate1");
        
        WebElement genre2 = webDriver.findElement(By.xpath("/html/body/div/div/div/form/fieldset[4]/input"));

        genre2.sendKeys(Keys.chord(Keys.CONTROL, "a"), "genre1");
        
        WebElement plot2 = webDriver.findElement(By.xpath("/html/body/div/div/div/form/fieldset[5]/input"));

        plot2.sendKeys(Keys.chord(Keys.CONTROL, "a"), "plot1");
        

        webDriver.findElement(By.className("editMovie")).click();

        Assert.assertTrue(webDriver.getTitle().contains("List of Movies"));
        System.out.println("new title: " + webDriver.getTitle());
    }

    @Test
    public void Delete() {
        webDriver.navigate().to("http://localhost:8090/DevOpsProject/MovieServlet/dashboard");

        Assert.assertEquals(webDriver.getTitle(), "List of Movies");

        webDriver.findElement(By.xpath("/html/body/div")).click();
    }

    @BeforeTest
    public void beforeTest() {
        // Setting system properties of ChromeDriver
        // to amend directory path base on your local file path
        String chromeDriverDir = "C:\\Program Files\\Google\\Chrome\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriverDir);

        // initialize FirefoxDriver at the start of test
        webDriver = new ChromeDriver();
    }

    @AfterTest
    public void afterTest() {
        // Quit the ChromeDriver and close all associated window at the end of test
        webDriver.close();
    }

}