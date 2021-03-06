/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dados.ItensVenda;
import dados.Produto;
import dados.Venda;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tabelas.VendasTable;

/**
 *
 * @author fabio
 */
public class TelaListarVenda extends javax.swing.JFrame {

    VendasTable tabela;

    /**
     * Creates new form TelaListarVenda
     */
    public TelaListarVenda() {
        initComponents();
        this.setLocationRelativeTo(this);
        tabela = new VendasTable();
        this.jTable1.setModel(tabela);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório Vendas");
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Selecione Uma Venda Para visualizar os itens :");

        jButton1.setText("Visualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Fechar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 363, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Fecha Tela
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Não tem vendas para selecionar");
        } else if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma venda para visualizar ");
        } else {
            Venda get = tabela.getVendaLinha(jTable1.getSelectedRow());
            ArrayList<ItensVenda> itensVenda = get.getItensVenda();
            String dadosVenda = null;
            double totalVenda = 0;
            double totalCusto = 0;
            double totalLucro = 0;
            for (ItensVenda itemProduto : itensVenda) {

                double totalLucroDestesItens = 0;
                double totalVendaDestesItens = 0;
                double totalCustoDestesItens = 0;

                Produto produto = itemProduto.getProduto();
                int quantidade = itemProduto.getQuantidade();

                dadosVenda += "\n-----------------------";
                dadosVenda += "\nitens vendidos: " + quantidade;
                dadosVenda += "\n----------- Produtos vendidos -----------";

                dadosVenda += "\n" + produto;
                dadosVenda += "\n--";

                totalVendaDestesItens += produto.getPrecoVenda();
                totalCustoDestesItens += produto.getPrecoCusto();
                totalLucroDestesItens += (produto.getPrecoVenda() - produto.getPrecoCusto());

                totalVenda += totalVendaDestesItens * quantidade;
                totalCusto += totalCustoDestesItens * quantidade;
                totalLucro += totalLucroDestesItens * quantidade;

                dadosVenda += "\nvenda total destes itens: " + totalVendaDestesItens * quantidade;
                dadosVenda += "\ncusto total destes itens: " + totalCustoDestesItens * quantidade;
                dadosVenda += "\nlucro total destes itens: " + totalLucroDestesItens * quantidade;

                dadosVenda += "\n-----------------------";
            }

            dadosVenda += "\nTOTAL Venda: " + totalVenda;
            dadosVenda += "\nTOTAL Custo: " + totalCusto;
            dadosVenda += "\nTOTAL Lucro: " + totalLucro;
            dadosVenda += "\n#######################################";

            
            JOptionPane.showMessageDialog(rootPane, dadosVenda);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaListarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListarVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
