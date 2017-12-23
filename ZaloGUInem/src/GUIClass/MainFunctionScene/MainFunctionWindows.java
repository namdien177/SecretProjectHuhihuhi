package GUIClass.MainFunctionScene;

import DataObject.FunctionCustomized.SplitPhonenumber;
import DataObject.FunctionCustomized.UserFunction;
import DataObject.User.UserClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainFunctionWindows implements Initializable {
    /*
    GUI Variables
     */
    public JFXTextArea PhoneField;
    public JFXButton AddBtn;
    public JFXButton ExitBtn;
    public JFXButton ExportBtn;
    public JFXButton SendMessageBtn;
    public TableView<UserClass> ListCustomer;

    /*
    Class Variables
     */
    private String[] UserPhoneList = null;

    /*
    Method [On Action] lấy toàn bộ text từ textarea và tách nó thành các số điện thoại hoàn chỉnh để đưa vào list
    và thực hiện thao tác tìm kiếm trên zalo (thông tin user)
     */
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

    /*
    Hàm khởi tạo chạy lệnh trước khi hiện view cho người sử dụng.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
