package loginsystem_mid;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginForm extends javax.swing.JFrame {

    /** Creates new form LoginForm */
    public LoginForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnGoRegister = new javax.swing.JButton(); // optional: button to go to RegisterForm

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtUsername.setText("Username");

        txtPassword.setText("Password");

        btnLogin.setText("Login");
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        btnGoRegister.setText("Register");
        btnGoRegister.addActionListener(this::btnGoRegisterActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin)
                    .addComponent(btnGoRegister))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnLogin)
                .addGap(10, 10, 10)
                .addComponent(btnGoRegister)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }

    // ===== BUTTON ACTIONS =====

    private void btnLoginActionPerformed(java.awt.event.ActionEvent actionEvent) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, txtUsername.getText());
            pst.setString(2, new String(txtPassword.getPassword()));

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new Dashboard().setVisible(true); // make sure Dashboard exists
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!");
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void btnGoRegisterActionPerformed(java.awt.event.ActionEvent actionEvent) {
        new RegisterForm().setVisible(true);
        this.dispose();
    }

    // ===== MAIN METHOD =====
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginForm().setVisible(true));
    }

    // ===== VARIABLES =====
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnGoRegister;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;

}