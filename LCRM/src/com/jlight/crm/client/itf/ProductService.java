package com.jlight.crm.client.itf;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jlight.crm.shared.bean.Category;
import com.jlight.crm.shared.bean.Product;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("product")
public interface ProductService extends RemoteService {
  public static class Util {

    private static ProductServiceAsync instance;

    public static ProductServiceAsync getInstance() {
      if ( instance == null ) {
        instance = GWT.create( ProductService.class );
      }
      return instance;
    }
  }
  
  void addProduct( Product Product ) throws IllegalArgumentException;

  boolean removeProduct( Integer ProductId ) throws IllegalArgumentException;

  Product updateProduct( Product Product ) throws IllegalArgumentException;

  Product queryProduct( String Productname ) throws IllegalArgumentException;

  List<Product> listProduct( int startIndex, int length );
  
  List<Category> getCategoryList();
  
  Category changeCategoryName(Integer id, String newName );
  
  void addCategory( Category category ) throws IllegalArgumentException;
  
  Category removeCategory(Integer id) throws IllegalArgumentException;
}
