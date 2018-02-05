package nc.ms.tb.formula.script;

import java.io.PrintWriter;
import org.w3c.dom.Element;

/**
 * 函数定义，可以根据函数定义进行解析。
 *
 */
public class FunctionDef
     
{

    public FunctionDef()
    {
        this("", "");
    }

    public FunctionDef(String s, String s1)
    {
        this(s, "", s1);
    }

    public FunctionDef(String s, String s1, String s2)
    {
        name = "";
        description = "";
        setName(s);
        setDescription(s1);
        setClassName(s2);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String s)
    {
        name = s;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String s)
    {
        description = s;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String s)
    {
        className = s;
    }

    public void readXML(Element element)
    {
    }

    public void writeXML(PrintWriter printwriter)
    {
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        return super.clone();
    }

    private String name;
    private String description;
    private String className;
}
