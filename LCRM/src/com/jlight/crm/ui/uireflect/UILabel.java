/**
 * 
 */
package com.jlight.crm.ui.uireflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * UI label
 * 
 * @author jacky
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UILabel
{

	/**
	 * 顺序
	 * @return
	 */
	int index() default 1000 ;
	
	/**
	 * 是不是id呢
	 * 
	 * @return
	 */
	boolean isId() default false;
	
	/**
	 * 允许为空吗
	 * 
	 * @return
	 */
	boolean reqiured() default false;
	
	/**
	 * 字段类型
	 * 
	 * String int float password check list date
	 * @return
	 */
	UIType type() default UIType.String;
	
	
	/**
	 * 界面上的title
	 * 
	 * @return
	 */
	String title() default "No Title";
	
	/**
	 * 校验字符串
	 * 
	 * 限制0-15 大于0 小于15 或者 长度在0到15之间
	 * @return
	 */
	String validateStr() default "0-15";
	
	/**
	 * 获取掩码
	 * 
	 * 决定了是否允许修改 是否enable
	 * 
	 * @return
	 */
	UIMask mask() default UIMask.All;
	
}
