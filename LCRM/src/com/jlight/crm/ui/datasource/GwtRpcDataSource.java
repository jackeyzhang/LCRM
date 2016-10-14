/**
 * 
 */

package com.jlight.crm.ui.datasource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.gwtent.reflection.client.ClassType;
import com.gwtent.reflection.client.Field;
import com.gwtent.reflection.client.TypeOracle;
import com.jlight.crm.ui.DefaultField;
import com.jlight.crm.ui.uireflect.UILabel;
import com.jlight.crm.ui.uireflect.UIType;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.grid.ListGridRecord;


/**
 * GWT异步返回datasource
 * 
 * @author chenhao
 * 
 */
public abstract class GwtRpcDataSource extends AbstractDataSource {

  @SuppressWarnings("rawtypes")
  private static Map<Class, DataSource> cache = new HashMap<Class, DataSource>();

  private DataSource datasource;

  public GwtRpcDataSource() {
    datasource = new DataSource() {

      @Override
      protected Object transformRequest( DSRequest request ) {
        String requestId = request.getRequestId();
        DSResponse response = new DSResponse();
        response.setAttribute( "clientContext", request.getAttributeAsObject( "clientContext" ) );
        // Asume success
        response.setStatus( 0 );
        switch ( request.getOperationType() ) {
          case FETCH :
            executeFetch( requestId, request, response );
            break;
          case ADD :
            executeAdd( requestId, request, response );
            break;
          case UPDATE :
            executeUpdate( requestId, request, response );
            break;
          case REMOVE :
            executeRemove( requestId, request, response );
            break;
          default :
            break;
        }
        return request.getData();
      }

    };
    datasource.setDataProtocol( DSProtocol.CLIENTCUSTOM );
    datasource.setDataFormat( DSDataFormat.CUSTOM );
    datasource.setClientOnly( false );
  }

  protected abstract void executeFetch( String requestId, DSRequest request, DSResponse response );

  protected abstract void executeAdd( String requestId, DSRequest request, DSResponse response );

  protected abstract void executeUpdate( String requestId, DSRequest request, DSResponse response );

  protected abstract void executeRemove( String requestId, DSRequest request, DSResponse response );

  @Override
  public <T> DataSource getDataSource( Class<T> cls ) {
    if ( cache.containsKey( cls ) ) {
      return cache.get( cls );
    }
    ClassType<T> classType = TypeOracle.Instance.getClassType( cls );
    Field[] fs = classType.getFields();

    List<DefaultField> fields = new ArrayList<DefaultField>();
    for ( Field f : fs ) {
      UILabel field = f.getAnnotation( UILabel.class );
      if ( field == null ) {
        continue;
      }
      DefaultField newfield = new DefaultField( f.getName(), field.type().toString(), field.title(), field.index() );

      if ( field.type().equals( UIType.Email ) ) {
        newfield.getField().setValidators( emailValidator );
      }

      if ( field.isId() ) {
        newfield.getField().setPrimaryKey( true );
        newfield.getField().setHidden( true );
      }

      if ( field.reqiured() ) {
        newfield.getField().setRequired( true );
      }

      newfield.setMask( field.mask().getValue() );

      fields.add( newfield );
    }

    Collections.sort( fields );

    for ( DefaultField f : fields ) {
      datasource.addField( f.getField() );
    }

    cache.put( cls, datasource );
    return datasource;
  }

  public <T> void getValues( T from, ListGridRecord to ) {
    @SuppressWarnings("unchecked")
    ClassType<T> classType = TypeOracle.Instance.getClassType( from.getClass().getName() );
    Field[] fs = classType.getFields();
    for ( Field f : fs ) {
      UILabel field = f.getAnnotation( UILabel.class );
      if ( field == null ) {
        continue;
      }
      to.setAttribute( f.getName(), f.getFieldValue( from ) );
    }
  }

  public <T> void setValues( ListGridRecord from, T to ) {
    @SuppressWarnings("unchecked")
    ClassType<T> classType = TypeOracle.Instance.getClassType( to.getClass().getName() );
    Field[] fs = classType.getFields();
    for ( Field f : fs ) {
      UILabel field = f.getAnnotation( UILabel.class );
      if ( field == null ) {
        continue;
      }
      String value = from.getAttributeAsString( f.getName() );
      if ( field.isId() ) {
        if ( value == null || value.isEmpty() ) {
          //          if ( field.type().equals( UIType.Integer ) ) {
          //            f.setFieldValue( to, 12345 );
          //          }
          //          else if ( field.type().equals( UIType.Long ) ) {
          //            f.setFieldValue( to, 12345l );
          //          }
          //          else {
          //            f.setFieldValue( to, "12345" );
          //          }
        }
        else {
          if ( field.type().equals( UIType.Integer ) ) {
            f.setFieldValue( to, Integer.decode( value ) );
          }
          else if ( field.type().equals( UIType.Long ) ) {
            f.setFieldValue( to, Long.decode( value ) );
          }
          else {
            f.setFieldValue( to, value );
          }
        }
      }
      else if ( field.type().equals( UIType.Integer ) ) {
        f.setFieldValue( to, Integer.decode( value == null ? "-1" : value ) );
      }
      else if ( field.type().equals( UIType.Password ) ) {
        f.setFieldValue( to, value == null ? "-1" : value );
      }
      else if ( field.type().equals( UIType.Date ) ) {
        f.setFieldValue( to, value == null ? "-1" : value );
      }
      else if ( field.type().equals( UIType.Float ) ) {
        f.setFieldValue( to, Float.parseFloat( value == null ? "-1" : value ) );
      }
      else if ( field.type().equals( UIType.DateTime ) ) {
        f.setFieldValue( to, from.getAttributeAsDate( f.getName() ) );
      }
      else {
        if ( f.getType().getClass().getName().contains( "Boolean" ) ) {
          f.setFieldValue( to, value == null ? false : value.equalsIgnoreCase( "false" ) ? false : true );
        }
        else {
          f.setFieldValue( to, value == null ? "" : value );
        }
      }

    }
  }

  protected ListGridRecord getEditedRecord( DSRequest request ) {
    // Retrieving values before edit
    JavaScriptObject oldValues = request.getAttributeAsJavaScriptObject( "oldValues" );
    // Creating new record for combining old values with changes
    ListGridRecord newRecord = new ListGridRecord();
    // Copying properties from old record
    JSOHelper.apply( oldValues, newRecord.getJsObj() );
    // Retrieving changed values
    JavaScriptObject data = request.getData();
    // Apply changes
    JSOHelper.apply( data, newRecord.getJsObj() );
    return newRecord;
  }
}
