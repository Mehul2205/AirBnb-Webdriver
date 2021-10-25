package ScreenShotFunctions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TakeAshotScreenshot {

    public static void TakeCompletePage(WebDriver driver, String fileWithPath){
        try{
            Screenshot screenshot = new AShot().takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "jpg", new File(fileWithPath));
        }catch (IOException e){
            System.out.println("File not Accessible");
        }
    }

    public static void TakeSingleElement(WebDriver driver, WebElement element, String fileWithPath) throws IOException {

        Object output = ((JavascriptExecutor) driver).executeScript("return window.devicePixelRatio");
        String value = String.valueOf(output);
        float windowDPR = Float.parseFloat(value);
        Screenshot screenshot= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(windowDPR),100)).coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver,element);
        ImageIO.write(screenshot.getImage(), "jpg", new File(fileWithPath));

    }

    public static void CompareImage(WebDriver driver, WebElement element, String fileWithPath) throws IOException {
        Screenshot logoSrcshot = new AShot().takeScreenshot(driver, element);

        // Reading the image for comparision
        BufferedImage expectedImage = ImageIO.read(new File(fileWithPath));
        BufferedImage actualImage = logoSrcshot.getImage();
        ImageDiffer img_differnece = new ImageDiffer();

        // Creating ImageDiffer object and calling the method makeDiff()

        ImageDiff differnece = img_differnece.makeDiff(actualImage, expectedImage);
        if (differnece.hasDiff() == true){        //Checking the difference using in-built functions)
            System.out.println("Both logo images matched"); //in case when no difference found
        }else {
            System.out.println("The logo images are different"); //in case when difference found
        }
    }

}
