package GUIClass.MainFunctionScene;

import DataObject.FunctionCustomized.SplitPhonenumber;
import DataObject.FunctionCustomized.UserFunction;
import DataObject.InOutFunction.ExportToExcel;
import DataObject.User.UserClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainFunctionWindows implements Initializable {
    //  Test number: 01664708402
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    GUI Variables
     */
    public JFXTextArea PhoneField;
    public JFXButton AddBtn;
    public JFXButton ExitBtn;
    public JFXButton ExportBtn;
    public JFXButton SendMessageBtn;
    public TableView<UserClass> ListCustomer;
    public AnchorPane rootPane;
    public JFXProgressBar progressBar;

    /////////////////////////Setup columns of List customer//////////////////////////////
    @FXML
    TableColumn<UserClass,Long> IDCustCol = new TableColumn<>("Customer ID");

    @FXML
    TableColumn<UserClass,String> CustNameCol = new TableColumn<>("Customer name");

    @FXML
    TableColumn<UserClass,String> GenderCol = new TableColumn<>("Gender");

    @FXML
    TableColumn<UserClass,String> PhoneCustCol = new TableColumn<>("Phone number");

    /////////////////////////////////////////////////////////////////////////////////////
    /*
    Class Variables
     */
    private String[] UserPhoneList = null;
    private List<UserClass> ListCustomerFound = new ArrayList<>();
    private static double ProgressPercentage = 0;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    Hàm khởi tạo chạy lệnh trước khi hiện view cho người sử dụng.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ////////////////////////////////////////////////////////////////////////////
        /////////////////////////////TABLE CUSTOMER DATA SETUP//////////////////////
        IDCustCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        CustNameCol.setCellValueFactory(new PropertyValueFactory<>("displayName"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("userGender"));
        PhoneCustCol.setCellValueFactory(new PropertyValueFactory<>("UserPhone"));
        /////////////////////////////CUSTOMIZE COL /////////////////////////////////
        CustNameCol.setMaxWidth(4000);
        GenderCol.setMaxWidth(2600);
        PhoneCustCol.setMaxWidth(4000);
        GenderCol.setStyle("-fx-alignment: CENTER");
        CustNameCol.setStyle("-fx-alignment: CENTER");
        PhoneCustCol.setStyle("-fx-alignment: CENTER");
        /////////////////////////////SORTING SETUP//////////////////////////////////
        CustNameCol.setSortType(TableColumn.SortType.ASCENDING);
        /////////////////////////////ADDING COLUMN TO LIST//////////////////////////
        if (ListCustomer.getColumns().isEmpty()){
            ListCustomer.getColumns().addAll(IDCustCol,CustNameCol, GenderCol, PhoneCustCol);
        }else {
            ListCustomer.getColumns().clear();
            ListCustomer.getColumns().addAll(IDCustCol,CustNameCol, GenderCol, PhoneCustCol);
        }
        /////////////////////////////ADDING LISTENER////////////////////////////////
        /*ListCustomer.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->{
            if (newValue != null){
                GetOneCustomer(newValue.getUserId());       //Use to send mess to one customer
                SendMessageBtn.setDisable(false);
            }else {
                SendMessageBtn.setDisable(true);
            }
        }));*/
        ListCustomer.getItems().addListener((ListChangeListener<UserClass>) c -> {
            if (!ListCustomer.getItems().isEmpty()) {
                SendMessageBtn.setDisable(false);
                ExportBtn.setDisable(false);
            } else {
                SendMessageBtn.setDisable(true);
                ExportBtn.setDisable(true);
            }
        });
        /////////////////////////////END SETUP TABLE////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////

        /*
        Adding listener cho textarea để enable button.

        PhoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null || newValue != ""){
                AddBtn.setDisable(false);
            }else{
                AddBtn.setDisable(true);
            }
        });*/
        PhoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (observable == null){
                AddBtn.setDisable(true);
            }else {
                AddBtn.setDisable(false);
            }
        });
    }

    public void setProgress (double percentage){
        progressBar.setProgress(percentage);
    }

    /*
    Method [On Action] lấy toàn bộ text từ textarea và tách nó thành các số điện thoại hoàn chỉnh để đưa vào list
    và thực hiện thao tác tìm kiếm trên zalo (thông tin user)
     */
    @FXML
    private void GetPhoneNumber(){
        ListCustomer.getItems().clear();
        ListCustomerFound.clear();
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
                    ListCustomerFound.add(userFunction.GetUserInformation(Phonenumber));
                }
            }
        }
        LoadCustomerListTable();    //Load list customer hiển thị lên bảng.
    }

    /*
    Get Observable List customer từ số điện thoại khi bấm nút.
     */
    private ObservableList<UserClass> getCustomer (){
        ObservableList<UserClass> obCarlist = FXCollections.observableArrayList();
        if (!ListCustomerFound.isEmpty()){
            for (UserClass c: ListCustomerFound) {
                obCarlist.addAll(c);
            }
        }
        return obCarlist;
    }

    /*
    Chuyển item của observable list vào trong table. Gọi hàm này để hiển thị lên bảng.
     */
    private void LoadCustomerListTable() throws NullPointerException{                                                                                        //
        ObservableList<UserClass> list = getCustomer();                                                               //
        ListCustomer.setItems(list);
    }

    /*
    Lấy thông tin của 1 người dùng (có thể không dùng nữa)
     */
    private void GetOneCustomer(long userId) {

    }

    /*
    Send Message to all customer on list
     */
    @FXML
    private void SendMessageToAll() throws IOException {
        if (!ListCustomerFound.isEmpty()){
            MessageWindows.CustomerZalo = ListCustomerFound;
            System.out.println("List does not empty");
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("MessageWindows.fxml"));
            Stage newstage = new Stage();
            newstage.setScene(new Scene(anchorPane));
            newstage.initStyle(StageStyle.UNDECORATED);
            newstage.initModality(Modality.APPLICATION_MODAL);
            newstage.showAndWait();
        }
    }

    @FXML
    private void ExportBtnPressed() throws IOException {
        if (!ListCustomerFound.isEmpty()){
            ObservableList<UserClass> obserb = FXCollections.observableArrayList();
            obserb.addAll(ListCustomerFound);
            ExcelMiniPopup.getCustomer = obserb;
            System.out.println("List does not empty");
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("ExcelMiniPopup.fxml"));
            Stage newstage = new Stage();
            newstage.setScene(new Scene(anchorPane));
            newstage.initStyle(StageStyle.UNDECORATED);
            newstage.initModality(Modality.APPLICATION_MODAL);
            newstage.showAndWait();
        }
    }

    @FXML
    private void ExitBtnPress(){
        Stage oldStage = (Stage) ExitBtn.getScene().getWindow();        //get stage by btn
        oldStage.close();
    }
}
