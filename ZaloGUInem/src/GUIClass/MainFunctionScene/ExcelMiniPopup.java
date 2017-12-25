package GUIClass.MainFunctionScene;

import DataObject.InOutFunction.ExportToExcel;
import DataObject.User.UserClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class ExcelMiniPopup {
    /*
    GUI variables
     */
    public JFXTextField excelNameInput;
    public JFXButton exportBtn;
    public JFXButton abortBtn;

    /*
    Class Variables
     */
    public static String namefile = null;
    public static boolean exportcondtion = false;
    /*
    Export as Excel
     */
    @FXML
    private void ExportBtn() {
        namefile = excelNameInput.getText();
        exportcondtion = true;
        Stage thisStage = (Stage) abortBtn.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    private void ExitBtnPress(){
        exportcondtion = false;
        namefile = null;
        Stage thisStage = (Stage) abortBtn.getScene().getWindow();
        thisStage.close();
    }
}
