package nc.ms.tb.formula.excel.core;

import java.util.Collection;


/**
 * excelBook模型接口
 * 
 * @author wangzhqa
 * 
 */
public interface IWorkBook {

	/**
	 * 添加sheet
	 * @author wangzhqa
	 * @since 2012-12-6
	 * @param sheet
	 * void
	 */
	public void addSheet(IWorkSheet sheet);

	/**
	 * 得到指定名称的Sheet
	 * @author wangzhqa
	 * @since 2012-12-6
	 * @param sheetName
	 * @return
	 * IWorkSheet
	 */
	public IWorkSheet getWorkSheet(String sheetName);

	/**
	 * 得到所有Sheet
	 * @author wangzhqa
	 * @since 2012-12-6
	 * @return
	 * Collection<IWorkSheet>
	 */
	public Collection<IWorkSheet> getAllSheet();
	
	
	
	
 
	

}
