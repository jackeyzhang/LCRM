/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.shared.bean;

import java.io.Serializable;
import java.util.Date;

import com.gwtent.reflection.client.Reflectable;
import com.jlight.crm.ui.uireflect.UILabel;
import com.jlight.crm.ui.uireflect.UIType;

/**
 * @author jzhang12
 * 
 *         客户编号 客户名称 客户地址 联系人 电话 邮件 手机 传真 收货地址 客户类型 创建时间
 * 
 *
 */
@Reflectable
public class Customer implements Serializable {

	private static final long serialVersionUID = 7006291913572343606L;

	@UILabel(type = UIType.Integer, isId = true)
	private Integer id;

	@UILabel(index = 0, title = "客户名称", reqiured = true)
	private String name;

	@UILabel(index = 1, title = "客户地址")
	private String custAddr;

	@UILabel(index = 2, title = "联系人")
	private String contact;
	@UILabel(index = 3, title = "电话")
	private String phone;
	@UILabel(index = 4, title = "邮件")
	private String email;
	@UILabel(index = 5, title = "手机")
	private String cellphone;
	@UILabel(index = 6, title = "传真")
	private String fax;
	@UILabel(index = 7, title = "收货地址")
	private String shiptoAddr;
	@UILabel(index = 8, title = "客户类型", type = UIType.Integer)
	private Integer custType;
	@UILabel(index = 9, title = "创建时间", type = UIType.DateTime)
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getShiptoAddr() {
		return shiptoAddr;
	}

	public void setShiptoAddr(String shiptoAddr) {
		this.shiptoAddr = shiptoAddr;
	}

	public Integer getCustType() {
		return custType;
	}

	public void setCustType(Integer custType) {
		this.custType = custType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
