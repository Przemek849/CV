/**
 *
 *  @author Koszewski Przemysław PD4177
 *
 */

package zad2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

  public static void main(String[] args) {
	  SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
              JFrame frame = new JFrame("Ramka");
              JButton button = new JButton("Przycisk");
              button.setBackground(Color.white);
              button.addActionListener(new ActionListener() {
                  private int i = 1;
                  private Color[] colors = {Color.white, Color.red, Color.green, Color.blue};

                  @Override
                  public void actionPerformed(ActionEvent e) {
                      if (i < 4) {
                          button.setBackground(colors[i]);
                          i++;
                          if (i == 4) {
                              i = 0;
                          }
                      }
                  }
              });
              frame.add(button);
              frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
              frame.setSize(400, 400);

              frame.setLocationRelativeTo(null);
              frame.setVisible(true);
          }
      });
  }
}
