package nc.ms.tb.formula.excel.core;

import java.util.Collection;


/**
 * excelBookģ�ͽӿ�
 * 
 * @author wangzhqa
 * 
 */
public interface IWorkBook {

	/**
	 * ���sheet
	 * @author wangzhqa
	 * @since 2012-12-6
	 * @param sheet
	 * void
	 */
	public void addSheet(IWorkSheet sheet);

	/**
	 * �õ�ָ�����Ƶ�Sheet
	 * @author wangzhqa
	 * @since 2012-12-6
	 * @param sheetName
	 * @return
	 * IWorkSheet
	 */
	public IWorkSheet getWorkSheet(String sheetName);

	/**
	 * �õ�����Sheet
	 * @author wangzhqa
	 * @since 2012-12-6
	 * @return
	 * Collection<IWorkSheet>
	 */
	public Collection<IWorkSheet> getAllSheet();
	
	
	
	
 
	

}
