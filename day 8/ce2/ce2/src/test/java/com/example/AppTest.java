package com.example;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;

import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;
    @BeforeMethod
    public void open()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
    }
    @Test(priority = 1)
    public void test1() throws InterruptedException
    {
        driver.findElement(By.linkText("Laptops")).click();
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,2000)");  
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='tbodyid']/div[3]/div/div/h4/a")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='tbodyid']/div[2]/div/a")).click();
        Thread.sleep(3000);
        
        driver.switchTo().alert().accept();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='cartur']")).click();
        Thread.sleep(3000);
        String a=driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]")).getText();
        String b=driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[3]")).getText();
        System.out.println(a);
        System.out.println(b);
    } 
    @Test(dataProvider = "apt")
    public void test2(String username,String password) throws InterruptedException
    {
        driver.findElement(By.xpath("//*[@id='login2']")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='logInModal']/div/div/div[3]/button[2]")).click();
    }
    Object[][]obj=new Object[1][2];
    @DataProvider(name="apt")
    public Object[][] DataProvider() throws IOException
    {
        FileInputStream fi=new FileInputStream("C:\\Users\\allwin santhosh\\Desktop\\sampledata for testing.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(fi);
        XSSFSheet sheet=workbook.getSheetAt(0);
        int row=sheet.getLastRowNum();
        int col=sheet.getRow(0).getLastCellNum();
        Row r=sheet.getRow(0);
        obj[0][0]=r.getCell(0).getStringCellValue();
        obj[0][1]=r.getCell(1).getStringCellValue();
        return obj;      
    }
    // @AfterMethod
    // public void close()
    // {
    //     driver.close();
    // }
}
