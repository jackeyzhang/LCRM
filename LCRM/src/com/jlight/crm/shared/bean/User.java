/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.shared.bean;

import java.io.Serializable;

import com.gwtent.reflection.client.Reflectable;
import com.jlight.crm.ui.uireflect.UILabel;
import com.jlight.crm.ui.uireflect.UIType;


/**
 * @author jzhang12
 * 
用户编号
用户姓名
部门
职位
用户名
密码
是否有效
 *
 */
@Reflectable
public class User implements Serializable {

  private static final long serialVersionUID = 7006291913572343606L;
  
  @UILabel(type = UIType.Integer,isId = true)
  private Integer id;

  @UILabel(index=0,title="称谓",reqiured = true)
  private String nickName;

  @UILabel(index=1,title="部门")
  private String department;

  @UILabel(index=2,title="职位")
  private String title;

  @UILabel(index=3,title="用户名",reqiured = true)
  private String userName;

  @UILabel(index=4,type = UIType.Password,title="密码",reqiured = true)
  private String password;

  @UILabel(index=5,type = UIType.RadioButton,title="注销")
  private Boolean isEnable;

  public User() {
    super();
  }

  public Integer getId() {
    return id;
  }

  public void setId( Integer id ) {
    this.id = id;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName( String nickName ) {
    this.nickName = nickName;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment( String department ) {
    this.department = department;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle( String title ) {
    this.title = title;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName( String userName ) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword( String password ) {
    this.password = password;
  }

  public Boolean getIsEnable() {
    return isEnable;
  }

  public void setIsEnable( Boolean isEnable ) {
    this.isEnable = isEnable;
  }

}
