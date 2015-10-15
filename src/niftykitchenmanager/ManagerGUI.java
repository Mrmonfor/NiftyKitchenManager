/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niftykitchenmanager;
import java.awt.BasicStroke;

//import java.io.IOException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Graphics2D;

import java.awt.Image;

import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.swing.*;
import java.util.Calendar;
import java.util.Scanner;

import javax.imageio.ImageIO;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


/**
 *
 * @author Matt
 * 
 * 
 */

public class ManagerGUI {
    private JFrame jFrame;
    private JPanel mainPanel, tablePanel, table10Panel, table11Panel, table12Panel, table13Panel,
            table14Panel, table15Panel, table16Panel,table17Panel, table18Panel, clockPanel, selectTablePanel, optionsPanel, 
            infoPanel, assignPanel, addPanel, editPanel, deletePanel, menuPanel, tableViewPanel, tableOptionsPanel, addToOrderPanel;
    private Color myPurple, myRed;
    private JButton table10, table11, table12, table13, table14, table15, 
            table16, table17, table18, assignButton, assignButton2, doneAssignButton, 
            editButton, addItemButton, deleteItemButton, doneEditButton, editItemButton, addTableItemButton, addTableItemButton2, 
            deleteTableItemButton, closeTableButton, doneTableButton, doneTableButton2;
    private JCheckBox cB10, cB11, cB12, cB13, cB14, cB15, cB16, cB17, cB18, empty, occupied;
    private JRadioButton w1, w2, w3, noWaiter;   
    private ButtonGroup bg1;
    private JComboBox seats;
    private JTextField clockJText, addJText, editJText, addPriceJText, editPriceJText ; 
    private JLabel mainPageLabel, optionLabel, infoLabel, infoLabel2, infoLabel3, selectWaiterLabel, addLabel,
            editLabel, deleteLabel, addNameLabel, editNameLabel, addPriceLabel, editPriceLabel, menuLabel, tablename, addMenuLabel;
    private String waiter1 ="Waiter #1", waiter2="Waiter #2", waiter3="Waiter #3", waiter4="";
    private DefaultListModel foodModel, foodModel2, tableModel;
    private JList foodMenu, foodMenu2, tableOrder;
    private JScrollPane menuScroll, orderScroll, menuScroll2;
    private Icon emptyIcon, selectedIcon, emptyTableIcon, buttonIcon, BG;
    private String[][] tableInfoArray;
    private String[] seatArray = {"Seat 1", "Seat 2", "Seat 3", "Seat 4", "Seat 5"};
    private JTree tableTree;
    private static final int BI_WIDTH = 40;
    private int currentTable;
    private Image background;
    
   
    
    public ManagerGUI() throws IOException{
       //UIManager.getDefaults().put("Button.disabledText",new ColorUIResource(Color.BLUE));
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = 1615;
       final int HEIGHT = 890;
       // final int WIDTH2 = 300;
       // final int HEIGHT2 = 150;
        jFrame = new JFrame("Manager System");
         myPurple = new Color(255, 200, 255);
         myRed = new Color(200, 0, 20);
        // System.out.println(Color.magenta);
       // mainPageLabel.set
        
       Dimension screenSize = new Dimension(WIDTH, HEIGHT);
        jFrame.setPreferredSize(screenSize);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //jFrame.setExtendedState(jFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
         //jFrame.setLocation(dim.width / 4 - jFrame4.getSize().width / 4, dim.height / 4 - jFrame4.getSize().height / 4);
        buildGUI();
       jFrame.add(mainPanel);
        jFrame.pack();
          jFrame.setVisible(true);
}
    public void buildGUI() throws IOException {
       
        createLargeIcons();
        makeArray();
        
        table10 = new JButton("<html>Table 10</html>");
        table11 = new JButton("<html>Table 11</html>");
        table12 = new JButton("<html>Table 12</html>");
        table13 = new JButton("<html>Table 13</html>");
        table14 = new JButton("<html>Table 14</html>");
        table15 = new JButton("<html>Table 15</html>");
        table16 = new JButton("<html>Table 16</html>");
        table17 = new JButton("<html>Table 17</html>");
        table18 = new JButton("<html>Table 18</html>");
        cB10 = new JCheckBox("", emptyIcon);
        cB10.setOpaque(false);
        cB11 = new JCheckBox("", emptyIcon);
        cB11.setOpaque(false);
        cB12 = new JCheckBox("", emptyIcon);
        cB12.setOpaque(false);
        cB13 = new JCheckBox("", emptyIcon);
        cB13.setOpaque(false);
        cB14 = new JCheckBox("", emptyIcon);
        cB14.setOpaque(false);
        cB15 = new JCheckBox("", emptyIcon);
        cB15.setOpaque(false);
        cB16 = new JCheckBox("", emptyIcon);
        cB16.setOpaque(false);
        cB17 = new JCheckBox("", emptyIcon);
        cB17.setOpaque(false);
        cB18 = new JCheckBox("", emptyIcon);
        cB18.setOpaque(false);
        empty = new JCheckBox("", emptyTableIcon);
        empty.setBackground(myPurple);
        occupied = new JCheckBox("", selectedIcon);
        occupied.setBackground(myPurple);
        assignButton = new JButton("Assign Tables", buttonIcon);
        editButton = new JButton("Edit Menu",buttonIcon);
        
        makeTableButton(table10);
        makeTableButton(table11);
        makeTableButton(table12);
        makeTableButton(table13);
        makeTableButton(table14);
        makeTableButton(table15);
        makeTableButton(table16);
        makeTableButton(table17);
        makeTableButton(table18);
        makeOptionButton(assignButton);
        makeOptionButton(editButton);
        
        checkTableEmpty();
        buildEditGUI();
        
        buildClockPanel();
        buildTableViewGUI();
       
        setTableLocations();

        //***** Main Panel ******************************
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
       
        mainPanel.setBackground(Color.magenta.brighter().brighter().brighter().brighter());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));   
        mainPageLabel = new JLabel("Select a Table to View");
        mainPageLabel.setOpaque(false);
        mainPageLabel.setFont(new Font("Candara", Font.PLAIN, 32));        
       
        //mainPageLabel.setLocation(100, 50);
        //**************************************************
        
         assignTablesPanel();
        
        infoLabel = new JLabel("<html><u>Restaurant Information:</u><br><br> Total Expenses: $2541.79 " 
                +  "<br>Total Income: $5213.92 " + "<br><br><br> Total Number of Customers: 24</html>" );
        infoLabel.setFont(new Font("Candara", Font.PLAIN, 34));
        infoLabel2 = new JLabel("<html>\0\0\0\0\0\0\0\0\0\0\0\0 Empty Table:\0\0\0\0\0\0\0\0</html>" );
        infoLabel2.setFont(new Font("Candara", Font.PLAIN, 34));
        infoLabel3 = new JLabel("<html>\0\0\0\0\0\0\0\0\0\0\0\0\0 Occupied Table: \0</html>" );
        infoLabel3.setFont(new Font("Candara", Font.PLAIN, 34));
       
        //****************** Table Panel*****************
        tablePanel = new JPanel();
        tablePanel.setLayout(null);        
        tablePanel.setLocation(0, 0);
        //final int PANELWIDTH = (int) (Math.floor((jFrame.getWidth())/2));
        tablePanel.setSize(1000, 852);
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        tablePanel.setBackground(Color.orange);
        
        
        //**********************************
        
        selectTablePanel =new JPanel(); 
        selectTablePanel.setLocation(10, 10);
        selectTablePanel.setSize(900, 40);
        selectTablePanel.setOpaque(false);
        selectTablePanel.add(mainPageLabel);
        tablePanel.add(selectTablePanel);
        
        infoPanel = new JPanel();
        infoPanel.setLocation(990, 75);
        infoPanel.setSize(605, 477);
        infoPanel.setBackground(myPurple);
        infoPanel.add(infoLabel);
        infoPanel.add(infoLabel2);
        infoPanel.add(empty);
        infoPanel.add(infoLabel3);
        infoPanel.add(occupied);
        
        optionsPanel = new JPanel();
        optionsPanel.setLocation(990, 552);
        optionsPanel.setSize(610, 300);
        optionsPanel.setBackground(myPurple);
        optionsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        optionLabel = new JLabel("Manager Options:");
        optionLabel.setFont(new Font("Candara", Font.PLAIN, 32));
        optionsPanel.add(optionLabel);
        optionsPanel.add(assignButton);
        optionsPanel.add(editButton);
        
        
        
        mainPanel.add(assignPanel);
        mainPanel.add(menuPanel);
        mainPanel.add(tablePanel);
        mainPanel.add(addPanel);
        mainPanel.add(infoPanel);
        mainPanel.add(editPanel);
        mainPanel.add(deletePanel);
        mainPanel.add(optionsPanel);
        mainPanel.add(clockPanel);
        mainPanel.add(tableOptionsPanel);
        mainPanel.add(tableViewPanel);
        
    
       tablePanel.add(table10Panel);
        tablePanel.add(table11Panel);
        tablePanel.add(table12Panel);
        tablePanel.add(table13Panel);
        tablePanel.add(table14Panel);
        tablePanel.add(table15Panel);
        tablePanel.add(table16Panel);
        tablePanel.add(table17Panel);
        tablePanel.add(table18Panel);
        
        
         background = ImageIO.read(this.getClass().getResource("tiles.png"));
         BG = new ImageIcon(background);
         JLabel back = new JLabel(BG);
         back.setBounds(4, 4, 992, 844);
         tablePanel.add(back);
        //table10.setLocation(0, 0); 
    }
    
    public void makeTableButton(JButton B){
        
        B.setBackground(myRed);
        B.setFocusPainted(false);
        Border thickBorder = new LineBorder(Color.BLACK, 6);
        B.setFont(new Font("Courier", Font.BOLD, 20));
        B.setPreferredSize(new Dimension(160, 200));
        B.setBorder(thickBorder);
        B.addActionListener(new GUIListener());
    }
    
     public void makeOptionButton(JButton B){
        B.setBackground(Color.YELLOW);
        
        Border thickBorder = new LineBorder(Color.BLACK, 3);
        B.setFont(new Font("Candara", Font.BOLD, 32));
        B.setPreferredSize(new Dimension(500, 111));
        B.setBorder(thickBorder);
        B.addActionListener(new GUIListener());
        B.setHorizontalTextPosition(JButton.CENTER);
        B.setVerticalTextPosition(JButton.CENTER);
    }
    
    public void setTableLocations(){
         
        table10Panel = new JPanel();
        table10Panel.setOpaque(false);
        table10Panel.setLocation(90, 55);
        table10Panel.setSize(190, 260);
        table10Panel.add(table10);
        table10Panel.add(cB10);
        setIcons(cB10);
       
        
        table11Panel = new JPanel();
        table11Panel.setOpaque(false);
        table11Panel.setLocation(400, 55);
        table11Panel.setSize(190, 260);
        table11Panel.add(table11);
        table11Panel.add(cB11);
        setIcons(cB11);
        
        table12Panel = new JPanel();
        table12Panel.setOpaque(false);
        table12Panel.setLocation(700, 55);
        table12Panel.setSize(190, 260);
        table12Panel.add(table12);
        table12Panel.add(cB12);
        setIcons(cB12);
        
        table13Panel = new JPanel();
        table13Panel.setOpaque(false);
        table13Panel.setLocation(90, 315);
        table13Panel.setSize(190, 260);
        table13Panel.add(table13);
        table13Panel.add(cB13);
        setIcons(cB13);
        
        table14Panel = new JPanel();
        table14Panel.setOpaque(false);
        table14Panel.setLocation(400, 315);
        table14Panel.setSize(190, 260);
        table14Panel.add(table14);
        table14Panel.add(cB14);
        setIcons(cB14);
        
        table15Panel = new JPanel();        
        table15Panel.setOpaque(false);
        //table15Panel.setBackground(Color.orange);
        table15Panel.setLocation(700, 315);
        table15Panel.setSize(190, 260);
        table15Panel.add(table15);
        table15Panel.add(cB15);
        setIcons(cB15);
        
        table16Panel = new JPanel();
        table16Panel.setOpaque(false);
        table16Panel.setLocation(90, 575);
        table16Panel.setSize(190, 260);
        table16Panel.add(table16);
        table16Panel.add(cB16);
        setIcons(cB16);
        
        table17Panel = new JPanel();
        table17Panel.setOpaque(false);
        table17Panel.setLocation(400, 575);
        table17Panel.setSize(190, 260);
        table17Panel.add(table17);
        table17Panel.add(cB17);
        setIcons(cB17);
        
        table18Panel = new JPanel();
        table18Panel.setOpaque(false);
        table18Panel.setLocation(700, 575);
        table18Panel.setSize(190, 260);
        table18Panel.add(table18);
        table18Panel.add(cB18);
        setIcons(cB18);
    }

   
    private class menuListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(foodMenu.getSelectedValue()!=null){
            String s = foodMenu.getSelectedValue().toString();
           String x = s.substring(0, s.indexOf("."));
           String y = s.substring(s.indexOf("$")+1);
           editJText.setText(x);
           editPriceJText.setText(y);
            }
        }
        
    }
    private class GUIListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          clockMethod();
          
          if(e.getSource()==assignButton){             
              assignTables();
          }else if(e.getSource()==editButton){
              tablePanel.setVisible(false);
              optionsPanel.setVisible(false);
              infoPanel.setVisible(false);
              menuPanel.setVisible(true);
              addPanel.setVisible(true);
              editPanel.setVisible(true);
              deletePanel.setVisible(true);
          }else if(e.getSource()==doneAssignButton){
              doneAssignMethod();
          }else if(e.getSource()==assignButton2){
              if(w1.isSelected()){
                  assign(waiter1);
              }
              if(w2.isSelected()){
                  assign(waiter2);
              }
              if(w3.isSelected()){
                  assign(waiter3);
              }
              if(noWaiter.isSelected()){
                  assign(waiter4);
              }
          } else if(e.getSource()== doneEditButton){
              tablePanel.setVisible(true);
              optionsPanel.setVisible(true);
              infoPanel.setVisible(true);
              menuPanel.setVisible(false);
              addPanel.setVisible(false);
              editPanel.setVisible(false);
              deletePanel.setVisible(false);
          }else if(e.getSource()==editItemButton){
             int index;
             index =foodMenu.getSelectedIndex();           
             foodModel.setElementAt(editJText.getText().toString() + ".........................................$"
                     + editPriceJText.getText().toString(), index);
          }
          else if(e.getSource()==addItemButton){
             addToMenu();
          }
          else if(e.getSource()==deleteItemButton){
             int index;
             index =foodMenu.getSelectedIndex();    
              foodModel.removeElementAt(index);
              foodMenu.setSelectedIndex(-1);
              editJText.setText(null);
              editPriceJText.setText(null);
          }//************************************table buttons start here*****************
          else if(e.getSource()==table10){
              selectTable(0, table10);
          }
          else if(e.getSource()==table11){
              selectTable(1, table11);
          }
          else if(e.getSource()==table12){
              selectTable(2, table12);
          }
          else if(e.getSource()==table13){
              selectTable(3, table13);
          }
          else if(e.getSource()==table14){
              selectTable(4, table14);
          }
          else if(e.getSource()==table15){
              selectTable(5, table15);
          }
          else if(e.getSource()==table16){
              selectTable(6, table16);
          }
          else if(e.getSource()==table17){
              selectTable(7, table17);
          }
          else if(e.getSource()==table18){
              selectTable(8, table18);
          }//******************************************************************************
          else if(e.getSource()==doneTableButton){
             tableModel.removeAllElements();
             tableModel.addElement("\n");
             checkTableEmpty();
            tablePanel.setVisible(true);
            optionsPanel.setVisible(true);
            infoPanel.setVisible(true);
            tableOptionsPanel.setVisible(false);
            tableViewPanel.setVisible(false);
          }
          else if(e.getSource()==seats){
              int seatNum = seats.getSelectedIndex();
              tableModel.removeAllElements();
              String s = tableInfoArray[currentTable][seatNum].toString();             
              Scanner scanner = new Scanner(s);
                while (scanner.hasNextLine()) {
                 String line = scanner.nextLine();
                // process the line
                 tableModel.addElement(line);
                }
                scanner.close();
              //tableModel.addElement(tableInfoArray[currentTable][seatNum]);
          }else if(e.getSource()==closeTableButton){
              closeTable(currentTable);
             //tableModel.removeAllElements();
             //tableModel.addElement("\n");
             checkTableEmpty();
            tablePanel.setVisible(true);
            optionsPanel.setVisible(true);
            infoPanel.setVisible(true);
            tableOptionsPanel.setVisible(false);
            tableViewPanel.setVisible(false);
          }else if(e.getSource()==deleteTableItemButton){
            deleteFromOrder();        
          }
          else if(e.getSource()==addTableItemButton){
              addToOrder();
          }
          else if(e.getSource()==doneTableButton2){
              checkTableEmpty();
              tableOptionsPanel.setVisible(true);        
                mainPanel.remove(addToOrderPanel);
                // mainPanel.add(menuPanel);
                //addToOrderPanel.setVisible(false);
          } else if(e.getSource()==addTableItemButton2){
              addSelectedToOrder();
          }
        }       
    }
    
    public void assign(String waiter){
         if(cB10.isSelected()){ 
             String sub = table10.getText().substring(6, 14);            
            table10.setText("<html>" +sub+ "<br />"+ waiter +"</html>");
            cB10.setSelected(false);
                  }
          if(cB11.isSelected()){          
            String sub = table11.getText().substring(6, 14);
            table11.setText("<html>" +sub+ "<br />"+ waiter +"</html>");
            cB11.setSelected(false);
                  }
          if(cB12.isSelected()){          
            String sub = table12.getText().substring(6, 14);
            table12.setText("<html>" +sub+ "<br />"+ waiter +"</html>");
            cB12.setSelected(false);
                  }
          if(cB13.isSelected()){          
           String sub = table13.getText().substring(6, 14);
            table13.setText("<html>" +sub+ "<br />"+ waiter +"</html>");
            cB13.setSelected(false);
                  }
          if(cB14.isSelected()){          
            String sub = table14.getText().substring(6, 14);
            table14.setText("<html>" +sub+ "<br />"+ waiter +"</html>");
            cB14.setSelected(false);
                  }
          if(cB15.isSelected()){          
            String sub = table15.getText().substring(6, 14);
            table15.setText("<html>" +sub+ "<br />"+ waiter +"</html>");
            cB15.setSelected(false);
                  }
         if(cB16.isSelected()){          
            String sub = table16.getText().substring(6, 14);
            table16.setText("<html>" +sub+ "<br />"+ waiter +"</html>");
            cB16.setSelected(false);
                  }
         if(cB17.isSelected()){          
           String sub = table17.getText().substring(6, 14);
            table17.setText("<html>" +sub+ "<br />"+ waiter +"</html>");
            cB17.setSelected(false);
                  }
         if(cB18.isSelected()){          
            String sub = table18.getText().substring(6, 14);
            table18.setText("<html>" +sub+ "<br />"+ waiter +"</html>");
            cB18.setSelected(false);
                  }
         
    }
    
    public void doneAssignMethod(){
                optionsPanel.setVisible(true);
               infoPanel.setVisible(true);
               selectTablePanel.setVisible(true);
               assignPanel.setVisible(false);
               table10.setEnabled(true);
               table11.setEnabled(true);
                table12.setEnabled(true);
                table13.setEnabled(true);
                table14.setEnabled(true);
                table15.setEnabled(true);
                table16.setEnabled(true);
                table17.setEnabled(true);
                table18.setEnabled(true);              
               cB10.setVisible(false);
               cB11.setVisible(false);
               cB12.setVisible(false);
               cB13.setVisible(false);
               cB14.setVisible(false);
               cB15.setVisible(false);
               cB16.setVisible(false);
               cB17.setVisible(false);
               cB18.setVisible(false);
               cB10.setSelected(false);
               cB11.setSelected(false);
               cB12.setSelected(false);
               cB13.setSelected(false);
               cB14.setSelected(false);
               cB15.setSelected(false);
               cB16.setSelected(false);
               cB17.setSelected(false);
               cB18.setSelected(false);
    }
    
    
    public void clockMethod(){
         Calendar now = Calendar.getInstance();
            String theDay = "";
            int day = now.get(Calendar.DAY_OF_WEEK);
            int month= now.get(Calendar.MONTH);
            int numDay = now.get(Calendar.DAY_OF_MONTH);
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int min = now.get(Calendar.MINUTE);
            int sec = now.get(Calendar.SECOND);

            switch (day) {
                case 1:
                    theDay = "Sunday";
                    break;
                case 2:
                    theDay = "Monday";
                    break;
                case 3:
                    theDay = "Tuesday";
                    break;
                case 4:
                    theDay = "Weds.";
                    break;
                case 5:
                    theDay = "Thursday";
                    break;
                case 6:
                    theDay = "Friday";
                    break;
                case 7:
                    theDay = "Saturday";
                    break;
            }
             if(sec<10){
                clockJText.setText(month +"/" +numDay + " " + theDay + " " + hour 
                    + ":" + min + ":0" + sec);
            }
            
            if(min <10){
                clockJText.setText(month +"/" +numDay + " " + theDay + " " + hour 
                    + ":0" + min + ":" + sec);
            }
             if (min <10 && sec <10){
                clockJText.setText(month +"/" +numDay + " " + theDay + " " + hour 
                    + ":0" + min + ":0" + sec);
            }
              if (min <10 && sec >10){
                clockJText.setText(month +"/" +numDay + " " + theDay + " " + hour 
                    + ":0" + min + ":" + sec);
            }
             if (min >10 && sec <10){
                clockJText.setText(month +"/" +numDay + " " + theDay + " " + hour 
                    + ":" + min + ":0" + sec);
            } 
            if (min >10 && sec >10){
                clockJText.setText(month +"/" +numDay + " " + theDay + " " + hour 
                    + ":" + min + ":" + sec);
            }
             
            
            
            
    }
    
    public void assignTables(){       
       optionsPanel.setVisible(false);
       infoPanel.setVisible(false);
       selectTablePanel.setVisible(false);
       assignPanel.setVisible(true);
       table10.setEnabled(false);
      
       table11.setEnabled(false);
       table12.setEnabled(false);
       table13.setEnabled(false);
       table14.setEnabled(false);
       table15.setEnabled(false);
       table16.setEnabled(false);
       table17.setEnabled(false);
       table18.setEnabled(false);
       cB10.setVisible(true);
       cB11.setVisible(true);
       cB12.setVisible(true);
       cB13.setVisible(true);
       cB14.setVisible(true);
       cB15.setVisible(true);
       cB16.setVisible(true);
       cB17.setVisible(true);
       cB18.setVisible(true);
    }
    
    public void assignTablesPanel(){
      
       w1 = new JRadioButton(" Waiter #1", emptyIcon);
        w2 = new JRadioButton(" Waiter #2", emptyIcon );
        w3 = new JRadioButton(" Waiter #3", emptyIcon);
        noWaiter = new JRadioButton("No Waiter", emptyIcon);
        w1.setSelectedIcon(selectedIcon);
        w2.setSelectedIcon(selectedIcon);
        w3.setSelectedIcon(selectedIcon);
        noWaiter.setSelectedIcon(selectedIcon);
        w1.setFont(new Font("Candara", Font.PLAIN, 48));
        w2.setFont(new Font("Candara", Font.PLAIN, 48));
        w3.setFont(new Font("Candara", Font.PLAIN, 48));
        noWaiter.setFont(new Font("Candara", Font.PLAIN, 48));
        w1.setBackground(myPurple);
        w2.setBackground(myPurple);
        w3.setBackground(myPurple);
        noWaiter.setBackground(myPurple);
        w1.setBorder(null);
        w2.setBorder(null);
        w3.setBorder(null);
        noWaiter.setBorder(null);
        
        
       
        bg1 = new ButtonGroup();
        bg1.add(w1);
        bg1.add(w2);
        bg1.add(w3);
        bg1.add(noWaiter);
        selectWaiterLabel = new JLabel("<html><u>Select Waiter:</u><br></html>");
        selectWaiterLabel.setFont(new Font("Candara", Font.PLAIN, 60));
        selectWaiterLabel.setLocation(30, 50);
        
        assignPanel = new JPanel();
        JPanel radioPanel = new JPanel();
        JPanel assignPanel2 = new JPanel();
        
        assignPanel.setLayout(null);
        assignPanel.setLocation(1000, 75);
        assignPanel.setSize(595, 773);
        radioPanel.setLocation(100, 100);
        radioPanel.setSize(400, 350);
        radioPanel.setBackground(myPurple);
        assignPanel.setBackground(myPurple);
        assignPanel2.setLocation(20, 525);
        assignPanel2.setSize(550, 240);
        assignPanel2.setBackground(myPurple); 
        
        radioPanel.add(selectWaiterLabel);
        radioPanel.add(w1);
        radioPanel.add(w2);
        radioPanel.add(w3);
        radioPanel.add(noWaiter);
        assignPanel.add(radioPanel);      
        assignButton2 = new JButton("Assign",buttonIcon);
        doneAssignButton = new JButton("Done",buttonIcon);       
        makeOptionButton(assignButton2);
        makeOptionButton(doneAssignButton);
        assignPanel2.add(assignButton2);
        assignPanel2.add(doneAssignButton);
        
        assignPanel.add(assignPanel2);
        assignPanel.setVisible(false);
    }
    
    public void buildClockPanel(){
         //clock methodd******************************//
        clockJText = new JTextField(14);
        clockJText.setEditable(false);
        clockJText.setFont(new Font("Candara", Font.PLAIN, 50));
        clockJText.setBackground(Color.ORANGE);
        clockJText.setHorizontalAlignment(JTextField.CENTER);
        //clockJText.setLocation(10, 10);
        //clockJText.setSize(600,74);
        clockJText.setBorder(null);
        clockPanel= new JPanel();
        
        clockPanel.setLocation(990,0);
        clockPanel.setSize(610,75);
        clockPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        clockPanel.setBackground(Color.orange);
        clockPanel.add(clockJText);
        Timer t = new Timer(1000, new GUIListener());
        t.start();
        //************************************//
        
    }
    
    public void createLargeIcons() throws IOException{
         //**************creates empty icon**********************
      BufferedImage img = new BufferedImage(BI_WIDTH, BI_WIDTH, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = img.createGraphics();
      g2.setStroke(new BasicStroke(4f));
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      int x = 4;
      int y = x;
      int width = BI_WIDTH - 2 * x;
      int height = width;
      g2.setColor(Color.white);
      g2.fillOval(x, y, width, height);
      g2.setColor(Color.black);
      g2.drawOval(x, y, width, height);
      g2.dispose();
      emptyIcon = new ImageIcon(img);
      //******************creates red filled icon
      img = new BufferedImage(BI_WIDTH, BI_WIDTH, BufferedImage.TYPE_INT_ARGB);
      g2 = img.createGraphics();
      g2.setStroke(new BasicStroke(4f));
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setColor(myRed);
      g2.fillOval(x, y, width, height);
      g2.setColor(Color.black);
      g2.drawOval(x, y, width, height);
      g2.dispose();
      selectedIcon = new ImageIcon(img);      
      //****************************************************************************************
       img = new BufferedImage(BI_WIDTH, BI_WIDTH, BufferedImage.TYPE_INT_ARGB);
      g2 = img.createGraphics();
      g2.setStroke(new BasicStroke(4f));
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setColor(Color.MAGENTA.darker());
      g2.fillOval(x, y, width, height);
      g2.setColor(Color.black);
      g2.drawOval(x, y, width, height);
      g2.dispose();
      emptyTableIcon = new ImageIcon(img); 
      /*****************************************/
        BufferedImage img2 = ImageIO.read(this.getClass().getResource("img.png"));
     
      buttonIcon = new ImageIcon(img2); 
    }
    
    public void setIcons(JCheckBox b){
        b.setBackground(Color.orange);
        b.setSelectedIcon(selectedIcon);
        b.setVisible(false);
    }
    
    public void buildEditGUI(){
       
       
        addPanel= new JPanel();
        addPanel.setLocation(990, 70);
        addPanel.setSize(610, 275);
        addPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        addPanel.setBackground(myPurple);
        addPanel.setVisible(false);
        addItemButton=new JButton("Add", buttonIcon);
        addItemButton.setHorizontalTextPosition(JButton.CENTER);
        addItemButton.setVerticalTextPosition(JButton.CENTER);
        addItemButton.setBackground(Color.YELLOW);
        Border thinBorder = new LineBorder(Color.BLACK, 3);
        addItemButton.setFont(new Font("Candara", Font.BOLD, 32));
        addItemButton.setPreferredSize(new Dimension(300, 60));
        addItemButton.setBorder(thinBorder);
        addItemButton.addActionListener(new GUIListener());
        addLabel = new JLabel("<html>\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Add Item:<html>\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0");
        addNameLabel = new JLabel("\0\0\0Name: ");
        addPriceLabel =new JLabel("\0\0\0\0\0Price: ");
        addLabel.setFont(new Font("Candara", Font.PLAIN, 48)); 
        addNameLabel.setFont(new Font("Candara", Font.PLAIN, 32));
        addPriceLabel.setFont(new Font("Candara", Font.PLAIN, 32));        
        addPriceJText = new JTextField(12);
        addJText = new JTextField(12);
        addJText.setPreferredSize( new Dimension( 200, 60 ) );
        addJText.setFont(new Font("Candara", Font.PLAIN, 40));
        addPriceJText.setPreferredSize( new Dimension( 200, 60 ) );
        addPriceJText.setFont(new Font("Candara", Font.PLAIN, 40));
        addPanel.add(addLabel);
        addPanel.add(addNameLabel);
        addPanel.add(addJText);
        addPanel.add(addPriceLabel);
        addPanel.add(addPriceJText);
        addPanel.add(addItemButton);
        
       
        
        editPanel= new JPanel();
        editPanel.setLocation(990, 340);
        editPanel.setSize(610, 275);
        editPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        editPanel.setBackground(myPurple);
        editPanel.setVisible(false);
        editItemButton=new JButton("Edit", buttonIcon);
        editItemButton.setHorizontalTextPosition(JButton.CENTER);
        editItemButton.setVerticalTextPosition(JButton.CENTER);
        editItemButton.setBackground(Color.YELLOW);
       
        editItemButton.setFont(new Font("Candara", Font.BOLD, 32));
        editItemButton.setPreferredSize(new Dimension(300, 60));
        editItemButton.setBorder(thinBorder);
        editItemButton.addActionListener(new GUIListener());
        editLabel = new JLabel("<html>\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Edit Item:<html>\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0");
        editNameLabel = new JLabel("\0\0\0Name: ");
        editPriceLabel =new JLabel("\0\0\0\0\0Price: ");
        editLabel.setFont(new Font("Candara", Font.PLAIN, 48)); 
        editNameLabel.setFont(new Font("Candara", Font.PLAIN, 32));
        editPriceLabel.setFont(new Font("Candara", Font.PLAIN, 32));        
        editPriceJText = new JTextField(12);
        editJText = new JTextField(12);
        editJText.setPreferredSize( new Dimension( 200, 60 ) );
        editJText.setFont(new Font("Candara", Font.PLAIN, 40));
        editPriceJText.setPreferredSize( new Dimension( 200, 60 ));
        editPriceJText.setFont(new Font("Candara", Font.PLAIN, 40));
        editPanel.add(editLabel);
        editPanel.add(editNameLabel);
        editPanel.add(editJText);
        editPanel.add(editPriceLabel);
        editPanel.add(editPriceJText);
        editPanel.add(editItemButton);
        
        deletePanel= new JPanel();
        deletePanel.setLocation(990, 610);
        deletePanel.setSize(610, 242);
        deletePanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        deletePanel.setBackground(myPurple);
        deletePanel.setVisible(false);
        deleteItemButton =new JButton("Delete", buttonIcon);
        deleteItemButton.setHorizontalTextPosition(JButton.CENTER);
        deleteItemButton.setVerticalTextPosition(JButton.CENTER);
        deleteItemButton.setBackground(Color.YELLOW);
        deleteItemButton.setFont(new Font("Candara", Font.BOLD, 32));
        deleteItemButton.setPreferredSize(new Dimension(300, 60));
        deleteItemButton.setBorder(thinBorder);
        deleteItemButton.addActionListener(new GUIListener());
        doneEditButton = new JButton("Done", buttonIcon);
        doneEditButton.setHorizontalTextPosition(JButton.CENTER);
        doneEditButton.setVerticalTextPosition(JButton.CENTER);
        doneEditButton.setBackground(Color.YELLOW);
        doneEditButton.setFont(new Font("Candara", Font.BOLD, 32));
        doneEditButton.setPreferredSize(new Dimension(300, 60));
        doneEditButton.setBorder(thinBorder);
        doneEditButton.addActionListener(new GUIListener());
        deleteLabel = new JLabel("Select Item to Delete: ");
        deleteLabel.setFont(new Font("Candara", Font.PLAIN, 42));
        deletePanel.add(deleteLabel);
        deletePanel.add(deleteItemButton);
        deletePanel.add(doneEditButton);
        
        menuPanel= new JPanel();
        menuPanel.setLocation(0, 0);
        menuPanel.setSize(1000, 852);
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        menuPanel.setBackground(myPurple);
        menuPanel.setVisible(false);        
        menuLabel = new JLabel("Menu: \0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0 ");
        menuLabel.setFont(new Font("Candara", Font.PLAIN, 42));
        foodModel = new DefaultListModel();
        
        foodModel.addElement("Water........................................$0.00\n");
        foodModel.addElement("Pepsi........................................$1.99\n");
        foodModel.addElement("Sprite........................................$1.99\n");
        foodModel.addElement("Burger........................................$9.99\n");
        foodModel.addElement("Cheese Pizza..............................$9.99\n");
        foodModel.addElement("Pepperoni Pizza........................$10.99\n");
        foodModel.addElement("Caeser Salad.................................$6.99\n");
        foodModel.addElement("Hot Dog........................................$4.99\n");
        foodModel.addElement("Cheeseburger...................................$10.99\n");
        foodModel.addElement("Nachos........................................$9.99\n");
        foodModel.addElement("Steak........................................$15.99\n");
        foodModel.addElement("Cheese Fries...................................$9.99\n");
        foodModel.addElement("Apple Pie....................................$4.99\n");
        foodModel.addElement("Cheese Cake................................$4.99\n");
        
        
        
        foodMenu = new JList(foodModel);
        
        foodMenu.setLocation(50, 100);
       foodMenu.setSize(850, 900);
        //foodMenu.setPreferredSize(new Dimension(850, 900));
        foodMenu.setVisibleRowCount(13);
        
        
        foodMenu.setFont(new Font("Courier", Font.PLAIN, 40));
        foodMenu.setBackground(Color.orange);
        //foodMenu.setForeground(Color.blue);
        
        foodMenu.setSelectionBackground(Color.red);
        foodMenu.addListSelectionListener(new menuListener());
        menuPanel.add(menuLabel);
        menuPanel.add(foodMenu);

        menuScroll = new JScrollPane(foodMenu);
        menuPanel.add(menuScroll);
        menuScroll.setBounds(50, 100, 850, 900);
        
      // menuScroll.getViewport().setViewPosition(new Point(50,50));
        menuScroll.setBackground(myPurple);
       
    }
    
    public void selectTable(int index, JButton b){
        currentTable = index;
       seats.setSelectedIndex(0);
        String s =b.getText().toString();
        String y = s.substring(s.indexOf("Ta"), s.indexOf("</"));
        y=y+"\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";       
        if(s.contains("Waiter")){
            String x=s;
            y=x.substring(0, x.indexOf("<br")) +": "+ x.substring(x.indexOf("W"));
        }
        tableModel.removeAllElements();
         String s2 = tableInfoArray[currentTable][0].toString();
              Scanner scanner = new Scanner(s2);
                while (scanner.hasNextLine()) {
                 String line = scanner.nextLine();
                // process the line
                 tableModel.addElement(line);
                }
                scanner.close();
        tablename.setText(y);
        tablePanel.setVisible(false);
        optionsPanel.setVisible(false);
        infoPanel.setVisible(false);
        tableOptionsPanel.setVisible(true);
        tableViewPanel.setVisible(true);
        
    }
    public void buildTableViewGUI(){
        tablename = new JLabel("testttt");
        tablename.setFont(new Font("Candara", Font.PLAIN, 34));
         tableViewPanel = new JPanel();
         tableViewPanel = new JPanel();
        //tablePanel.setLayout(null);        
        tableViewPanel.setLocation(0, 0);
        //final int PANELWIDTH = (int) (Math.floor((jFrame.getWidth())/2));
        tableViewPanel.setSize(992, 852);
        tableViewPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        tableViewPanel.setBackground(myPurple);
        tableViewPanel.setVisible(false);
        tableViewPanel.add(tablename);
        
        tableOptionsPanel = new JPanel(new FlowLayout(0 ,50, 65));
        tableOptionsPanel.setLocation(990, 75);
        tableOptionsPanel.setSize(605, 773);
        tableOptionsPanel.setBackground(myPurple);
        tableOptionsPanel.setVisible(false);
        addTableItemButton = new JButton("Add Item", buttonIcon);
        deleteTableItemButton = new JButton("Delete Item", buttonIcon);
        closeTableButton = new JButton("Close Table", buttonIcon);
        doneTableButton = new JButton("Done", buttonIcon);
        makeOptionButton(addTableItemButton);
        makeOptionButton(deleteTableItemButton);
        makeOptionButton(closeTableButton);
        makeOptionButton(doneTableButton);        
        tableOptionsPanel.add(addTableItemButton);     
        tableOptionsPanel.add(deleteTableItemButton);       
        tableOptionsPanel.add(closeTableButton);       
        tableOptionsPanel.add(doneTableButton);
        
        tableModel = new DefaultListModel();
        tableModel.addElement("\n");

        tableOrder = new JList(tableModel);
        tableOrder.setLocation(50, 100);
        //foodMenu.setSize(650, 600);
        tableOrder.setPreferredSize(new Dimension(850, 500));
        tableOrder.setVisibleRowCount(10);
        
        tableOrder.setFont(foodMenu.getFont().deriveFont(48.0f));
        tableOrder.setBackground(Color.orange);
        //foodMenu.setForeground(Color.blue);
        
        tableOrder.setSelectionBackground(Color.red);
        tableOrder.addListSelectionListener(new menuListener());
        tableViewPanel.add(tableOrder);
        orderScroll = new JScrollPane(tableOrder);
        tableViewPanel.add(orderScroll);
        orderScroll.setBounds(50, 100, 650, 420);
        orderScroll.setBackground(myPurple);
        
         seats = new JComboBox<String>(seatArray);
         seats.setFont(new Font("Candara", Font.PLAIN, 48));
         seats.setPreferredSize(new Dimension(300, 70));
         seats.setBackground(Color.yellow);
         seats.addActionListener(new GUIListener());
        tableViewPanel.add(seats);
        /**
       // Create data for the tree
		DefaultMutableTreeNode root = new DefaultMutableTreeNode( "Table" );
              DefaultMutableTreeNode itemClubs = new DefaultMutableTreeNode( "Seat 1" );
                
		addTreeItem( itemClubs );
		root.add( itemClubs );

		DefaultMutableTreeNode itemDiamonds
					= new DefaultMutableTreeNode( "Fries" );
		//addAllCard( itemDiamonds );
		root.add( itemDiamonds );

		DefaultMutableTreeNode itemSpades
					= new DefaultMutableTreeNode( "Salad" );
		//addAllCard( itemSpades );
		root.add( itemSpades );

		DefaultMutableTreeNode itemHearts
					= new DefaultMutableTreeNode( "ChX" );
		//addAllCard( itemHearts );
		root.add( itemHearts );

		// Create a new tree control
		DefaultTreeModel treeModel = new DefaultTreeModel( root );
                UIManager.put("Tree.rendererFillBackground", false);
		tableTree = new JTree( treeModel );
                tableTree.setFont(new Font("Candara", Font.PLAIN, 64));
                tableTree.setPreferredSize(new Dimension(800, 500));
                tableTree.setBorder(BorderFactory.createLineBorder(Color.black, 4));
                tableTree.setBackground(Color.yellow);


		// Add the listbox to a scrolling pane
		orderScroll = new JScrollPane();
		orderScroll.add( tableTree);
		tableViewPanel.add(tableTree);
                tableViewPanel.add(orderScroll);
                orderScroll.setBounds(50, 100, 650, 420);
                orderScroll.setBackground(myOrange);
               **/ 
    }
    
    public void addTreeItem(DefaultMutableTreeNode s){
        s.add( new DefaultMutableTreeNode( "Burger" ) );
    }
    
    public void makeArray(){
        tableInfoArray = new String[9][5];
        tableInfoArray[0][0] = "Burger..........................................$9.99\nPepsi "
                + "..........................................$1.99\nSalad..........................................$4.99\n";
        tableInfoArray[0][1] = "Pizza..........................................$9.99\nCoke "
                + "..........................................$1.99\nApple Pie....................................$4.99\n";
        tableInfoArray[0][2] = "Pasta..........................................$9.99\nPepsi "
                + "..........................................$1.99\nSalad..........................................$4.99\n";
        tableInfoArray[0][3] = "Hot Dog....................................$9.99\nPepsi "
                + "..........................................$1.99\nSalad..........................................$4.99\n";
        tableInfoArray[0][4] = "(none)";
        
        tableInfoArray[1][0] = "Mac&CHZ....................................$9.99\nCoke "
                + "..........................................$1.99\nSalad..........................................$4.99\n";
        tableInfoArray[1][1] = "(none)";
        tableInfoArray[1][2] = "Steak..........................................$15.99\nPepsi "
                + "..........................................$1.99\nSalad..........................................$4.99\n";
        tableInfoArray[1][3] = "Steak..........................................$15.99\nPepsi "
                + "..........................................$1.99\nSalad..........................................$4.99\n";
        tableInfoArray[1][4] ="(none)"; 
        tableInfoArray[2][0] = "(none)";
        tableInfoArray[2][1] = "Burger..........................................$9.99\nPepsi "
                + "..........................................$1.99\nSalad..........................................$4.99\n";
        tableInfoArray[2][2] = "Burger..........................................$9.99\nPepsi "
                + "..........................................$1.99\nSalad..........................................$4.99\n";
        tableInfoArray[2][3] ="(none)";
        tableInfoArray[2][4] ="(none)";
        for(int i=3; i<9; i++){
            for(int j = 0; j <5;j++){
                tableInfoArray[i][j]="(none)";
            }
        }  
    }
    public void closeTable(int tableNum){       
            for(int j = 0; j <5;j++){
                tableInfoArray[tableNum][j]="(none)";
            }        
    }
    
    public void checkTableEmpty(){
          
        for(int i=0; i<9; i++){ 
            int count;
            count=0;
           for(int j=0; j<5;j++){
                if(tableInfoArray[i][j].contains("(none)")){
               count++;
                }
                if(count==5){
                switch(i){
                    case 0:
                        table10.setBackground(Color.MAGENTA.darker());
                        break;
                    case 1:
                        table11.setBackground(Color.MAGENTA.darker());
                        break;
                    case 2:
                        table12.setBackground(Color.MAGENTA.darker());
                        break;
                    case 3:
                        table13.setBackground(Color.MAGENTA.darker());
                        break;
                    case 4:
                        table14.setBackground(Color.MAGENTA.darker());
                        break;
                    case 5:
                        table15.setBackground(Color.MAGENTA.darker());
                        break;
                    case 6:
                        table16.setBackground(Color.MAGENTA.darker());
                        break;
                    case 7:
                        table17.setBackground(Color.MAGENTA.darker());
                        break;
                    case 8:
                        table18.setBackground(Color.MAGENTA.darker());
                        break;
                }
           }              
            }                
            }
        
        for(int i=0; i<9; i++){ 
            int count;
            count=0;
           for(int j=0; j<5;j++){
                if(!tableInfoArray[i][j].contains("(none)")){
               count++;
                }
                if(count>=1){
                switch(i){
                    case 0:
                        table10.setBackground(myRed);
                        break;
                    case 1:
                        table11.setBackground(myRed);
                        break;
                    case 2:
                        table12.setBackground(myRed);
                        break;
                    case 3:
                        table13.setBackground(myRed);
                        break;
                    case 4:
                        table14.setBackground(myRed);
                        break;
                    case 5:
                        table15.setBackground(myRed);
                        break;
                    case 6:
                        table16.setBackground(myRed);
                        break;
                    case 7:
                        table17.setBackground(myRed);
                        break;
                    case 8:
                        table18.setBackground(myRed);
                        break;
                }
           }              
            }                
            }
    }
    
    public void deleteFromOrder(){
          int tableIndex, seatIndex;
              String s, s2, s3;             
              seatIndex=seats.getSelectedIndex();
              tableIndex =tableOrder.getSelectedIndex();              
              s = tableOrder.getSelectedValue().toString();
              s2 =tableInfoArray[currentTable][seatIndex];              
              tableModel.removeElementAt(tableIndex);                          
              s3 =s2.replace(s, "");              
              tableInfoArray[currentTable][seatIndex] = s3;
              tableOrder.setSelectedIndex(-1);
              if(tableModel.isEmpty()){
                 tableInfoArray[currentTable][seatIndex] = "(none)";                  
              }  
    }
    public void addToMenu(){
         String s = addJText.getText().toString();
              String x = addPriceJText.getText().toString();
              String dotLength="";
              int dots, name, price;
              name=s.length();
              price=x.length();
              dots= 30-(name+price);
              
              for(int i=0; i<dots;i++){
                  if(dotLength.length()<50){
                  dotLength=dotLength+".";
                  }
              }
              int c = foodMenu.getSelectedIndex();
              if(c<0){
                 foodModel.addElement( s +dotLength+ "$" + x+"\n"); 
              }else{
              foodModel.add(c, s + dotLength+ "$" + x+"\n");}
              //foodModel.addElement(s + " --------------------------------- $" + x);
              addJText.setText(null);
              addPriceJText.setText(null);
    }
    
    public void addToOrder(){
         tableOptionsPanel.setVisible(false);
        
         //mainPanel.remove(menuPanel);
          addToOrderGUI();
         mainPanel.add(addToOrderPanel);
         addToOrderPanel.setVisible(true);
    }
    
    public void addToOrderGUI(){
        addToOrderPanel= new JPanel((new FlowLayout(0 ,50, 25)));
        addToOrderPanel.setLocation(990, 70);
        addToOrderPanel.setSize(610, 782);
        addToOrderPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        addToOrderPanel.setBackground(myPurple);
        addToOrderPanel.setVisible(false);
        addTableItemButton2=new JButton("Add Item", buttonIcon);
        addTableItemButton2.setBackground(Color.YELLOW);
        Border thinBorder = new LineBorder(Color.BLACK, 3);       
        addTableItemButton2.setHorizontalTextPosition(JButton.CENTER);
        addTableItemButton2.setVerticalTextPosition(JButton.CENTER);
       
        addTableItemButton2.setFont(new Font("Candara", Font.BOLD, 32));
        addTableItemButton2.setPreferredSize(new Dimension(300, 60));
        addTableItemButton2.setBorder(thinBorder);
        addTableItemButton2.addActionListener(new GUIListener());
        
        doneTableButton2=new JButton("Done", buttonIcon);
        doneTableButton2.setBackground(Color.YELLOW);
        doneTableButton2.setHorizontalTextPosition(JButton.CENTER);
        doneTableButton2.setVerticalTextPosition(JButton.CENTER);
        
        doneTableButton2.setFont(new Font("Candara", Font.BOLD, 32));
        doneTableButton2.setPreferredSize(new Dimension(300, 60));
        doneTableButton2.setBorder(thinBorder);
        doneTableButton2.addActionListener(new GUIListener());
        addMenuLabel = new JLabel("<html>\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Menu:<html>\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0");        
        addMenuLabel.setFont(new Font("Candara", Font.PLAIN, 48)); 
        addToOrderPanel.add(addMenuLabel);
        foodModel2 = new DefaultListModel();
        for(int i=0;i<foodModel.getSize();i++ ){
            foodModel2.addElement(foodModel.getElementAt(i));
        }
         foodMenu2 = new JList(foodModel2);
        foodMenu2.setPreferredSize(new Dimension(500, 800));
        foodMenu2.setVisibleRowCount(12);
        
        foodMenu2.setFont(foodMenu2.getFont().deriveFont(24.0f));
        foodMenu2.setBackground(Color.orange);
        //foodMenu.setForeground(Color.blue);
        
        foodMenu2.setSelectionBackground(Color.red);
        addToOrderPanel.add(foodMenu2);

        menuScroll = new JScrollPane(foodMenu2);
        addToOrderPanel.add(menuScroll);
        addToOrderPanel.add(addTableItemButton2);
        addToOrderPanel.add(doneTableButton2);
        
    }
    public void addSelectedToOrder(){
        String s0 = foodMenu2.getSelectedValue().toString();
             if(tableModel.getElementAt(0).toString().contentEquals("(none)")){
                 tableModel.removeElementAt(0);
                 tableModel.addElement( s0 );
             }else{
              tableModel.addElement( s0 );
             }
               int tableIndex, seatIndex;
              String s, s2, s3;             
              seatIndex=seats.getSelectedIndex();
              s2 =tableInfoArray[currentTable][seatIndex];
              if(s2.contains("(none)")){
                 s3 = s2.replace("(none)", "");
                 tableInfoArray[currentTable][seatIndex] =s3+ s0;
              }else{
              tableInfoArray[currentTable][seatIndex] =s2+ s0;
              }
    }
 
   
}
