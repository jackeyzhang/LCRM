/**
 * 
 */
package com.jlight.crm.ui.form;

import com.smartgwt.client.types.FormErrorOrientation;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.BlurEvent;
import com.smartgwt.client.widgets.form.fields.events.BlurHandler;
import com.smartgwt.client.widgets.form.fields.events.FocusEvent;
import com.smartgwt.client.widgets.form.fields.events.FocusHandler;


/**
 * default text item for form
 * 
 * @author chenhao
 *
 */
public class DefaultTextItem extends TextItem {

  public DefaultTextItem() {
    super();
    init();
  }

  /**
   * @param name
   * @param title
   */
  public DefaultTextItem( String name, String title ) {
    super( name, title );
    init();
  }

  /**
   * @param name
   */
  public DefaultTextItem( String name ) {
    super( name );
    init();
  }

  private void init() {
    setTitleStyle( "form_texttitle" );
    setTextBoxStyle( "form_textbox" );
    setShowFocused( false );
    setShowErrorText( true );
    setShowErrorStyle( false );
    setErrorOrientation( FormErrorOrientation.RIGHT );
    setRequiredMessage( "不能为空" );

    addFocusHandler( new FocusHandler() {

      @Override
      public void onFocus( FocusEvent event ) {
        setTextBoxStyle( "form_textbox_focus" );
      }
    } );

    addBlurHandler( new BlurHandler() {

      @Override
      public void onBlur( BlurEvent event ) {
        setTextBoxStyle( "form_textbox" );
      }
    } );

  }
}
