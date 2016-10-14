/**
 * 
 */

package com.jlight.crm.ui.uireflect;

import com.jlight.crm.ui.form.FormType;


/**
 * 控制可见性 可编辑性的掩码
 * 
 *          list   show    add     edit
 * display   1      4       16      64
 * enable    2      8       32      128
 * 
 * all = sum all = 255
 * 
 * @author jacky
 * 
 */
public enum UIMask {

  list_display(1 << 0), list_enable(1 << 1), list_only(list_display.getValue() + list_enable.getValue()),

  show_display(1 << 2), show_enable(1 << 3), show_only(show_display.getValue() + show_enable.getValue()),

  add_display(1 << 4), add_enable(1 << 5), add_only(add_display.getValue() + add_enable.getValue()),

  edit_display(1 << 6), edit_enable(1 << 7), edit_only(edit_display.getValue() + edit_enable.getValue()), all(255), never(0);

  private int value;

  private UIMask( int value ) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static boolean isVisiable( int mask, FormType type ) {
    if ( type == FormType.LIST ) {
      int result = mask & list_display.getValue();
      if ( result > 0 )
        return true;
    }
    if ( type == FormType.SHOWFORM ) {
      int result = mask & show_display.getValue();
      if ( result > 0 )
        return true;
    }
    if ( type == FormType.ADDFORM ) {
      int result = mask & add_display.getValue();
      if ( result > 0 )
        return true;
    }
    if ( type == FormType.EDITFORM ) {
      int result = mask & edit_display.getValue();
      if ( result > 0 )
        return true;
    }
    return false;
  }

  public static boolean isEnable( int mask, FormType type ) {
    if ( type == FormType.LIST ) {
      int result = mask & list_enable.getValue();
      if ( result > 0 )
        return true;
    }
    if ( type == FormType.SHOWFORM ) {
      int result = mask & show_enable.getValue();
      if ( result > 0 )
        return true;
    }
    if ( type == FormType.ADDFORM ) {
      int result = mask & add_enable.getValue();
      if ( result > 0 )
        return true;
    }
    if ( type == FormType.EDITFORM ) {
      int result = mask & edit_enable.getValue();
      if ( result > 0 )
        return true;
    }
    return false;
  }

}
