/**
 * 
 */

package com.jlight.crm.ui.uireflect;

/**
 * 控制可见性 可编辑性的掩码
 * 
 *          list   show    add     edit
 * display   1      4       16      64
 * enable    2      8       32      128
 * 
 * all = sum all = 255
 * 
 * @author jacky
 * 
 */
public interface MaskValue {

  public static final int Never = 0;
  public static final int Display_List = 1;
  public static final int Enable_List = 2;

  public static final int Display_Show = 4;
  public static final int Enable_Show = 8;

  public static final int Display_Add = 16;
  public static final int Enable_Add = 32;

  public static final int Display_Edit = 64;
  public static final int Enable_Edit = 128;

  public static final int All = 255;

  public static final int OnlyEnableOnAdd = MaskValue.Enable_List + MaskValue.Display_List + MaskValue.Display_Add + MaskValue.Display_Edit + MaskValue.Enable_Add;

}
