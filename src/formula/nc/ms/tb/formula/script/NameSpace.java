package nc.ms.tb.formula.script;



/**
 * �����ռ�ӿ�
 *
 */
public interface NameSpace
{

    public abstract Object getVariable(String s, Calculator calculator);

    public abstract Function getMethod(String s, Calculator calculator);
}
