package nc.ms.tb.formula.script;

/**
 * ����excel�е�A1;'sheetName'!A1������ʽ,ί�и��ýӿ�.
 * @author wangzhqa
 *
 */
public interface IParseCellElementDelegate {

	/**
	 * ִ�в����ؽ��
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
