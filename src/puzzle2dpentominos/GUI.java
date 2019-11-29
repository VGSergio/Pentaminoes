/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle2dpentominos;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuBar = new javax.swing.JMenuBar();
        ControlMenu = new javax.swing.JMenu();
        SolveOption = new javax.swing.JMenuItem();
        ResetOption = new javax.swing.JMenuItem();
        ExitOption = new javax.swing.JMenuItem();
        SpeedMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        SizeMenu = new javax.swing.JMenu();
        BoardSize1 = new javax.swing.JMenuItem();
        BoardSize2 = new javax.swing.JMenuItem();
        BoardSize3 = new javax.swing.JMenuItem();
        BoardSize4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Puzzle2DPentominos");
        setSize(new java.awt.Dimension(1000, 1000));
        getContentPane().setLayout(new java.awt.GridLayout(2, 2));

        MenuBar.setSelectionModel(MenuBar.getSelectionModel());

        ControlMenu.setText("Control");

        SolveOption.setText("Solve");
        ControlMenu.add(SolveOption);

        ResetOption.setText("Reset");
        ControlMenu.add(ResetOption);

        ExitOption.setText("Exit");
        ControlMenu.add(ExitOption);

        MenuBar.add(ControlMenu);

        SpeedMenu.setText("Speed");

        jMenuItem1.setText("jMenuItem1");
        SpeedMenu.add(jMenuItem1);

        MenuBar.add(SpeedMenu);

        SizeMenu.setText("Size");

        BoardSize1.setText("6 x 10");
        BoardSize1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoardSize1MouseReleased(evt);
            }
        });
        SizeMenu.add(BoardSize1);

        BoardSize2.setText("5 x 12");
        BoardSize2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoardSize2MouseReleased(evt);
            }
        });
        SizeMenu.add(BoardSize2);

        BoardSize3.setText("4 x 15");
        BoardSize3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoardSize3MouseReleased(evt);
            }
        });
        SizeMenu.add(BoardSize3);

        BoardSize4.setText("3 x 20");
        BoardSize4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoardSize4MouseReleased(evt);
            }
        });
        SizeMenu.add(BoardSize4);

        MenuBar.add(SizeMenu);

        setJMenuBar(MenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoardSize1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize1MouseReleased
        Tablero t = new Tablero(6, 10);
    }//GEN-LAST:event_BoardSize1MouseReleased

    private void BoardSize2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize2MouseReleased
        Tablero t = new Tablero(5, 12);
    }//GEN-LAST:event_BoardSize2MouseReleased

    private void BoardSize3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize3MouseReleased
        Tablero t = new Tablero(4, 15);
    }//GEN-LAST:event_BoardSize3MouseReleased

    private void BoardSize4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardSize4MouseReleased
        Tablero t = new Tablero(3, 20);
    }//GEN-LAST:event_BoardSize4MouseReleased

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GUI().setVisible(true);
//            }
//        });
//        
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem BoardSize1;
    private javax.swing.JMenuItem BoardSize2;
    private javax.swing.JMenuItem BoardSize3;
    private javax.swing.JMenuItem BoardSize4;
    private javax.swing.JMenu ControlMenu;
    private javax.swing.JMenuItem ExitOption;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem ResetOption;
    private javax.swing.JMenu SizeMenu;
    private javax.swing.JMenuItem SolveOption;
    private javax.swing.JMenu SpeedMenu;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
