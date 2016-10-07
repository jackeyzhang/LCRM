/**
 * 
 */
package com.jlight.crm.ui.form;

import java.util.ArrayList;
import java.util.List;

import com.jlight.crm.ui.DefaultField;
import com.jlight.crm.ui.uireflect.UIMask;
import com.jlight.crm.ui.uireflect.UIType;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.fields.DateTimeItem;
import com.smartgwt.client.widgets.form.fields.FormItem;


/**
 * form widget factory
 * 
 * @author chenhao
 *
 */
public class FormItemFactory {

  public static List<FormItem> getWidgets( DataSource ds, String op ) {
    List<FormItem> items = new ArrayList<FormItem>();
    for ( DataSourceField field : ds.getFields() ) {
      if ( field.getPrimaryKey() ) {
        continue;
      }
      items.add( getWidget( field, op ) );
    }
    return items;
  }

  public static FormItem getWidget( DataSourceField mfield, String op ) {
    FormItem item = null;
    if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.Integer.toString() ) ) {
      item = new DefaultIntegerItem();
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.Password.toString() ) ) {
      item = new DefaultPasswordItem();
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.TextArea.toString() ) ) {
      item = new DefaultTextAreaItem();
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.String.toString() ) ) {
      item = new DefaultTextItem();
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.Date.toString() ) ) {
      item = new DefaultDateItem();
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.DateTime.toString() ) ) {
      item = new DateTimeItem();
      item.setDateFormatter( DateDisplayFormat.TOSERIALIZEABLEDATE );
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.Mobile.toString() ) ) {
      item = new DefaultTextItem();
    }
    else {
      item = new DefaultTextItem();
    }

    int mask = mfield.getAttributeAsInt( DefaultField.MASK );
    if ( op.equals( FormConst.OP_LOOK ) ) {
      item.disable();
    }
    else if ( op.equals( FormConst.OP_ADD ) ) {
      if ( ( mask & UIMask.showInAdd.getValue() ) == 0 ) {
        item.hide();
      }
      if ( ( mask & UIMask.enInAdd.getValue() ) == 0 ) {
        item.disable();
      }
    }
    else if ( op.equals( FormConst.OP_MF ) ) {
      if ( ( mask & UIMask.showInEdit.getValue() ) == 0 ) {
        item.hide();
      }
      if ( ( mask & UIMask.enInEdit.getValue() ) == 0 ) {
        item.disable();
      }
    }
    else {
      item.enable();
    }
    item.setName( mfield.getName() );
    return item;
  }

  public static List<FormItem> getWidgets( DataSource ds ) {
    List<FormItem> items = new ArrayList<FormItem>();
    for ( DataSourceField field : ds.getFields() ) {
      if ( field.getPrimaryKey() ) {
        continue;
      }
      items.add( getWidget( field ) );
    }
    return items;
  }

  public static FormItem getWidget( DataSourceField mfield ) {
    FormItem item = null;
    if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.Integer.toString() ) ) {
      item = new DefaultIntegerItem();
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.Password.toString() ) ) {
      item = new DefaultPasswordItem();
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.TextArea.toString() ) ) {
      item = new DefaultTextAreaItem();
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.String.toString() ) ) {
      item = new DefaultTextItem();
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.Date.toString() ) ) {
      item = new DefaultDateItem();
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.DateTime.toString() ) ) {
      item = new DefaultDateTimeItem();
      item.setDateFormatter( DateDisplayFormat.TOSERIALIZEABLEDATE );
    }
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.Mobile.toString() ) ) {
      item = new DefaultTextItem();
    }
    else {
      item = new DefaultTextItem();
    }
    item.setName( mfield.getName() );
    return item;
  }

}
