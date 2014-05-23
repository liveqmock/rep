package rep.shortmessage;

import java.io.UnsupportedEncodingException;

public class SendMessageUtil {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 输入软件序列号和密码
		String sn = "SDK-CSL-010-00147";
		String pwd = "416035";
		String content = "测试内容";
		Client client = new Client(sn, pwd);
		// 我们的Demo最后是拼成xml了，所以要按照xml的语法来转义
		if (content.indexOf("&") >= 0) {
			content = content.replace("&", "&amp;");
		}

		if (content.indexOf("<") >= 0) {

			content = content.replace("<", "&lt;");

		}
		if (content.indexOf(">") >= 0) {
			content = content.replace(">", "&gt;");
		}
		// 短信发送
		String result_mt = client.mt("18616818351", content, "", "", "");
		if (result_mt.startsWith("-") || result_mt.equals(""))// 以负号判断是否发送成功
		{
			System.out.print("发送失败！返回值为：" + result_mt + "。请查看webservice返回值对照表");
			return;
		}
		// 输出返回标识，为小于19位的正数，String类型的，记录您发送的批次
		else {
			System.out.print("发送成功，返回值为：" + result_mt);
		}
	}
}
