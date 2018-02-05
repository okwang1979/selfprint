package nc.ms.tb.formula.excel.core;

import nc.vo.tb.rule.excel.CellElement;
import nc.vo.tb.rule.excel.ColumnRow;

/**
 * 
 * 
 * ���еĶ�excel�Ĳ����඼��װ������ӿ���.
 * @author wangzhqa
 *
 */
public interface ITbbExcel {
	
	
	/**
	 * �õ���ǰ�����cellElement,����Ƕ�׵Ĵ���,�˷����߼�������.
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
	 * ���õ�ǰ���sheet.
	 * @author wangzhqa
	 * @since 2012-12-1
	 * @param sheetName
	 * @return
	 * WorkSheet
	 */
	public void setCurrentSheet(String  sheetName);
	
	/**
	 * �õ���ǰ���sheet.
	 * @author wangzhqa
	 * @since 2012-12-1
	 * @return
	 * WorkSheet
	 */
	public IWorkSheet getCurrentSheet(); 
	
	
 
	/**
	 * �õ�ָ���ĵ�Ԫ��
	 * @author wangzhqa
	 * @since 2012-12-1
	 * @param sheetName
	 * @param row
	 * @param column
	 * @return
	 * Object:�������Ϳ�������ֵ,�ַ���,boolean,��formula.
	 */
	public CellElement getElement(String sheetName, int row,int column);
	
	/**
	 * �õ�ָ���ĵ�Ԫ��
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
