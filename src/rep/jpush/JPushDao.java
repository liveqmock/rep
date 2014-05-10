package rep.jpush;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

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
//		)List<JpushVO> result = new ArrayList<JpushVO>(); 
//		StringBuffer buf = new StringBuffer("select * from push_user where ");
//		if(vo.getPushId()>0){
//			buf.append(" id = ?");
//		}
//		if(vo.getSysCode()!=null&&!"".equals(vo.getSysCode())){
//			buf.append(" syscode = ?");
//		}
//		if(vo.getUserId()!=null&&!"".equals(vo.getUserId())){
//			buf.append(" userid = ?");
//		}
//		if(vo.getUserId()!=null&&!"".equals(vo.getUserId())){
//			buf.append(" userid = ?");
//		}
//		template.execute(new ConnectionCallback() {
//			@Override
//			public Object doInConnection(Connection con) throws SQLException,
//					DataAccessException {
//				PreparedStatement ps = con
//						.prepareStatement("select * from push_user where userid ");
//				ps.setInt(1, vo.getEmpId());
//				ps.setString(2, vo.getEmpCode());
//				ps.setString(3, vo.getEmpName());
//				ps.setString(4, vo.getBirthDate());
//				ps.setString(5, vo.getEmpStatus());
//				ps.setString(6, vo.getCardNo());
//				ps.setString(7, vo.getMobileNo());
//				ps.setString(8, vo.getEmail());
//				ps.setString(9, vo.getJobName());
//				ps.setString(10, vo.getJobLevel());
//				ps.setString(11, vo.getJobSequence());
//				ps.setString(12, vo.getJobDuty());
//				ps.executeUpdate();
//				return null;
//			}
//		},arg);
//		
//		result = this.getSqlSession(.selectList(NAMESPACE + "getPushs", vo);
		return null;
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
