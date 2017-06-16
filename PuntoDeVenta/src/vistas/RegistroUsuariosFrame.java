package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modelos.Usuario;
import principal.Utilidades;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import modelos.TipoUsuario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegistroUsuariosFrame extends JFrame {
	
	private Usuario model = new Usuario();
	private List<String> errores = new ArrayList<>();
	
	private JTextField txtNombreCompleto;
	private JTextField txtNombreUsuario;
	private JPasswordField txtClave;
	private JPasswordField txtConfirmacion;
	private Usuario usuario;
	JComboBox cbTipoUsuario = new JComboBox();
	private JButton btnAgregar;
	private JButton btnInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		principal.Program.main(new String[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroUsuariosFrame frame = new RegistroUsuariosFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private boolean validar() {
		boolean temp = model.esValido(errores);
		btnAgregar.setEnabled(temp);
		btnInfo.setEnabled(!temp);
		return temp;
	}
	
	@Override
	public void setVisible(boolean b) {
		try {
			cbTipoUsuario.removeItem(TipoUsuario.SysAdmin);
			switch(usuario.getTipo()) {
			case Administrador:
				getContentPane().setBackground(new Color(113, 46, 36));
				cbTipoUsuario.removeItem(TipoUsuario.Administrador);
				cbTipoUsuario.removeItem(TipoUsuario.Gerente);
				break;
			
			case Gerente:
				getContentPane().setBackground(new Color(64, 104, 244));
				break;
			case SysAdmin:
				getContentPane().setBackground(new Color(106, 90, 205));
				break;
				
			case Cajero: 
				default:
				JOptionPane.showMessageDialog(this, "Usted no está autorizado para continuar", "Desautorizado", JOptionPane.INFORMATION_MESSAGE);
				return;
			
			}
			super.setVisible(b);
		} catch (Exception e) {
			super.setVisible(false);
			JOptionPane.showMessageDialog(this, "Usted no está autorizado para continuar", "Desautorizado", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Create the frame.
	 */
	public RegistroUsuariosFrame() {
		usuario = principal.Program.getLoggedUser();
		
		
		setBackground(SystemColor.desktop);
		setBounds(100, 100, 491, 421);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre Completo");
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombreDeUsuario.setToolTipText("Su nombre y Apellido");
		lblNombreDeUsuario.setForeground(new Color(255, 255, 255));
		lblNombreDeUsuario.setFont(new Font("Segoe UI Light", Font.ITALIC, 16));
		lblNombreDeUsuario.setBounds(15, 87, 125, 22);
		getContentPane().add(lblNombreDeUsuario);
		
		JLabel label = new JLabel("Nombre de Usuario");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setToolTipText("El usuario con el cual acceder\u00E1 al sistema");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Light", Font.ITALIC, 16));
		label.setBounds(10, 121, 130, 22);
		getContentPane().add(label);
		
		txtNombreCompleto = new JTextField();
		txtNombreCompleto.setName("txtNombreCompleto");
		txtNombreCompleto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				model.setNombreCompleto(txtNombreCompleto.getText());
				validar();
			}
		});
		txtNombreCompleto.setToolTipText("Su Nombre y Apellido");
		txtNombreCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNombreCompleto.setBounds(145, 83, 321, 31);
		getContentPane().add(txtNombreCompleto);
		txtNombreCompleto.setColumns(10);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setName("txtNombreUsuario");
		txtNombreUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				model.setNombreUsuario(txtNombreUsuario.getText());
				validar();
			}
		});
		txtNombreUsuario.setToolTipText("El usuario con el cual acceder\u00E1 al sistema");
		txtNombreUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(145, 117, 186, 31);
		getContentPane().add(txtNombreUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setToolTipText("El usuario con el cual acceder\u00E1 al sistema");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Segoe UI Light", Font.ITALIC, 16));
		lblContrasea.setBounds(10, 155, 130, 22);
		getContentPane().add(lblContrasea);
		
		JLabel lblRepitaContrasea = new JLabel("Repita Contrase\u00F1a");
		lblRepitaContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRepitaContrasea.setToolTipText("El usuario con el cual acceder\u00E1 al sistema");
		lblRepitaContrasea.setForeground(Color.WHITE);
		lblRepitaContrasea.setFont(new Font("Segoe UI Light", Font.ITALIC, 16));
		lblRepitaContrasea.setBounds(5, 189, 135, 22);
		getContentPane().add(lblRepitaContrasea);
		
		txtClave = new JPasswordField();
		txtClave.setName("txtClave");
		txtClave.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				model.setHashClave(String.valueOf(txtClave.getPassword()));
				validar();
			}
		});
		txtClave.setToolTipText("Escriba su contrase\u00F1a");
		txtClave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtClave.setColumns(10);
		txtClave.setBounds(145, 151, 186, 31);
		getContentPane().add(txtClave);
		
		txtConfirmacion = new JPasswordField();
		txtConfirmacion.setName("txtConfirmacion");
		txtConfirmacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				model.setSalesClave(String.valueOf(txtConfirmacion.getPassword()));
				validar();
			}
		});
		txtConfirmacion.setToolTipText("Vuelva a Escribir su contrase\u00F1a");
		txtConfirmacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtConfirmacion.setColumns(10);
		txtConfirmacion.setBounds(145, 185, 186, 31);
		getContentPane().add(txtConfirmacion);
		
		JLabel lblRegistroDeUsuarios = new JLabel("Registro de Usuarios");
		lblRegistroDeUsuarios.setForeground(new Color(255, 255, 0));
		lblRegistroDeUsuarios.setFont(new Font("Segoe UI Light", Font.ITALIC, 28));
		lblRegistroDeUsuarios.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRegistroDeUsuarios.setBounds(10, 6, 448, 58);
		getContentPane().add(lblRegistroDeUsuarios);
		
		JButton btnLimpiar = new JButton("Limpiar Campos");
		btnLimpiar.setName("btnLimpiar");
		btnLimpiar.setIcon(null);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtClave.setText("");
				txtConfirmacion.setText("");
				txtNombreUsuario.setText("");
				txtNombreCompleto.setText("");
				model = new Usuario();
				btnAgregar.setEnabled(false);
				btnInfo.setEnabled(true);
			}
		});
		btnLimpiar.setBounds(74, 318, 149, 40);
		getContentPane().add(btnLimpiar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setName("btnAgregar");
		btnAgregar.setEnabled(false);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
				if(JOptionPane.showConfirmDialog(RegistroUsuariosFrame.this, "¿Seguro que desea agregar al " + cbTipoUsuario.getSelectedItem().toString() + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					String clear = model.getHashClave();
					model.setSalesClave(Utilidades.generarSales());
					model.setHashClave(Utilidades.generarHash(model.getHashClave(), model.getSalesClave()));
					model.setTipo((TipoUsuario)cbTipoUsuario.getSelectedItem());
					model.setTienda(usuario.getTienda());
					
					List<Usuario> duplicacion = new Usuario().listar(String.format("WHERE NombreUsuario='%s'", model.getNombreUsuario().replace("'", "''").replace("\"", "\"\"")));
					if(duplicacion.size() > 0)
					{
						JOptionPane.showMessageDialog(RegistroUsuariosFrame.this, "Su nombre de usuario no está disponible", "Error :/", JOptionPane.WARNING_MESSAGE);
						model.setHashClave(clear);
						model.setSalesClave(clear);
					}
					else if(model.insertar()) {
						JOptionPane.showMessageDialog(RegistroUsuariosFrame.this, "Se agregó el usuario satisfactorimente", "Éxito :)", JOptionPane.INFORMATION_MESSAGE);
						RegistroUsuariosFrame.this.dispose();
					} else {
						JOptionPane.showMessageDialog(RegistroUsuariosFrame.this, "Ocurrió un error con la base de datos", "Error :(", JOptionPane.ERROR_MESSAGE);
						model.setHashClave(clear);
						model.setSalesClave(clear);
					}
					
				}
			}
		});
		btnAgregar.setIcon(null);
		btnAgregar.setBounds(225, 318, 141, 40);
		getContentPane().add(btnAgregar);
		
		cbTipoUsuario.setModel(new DefaultComboBoxModel(TipoUsuario.values()));
		cbTipoUsuario.setName("cbTipoUsuario");
		cbTipoUsuario.setBounds(145, 221, 186, 31);
		getContentPane().add(cbTipoUsuario);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setToolTipText("El usuario con el cual acceder\u00E1 al sistema");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Segoe UI Light", Font.ITALIC, 16));
		lblTipo.setBounds(15, 223, 125, 22);
		getContentPane().add(lblTipo);
		
		btnInfo = new JButton("");
		btnInfo.setName("btnInfo");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(RegistroUsuariosFrame.this, errores.toArray(new String[0]));
			}
		});
		btnInfo.setIcon(new ImageIcon(RegistroUsuariosFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		btnInfo.setBounds(361, 318, 38, 40);
		getContentPane().add(btnInfo);
		validar();
	}
}
