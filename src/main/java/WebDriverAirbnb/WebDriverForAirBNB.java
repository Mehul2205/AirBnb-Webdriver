package WebDriverAirbnb;

import DataReader.readExcelData;
import ScreenShotFunctions.TakeAshotScreenshot;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class WebDriverForAirBNB {


    // Select Respective browser based upon the value
    public static WebDriver setBrowser(int selectDriver){
        WebDriver driver;
        if(selectDriver == 0) {

            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();

        }else if(selectDriver == 1){

            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();

        }else{
            System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
            driver = new EdgeDriver();
        }
        return driver;
    }

    // Code to accept the cookies pop-up
    public static void AcceptCookies(WebDriver driver){
        // Accepting Cookies and Wait
        By cookies_accept = By.xpath("//*[@data-testid=\"accept-btn\"]");
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        ((WebElement)wait.until(ExpectedConditions.elementToBeClickable(cookies_accept))).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookies_accept));
    }

    // Code for selecting the start date for booking
    public static void SelectStartDateOfBooking(Date startDate, WebDriver driver, Actions ac){
        if(startDate.getYear()+1900 == LocalDate.now().getYear()){
            if(startDate.getMonth()+1 == LocalDate.now().getMonthValue()){
                for(int i=1; i<7; i++){
                    for(int j=1; j<8; j++){
                        //System.out.println(new String().valueOf(startDate.getDate()));
                        if(driver.findElements(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]//div")).size()!=0){
                            //System.out.println(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]/div/div/div")).getText()+":"+driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]/div/div/div")).getText().length());
                            if(new String().valueOf(startDate.getDate()).equals(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]/div/div/div")).getText())){
                                ac.click(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]//div[@class='_1lds9wb'][1]//table//tr["+i+"]//td["+j+"]"))).build().perform();
                                break;
                            }
                        }
                    }
                }
            }else if(startDate.getMonth()+1 > LocalDate.now().getMonthValue()){
                int numOfClicks = startDate.getMonth()+1-LocalDate.now().getMonthValue();
                for(int i=0; i<numOfClicks; i++){
                    ac.click(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[1]/div[2]/div/button"))).build().perform();
                }
                for(int i=1; i<7; i++){
                    for(int j=1; j<8; j++){
                        //System.out.println(new String().valueOf(startDate.getDate()));
                        if(driver.findElements(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]//div")).size()!=0){
                            //System.out.println(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]/div/div/div")).getText()+":"+driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]/div/div/div")).getText().length());
                            if(new String().valueOf(startDate.getDate()).equals(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]/div/div/div")).getText())){
                                ac.click(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]//div[@class='_1lds9wb'][1]//table//tr["+i+"]//td["+j+"]"))).build().perform();
                                break;
                            }
                        }
                    }
                }
            }else{
                System.out.println("Error");
            }
        }else if(startDate.getYear()+1900 > LocalDate.now().getYear()){
            int getYearsAhead = startDate.getYear()+1900 - LocalDate.now().getYear();
            int numOfClicks = getYearsAhead*12 + startDate.getMonth()+1 - LocalDate.now().getMonthValue();
            for(int i=0; i<numOfClicks; i++){
                ac.click(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[1]/div[2]/div/button"))).build().perform();
            }
            for(int i=1; i<7; i++){
                for(int j=1; j<8; j++){
                    //System.out.println(new String().valueOf(startDate.getDate()));
                    if(driver.findElements(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]//div")).size()!=0){
                        //System.out.println(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]/div/div/div")).getText()+":"+driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]/div/div/div")).getText().length());
                        if(new String().valueOf(startDate.getDate()).equals(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]/div/div/div")).getText())){
                            ac.click(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]//div[@class='_1lds9wb'][1]//table//tr["+i+"]//td["+j+"]"))).build().perform();
                            break;
                        }
                    }
                }
            }
        }else{
            System.out.println("Error Date Invalid");
        }


    }

    // This adds all the details required for booking a hotel
    public static void AddDetailsforBooking(WebDriver driver, Row row1) throws InterruptedException {
        Actions ac = new Actions(driver);


        // Adding value to First Field (Location)
        driver.findElement(By.xpath("//div[@class='_gor68n']//input")).sendKeys(new CharSequence[]{row1.getCell(1).toString()});
        Thread.sleep(3000L);
        ac.moveToElement(driver.findElement(By.xpath("//div[@class='_zusf8ed']//div[@class='_mnnz9u']"))).build().perform();
        ac.sendKeys(new CharSequence[]{Keys.DOWN, Keys.ENTER}).build().perform();

        Date startDate = row1.getCell(2).getDateCellValue();
        Date endDate = row1.getCell(3).getDateCellValue();

        if(endDate.compareTo(startDate) == -1){
            System.out.println("Error!! End Date is less than Start Date");
        }else{

            WebDriverForAirBNB.SelectStartDateOfBooking(startDate, driver, ac);

            if(startDate.getMonth() == endDate.getMonth()){
                for(int i=1; i<7; i++) {
                    for (int j = 1; j < 8; j++) {
                        if (driver.findElements(By.xpath("//*[@id=\"panel--tabs--0\"]//div[@class='_1lds9wb'][1]//table/tbody/tr[" + i + "]/td[" + j + "]//div")).size() != 0) {
                            if (new String().valueOf(endDate.getDate()).equals(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]//div[@class='_1lds9wb'][1]//table/tbody/tr[" + i + "]/td[" + j + "]/div/div/div")).getText())) {
                                ac.click(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]//div[@class='_1lds9wb'][1]//table//tr[" + i + "]//td[" + j + "]"))).build().perform();
                                break;
                            }
                        }
                    }
                }
            }else if(startDate.getMonth()+1 == endDate.getMonth()){
                for(int i=1; i<7; i++) {
                    for (int j = 1; j < 8; j++) {
                        if (driver.findElements(By.xpath("//*[@id=\"panel--tabs--0\"]//div[@class='_1lds9wb'][2]//table/tbody/tr[" + i + "]/td[" + j + "]//div")).size() != 0) {
                            if (new String().valueOf(endDate.getDate()).equals(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]//div[@class='_1lds9wb'][2]//table/tbody/tr[" + i + "]/td[" + j + "]/div/div/div")).getText())) {
                                ac.click(driver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]//div[@class='_1lds9wb'][2]//table//tr[" + i + "]//td[" + j + "]"))).build().perform();
                                break;
                            }
                        }
                    }
                }

            }else{
                System.out.println("Error! More that 30 days of stay is invalid");
            }
        }

        int numsOfAdults = (int) row1.getCell(4).getNumericCellValue();
        int numsOfChild = (int) row1.getCell(5).getNumericCellValue();
        int numsOfInfants = (int) row1.getCell(6).getNumericCellValue();;
        // Adding guest Details and Submitting
        ac.click(driver.findElement(By.xpath("//*[@id=\"search-tabpanel\"]/div/div[5]/div[1]/div"))).build().perform();

        for(int i=0; i<numsOfAdults; i++)
            ac.click(driver.findElement(By.xpath("//*[@id=\"stepper-adults\"]/button[2]"))).build().perform();
        for(int i=0; i<numsOfChild; i++)
            ac.click(driver.findElement(By.xpath("//*[@id=\"stepper-children\"]/button[2]"))).build().perform();
        for(int i=0; i<numsOfInfants; i++)
            ac.click(driver.findElement(By.xpath("//*[@id=\"stepper-infants\"]/button[2]"))).build().perform();

        driver.findElement(By.xpath("//*[@id=\"search-tabpanel\"]//div[@class='_w64aej']/button")).click();
        Thread.sleep(3000L);

    }

    // This is a main Function
    public static void main(String[] args) throws InterruptedException, IOException {

        System.out.println("Execution Starts-");
        // Initialization of Driver;
        WebDriver driver;

        // Set this to 0 -> Chrome, 1-> Firefox, anything else-> Edge
        int selectDriver = 0;
        driver = WebDriverForAirBNB.setBrowser(selectDriver);

        // Initialization
        Sheet sheet;
        Row rowFirst = null;
        Cell cell1 = null;
        try{
            sheet = readExcelData.readData("src/main/resources/data/Data.xlsx");
            rowFirst = sheet.getRow(1);
            cell1 = rowFirst.getCell(0);
        }catch(Exception e){
            System.out.println("File not found");
        }
        //System.out.println(cell1.toString());
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(cell1.toString());

        // Call Function which Accept Cookies
        WebDriverForAirBNB.AcceptCookies(driver);
        Thread.sleep(3000);

        // Calls function which add Details of booking
        WebDriverForAirBNB.AddDetailsforBooking(driver, rowFirst);

        Thread.sleep(3000);
        int ApartmentListing = driver.findElements(By.xpath("//*[@id=\"site-content\"]//div[@class=\"_8ssblpx\"]")).size();

        // Java Script Executor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i=1; i<=ApartmentListing; i++){
            TakeAshotScreenshot.TakeSingleElement(driver, driver.findElement(By.xpath("//*[@id=\"site-content\"]//div[@class=\"_8ssblpx\"]["+i+"]")),"src/main/resources/images/hotels/hotel"+i+".jpg");

            String a = driver.findElement(By.xpath("//*[@class=\"_8ssblpx\"]["+i+"]//div[@class=\"_12oal24\"]//div[@class='_5kaapu']//span")).getText();
            String b = driver.findElement(By.xpath("//*[@class=\"_8ssblpx\"][1]//div[@class=\"_12oal24\"]//div[@class='_3c0zz1']//span[3]")).getText() + " | " + driver.findElement(By.xpath("//*[@class=\"_8ssblpx\"][1]//div[@class=\"_12oal24\"]//div[@class='_3c0zz1']//span[7]")).getText();
            int getSize = driver.findElements(By.xpath("//*[@class=\"_8ssblpx\"][1]//div[@class='_12oal24']//div[@class='_h34mg6']")).size();
            String c;
            if(getSize == 2)
                c = driver.findElement(By.xpath("//*[@class=\"_8ssblpx\"]["+i+"]//div[@class=\"_12oal24\"]//div[@class='_1hxyyw3']//span[2]")).getText();
            else
                c = "No Rating";
            String d = driver.findElement(By.xpath("//*[@class=\"_8ssblpx\"]["+i+"]//div[@class=\"_12oal24\"]//div[@class='_1jo4hgw']//span[1]")).getText() + driver.findElement(By.xpath("//*[@class=\"_8ssblpx\"]["+i+"]//div[@class=\"_12oal24\"]//div[@class='_1jo4hgw']//span[2]")).getText();

            System.out.println("--------------------------");
            System.out.println(i+". "+a);
            System.out.println(b);
            System.out.println("Rating - "+ c + ", Price - "+ d);
            System.out.println("--------------------------");

        }
        js.executeScript("alert('Code ran successfully');");
        Thread.sleep(1000);
        System.out.println("Execution Ends.");
        driver.quit();

    }
}