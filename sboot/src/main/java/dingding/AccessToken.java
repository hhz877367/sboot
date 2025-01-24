package dingding;
import com.aliyun.tea.*;
import com.aliyun.dingtalkoauth2_1_0.models.*;
import com.aliyun.teaopenapi.models.*;
public class AccessToken {
    /*  AgentId 1793454581
        AppKey dingw7t67c9tfyrdeux6
    *  AppSecret OqALIPqkakumjqC7XzbbbrzMLWnYrOni7imY77aNwpI5-jrngr4YFnwezxar161X
    * */
    public static com.aliyun.dingtalkoauth2_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }
    public static String getAccessToken() throws Exception {

        com.aliyun.dingtalkoauth2_1_0.Client client = AccessToken.createClient();
        GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest()
                .setAppKey("dingw7t67c9tfyrdeux6")
                .setAppSecret("OqALIPqkakumjqC7XzbbbrzMLWnYrOni7imY77aNwpI5-jrngr4YFnwezxar161X");
        try {
            GetAccessTokenResponse response=client.getAccessToken(getAccessTokenRequest);
            //输出我们得到的accessToken的值
            System.out.println(response.getBody().getAccessToken());
            return response.getBody().getAccessToken();


        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getAccessToken());

    }
    /*获取通讯录权限范围*/
    public  static  void  getPowRange(){

    }

}
