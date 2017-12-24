package DataObject.FunctionCustomized;

import DataObject.Initialize_Zalo_Subject.ZaloOAAccess;
import DataObject.User.UserClass;
import com.google.gson.JsonObject;
import com.vng.zalo.sdk.APIException;
import com.vng.zalo.sdk.oa.ZaloOaClient;

import java.util.List;

public class MessageFunction {

    public boolean SendMessage(UserClass anUser, String message){
        try {
            String oaid = "3093468968868500357";
            String secrect = "45d724RKDL25pKu1LJ1R";
            ZaloOaClient oaClient = new ZaloOAAccess().GetZaloUserClient(oaid, secrect);

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
