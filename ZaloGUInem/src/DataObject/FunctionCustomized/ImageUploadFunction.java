package DataObject.FunctionCustomized;

import DataObject.Initialize_Zalo_Subject.ZaloOAAccess;
import com.google.gson.JsonObject;
import com.vng.zalo.sdk.oa.ZaloOaClient;
import javafx.stage.FileChooser;

import java.io.File;

public class ImageUploadFunction {

    private ZaloOaClient oaClient;

    public ImageUploadFunction(){
        String oaid = "3093468968868500357";
        String secrect = "45d724RKDL25pKu1LJ1R";
        this.oaClient = new ZaloOAAccess().GetZaloUserClient(oaid, secrect);
    }

    private File getFileUpload(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image/Gif type","*.png", "*.jpeg", "*.jpg", "*.gif"));
        return fileChooser.showOpenDialog(null);
    }

    public String getIDUploadPicture(){
        File uploaded = getFileUpload();
        String IDupload = "-1";
        try{
            String absolutePath = uploaded.getAbsolutePath();
            if (absolutePath.contains(".gif")){
                JsonObject ret = oaClient.uploadGifPhoto(absolutePath);
                IDupload = new ZaloOAAccess().ConvertJsonToImageObject(ret).getImageId();
                return IDupload;
            }else{
                JsonObject ret = oaClient.uploadPhoto(absolutePath);
                IDupload = new ZaloOAAccess().ConvertJsonToImageObject(ret).getImageId();
                return IDupload;
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
            return IDupload;
        }
    }
}
