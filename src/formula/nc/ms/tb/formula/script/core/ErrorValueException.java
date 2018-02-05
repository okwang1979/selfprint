package nc.ms.tb.formula.script.core;



public class ErrorValueException extends Exception
{

    public ErrorValueException()
    {
    }

    public ErrorValueException(String s)
    {
        super(s);
    }

    public ErrorValueException(Throwable throwable)
    {
        super(throwable);
    }

    public ErrorValueException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
