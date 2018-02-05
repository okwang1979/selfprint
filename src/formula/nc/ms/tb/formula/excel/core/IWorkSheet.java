package nc.ms.tb.formula.excel.core;

import nc.vo.tb.rule.excel.CellElement;
import nc.vo.tb.rule.excel.ColumnRow;

/**
 * 公式使用的shet接口
 * @author wangzhqa
 *
 */
public interface IWorkSheet {

//	private String name;

//	private Map<ColumnRow, CellElement> cellMap = new HashMap<ColumnRow, CellElement>();
	
	
	/**
	 * 行偏移
	 * @author wangzhqa
	 * @since 2012-12-6
	 * @return
	 * int
	 */
	public int getRowOffSet();
	
	public void setRowOffSet(int offset);
	
	/**
	 * 列偏移
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
