package DataReader;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class readExcelData {

    public static Sheet readData(String path){
        try{
            FileInputStream fis =new FileInputStream(path);
            Workbook wb = new XSSFWorkbook(fis) ;//.xlsx
            Sheet sheet1=wb.getSheetAt(0);//select the particular sheet index(starting from 0)
            return sheet1;
        }catch (IOException e){
            System.out.println("File not Found!!");
            return null;
        }
    }
}