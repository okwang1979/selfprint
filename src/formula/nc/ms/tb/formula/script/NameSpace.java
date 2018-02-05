package nc.ms.tb.formula.script;



/**
 * 命名空间接口
 *
 */
public interface NameSpace
{

    public abstract Object getVariable(String s, Calculator calculator);

    public abstract Function getMethod(String s, Calculator calculator);
}
