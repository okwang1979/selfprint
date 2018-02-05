package nc.ms.tb.formula.script;

/**
 * 解析excel中的A1;'sheetName'!A1这类表达式,委托给该接口.
 * @author wangzhqa
 *
 */
public interface IParseCellElementDelegate {

	/**
	 * 执行并返回结果
	 * @author wangzhqa
	 * @since 2012-12-18
	 * @param paramCalculator
	 * @param paramString
	 * @return
	 * Object
	 */
	public Object parseCellElementList(Calculator paramCalculator,
			String paramString);

}
