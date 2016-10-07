package com.jlight.crm.client.itf;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jlight.crm.shared.bean.User;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

  void login( String username, String password, AsyncCallback<Boolean> callback );

  void addUser( User user, AsyncCallback<Void> callback );

  void removeUser( Integer userId, AsyncCallback<Boolean> callback );

  void updateUser( User user, AsyncCallback<User> callback );

  void queryUser( String username, AsyncCallback<User> callback );

  void listUser( int startIndex, int length, AsyncCallback<List<User>> callback );
}
