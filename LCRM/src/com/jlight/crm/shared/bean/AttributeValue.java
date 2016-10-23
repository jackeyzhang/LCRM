/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.shared.bean;

import java.io.Serializable;
import java.util.Date;

import com.gwtent.reflection.client.Reflectable;


/**
 * @author jzhang12
 *
 */
@Reflectable
public class AttributeValue implements Serializable {

  private static final long serialVersionUID = -5864651998937358563L;

  private Integer id;
  private Integer productId;
  private Integer attributeId;
  private String value;
  private Date updateTime;
  
  public Integer getId() {
    return id;
  }
  
  public void setId( Integer id ) {
    this.id = id;
  }
  
  public Integer getProductId() {
    return productId;
  }
  
  public void setProductId( Integer productId ) {
    this.productId = productId;
  }
  
  public Integer getAttributeId() {
    return attributeId;
  }
  
  public void setAttributeId( Integer attributeId ) {
    this.attributeId = attributeId;
  }
  
  public String getValue() {
    return value;
  }
  
  public void setValue( String value ) {
    this.value = value;
  }
  
  public Date getUpdateTime() {
    return updateTime;
  }
  
  public void setUpdateTime( Date updateTime ) {
    this.updateTime = updateTime;
  }
  
  

}
