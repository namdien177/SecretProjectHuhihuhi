package DataObject.FunctionCustomized;

import DataObject.User.UserClass;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vng.zalo.sdk.APIException;
import com.vng.zalo.sdk.ZaloUser;
import com.vng.zalo.sdk.oa.ZaloOaClient;
import com.vng.zalo.sdk.oa.ZaloOaInfo;

import java.util.ArrayList;
import java.util.Scanner;

public class UserFunction {
    public void GetUserInformation(String Phonenumber){

        Gson gson = new Gson();
        //Offical Account
        /*
        *Hàm khai báo Offical Account
        * OAID: id của offical Account
        * secrect: mã bí mật của OA API
        * */

        String oaid = "3093468968868500357";
        // zalo for developer
        String secrect = "45d724RKDL25pKu1LJ1R";
        ZaloOaInfo info = new ZaloOaInfo(Long.parseLong(oaid), secrect); // zalo for developer
        System.out.println(info);
        ZaloOaClient oaClient = new ZaloOaClient(info);

//        String templateId = "2c5599bda5f84ca615e9";
        JsonObject data = new JsonObject();
        data.addProperty("content", "Chào bạn, Chúc bạn một ngày vui vẻ!");





            try {
                JsonObject profile = oaClient.getProfile(Long.parseLong(Phonenumber));
                ConvertJsonToObject(profile);
            } catch (APIException e) {
                // error
                System.out.println("API Error code : " + e.getCode());
                System.out.println("API Error message : " + e.getMessage());
            }



    }
    private String ConvertJsonToObject(JsonObject json){
        Gson gson = new Gson();
        System.out.println("--json--");
        JsonElement stringresult = gson.fromJson(json.getAsJsonObject("data"), JsonElement.class);
        String result = gson.toJson(stringresult);
        System.out.println(result);
        UserClass userinfo = gson.fromJson(result,UserClass.class);
        System.out.println(userinfo.getUserName());
        System.out.println(userinfo.getUserID());
        System.out.println(userinfo.getUserGender());
        return null;
    }
}
