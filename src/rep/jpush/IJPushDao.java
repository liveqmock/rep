package rep.jpush;

import java.util.List;

/**
 * 职员操作. TODO(描述类的职责)
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:130126,date:2014-4-10 上午11:11:58,content:TODO
 * </p>
 * 
 * @author 130126
 * @date 2014-4-10 上午11:11:58
 * @since
 * @version
 */
public interface IJPushDao {
	/**
	 * 添加推送用户的tag和别名.
	 * 
	 * @param userId
	 * @param sysCode
	 * @param deviceType
	 * @param token
	 */
	public void savePushUser(JpushVO vo);
	
	/**
	 * 更新用户tag和别名
	 * @param vo
	 */
	public void updatePushUser(JpushVO vo);

	/**
	 * 删除用户tag
	 * 
	 * @param userId
	 * @param sysCode
	 */
	public void deletePushUser(String userId, String sysCode, String deviceType);

	/**
	 * 查询.
	 * 
	 * @param userId
	 * @param sysCode
	 * @return
	 */
	public List<JpushVO> selectPushUser(JpushVO vo);
}
