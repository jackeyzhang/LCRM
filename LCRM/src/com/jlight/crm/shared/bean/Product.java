/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.shared.bean;

import java.io.Serializable;
import java.util.Date;

import com.gwtent.reflection.client.Reflectable;
import com.jlight.crm.ui.uireflect.MaskValue;
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

  @UILabel(index = 0, title = "产品名称", reqiured = true, mask = MaskValue.OnlyEnableOnAdd)
  private String name;

  @UILabel(index = 1, title = "产品编码", reqiured = true, mask = MaskValue.OnlyEnableOnAdd)
  private String code;

  @UILabel(index = 2, title = "产品识别码", mask = MaskValue.OnlyEnableOnAdd)
  private String barCode;

  @UILabel(index = 3, title = "类别", reqiured = true, type = UIType.List, mask = MaskValue.OnlyEnableOnAdd)
  private Integer cid;

  @UILabel(index = 4, title = "数量单位")
  private String unit;

  @UILabel(index = 5, title = "交货期")
  private String period;

  @UILabel(index = 6, title = "工作部门")
  private String department;

  @UILabel(index = 7, title = "产品特点")
  private String point;

  @UILabel(index = 8, title = "备注", type = UIType.TextArea)
  private String remark;

  @UILabel(index = 9, title = "创建时间", type = UIType.DateTime, mask = MaskValue.Never)
  private Date create;

  @UILabel(index = 10, title = "更新时间", type = UIType.DateTime, mask = MaskValue.Never)
  private Date edit;

  @UILabel(index = 11, title = "创建人", mask = MaskValue.Never)
  private Integer createUser;

  @UILabel(index = 12, title = "更新人", mask = MaskValue.Never)
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
