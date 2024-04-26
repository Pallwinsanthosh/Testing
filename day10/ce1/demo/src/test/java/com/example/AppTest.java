package com.example;



import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.rule.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{     
    Logger logger=Logger.getLogger(getClass());
    WebDriver driver;
    @org.testng.annotations.BeforeMethod
    public void BeforeMethod()
    {   
        PropertyConfigurator.configure("E:\\Users\\huzaif\\Selenium\\Log4j\\CW\\EX1\\demo\\src\\main\\java\\com\\example\\log4j2.properties");
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://www.moneycontrol.com/");
        logger.info("Open url");
        driver.findElement(By.xpath("/html/body/div/div[1]/span/a")).click();
    }
    @Test
    public void Test1()
    {  
        SoftAssert assert1=new SoftAssert();
        driver.findElement(By.xpath("//*[@id='search_str']")).sendKeys("Reliance Industries");
        logger.info("Locate and click on search bar & search Reliance Industries");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='autosuggestlist']/ul/li[1]/a")));
        driver.findElement(By.xpath("//*[@id='autosuggestlist']/ul/li[1]/a")).click();
        logger.info("Locate the primary suggestion");
        
            String name=driver.findElement(By.xpath("//*[@id='stockName']/h1")).getText();
            try{

                Assert.assertEquals(name,"Reliance Industries Ltd.");
                logger.info("Webpage contains the term Reliance Industries Ltd.");
            }
            catch(AssertionError e)
            {
                logger.error("Webpage doesn't contains Reliance Industries Ltd");
                Assert.fail("webpage doesn't conatins Relaince Industries Ltd");
            }
        Actions action=new Actions(driver);
        WebElement w1=driver.findElement(By.xpath("//*[@id=\"common_header\"]/div[1]/div[3]/nav/div/ul/li[10]"));
        action.moveToElement(w1).perform();
        logger.info("Hovering Has Been Done");
        try{

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='common_header']/div[1]/div[3]/nav/div/ul/li[10]/div/div/ul/li[2]/ul/li[5]/a")));
            driver.findElement(By.xpath("//*[@id='common_header']/div[1]/div[3]/nav/div/ul/li[10]/div/div/ul/li[2]/ul/li[5]/a")).click();
            logger.info("Sip Return has been clicked");
            assert1.assertTrue(true);
        }
        catch(NoSuchElementException e)
        {
            logger.error("Not Found");
            assert1.assertFalse(false);
        }
        WebElement web1=driver.findElement(By.xpath("//*[@id='ff_id']"));
        Select s1=new Select(web1);
        s1.selectByIndex(2);
        logger.info("Axis Mutual Fund Has Been Selected");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='im_id']")));
        WebElement web2=driver.findElement(By.xpath("//*[@id='im_id']"));
        Select s2=new Select(web2);
        s2.selectByIndex(1);
        logger.info("Axis Arbitrage Fund-Direct Plan D has been selected");
        driver.findElement(By.xpath("//*[@id='invamt']")).sendKeys("100,000");
        WebElement web3=driver.findElement(By.xpath("//*[@id='frq']"));
        Select s3=new Select(web3);
        s3.selectByIndex(0);
        logger.info("SIP Frequency:Monthly has been selected");
        driver.findElement(By.xpath("//*[@id='stdt']")).sendKeys("2021-08-02");
        logger.info("Start Date has been selected");
        driver.findElement(By.xpath("//*[@id='endt']")).sendKeys("2023-08-17");
        logger.info("End Date has been selected");
        driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[2]/form/div[8]/input")).click();
        logger.info("calculate button has been clcicked");
        String n=driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[1]/td[2]")).getText();
        System.out.println(n);
        String k=driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[3]/td[2]")).getText();
        System.out.println(k);





        
        
            


    }
}
