package DataObject.InOutObject;

import DataObject.User.UserClass;
import GUIClass.MainFunctionScene.MainFunctionWindows;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExportToExcel {
    MainFunctionWindows main = new MainFunctionWindows();
    private XSSFWorkbook wb = new XSSFWorkbook();
    private XSSFSheet sheet = wb.createSheet("User Information");;
    private XSSFRow header = sheet.createRow(0);
    private XSSFRow row = null;
    public ExportToExcel(){

    }
    public ExportToExcel(ObservableList<UserClass> list) throws IOException {
        row = sheet.createRow(0);
        row.createCell(0).setCellValue("UserID");
        row.createCell(1).setCellValue("Display Name");
        row.createCell(2).setCellValue("Gender");
        row.createCell(3).setCellValue("Phone");
        int index = 1;
        for (UserClass user:list
                ) {
            CreateRow(user,index);
            index++;
        }
        FileOutputStream fileOut = new FileOutputStream("UserDetail.xlsx");
        wb.write(fileOut);
        fileOut.close();
        System.out.println("export done!");

    }

    public void CreateRow(UserClass user, int Index){
        /*
        * Khởi tạo giá trị header cho file excel
        *Rownum mặc định là 0
        * */

        row = sheet.createRow(Index);
        row.createCell(0).setCellValue(user.getUserId());
        row.createCell(1).setCellValue(user.getDisplayName());
        row.createCell(2).setCellValue(user.getUserGender());
        row.createCell(3).setCellValue(user.getUserPhone());

    }


}
