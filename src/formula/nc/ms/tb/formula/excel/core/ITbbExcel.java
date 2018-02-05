package nc.ms.tb.formula.excel.core;

import nc.vo.tb.rule.excel.CellElement;
import nc.vo.tb.rule.excel.ColumnRow;

/**
 * 
 * 
 * 所有的对excel的操作类都封装到这个接口中.
 * @author wangzhqa
 *
 */
public interface ITbbExcel {
	
	
	/**
	 * 得到当前计算的cellElement,由于嵌套的存在,此方法逻辑不严密.
	 * @author wangzhqa
	 * @since 2013-6-3
	 * @return
	 * CellElement
	 * 
	 */
	@Deprecated
	public CellElement  getCurrentCell();
	
	
	/**
	 * 
	 * @author wangzhqa
	 * @since 2012-12-1
	 * @return
	 * WorkBook
	 */
	public IWorkBook  getWorkBook();
	
	public IWorkSheet getWorkSheet(String sheetName);
	
	/**
	 * 设置当前活动的sheet.
	 * @author wangzhqa
	 * @since 2012-12-1
	 * @param sheetName
	 * @return
	 * WorkSheet
	 */
	public void setCurrentSheet(String  sheetName);
	
	/**
	 * 得到当前活动的sheet.
	 * @author wangzhqa
	 * @since 2012-12-1
	 * @return
	 * WorkSheet
	 */
	public IWorkSheet getCurrentSheet(); 
	
	
 
	/**
	 * 得到指定的单元格
	 * @author wangzhqa
	 * @since 2012-12-1
	 * @param sheetName
	 * @param row
	 * @param column
	 * @return
	 * Object:返回类型可以是数值,字符串,boolean,和formula.
	 */
	public CellElement getElement(String sheetName, int row,int column);
	
	/**
	 * 得到指定的单元格
	 * @author wangzhqa
	 * @since 2012-12-1
	 * @param sheetName
	 * @param columnRow
	 * @return
	 * Object:
	 */
	public CellElement getElement(String sheetName,ColumnRow columnRow);


	public void setCurrentCellElement(CellElement localCellElement);
	
	

	
	

}
