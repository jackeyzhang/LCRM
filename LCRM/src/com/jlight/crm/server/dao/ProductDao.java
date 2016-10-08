/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.server.dao;

import org.hibernate.Query;

import com.jlight.crm.server.dao.support.HibernateSupport;
import com.jlight.crm.shared.bean.Product;


/**
 * @author jzhang12
 *
 */
public class ProductDao implements HibernateSupport<Product> {

  public Product getProduct( String Productname ) {
    Query query = getSession().createQuery( "from Product where name = ? " );
    query.setString( 0, Productname );
    if ( query.list() == null || query.list().size() == 0 ) {
      return null;
    }
    return (Product)query.list().get( 0 );
  }

}
