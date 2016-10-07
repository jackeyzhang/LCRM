/**
 * 
 */
package com.jlight.crm.ui;

import java.util.List;

import com.jlight.crm.ui.form.FormConst;
import com.jlight.crm.ui.form.FormItemFactory;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;


/**
 * 抽象的form
 * 
 * @author chenhao
 *
 */
public abstract class DefaultForm extends DynamicForm {

  public DefaultForm() {
    super();
  }

  public DynamicForm getAddForm() {
    List<FormItem> items = FormItemFactory.getWidgets( getDS(), FormConst.OP_ADD );
    preGetAddForm( items );
    setDataSource( getDS() );
    setItems( items.toArray( new FormItem[items.size()] )  );
    afterGetAddForm( items );
    return this;
  }

  public DynamicForm getModifyForm() {
    List<FormItem> items = FormItemFactory.getWidgets( getDS(), FormConst.OP_MF );
    preGetModifyForm( items );
    setDataSource( getDS() );
    setItems( items.toArray( new FormItem[items.size()] )  );
    afterGetModifyForm( items );
    return this;
  }

  public DynamicForm getLookForm() {
    List<FormItem> items = FormItemFactory.getWidgets( getDS(), FormConst.OP_LOOK );
    preGetLookForm( items );
    setDataSource( getDS() );
    setItems( items.toArray( new FormItem[items.size()] )  );
    afterGetLookForm( items );
    return this;
  }

  public abstract DataSource getDS();

  public void preGetAddForm( List<FormItem> items ) {
    this.setWidth( 400 );
    this.setNumCols( 3 );
    this.setColWidths( 150, 250 );
    for ( FormItem item : items ) {
      item.setWidth( 240 );
    }
  }

  public void preGetModifyForm( List<FormItem> items ) {}

  public void preGetLookForm( List<FormItem> items ) {}

  public void afterGetAddForm( List<FormItem> items ) {}

  public void afterGetModifyForm( List<FormItem> items ) {}

  public void afterGetLookForm( List<FormItem> items ) {}
}
