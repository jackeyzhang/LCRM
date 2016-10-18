package com.jlight.crm.ui;

import com.jlight.crm.ui.uireflect.UIType;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DateDisplayFormat;


public class DefaultField implements Comparable<DefaultField>
{

	private int mask,index = -1;
	
	public static final String STYPE = "stype", MASK = "smask";
	
	private DataSourceField field;
	
	/**
	 * @param name
	 * @param simpleType
	 * @param title
	 */
	public DefaultField( String name, String simpleType, String title, int index)
	{
		this(name,simpleType,title,-1,index);
	}
	
	
	
	/**
	 * @param name
	 * @param simpleType
	 * @param title
	 */
	public DefaultField( String name, String simpleType, String title, int mask,int _index )
	{
//		super( name, new SimpleType(), title );
		if( simpleType.equals( UIType.DateTime.toString( )  ))
		{
			field = new DataSourceField( name, com.smartgwt.client.types.FieldType.DATETIME, title );
			field.setDateFormatter( DateDisplayFormat.TOSERIALIZEABLEDATE );
		}
		else if( simpleType.equals( UIType.Date.toString( )  ))
		{
			field = new DataSourceField( name, com.smartgwt.client.types.FieldType.DATE, title );
			field.setDateFormatter( DateDisplayFormat.TOJAPANSHORTDATE );
		}
		else if( simpleType.equals( UIType.RadioButton.toString( )  ))
        {
            field = new DataSourceField( name, com.smartgwt.client.types.FieldType.BOOLEAN, title );
        }
		else if( simpleType.equals( UIType.Integer.toString( )  ))
        {
            field = new DataSourceField( name, com.smartgwt.client.types.FieldType.INTEGER, title );
        }
		else if( simpleType.equals( UIType.Password.toString( )  ))
        {
            field = new DataSourceField( name, com.smartgwt.client.types.FieldType.PASSWORD, title );
        }
		else if( simpleType.equals( UIType.List.toString( )  ))
        {
            field = new DataSourceField( name, com.smartgwt.client.types.FieldType.ENUM, title );
        }
		else
		{
			field = new DataSourceField( name, com.smartgwt.client.types.FieldType.TEXT, title );
		}
		this.mask = mask;
		this.index = _index;
		field.setAttribute( STYPE, simpleType );
		field.setAttribute( MASK, mask );
	}


	
	/**
	 * @return the mask
	 */
	public int getMask( )
	{
		return mask;
	}

	
	/**
	 * @param mask the mask to set
	 */
	public void setMask( int mask )
	{
		this.mask = mask;
		field.setAttribute( MASK, mask );
	}



	@Override
	public int compareTo( DefaultField o )
	{
		return this.index - o.index ;
	}
	
	public DataSourceField getField()
	{
		return field;
	}
	
}
