package rep.services;

public interface ICalcStatis {
	/**
	 * 从未进店数 
	 * 从未进店=（Σ店外人流量-Σ进店人数）/ Σ店外人流量
	 * @Title: getG1
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param numbers
	 * @param @return
	 * @return double
	 * @throws
	 */
	public double getG1(int... numbers);

	/**
	 * 进店没有兴趣数.
	 * 进店没兴趣=（Σ进店人数-Σ接触人数）/ Σ店外人流量
	 * @Title: getG2
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param numbers
	 * @param @return
	 * @return double
	 * @throws
	 */
	public double getG2(int... numbers);

	/**
	 * 有兴趣不试衣数.
	 * 有兴趣不试衣=（Σ接触人数-Σ试衣人数）/ Σ店外人流量
	 * @Title: getG3
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param numbers
	 * @param @return
	 * @return double
	 * @throws
	 */
	public double getG3(int... numbers);

	/**
	 * 试衣不购买.
	 * 试衣不购买=（Σ试衣人数-Σ购买人数）/ Σ店外人流量
	 * @Title: getG4
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param numbers
	 * @param @return
	 * @return double
	 * @throws
	 */
	public double getG4(int... numbers);

	/**
	 * 试衣并购买.
	 * 试衣并购买= Σ购买人数 / Σ店外人流量
	 * @Title: getG5
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param numbers
	 * @param @return
	 * @return double
	 * @throws
	 */
	public double getG5(int... numbers);

	/**
	 * 重复购买率.
	 * 重复购买率= Σ老顾客购买人数 / Σ店外人流量
	 * @Title: getG6
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param numbers
	 * @param @return
	 * @return double
	 * @throws
	 */
	public double getG6(int... numbers);

	/**
	 * 绩效计算值.
	 * 
	 * @Title: getRpi
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param doubles
	 * @param @return
	 * @return double
	 * @throws
	 */
	public double getRpi(double... doubles);

	public String getXStr(double... doubles);

	public String getYStr(String lastTime, double... doubles);

	public String getZStr(String lastTime, double... doubles);
}
