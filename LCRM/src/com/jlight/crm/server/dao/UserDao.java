/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.server.dao;

import org.hibernate.Query;

import com.jlight.crm.server.dao.support.HibernateSupport;
import com.jlight.crm.shared.bean.User;


/**
 * @author jzhang12
 *
 */
public class UserDao implements HibernateSupport<User> {
  
  public User getUser(String username){
    Query query = getSession().createQuery("from User where userName = ? ");
    query.setString(0, username);
    if (query.list() == null || query.list().size() == 0) {
        return null;
    }
    return (User) query.list().get(0);
  }

}
