/**
 *
 *  @author Koszewski Przemysław PD4177
 *
 */

package zad1;

import javax.swing.*;
import java.awt.*;

public class Main {

  public static void main(String[] args) {
	  
	  SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
              JFrame frame = new JFrame();
              frame.setLayout(new BorderLayout());
              String polozenie[] = {"West", "North", "East", "South", "Center"};

              Color[] colors = {Color.white, Color.red, Color.green, Color.blue, Color.cyan};

              Color[] pismo = {Color.MAGENTA, Color.orange, Color.red, Color.pink, Color.green};

              Integer[] wielkosc = {8, 12, 16, 20, 24};

              String[] podpowiedz = {"Label1", "Label2", "Label3", "Label4", "Label5"};

              for (int i = 0; i < polozenie.length; i++) {
                  JLabel Lab = new JLabel(podpowiedz[i]);
                  Lab.setOpaque(true);
                  Lab.setBackground(colors[i]);
                  Lab.setPreferredSize(new Dimension(200, 100));
                  Lab.setFont(new Font("Dialog", Font.PLAIN, wielkosc[i]));
                  Lab.setForeground(pismo[i]);
                  Lab.setToolTipText(podpowiedz[i]);

                  switch (i) {
                      case 0:
                          Lab.setBorder(BorderFactory.createLineBorder(pismo[i]));
                          break;
                      case 1:
                          Lab.setBorder(BorderFactory.createRaisedBevelBorder());
                          break;
                      case 2:
                          Lab.setBorder(BorderFactory.createMatteBorder(5, 10, 5, 15, Color.blue));
                          break;
                      case 3:
                          Lab.setBorder(BorderFactory.createSoftBevelBorder(wielkosc[i]));
                          break;
                      case 4:
                          Lab.setBorder(BorderFactory.createLoweredBevelBorder());
                          break;
                  }
                  frame.add(Lab, polozenie[i]);
              }
              frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
              frame.pack();
              frame.setLocationRelativeTo(null);
              frame.setVisible(true);
          }
      });
  }
}
