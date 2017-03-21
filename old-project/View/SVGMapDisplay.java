package View;

import org.apache.batik.swing.*;
import org.apache.batik.swing.svg.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Event.*;

public class SVGMapDisplay {

  JSVGCanvas svgCanvas = new JSVGCanvas();

  public static void main(String args[]) {
    JFrame frame = new JFrame("Colorado");
    frame.setSize(1280, 1024);

    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent ev) {
        System.exit(0);
      }
    });

   
  }

  public SVGMapDisplay(JFrame frame, String fileName) {
	  try{
		    frame.getContentPane().setLayout(new BorderLayout());
		    frame.getContentPane().add("Center", svgCanvas);
		    frame.setVisible(true);
		    svgCanvas.setURI("file:"+fileName+".svg");
	  }
	  catch(Exception e){
		  System.out.println("good");
	  }
  }
  public void run(){
	  JFrame frame = new JFrame("JSVGCanvas Demo");
	  frame.setSize(400, 400);

	  frame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent ev) {
      System.exit(0);
    }
  });
  }
}