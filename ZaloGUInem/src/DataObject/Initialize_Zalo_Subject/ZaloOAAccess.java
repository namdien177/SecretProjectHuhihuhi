package DataObject.Initialize_Zalo_Subject;

import DataObject.User.UserClass;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vng.zalo.sdk.oa.ZaloOaClient;
import com.vng.zalo.sdk.oa.ZaloOaInfo;

public class ZaloOAAccess {

    public ZaloOaClient GetZaloUserClient(String OA_ID, String Token){
        ZaloOaInfo Information = new ZaloOaInfo(Long.parseLong(OA_ID),Token);
        return new ZaloOaClient(Information);   // zalo for developer
    }

    /*
    Get Userclass information từ JsonObject
     */
    public UserClass ConvertJsonToUserObject(JsonObject json){
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
        // Test :System.out.println(result);
        UserClass testingsub = gson.fromJson(result,UserClass.class);
        //System.out.println(testingsub.getDisplayName());
        /*
        Convert string dạng Json sang Java class và return.
         */
        return gson.fromJson(result,UserClass.class);
    }
}
