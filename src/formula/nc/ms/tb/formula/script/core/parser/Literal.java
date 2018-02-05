package nc.ms.tb.formula.script.core.parser;


import nc.ms.tb.formula.script.Calculator;

/**
 * Êý×Ö½Úµã¡£
 *
 */
public class Literal extends AbstractNode
{
  String statement;

  public String getStatement()
  {
    return this.statement;
  }

  public void setStatement(String paramString)
  {
    this.statement = paramString;
  }

  public Object eval(Calculator paramCalculator)
  {
    if (this.statement == null)
      throw new InterpreterError("statement in literal is null");
    
    if(Calculator.STRING_BEGIN_EXCEL_QUOTATION.equals( paramCalculator.getStrBeginType())){
    	  if (this.statement.startsWith("\"")&&this.statement.endsWith("\""))
    	      return this.statement.substring(1, this.statement.length() - 1);
    }else{
    	  if (this.statement.startsWith("'")&&this.statement.endsWith("'")){
    		  Object value  = paramCalculator.getContext().getValue(this.statement+"statement");
    		  if(value==null){
    			  String rtn =  this.statement.substring(1, this.statement.length() - 1).replaceAll("'''","'");
    			  paramCalculator.getContext().setValue(this.statement+"statement",rtn);
    			  return rtn;
    		  }else{
    			  return value;
    		  }
    		 
    	  }
    	      
    }
    
  
    try
    {
      return new Integer(this.statement);
    }
    catch (Exception localException1)
    {
      try
      {
    	  if(this.statement.endsWith("%"))
    	  {
    		  
    		  if(paramCalculator.isExcelCalculator()){
    			  Object value = paramCalculator.evalExcel(this.statement.substring(0,this.statement.length()-1));
    			  return new Double(String.valueOf(value))*0.01;
    		  }else{
    			  return new Double(String.valueOf(this.statement.substring(0,this.statement.length()-1)))*0.01;
    		  }
    		
    		  
    		  
    	  }else{
    		  return new Double(this.statement);
    	  }
      
      }
      catch (Exception localException2)
      {
        throw new InterpreterError("statement in literal can not be parsed");
      }
    }
  }

  public String toString()
  {
    return this.statement;
  }
}