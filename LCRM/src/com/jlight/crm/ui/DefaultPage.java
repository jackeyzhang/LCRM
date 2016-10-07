/**
 * 
 */
package com.jlight.crm.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;


/**
 * Default Page
 * 
 * @author jzhang12
 *  
 */
public abstract class DefaultPage extends VLayout {

  private static Map<String, DefaultPage> tokenCache = new HashMap<String, DefaultPage>();

  private static DefaultPage lastPage, currentPage;

  static {
    History.addValueChangeHandler( new ValueChangeHandler<String>() {

      public void onValueChange( ValueChangeEvent<String> event ) {
        DefaultPage page = tokenCache.get( event.getValue() );
        if ( currentPage == null ) {
          currentPage = page;
        }
        else {
          lastPage = currentPage;
          currentPage = page;
        }
        if ( lastPage != null ) {
          lastPage.clear();
          currentPage.draw();
        }
        else {
          currentPage.draw();
        }
      }
    } );
  }

  private String tokenhistory = "";

  public DefaultPage( final String token ) {
    this.tokenhistory = token;
    this.setWidth100();
    this.setHeight100();
    //TOP
    if ( displayDefaultTopPanel() )
      this.addMember( getDefaultTopPanel() );
    //Main
    this.addMember( getMainContent() );
    //Footer
    if ( displayDefaultFooter() )
      this.addMember( getDefaultFooter() );

  }

  /**
   * @return the token
   */
  public String getToken() {
    return tokenhistory;
  }

  public Layout getPage() {
    return this;
  }

  public void clear() {
    if ( this.isCreated() ) {
      super.clear();
    }
  }

  protected Layout getDefaultTopPanel() {
    HLayout toppanel = new HLayout();
    toppanel.setWidth100();
    toppanel.setHeight( 60 );
    toppanel.setStyleName( "defaulttoppanel" );
    toppanel.setMembersMargin( 200 );

    Label title = new Label( "订单管理专家" );
    title.setStyleName( "white" );
    title.setWidth( 200 );
    toppanel.addMember( title );

    return toppanel;

  }

  protected Layout getDefaultFooter() {
    HLayout versionpanel = new HLayout();
    versionpanel.setWidth100();
    versionpanel.setHeight( 30 );
    versionpanel.setAlign( Alignment.CENTER );
    versionpanel.setStyleName( "footer" );
    Label version = new Label( "Copyright ©2016 Jacky" );
    version.setWidth( 200 );
    versionpanel.addMember( version );
    return versionpanel;
  }

  public Layout getDefaultVersionPanel() {
    return new Layout();
  }

  public static double getWidthNum( double percentOfScreen ) {
    return Window.getClientWidth() * percentOfScreen;
  }

  public static double getHeightNum( double percentOfScreen ) {
    return Window.getClientHeight() * percentOfScreen;
  }

  public static int getWidthInt( double percentOfScreen ) {
    return (int) ( Window.getClientWidth() * percentOfScreen );
  }

  public static int getHeightInt( double percentOfScreen ) {
    return (int) ( Window.getClientHeight() * percentOfScreen );
  }

  public static String getWidth( double percentOfScreen ) {
    return ( Window.getClientWidth() * percentOfScreen ) + "px";
  }

  public static String getHeight( double percentOfScreen ) {
    return ( Window.getClientHeight() * percentOfScreen ) + "px";
  }

  public static void clearLayout( Layout layout ) {
    for ( Canvas mem : layout.getMembers() ) {
      layout.removeMember( mem );
    }
  }

  /**
   * forward 
   * 
   * @param token
   * @return
   */
  public Layout forward( String token ) {
    if ( getCookies( "loginname" ) == null )
      return tokenCache.get( "login" );
    History.newItem( token );
    return tokenCache.get( token );
  }

  public void display() {
    tokenCache.put( tokenhistory, this );
    History.newItem( tokenhistory );
  }

  protected boolean displayDefaultTopPanel() {
    return true;
  }

  protected boolean displayDefaultFooter() {
    return true;
  }

  protected Canvas getMainContent() {
    return new Canvas();
  }

  protected void setCookies( String name, String value ) {
    Date now = new Date(System.currentTimeMillis() + 30*1000*60);
    Cookies.setCookie( name, value, now );
  }

  protected void removeCookies( String name ) {
    Cookies.removeCookie( name );
  }

  protected String getCookies( String name ) {
    return Cookies.getCookie( name );
  }
}
