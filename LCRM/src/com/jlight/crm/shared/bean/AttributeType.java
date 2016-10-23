/**
 * Created by Jacky Zhang.
 */
package com.jlight.crm.shared.bean;


/**
 * @author jzhang12
 *
 */
public class AttributeType {

  public static final AttributeType SIZE = new AttributeType(1,"大小");
  public static final AttributeType COLOR = new AttributeType(2,"颜色");
  public static final AttributeType MATERIAL = new AttributeType(3,"材质");
  
  private int id;
  private String name;
  
  public AttributeType(int id, String name){
    this.id = id;
    this.name = name;
  }

  
  public int getId() {
    return id;
  }

  
  public void setId( int id ) {
    this.id = id;
  }

  
  public String getName() {
    return name;
  }

  
  public void setName( String name ) {
    this.name = name;
  }
  
  
}
