
package nc.ms.tb.formula.script.core.parser;




import java.util.Map;


import nc.ms.tb.formula.script.Calculator;
import nc.ms.tb.formula.script.NameSpace;
import nc.ms.tb.formula.script.core.DefaultNameSpace;




/**
 * 除 function 与 literal外其他处理问题都是由此节点进行处理的。
 *
 */
public class Ambiguity extends AbstractNode
{
	String statement;

    public Ambiguity()
    {
    }

    public String getStatement()
    {
        return statement;
    }

    public void setStatement(String s)
    {
        statement = s;
    }

    public Object eval(Calculator calculator)
    {
        if(statement == null)
            throw new InterpreterError("statement in literal is null");
        Object obj = calculator.get(statement);
        if(obj == null)
        {
            NameSpace namespace = calculator.getNameSpace();
            if(namespace == null)
                namespace = DefaultNameSpace.getInstance();
            obj = namespace.getVariable(statement, calculator);
        }
        if(obj == null)
            throw new InterpreterError("null field resolved: " + statement);
        else
            return obj;
    }

    public void collect(Map map)
    {
        super.collect(map);
    }

    public String toString()
    {
        return statement;
    }

    
}
