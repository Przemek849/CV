/**
 *
 *  @author Koszewski Przemysław PD4177
 *
 */

package zad1;

import javax.swing.*;

public class Main {

  public static void main(String[] args) {
	  
	  AbstractListModel abstractListModel = new AbstractListModel() {
          @Override
          public int getSize() {
              return 131;
          }

          @Override
          public Object getElementAt(int index) {
              int k = index - 70;
              double f = 32 + 1.8 * k;

              return k + " stopni C = " + Math.round(f * 10.0)/10.0 + " stopni F";
          }
      };

      JFrame fr = new JFrame();
      JList list = new JList<>(abstractListModel);
      JScrollPane sc = new JScrollPane(list);
      fr.add(sc);

      fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      fr.pack();
      fr.setVisible(true);
  }
}
