/* GUI class. The GUI has an options bar, the board, a text with the status
 * of the game or some instructions, and a solutions panel
 */
package puzzle2dpentamino;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class GUI extends JFrame{
    
    private Board Board;
    private Board[] Solutions;
    private final int[] SPEEDS = {0, 30, 70, 120, 300, 600, 3000};
    private int Rows = 3, Columns = 20;
    private int Speed = SPEEDS[0];
    
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        initSpeedButtons();
        initMessages();
        SolutionsPanel.add(SolutionPicker);
        
        add(TabbedPane);                //Adds TabbedPane to the frame
        GenerateBoard(Rows, Columns);   //Creates initial board
    }
    
    /**
     * Creates a new empty board
     */
    private void GenerateBoard(int rows, int columns){
        if (Board!=null){                   //A board already exists
            BoardPanel.remove(Board);       //Deletes previous board
        }   
        
        //Board configuration
        Board = new Board(rows, columns);       //Creates a new board
        BoardPanel.add(Board);                  //Adds the new board
        Rows = rows;  Columns = columns;        //Updates rows, columns
        Board.setSpeed(Speed);                  //Sets solving speed
        
        //Message configuration
        setUpMessages();
        
        //Solution picker configuration
        SolutionPicker.setSize(100, 30);            //SolutionPicker size
        SolutionPicker.setLocation((Board.getWidth()- SolutionPicker.getWidth())/2, 30);   //SolutionPicker location
        
        //BoardPanel and SolutionPanel configuration
        int width = Board.getWidth();           //New panel width
        int height = Board.getHeight() + Message1.getHeight()*3;    //New panel height
        BoardPanel.setSize(width, height);                          //BoardPanel new size
        SolutionsPanel.setSize(width, height);                      //SolutionPanel new size
        
        //TabbedPane configuration
        height += TabbedPane.getBoundsAt(0).height;     //TabbedPane new height
        TabbedPane.setSize(width, height);              //TabbedPane new size
        
        //Frame configuration
        height += getInsets().top + MenuBar.getBounds().height + getInsets().bottom*2;    //Frame new height
        setSize(width, height);                 //Sets frame's new size
        setLocationRelativeTo(null);            //Centers frame 
        setResizable(false);                    //Frame NOT resizable by the user
    }
    
    /**
     * Initializes speed radio buttons
     */
    private void initSpeedButtons(){
        ButtonGroup g = new ButtonGroup();                          //We add all the radiobuttons 
        for(int i=0; i<SpeedMenu.getMenuComponentCount(); i++){     //to the same group so only 
            g.add(SpeedMenu.getItem(i));                            //one can be selected 
        }
        Speed0.setSelected(true);       //Default speed
    }
    
    /**
     * Default status message setter
     */
    private void setUpMessages(){
        String s2 = "Block Squares or use Solve(Ctrl+S) to start";              //Updates the message    
        Message1.setText("");                                                   //Updates the status text
        Message2.setText(s2);                                                   //Updates the status text
        Message3.setText("");                                                   //Updates the status text
        
        Message1.setBounds(0, Board.getHeight()                       , Board.getWidth(), Message1.getHeight());      //Sets it's location 
        Message2.setBounds(0, Board.getHeight()+Message1.getHeight()  , Board.getWidth(), Message2.getHeight());      //Sets it's location
        Message3.setBounds(0, Board.getHeight()+Message1.getHeight()*2, Board.getWidth(), Message3.getHeight());      //Sets it's location
    }
    
    /**
     * Initializes status messages
     */
    private void initMessages(){
        JLabel[] array = {Message1, Message2, Message3};
        for (JLabel array1 : array) {
            array1.setSize(100, 30);
            array1.setAlignmentX(CENTER_ALIGNMENT);
            array1.setAlignmentY(CENTER_ALIGNMENT);
            BoardPanel.add(array1);
        }
     }
    
    /**
     * Updates solutions picker combobox with the solutions found
     */
    private void updateSolutions(){
        if(Solutions.length>0){
            for(int i=0; i<Solutions.length; i++){
                SolutionPicker.addItem("Solution " + i);
            }
        }
    }
    
    /**
     * Clears solutions picker combobox and erases solutions boards
     */
    private void resetSolutions(){
        Component[] aux = SolutionsPanel.getComponents();
        for (Component cmpnt : aux) {
            if(cmpnt instanceof puzzle2dpentamino.Board) {
                SolutionsPanel.remove(cmpnt);
            }
        }
        SolutionPicker.removeAllItems();
    }
    
    /**
     * Shows the desired solution
     * @param board 
     */
    private void showSolution(Board board){
        Component[] aux = SolutionsPanel.getComponents();       //Gets all the componenets
        for (Component cmpnt : aux) {                           //on the solution panel
            if(cmpnt instanceof puzzle2dpentamino.Board){       //if there was another 
                SolutionsPanel.remove(cmpnt);                   //solution being shown
            }                                                   //its deleted
        }

        board.setBounds(0, Message1.getHeight()*3, getWidth(), board.getHeight());      //places the solution
        SolutionsPanel.add(board);                                  //adds it to the solution panel
        repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabbedPane = new javax.swing.JTabbedPane();
        BoardPanel = new javax.swing.JPanel();
        SolutionsPanel = new javax.swing.JPanel();
        SolutionPicker = new javax.swing.JComboBox<>();
        Message1 = new javax.swing.JLabel();
        Message2 = new javax.swing.JLabel();
        Message3 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        ControlMenu = new javax.swing.JMenu();
        SolveOption = new javax.swing.JMenuItem();
        ResetOption = new javax.swing.JMenuItem();
        ExitOption = new javax.swing.JMenuItem();
        SpeedMenu = new javax.swing.JMenu();
        Speed0 = new javax.swing.JRadioButtonMenuItem();
        Speed1 = new javax.swing.JRadioButtonMenuItem();
        Speed2 = new javax.swing.JRadioButtonMenuItem();
        Speed3 = new javax.swing.JRadioButtonMenuItem();
        Speed4 = new javax.swing.JRadioButtonMenuItem();
        Speed5 = new javax.swing.JRadioButtonMenuItem();
        Speed6 = new javax.swing.JRadioButtonMenuItem();
        SizeMenu = new javax.swing.JMenu();
        BoardSize0 = new javax.swing.JMenuItem();
        BoardSize1 = new javax.swing.JMenuItem();
        BoardSize2 = new javax.swing.JMenuItem();
        BoardSize3 = new javax.swing.JMenuItem();

        TabbedPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TabbedPaneKeyReleased(evt);
            }
        });

        BoardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoardPanelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout BoardPanelLayout = new javax.swing.GroupLayout(BoardPanel);
        BoardPanel.setLayout(BoardPanelLayout);
        BoardPanelLayout.setHorizontalGroup(
            BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        BoardPanelLayout.setVerticalGroup(
            BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        TabbedPane.addTab("Board", BoardPanel);

        javax.swing.GroupLayout SolutionsPanelLayout = new javax.swing.GroupLayout(SolutionsPanel);
        SolutionsPanel.setLayout(SolutionsPanelLayout);
        SolutionsPanelLayout.setHorizontalGroup(
            SolutionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        SolutionsPanelLayout.setVerticalGroup(
            SolutionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        TabbedPane.addTab("Solutions", SolutionsPanel);

        SolutionPicker.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SolutionPickerItemStateChanged(evt);
            }
        });

        Message1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Message1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        Message2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Message2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        Message3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Message3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Puzzle2DPentominos");
        setSize(new java.awt.Dimension(1000, 1000));

        MenuBar.setSelectionModel(MenuBar.getSelectionModel());

        ControlMenu.setText("Control");

        SolveOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SolveOption.setText("Solve");
        SolveOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SolveOptionMouseReleased(evt);
            }
        });
        ControlMenu.add(SolveOption);

        ResetOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        ResetOption.setText("Reset");
        ResetOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ResetOptionMouseReleased(evt);
            }
        });
        ControlMenu.add(ResetOption);

        ExitOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        ExitOption.setText("Exit");
        ExitOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ExitOptionMouseReleased(evt);
            }
        });
        ControlMenu.add(ExitOption);

        MenuBar.add(ControlMenu);

        SpeedMenu.setText("Speed");

        Speed0.setText("Only solutions");
        Speed0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Speed0MouseReleased(evt);
            }
        });
        SpeedMenu.add(Speed0);

        Speed1.setText("Fastest");
        Speed1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Speed1MouseReleased(evt);
            }
        });
        SpeedMenu.add(Speed1);

        Speed2.setText("Faster");
        Speed2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Speed2MouseReleased(evt);
            }
        });
        SpeedMenu.add(Speed2);

        Speed3.setText("Normal");
        Speed3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Speed3MouseReleased(evt);
            }
        });
        SpeedMenu.add(Speed3);

        Speed4.setText("Slow");
        Speed4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Speed4MouseReleased(evt);
            }
        });
        SpeedMenu.add(Speed4);

        Speed5.setText("Slower");
        Speed5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Speed5MouseReleased(evt);
            }
        });
        SpeedMenu.add(Speed5);

        Speed6.setText("Slowest");
        Speed6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Speed6MouseReleased(evt);
            }
        });
        SpeedMenu.add(Speed6);

        MenuBar.add(SpeedMenu);

        SizeMenu.setText("Size");

        BoardSize0.setText("6 x 10");
        BoardSize0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoardSize0MouseReleased(evt);
            }
        });
        SizeMenu.add(BoardSize0);

        BoardSize1.setText("5 x 12");
        BoardSize1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoardSize1MouseReleased(evt);
            }
        });
        SizeMenu.add(BoardSize1);

        BoardSize2.setText("4 x 15");
        BoardSize2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoardSize2MouseReleased(evt);
            }
        });
        SizeMenu.add(BoardSize2);

        BoardSize3.setText("3 x 20");
        BoardSize3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoardSize3MouseReleased(evt);
            }
        });
        SizeMenu.add(BoardSize3);

        MenuBar.add(SizeMenu);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoardSize0MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize0MouseReleased
        evt.consume();          //Frees memory
        resetSolutions();
        GenerateBoard(6,10);    //New (6x10) board
    }//GEN-LAST:event_BoardSize0MouseReleased

    private void BoardSize1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize1MouseReleased
        evt.consume();          //Frees memory 
        resetSolutions();
        GenerateBoard(5,12);    //New (5x12) board
    }//GEN-LAST:event_BoardSize1MouseReleased

    private void BoardSize2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize2MouseReleased
        evt.consume();          //Frees memory 
        resetSolutions();
        GenerateBoard(4,15);    //New (4x15) board
    }//GEN-LAST:event_BoardSize2MouseReleased

    private void BoardSize3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize3MouseReleased
        evt.consume();          //Frees memory 
        resetSolutions();
        GenerateBoard(3,20);    //New (3x20) board
    }//GEN-LAST:event_BoardSize3MouseReleased

    private void ExitOptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitOptionMouseReleased
        evt.consume();      //Frees memory 
        System.exit(0);     //Exits the program
    }//GEN-LAST:event_ExitOptionMouseReleased

    private void ResetOptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetOptionMouseReleased
        evt.consume();                  //Frees memory 
        Board.setSolving(false);
        try {
            sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        resetSolutions();
        GenerateBoard(Rows, Columns);   //New board with the previous one rows and columns
    }//GEN-LAST:event_ResetOptionMouseReleased

    private void SolveOptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SolveOptionMouseReleased
        evt.consume();
        new Thread(() -> {
            solve();     //Starts solving the puzzle
        }).start();
    }//GEN-LAST:event_SolveOptionMouseReleased

    private void Speed0MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed0MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[0]);        //Sets solving speed
    }//GEN-LAST:event_Speed0MouseReleased

    private void Speed1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed1MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[1]);        //Sets solving speed
    }//GEN-LAST:event_Speed1MouseReleased

    private void Speed2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed2MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[2]);        //Sets solving speed
    }//GEN-LAST:event_Speed2MouseReleased

    private void Speed3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed3MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[3]);        //Sets solving speed
    }//GEN-LAST:event_Speed3MouseReleased

    private void Speed4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed4MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[4]);        //Sets solving speed
    }//GEN-LAST:event_Speed4MouseReleased

    private void Speed5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed5MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[5]);        //Sets solving speed
    }//GEN-LAST:event_Speed5MouseReleased

    private void Speed6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed6MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[6]);        //Sets solving speed
    }//GEN-LAST:event_Speed6MouseReleased

    private void BoardPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardPanelMouseReleased
        if(evt.getY()<Board.getHeight() && evt.getX()< Board.getWidth()){       //Event happened inside board bounds
            if(!Board.isSolving()){
                int x = evt.getX() ;                 //Fixed coordinates
                int y = evt.getY() ;                 //Fixed coordinates
                evt.consume();                       //Frees memory
                Board.ChangeSquareStatus(x, y);     //Updates square's status and color
                repaint();                          //Updates the GUI with the new square's color
            }
        }
    }//GEN-LAST:event_BoardPanelMouseReleased

    private void TabbedPaneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabbedPaneKeyReleased
        int key = evt.getKeyCode();
        if (evt.isControlDown() && (key == KeyEvent.VK_E)){         //Ctrl+E
            evt.consume();      //Frees memory
            System.exit(0);     //Exits the program
        }
        else if (evt.isControlDown() && (key == KeyEvent.VK_R)){    //Ctrl+R
            evt.consume();                      //Frees memory
            Board.setSolving(false);
            try {
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            resetSolutions();
            GenerateBoard(Rows, Columns);       //New board with the previous one rows and columns
        }
        else if (evt.isControlDown() && (key == KeyEvent.VK_S)){
            evt.consume();      //Frees memory
            new Thread(() -> {
                solve();        //Starts solving the puzzle
            }).start();
        }
    }//GEN-LAST:event_TabbedPaneKeyReleased

    private void SolutionPickerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SolutionPickerItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED){    //Solution selected
            showSolution(Solutions[SolutionPicker.getSelectedIndex()]);
        }
        TabbedPane.requestFocus();      //returns focus to the keyevent listener component
    }//GEN-LAST:event_SolutionPickerItemStateChanged

    /**
     * Method to change status message
     * @param message1
     * @param message2
     * @param message3 
     */
    public void setMessage(String message1, String message2, String message3){
        Message1.setText(message1);
        Message2.setText(message2);
        Message3.setText(message3);
    }
    
    /**
     * Sets the speed at which the board will be solved
     * @param miliseconds 
     */
    private void setSpeed(int miliseconds){
        Speed = miliseconds;
        Board.setSpeed(miliseconds);
    }
    
    private void solve(){
        if(!Board.isSolving()){
            Board.setSolving(true);
            long start = System.currentTimeMillis()/1000;
            Solutions = Board.Solve(this, Board, new boolean[12], 0, 12);
            
            long finish = System.currentTimeMillis()/1000;
            System.out.println("Solved in "+(finish-start)+" seconds");
            Message1.setText("Done.");
            updateSolutions();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);     //Inicia el puzzle
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoardPanel;
    private javax.swing.JMenuItem BoardSize0;
    private javax.swing.JMenuItem BoardSize1;
    private javax.swing.JMenuItem BoardSize2;
    private javax.swing.JMenuItem BoardSize3;
    private javax.swing.JMenu ControlMenu;
    private javax.swing.JMenuItem ExitOption;
    private javax.swing.JMenuBar MenuBar;
    public static javax.swing.JLabel Message1;
    public static javax.swing.JLabel Message2;
    public static javax.swing.JLabel Message3;
    private javax.swing.JMenuItem ResetOption;
    private javax.swing.JMenu SizeMenu;
    private javax.swing.JComboBox<String> SolutionPicker;
    private javax.swing.JPanel SolutionsPanel;
    private javax.swing.JMenuItem SolveOption;
    private javax.swing.JRadioButtonMenuItem Speed0;
    private javax.swing.JRadioButtonMenuItem Speed1;
    private javax.swing.JRadioButtonMenuItem Speed2;
    private javax.swing.JRadioButtonMenuItem Speed3;
    private javax.swing.JRadioButtonMenuItem Speed4;
    private javax.swing.JRadioButtonMenuItem Speed5;
    private javax.swing.JRadioButtonMenuItem Speed6;
    private javax.swing.JMenu SpeedMenu;
    private javax.swing.JTabbedPane TabbedPane;
    // End of variables declaration//GEN-END:variables

}
