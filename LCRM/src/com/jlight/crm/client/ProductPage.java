/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.jlight.crm.client.itf.ProductService;
import com.jlight.crm.client.itf.ProductServiceAsync;
import com.jlight.crm.shared.bean.Product;
import com.jlight.crm.ui.AsyncCallbackWithStatus;
import com.jlight.crm.ui.DefaultListDForm;
import com.jlight.crm.ui.datasource.GwtRpcDataSource;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tab.Tab;


/**
 * @author jzhang12
 *
 */
public class ProductPage extends Tab {

  private static final String QUERY_NAME = "productName";
  
  public ProductPage( String title ) {
    this(title, "product.png");
  }

  public ProductPage( String title, String icon ) {
    this.setTitle( title );
    this.setIcon( icon );
    this.setPane( new ProductList().getDefaultLayout() );
  }

  class ProductList extends DefaultListDForm {

    @Override
    public DataSource getDataSource() {
      return new ProductDataSource().getDataSource( Product.class );
    }

    @Override
    public int getOP() {
      return -1;
    }

    @Override
    public String getQueryName() {
      return "name";
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

}
