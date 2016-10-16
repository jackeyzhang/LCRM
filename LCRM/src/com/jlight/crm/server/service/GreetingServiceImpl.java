package com.jlight.crm.server.service;

import java.util.List;

import javax.servlet.ServletException;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jlight.crm.client.itf.GreetingService;
import com.jlight.crm.server.dao.UserDao;
import com.jlight.crm.shared.bean.User;


/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

  UserDao userDao = null;

  @Override
  public void init() throws ServletException {
    super.init();
    userDao = new UserDao();
  }

  @Override
  public boolean login( String username, String password ) throws IllegalArgumentException {
    User user = userDao.getUser( username );
    if ( user == null )
      return false;
    if ( user.getPassword().equals( password ) )
      return true;
    return false;
  }

  public String escapeHtml( String html ) {
    if ( html == null ) {
      return null;
    }
    return html.replaceAll( "&", "&amp;" ).replaceAll( "<", "&lt;" ).replaceAll( ">", "&gt;" );
  }

  @Override
  public void addUser( User user ) throws IllegalArgumentException {
    userDao.saveorupdate( user );
  }

  @Override
  public boolean removeUser(Integer userId) throws IllegalArgumentException {
    if(userId == null) return false;
    User temp = getUserById(userId);
    if ( temp != null ) {
      userDao.delete( temp );
    }
    else {
      return false;
    }
    return true;
  }

  @Override
  public User updateUser( User user ) throws IllegalArgumentException {
    return userDao.update( user );
  }

  @Override
  public User queryUser( String username ) throws IllegalArgumentException {
    return userDao.getUser( username );
  }

  @Override
  public List<User> listUser( int startIndex, int length ) {
    String hql = "from User";
    Query query = userDao.getSession().createQuery( hql );
    query.setFirstResult( startIndex );
    query.setMaxResults( length );
    @SuppressWarnings("unchecked")
    List<User> ls = query.list();
    return ls;
  }

  @SuppressWarnings("unchecked")
  public User getUserById( Integer id ) {
    Session session = userDao.getSession();
    Query query = session.createQuery( "from User where id = ? " );
    query.setInteger( 0, id );
    List<User> li = query.list();
    if ( li == null || li.size() == 0 ) {
      return null;
    }
    return li.get( 0 );
  }

}
