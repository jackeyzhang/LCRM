/**
 * 
 */
package com.jlight.crm.ui.form;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.FormErrorOrientation;
import com.smartgwt.client.widgets.form.fields.ButtonItem;


/**
 * default CheckboxItem for form
 * 
 * @author chenhao
 *
 */
public class DefaultButtonItem extends ButtonItem {

  public DefaultButtonItem() {
    super();
    init();
  }

  /**
   * @param name
   * @param title
   */
  public DefaultButtonItem( String name, String title ) {
    super( name, title );
    init();
  }

  /**
   * @param name
   */
  public DefaultButtonItem( String name ) {
    super( name );
    init();
  }

  private void init() {
    this.setAlign( Alignment.CENTER );
    setErrorOrientation( FormErrorOrientation.RIGHT );
    setHoverStyle("buttonItemHover");
  }
}
