/**
 * 
 */
package com.jlight.crm.ui.form;

import com.smartgwt.client.types.FormErrorOrientation;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;


/**
 * default RadioGroupItem for form
 * 
 * @author chenhao
 *
 */
public class DefaultRadioGroupItem extends RadioGroupItem {

  public DefaultRadioGroupItem() {
    super();
    init();
  }

  /**
   * @param name
   * @param title
   */
  public DefaultRadioGroupItem( String name, String title ) {
    super( name, title );
    init();
  }

  /**
   * @param name
   */
  public DefaultRadioGroupItem( String name ) {
    super( name );
    init();
  }

  private void init() {
    setTitleStyle( "form_texttitle" );
    setTextBoxStyle( "form_radio" );
    setShowFocused( false );
    setShowErrorText( true );
    setShowErrorStyle( false );
    setErrorOrientation( FormErrorOrientation.RIGHT );
    setRequiredMessage( "不能为空" );
  }

}
