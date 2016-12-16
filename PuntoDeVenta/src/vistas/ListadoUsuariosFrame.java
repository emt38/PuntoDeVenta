package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelos.TipoUsuario;
import modelos.Usuario;
import principal.Program;
import principal.Utilidades;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class ListadoUsuariosFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblUsuarios;
	private Usuario usuario;
	private List<Usuario> usuarios;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Program.main(new String[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoUsuariosFrame frame = new ListadoUsuariosFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListadoUsuariosFrame() {
		usuario = Program.getLoggedUser();
		
		switch(usuario.getTipo()) {
		case Administrador:
			usuarios = new Usuario().listar(String.format("WHERE idtienda=%s AND tipo=1", usuario.getTienda().getId()));
			break;
		
		case Gerente:
			usuarios = new Usuario().listar(String.format("WHERE idtienda=%s AND tipo IN (0, 1, 2)", usuario.getTienda().getId()));
			break;
		case SysAdmin:
			usuarios = new Usuario().listar(String.format("tipo != 3", usuario.getTienda().getId()));
			break;
			
		case Cajero:
			usuarios = new Usuario().listar(String.format("id = %s", usuario.getId()));
			break;
			
			default:
			JOptionPane.showMessageDialog(this, "Usted no está autorizado para continuar", "Desautorizado", JOptionPane.INFORMATION_MESSAGE);
			return;
		
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrar Usuarios");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroUsuariosFrame temp = new RegistroUsuariosFrame();
				temp.setVisible(true);
			}
		});
		btnRegistrar.setBounds(10, 11, 150, 37);
		contentPane.add(btnRegistrar);
		
		String[] columnas = new String[] {"id", "nombreCompleto", "nombreUsuario", "tipo"};
		Object[][] datos = Utilidades.listToBidiArray(usuarios, columnas);
		
		tblUsuarios = new JTable();
		tblUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUsuarios.setModel(new DefaultTableModel(
			datos,
			new String[] {
				"Id", "Nombre Completo", "Usuario", "Tipo"
			}
		){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(68);
		tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(283);
		tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(152);
		tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(115);
		tblUsuarios.setRowHeight(24);
		tblUsuarios.setBounds(10, 80, 608, 275);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tblUsuarios);
		scrollPane.setBounds(10, 80, 608, 275);
		contentPane.add(scrollPane);
		
		JButton btnReestablecerContrasea = new JButton("Reestablecer Contrase\u00F1a");
		btnReestablecerContrasea.setEnabled(false);
		btnReestablecerContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReestablecerContrasea.setBounds(165, 11, 176, 37);
		contentPane.add(btnReestablecerContrasea);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setBounds(346, 11, 176, 37);
		contentPane.add(btnEliminar);
	}
}


