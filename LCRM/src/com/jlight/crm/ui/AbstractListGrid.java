/**
 * 
 */
package com.jlight.crm.ui;

import com.jlight.crm.ui.form.FormType;
import com.jlight.crm.ui.uireflect.UIMask;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.widgets.grid.ListGrid;


/**
 * 抽象的list 
 * 
 * feature1:实现了mask对list的遮蔽功能
 *
 * @author chenhao
 *
 */
public class AbstractListGrid extends ListGrid
{
	
	public AbstractListGrid()
	{
		setSortField( "id" );
	}
	
	protected void maskAndSetFields(DataSource ds)
	{
		DataSourceField[] fs = ds.getFields( );
		for( DataSourceField f : fs)
		{
			if( null == f )
			{
				continue;
			}
			int mask = f.getAttributeAsInt( DefaultField.MASK );
			if( !UIMask.isVisiable( mask, FormType.LIST )){
			  f.setHidden( true );
			}
		}
		this.setDataSource( ds );
	}

}
