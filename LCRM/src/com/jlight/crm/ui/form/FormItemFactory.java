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

  public static List<FormItem> getWidgets( DataSource ds, FormType type ) {
    List<FormItem> items = new ArrayList<FormItem>();
    for ( DataSourceField field : ds.getFields() ) {
      if ( field.getPrimaryKey() ) {
        continue;
      }
      items.add( getWidget( field, type ) );
    }
    return items;
  }

  public static FormItem getWidget( DataSourceField mfield, FormType type ) {
    if ( mfield.getAttribute( DefaultField.STYPE ) == null ) {
      return new DefaultTextItem( mfield.getName(), mfield.getTitle() );
    }
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
    else if ( mfield.getAttribute( DefaultField.STYPE ).equals( UIType.List.toString() ) ) {
      item = new DefaultComboBoxItem();
    }
    else {
      item = new DefaultTextItem();
    }

    int mask = mfield.getAttributeAsInt( DefaultField.MASK );

    if ( !UIMask.isEnable( mask, type ) ) {
      item.disable();
    }

    if ( !UIMask.isVisiable( mask, type ) ) {
      item.hide();
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
