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
    public UserClass GetUserInformation(String Phonenumber){
        //Offical Account
        /*
        *Hàm khai báo Offical Account
        * OAID: id của offical Account
        * secrect: mã bí mật của OA API
        * */
        //  Test number: 01664708402
        String oaid = "3093468968868500357";
        //  zalo for developer
        String secrect = "45d724RKDL25pKu1LJ1R";
        ZaloOaInfo info = new ZaloOaInfo(Long.parseLong(oaid), secrect); // zalo for developer
        System.out.println(info);
        ZaloOaClient oaClient = new ZaloOaClient(info);

        //String templateId = "2c5599bda5f84ca615e9";
        JsonObject data = new JsonObject();
        data.addProperty("content", "Chào bạn, Chúc bạn một ngày vui vẻ!");
        try {
            JsonObject profile = oaClient.getProfile(Long.parseLong(Phonenumber));
            new UserClass();
            UserClass userClass;
            userClass = ConvertJsonToUserObject(profile);
            userClass.setUserPhone(Phonenumber);
            return userClass;
        } catch (APIException e) {
            // error
            System.out.println("API Error code : " + e.getCode());
            System.out.println("API Error message : " + e.getMessage());
            return null;
        }
    }

    /*
    Get Userclass information từ JsonObject
     */
    private UserClass ConvertJsonToUserObject(JsonObject json){
        Gson gson = new Gson();                             //khởi tạo đối tượng Gson để convert JsonObject sang Java Obj
        // Test : System.out.println("--json--");
        /*
        json.getAsJsonObject("data") = Lấy dữ liệu định nghĩa data trong dictionary
         */
        JsonElement stringresult = gson.fromJson(json.getAsJsonObject("data"), JsonElement.class);
        /*
        Chuyển đối tượng JsonEle về dạng String
         */
        String result = gson.toJson(stringresult);
        // Test :
        System.out.println(result);
        UserClass testingsub = gson.fromJson(result,UserClass.class);
        System.out.println(testingsub.getDisplayName());
        /*
        Convert string dạng Json sang Java class và return.
         */
        return gson.fromJson(result,UserClass.class);
    }
}
