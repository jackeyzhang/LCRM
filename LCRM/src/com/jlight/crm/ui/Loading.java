package com.jlight.crm.ui;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;


/**
 * system operation loading
 * 
 * @author jacky
 *
 */
public class Loading extends DefaultDialog {

  private Label hintlabel;

  public Loading( String title ) {
    super( title );
    this.setIsModal( true );
    this.setShowMinimizeButton( false );
    this.setShowModalMask( true );
    this.setShowCloseButton( false );
  }

  @Override
  public Canvas getView() {
    Img img = new Img( "loading2.gif" );
    img.setSize( "100px", "100px" );
    return img;
  }

  public void setHint( String hint ) {
    hintlabel.setContents( hint );
    hintlabel.redraw();
  }

}
