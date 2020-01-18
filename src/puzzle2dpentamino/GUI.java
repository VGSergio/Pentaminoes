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
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Sergio Vega
 *         Andreas Korn
 */
public class GUI extends JFrame{
    
    private Board Board;
    private Board[] Solutions;
    private final int[] SPEEDS = {0, 1, 30, 70, 120, 300, 600};     //Change this values to change solving Board speeds, value 0 CAN'T be changed
    private int Rows = 6, Columns = 10;
    private int Speed = SPEEDS[3];                                  //Initial speed
    
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
        StopOption = new javax.swing.JMenuItem();
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
        jMenu1 = new javax.swing.JMenu();
        StatusMessageCheckBox = new javax.swing.JCheckBoxMenuItem();
        TempSolCheckBox = new javax.swing.JCheckBoxMenuItem();

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

        StopOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        StopOption.setText("Stop");
        StopOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                StopOptionMouseReleased(evt);
            }
        });
        ControlMenu.add(StopOption);

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

        jMenu1.setText("Performance");

        StatusMessageCheckBox.setText("Disable status message (better performance)");
        StatusMessageCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                StatusMessageCheckBoxMouseReleased(evt);
            }
        });
        jMenu1.add(StatusMessageCheckBox);

        TempSolCheckBox.setText("Save temporary solutions (worse performance)");
        TempSolCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TempSolCheckBoxMouseReleased(evt);
            }
        });
        jMenu1.add(TempSolCheckBox);

        MenuBar.add(jMenu1);

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
        evt.consume();              //Frees memory
        ResetBoard(6, 10);
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_BoardSize0MouseReleased

    private void BoardSize1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize1MouseReleased
        evt.consume();              //Frees memory 
        ResetBoard(5, 12);
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_BoardSize1MouseReleased

    private void BoardSize2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize2MouseReleased
        evt.consume();          //Frees memory 
        ResetBoard(4, 15);
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_BoardSize2MouseReleased

    private void BoardSize3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize3MouseReleased
        evt.consume();          //Frees memory 
        ResetBoard(3, 20);
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_BoardSize3MouseReleased

    private void ExitOptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitOptionMouseReleased
        evt.consume();      //Frees memory 
        System.exit(0);     //Exits the program
    }//GEN-LAST:event_ExitOptionMouseReleased

    private void ResetOptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetOptionMouseReleased
        evt.consume();                  //Frees memory 
        ResetBoard(Rows, Columns);
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_ResetOptionMouseReleased

    private void SolveOptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SolveOptionMouseReleased
        evt.consume();
        new Thread(() -> {
            solve();     //Starts solving the puzzle
        }).start();
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_SolveOptionMouseReleased

    private void Speed0MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed0MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[0]);        //Sets solving speed
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_Speed0MouseReleased

    private void Speed1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed1MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[1]);        //Sets solving speed
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_Speed1MouseReleased

    private void Speed2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed2MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[2]);        //Sets solving speed
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_Speed2MouseReleased

    private void Speed3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed3MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[3]);        //Sets solving speed
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_Speed3MouseReleased

    private void Speed4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed4MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[4]);        //Sets solving spee
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener componentd
    }//GEN-LAST:event_Speed4MouseReleased

    private void Speed5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed5MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[5]);        //Sets solving speed
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_Speed5MouseReleased

    private void Speed6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Speed6MouseReleased
        evt.consume();              //Frees memory
        setSpeed(SPEEDS[6]);        //Sets solving speed
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_Speed6MouseReleased

    private void BoardPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardPanelMouseReleased
        if(evt.getY()<Board.getHeight() && evt.getX()< Board.getWidth()){       //Event happened inside board bounds
            if(!Board.isSolving()){
                Board.ChangeSquareStatus(evt.getX(), evt.getY());       //Updates square's status and color
                evt.consume();          //Frees memory
                repaint();              //Updates the GUI with the new square's color
            }
        }
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_BoardPanelMouseReleased

    private void TabbedPaneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabbedPaneKeyReleased
        int key = evt.getKeyCode();
        if (evt.isControlDown() && (key == KeyEvent.VK_E)){         //Ctrl+E
            evt.consume();      //Frees memory
            System.exit(0);     //Exits the program
        } else if (evt.isControlDown() && (key == KeyEvent.VK_R)){    //Ctrl+R
            evt.consume();                      //Frees memory
            ResetBoard(Rows, Columns);
        } else if (evt.isControlDown() && (key == KeyEvent.VK_S)){    //Ctrl+S
            evt.consume();      //Frees memory
            new Thread(() -> {
                solve();        //Starts solving the puzzle
            }).start();
        } else if (evt.isControlDown() && (key == KeyEvent.VK_P)){    //Ctrl+P
            evt.consume();
            Board.setSolving(false);
        }
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_TabbedPaneKeyReleased

    private void SolutionPickerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SolutionPickerItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED){                    //A solution has been selected
            showSolution(Solutions[SolutionPicker.getSelectedIndex()]);     //Shows the selected solution
        }
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_SolutionPickerItemStateChanged

    private void StatusMessageCheckBoxMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StatusMessageCheckBoxMouseReleased
        evt.consume();
        Board.setStatusMessage(StatusMessageCheckBox.getState());
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_StatusMessageCheckBoxMouseReleased

    private void TempSolCheckBoxMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TempSolCheckBoxMouseReleased
        evt.consume();
        Board.setTemporalSolutions(TempSolCheckBox.getState());
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_TempSolCheckBoxMouseReleased

    private void StopOptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StopOptionMouseReleased
        evt.consume();
        Board.setSolving(false);
        TabbedPane.requestFocus();      //Returns focus to the keyevent listener component
    }//GEN-LAST:event_StopOptionMouseReleased

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
        Board.setStatusMessage(StatusMessageCheckBox.getState());              //Sets the new board StatusMessage to the previously selected one
        Board.setTemporalSolutions(TempSolCheckBox.getState());
        
        //Message configuration
        setUpMessages();
        
        //Solution picker configuration
        SolutionPicker.setSize(150, 30);            //SolutionPicker size
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
        width += getInsets().right;
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
            g.add(SpeedMenu.getItem(i));                            //one can be selected at a time
        }
        Speed3.setSelected(true);       //Default speed -> Normal speed
    }
    
    /**
     * Default status message setter
     */
    private void setUpMessages(){
        String s2 = "Block Squares or use Solve(Ctrl+S) to start";              //Initial message    
        Message1.setText("");                                                   //Updates the status text
        Message2.setText(s2);                                                   //Updates the status text
        Message3.setText("");                                                   //Updates the status text
        
        Message1.setBounds(0, Board.getHeight()                       , Board.getWidth(), Message1.getHeight());      //Sets its location 
        Message2.setBounds(0, Board.getHeight()+Message1.getHeight()  , Board.getWidth(), Message2.getHeight());      //Sets its location
        Message3.setBounds(0, Board.getHeight()+Message1.getHeight()*2, Board.getWidth(), Message3.getHeight());      //Sets its location
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

        board.setBounds(0, Message1.getHeight()*3, getWidth(), board.getHeight());      //Places the solution
        SolutionsPanel.add(board);                                                      //adds it to the solution panel
        repaint();                      //Repaints frame
    }
    
    /**
     * Stops board solving, resets status message and solutions panel, 
     * and creates a new Board.
     * @param row
     * @param column 
     */
    private void ResetBoard(int row, int column){
        Board.setSolving(false);            //Stops BackTracking solver method in case it was stooped during execution
        
        try {               
            sleep(10);                      //Wait for a proper status message update
            setUpMessages();                //Resets the status message
            sleep(10);                      //Wait for a proper status message update
        } catch (InterruptedException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        resetSolutions();                   //Erases previous solutions found
        GenerateBoard(row, column);         //New (rowXcolumn) board
    }
        
    /**
     * Sets the speed at which the board will be solved
     * @param miliseconds 
     */
    private void setSpeed(int miliseconds){
        Speed = miliseconds;
        Board.setSpeed(miliseconds);
    }
    
    /**
     * 
     */
    private void solve(){
        if(!Board.isSolving() && SolutionPicker.getItemCount()==0){
            Board.setSolving(true);
            long start = System.currentTimeMillis()/1000;
            Message2.setText("Solving");
            if(Board.hasBlockedSquares())
                Solutions = Board.Solve(this, 0, new boolean[12]);
            else
                Solutions = Board.Solve(this, 0, new boolean[12], 0, 12);
            
            long finish = System.currentTimeMillis()/1000;
            System.out.println("Solved in "+(finish-start)+" seconds\n");
            Message2.setText("Solved");
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
    private static javax.swing.JPanel BoardPanel;
    private static javax.swing.JMenuItem BoardSize0;
    private static javax.swing.JMenuItem BoardSize1;
    private static javax.swing.JMenuItem BoardSize2;
    private static javax.swing.JMenuItem BoardSize3;
    private static javax.swing.JMenu ControlMenu;
    private static javax.swing.JMenuItem ExitOption;
    private static javax.swing.JMenuBar MenuBar;
    private static javax.swing.JLabel Message1;
    private static javax.swing.JLabel Message2;
    private static javax.swing.JLabel Message3;
    private static javax.swing.JMenuItem ResetOption;
    private static javax.swing.JMenu SizeMenu;
    private static javax.swing.JComboBox<String> SolutionPicker;
    private static javax.swing.JPanel SolutionsPanel;
    private static javax.swing.JMenuItem SolveOption;
    private static javax.swing.JRadioButtonMenuItem Speed0;
    private static javax.swing.JRadioButtonMenuItem Speed1;
    private static javax.swing.JRadioButtonMenuItem Speed2;
    private static javax.swing.JRadioButtonMenuItem Speed3;
    private static javax.swing.JRadioButtonMenuItem Speed4;
    private static javax.swing.JRadioButtonMenuItem Speed5;
    private static javax.swing.JRadioButtonMenuItem Speed6;
    private static javax.swing.JMenu SpeedMenu;
    private static javax.swing.JCheckBoxMenuItem StatusMessageCheckBox;
    private static javax.swing.JMenuItem StopOption;
    private static javax.swing.JTabbedPane TabbedPane;
    private static javax.swing.JCheckBoxMenuItem TempSolCheckBox;
    private static javax.swing.JMenu jMenu1;
    // End of variables declaration//GEN-END:variables

}
