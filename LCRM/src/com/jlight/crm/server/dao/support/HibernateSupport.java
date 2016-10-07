package com.jlight.crm.server.dao.support;

import org.hibernate.Session;


/**
 * @author jzhang12
 *
 * @param <T>
 */
public interface HibernateSupport<T> {


  default T save( T t ) {
    Session session = getSession();
    session.beginTransaction();
    session.save( t );
    session.getTransaction().commit();
    session.close();
    return t;
  }

  default T delete( T t ) {
    Session session = getSession();
    session.beginTransaction();
    session.delete( t );
    session.getTransaction().commit();
    session.close();
    return t;
  }

  default T update( T t ) {
    Session session = getSession();
    session.beginTransaction();
    session.merge( t );
    session.getTransaction().commit();
    session.close();
    return t;
  }

  default T saveorupdate( T t ) {
    Session session = getSession();
    session.beginTransaction();
    session.saveOrUpdate( t );
    session.getTransaction().commit();
    session.close();
    return t;
  }

  default Session getSession() {
    return SessionManager.getInstance().getSession();
  }

}
