package rep.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rep.jpush.Result;

import com.alibaba.fastjson.JSON;
import common.MyJdbcTool;
import common.base.SpringContextUtil;
import common.util.Coder;

import dwz.present.BaseAction;

/**
 * 直接执行sql的一个Action.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class MyUserServicesAction extends BaseAction {
	/* 修改密码,登陆,忘记密码 */
	private String userId;
	private String password;
	private String oldPassword;

	/* 注册信息. */
	private double masterPrice;
	private int workNum;
	private int weekendNum;
	private String brandName, brandType, phone, param1, lng_north, lat_east,
			worktime = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public double getMasterPrice() {
		return masterPrice;
	}

	public void setMasterPrice(double masterPrice) {
		this.masterPrice = masterPrice;
	}

	public int getWorkNum() {
		return workNum;
	}

	public void setWorkNum(int workNum) {
		this.workNum = workNum;
	}

	public int getWeekendNum() {
		return weekendNum;
	}

	public void setWeekendNum(int weekendNum) {
		this.weekendNum = weekendNum;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getLng_north() {
		return lng_north;
	}

	public void setLng_north(String lng_north) {
		this.lng_north = lng_north;
	}

	public String getLat_east() {
		return lat_east;
	}

	public void setLat_east(String lat_east) {
		this.lat_east = lat_east;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	/**
	 * 注册。
	 * 
	 * @Title: regiest
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String regiest() {
		return null;
	}

	/**
	 * 登陆.
	 * 
	 * @Title: login
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String login() {
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		String[] args = new String[] { userId };
		List pass = jdbcTool.queryForList(
				"select pass from user_t where loginId=? ", args);
		Result<String> r = new Result<String>();
		try {
			if (pass == null || pass.size() == 0) {
				r.setErrorCode(-1);
				r.setErrorMessage("用户名不存在!");
				r.setCount(0);
				writeToPage(response, JSON.toJSONString(r));
				return null;
			} else {
				Map m = (HashMap) pass.get(0);
				String p = m.get("pass") + "";
				password = Coder.toMyCoder(password);
				if (!p.equals(password)) {
					r.setErrorCode(-2);
					r.setErrorMessage("密码不正确!");
					r.setCount(0);
					writeToPage(response, JSON.toJSONString(r));
					return null;
				} else {
					r.setErrorCode(1);
					r.setErrorMessage("登陆成功!");
					r.setCount(0);
					writeToPage(response, JSON.toJSONString(r));
					return null;
				}
			}
		} catch (Exception e) {
			log.error(e);
			r.setErrorCode(-3);
			r.setErrorMessage("出现系统异常!");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
	}

	/**
	 * 更新密码.
	 * 
	 * @Title: updatePass
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String updatePass() {
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		String[] args = new String[] { userId };
		List pass = jdbcTool.queryForList(
				"select pass from user_t where loginId=? ", args);
		Result<String> r = new Result<String>();
		try {
			if (pass == null || pass.size() == 0) {
				r.setErrorCode(-1);
				r.setErrorMessage("用户名不存在!");
				r.setCount(0);
				writeToPage(response, JSON.toJSONString(r));
				return null;
			} else {
				Map m = (HashMap) pass.get(0);
				String p = m.get("pass") + "";
				oldPassword = Coder.toMyCoder(oldPassword);
				if (!p.equals(oldPassword)) {
					r.setErrorCode(-2);
					r.setErrorMessage("用户名原密码不正确!");
					r.setCount(0);
					writeToPage(response, JSON.toJSONString(r));
					return null;
				} else {
					password = Coder.toMyCoder(password);
					args = new String[] { password, userId };
					jdbcTool.updateSql(
							"update user_t set pass=? where loginId = ?", args);
					r.setErrorCode(1);
					r.setErrorMessage("修改成功!");
					r.setCount(0);
					writeToPage(response, JSON.toJSONString(r));
					return null;
				}
			}
		} catch (Exception e) {
			log.error(e);
			r.setErrorCode(-3);
			r.setErrorMessage("出现系统异常!");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}

	}

	/**
	 * 忘记密码.
	 * 
	 * @Title: forgetPass
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String forgetPass() {
		return null;
	}
}
