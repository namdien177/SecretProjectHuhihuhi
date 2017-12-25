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
    public static ObservableList<UserClass> getCustomer;

    /*
    Export as Excel
     */
    @FXML
    private void ExportBtn() throws IOException {
        if (getCustomer != null){
            ExportToExcel ex = new ExportToExcel(getCustomer,excelNameInput.getText());
        }
    }

    @FXML
    private void ExitBtnPress(){
        Stage thisStage = (Stage) abortBtn.getScene().getWindow();
        thisStage.close();
    }
}
