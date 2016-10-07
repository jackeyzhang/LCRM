/**
 * 
 */
package com.jlight.crm.ui.datasource;

import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;



/**
 * 抽象的数据源
 * 
 * 1、提供了几种常用的校验器
 * 2、尝试提供基于json的数据传输
 * 
 * @author chenhao
 *
 */
public abstract class AbstractDataSource implements IDataSource
{

	/**
	 * 邮箱地址的校验器
	 */
	protected  static final RegExpValidator emailValidator = new RegExpValidator();
    static{
	    emailValidator.setErrorMessage("无效的邮箱地址");
	    emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
    }
	
    /**
     * 获取字段是否匹配的校验器
     * 
     * @param field
     * @param errormessage
     * @return
     */
    protected MatchesFieldValidator getMatchesFieldValidator(String field,String errormessage)
    {
    	 MatchesFieldValidator matchesValidator = new MatchesFieldValidator();
         matchesValidator.setOtherField(field);
         matchesValidator.setErrorMessage(errormessage); 
         return matchesValidator;
    }
    
    /**
     * 获取字段长度是否匹配的校验器
     * @param min
     * @param max
     * @return
     */
    protected LengthRangeValidator getLengthRangeValidator(int min,int max)
    {
    	LengthRangeValidator validator = new LengthRangeValidator();
    	validator.setMin( min );
    	validator.setMax( max );
    	return validator;
    }
    
    /**
	 *  获取整数范围的校验器
	 */
    protected IntegerRangeValidator getIntegerRangeValidator(int min,int max)
    {
    	IntegerRangeValidator validator = new IntegerRangeValidator();
    	validator.setMax( max );
    	validator.setMin( min );
    	return validator;
    }

}
