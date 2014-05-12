package rep.jpush;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import common.util.CommonUtil;

public class JPushDao implements IJPushDao {
	private JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public void savePushUser(final JpushVO vo) {
		template.execute(new ConnectionCallback() {
			@Override
			public Object doInConnection(Connection con) throws SQLException,
					DataAccessException {
				PreparedStatement ps = con
						.prepareStatement("insert into push_user(userid,token,devicetype,syscode ) values(?,?,?,? )");
				ps.setString(1, vo.getUserId());
				ps.setString(2, vo.getToken());
				ps.setString(3, vo.getDeviceType());
				ps.setString(4, vo.getSysCode());
				ps.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void deletePushUser(final String userId, final String sysCode,
			final String deviceType) {
		template.execute(new ConnectionCallback() {
			@Override
			public Object doInConnection(Connection con) throws SQLException,
					DataAccessException {
				PreparedStatement ps = con
						.prepareStatement("delete from  push_user where   userid = ? and syscode=? and devicetype=?");
				ps.setString(1, userId);
				ps.setString(2, sysCode);
				ps.setString(3, deviceType);
				ps.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public List<JpushVO> selectPushUser(final JpushVO vo) {
		List<JpushVO> result = new ArrayList<JpushVO>();
		final List args = new ArrayList();
		StringBuffer buf = new StringBuffer(
				"select * from push_user where 1=1 ");
		if (!CommonUtil.isEmpty(vo.getSysCode())) {
			buf.append(" and syscode = ?");
			args.add(vo.getSysCode());
		}
		if (!CommonUtil.isEmpty(vo.getUserId())) {
			buf.append(" and userid = ?");
			args.add(vo.getUserId());
		}
		if (!CommonUtil.isEmpty(vo.getDeviceType())) {
			buf.append(" and devicetype = ?");
			args.add(vo.getDeviceType());
		}
		final String sql = buf.toString();
		return template.execute(new ConnectionCallback() {
			@Override
			public List<JpushVO> doInConnection(Connection con)
					throws SQLException, DataAccessException {
				List<JpushVO> temp = new ArrayList<JpushVO>();
				PreparedStatement ps = con.prepareStatement(sql);
				for (int i = 0; i < args.size(); i++) {
					ps.setString(i + 1, args.get(i) + "");
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					JpushVO v = new JpushVO();
					v.setDeviceType(rs.getString("devicetype"));
					v.setToken(rs.getString("token"));
					v.setUserId(rs.getString("userid"));
					temp.add(v);
				}
				return temp;
			}
		});
	}

	@Override
	public void updatePushUser(final JpushVO vo) {
		template.execute(new ConnectionCallback() {
			@Override
			public Object doInConnection(Connection con) throws SQLException,
					DataAccessException {
				PreparedStatement ps = con
						.prepareStatement("update  push_user set token=  ? where  userid = ? and syscode=? and devicetype=? ");
				ps.setString(2, vo.getUserId());
				ps.setString(1, vo.getToken());
				ps.setString(4, vo.getDeviceType());
				ps.setString(3, vo.getSysCode());
				ps.executeUpdate();
				return null;
			}
		});
	}

}
