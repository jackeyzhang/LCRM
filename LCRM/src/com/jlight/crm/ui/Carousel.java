/**
 * 
 */
package com.jlight.crm.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Timer;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;


/**
 * Carousel picture 3
 * 
 * @author jacky
 *
 */

public class Carousel extends VLayout {

  private int count = 0;

  private List<Img> imgs = new ArrayList<Img>();

  private HLayout imagePanel = new HLayout();

  private HLayout controlPanel = new HLayout();

  private Canvas control1, control2, control3;

  private boolean started = true;

  public Carousel( int width, int height, String... ImagePath ) {
    for ( String path : ImagePath ) {
      Img img1 = new Img( path, width, height );
      imgs.add( img1 );
    }
    setWidth(width);
    setHeight(height);
    loadPanel( width );
  }


  private void loadPanel( int width ) {
    HLayout spacePanel = new HLayout();
    spacePanel.setHeight( "20px" );
    addMember( spacePanel );

    imagePanel.setAlign( Alignment.CENTER );
    controlPanel.setAlign( Alignment.CENTER );

    WithCenterLittleControlPanel wcontrolPanel = new WithCenterLittleControlPanel( 20, width );
    control1 = wcontrolPanel.addNoTextControl();
    control2 = wcontrolPanel.addNoTextControl();
    control3 = wcontrolPanel.addNoTextControl();
    controlPanel.addMember( wcontrolPanel );

    addMember( imagePanel );
    addMember( controlPanel );

    control1.addMouseOverHandler( new MouseOverHandler() {

      @Override
      public void onMouseOver( MouseOverEvent event ) {
        count = 0;
        started = false;
        animateShow();
      }
    } );

    control1.addMouseOutHandler( new MouseOutHandler() {

      @Override
      public void onMouseOut( MouseOutEvent event ) {
        started = true;
      }
    } );

    control2.addMouseOverHandler( new MouseOverHandler() {

      @Override
      public void onMouseOver( MouseOverEvent event ) {
        count = 1;
        started = false;
        animateShow();
      }
    } );

    control2.addMouseOutHandler( new MouseOutHandler() {

      @Override
      public void onMouseOut( MouseOutEvent event ) {
        started = true;
      }
    } );

    control3.addMouseOverHandler( new MouseOverHandler() {

      @Override
      public void onMouseOver( MouseOverEvent event ) {
        count = 2;
        started = false;
        animateShow();
      }
    } );

    control3.addMouseOutHandler( new MouseOutHandler() {

      @Override
      public void onMouseOut( MouseOutEvent event ) {
        started = true;
      }
    } );

    new Timer() {

      @Override
      public void run() {
        if ( started )
          animateShow();
      }
    }.scheduleRepeating( 4000 );
  }

  private void animateShow() {
    if ( count == imgs.size() ) {
      count = 0;
    }
    for ( Canvas cas : imagePanel.getMembers() ) {
      imagePanel.removeMember( cas );
    }
    if ( count == 0 ) {
      control1.setStyleName( "centercontrolpanel-mousein" );
      control2.setStyleName( "centercontrolpanel" );
      control3.setStyleName( "centercontrolpanel" );
    }
    else if ( count == 1 ) {
      control2.setStyleName( "centercontrolpanel-mousein" );
      control1.setStyleName( "centercontrolpanel" );
      control3.setStyleName( "centercontrolpanel" );
    }
    else if ( count == 2 ) {
      control3.setStyleName( "centercontrolpanel-mousein" );
      control2.setStyleName( "centercontrolpanel" );
      control1.setStyleName( "centercontrolpanel" );
    }
    imagePanel.setVisible( false );
    imagePanel.addMember( imgs.get( count ) );
    imagePanel.animateShow( AnimationEffect.FADE, null, 2000 );
    count++;
  }

  public class WithCenterLittleControlPanel extends HLayout {

    public WithCenterLittleControlPanel( int height, int width ) {
      super();
      this.setWidth( width );
      this.setHeight( height );
      this.setPadding( 5 );
      this.setMembersMargin( 15 );
      this.setAlign( Alignment.CENTER );
    }

    public WithCenterLittleControlPanel( int height ) {
      super();
      this.setWidth100();
      this.setHeight( height );
      this.setAlign( Alignment.CENTER );
    }

    public Canvas addControl( String title, String width ) {
      final HLayout h = new HLayout();
      h.setStyleName( "centercontrolpanel" );
      h.setWidth( width );
      h.setAlign( Alignment.CENTER );

      h.addMember( new Label( title ) );
      h.addMouseOverHandler( new MouseOverHandler() {

        @Override
        public void onMouseOver( MouseOverEvent event ) {
          h.setStyleName( "centercontrolpanel-mousein" );
        }
      } );

      h.addMouseOutHandler( new MouseOutHandler() {

        @Override
        public void onMouseOut( MouseOutEvent event ) {
          h.setStyleName( "centercontrolpanel" );
        }
      } );
      addMember( h );
      return h;
    }

    public Canvas addNoTextControl() {
      HLayout h = new HLayout();
      h.setStyleName( "centercontrolpanel" );
      h.setWidth( 10 );
      h.setHeight( 10 );
      h.setAlign( Alignment.CENTER );
      addMember( h );
      return h;
    }

  }
}
