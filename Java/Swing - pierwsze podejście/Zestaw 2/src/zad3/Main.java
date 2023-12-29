/**
 *
 *  @author Koszewski Przemysław PD4177
 *
 */

package zad3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class Main {

  public static void main(String[] args) {
	  
	  
      String[] kolumny = {"Autor", "Tytuł", "Cena", "Obrazek"};

      JFrame frame = new JFrame();
      frame.setLayout(new BorderLayout());

      CustomTableModel defaultTableModel = new CustomTableModel(kolumny,0);

      JTable table = new JTable(defaultTableModel);


      try {
          BufferedReader reader = new BufferedReader(new FileReader("ksiazki/ksiazki.txt"));

          CustomTableModel tableModel = (CustomTableModel) table.getModel();

          //opcja2
          Scanner scanner = new Scanner("ksiazki/ksiazki.txt");

          String line;
          while ((line = reader.readLine()) != null) {
              Object[] obj = line.split(",");
              for (int i = 0; i < obj.length; i++) {
                  obj[i] = obj[i].toString().trim();
              }

              tableModel.addRow(new Object[]{obj[0], obj[1], obj[2], new ImageIcon("ksiazki/" + obj[3].toString())});
          }
          reader.close();

      } catch (Exception e) {
          e.printStackTrace();
      }

      JScrollPane scrollPane = new JScrollPane(table);

      JPanel panel = new JPanel();

      JButton dodaj = new JButton("Dodaj");
      dodaj.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              Vector<Object> nowy = new Vector<>();
              nowy.add("");
              nowy.add("");
              nowy.add("");
              nowy.add("");
              defaultTableModel.addRow(nowy);
          }
      });

      panel.add(dodaj);

      JButton usun = new JButton("Usuń");
      usun.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int zaznaczony = table.getSelectedRow();
              if (zaznaczony != -1) {
                  defaultTableModel.removeRow(zaznaczony);
              } else {
                  System.out.println("Zaznacz wiersz, aby usunąć");
              }
          }
      });

      panel.add(usun);

      frame.add(panel, BorderLayout.NORTH);
      frame.add(scrollPane, BorderLayout.CENTER);

      frame.pack();
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setVisible(true);

  }
}

class CustomTableModel extends DefaultTableModel {
  private static final long serialVersionUID = 1L;

  public CustomTableModel(Object[] columnNames, int rowCount) {
      super(columnNames, rowCount);
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
      if (columnIndex == 3) {
          return ImageIcon.class;
      }
      return super.getColumnClass(columnIndex);
  }
  public boolean isCellEditable(int rowIndex, int columnIndex) {
      if (columnIndex == 2) {
          return true;
      } else {
          return false;
      }
  }
}