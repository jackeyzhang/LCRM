/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.jlight.crm.client.itf.GreetingService;
import com.jlight.crm.client.itf.GreetingServiceAsync;
import com.jlight.crm.shared.bean.User;
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
public class UserPage extends Tab {

  private static final String QUERY_NAME = "nickName";

  public UserPage( String title ) {
    this(title, "teachers512.png");
  }
  
  public UserPage( String title, String icon ) {
    this.setTitle( title );
    this.setIcon( icon );
    this.setPane( new UserList().getDefaultLayout() );
  }

  class UserList extends DefaultListDForm {

    @Override
    public DataSource getDS() {
      return new UserDataSource().getDataSource( User.class );
    }

    @Override
    public int getOP() {
      return -1;
    }

    @Override
    public String getQueryName() {
      return "nickName";
    }
  }

  class UserDataSource extends GwtRpcDataSource {

    private GreetingServiceAsync service = GreetingService.Util.getInstance();

    @Override
    protected void executeFetch( final String requestId, final DSRequest request, final DSResponse response ) {
      final int startIndex = ( request.getStartRow() < 0 ) ? 0 : request.getStartRow();
      final int endIndex = ( request.getEndRow() == null ) ? -1 : request.getEndRow();
      final String name = request.getCriteria().getAttributeAsString( QUERY_NAME );
      service.listUser( startIndex, endIndex, new AsyncCallbackWithStatus<List<User>>() {

        @Override
        public void call( List<User> result ) {
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
                  if ( result.get( i ).getUserName().equalsIgnoreCase( name ) ) {
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
          getDataSource( User.class ).processResponse( requestId, response );
        }
      } );

    }

    @Override
    protected void executeAdd( final String requestId, final DSRequest request, final DSResponse response ) {
      JavaScriptObject data = request.getData();
      final ListGridRecord rec = new ListGridRecord( data );
      User user = new User();
      setValues( rec, user );
      service.addUser( user, new AsyncCallbackWithStatus<Void>() {

        @Override
        public void call( Void result ) {
          ListGridRecord[] list = new ListGridRecord[1];
          list[0] = rec;
          response.setData( list );
          getDataSource( User.class ).processResponse( requestId, response );
        }
      } );
    }

    @Override
    protected void executeUpdate( final String requestId, final DSRequest request, final DSResponse response ) {
      JavaScriptObject data = request.getData();
      final ListGridRecord rec = new ListGridRecord( data );
      User user = new User();
      setValues( rec, user );
      service.updateUser( user, new AsyncCallbackWithStatus<User>() {

        @Override
        public void call( User result ) {
          ListGridRecord[] list = new ListGridRecord[1];
          list[0] = rec;
          response.setData( list );
          getDataSource( User.class ).processResponse( requestId, response );
        }
      } );
    }

    @Override
    protected void executeRemove( final String requestId, final DSRequest request, final DSResponse response ) {
      JavaScriptObject data = request.getData();
      final ListGridRecord rec = new ListGridRecord( data );
      Integer id = rec.getAttributeAsInt( "id" );
      service.removeUser( id, new AsyncCallbackWithStatus<Boolean>() {

        @Override
        public void call( Boolean result ) {
          ListGridRecord[] list = new ListGridRecord[1];
          list[0] = rec;
          response.setData( list );
          getDataSource( User.class ).processResponse( requestId, response );
        }
      } );
    }

  }
}
