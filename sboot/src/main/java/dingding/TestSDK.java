package dingding;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;


public class TestSDK {
    public static void main(String[] args) throws ApiException {
        /* AgentId 1793454581
            AppKey dingw7t67c9tfyrdeux6
        *  AppSecret OqALIPqkakumjqC7XzbbbrzMLWnYrOni7imY77aNwpI5-jrngr4YFnwezxar161X
        * */

   /*     DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey("dingw7t67c9tfyrdeux6");
        request.setAppsecret("OqALIPqkakumjqC7XzbbbrzMLWnYrOni7imY77aNwpI5-jrngr4YFnwezxar161X");
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        JSONObject jsonObject = JSON.parseObject(response.getBody());
        System.out.println(jsonObject.toJSONString());
        String access_token = jsonObject.getString("access_token");
        System.out.println(access_token);*/
    }
}
