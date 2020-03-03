package pay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101500694894";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8JvGVSQUUC4TlBLecAjZpl2wmt7EJ00w99nWSAxmPQonNmGSD4ShlteEnh/BIdpW1Mfe1ECT/38OIpgPVU4gIieo54qbD2nrW83sqYeGPhj62m48Dxn4ZIX0bpGWuMD/0D3kuhdcGy6zwu8MzOvl7PwcRIoJxpMWoVz3oWHZ3yB+vEG2BdQmvx/jaNfiDRYlwebdSNeNYMHLP1+QftgTDXtFc9EZK3d9/9uVwZe2/Crf7fZqdUmYbAm6Z4orgjNDnHgA6oQMPMzo7lOVUOxxS04pokqRFjkKPN+E2Ac+UjeLOTRsZYHl/qXZHK1mJRbYt9H1zzmAsnYe0Ii6dmAOdAgMBAAECggEATOOlShqDBsQGVQjud8S3s8L7oX7jzF4bBB9ZwIefxFmusuQZ/4Ld3WLMVjlJSnObjmaTEd09IYux/n/JhT4Tk1m47FrhR5xbIshxhsplQR/dNQaxFRvioCZ9V/SA0QRKvlQsc4SicNZ/IAm7JY5uMZ16J9hwcjyhwb+VPwQXta7Q/8A6EYmAd9SAbthdyPnmx3GwI2tWPtdN/RtKICHUEQARk0DgPdj8a2BT1MnU7R68+NZLTcEdzQ/QGnRJqF3HQpxWwuwQ4Y3SV/symAkwi4YSxNcIzY5nG+c27Lr4ICdui9cFqb4Q0sfODd1061Ovb6cnoEJ8CzkaMDytVcJQAQKBgQDpHD1gJJtgX4jh4bVxTVjjFF8igSu26nm6WrHdNTI2THbP+2/a/K27XPq0je+Hz/bvs3itJLT50sRIv2GejIJmTxM+4+sHS/eHm5sXeFBoJjg1/CWphuvA86LOTH5DC9g4XHaz02KpRPt3avbdH82SGuV5Um038yzQNEuB7P4aAQKBgQDOoJPBqQ5UXMwYt3USJHkqGn0dr5vLGcE6JUOgwjdMDq1nwrl5e8pM98D0NvbZpFTu6+ZgdhtGypuBdg63GXOVlcVYo+YQvOryeoeD66vqv1OFt+catANeC+6EEW6Myno5Lnji4PCd9jIH5+Pv7jHvUSVj6etLxCnpcKuWlggRnQKBgADL9+m8Nku+B9m9y8D3YrZj+29CwcN+Saw5YyI+bE2DNWTAl0FIW8+oIbM8SPVN4KoxEtiEp5my/rjA7rR8BnYTDx3H6aW8vLvZ7E3IO34fo1GL6xUJQQUH0CakXEoeYD7REhbLQisR8UoXM4DN+wXk0aP4hCcMzbpoc1meVcABAoGAc9nSnm10DFbvVBhgemiaysGJTwCsACcTkM9d6J+cM3ezQA8MwRFoLtlmhUbgBs09lawMQh9WUc29fMu9mmmZxc/wky9qCg5ySLvsiUm03WyinsGqwO0XSj60X7YKXsQT3UWyH7rxDhAAozAgO+E4fC7qIOgBtjxpJ2q1ujuqkH0CgYEAtUyf318h5gjLysFKn/QchLZ1YLUW4y4Sp5ueRRdA+wvr4huJD9kMPGHpYjLrf/M7Jr2cowJqRuSemSMF331iTfybefrvBIWwkdx48NMew7IFBUAV+F/PUARbfcegh+AJ0ika+IPc2Kt+Rh/MhKlrghQdPkwQO3p/HC2goYi0W6U=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj5zsSQbl4eNx6cWRxjxn9y3kn58FWJQyuH8OYfbELnhYrre+Y4Q6MP4GhrhUqt8Vlt9sFPDz1z5mPk7egbxkDQ8QuXPd07YGnYoICpcAbjDNCXLXH9V5SOUoKJerQfa3Bv3vWQfQKDWSB1Lwq6XDjWe1XU/godUbLbe+cTT+gj/M6WpOL34gOaXzsxT8Dk0+dFvxarkJZNvXk+/tYBAfSynH3sQuUEK5tyOsj3A2i3cjBjpgp5KTObLOQTCYO8urrSlZKBu+Givbk0FQ6K0B/bPXcDdb8q5OJ3Ysj46RuI7VQX83K5K7MkTlm7mijqVS9acmLjhJY+RWic6DTLbL8QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8081/OrderManagement/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8081/OrderManagement/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

