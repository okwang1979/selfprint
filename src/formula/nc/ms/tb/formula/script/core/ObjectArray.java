package nc.ms.tb.formula.script.core;



import java.io.Serializable;
import java.util.*;

/**
 * excel单元格返回多个值使用此类,返回.
 *
 */
public class ObjectArray
    implements Serializable, Cloneable
{
	
	
	
	private int rowCount = 0 ;
	
	private int colCount = 0;

    public ObjectArray()
    {
        valueList = new ArrayList();
    }

    public void addValue(Object obj)
    {
        valueList.add(obj);
    }

    public void addObjectArray(ObjectArray objectarray)
    {
        valueList.addAll(objectarray.valueList);
    }

    public void addCollection(Collection collection)
    {
        valueList.addAll(collection);
    }

    public Object getValue(int i)
    {
        return valueList.get(i);
    }

    public int length()
    {
        return valueList.size();
    }

    public List getValueList()
    {
    	
    	
        return   Collections.unmodifiableList(valueList);
    }

    public String toString()
    {
        return valueList.toString();
    }

    private List valueList;

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
