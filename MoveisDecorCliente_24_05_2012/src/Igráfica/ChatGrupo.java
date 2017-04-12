/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChatGrupo.java
 *
 * Created on 08/05/2012, 01:19:40
 */
package Igráfica;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import moveisdecor.Multicast;

/**
 *
 * @author Felipe
 */
public class ChatGrupo extends javax.swing.JFrame {
    
    static Multicast grupo1 = new Multicast();
    static grafLogin log = new grafLogin();
    private static boolean Broadcast = false;
    private static String IP;
    private static String login;
    private static String hist;
   
    
    public ChatGrupo() {
        initComponents();
        setLocationRelativeTo(null); // Centraliza Janela
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        CampoEscrita = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        visGlobal = new javax.swing.JTextArea();
        Enviar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 246, 230));

        CampoEscrita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoEscritaActionPerformed(evt);
            }
        });

        visGlobal.setColumns(20);
        visGlobal.setRows(5);
        jScrollPane1.setViewportView(visGlobal);

        Enviar.setText("Enviar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Viner Hand ITC", 0, 48));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Igráfica/novo-2.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(CampoEscrita, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(156, 156, 156)
                            .addComponent(jLabel1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CampoEscrita, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoEscritaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoEscritaActionPerformed
}//GEN-LAST:event_CampoEscritaActionPerformed

    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        String texto = login + ": " + CampoEscrita.getText();


        try {
            grupo1.MulticastEnvia(texto);
        } catch (IOException ex) {
            Logger.getLogger(ChatGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EnviarActionPerformed

   

    public void setMsg(String Texto) {
        hist = visGlobal.getText();
        visGlobal.setText(hist + "\n" + Texto.trim());
        // O método trim() remove os espaços em branco após a string 

    }

    
    void setLogin(String login) {
        this.login = login;
    }
    
    
    
    
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField CampoEscrita;
    private javax.swing.JButton Enviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea visGlobal;
    // End of variables declaration//GEN-END:variables
}
