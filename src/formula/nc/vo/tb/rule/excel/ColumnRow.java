package nc.vo.tb.rule.excel;

/**
 * 行列与A1之间的转换
 * 
 * @author wangzhqa
 * 
 */
public class ColumnRow {

	static final char[] digits = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private int column;
	private int row;

	public ColumnRow() {
		this(0, 0);
	}

	public ColumnRow(int row, int column) {
		this.column = column;
		this.row = row;
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int paramInt) {
		this.column = paramInt;
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int paramInt) {
		this.row = paramInt;
	}

	public String toSheetString() {
		return convertColumnRowToCellString(this);
	}

	public String toString() {
		return toSheetString();
	}

	public int hashCode() {
		int i = 17;
		i = 37 * i + this.column;
		i = 37 * i + this.row;
		return i;
	}

	public boolean equals(Object paramObject) {
		if ((paramObject == null) || (!(paramObject instanceof ColumnRow)))
			return false;
		ColumnRow localColumnRow = (ColumnRow) paramObject;
		return ((getColumn() == localColumnRow.getColumn()) && (getRow() == localColumnRow.getRow()));
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public static String convertColumnRowToCellString(ColumnRow paramColumnRow) {
		if (paramColumnRow == null)
			return "";
		if (paramColumnRow.getColumn() < 0 || paramColumnRow.getRow() < 0) {
			return "CR-1";
		}
		return convertIntToABC(paramColumnRow.getColumn() + 1) + (paramColumnRow.getRow() + 1);
	}

	private static String convertIntToABC(int paramInt) {
		int i = paramInt;
		StringBuffer localStringBuffer = new StringBuffer();
		if (i == 0)
			return "";
		while (i != 0) {
			int j = i % 26;
			if (j == 0)
				j = 26;
			localStringBuffer.insert(0, digits[(j - 1)]);
			i = (i - j) / 26;
		}
		return localStringBuffer.toString();
	}

	/**
	 * 根据字符串转换成Columnrow
	 * 
	 * @author wangzhqa
	 * @since 2012-12-1
	 * @param columnRowStr
	 * @return ColumnRow
	 */
	public static ColumnRow convertCellStringToColumnRow(String columnRowStr) {
		ColumnRow localColumnRow = new ColumnRow(-1, -1);
		if (columnRowStr == null)
			return localColumnRow;
		columnRowStr = columnRowStr.trim();
		// if (!(columnRowStr.matches("[a-zA-Z]+\\d+")))
		// return localColumnRow;
		try {
			int i = 0;
			for (; columnRowStr.getBytes().length > i; i++) {
				if (columnRowStr.getBytes()[i] < 60) {
					break;
				}
			}

			localColumnRow.setColumn(convertABCToInt(columnRowStr.substring(0, i)) - 1);
			localColumnRow.setRow(Integer.parseInt(columnRowStr.substring(i)) - 1);
		} catch (NumberFormatException e) {

		}
		return localColumnRow;
	}

	private static int convertABCToInt(String paramString) {
		int i = 0;
		Character localCharacter = Character.valueOf('A');
		paramString = paramString.toUpperCase();
		int j = paramString.length();
		for (int k = 0; k < j; ++k)
			i = Character.getNumericValue(paramString.charAt(k)) - Character.getNumericValue(localCharacter.charValue())
					+ 26 * i + 1;
		return i;
	}

}
