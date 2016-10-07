package com.jlight.crm.client.itf;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jlight.crm.shared.bean.User;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

  public static class Util {

    private static GreetingServiceAsync instance;

    public static GreetingServiceAsync getInstance() {
      if ( instance == null ) {
        instance = GWT.create( GreetingService.class );
      }
      return instance;
    }
  }

  boolean login( String username, String password ) throws IllegalArgumentException;

  void addUser( User user ) throws IllegalArgumentException;

  boolean removeUser( Integer userId ) throws IllegalArgumentException;

  User updateUser( User user ) throws IllegalArgumentException;

  User queryUser( String username ) throws IllegalArgumentException;

  List<User> listUser( int startIndex, int length );
}
