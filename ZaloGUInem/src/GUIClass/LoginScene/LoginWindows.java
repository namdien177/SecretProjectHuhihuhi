package GUIClass.LoginScene;

import DataObject.Initialize_Zalo_Subject.ZaloOAAccess;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindows implements Initializable{
    public JFXButton LoginBtn;
    public Label UsernameLabel;
    public AnchorPane thisAnchorPane;


    /*
    Loading Username from Zalo OA
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String name = "3093468968868500357";
        UsernameLabel.setText(name);
    }

    /*
    Login button. On click Action.
     */
    @FXML
    protected void LoginProceed() throws IOException {
        AnchorPane mainfucnt = FXMLLoader.load(getClass().getResource("../MainFunctionScene/MainFunctionWindows.fxml"));
        thisAnchorPane.getChildren().setAll(mainfucnt);
    }
}
