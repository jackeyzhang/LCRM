/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.jlight.crm.client.itf.ProductService;
import com.jlight.crm.client.itf.ProductServiceAsync;
import com.jlight.crm.shared.bean.Category;
import com.jlight.crm.shared.bean.Product;
import com.jlight.crm.ui.AsyncCallbackWithStatus;
import com.jlight.crm.ui.DefaultDialog;
import com.jlight.crm.ui.DefaultForm;
import com.jlight.crm.ui.DefaultListDForm;
import com.jlight.crm.ui.datasource.GwtRpcDataSource;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellContextClickEvent;
import com.smartgwt.client.widgets.grid.events.CellContextClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SplitPane;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;


/**
 * @author jzhang12
 *
 */
public class ProductPage extends Tab {

  private static final String QUERY_NAME = "name";

  private ProductList list = null;

  private SplitPane splitPane = new SplitPane();

  private static List<Category> allCategories = null;

  public ProductPage( String title ) {
    this( title, "product.png" );
  }

  public ProductPage( String title, String icon ) {
    this.setTitle( title );
    this.setIcon( icon );
    splitPane.setNavigationTitle( "产品分类" );
    splitPane.setShowDetailToolStrip( false );
    splitPane.setShowLeftButton( true );
    splitPane.setShowRightButton( true );
    splitPane.setBorder( "1px solid blue" );
    splitPane.setNavigationPane( getTree() );
    this.setPane( splitPane );
  }

  private Canvas getTree() {
    final TreeGrid treeGrid = new TreeGrid();
    treeGrid.setLoadDataOnDemand( false );
    treeGrid.setWidth("*");
    treeGrid.setHeight( "*" );
    treeGrid.setNodeIcon( "document_plain_new.png" );
    treeGrid.setFolderIcon( "folder_document.png" );
    treeGrid.setShowOpenIcons( false );
    treeGrid.setShowDropIcons( false );
    treeGrid.setClosedIconSuffix( "" );
    treeGrid.setShowHeader( false );
    treeGrid.setAutoFetchData( true );
    treeGrid.addSelectionUpdatedHandler( new SelectionUpdatedHandler() {

      @Override
      public void onSelectionUpdated( SelectionUpdatedEvent event ) {
        if ( treeGrid.getSelectedRecord() == null ) {
          list.refreshData();
          return;
        }
        Criteria criteria = new Criteria();
        criteria.addCriteria( "cid", treeGrid.getSelectedRecord().getAttributeAsString( "id" ) );
        list.filterData( criteria );
      }
    } );
    
    treeGrid.addCellContextClickHandler( new CellContextClickHandler(){
      @Override
      public void onCellContextClick( CellContextClickEvent event ) {
        System.out.println(event.getRecord().getAttributeAsString( "id" ));
      }
    });
    
    treeGrid.setContextMenu( getCategoryMenu() );

    TreeGridField field = new TreeGridField( "name", "Tree from local data" );
    field.setCanSort( false );
    treeGrid.setFields( field );

    ProductServiceAsync service = ProductService.Util.getInstance();

    service.getCategoryList( new AsyncCallbackWithStatus<List<Category>>() {

      @Override
      public void call( List<Category> cateList ) {
        allCategories = cateList;
        CategoryTree tree = new CategoryTree();
        List<CategoryTreeNode> nodes = new ArrayList<CategoryTreeNode>();
        for ( Category category : allCategories ) {
          CategoryTreeNode node = new CategoryTreeNode( category.getId(), category.getPid(), category.getName() );
          nodes.add( node );
        }
        tree.setData( nodes.toArray( new CategoryTreeNode[nodes.size()] ) );
        treeGrid.setData( tree );
        treeGrid.getData().openAll();
        treeGrid.redraw();
        list = new ProductList();
        splitPane.setDetailPane( list.getDefaultLayout() );
      }
    } );

    return treeGrid;
  }
  
  private Menu getCategoryMenu(){
    Menu menu = new Menu();
    menu.addItem( new MenuItem("增加分类") );
    menu.addItem( new MenuItemSeparator()  );
    menu.addItem( new MenuItem("修改") );
    menu.addItem( new MenuItem("删除") );
    return menu;
  }

  class ProductList extends DefaultListDForm {

    private DefaultForm form = new DefaultForm() {

      @Override
      public DataSource getDS() {
        return ProductList.this.getDS();
      }

      @Override
      public void afterGetAddForm( List<FormItem> items ) {
        for ( FormItem item : items ) {
          initCidField( item );
        }
      }

      @Override
      public void afterGetModifyForm( List<FormItem> items ) {
        for ( FormItem item : items ) {
          initCidField( item );
        }
      }

      private void initCidField( FormItem item ) {
        if ( item.getName().equalsIgnoreCase( "cid" ) ) {
          LinkedHashMap<Integer, String> valueMap = new LinkedHashMap<Integer, String>();
          for ( Category ca : allCategories ) {
            valueMap.put( ca.getId(), ca.getName() );
          }
          item.setValueMap( valueMap );
        }
      }

    };

    @Override
    public DataSource getDS() {
      DataSource ds = new ProductDataSource().getDataSource( Product.class );
      DataSourceField cid = ds.getField( "cid" );
      if ( cid != null ) {
        LinkedHashMap<Integer, String> valueMap = new LinkedHashMap<Integer, String>();
        for ( Category ca : allCategories ) {
          valueMap.put( ca.getId(), ca.getName() );
        }
        cid.setValueMap( valueMap );
      }
      return ds;
    }

    @Override
    public int getOP() {
      return -1;
    }

    @Override
    public String getQueryName() {
      return "name";
    }

    @Override
    public IButton getAddButton() {
      IButton add = new IButton( "增加产品" );
      add.addClickHandler( new ClickHandler() {

        @Override
        public void onClick( ClickEvent event ) {
          DefaultDialog dialog = new DefaultDialog( "增加产品" ) {

            @Override
            public Canvas getView() {
              final DynamicForm newform = form.getAddForm();
              VLayout v = new VLayout();
              v.addMember( newform );
              IButton submit = new IButton( "保存" );
              submit.addClickHandler( new ClickHandler() {

                @Override
                public void onClick( ClickEvent event ) {
                  if ( newform.validate() ) {
                    newform.submit();
                    hide();
                  }
                }
              } );
              IButton cancel = new IButton( "取消" );
              cancel.addClickHandler( new ClickHandler() {

                @Override
                public void onClick( ClickEvent event ) {
                  hide();
                }
              } );
              HLayout h = new HLayout();
              h.setPadding( 15 );
              h.setWidth( 400 );
              h.setAlign( Alignment.RIGHT );
              h.addMember( submit );
              h.addMember( cancel );
              v.addMember( h );
              return v;
            }
          };
          dialog.show();
        }
      } );
      return add;
    }

    @Override
    public IButton getModifyButton() {
      IButton add = new IButton( "修改产品" );
      add.addClickHandler( new ClickHandler() {

        @Override
        public void onClick( ClickEvent event ) {
          if ( list.getSelectedRecord() == null ) {
            SC.say( "请选择一条修改的产品" );
            return;
          }
          DefaultDialog dialog = new DefaultDialog( "修改产品" ) {

            @Override
            public Canvas getView() {
              final DynamicForm newform = form.getModifyForm();
              VLayout v = new VLayout();
              v.setWidth( 500 );
              v.addMember( newform );
              newform.editSelectedData( list );
              IButton submit = new IButton( "保存" );
              submit.addClickHandler( new ClickHandler() {

                @Override
                public void onClick( ClickEvent event ) {
                  if ( newform.validate() ) {
                    newform.submit();
                    hide();
                  }
                }
              } );
              IButton cancel = new IButton( "取消" );
              cancel.addClickHandler( new ClickHandler() {

                @Override
                public void onClick( ClickEvent event ) {
                  hide();
                }
              } );
              HLayout h = new HLayout();
              h.setPadding( 15 );
              h.setWidth( 400 );
              h.setAlign( Alignment.RIGHT );
              h.addMember( submit );
              h.addMember( cancel );
              v.addMember( h );
              return v;
            }
          };
          dialog.show();
        }
      } );
      return add;
    }

  }

  class ProductDataSource extends GwtRpcDataSource {

    private ProductServiceAsync service = ProductService.Util.getInstance();

    @Override
    protected void executeFetch( final String requestId, final DSRequest request, final DSResponse response ) {
      final int startIndex = ( request.getStartRow() < 0 ) ? 0 : request.getStartRow();
      final int endIndex = ( request.getEndRow() == null ) ? -1 : request.getEndRow();
      final String name = request.getCriteria().getAttributeAsString( QUERY_NAME );
      service.listProduct( startIndex, endIndex, new AsyncCallbackWithStatus<List<Product>>() {

        @Override
        public void call( List<Product> result ) {
          int size = result.size();
          if ( endIndex >= 0 ) {
            if ( endIndex < startIndex ) {
              size = 0;
            }
            else {
              size = endIndex - startIndex + 1;
            }
          }
          final List<ListGridRecord> list = new ArrayList<ListGridRecord>();
          if ( size > 0 ) {

            for ( int i = 0; i < result.size(); i++ ) {
              if ( i >= startIndex && i <= endIndex ) {
                ListGridRecord record = new ListGridRecord();
                if ( null != name && !name.isEmpty() ) {
                  if ( result.get( i ).getName().equalsIgnoreCase( name ) ) {
                    getValues( result.get( i ), record );
                    list.add( record );
                  }
                }
                else {
                  getValues( result.get( i ), record );
                  list.add( record );
                }

              }
            }
          }
          response.setData( list.toArray( new ListGridRecord[list.size()] ) );
          response.setTotalRows( result.size() );
          getDataSource( Product.class ).processResponse( requestId, response );
        }
      } );

    }

    @Override
    protected void executeAdd( final String requestId, final DSRequest request, final DSResponse response ) {
      JavaScriptObject data = request.getData();
      final ListGridRecord rec = new ListGridRecord( data );
      Product Product = new Product();
      setValues( rec, Product );
      service.addProduct( Product, new AsyncCallbackWithStatus<Void>() {

        @Override
        public void call( Void result ) {
          ListGridRecord[] list = new ListGridRecord[1];
          list[0] = rec;
          response.setData( list );
          getDataSource( Product.class ).processResponse( requestId, response );
        }
      } );
    }

    @Override
    protected void executeUpdate( final String requestId, final DSRequest request, final DSResponse response ) {
      JavaScriptObject data = request.getData();
      final ListGridRecord rec = new ListGridRecord( data );
      Product Product = new Product();
      setValues( rec, Product );
      service.updateProduct( Product, new AsyncCallbackWithStatus<Product>() {

        @Override
        public void call( Product result ) {
          ListGridRecord[] list = new ListGridRecord[1];
          list[0] = rec;
          response.setData( list );
          getDataSource( Product.class ).processResponse( requestId, response );
        }
      } );
    }

    @Override
    protected void executeRemove( final String requestId, final DSRequest request, final DSResponse response ) {
      JavaScriptObject data = request.getData();
      final ListGridRecord rec = new ListGridRecord( data );
      Integer id = rec.getAttributeAsInt( "id" );
      service.removeProduct( id, new AsyncCallbackWithStatus<Boolean>() {

        @Override
        public void call( Boolean result ) {
          ListGridRecord[] list = new ListGridRecord[1];
          list[0] = rec;
          response.setData( list );
          getDataSource( Product.class ).processResponse( requestId, response );
        }
      } );
    }

  }

  class CategoryTree extends Tree {

    public CategoryTree() {
      super();
      setModelType( TreeModelType.PARENT );
      setNameProperty( "name" );
      setIdField( "id" );
      setParentIdField( "parent" );
      setShowRoot( true );
    }

  }

  public static class CategoryTreeNode extends TreeNode {

    public CategoryTreeNode( Integer id, Integer cid, String name ) {
      setId( id );
      setParent( cid );
      setName( name );
    }

    public void setId( Integer value ) {
      setAttribute( "id", value );
    }

    public void setParent( Integer value ) {
      setAttribute( "parent", value );
    }

    public void setName( String name ) {
      setAttribute( "name", name );
    }
  }

}
