/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.shared.bean;

import java.io.Serializable;

import com.gwtent.reflection.client.Reflectable;


/**
 * @author jzhang12
 *
 */
@Reflectable
public class Attribute implements Serializable {

  private static final long serialVersionUID = -5864651998937358563L;

  private Integer id;
  private String name;
  private Integer type;
  private String allValue;
  private String description;
  
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
  
  public Integer getType() {
    return type;
  }
  
  public void setType( Integer type ) {
    this.type = type;
  }
  
  public String getAllValue() {
    return allValue;
  }
  
  public void setAllValue( String allValue ) {
    this.allValue = allValue;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription( String description ) {
    this.description = description;
  }

}
