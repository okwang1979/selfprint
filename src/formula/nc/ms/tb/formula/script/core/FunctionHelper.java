package nc.ms.tb.formula.script.core;


import antlr.Token;
import antlr.TokenStreamException;
//import com.fr.base.BaseUtils;
//import com.fr.base.ColumnRow;
//import com.fr.base.core.BaseCoreUtils;
//import com.fr.base.core.FRCoreContext;
//import com.fr.util.Utils;
import java.io.StringReader;
import java.text.*;
import java.util.Date;

import nc.vo.mdm.pub.NtbLogger;


/**
 * 函数的公用方法
 *
 */
public class FunctionHelper {

	private FunctionHelper() {
	}

//	public static Dat
//	eFormat Date_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
//
//	public static DateFormat Date_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd");
//
//	public static DateFormat Time_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	
	

	/**
	 * @param s ：日期字符串
	 * @return 得到指定的日期
	 */
	public static Date defaultDateParse(String s) {
		Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy/MM/dd").parse(s);
		} catch (ParseException exp) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
			} catch (ParseException e) {
				NtbLogger.error(e);
//				e.printStackTrace();
			}
		}

		return date;
	
	}
	
	public static DateFormat getDataFormat(){
		return new SimpleDateFormat("yyyy/MM/dd");
	}



	/**
	 * @param d 数值
	 * @return 根据转入的double返回具体类
	 */
	public static Object parsePrimitiveDouble(double d) {
		int i = ("" + d).indexOf(".");
		int j;
		try {
			j = Integer.parseInt(("" + d).substring(0, i));
		} catch (Exception exception) {
			return new Double(d);
		}
		if ((double) j == d)
			return Integer.valueOf(j);
		else
			return new Double(d);
	}

}
