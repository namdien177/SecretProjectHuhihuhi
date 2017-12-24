package GUIClass.MainFunctionScene;

import DataObject.FunctionCustomized.ImageUploadFunction;
import DataObject.FunctionCustomized.MessageFunction;
import DataObject.User.UserClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.List;

public class MessageWindows {
    /*
    Class Variables
     */
    public static List<UserClass> CustomerZalo;
    private static String uploadFileID;

    /*
    GUI Variables
     */
    public JFXTextArea MessageContentInput;
    public JFXButton SendMessageBtn;
    public JFXButton CancelBtn;
    /*
    * Hàm xử lý nút SendMessageBtnClicked
    *
    * */
    @FXML
    private void SendMessageBtnClicked(){
        if (!CustomerZalo.isEmpty()){
            for (UserClass user :
                    CustomerZalo) {
                boolean resultMess = new MessageFunction().SendMessage(user,MessageContentInput.getText());

                if (resultMess && uploadFileID!=null){
                    System.out.println("Send Message Succeed for user: "+user.getUserId() +" | "+user.getDisplayName());
                    Stage oldStage = (Stage) SendMessageBtn.getScene().getWindow();        //get stage by btn
                    oldStage.close();
                }
                else
                    System.out.println("Send Message Failed for user: "+user.getUserId() +" | "+user.getDisplayName());
            }
        }
    }

    @FXML
    private void UploadImage(){
        uploadFileID = new ImageUploadFunction().getIDUploadPicture();
    }

    @FXML
    private void CloseBtnClicked(){
        Stage oldStage = (Stage) CancelBtn.getScene().getWindow();        //get stage by btn
        oldStage.close();
    }
}
