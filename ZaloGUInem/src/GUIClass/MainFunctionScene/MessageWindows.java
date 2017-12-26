package GUIClass.MainFunctionScene;

import DataObject.FunctionCustomized.ImageUploadFunction;
import DataObject.FunctionCustomized.MessageFunction;
import DataObject.User.UserClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public class MessageWindows {
    /*
    Class Variables
     */
    public static List<UserClass> CustomerZalo;
    public static String AbsolutePath;
    public static String MessageContent;
    public static String PictureMessage;
    public static boolean StartSend = false;
    /*
    GUI Variables
     */
    public JFXTextArea MessageContentInput;
    public JFXButton SendMessageBtn;
    public JFXButton CancelBtn;
    public JFXTextField PicDesc;
    public ImageView MessagePicture;

    @FXML
    private void SendMessageBtnClicked(){
        StartSend = true;
        MessageContent = MessageContentInput.getText();
        PictureMessage = PicDesc.getText();
        Stage oldStage = (Stage) SendMessageBtn.getScene().getWindow();        //get stage by btn
        oldStage.close();
    }

    @FXML
    private void UploadImage(){
        AbsolutePath = new ImageUploadFunction().getAbsolutePathFile();
        if (AbsolutePath != null){
            File getImageFile = new File(AbsolutePath);
            try {
                MessagePicture.setImage(new Image(getImageFile.toURI().toURL().toExternalForm()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void CloseBtnClicked(){
        StartSend = false;
        AbsolutePath = null;
        Stage oldStage = (Stage) CancelBtn.getScene().getWindow();        //get stage by btn
        oldStage.close();
    }
}
