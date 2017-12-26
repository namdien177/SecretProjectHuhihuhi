package DataObject.FunctionCustomized;

import DataObject.Initialize_Zalo_Subject.ZaloOAAccess;
import DataObject.User.UserClass;
import com.google.gson.JsonObject;
import com.vng.zalo.sdk.APIException;
import com.vng.zalo.sdk.oa.ZaloOaClient;

public class UserFunction {
    public UserClass GetUserInformation(String Phonenumber){
        //Offical Account
        /*
        *Hàm khai báo Offical Account
        * OAID: id của offical Account
        * secrect: mã bí mật của OA API
        * */
        String oaid = "3093468968868500357";
        String secrect = "45d724RKDL25pKu1LJ1R";
        ZaloOaClient oaClient = new ZaloOAAccess().GetZaloUserClient(oaid, secrect);

        //String templateId = "2c5599bda5f84ca615e9";
        JsonObject data = new JsonObject();
        data.addProperty("content", "Chào bạn, Chúc bạn một ngày vui vẻ!");
        try {
            JsonObject profile = oaClient.getProfile(Long.parseLong(Phonenumber));
            UserClass userClass= new ZaloOAAccess().ConvertJsonToUserObject(profile);
            if (userClass==null)
                return null;
            userClass.setUserPhone(Phonenumber);
            return userClass;
        } catch (APIException e) {
            // error
            System.out.println("API Error code : " + e.getCode());
            System.out.println("API Error message : " + e.getMessage());
            return null;
        }
    }
}
