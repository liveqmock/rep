package com.renjie120.codectest;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import common.util.Coder;

public class CodecTest extends
		AbstractTransactionalDataSourceSpringContextTests {
	public void testBase64() {
		String s = "18616818351";
		String base64;
		try {
			base64 = Coder.encryptBASE64(s.getBytes());
			String md5 = Coder.encryptMD5Str(base64);
			System.out.println("base64:" + base64);
			System.out.println("md5:" + md5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void testMyPassword() {
		String s = "p^j5q#*3";
		System.out.println("解密之后密码是："+Coder.fromMyCoder(s));
	}
}
