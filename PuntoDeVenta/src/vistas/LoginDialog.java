package vistas;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

import modelos.Usuario;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginDialog extends JDialog {
	private JTextField txtNombreUsuario;
	private JPasswordField txtClave;
	
	private Usuario usuario;
	private JButton btnSalir;
	private JButton btnIniciarSesion;
	
	public Usuario getLoggedUser() {
		return usuario;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDialog dialog = new LoginDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		
		getContentPane().setBackground(new Color(100, 149, 237));
		getContentPane().setForeground(new Color(0, 0, 0));
		setBounds(100, 100, 450, 258);
		getContentPane().setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombreDeUsuario.setForeground(new Color(255, 255, 255));
		lblNombreDeUsuario.setBounds(10, 25, 146, 20);
		getContentPane().add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblContrasea.setBounds(10, 90, 146, 20);
		getContentPane().add(lblContrasea);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBounds(10, 49, 241, 29);
		getContentPane().add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setName("txtNombreUsuario");
		
		txtClave = new JPasswordField();
		txtClave.setName("txtClave");
		txtClave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n') {
					if((txtNombreUsuario.getText().length() < 3) || (txtClave.getPassword().length < 6))
						JOptionPane.showMessageDialog(LoginDialog.this, "Error de usuario o contraseña");
					else
					{
						List<Usuario> users = new Usuario().listar(String.format("WHERE nombreusuario='%s'", txtNombreUsuario.getText().replace("'", "''").replace("\"", "\"\"")));
						
						if(users.size() != 1)
						{
							JOptionPane.showMessageDialog(LoginDialog.this, "Error de usuario o contraseña");
							return;
						}
							
						Usuario temp = users.get(0);
						if(temp.iniciarSesion(String.valueOf(txtClave.getPassword()))) {
							usuario = temp;
							setDefaultCloseOperation(HIDE_ON_CLOSE);
							LoginDialog.this.dispose();
						}
						else {
							JOptionPane.showMessageDialog(LoginDialog.this, "Error de usuario o contraseña");
						}
					}
					txtClave.setText("");
				}
			}
		});
		txtClave.setColumns(10);
		txtClave.setBounds(10, 115, 241, 29);
		getContentPane().add(txtClave);
		
		JLabel lblPos = new JLabel("POS");
		lblPos.setFont(new Font("Georgia", Font.PLAIN, 66));
		lblPos.setForeground(new Color(255, 255, 255));
		lblPos.setBounds(278, 64, 135, 69);
		getContentPane().add(lblPos);
		
		btnSalir = new JButton("Salir");
		btnSalir.setName("btnSalir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginDialog.this.dispose();
			}
		});
		btnSalir.setBounds(10, 169, 182, 39);
		getContentPane().add(btnSalir);
		
		btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesion.setName("btnIniciarSesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((txtNombreUsuario.getText().length() < 3) || (txtClave.getPassword().length < 6))
					JOptionPane.showMessageDialog(LoginDialog.this, "Error de usuario o contraseña");
				else
				{
					List<Usuario> users = new Usuario().listar(String.format("WHERE nombreusuario='%s'", txtNombreUsuario.getText().replace("'", "''").replace("\"", "\"\"")));
					
					if(users.size() != 1)
					{
						JOptionPane.showMessageDialog(LoginDialog.this, "Error de usuario o contraseña");
						return;
					}
						
					Usuario temp = users.get(0);
					if(temp.iniciarSesion(String.valueOf(txtClave.getPassword()))) {
						usuario = temp;
						setDefaultCloseOperation(HIDE_ON_CLOSE);
						LoginDialog.this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(LoginDialog.this, "Error de usuario o contraseña");
					}
				}
				txtClave.setText("");
			}
		});
		btnIniciarSesion.setBounds(231, 169, 182, 39);
		getContentPane().add(btnIniciarSesion);

	}
}
