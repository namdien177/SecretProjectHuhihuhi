package DataObject.FunctionCustomized;

import DataObject.Initialize_Zalo_Subject.ZaloOAAccess;
import DataObject.User.UserClass;
import com.google.gson.JsonObject;
import com.vng.zalo.sdk.APIException;
import com.vng.zalo.sdk.oa.ZaloOaClient;
import com.vng.zalo.sdk.oa.message.MsgGif;
import com.vng.zalo.sdk.oa.message.MsgImage;
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
        String PathImage = "-1";
        try{
            System.out.println(uploaded.getAbsolutePath());
            return uploaded.getAbsolutePath();
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
            return PathImage;
        }
    }

    public boolean SendImage_Gif(UserClass anUser, String desc, String absolutePart){
        System.out.println("step 1.1");
        try{
            if (absolutePart.contains(".gif")){
                System.out.println("step 1.1.1");
                JsonObject ret = oaClient.uploadGifPhoto(absolutePart);
                String IDupload = new ZaloOAAccess().ConvertJsonToImageObject(ret).getImageId();
                System.out.println(ret);
                System.out.println(IDupload + " = > ID picture");

                MsgGif gif = new MsgGif();
                gif.setImageid(IDupload);
                gif.setHeight(100);
                gif.setWidth(100);

                JsonObject jsonObject = oaClient.sendGifMessage(anUser.getUserId(), gif);
                System.out.println(jsonObject);
                return new ZaloOAAccess().CheckSendingCondition(jsonObject);                //return true if message was sent

            }else if (absolutePart.equals("-1")){
                System.out.println("step 1.1.2");
                return false;
            }else{
                JsonObject ret = oaClient.uploadPhoto(absolutePart);
                System.out.println(ret +"   =>RET png");
                String IDupload = new ZaloOAAccess().ConvertJsonToImageObject(ret).getImageId();

                MsgImage image = new MsgImage();
                image.setImageid(IDupload);
                image.setMessage(desc);
                JsonObject jsonObject = oaClient.sendImageMessage(anUser.getUserId(), image);
                return new ZaloOAAccess().CheckSendingCondition(jsonObject);                //return true if message was sent
            }
        }catch (APIException api){
            System.out.println("Error: " + api.getMessage());
            return false;
        }

    }
}
