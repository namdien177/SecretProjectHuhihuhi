package DataObject.FunctionCustomized;

import DataObject.Initialize_Zalo_Subject.ZaloOAAccess;
import DataObject.User.UserClass;
import com.google.gson.JsonObject;
import com.vng.zalo.sdk.APIException;
import com.vng.zalo.sdk.oa.ZaloOaClient;

import java.io.File;

public class MessageFunction {

    private ZaloOaClient oaClient;

    /*
    Initialize to get ZaloOaClient
     */
    public MessageFunction(){
        String oaid = "3093468968868500357";
        String secrect = "45d724RKDL25pKu1LJ1R";
        this.oaClient = new ZaloOAAccess().GetZaloUserClient(oaid, secrect);
    }

    /*
    Sending message to user on Zalo.
    * anUser: UserClass
    * message: String
    * return boolean if succeed
     */
    public boolean SendMessage(UserClass anUser, String message){
        try {
            JsonObject profile = oaClient.sendTextMessage(anUser.getUserId(),message);
            System.out.println(profile);
            return true;
        } catch (APIException e) {
            // error
            System.out.println("API Error code : " + e.getCode());
            System.out.println("API Error message : " + e.getMessage());
            return false;
        }
    }
}
