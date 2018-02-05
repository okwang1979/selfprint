package nc.ms.tb.formula.excel.core;

import nc.vo.tb.rule.excel.CellElement;
import nc.vo.tb.rule.excel.ColumnRow;

/**
 * ��ʽʹ�õ�shet�ӿ�
 * @author wangzhqa
 *
 */
public interface IWorkSheet {

//	private String name;

//	private Map<ColumnRow, CellElement> cellMap = new HashMap<ColumnRow, CellElement>();
	
	
	/**
	 * ��ƫ��
	 * @author wangzhqa
	 * @since 2012-12-6
	 * @return
	 * int
	 */
	public int getRowOffSet();
	
	public void setRowOffSet(int offset);
	
	/**
	 * ��ƫ��
	 * @author wangzhqa
	 * @since 2012-12-6
	 * @return
	 * int
	 */
	public int getColOffSet();
	
	public void setColOffSet(int offset);

	public String getName();

	public void setName(String name) ;
	
	public String getPkSheet();
	
	
	
	public CellElement getCellElement(int row,int column);
	public CellElement getCellElement(String columnRowStr);
	
	public CellElement getCellElement(ColumnRow columnRow);
	

}
