package nc.ms.tb.formula.script.core.parser;





/**
 * 计算时抛出此异常
 *
 */
public class UtilTargetError extends UtilEvalError
{

    public UtilTargetError(String s, Throwable throwable)
    {
        super(s);
        t = throwable;
    }

    public UtilTargetError(Throwable throwable)
    {
        this(null, throwable);
    }

    public Throwable t;
}
