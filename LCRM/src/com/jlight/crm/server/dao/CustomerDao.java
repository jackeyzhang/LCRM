/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.server.dao;

import org.hibernate.Query;

import com.jlight.crm.server.dao.support.HibernateSupport;
import com.jlight.crm.shared.bean.Customer;


/**
 * @author jzhang12
 *
 */
public class CustomerDao implements HibernateSupport<Customer> {

  public Customer getCustomer( String custName ) {
    Query query = getSession().createQuery( "from Product where custName = ? " );
    query.setString( 0, custName );
    if ( query.list() == null || query.list().size() == 0 ) {
      return null;
    }
    return (Customer)query.list().get( 0 );
  }

}
