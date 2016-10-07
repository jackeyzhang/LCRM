/**
 * 
 */
package com.jlight.crm.ui.datasource;

import com.smartgwt.client.data.DataSource;


/**
 * 支持根据Class反射来配置field的datasource
 * 
 * @author chenhao
 *
 */
public interface IDataSource
{
	
	<T> DataSource getDataSource(Class<T> cls);
}
