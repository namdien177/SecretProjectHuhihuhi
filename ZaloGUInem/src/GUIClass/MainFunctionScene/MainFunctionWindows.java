package GUIClass.MainFunctionScene;

import DataObject.FunctionCustomized.SplitPhonenumber;
import DataObject.FunctionCustomized.UserFunction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.Objects;

public class MainFunctionWindows {
    public JFXTextArea PhoneField;
    public JFXButton AddBtn;
    public JFXButton ExitBtn;
    public TableView ListCustomer;
    public JFXButton ExportBtn;
    public JFXButton SendMessageBtn;

    String[] UserPhoneList = null;
    @FXML
    private void GetPhoneNumber(){
        String getText = PhoneField.getText();
        if (getText == null || Objects.equals(getText, "")){
            System.out.println("dit");
        }else{
            UserPhoneList = new SplitPhonenumber().SplitPhone(getText);
            for (String Phonenumber :
                    UserPhoneList) {
                if (!Phonenumber.contains("S")){
                    System.out.println(Phonenumber);
                    UserFunction userFunction = new UserFunction();
                    userFunction.GetUserInformation(Phonenumber);
                }
            }
        }
    }
}
