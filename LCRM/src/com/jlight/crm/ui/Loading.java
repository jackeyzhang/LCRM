package com.jlight.crm.ui;

import com.google.gwt.user.client.ui.PopupPanel;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;


/**
 * system operation loading
 * 
 * @author jacky
 *
 */
public class Loading extends PopupPanel {

  public Loading( String title ) {
    setWidget( getView() );
    setGlassEnabled( true );
    setModal( true );
  }

  public Canvas getView() {
    Img img = new Img( "wait-new.gif" );
    img.setSize( "30px", "30px" );
    return img;
  }

}
