package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelos.Usuario;
import principal.Utilidades;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class ReestablecerClaveDialog extends JDialog {
	private Usuario usuario;
	private JPasswordField pfClave;
	private JPasswordField pfConfirmacion;
	private boolean result = false;
	
	public boolean reestablecerClave(Usuario temp) {
		usuario = temp;
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setVisible(true);
		
		return result;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReestablecerClaveDialog dialog = new ReestablecerClaveDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean esLaClaveAnterior(String clave) {
		if(Utilidades.verificarHash(clave, usuario.getHashClave(), usuario.getSalesClave())) {
			JOptionPane.showMessageDialog(this, "La clave introducida es igual a la ya establecida.");
			return true;
		} else
			return false;
	}

	/**
	 * Create the dialog.
	 */
	public ReestablecerClaveDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Reestablecer Contrase\u00F1a");
		setBounds(100, 100, 368, 201);
		getContentPane().setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setName("btnCancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReestablecerClaveDialog.this.dispose();
			}
		});
		btnCancelar.setBounds(10, 115, 154, 36);
		getContentPane().add(btnCancelar);
		
		JButton btnReestablecer = new JButton("Reestablecer");
		btnReestablecer.setName("btnReestablecer");
		btnReestablecer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pfClave.getPassword().length < 6) {
					JOptionPane.showMessageDialog(ReestablecerClaveDialog.this, "La contraseña debe contener al menos 6 caracteres.");
					return;
				}
				
				String clave = String.valueOf(pfClave.getPassword());
				String confirmacion = String.valueOf(pfConfirmacion.getPassword());
				
				if(clave.equals(confirmacion)) {
					if(!esLaClaveAnterior(clave) && (JOptionPane.showConfirmDialog(ReestablecerClaveDialog.this, "Seguro que desea continuar?", "Confirmar reestablecimiento", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)) {
						usuario.setSalesClave(Utilidades.generarSales());
						usuario.setHashClave(Utilidades.generarHash(clave, usuario.getSalesClave()));
						if(usuario.actualizar()) {
							result = true;
							ReestablecerClaveDialog.this.dispose();
						} else {
							JOptionPane.showMessageDialog(ReestablecerClaveDialog.this, "Ha ocurrido un error inesperado");
						}
					}
				} else {
					JOptionPane.showMessageDialog(ReestablecerClaveDialog.this, "Las contraseñas no coinciden.");
				}
			}
		});
		btnReestablecer.setBounds(188, 115, 154, 36);
		getContentPane().add(btnReestablecer);
		
		JLabel lblNuevaClave = new JLabel("Nueva Clave");
		lblNuevaClave.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNuevaClave.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNuevaClave.setBounds(10, 28, 84, 14);
		getContentPane().add(lblNuevaClave);
		
		JLabel lblConfirmar = new JLabel("Confirmar");
		lblConfirmar.setHorizontalAlignment(SwingConstants.TRAILING);
		lblConfirmar.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblConfirmar.setBounds(10, 64, 84, 14);
		getContentPane().add(lblConfirmar);
		
		pfClave = new JPasswordField();
		pfClave.setName("pfClave");
		pfClave.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pfClave.setBounds(104, 23, 238, 25);
		getContentPane().add(pfClave);
		
		pfConfirmacion = new JPasswordField();
		pfConfirmacion.setName("pfConfirmacion");
		pfConfirmacion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pfConfirmacion.setBounds(104, 58, 238, 25);
		getContentPane().add(pfConfirmacion);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
