package com.jlight.crm.server.service;

import java.util.List;

import javax.servlet.ServletException;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jlight.crm.client.itf.CustomerService;
import com.jlight.crm.server.dao.CustomerDao;
import com.jlight.crm.shared.bean.Customer;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CustomerServiceImpl extends RemoteServiceServlet implements
		CustomerService {

	CustomerDao customerDao = null;

	@Override
	public void init() throws ServletException {
		super.init();
		customerDao = new CustomerDao();
	}

	@Override
	public void addCustomer(Customer customer) throws IllegalArgumentException {
		customerDao.saveorupdate(customer);
	}

	@Override
	public boolean removeCustomer(Integer customerId)
			throws IllegalArgumentException {
		if (customerId == null)
			return false;
		Customer temp = getCustomerById(customerId);
		if (temp != null) {
			customerDao.delete(temp);
		} else {
			return false;
		}
		return true;
	}

	@Override
	public Customer updateCustomer(Customer customer)
			throws IllegalArgumentException {
		return customerDao.saveorupdate(customer);
	}

	@Override
	public Customer queryCustomer(String customerName)
			throws IllegalArgumentException {
		return customerDao.getCustomer(customerName);
	}

	@Override
	public List<Customer> listCustomer(int startIndex, int length) {
		String hql = "from Customer";
		Query query = customerDao.getSession().createQuery(hql);
		query.setFirstResult(startIndex);
		query.setMaxResults(length);
		@SuppressWarnings("unchecked")
		List<Customer> ls = query.list();
		return ls;
	}

	@SuppressWarnings("unchecked")
	public Customer getCustomerById(Integer id) {
		Session session = customerDao.getSession();
		Query query = session.createQuery("from Customer where id = ? ");
		query.setInteger(0, id);
		List<Customer> li = query.list();
		if (li == null || li.size() == 0) {
			return null;
		}
		return li.get(0);
	}

}
