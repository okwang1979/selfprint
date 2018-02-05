package nc.ms.tb.formula.script.core;

import java.util.ArrayList;

/**
 * ��¼Excel������,��¼�����¼����ArrayList.
 * �������� row*col  = dataCount
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
