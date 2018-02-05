package nc.ms.tb.formula.script;

import java.io.Serializable;

public class Variable
    implements Serializable, Cloneable
{

    public Variable(Object obj)
    {
        setValue(obj);
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object obj)
    {
        if(obj == null)
            value = Primitive.NULL;
        else
            value = obj;
    }

    public String toString()
    {
        return value.toString();
    }

    Object value;
}
