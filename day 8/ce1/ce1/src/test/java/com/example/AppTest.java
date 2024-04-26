package com.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    @BeforeMethod
    public void Before()
    {
        WebDriverManager.chromedriver().setup();
         driver= new ChromeDriver();
         driver.get("http://dbankdemo.com/bank/login");
         driver.manage().window().maximize();
    }
    @Test(dataProvider="ptd")
    public void test1(String username,String password)
    {
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='submit']")).click();
        if(driver.getCurrentUrl().contains("home"))
        {
            System.out.println("welcome");
        }
        else{
            
            System.out.println("move away");
        }
    }
    
    @Test(dataProvider = "ptd")
    public void test2(String username,String password) throws InterruptedException
    {
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='submit']")).click();
        driver.findElement(By.xpath("//*[@id='deposit-menu-item']")).click();
        Thread.sleep(3000);
        WebElement element=driver.findElement(By.xpath("//*[@id='selectedAccount']"));
        Select select=new Select(element);
        select.selectByIndex(1);
        driver.findElement(By.xpath("//*[@id='amount']")).sendKeys("5000");
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        Thread.sleep(100000);
        String str=driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")).getText();
        if(str.equals("$5000.00"))
        {
            System.out.println(str);
        }
    }
    @Test(dataProvider = "ptd")
    public void test3(String username,String password) throws InterruptedException
    {
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='submit']")).click();
        driver.findElement(By.xpath("//*[@id='withdraw-menu-item']")).click();
        Thread.sleep(3000);
        WebElement element=driver.findElement(By.xpath("//*[@id='selectedAccount']"));
        Select select =new Select(element);
        select.selectByIndex(1);
        driver.findElement(By.xpath("//*[@id='amount']")).sendKeys("3000");
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        String str=driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")).getText();
        if(str.equals("$3000.00"))
        {
            System.out.println("yes");
        }
    }
    Object[][] obj=new Object[1][2];
    @DataProvider(name ="ptd")
    public Object[][] ReturnData()
    {
       obj[0][0]="S@gmail.com";
       obj[0][1]="P@ssword12";
       return obj;
    }
    // @AfterMethod
    // public void close()
    // {
    //     driver.close();
    // }
}