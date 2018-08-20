/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author gferreira
 */
public class TelaCliente extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst=null;
ResultSet rs=null;
    /**
     * Creates new form TelaCliente
     */
 

    public TelaCliente() {
        initComponents();
        conexao=ModuloConexao.conector();
        Carega_Usuarios();
    }
    
     private void adicionar(){
        String sql="insert into tbclientes(nomecli,endcli,fonecli,emailcli) values(?,?,?,?)";
        try{
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtNome.getText());
            pst.setString(2, txtEndereco.getText());
            pst.setString(3,txtTelefone.getText());
            pst.setString(4,txtEmail.getText());
                
            if ((txtNome.getText().isEmpty())||(txtTelefone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null,"Preencha os campos obrigatórios");
            } else {
            
            
            int adicionado =pst.executeUpdate();
           if(adicionado>0){
               JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso !");
              txtEndereco.setText("");
               txtNome.setText("");
                txtTelefone.setText("");
                txtEmail.setText("");               
           }
        }
        }
         catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);  
        }
    }

     private void pesquisar_usuario(){
         String sql="select * from tbclientes where nomecli like ?";
         try {
             pst = conexao.prepareStatement(sql);
             
             pst.setString(1,txtCliNome.getText()+"%");
             rs=pst.executeQuery();
             //usando a biblioteca rs2xml.jar para preencher tabela
             
             tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e); 
         }
     }
     
     private void Carega_Usuarios(){
         String sql="select * from tb_usuario ";
         try {
             pst = conexao.prepareStatement(sql);
             
             //pst.setString(1,txtCliNome.getText()+"%");
             rs=pst.executeQuery();
             //usando a biblioteca rs2xml.jar para preencher tabela
             
             tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e); 
         }
     }
   
     public void setar_campos(){
         int setar = tblClientes.getSelectedRow();
         txtId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
         txtNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
         txtEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
         txtTelefone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
         txtEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
         btnAdicionar.setEnabled(false);
     }
     
      private void alterar(){
        String sql="update tbclientes set nomecli=?, endcli=?,fonecli=?, emailcli=? where idcli=?";
        try{
            pst=conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2,txtEndereco.getText());
            pst.setString(3,txtTelefone.getText());
            pst.setString(4,txtEmail.getText());
            pst.setString(5,txtId.getText());
            
            if ((txtNome.getText().isEmpty())||(txtTelefone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null,"Preencha os campos obrigatórios");
            } else {
            
            
            int alterado =pst.executeUpdate();
            if(alterado>0){
               JOptionPane.showMessageDialog(null,"Dados do cliente alterados com sucesso !");
               txtId.setText("");
               txtEndereco.setText("");
                txtNome.setText("");
                txtTelefone.setText("");
                txtEmail.setText(""); 
                btnAdicionar.setEnabled(true);
           }
        }
        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);  
        }
        
    }
      
      private void remover(){
       int confirma= JOptionPane.showConfirmDialog(null,"Deseja mesmo remover este Cliente?","Atenção",JOptionPane.YES_NO_OPTION);
       if(confirma==JOptionPane.YES_OPTION){
           String sql="delete from tbclientes where idcli=?";
           try{
               pst=conexao.prepareStatement(sql);
               pst.setString(1,txtId.getText());
             
               
               int removido =pst.executeUpdate();
           if(removido>0){
               JOptionPane.showMessageDialog(null,"Cliente removido com sucesso !");
                txtId.setText("");
                txtNome.setText("");
                txtEndereco.setText("");
                txtEmail.setText("");
                btnAdicionar.setEnabled(true);
                txtTelefone.setText("");   
           }
           else{
                JOptionPane.showMessageDialog(null, "Cliente não encontrado no banco de dados"); 
           }
           }
           catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);  
        }
       }
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        lblBuscar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(667, 529));

        jLabel1.setText("* Nome");

        jLabel2.setText("Endereço");

        jLabel3.setText("* Telefone");

        jLabel4.setText("E-mail");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnEditar.setRequestFocusEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnDeletar.setToolTipText("Deletar");
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnDeletar.setRequestFocusEnabled(false);
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        jLabel5.setText("* Campos obrigatórios");

        txtCliNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliNomeKeyReleased(evt);
            }
        });

        lblBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/lupa.png"))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        txtId.setEnabled(false);

        jLabel6.setText("Id Cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome)
                            .addComponent(txtEmail)
                            .addComponent(txtEndereco)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(111, 111, 111))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBuscar)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        setBounds(0, 0, 667, 529);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
     adicionar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
     alterar();               // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
     remover();               // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void txtCliNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliNomeKeyReleased
     pesquisar_usuario();        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliNomeKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
setar_campos();        // TODO add your handling code here:
    }//GEN-LAST:event_tblClientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
