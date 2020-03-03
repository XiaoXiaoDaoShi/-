package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import pay.AlipayConfig;

/**
 * Servlet implementation class PayServlet
 */
@WebServlet("/PayServlet")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String APP_ID = "2016101500694894";
		String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8JvGVSQUUC4TlBLecAjZpl2wmt7EJ00w99nWSAxmPQonNmGSD4ShlteEnh/BIdpW1Mfe1ECT/38OIpgPVU4gIieo54qbD2nrW83sqYeGPhj62m48Dxn4ZIX0bpGWuMD/0D3kuhdcGy6zwu8MzOvl7PwcRIoJxpMWoVz3oWHZ3yB+vEG2BdQmvx/jaNfiDRYlwebdSNeNYMHLP1+QftgTDXtFc9EZK3d9/9uVwZe2/Crf7fZqdUmYbAm6Z4orgjNDnHgA6oQMPMzo7lOVUOxxS04pokqRFjkKPN+E2Ac+UjeLOTRsZYHl/qXZHK1mJRbYt9H1zzmAsnYe0Ii6dmAOdAgMBAAECggEATOOlShqDBsQGVQjud8S3s8L7oX7jzF4bBB9ZwIefxFmusuQZ/4Ld3WLMVjlJSnObjmaTEd09IYux/n/JhT4Tk1m47FrhR5xbIshxhsplQR/dNQaxFRvioCZ9V/SA0QRKvlQsc4SicNZ/IAm7JY5uMZ16J9hwcjyhwb+VPwQXta7Q/8A6EYmAd9SAbthdyPnmx3GwI2tWPtdN/RtKICHUEQARk0DgPdj8a2BT1MnU7R68+NZLTcEdzQ/QGnRJqF3HQpxWwuwQ4Y3SV/symAkwi4YSxNcIzY5nG+c27Lr4ICdui9cFqb4Q0sfODd1061Ovb6cnoEJ8CzkaMDytVcJQAQKBgQDpHD1gJJtgX4jh4bVxTVjjFF8igSu26nm6WrHdNTI2THbP+2/a/K27XPq0je+Hz/bvs3itJLT50sRIv2GejIJmTxM+4+sHS/eHm5sXeFBoJjg1/CWphuvA86LOTH5DC9g4XHaz02KpRPt3avbdH82SGuV5Um038yzQNEuB7P4aAQKBgQDOoJPBqQ5UXMwYt3USJHkqGn0dr5vLGcE6JUOgwjdMDq1nwrl5e8pM98D0NvbZpFTu6+ZgdhtGypuBdg63GXOVlcVYo+YQvOryeoeD66vqv1OFt+catANeC+6EEW6Myno5Lnji4PCd9jIH5+Pv7jHvUSVj6etLxCnpcKuWlggRnQKBgADL9+m8Nku+B9m9y8D3YrZj+29CwcN+Saw5YyI+bE2DNWTAl0FIW8+oIbM8SPVN4KoxEtiEp5my/rjA7rR8BnYTDx3H6aW8vLvZ7E3IO34fo1GL6xUJQQUH0CakXEoeYD7REhbLQisR8UoXM4DN+wXk0aP4hCcMzbpoc1meVcABAoGAc9nSnm10DFbvVBhgemiaysGJTwCsACcTkM9d6J+cM3ezQA8MwRFoLtlmhUbgBs09lawMQh9WUc29fMu9mmmZxc/wky9qCg5ySLvsiUm03WyinsGqwO0XSj60X7YKXsQT3UWyH7rxDhAAozAgO+E4fC7qIOgBtjxpJ2q1ujuqkH0CgYEAtUyf318h5gjLysFKn/QchLZ1YLUW4y4Sp5ueRRdA+wvr4huJD9kMPGHpYjLrf/M7Jr2cowJqRuSemSMF331iTfybefrvBIWwkdx48NMew7IFBUAV+F/PUARbfcegh+AJ0ika+IPc2Kt+Rh/MhKlrghQdPkwQO3p/HC2goYi0W6U=";
		String FORMAT = "json";
		String CHARSET = "utf-8";
		String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj5zsSQbl4eNx6cWRxjxn9y3kn58FWJQyuH8OYfbELnhYrre+Y4Q6MP4GhrhUqt8Vlt9sFPDz1z5mPk7egbxkDQ8QuXPd07YGnYoICpcAbjDNCXLXH9V5SOUoKJerQfa3Bv3vWQfQKDWSB1Lwq6XDjWe1XU/godUbLbe+cTT+gj/M6WpOL34gOaXzsxT8Dk0+dFvxarkJZNvXk+/tYBAfSynH3sQuUEK5tyOsj3A2i3cjBjpgp5KTObLOQTCYO8urrSlZKBu+Givbk0FQ6K0B/bPXcDdb8q5OJ3Ysj46RuI7VQX83K5K7MkTlm7mijqVS9acmLjhJY+RWic6DTLbL8QIDAQAB";
		String SIGN_TYPE = "RSA2";
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); //获得初始化的AlipayClient
		
	    AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
	    alipayRequest.setReturnUrl(AlipayConfig.return_url);
	    alipayRequest.setNotifyUrl(AlipayConfig.notify_url);//在公共参数中设置回跳和通知地址
	    
	    
	    String trade_no = String.valueOf(new Date().getTime());
	    
	    
	    alipayRequest.setBizContent("{" +
	        "\"out_trade_no\":\"2015032001010000000000\"," +
	        "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	        "\"total_amount\":0.01," +
	        "\"subject\":\"订餐\"}");
//	        "    \"body\":\"Iphone6 16G\"," +
//	        "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
//	        "    \"extend_params\":{" +
//	        "    \"sys_service_provider_id\":\"2088511833207846\"" +
//	        "    }"+
//	        "  }");//填充业务参数
	    
	    String form="";
	    try {
	        form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
	    } catch (AlipayApiException e) {
	        e.printStackTrace();
	    }
	   response.setContentType("text/html;charset=" + CHARSET);
	    response.getWriter().write(form);//直接将完整的表单html输出到页面
	    response.getWriter().flush();
	   response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
