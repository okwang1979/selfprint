
package nc.ms.tb.formula.script.core.parser;


/**
 * 解析器错误是抛出此异常。
 *
 */
public class InterpreterError extends RuntimeException
{

    public InterpreterError(String s)
    {
        super(s);
    }
}
