package GUIClass.MainFunctionScene;

import DataObject.FunctionCustomized.ImageUploadFunction;
import DataObject.FunctionCustomized.MessageFunction;
import DataObject.User.UserClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.List;

public class MessageWindows {
    /*
    Class Variables
     */
    public static List<UserClass> CustomerZalo;
    private static String AbsolutePath;

    /*
    GUI Variables
     */
    public JFXTextArea MessageContentInput;
    public JFXButton SendMessageBtn;
    public JFXButton CancelBtn;
    public JFXTextField PicDesc;

    @FXML
    private void SendMessageBtnClicked(){
        if (!CustomerZalo.isEmpty()){
            for (UserClass user :
                    CustomerZalo) {
                boolean resultMess = new MessageFunction().SendMessage(user,MessageContentInput.getText());
                boolean resultImg = false;
                if (AbsolutePath != null){
                    System.out.println("step 1");
                    resultImg = new ImageUploadFunction().SendImage_Gif(user,PicDesc.getText(),AbsolutePath);
                }
                System.out.println("step 2");
                if (resultMess && resultImg){
                    System.out.println("Send Message + Image Succeed for user: "+user.getUserId() +" | "+user.getDisplayName());
                    Stage oldStage = (Stage) SendMessageBtn.getScene().getWindow();        //get stage by btn
                    oldStage.close();
                }else if (resultMess && !resultImg){
                    System.out.println("step 3");
                    System.out.println("Send Message Succeed but failed Image for user: "+user.getUserId() +" | "+user.getDisplayName());
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
        AbsolutePath = new ImageUploadFunction().getIDUploadPicture();
    }

    @FXML
    private void CloseBtnClicked(){
        Stage oldStage = (Stage) CancelBtn.getScene().getWindow();        //get stage by btn
        oldStage.close();
    }
}
