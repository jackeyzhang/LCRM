/**
 * 
 */

package com.jlight.crm.ui;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;


/**
 * 抽象的list表单
 * 
 * 供自动生成界面的form使用 
 * 
 * 优点：统一code
 * 缺点：不够灵活
 * 
 * 提供权限验证 以及默认操作dialog
 * 
 * @author chenhao
 * 
 */
public abstract class DefaultListDForm extends AbstractListGrid {

  private int op = 1;

  private DynamicForm _addNewForm = new DynamicForm();

  public DefaultListDForm() {
    setWidth100();
    setHeight100();
    setAutoFetchData( true );
    setDataSource( getDS() );
    maskAndSetFields( getDS() );
  }

  /**
   * 得到操作权限的和
   * 
   * 0 查看 1增加 2 修改 4 删除 8 搜索 二进制次方表示 -1表示全部支持
   * 
   * @return
   */
  public abstract int getOP();

  /**
   * 获取datasource
   * 
   */
  public abstract DataSource getDS();

  /**
   * 获取默认提供的界面
   * 
   * 上面是操作下面是list
   * 
   * @return
   */
  public VLayout getDefaultLayout() {
    VLayout layout = new VLayout();
    layout.setWidth100();
    layout.setHeight100();

    layout.addMember( getOpertaionLayout() );
    layout.addMember( this );
    return layout;
  }

  public IButton getAddButton() {
    IButton addbutton = new IButton( getAddButtonTitle() );
    addbutton.addClickHandler( new ClickHandler() {

      @Override
      public void onClick( ClickEvent event ) {
        getOpDialog( null ).show();
      }
    } );
    return addbutton;
  }

  public IButton getModifyButton() {
    IButton modifybutton = new IButton( getModifyButtonTitle() );
    modifybutton.addClickHandler( new ClickHandler() {

      @Override
      public void onClick( ClickEvent event ) {
        if ( getSelectedRecords().length == 0 ) {
          SC.say( "请先选中一条要修改的记录" );
          return;
        }
        getOpDialog( getSelectedRecord() ).show();
      }
    } );
    return modifybutton;
  }

  public IButton getRemoveButton() {
    IButton removebutton = new IButton( getRemoveButtonTitle() );
    removebutton.addClickHandler( new ClickHandler() {

      @Override
      public void onClick( ClickEvent event ) {
        if ( getSelectedRecords().length == 0 ) {
          SC.say( "请先选中一条要删除的记录" );
          return;
        }
        getDataSource().removeData( getSelectedRecord() );
      }
    } );

    return removebutton;
  }

  public Layout getSearchPanel() {
    HLayout buttonlayout = new HLayout();
    // final FilterBuilder filterBuilder = new FilterBuilder( );
    // filterBuilder.setDataSource( getDataSource( ) );
    // filterBuilder.setShowAddButton( false );
    // filterBuilder.setShowRemoveButton( false );
    // filterBuilder.setTopOperatorAppearance(TopOperatorAppearance.NONE);
    // filterBuilder.setShowSubClauseButton( false );
    final SearchForm form = new SearchForm();
    form.setWidth( 300 );
    form.setNumCols( 3 );

    final TextItem query = new TextItem();
    query.setName( getQueryName() );
    query.setTitle( "请输入" );

    ButtonItem button = new ButtonItem();
    button.setTitle( getSearchButtonTitle() );
    button.setStartRow( false );
    button.addClickHandler( new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

      @Override
      public void onClick( com.smartgwt.client.widgets.form.fields.events.ClickEvent event ) {
        if ( query.getValueAsString() == null || query.getValueAsString().isEmpty() ) {
          SC.say( "请输入查找的内容" );
          return;
        }
        Criteria criteria = form.getValuesAsCriteria();
        fetchData( criteria );
      }
    } );
    form.setItems( query, button );
    buttonlayout.addMember( form );
    return buttonlayout;
  }

  /**
   * 查询的字段名 在datasource中定义
   * 
   * @return
   */
  public abstract String getQueryName();

  public String getAddButtonTitle() {
    return "新增";
  }

  public String getModifyButtonTitle() {
    return "修改";
  }

  public String getRemoveButtonTitle() {
    return "删除";
  }

  public String getSearchButtonTitle() {
    return "查找";
  }

  /**
   * 获取操作的布局
   * 
   * @return
   */
  public Layout getOpertaionLayout() {
    HLayout buttonlayout = new HLayout();
    buttonlayout.setWidth100();
    buttonlayout.setHeight( 30 );
    buttonlayout.setAlign( Alignment.CENTER );
    buttonlayout.setMembersMargin( 10 );
    buttonlayout.addMember( getSearchPanel() );
    buttonlayout.addMember( getAddButton() );
    buttonlayout.addMember( getModifyButton() );
    buttonlayout.addMember( getRemoveButton() );
    return buttonlayout;
  }

  protected Window getOpDialog( final ListGridRecord record ) {
    String title = "";
    if ( record == null ) {
      title = "新增";
      op = 1;
    }
    else {
      title = "修改";
      op = 2;
    }
    final String ti = title;
    Window opdialog = new DefaultDialog( title ) {

      @Override
      public Canvas getView() {

        VLayout layout = new VLayout();
        layout.setWidth100();
        _addNewForm = new DynamicForm();
        _addNewForm.setColWidths( 200, 200 );
        _addNewForm.setPadding( 2 );
        _addNewForm.setAutoFetchData( false );
        _addNewForm.setDataSource( getDataSource() );
        if ( op == 1 ) {
          _addNewForm.editNewRecord();
        }
        else {
          _addNewForm.editRecord( record );
        }
        IButton okbutton = new IButton( "确认" );
        okbutton.addClickHandler( new ClickHandler() {

          @Override
          public void onClick( ClickEvent event ) {
            _addNewForm.validate();
            _addNewForm.submit( new DSCallback() {

              @Override
              public void execute( DSResponse dsResponse, Object data, DSRequest dsRequest ) {
                if ( dsResponse.getErrors() == null || dsResponse.getErrors().isEmpty() ) {
                  SC.say( ti + "成功！" );
                }
                else {
                  SC.say( ti + "失败！" );
                }
                hide();
              }
            } );

          }
        } );

        layout.addMember( _addNewForm );
        layout.addMember( okbutton );
        return layout;
      }

    };
    opdialog.setWidth( 400 );
    opdialog.setAutoHeight();
    return opdialog;
  }
}
