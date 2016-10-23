/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.server.dao;

import org.hibernate.Query;

import com.jlight.crm.server.dao.support.HibernateSupport;
import com.jlight.crm.shared.bean.Category;


/**
 * @author jzhang12
 *
 */
public class CategoryDao implements HibernateSupport<Category> {

  public Category getCategory( Integer id ) {
    Query query = getSession().createQuery( "from Category where id = ? " );
    query.setInteger( 0, id );
    if ( query.list() == null || query.list().size() == 0 ) {
      return null;
    }
    return (Category)query.list().get( 0 );
  }

}
