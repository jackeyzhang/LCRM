/**
 * 
 */
package com.jlight.crm.ui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;


/**
 * call back with status
 * 
 * @author jzhang12
 * @param <T>
 */
public abstract class AsyncCallbackWithStatus<T> implements AsyncCallback<T> {

  Loading loadinghint;

  public AsyncCallbackWithStatus() {
    this( "操作中......" );
  }

  public AsyncCallbackWithStatus( String hint ) {
    this.loadinghint = new Loading( hint );
    loadinghint.center();
  }

  @Override
  public void onFailure( Throwable caught ) {
    if ( loadinghint != null ) {
      loadinghint.hide();
    }
    SC.say( "系统出了个小问题:" + caught.getMessage() );
  }

  @Override
  public void onSuccess( T result ) {
    call( result );
    if ( loadinghint != null ) {
      loadinghint.hide();
    }
  }

  public abstract void call( T result );

}
