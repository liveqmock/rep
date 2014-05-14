package com.renjie120.tooltest;

import java.util.Date;

import junit.framework.TestCase;
import brightmoon.util.DateUtil;

public class DateUtilTest extends TestCase {

	/**
	 * 测试当前周里面的第几天.星期天是第一天。
	 * 
	 * @Title: testGetDayOfWeek
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return void
	 * @throws
	 */
	public void testGetDayOfWeek() {
		int dayInThisWeek = DateUtil.getDayOfWeek(new Date());
		System.out.println(dayInThisWeek);
	}
}
