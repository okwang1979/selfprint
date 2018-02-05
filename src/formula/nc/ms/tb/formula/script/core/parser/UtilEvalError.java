package nc.ms.tb.formula.script.core.parser;

//通用错误异，节点运算时抛出此异常。
public class UtilEvalError extends Exception
{

    protected UtilEvalError()
    {
    }

    public UtilEvalError(String s)
    {
        super(s);
    }
}
