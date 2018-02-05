package nc.ms.tb.formula.context;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.ms.tb.formula.excel.core.IWorkBook;
import nc.ms.tb.formula.script.core.parser.Node;

/**
 * 公式计算时的上下文接口
 * 
 */
/**
 * @author wangzhqa <br>
 * @since 2012-2-17 <br>
 * @version 1.0 <br>
 */
public interface IFormulaContext {
	

	/**
	 * 下面的全局静态key是设置setValue与getValue设置的取值的键值
	 * 
	 */

	/**
	 * 任务当前年，当前季度，当前月。
	 * 
	 */
	public static final String KEY_TASK_YEAR = "YEAR";

	public static final String KEY_TASK_QUARTER = "QUARTER";

	public static final String KEY_TASK_MONTH = "MONTH";
	
	/**
	 * 当前任务的业务方案
	 */
	public static final String KEY_TASK_MVTYPE ="TASK_MVTYPE";
	
	
	public static final String  KEY_TASK_ENTITY="TASK_ENTITY";
	
	

	/**
	 * 当前编制主体组织的Key
	 */
	public static final String KEY_CURRENT_ORG = "@CurrentOrgKey";

	/**
	 * 当前编制主体结构的Key
	 */
	public static final String KEY_CURRENT_ENTITY_HIERARCHY = "@CurrentEntityHierarchyKey";

	/**
	 * 根据where部分确认当前member，在一些函数中应用可以通过其他参数在context中找到当前的DimMember。
	 */
	public static final String KEY_CURRENT_MEMBER = "@CurrentMember";

	/**
	 * 根据where部分确认当前member，在一些函数中应用可以通过其他参数在context中找到当前的DimMember。
	 */
	public static final String KEY_CURRENT_TIME = "@CurrentTime";

	/**
	 * 根据where部分确认当前Hierarchy，在一些函数中应用可以通过其他参数在context中找到当前的DimMember。
	 */
	public static final String KEY_CURRENT_HIERARCHY = "@CurrentHierarchyKey";

	/**
	 * 放在map中折算是需要的目标币种，value是目标币种的LevelValue。
	 */
	public static final String KEY_TARTET_CURRENCY = "@TargetCurrency";
	
	
	
	/**
	 * 设置返回当前计算的Node.
	 * @return
	 */
	public Node getCurrentNode();
	
	public void setCurrentNode(Node node);




	public void setValue(String key, Object value);

	public Object getValue(String key);

	// /**
	// * 公式执行是，在select与update 时需要把默认值填写到维度筛选中因此，这里设置个标识，是否需要填充默认值。
	// *
	// * @return
	// */
	// public boolean haveDefaultTuple();
	//
	// /**
	// * @param haveDefaultTuple
	// */
	// public void setDefaultTuple(boolean haveDefaultTuple);

	/**
	 * 因为{[]}这种在select 与update需要转化成value 在where部分需要转化成celllist因此需要知道当前的返回值是什么。
	 * 
	 * @return
	 */
	public boolean isTubleToValue();

	public void setTubleToValue(boolean tupleToValue);

	/**
	 * 数据筛选时，需要确认数据的取数方式。参见 ICutCube类类型。
	 * 
	 * @author wangzhqa
	 * @since 2012-2-13<br>
	 * @param <br>
	 * @param <br>
	 * @return <br>
	 */
	public int getCutCubeType();

	public void setCutCubeType(int type);


	
	
	/**
	 * 当前的上下文WorkBook
	 * @author wangzhqa
	 * @since 2013-1-5
	 * @return
	 * IWorkBook
	 */
	public  IWorkBook  getWorkBook();
	public void setWorkBook(IWorkBook workBook);
	
	
	
	/**
	 * 当前执行sheet名称列表
	 * 
	 * @return
	 * @author: wangzhqa@yonyou.com
	 */
	public List<String> getExecuteSheetNames();
	
	public void setExecuteSheetNames(List<String> sheetNames);
	
	
	

}
