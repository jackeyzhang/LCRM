package com.jlight.crm.client.itf;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jlight.crm.shared.bean.Customer;

public interface CustomerServiceAsync {

	void addCustomer(Customer customer, AsyncCallback<Void> callback);

	void removeCustomer(Integer customerId, AsyncCallback<Boolean> callback);

	void updateCustomer(Customer customer, AsyncCallback<Customer> callback);

	void queryCustomer(String customerName, AsyncCallback<Customer> callback);

	void listCustomer(int startIndex, int length,
			AsyncCallback<List<Customer>> callback);

}
