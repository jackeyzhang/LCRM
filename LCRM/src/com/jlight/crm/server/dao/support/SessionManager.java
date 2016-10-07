/**
 * 
 */
package com.jlight.crm.server.dao.support;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * 
 * @author jzhang12
 *
 */
public class SessionManager {

  private static SessionFactory sessionFactory = null;
  private static ThreadLocal<Session> threadLocal = null;
  private static SessionManager sessionManager = null;
  private static String CONFIG_PATH =  "com/jlight/crm/server/dao/hibernate.cfg.xml";
  static {
    Configuration cfg = new Configuration();
    cfg.configure(CONFIG_PATH);
    sessionFactory = cfg.buildSessionFactory();
    threadLocal = new ThreadLocal<Session>();
    sessionManager = new SessionManager();
  }

  public void closeSession() {
    Session session = (Session)threadLocal.get();
    if ( session != null ) {
      session.close();
    }
    threadLocal.set( null );
  }

  public static SessionManager getInstance() {
    return sessionManager;
  }

  public Session getSession() {
    Session session = (Session)threadLocal.get();
    if ( session == null || !session.isOpen() ) {
      session = sessionFactory.openSession();
      threadLocal.set( session );
    }
    return session;
  }

}
