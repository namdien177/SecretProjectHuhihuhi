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

    /*
    Calling OA client at start
     */
    public ImageUploadFunction(){
        String oaid = "3093468968868500357";
        String secrect = "45d724RKDL25pKu1LJ1R";
        this.oaClient = new ZaloOAAccess().GetZaloUserClient(oaid, secrect);
    }

    /*
    Getting upload file
     */
    private File getFileUpload(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image/Gif type","*.png", "*.jpeg", "*.jpg", "*.gif"));
        return fileChooser.showOpenDialog(null);
    }

    /*
    Getting absolute directory file
     */
    public String getAbsolutePathFile(){
        File uploaded = getFileUpload();
        String PathImage = "-1";            //Indicate error while getting file path
        try{
            System.out.println(uploaded.getAbsolutePath());
            return uploaded.getAbsolutePath();
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
            return PathImage;
        }
    }

    public boolean SendImage_Gif(UserClass anUser, String desc, String absolutePart){
        try{
            if (absolutePart.contains(".gif")){
                /*
                Get JsonObject as returned Json when uploading file
                 */
                JsonObject ret = oaClient.uploadGifPhoto(absolutePart);
                String IDupload = new ZaloOAAccess().ConvertJsonToImageObject(ret).getImageId();
                //Zalo upload gif api
                //create instance of gif type item
                MsgGif gif = new MsgGif();
                gif.setImageid(IDupload);
                gif.setHeight(100);
                gif.setWidth(100);
                //upload item as in gif type
                JsonObject jsonObject = oaClient.sendGifMessage(anUser.getUserId(), gif);
                return true;                //return true if message was sent

            }else if (absolutePart.equals("-1")){
                //Error while getting absolute path
                System.out.println("Error while getting absolute path - Absolute Path returned -1");
                return false;
            }else{
                /*
                Get JsonObject as returned Json when uploading file
                 */
                JsonObject ret = oaClient.uploadPhoto(absolutePart);
                String IDupload = new ZaloOAAccess().ConvertJsonToImageObject(ret).getImageId();
                //Zalo upload image api
                //create instance of image type item
                MsgImage image = new MsgImage();
                image.setImageid(IDupload);
                image.setMessage(desc);     //description of image
                //upload item as in gif type
                JsonObject jsonObject = oaClient.sendImageMessage(anUser.getUserId(), image);
                return true;                //return true if message was sent
            }
        }catch (APIException api){
            System.out.println("Error in API: " + api.getMessage());
            return false;
        }

    }
}
