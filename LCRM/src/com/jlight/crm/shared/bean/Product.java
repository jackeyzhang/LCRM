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
 ProID
ProName
ProNo
ProCode
CID
UnitID
DPeriod
Dep
Point
Remark
EditDate
 *
 */
@Reflectable
public class Product implements Serializable {

  private static final long serialVersionUID = 7006291913572343606L;

  @UILabel(type = UIType.Integer, title = "产品编号", isId = true)
  private Integer id;

  @UILabel(title = "产品名称", reqiured = true)
  private String name;

  @UILabel(title = "产品编码", reqiured = true)
  private String code;

  @UILabel(title = "产品识别码")
  private String barCode;

  @UILabel(title = "产品编码", reqiured = true)
  private Integer cid;

  @UILabel(title = "数量单位")
  private String unit;

  @UILabel(title = "交货期")
  private String period;

  @UILabel(title = "工作部门")
  private String department;

  @UILabel(title = "产品特点")
  private String point;

  @UILabel(title = "备注", type = UIType.TextArea)
  private String remark;

  @UILabel(title = "创建时间", type = UIType.DateTime)
  private Date create;

  @UILabel(title = "更新时间", type = UIType.DateTime)
  private Date edit;

  @UILabel(title = "创建人")
  private Integer createUser;

  @UILabel(title = "更新人")
  private Integer editUser;

  public Product() {
    super();
  }

  public Integer getId() {
    return id;
  }

  public void setId( Integer id ) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode( String code ) {
    this.code = code;
  }

  public String getBarCode() {
    return barCode;
  }

  public void setBarCode( String barCode ) {
    this.barCode = barCode;
  }

  public Integer getCid() {
    return cid;
  }

  public void setCid( Integer cid ) {
    this.cid = cid;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit( String unit ) {
    this.unit = unit;
  }

  public String getPeriod() {
    return period;
  }

  public void setPeriod( String period ) {
    this.period = period;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment( String department ) {
    this.department = department;
  }

  public String getPoint() {
    return point;
  }

  public void setPoint( String point ) {
    this.point = point;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark( String remark ) {
    this.remark = remark;
  }

  public Date getCreate() {
    return create;
  }

  public void setCreate( Date create ) {
    this.create = create;
  }

  public Date getEdit() {
    return edit;
  }

  public void setEdit( Date edit ) {
    this.edit = edit;
  }

  public Integer getCreateUser() {
    return createUser;
  }

  public void setCreateUser( Integer createUser ) {
    this.createUser = createUser;
  }

  public Integer getEditUser() {
    return editUser;
  }

  public void setEditUser( Integer editUser ) {
    this.editUser = editUser;
  }

}
