package nc.ms.tb.formula.script.core;

import java.util.ArrayList;

/**
 * 记录Excel行列数,记录数与记录数的ArrayList.
 * 理论上行 row*col  = dataCount
 * @author wangzhqa
 *
 * @param <E>
 */
public class ExcelAreaList<E>  extends ArrayList<E> {
	
	private int rowCount =0;
	private int colCount = 0;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcelAreaList(){
		
	}
	


	
	
	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public void setColCount(int colCount) {
		this.colCount = colCount;
	}


	
	

}
