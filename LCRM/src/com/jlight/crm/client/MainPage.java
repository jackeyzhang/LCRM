/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.client;

import java.util.HashMap;
import java.util.Map;

import com.jlight.crm.ui.DefaultPage;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SplitPane;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;


/**
 * @author jzhang12
 *
 */
public class MainPage extends DefaultPage {

  public static final String TOKEN = "main";

  private SplitPane splitPane;

  private ListGrid listGrid;

  private TabSet topTabSet;

  private static ListGridRecord[] records = { createRecord( "产品管理" ), createRecord( "客户管理" ), createRecord( "订单管理" ), createRecord( "用户管理" ) };

  private static Map<String, Tab> pages = new HashMap<String, Tab>();

  public MainPage() {
    this( TOKEN );
    pages.put( "产品管理", new ProductPage( "产品管理" ) );
    pages.put( "客户管理", new CustomerPage( "客户管理" ) );
    pages.put( "订单管理", new OrderPage( "订单管理" ) );
    pages.put( "用户管理", new UserPage( "用户管理" ) );
  }

  public MainPage( String token ) {
    super( token );
  }

  @Override
  protected Canvas getMainContent() {
    splitPane = new SplitPane();
    splitPane.setNavigationTitle( "Categories" );
    splitPane.setShowLeftButton( true );
    splitPane.setShowRightButton( true );
    splitPane.setBorder( "1px solid blue" );

    listGrid = new ListGrid();
    listGrid.setAutoFetchData( true );
    listGrid.setWidth( "*" );
    listGrid.setHeight( "*" );
    listGrid.setOverflow( Overflow.VISIBLE );
    listGrid.setBodyOverflow( Overflow.VISIBLE );
    listGrid.setLeaveScrollbarGap( false );
    listGrid.setData( records );
    ListGridField itemNameField = new ListGridField( "name", "请选择" );
    listGrid.setFields( itemNameField );
    itemNameField.setAlign( Alignment.CENTER );
    itemNameField.addRecordClickHandler( new RecordClickHandler() {

      @Override
      public void onRecordClick( RecordClickEvent event ) {
        Record record = event.getRecord();
        String tabname = record.getAttribute( "name" );
        Tab tab = pages.get( tabname );
        int index = -1;
        for ( int i = 0; i < topTabSet.getTabs().length; i++ ) {
          Tab temp = topTabSet.getTab( i );
          if ( temp.getTitle().equals( tabname ) ) {
            topTabSet.selectTab( i );
            index = i;
          }
        }
        if ( index == -1 ) {
          topTabSet.addTab( tab );
          topTabSet.selectTab( tab );
        }
      }
    } );
    listGrid.fetchData();

    splitPane.setNavigationPane( listGrid );
    splitPane.setNavigationTitle( "菜单" );

    topTabSet = new TabSet();
    topTabSet.setTabBarPosition( Side.TOP );

    splitPane.setDetailPane( topTabSet );

    IButton logout = new IButton( "退出登录" );
    logout.setIcon( "logout.png" );
    logout.addClickHandler( new ClickHandler() {

      @Override
      public void onClick( ClickEvent event ) {
        forward( LoginPage.TOKEN );
        removeCookies( "loginname" );
      }
    } );
    splitPane.setDetailToolButtons( new Label( "欢迎你，" + getCookies( "loginname" ) ), logout );
    return splitPane;
  }

  public static ListGridRecord createRecord( String name ) {
    ListGridRecord record = new ListGridRecord();
    record.setAttribute( "name", name );
    return record;
  }
}
