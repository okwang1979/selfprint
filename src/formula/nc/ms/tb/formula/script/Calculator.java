package nc.ms.tb.formula.script;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import nc.ms.tb.formula.context.IFormulaContext;
import nc.ms.tb.formula.excel.core.ITbbExcel;
import nc.ms.tb.formula.excel.parser.ExcelLexer;
import nc.ms.tb.formula.excel.parser.ExcelParser;
import nc.ms.tb.formula.script.core.parser.Expression;
import nc.ms.tb.formula.script.core.parser.TbbLexer;
import nc.ms.tb.formula.script.core.parser.TbbParser;
import nc.ms.tb.formula.script.core.parser.UtilEvalError;

/**
 * 计算类，在这里放置执行时的上下文信息。
 *
 * @author
 * @version
 * @Date
 *
 * @see
 */
public class Calculator implements Cloneable {


	/**
	 * 由于excel使用的是"双引号"作为字符串开始,NC端杜威使用"单引号"因此添加这个属性.
	 */
	public static final String  STRING_BEGIN_EXCEL_QUOTATION ="Double";

	public static final String  STRING_BEGIN_NC_QUOTATION ="Single";
	private Map<String, Variable> variables;

	private NameSpace globalNameSpace;

	private Calculator parent;

	// private Set inparseRCESet;

	private static Map parsedExpression = new HashMap();



	/**
	 *
	 * 缓存计算结果的Map,确保结果只计算一次.只对Excel有效.
	 */
	private Map<String, Map<String, Object>>  finishFormula = new HashMap<String, Map<String,Object>>();

	private IFormulaContext context;


	private  String strBeginType = STRING_BEGIN_NC_QUOTATION;

	private ITbbExcel tbbExcel;


	private int calculatorLevel = 1 ;

	/**
	 * 委托解析.
	 */
	private IParseCellElementDelegate parseCellElementDelegate = new  EvalParseCellElementDelegate();



	/**
	 * 标识:Excel单元格取值返回CellElement本身,默认返回CellElement的Value.
	 */
	private boolean cellValueIsCell = false;


	private FormulaExecuteInfo  excuteInfo = new FormulaExecuteInfo();




	public boolean isCellValueIsCell() {
		return cellValueIsCell;
	}


	public void setCellValueIsCell(boolean cellValueIsCell) {
		this.cellValueIsCell = cellValueIsCell;
	}


	/**
	 * 如果是Excel计算器,返回true.
	 * @author wangzhqa
	 * @since 2013-3-21
	 * @return
	 * boolean
	 */
	public boolean isExcelCalculator(){
		return STRING_BEGIN_EXCEL_QUOTATION.equals(this.getStrBeginType());
	}


	public ITbbExcel getTbbExcel() {
		return tbbExcel;
	}

	public void setTbbExcel(ITbbExcel tbbExcel) {
		this.tbbExcel = tbbExcel;
	}

	public Calculator() {
		variables = new HashMap<String, Variable>();
		// inparseRCESet = new HashSet();
	}

	public Calculator(IFormulaContext planContext) {
		this();
		this.context = planContext;

	}

	public IFormulaContext getContext() {
		return this.context;
	}

	public void setParent(Calculator calculator) {
		parent = calculator;
	}

	public Calculator getParent() {
		return parent;
	}

	public NameSpace getNameSpace() {
		if (globalNameSpace != null)
			return globalNameSpace;
		else {
			return null;
		}
	}

	public void setNameSpace(NameSpace namespace) {
		globalNameSpace = namespace;
	}

	public void set(String s, Object obj) {
		if (s == null)
			return;
		if (obj == null)
			obj = Primitive.NULL;
		Variable variable = (Variable) variables.get(s);
		if (variable != null)
			variable.setValue(obj);
		else
			variables.put(s, new Variable(obj));
	}

	public Object get(String s) {
		return unwrapVariable((Variable) variables.get(s));
	}




	public  Object evalExcel(String s) throws UtilEvalError{

		if(s==null||0==s.length()){
			return "";
		}

		Object obj = null;
		try {
			if (parent != null) {

				obj = parent.evalExcel(s);
			}
		} catch (Exception exception) {
		}
//		parsedExpression.clear();
		if (obj == null) {
			Expression expression = (Expression) parsedExpression.get(s);
			if (expression == null) {

				StringReader stringreader = new StringReader(s);
				ExcelLexer frlexer = new ExcelLexer(stringreader);
				ExcelParser frparser = new ExcelParser(frlexer);
				try {
					expression = frparser.parse();
//					parsedExpression.put(s, expression);
				} catch (RecognitionException recognitionexception) {

					throw new UtilEvalError("公式语法错误！");
				} catch (TokenStreamException tokenstreamexception) {

					throw new UtilEvalError("公式语法错误！");
				}
			}
			if (expression != null){

//				String sheetName = this.getTbbExcel()
//				.getCurrentSheet().getName();


					obj = expression.eval(this);





//				this.getContext()
			}

		}

		return obj;

	}

	public Object eval(String s) throws UtilEvalError {

		Object obj = null;
		try {
			if (parent != null) {

				obj = parent.eval(s);
			}
		} catch (Exception exception) {
		}
		if (obj == null) {
			Expression expression = (Expression) parsedExpression.get(s);
			if (expression == null) {
				// StringReader stringreader = new StringReader(
				// this.eraseFormulaBlank(s));
				StringReader stringreader = new StringReader(s);
				TbbLexer frlexer = new TbbLexer(stringreader);
				TbbParser frparser = new TbbParser(frlexer);
				try {
					expression = frparser.parse();
					parsedExpression.put(s, expression);
				} catch (RecognitionException recognitionexception) {
//					recognitionexception.printStackTrace();
					throw new UtilEvalError("公式语法错误！");
				} catch (TokenStreamException tokenstreamexception) {
//					tokenstreamexception.printStackTrace();
					throw new UtilEvalError("公式语法错误！");
				}
			}
			if (expression != null)
				obj = expression.eval(this);
		}
		return obj;

	}

	// public void clearTrace() {
	// inparseRCESet.clear();
	// }

	private Object unwrapVariable(Variable variable) {
		return variable != null ? variable.getValue() : null;
	}

	public static void main(String args[]) {
		CalClone();
	}

	private static void CalClone() {
		Calculator calculator = new Calculator();

		try {
			Calculator calculator1 = (Calculator) calculator.clone();

		} catch (CloneNotSupportedException clonenotsupportedexception) {
//			clonenotsupportedexception.printStackTrace();
		}
	}

	// private static void lexer() {
	// String s = "\"\u770B\u4E86\uFF08\"";
	// StringReader stringreader = new StringReader(s);
	// FRLexer frlexer = new FRLexer(stringreader);
	// try {
	// Token token;
	// for (; (token = frlexer.nextToken()) != null
	// && token.getText() != null; System.out.println("\ttype:"
	// + token.getType()))
	// System.out.print("token:" + token.getText());
	//
	// } catch (TokenStreamException tokenstreamexception) {
	// tokenstreamexception.printStackTrace();
	// }
	// }



	private static void parse() {
		String s = "  aaaa = \"abc\" + 12 + 12/23 * 34 + (343%45)";
		Calculator calculator = new Calculator();
		try {
			Object obj = calculator.eval(s);
//			System.out.println("result:" + obj);
		} catch (Exception exception) {
//			exception.printStackTrace();
		}
	}

	public FormulaExecuteInfo getExcuteInfo() {
		return excuteInfo;
	}


	public String getStrBeginType() {
		return strBeginType;
	}

	public void setStrBeginType(String strBeginType) {
		this.strBeginType = strBeginType;
	}

	public void setContext(IFormulaContext context) {
		this.context = context;
	}

	public static String eraseFormulaBlank(String s) {
		Matcher matcher = Pattern.compile("\"[^\"]*\"").matcher(s);
		ArrayList arraylist = new ArrayList();
		for (; matcher.find(); arraylist.add(matcher.group()))
			;
		String as[] = s.split("\"[^\"]*\"");
		StringBuffer stringbuffer = new StringBuffer();
		int i = 0;
		for (int j = as.length; i < j; i++) {
			stringbuffer.append(as[i].replaceAll("\\s+", ""));
			if (i < j - 1)
				stringbuffer.append(arraylist.remove(0));
		}

		if (arraylist.size() > 0)
			stringbuffer.append(arraylist.remove(0));
		return stringbuffer.toString();
	}

	public int getCalculatorLevel() {
		return calculatorLevel;
	}

	public void setCalculatorLevel(int calculatorLevel) {
		this.calculatorLevel = calculatorLevel;
	}

	public IParseCellElementDelegate getParseCellElementDelegate() {
		return parseCellElementDelegate;
	}

	public void setParseCellElementDelegate(
			IParseCellElementDelegate parseCellElementDelegate) {
		this.parseCellElementDelegate = parseCellElementDelegate;
	}





}