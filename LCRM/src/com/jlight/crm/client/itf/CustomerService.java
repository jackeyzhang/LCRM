package com.jlight.crm.client.itf;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jlight.crm.shared.bean.Customer;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("customer")
public interface CustomerService extends RemoteService {
	public static class Util {

		private static CustomerServiceAsync instance;

		public static CustomerServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(CustomerService.class);
			}
			return instance;
		}
	}

	void addCustomer(Customer customer) throws IllegalArgumentException;

	boolean removeCustomer(Integer customerId) throws IllegalArgumentException;

	Customer updateCustomer(Customer customer) throws IllegalArgumentException;

	Customer queryCustomer(String customerName) throws IllegalArgumentException;

	List<Customer> listCustomer(int startIndex, int length);
}
