package com.jlight.crm.client.itf;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jlight.crm.shared.bean.Product;


public interface ProductServiceAsync {

  void addProduct( Product Product, AsyncCallback<Void> callback );

  void removeProduct( Integer ProductId, AsyncCallback<Boolean> callback );

  void updateProduct( Product Product, AsyncCallback<Product> callback );

  void queryProduct( String Productname, AsyncCallback<Product> callback );

  void listProduct( int startIndex, int length, AsyncCallback<List<Product>> callback );

}
