package nc.ms.tb.formula.excel.core;

import java.math.BigDecimal;

import nc.vo.tb.rule.excel.CellElement;

/**
 * excel�е�һЩͨ�÷����ŵ�����.
 * 
 * @author wangzhqa
 * 
 */
public class TbbExcelHelper {

	public static Double getCellValue(Double value, CellElement cell) {
		try {

//			UFDouble tempValue = new UFDouble(newvalue);
			// if(tempValue.doubleValue()<1000&&tempValue.doubleValue()>0){
			// NtbLogger.print(tempValue);
			// }
			if(value.isInfinite()){
				return 0D;
			}
			// lrx 2016-11-2 ά������Ϊ�����ĵ�Ԫ��������ʱ������Ԫ����
			int valueScale = cell.getValueScale();
		
			BigDecimal bVs = new BigDecimal(valueScale/*cell.getValueScale()*/);
			BigDecimal num1 = new BigDecimal(value);
			BigDecimal num2 = num1.divide(bVs);

			if (valueScale/*cell.getValueScale()*/ == 1) {
				// С��λ������2λС��.
//				int digits = cell.getDigits() > 2 ? cell.getDigits() : 2;
				int digits = cell.getDigits();
				if (cell.isPercentCell()) {
					digits = cell.getDigits() + 2;
				}
//				digits = digits>6?6:digits;
				num2 = num2.setScale(digits, BigDecimal.ROUND_HALF_UP);
			} else {
				num2 = num2
						.setScale(cell.getDigits(), BigDecimal.ROUND_HALF_UP);
			}

			num2 = num2.multiply(bVs);
			return num2.doubleValue();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * @author wangzhqa
	 * @since 2013-5-15
	 * @param value
	 * @param scale
	 *            :��Ӧ����.100,1000
	 * @param digits
	 *            :��Ӧ��������С��λ��.
	 * @return Double
	 */
	public static Double getRoundValue(Double value, int scale, int digits) {

		try {
 
			BigDecimal bVs = new BigDecimal(scale);
			BigDecimal num1 = new BigDecimal(value.toString());
			BigDecimal num2 = num1.divide(bVs);
			num2 = num2.setScale(digits, BigDecimal.ROUND_HALF_UP);
			num2 = num2.multiply(bVs);
			return num2.doubleValue();
		} catch (Exception e) {
			return null;
		}

	}

}
