package nc.vo.tb.rule.excel;

/**
 * 封装excel模型的每一个单元格的类
 * 
 * @author wangzhqa
 * 
 */
public interface CellElement {

	public boolean isFormulaCell();

	public Object getValue();

	public void setValue(Object value);

	public int getRow();

	public int getCol();

	public int getValueScale();

	public void setValueScale(int valueScale);

	public int getDigits();

	public void setDigits(int digits);

	// public boolean isDimCell();

	public boolean isPercentCell();

	public void setPercentCell(boolean percentCell);

	public int getWorkBookRow();

	public void setWorkBookRow(int workBookRow);

	public int getWorkBookCol();

	public void setWorkBookCol(int workBookCol);

	public String getVarId();

	public boolean isWritable();

	public ExcelFormula getFormula();

}
