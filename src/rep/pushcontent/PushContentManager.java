
package rep.pushcontent;
import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于推送信息表的业务操作操作接口.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface PushContentManager  extends BusinessObjectManager {
	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<PushContent> searchPushContent(Map<PushContentSearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchPushContentNum(Map<PushContentSearchFields, Object> criterias);

	/**
	 * 保存实体到数据库.
	 * @param pushcontent
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createPushContent(PushContent pushcontent) throws ValidateFieldsException;

	/**
	 * 更新操作.
	 * @param pushcontent
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void updatePushContent(PushContent pushcontent) throws ValidateFieldsException;

	/**
	 * 删除操作
	 * @param sno
	 */
	@Transactional
	public void removePushContents(String sno);

	/**
	 * 根据主键取值.
	 * @param sno
	 * @return
	 */
	public PushContent getPushContent(int sno);
}
