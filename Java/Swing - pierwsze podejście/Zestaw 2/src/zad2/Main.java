/**
 *
 *  @author Koszewski Przemysław PD4177
 *
 */

package zad2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

  public static void main(String[] args) {
	  
	  SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
              JFrame frame = new JFrame();

              frame.setSize(400,800);

              frame.getContentPane().setLayout(new BorderLayout());

              JTextField textField = new JTextField();
              
              DefaultListModel listModel = new DefaultListModel<>();
              
              JList list = new JList(listModel);
              
              list.addMouseListener(new MouseAdapter() {
                  @Override
                  public void mouseClicked(MouseEvent e) {
                      JList lista = (JList) e.getSource();
                      if (e.isAltDown()) {
                          int a = lista.getLeadSelectionIndex();
                          if (a != -1) {
                              listModel.remove(a);
                          }
                      }
                  }
              });

              JScrollPane scrollPane = new JScrollPane(list);

              textField.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      JTextField field = (JTextField) e.getSource();
                      String tekst = field.getText();
                      if (!tekst.isEmpty()) {
                          listModel.addElement(field.getText());
                          field.selectAll();
                          field.replaceSelection("");}
                  }
              });

              frame.add(textField, BorderLayout.NORTH);
              frame.add(scrollPane, BorderLayout.CENTER);

              frame.pack();
              frame.setLocationRelativeTo(null);
              frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
              frame.setVisible(true);
          }
      });
  }
}
