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
 */
@Reflectable
public class Category implements Serializable{
  
  private static final long serialVersionUID = 5904551980841952546L;

  @UILabel(type = UIType.Integer, isId = true)
  private Integer id;
  
  @UILabel(type = UIType.Integer)
  private Integer pid;
  
  @UILabel(title = "客户名称", reqiured = true)
  private String name;
  
  private Integer sortId;

  
  public Integer getId() {
    return id;
  }

  
  public void setId( Integer id ) {
    this.id = id;
  }

  
  public Integer getPid() {
    return pid;
  }

  
  public void setPid( Integer pid ) {
    this.pid = pid;
  }

  
  public String getName() {
    return name;
  }

  
  public void setName( String name ) {
    this.name = name;
  }

  
  public Integer getSortId() {
    return sortId;
  }

  
  public void setSortId( Integer sortId ) {
    this.sortId = sortId;
  }
  
}
