
package nc.ms.tb.formula.script;

import java.io.Serializable;


/**
 *
 */
public class Primitive
    implements Serializable
{

    private Primitive(int i)
    {
        idx = i;
    }



    public String toString()
    {
        if(this == ERROR_NAME)
            return "";
        if(this == ERROR_VALUE)
            return "";
        else
            return "";
    }

    private int idx;
    public static final Primitive NULL;
    public static final Primitive ERROR_NAME;
    public static final Primitive ERROR_VALUE;
    private static final Primitive ps[];

    static 
    {
        NULL = new Primitive(0);
        ERROR_NAME = new Primitive(1);
        ERROR_VALUE = new Primitive(2);
        ps = (new Primitive[] {
            NULL, ERROR_NAME, ERROR_VALUE
        });
    }
}
