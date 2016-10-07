/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.client;

import com.google.gwt.core.client.GWT;
import com.jlight.crm.client.itf.GreetingService;
import com.jlight.crm.client.itf.GreetingServiceAsync;
import com.jlight.crm.ui.AsyncCallbackWithStatus;
import com.jlight.crm.ui.Carousel;
import com.jlight.crm.ui.DefaultPage;
import com.jlight.crm.ui.form.DefaultButtonItem;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;


/**
 * @author jzhang12
 *
 */
public class LoginPage extends DefaultPage {

  public static final String TOKEN = "login";

  private static GreetingServiceAsync greetingService = GWT.create( GreetingService.class );;

  public LoginPage() {
    this( TOKEN );
  }

  public LoginPage( String token ) {
    super( token );
  }

  public Canvas getMainContent() {
    HLayout H = new HLayout();
    H.setAlign( Alignment.CENTER );
    H.addMember( getCarousel() );
    H.addMember( getLoginForm() );
    return H;
  }

  private Canvas getCarousel() {
    String[] path = { "sliderimage1.jpg", "sliderimage2.jpg", "sliderimage3.jpg" };
    Carousel c = new Carousel( 500, 300, path );
    return c;
  }

  private Canvas getLoginForm() {
    VLayout loginformPanel = new VLayout( 30 );
    loginformPanel.setAlign( VerticalAlignment.CENTER );
    loginformPanel.setStyleName( "loginformPanel" );
    loginformPanel.setAutoHeight();
    loginformPanel.setAutoWidth();

    final DynamicForm form = new DynamicForm();
    form.setWidth( 300 );
    form.setHeight( 300 );
    form.setNumCols( 2 );
    form.setColWidths( 150, "*" );
    form.setBorder( "1px solid blue" );
    form.setPadding( 5 );
    form.setCanDragResize( true );

    HeaderItem header = new HeaderItem();
    header.setCellStyle( "cellPadding" );
    header.setTextBoxStyle( "logintitle" );
    header.setDefaultValue( "欢迎您的使用，请登录系统" );

    final TextItem userItem = new TextItem();
    userItem.setTitle( "用户名" );
    userItem.setTitleStyle( "logintitle" );
    userItem.setRequired( true );
    userItem.setRequiredMessage( "用户名不能为空" );
    userItem.setDefaultValue( "jacky" );
    userItem.setWidth( "*" );

    final PasswordItem passwordItem = new PasswordItem();
    passwordItem.setRequired( true );
    passwordItem.setRequiredMessage( "密码不能为空" );
    passwordItem.setTitle( "密码" );
    passwordItem.setTitleStyle( "logintitle" );
    passwordItem.setDefaultValue( "123" );
    passwordItem.setWidth( "*" );

    CheckboxItem licenseAccept = new CheckboxItem();
    licenseAccept.setTitle( "记住我" );
    licenseAccept.setValue( false );
    licenseAccept.addChangeHandler( new ChangeHandler() {

      public void onChange( ChangeEvent event ) {

      }
    } );

    DefaultButtonItem submit = new DefaultButtonItem();
    submit.setTitle( "登录" );
    submit.setWidth( "*" );
    submit.addClickHandler( new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
      @Override
      public void onClick( ClickEvent event ) {
        if ( form.validate() ) {
          greetingService.login( userItem.getValueAsString(), passwordItem.getValueAsString(), new AsyncCallbackWithStatus<Boolean>() {

            @Override
            public void call( Boolean result ) {
              if ( result ) {
                setCookies( "loginname", userItem.getValueAsString() );
                new MainPage().display();
              }
              else {
                SC.say( "用户名或密码错误" );
              }
            }
          } );
        }
      }
    } );

    form.setFields( header, userItem, passwordItem, licenseAccept, submit );
    loginformPanel.addMember( form );
    return loginformPanel;
  }
}
