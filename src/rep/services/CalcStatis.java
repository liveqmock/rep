package rep.services;

import common.util.CommonUtil;

/**
 * 计算各个带统计的数值.
 * 
 * @ClassName: CalcStatis
 * @Description: TODO
 * @date 2014-5-14 下午08:55:56
 * 
 */
public class CalcStatis implements ICalcStatis {
	private CalcStatis() {
	}

	private static class SingletonHolder {
		public final static CalcStatis instance = new CalcStatis();
	}

	public static CalcStatis getInstance() {
		return SingletonHolder.instance;
	}

	@Override
	public double getG1(int... numbers) {
		// 店外人数
		int outNum = numbers[0];
		// 进店人数
		int comeNum = numbers[1];
		if (outNum == 0)
			return 0;
		return CommonUtil.divide(CommonUtil.subtract(outNum, comeNum), outNum,
				4);
	}

	@Override
	public double getG2(int... numbers) {
		// 店外人流
		int outNum = numbers[0];
		// 进店人数
		int comeNum = numbers[1];
		// 接触人数
		int intreNum = numbers[2];
		if (outNum == 0)
			return 0;
		return CommonUtil.divide(CommonUtil.subtract(comeNum, intreNum),
				outNum, 4);
	}

	@Override
	public double getG3(int... numbers) {
		// 店外人数
		int outNum = numbers[0];
		// 接触人数
		int intreNum = numbers[1];
		// 试衣人数
		int tryNum = numbers[2];
		if (outNum == 0)
			return 0;
		return CommonUtil.divide(CommonUtil.subtract(intreNum, tryNum), outNum,
				4);
	}

	@Override
	public double getG4(int... numbers) {
		// 店外人数
		int outNum = numbers[0];
		// 试衣人数
		int tryNum = numbers[1];
		// 购买人数
		int buyNum = numbers[2];
		if (outNum == 0)
			return 0;
		return CommonUtil
				.divide(CommonUtil.subtract(tryNum, buyNum), outNum, 4);
	}

	@Override
	public double getG5(int... numbers) {
		// 店外人数
		int outNum = numbers[0];
		// 购买人数
		int buyNum = numbers[1];
		if (outNum == 0)
			return 0;
		return CommonUtil.divide(buyNum, outNum, 4);
	}

	@Override
	public double getG6(int... numbers) {
		// 店外人数
		int outNum = numbers[0];
		// 重复购买人数
		int oldNum = numbers[1];
		if (outNum == 0)
			return 0;
		return CommonUtil.divide(oldNum, outNum, 4);
	}

	@Override
	public double getRpi(double... doubles) {
		// double g1 = doubles[0];
		double g2 = doubles[1];
		double g3 = doubles[2];
		double g4 = doubles[3];
		double g5 = doubles[4];
		double g6 = doubles[5];
		// g1 = CommonUtil.multiply(g1, 0);
		g2 = CommonUtil.multiply(g2, 0.1);
		g3 = CommonUtil.multiply(g3, 0.3);
		g4 = CommonUtil.multiply(g4, 0.5);
		g5 = CommonUtil.multiply(g5, 0.8);
		// g6 = CommonUtil.multiply(g1, 1);
		// TODO Auto-generated method stub
		double result = CommonUtil.add(g2, g3);
		result = CommonUtil.add(result, g4);
		result = CommonUtil.add(result, g5);
		result = CommonUtil.add(result, g6);
		return result;
	}

	@Override
	public String getXStr(double... doubles) {
		int result = 0;
		double max = 0.0;
		int flag = 0;
		for (double d : doubles) {
			if (d > max) {
				result = flag;
				max = d;
			}
			flag++;
		} 
		return "";
	}

	@Override
	public String getYStr(String lastTime, double... doubles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getZStr(String lastTime, double... doubles) {
		// TODO Auto-generated method stub
		return null;
	}

}
