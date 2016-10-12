package com.jlight.crm.server.service;

import java.util.List;

import javax.servlet.ServletException;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jlight.crm.client.itf.ProductService;
import com.jlight.crm.server.dao.ProductDao;
import com.jlight.crm.shared.bean.Category;
import com.jlight.crm.shared.bean.Product;


/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ProductServiceImpl extends RemoteServiceServlet implements ProductService {

  ProductDao productDao = null;

  @Override
  public void init() throws ServletException {
    super.init();
    productDao = new ProductDao();
  }

  @Override
  public void addProduct( Product Product ) throws IllegalArgumentException {
    productDao.saveorupdate( Product );
  }

  @Override
  public boolean removeProduct( Integer ProductId ) throws IllegalArgumentException {
    if(ProductId == null) return false;
    Product temp = getProductById(ProductId);
    if ( temp != null ) {
      productDao.delete( temp );
    }
    else {
      return false;
    }
    return true;
  }

  @Override
  public Product updateProduct( Product Product ) throws IllegalArgumentException {
    return productDao.saveorupdate( Product );
  }

  @Override
  public Product queryProduct( String Productname ) throws IllegalArgumentException {
    return productDao.getProduct( Productname );
  }

  @Override
  public List<Product> listProduct( int startIndex, int length ) {
    String hql = "from Product";
    Query query = productDao.getSession().createQuery( hql );
    query.setFirstResult( startIndex );
    query.setMaxResults( length );
    @SuppressWarnings("unchecked")
    List<Product> ls = query.list();
    return ls;
  }

  @SuppressWarnings("unchecked")
  public Product getProductById( Integer id ) {
    Session session = productDao.getSession();
    Query query = session.createQuery( "from Product where id = ? " );
    query.setInteger( 0, id );
    List<Product> li = query.list();
    if ( li == null || li.size() == 0 ) {
      return null;
    }
    return li.get( 0 );
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Category> getCategoryList() {
    Session session = productDao.getSession();
    Query query = session.createQuery( "from Category " );
    List<Category> li = query.list();
    if ( li == null || li.size() == 0 ) {
      return null;
    }
    return li;
  }


}
