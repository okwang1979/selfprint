package nc.ms.tb.formula.script.core.parser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import nc.ms.tb.formula.script.Primitive;
import nc.ms.tb.formula.script.Variable;
import nc.ms.tb.formula.script.core.FunctionHelper;
import nc.ms.tb.formula.script.core.ObjectArray;
import nc.vo.tb.rule.excel.CellElement;

/**
 * 公式计算通用类
 * 
 */
public class OperationUtils {
	public static Object binaryOperation(Object paramObject1,
			Object paramObject2, String paramString) throws UtilEvalError {
		Object localObject3;
		if ((paramObject1 == Primitive.ERROR_NAME)
				|| (paramObject1 == Primitive.ERROR_VALUE)
				|| (paramObject2 == Primitive.ERROR_NAME)
				|| (paramObject2 == Primitive.ERROR_VALUE))
			return Primitive.ERROR_NAME;
		if ((paramObject1 instanceof Date) && (paramObject2 instanceof Integer))
			if (Arrays.asList(new String[] { "+", "-" }).indexOf(paramString) > -1)
				return datePSInteger((Date) paramObject1,
						(Integer) paramObject2, paramString);
		// if ((paramObject1 instanceof String)
		// && (paramObject2 instanceof Integer)
		// && ("*".equals(paramString)))
		// return stringMultiInteger((String) paramObject1,
		// (Integer) paramObject2);
		Object[] arrayOfObject = promotePrimitives(paramObject1, paramObject2);
		Object localObject1 = arrayOfObject[0];
		Object localObject2 = arrayOfObject[1];
		if ((localObject1.getClass() != localObject2.getClass())
				&& (localObject1 != Primitive.NULL)
				&& (localObject2 != Primitive.NULL)) {
			if (localObject1 instanceof Number
					&& localObject2 instanceof Number) {
				localObject1 = ((Number) localObject1).doubleValue();
				localObject2 = ((Number) localObject2).doubleValue();
			} else {
				localObject1 = String.valueOf(localObject1);
				localObject2 = String.valueOf(localObject2);
			}
			

		}

		try {
			localObject3 = binaryOperationImpl(localObject1, localObject2,
					paramString);
		} catch (UtilEvalError localUtilEvalError) {
			throw new UtilTargetError("Arithemetic Exception in binary op",
					localUtilEvalError);
//			return true;
		}
		return localObject3;
	}

	static Object binaryOperationImpl(Object paramObject1, Object paramObject2,
			String paramString) throws UtilEvalError {
		if ((paramObject1 == Primitive.NULL)
				|| (paramObject2 == Primitive.NULL))
			return nullBinaryOperation(paramObject1, paramObject2, paramString);
		if (paramObject1 instanceof String)
			return stringBinaryOperation((String) paramObject1,
					(String) paramObject2, paramString);
		if (paramObject1 instanceof Boolean)
			return booleanBinaryOperation((Boolean) paramObject1,
					(Boolean) paramObject2, paramString);
		if (paramObject1 instanceof Integer)
			return intBinaryOperation((Integer) paramObject1,
					(Integer) paramObject2, paramString);
		if (paramObject1 instanceof Long)
			return longBinaryOperation((Long) paramObject1,
					(Long) paramObject2, paramString);
		if (paramObject1 instanceof Float)
			return floatBinaryOperation((Float) paramObject1,
					(Float) paramObject2, paramString);
		if (paramObject1 instanceof Double)
			return doubleBinaryOperation((Double) paramObject1,
					(Double) paramObject2, paramString);
		if (paramObject1 instanceof Date)
			return dateBinaryOperation((Date) paramObject1,
					(Date) paramObject2, paramString);
		if (paramObject1 instanceof Number) {
			return doubleBinaryOperation(((Number) paramObject1).doubleValue(),
					((Number) paramObject2).doubleValue(), paramString);
		}
	
		if(paramObject1 instanceof ObjectArray&&paramObject2 instanceof ObjectArray){
			
			
			return OperationUtils.binaryOperation(getArrayValue((ObjectArray)paramObject1),getArrayValue((ObjectArray)paramObject2), "+");
			 
		}
		if(paramObject1 instanceof List&&paramObject2 instanceof List){
			ObjectArray array1= new ObjectArray();
			array1.addCollection((List)paramObject1);
			ObjectArray array2= new ObjectArray();
			array2.addCollection((List)paramObject2);
			
			return OperationUtils.binaryOperation(getArrayValue(array1),getArrayValue(array2), paramString);
		}
		 
		
		throw new UtilEvalError("Invalid types in binary operator");
	}
	
	private static Object getArrayValue(ObjectArray objArray){
		
	
		
		if(objArray.getValueList()!=null&&objArray.getValueList().size()>0){
			List<Number> numbers = new ArrayList<Number>();
			for(Object obj:objArray.getValueList()){
				String value = "";
				if(obj instanceof Number){
					numbers.add((Number)obj);
				}else if(obj instanceof CellElement){
					
					if(((CellElement)obj).getValue() instanceof Number){
						numbers.add((Number)((CellElement)obj).getValue());
					} else{
						try{
							numbers.add(Double.parseDouble( String.valueOf(((CellElement)obj).getValue())));
						}catch(Exception ex){
							
						}
					}
					 
				} 
				else{
					try{
						numbers.add(Double.parseDouble( String.valueOf(obj)));
					}catch(Exception ex){
						
					}
				}
			}
			   Object d = 0D;
			for(Number number:numbers){
				try {
					d = OperationUtils.binaryOperation(d, number.doubleValue(), "+");
				} catch (UtilEvalError e) {
					 
				}
			}
			return d;
		}
		return 0;
		
	}

	static Object datePSInteger(Date paramDate, Integer paramInteger,
			String paramString) {
		if ("+".equals(paramString)) {
			long l = paramDate.getTime();
			l += paramInteger.intValue() * 24L * 3600L * 1000L;
			return new Date(l);
		}
		long l = paramDate.getTime();
		l -= paramInteger.intValue() * 24L * 3600L * 1000L;
		return new Date(l);
	}

	static Object stringMultiInteger(String paramString, Integer paramInteger) {
		int i = paramInteger.intValue();
		StringBuffer localStringBuffer = new StringBuffer();
		for (int j = 0; j < i; ++j)
			localStringBuffer.append(paramString);
		return localStringBuffer.toString();
	}

	static Object nullBinaryOperation(Object paramObject1, Object paramObject2,
			String paramString) throws UtilEvalError {
		Object localObject = Primitive.NULL;
		if(paramObject1== Primitive.NULL){
			if(paramObject2 == Primitive.NULL){
				
				if ("<".equals(paramString))
					return Boolean.FALSE;
				if (">".equals(paramString))
					return Boolean.FALSE;
				if ("==".equals(paramString))
					return Boolean.TRUE;
				if ("<=".equals(paramString))
					return Boolean.TRUE;
				if (">=".equals(paramString))
					return Boolean.TRUE;
				if (("!=".equals(paramString)) || ("<>".equals(paramString)))
					return Boolean.FALSE;
				if("=".equals(paramString)){
					return Boolean.TRUE;
				}

				
				
				return Primitive.NULL;
			}else{
				if(paramObject2 instanceof Number){
					return doubleBinaryOperation(0D, ((Number)paramObject2).doubleValue(), paramString);
				}else{
					return stringBinaryOperation("", String.valueOf(paramObject2), paramString);
				}
			}
		}else if(paramObject2 == Primitive.NULL){
			if(paramObject1 instanceof Number){
				return doubleBinaryOperation( ((Number)paramObject1).doubleValue(),0D, paramString);
			}else{
				return stringBinaryOperation(String.valueOf(paramObject1),"", paramString);
			}
		}
		
//		if (paramObject1 == Primitive.NULL)
//			if ("==".equals(paramString))
//				localObject = Boolean.valueOf(paramObject2 == Primitive.NULL);
//			else if (("!=".equals(paramString)) || ("<>".equals(paramString)))
//				localObject = Boolean.valueOf(paramObject2 != Primitive.NULL);
//			else if (paramObject2 instanceof Number)
//				localObject = binaryOperation(Integer.valueOf(0), paramObject2,
//						paramString);
//			else if (paramObject2 instanceof String)
//				localObject = binaryOperation("", paramObject2, paramString);
//			else if (paramObject2 == Primitive.NULL)
//				if ("==".equals(paramString))
//					localObject = Boolean
//							.valueOf(paramObject1 == Primitive.NULL);
//				else if (("!=".equals(paramString))
//						|| ("<>".equals(paramString)))
//					localObject = Boolean
//							.valueOf(paramObject1 != Primitive.NULL);
//				else if (paramObject1 instanceof Number)
//					localObject = binaryOperation(paramObject1,
//							Integer.valueOf(0), paramString);
//				else if (paramObject1 instanceof String)
//					localObject = binaryOperation(paramObject1, "", paramString);
		return stringBinaryOperation(String.valueOf(paramObject1), String.valueOf(paramObject2), paramString);
	}

	static Object stringBinaryOperation(String paramString1,
			String paramString2, String paramString3) throws UtilEvalError {
		
		
		
		
		
		
		if ("=".equals(paramString3)) {
			if ("".equals(paramString2)) {
				paramString2 = "0";
			}
			if ("".equals(paramString1)) {
				paramString1 = "0";
			}
			Double d3 = stringToDouble(paramString1);
			Double d4 = stringToDouble(paramString2);
			if (d3 != null && d4 != null) {
				return doubleBinaryOperation(d3, d4, paramString3);
			} else {
				return String.valueOf(paramString1).equals(paramString2);
			}
		}

		if (">".equals(paramString3)) {
			int i = paramString1.compareTo(paramString2);
			if (i <= 0)
				return new Boolean(false);
			return new Boolean(true);
		}

		if ("<".equals(paramString3)) {
			int j = paramString1.compareTo(paramString2);
			if (j < 0)
				return new Boolean(true);
			return new Boolean(false);
		}

		if (">=".equals(paramString3)) {
			int k = paramString1.compareTo(paramString2);
			if (k >= 0)
				return new Boolean(true);
			return new Boolean(false);
		}
		if ("<=".equals(paramString3)) {
			int l = paramString1.compareTo(paramString2);
			if (l <= 0)
				return new Boolean(true);
			return new Boolean(false);
		}
		if("<>".equals(paramString3)){
			if("".equals(paramString1)){
				paramString1 ="0";
			}
			if("".equals(paramString2)){
				paramString2 ="0";
			}
			return !paramString1.equals(paramString2);
		}


		Double d1 = stringToDouble(paramString1);
		Double d2 = stringToDouble(paramString2);
		if (d1 != null && d2 != null) {
			return doubleBinaryOperation(d1, d2, paramString3);
		}

		if ("+".equals(paramString3)) {
			return paramString1 + paramString2;
		}else{
			if(d1==null)  d1=0D;
			if(d2==null)  d2 = 0D;
				return doubleBinaryOperation(d1, d2, paramString3);
		}
		

	}

	private static Double stringToDouble(String value) {
		if (value == null) {
			return null;
		}
		value = value.replaceAll(",", "");
//		if (value.contains(",")) {
//			value = value.replaceAll(",", "");
//		}
		try {
			if(value.length()>5&&value.charAt(4)=='-')return null;
			double d1 = Double.parseDouble(value);

			return d1;
		} catch (Exception ex) {
			try {
				if (value.endsWith("%")) {
					double d1 = Double.parseDouble(value.substring(0,
							value.length() - 1)) * 0.01;
					return d1;
				}
			} catch (Exception e) {
				return null;
			}

		}
		return null;
	}

	private static Boolean isEqualse(Object obj1, Object obj2) {

		if (obj1 instanceof Number) {

		}
		if (obj1 instanceof String) {

		}
		if (obj1 == null) {

		}
		if (obj1 instanceof Number) {

		}
		if (obj1 instanceof String) {

		}
		if (obj1 == null) {

		}
		return false;
	}

	static Boolean booleanBinaryOperation(Boolean paramBoolean1,
			Boolean paramBoolean2, String paramString) {
		boolean bool1 = paramBoolean1.booleanValue();
		boolean bool2 = paramBoolean2.booleanValue();
		if ("==".equals(paramString))
			return ((bool1 == bool2) ? Boolean.TRUE : Boolean.FALSE);
		if (("!=".equals(paramString)) || ("<>".equals(paramString)))
			return ((bool1 != bool2) ? Boolean.TRUE : Boolean.FALSE);
		if ("||".equals(paramString))
			return (((bool1) || (bool2)) ? Boolean.TRUE : Boolean.FALSE);
		if ("&&".equals(paramString))
			return (((bool1) && (bool2)) ? Boolean.TRUE : Boolean.FALSE);
		if ("*".equals(paramString))
			return (((bool1) && (bool2)) ? Boolean.TRUE : Boolean.FALSE);
		if ("+".equals(paramString))
			return (((bool1) || (bool2)) ? Boolean.TRUE : Boolean.FALSE);
		throw new InterpreterError("unimplemented binary bool operator:\n"
				+ paramBoolean1 + paramString + paramBoolean2);
	}

	static Object intBinaryOperation(Integer paramInteger1,
			Integer paramInteger2, String paramString) {
		int i = paramInteger1.intValue();
		int j = paramInteger2.intValue();
		if ("<".equals(paramString))
			return ((i < j) ? Boolean.TRUE : Boolean.FALSE);
		if (">".equals(paramString))
			return ((i > j) ? Boolean.TRUE : Boolean.FALSE);
		if ("==".equals(paramString))
			return ((i == j) ? Boolean.TRUE : Boolean.FALSE);
		if ("=".equals(paramString))
			return ((i == j) ? Boolean.TRUE : Boolean.FALSE);
		if ("<=".equals(paramString))
			return ((i <= j) ? Boolean.TRUE : Boolean.FALSE);
		if (">=".equals(paramString))
			return ((i >= j) ? Boolean.TRUE : Boolean.FALSE);
		if (("!=".equals(paramString)) || ("<>".equals(paramString)))
			return ((i != j) ? Boolean.TRUE : Boolean.FALSE);
		if ("+".equals(paramString))
			return Double.valueOf(i + j);
		if ("-".equals(paramString))
			return Double.valueOf(i - j);
		if ("*".equals(paramString))
			return Double.valueOf(i * j);
		if ("/".equals(paramString)) {
			if (j == 0)
				return Integer.valueOf(0);
			return new Double(new Double(i).doubleValue()
					/ new Double(j).doubleValue());
		}
		if ("%".equals(paramString))
			return Integer.valueOf(i % j);
		if ("^".equals(paramString))
			return new Double(Math.pow(i, j));
		throw new InterpreterError("Unimplemented binary int operator:\n"
				+ paramInteger1 + paramString + paramInteger2);
	}

	static Object longBinaryOperation(Long paramLong1, Long paramLong2,
			String paramString) {
		long l1 = paramLong1.longValue();
		long l2 = paramLong2.longValue();
		if ("<".equals(paramString))
			return ((l1 < l2) ? Boolean.TRUE : Boolean.FALSE);
		if (">".equals(paramString))
			return ((l1 > l2) ? Boolean.TRUE : Boolean.FALSE);
		if ("==".equals(paramString))
			return ((l1 == l2) ? Boolean.TRUE : Boolean.FALSE);
		if ("<=".equals(paramString))
			return ((l1 <= l2) ? Boolean.TRUE : Boolean.FALSE);
		if (">=".equals(paramString))
			return ((l1 >= l2) ? Boolean.TRUE : Boolean.FALSE);
		if (("!=".equals(paramString)) || ("<>".equals(paramString)))
			return ((l1 != l2) ? Boolean.TRUE : Boolean.FALSE);
		if ("+".equals(paramString))
			return Long.valueOf(l1 + l2);
		if ("-".equals(paramString))
			return Long.valueOf(l1 - l2);
		if ("*".equals(paramString))
			return Long.valueOf(l1 * l2);
		if ("/".equals(paramString))
			return Long.valueOf(l1 / l2);
		if ("%".equals(paramString))
			return Long.valueOf(l1 % l2);
		if ("^".equals(paramString))
			return new Double(Math.pow(l1, l2));
		throw new InterpreterError("Unimplemented binary long operator:\n"
				+ paramLong1 + paramString + paramLong2);
	}

	static Object floatBinaryOperation(Float paramFloat1, Float paramFloat2,
			String paramString) throws UtilEvalError {
		float f1 = paramFloat1.floatValue();
		float f2 = paramFloat2.floatValue();
		if ("<".equals(paramString))
			return ((f1 < f2) ? Boolean.TRUE : Boolean.FALSE);
		if (">".equals(paramString))
			return ((f1 > f2) ? Boolean.TRUE : Boolean.FALSE);
		if ("==".equals(paramString))
			return ((f1 == f2) ? Boolean.TRUE : Boolean.FALSE);
		if ("<=".equals(paramString))
			return ((f1 <= f2) ? Boolean.TRUE : Boolean.FALSE);
		if (">=".equals(paramString))
			return ((f1 >= f2) ? Boolean.TRUE : Boolean.FALSE);
		if (("!=".equals(paramString)) || ("<>".equals(paramString)))
			return ((f1 != f2) ? Boolean.TRUE : Boolean.FALSE);
		if ("+".equals(paramString))
			return new Float(f1 + f2);
		if ("-".equals(paramString))
			return new Float(f1 - f2);
		if ("*".equals(paramString))
			return new Float(f1 * f2);
		if ("/".equals(paramString))
			return new Float(f1 / f2);
		if ("%".equals(paramString))
			return new Float(f1 % f2);
		if ("^".equals(paramString))
			return new Double(Math.pow(f1, f2));
		throw new InterpreterError("Unimplemented binary float operator:\n"
				+ paramFloat1 + paramString + paramFloat2);
	}

	static Object doubleBinaryOperation(Double paramDouble1,
			Double paramDouble2, String paramString) throws UtilEvalError {
 
		double d1 = paramDouble1;
		double d2 = paramDouble2;

		// double d1 = paramDouble1;
		// double d2 = paramDouble2;
		if ("<".equals(paramString))
			return ((d1 < d2) ? Boolean.TRUE : Boolean.FALSE);
		if (">".equals(paramString))
			return ((d1 > d2) ? Boolean.TRUE : Boolean.FALSE);
		if ("==".equals(paramString))
			return ((d1 == d2) ? Boolean.TRUE : Boolean.FALSE);
		if ("=".equals(paramString))
			return ((d1 == d2) ? Boolean.TRUE : Boolean.FALSE);
		if ("<=".equals(paramString))
			return ((d1 <= d2) ? Boolean.TRUE : Boolean.FALSE);
		if (">=".equals(paramString))
			return ((d1 >= d2) ? Boolean.TRUE : Boolean.FALSE);
		if (("!=".equals(paramString)) || ("<>".equals(paramString)))
			return ((d1 != d2) ? Boolean.TRUE : Boolean.FALSE);
		if ("+".equals(paramString))
			return new Double(new BigDecimal(d1 + "").add(
					new BigDecimal(d2 + "")).doubleValue());
		if ("-".equals(paramString))
			try {
				return new Double(new BigDecimal(d1 + "").subtract(
						new BigDecimal(d2 + "")).doubleValue());
			} catch (Exception ex) {
				throw new UtilEvalError();
				// return 0D;
			}

		if ("*".equals(paramString))
			try {
				return new Double(new BigDecimal(d1 + "").multiply(
						new BigDecimal(d2 + "")).doubleValue());
			} catch (NumberFormatException ex) {
				return 0;
			}

		if ("/".equals(paramString)) {
			if (d2 == 0D) {
				return 0D;
			}
			return new Double(d1 / d2);
		}

		if ("%".equals(paramString))
			return new Double(d1 % d2);
		if ("^".equals(paramString))
			return new Double(Math.pow(d1, d2));
		throw new InterpreterError("Unimplemented binary double operator:\n"
				+ paramDouble1 + paramString + paramDouble2);
	}

	static Object dateBinaryOperation(Date paramDate1, Date paramDate2,
			String paramString) {
		if ("<".equals(paramString))
			return ((paramDate1.compareTo(paramDate2) < 0) ? Boolean.TRUE
					: Boolean.FALSE);
		if (">".equals(paramString))
			return ((paramDate1.compareTo(paramDate2) > 0) ? Boolean.TRUE
					: Boolean.FALSE);
		if ("==".equals(paramString))
			return ((paramDate1.compareTo(paramDate2) == 0) ? Boolean.TRUE
					: Boolean.FALSE);
		if ("<=".equals(paramString))
			return ((paramDate1.compareTo(paramDate2) <= 0) ? Boolean.TRUE
					: Boolean.FALSE);
		if (">=".equals(paramString))
			return ((paramDate1.compareTo(paramDate2) >= 0) ? Boolean.TRUE
					: Boolean.FALSE);
		if (("!=".equals(paramString)) || ("<>".equals(paramString)))
			return ((paramDate1.compareTo(paramDate2) != 0) ? Boolean.TRUE
					: Boolean.FALSE);
		if ("-".equals(paramString)) {
			long l1 = paramDate1.getTime();
			long l2 = paramDate2.getTime();
			long l3 = l1 - l2;
			int i = (int) (l3 / 86400000L);
			return Integer.valueOf(i);
		}
		throw new InterpreterError("Unimplemented binary double operator:\n"
				+ paramDate1 + paramString + paramDate2);
	}

	static boolean booleanUnaryOperation(Boolean paramBoolean,
			String paramString) throws UtilEvalError {
		boolean bool = paramBoolean.booleanValue();
		if ("!".equals(paramString))
			return (!(bool));
		throw new UtilEvalError("Operator inappropriate for boolean:\n"
				+ paramString + paramBoolean);
	}

	static int intUnaryOperation(Integer paramInteger, String paramString) {
		int i = paramInteger.intValue();
		if ("+".equals(paramString))
			return i;
		if ("-".equals(paramString))
			return (-i);
		throw new InterpreterError("bad integer unaryOperation:\n"
				+ paramString + paramInteger);
	}

	static long longUnaryOperation(Long paramLong, String paramString) {
		long l = paramLong.longValue();
		if ("+".equals(paramString))
			return l;
		if ("-".equals(paramString))
			return (-l);
		throw new InterpreterError("bad long unaryOperation:\n" + paramString
				+ paramLong);
	}

	static float floatUnaryOperation(Float paramFloat, String paramString) {
		float f = paramFloat.floatValue();
		if ("+".equals(paramString))
			return f;
		if ("-".equals(paramString))
			return (-f);
		throw new InterpreterError("bad float unaryOperation:\n" + paramString
				+ paramFloat);
	}

	static double doubleUnaryOperation(Double paramDouble, String paramString) {
		double d = paramDouble.doubleValue();
		if ("+".equals(paramString))
			return d;
		if ("-".equals(paramString))
			return (-d);
		throw new InterpreterError("bad double unaryOperation:\n" + paramString
				+ paramDouble);
	}

	static Object[] promotePrimitives(Object paramObject1, Object paramObject2) {
		boolean bool = false;
		Object localObject;
		while (paramObject1 instanceof Variable)
			paramObject1 = ((Variable) paramObject1).getValue();
		while (paramObject2 instanceof Variable)
			paramObject2 = ((Variable) paramObject2).getValue();
		paramObject1 = promoteToInteger(paramObject1);
		paramObject2 = promoteToInteger(paramObject2);
		if ((paramObject1 instanceof Date) && (!(paramObject2 instanceof Date)))
			try {
				paramObject2 = FunctionHelper.getDataFormat().parse(
						paramObject2.toString());
			} catch (Exception localException1) {
				try {
					paramObject2 = FunctionHelper.getDataFormat().parse(
							paramObject2.toString());
				} catch (ParseException localParseException1) {
					// FRCoreContext.log(localParseException1);
				}
			}
		if ((paramObject2 instanceof Date) && (!(paramObject1 instanceof Date)))
			try {
				paramObject1 = FunctionHelper.getDataFormat().parse(
						paramObject1.toString());
			} catch (Exception localException2) {
				try {
					paramObject1 = FunctionHelper.getDataFormat().parse(
							paramObject1.toString());
				} catch (ParseException localParseException2) {
					// FRCoreContext.log(localParseException2);
				}
			}
		if ((bool
				&& ((paramObject1 instanceof Number) || (paramObject2 instanceof Number)) ? 1
				: 0) != 0)
			if ((paramObject2 instanceof Number) && (!(bool))) {
				localObject = paramObject1.toString();
				if (((String) localObject).matches("\\d+"))
					paramObject1 = Integer.valueOf((String) localObject);
				else if (((String) localObject).matches("\\d*\\.\\d+"))
					paramObject1 = Double.valueOf((String) localObject);
			} else if (!(paramObject2 instanceof Number)) {
				localObject = paramObject2.toString();
				if (((String) localObject).matches("\\d+"))
					paramObject2 = Integer.valueOf((String) localObject);
				else if (((String) localObject).matches("\\d*\\.\\d+"))
					paramObject2 = Double.valueOf((String) localObject);
			}
		if ((paramObject1 instanceof Number)
				&& (paramObject2 instanceof Number)) {
			if (paramObject1 instanceof BigDecimal)
				paramObject1 = new Double(
						((BigDecimal) paramObject1).doubleValue());
			if (paramObject2 instanceof BigDecimal)
				paramObject2 = new Double(
						((BigDecimal) paramObject2).doubleValue());
			if (paramObject1 instanceof BigInteger)
				paramObject1 = new Double(
						((BigInteger) paramObject1).doubleValue());
			if (paramObject2 instanceof BigInteger)
				paramObject2 = new Double(
						((BigInteger) paramObject2).doubleValue());
			localObject = (Number) paramObject1;
			Number localNumber = (Number) paramObject2;
			if (((bool = localObject instanceof Double))
					|| (localNumber instanceof Double))
				if (bool)
					paramObject2 = new Double(localNumber.doubleValue());
				else
					paramObject1 = new Double(
							((Number) localObject).doubleValue());
			else if (((bool = localObject instanceof Float))
					|| (localNumber instanceof Float))
				if (bool)
					paramObject2 = new Float(localNumber.floatValue());
				else
					paramObject1 = new Float(
							((Number) localObject).floatValue());
			else if (((bool = localObject instanceof Long))
					|| (localNumber instanceof Long))
				if (bool)
					paramObject2 = Long.valueOf(localNumber.longValue());
				else
					paramObject1 = Long.valueOf(((Number) localObject)
							.longValue());
		}
		if ((paramObject1 instanceof String)
				|| (paramObject2 instanceof String)) {
			paramObject1 = (paramObject1 == Primitive.NULL) ? paramObject1
					: paramObject1.toString();
			paramObject2 = (paramObject2 == Primitive.NULL) ? paramObject2
					: paramObject2.toString();
		}
		return new Object[] { paramObject1, paramObject2 };
	}

	static Object promoteToInteger(Object paramObject) {
		if (paramObject instanceof Character)
			return Integer.valueOf(((Character) paramObject).charValue());
		if ((paramObject instanceof Byte) || (paramObject instanceof Short))
			return Integer.valueOf(((Number) paramObject).intValue());
		return paramObject;
	}
}