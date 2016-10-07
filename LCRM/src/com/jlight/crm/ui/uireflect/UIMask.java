/**
 * 
 */

package com.jlight.crm.ui.uireflect;

/**
 * 控制可见性 可编辑性的掩码
 * 
 * @author jacky
 * 
 */
public enum UIMask {
	
	nevershow(0),

	showInList(1),

	showInEdit(2),

	showInAdd(4), 
	
	showAll(7),// show all 7

	enInAdd(8),//enable only add 15

	enInEdit(16),//enable only edit 23
	
	enAll(24),//enable all
	
	All(31);//show and enable all

	private int value;

	private UIMask( int _v )
	{
		this.value = _v;
	}

	/**
	 * @return the value
	 */
	public Integer getValue( )
	{
		return value;
	}

}
