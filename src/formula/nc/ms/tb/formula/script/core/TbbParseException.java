package nc.ms.tb.formula.script.core;

public class TbbParseException extends Exception{

	 public TbbParseException()
	    {
	    }

	    public TbbParseException(String s)
	    {
	        super(s);
	    }

	    public TbbParseException(Throwable throwable)
	    {
	        super(throwable);
	    }

	    public TbbParseException(String s, Throwable throwable)
	    {
	        super(s, throwable);
	    }
}
