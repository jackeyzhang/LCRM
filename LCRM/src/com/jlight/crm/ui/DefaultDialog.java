/**
 * 
 */

package com.jlight.crm.ui;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 抽象的dialog实现
 * 
 * @author chenhao
 * 
 */
public abstract class DefaultDialog extends Window
{

	private Canvas content;
	
	public DefaultDialog( String title )
	{
		this( title, true, true, true );
	}

	public DefaultDialog( String title, boolean isModal, boolean closeable,
			boolean isMinable )
	{
		this.setAutoSize( true );
		this.setTitle( title );
		this.setIsModal( isModal );
		this.setShowModalMask(true); 
		this.setShowCloseButton( closeable );
		this.setShowMinimizeButton( isMinable );
		this.content = getView( );
		this.addItem( content );
		this.content.setStyleName( "abstractdialog-content" );
		if ( getTitleView( ) != null )
		{
			this.addMember( getTitleView( ) );
		}

	}

	
	/**
	 * 获取到对话框的页面内容
	 * 
	 * 子类实现
	 * 
	 * @return
	 */
	public abstract Canvas getView( );
	
	public VLayout getDefaultVLayout(){
		VLayout wholepanel = new VLayout( );
		wholepanel.setStyleName( "abstractdialog-content" );
		return wholepanel;
	}

	public Canvas getTitleView( )
	{
		return null;
	}

	public void show( )
	{
		if(this.isCreated( ) == false)
		{
			this.draw( );
			this.centerInPage( );
		}else{
			this.setVisible( true );
			this.centerInPage( );
		}
	}
}
