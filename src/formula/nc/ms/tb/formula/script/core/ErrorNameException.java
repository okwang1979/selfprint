package nc.ms.tb.formula.script.core;


//名字空间类出错抛出此异常
public class ErrorNameException extends Exception
{

    public ErrorNameException()
    {
    }

    public ErrorNameException(String s)
    {
        super(s);
    }

    public ErrorNameException(Throwable throwable)
    {
        super(throwable);
    }

    public ErrorNameException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
