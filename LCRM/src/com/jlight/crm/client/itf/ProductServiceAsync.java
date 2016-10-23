package com.jlight.crm.client.itf;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jlight.crm.shared.bean.Category;
import com.jlight.crm.shared.bean.Product;


public interface ProductServiceAsync {

  void addProduct( Product Product, AsyncCallback<Void> callback );

  void removeProduct( Integer ProductId, AsyncCallback<Boolean> callback );

  void updateProduct( Product Product, AsyncCallback<Product> callback );

  void queryProduct( String Productname, AsyncCallback<Product> callback );

  void listProduct( int startIndex, int length, AsyncCallback<List<Product>> callback );

  void getCategoryList( AsyncCallback<List<Category>> callback );

  void changeCategoryName( Integer id, String newName, AsyncCallback<Category> callback );

  void addCategory( Category category, AsyncCallback<Void> callback );

  void removeCategory( Integer id, AsyncCallback<Category> callback );


}
