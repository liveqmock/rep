package rep.shortmessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class SendMessageUtil {
	public void postUrlWithParams(String url, Map params, String encoding)
			throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {

			HttpPost httpost = new HttpPost(url);
			// 添加参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null && params.keySet().size() > 0) {
				Iterator iterator = params.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry entry = (Entry) iterator.next();
					nvps.add(new BasicNameValuePair((String) entry.getKey(),
							(String) entry.getValue()));
				}
			}

			httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();

			System.out.println("Login form get: " + response.getStatusLine()
					+ entity.getContent());
			dump(entity, encoding);
			System.out.println("Post logon cookies:");
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			if (cookies.isEmpty()) {
				System.out.println("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("- " + cookies.get(i).toString());
				}
			}

		} finally {
			// 关闭请求
			httpclient.getConnectionManager().shutdown();
		}
	}

	private static void dump(HttpEntity entity, String encoding)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				entity.getContent(), encoding));
		System.out.println(br.readLine());
	}
	
	private static String parseStr(String str){
//		return str;
		try {
			return URLEncoder.encode(str, "gb2312");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return str;
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		SendMessageUtil util = new SendMessageUtil();
		Map m = new HashMap();
		m.put("zh",parseStr("5657") );
		m.put("mm", parseStr("5657"));
		m.put("nr",parseStr("你好"));
		m.put("hm", parseStr("15002121077"));
		m.put("dxlbid",parseStr("57") );
		try {
			util.postUrlWithParams("http://www.6610086.cn/smsComputer/smsComputersend.asp", m, "GBK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		// 输入软件序列号和密码
//		String sn = "SDK-CSL-010-00147";
//		String pwd = "416035";
//		String content = "测试内容";
//		Client client = new Client(sn, pwd);
//		// 我们的Demo最后是拼成xml了，所以要按照xml的语法来转义
//		if (content.indexOf("&") >= 0) {
//			content = content.replace("&", "&amp;");
//		}
//
//		if (content.indexOf("<") >= 0) {
//
//			content = content.replace("<", "&lt;");
//
//		}
//		if (content.indexOf(">") >= 0) {
//			content = content.replace(">", "&gt;");
//		}
//		// 短信发送
//		String result_mt = client.mt("18616818351", content, "", "", "");
//		if (result_mt.startsWith("-") || result_mt.equals(""))// 以负号判断是否发送成功
//		{
//			System.out.print("发送失败！返回值为：" + result_mt + "。请查看webservice返回值对照表");
//			return;
//		}
//		// 输出返回标识，为小于19位的正数，String类型的，记录您发送的批次
//		else {
//			System.out.print("发送成功，返回值为：" + result_mt);
//		}
	}
}
