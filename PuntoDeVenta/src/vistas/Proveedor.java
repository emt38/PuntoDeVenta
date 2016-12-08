package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.Toolkit;

public class Proveedor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Proveedor frame = new Proveedor();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Proveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Proveedor.class.getResource("/Iconos_E_Imagenes/GENTE.JPG")));
		setTitle("PROVEEDOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGuardar = new JButton("SALIR");
		btnGuardar.setBounds(451, 217, 101, 31);
		contentPane.add(btnGuardar);
		
		JButton btnEditar = new JButton("CANCELAR");
		btnEditar.setBounds(269, 217, 101, 31);
		contentPane.add(btnEditar);
		
		JButton btnGuardar_1 = new JButton("GUARDAR");
		btnGuardar_1.setHorizontalAlignment(SwingConstants.TRAILING);
		btnGuardar_1.setBounds(182, 217, 101, 31);
		contentPane.add(btnGuardar_1);
		
		JButton btnNuevo = new JButton("NUEVO");
		btnNuevo.setSelectedIcon(new ImageIcon(Proveedor.class.getResource("/Iconos_E_Imagenes/NUEVO.JPG")));
		btnNuevo.setMaximumSize(new Dimension(20, 9));
		btnNuevo.setBounds(5, 217, 101, 31);
		contentPane.add(btnNuevo);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(93, 217, 101, 31);
		contentPane.add(btnModificar);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(60, 11, 197, 20);
		contentPane.add(textField);
		
		JLabel lblCodigo = new JLabel("CODIGO:");
		lblCodigo.setBounds(5, 14, 56, 14);
		contentPane.add(lblCodigo);
		
		JLabel lblNomre = new JLabel("NOMBRE:");
		lblNomre.setBounds(5, 55, 64, 14);
		contentPane.add(lblNomre);
		
		textField_1 = new JTextField();
		textField_1.setBounds(133, 52, 312, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblApellido = new JLabel("APELLIDO:");
		lblApellido.setBounds(5, 80, 64, 14);
		contentPane.add(lblApellido);
		
		textField_2 = new JTextField();
		textField_2.setBounds(133, 77, 312, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDirecion = new JLabel("DIRECION:");
		lblDirecion.setBounds(5, 105, 64, 14);
		contentPane.add(lblDirecion);
		
		textField_3 = new JTextField();
		textField_3.setBounds(133, 102, 403, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCedulaRnc = new JLabel("CEDULA / RNC:");
		lblCedulaRnc.setBounds(5, 130, 83, 14);
		contentPane.add(lblCedulaRnc);
		
		textField_4 = new JTextField();
		textField_4.setBounds(133, 127, 144, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 42, 547, 14);
		contentPane.add(separator);
		
		JLabel lblTelefono = new JLabel("TELEFONO:");
		lblTelefono.setBounds(5, 155, 73, 14);
		contentPane.add(lblTelefono);
		
		textField_5 = new JTextField();
		textField_5.setBounds(133, 152, 144, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(372, 127, 164, 20);
		contentPane.add(comboBox);
		
		JLabel lblSexo = new JLabel("SEXO:");
		lblSexo.setBounds(302, 130, 46, 14);
		contentPane.add(lblSexo);
		
		JLabel lblCelular = new JLabel("CELULAR:");
		lblCelular.setBounds(302, 155, 64, 14);
		contentPane.add(lblCelular);
		
		textField_6 = new JTextField();
		textField_6.setBounds(372, 152, 164, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(133, 177, 144, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblFechaDeIngreso = new JLabel("FECHA DE INGRESO:");
		lblFechaDeIngreso.setBounds(5, 180, 118, 14);
		contentPane.add(lblFechaDeIngreso);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(372, 177, 164, 20);
		contentPane.add(textField_8);
		
		JLabel lblBalance = new JLabel("BALANCE:");
		lblBalance.setBounds(302, 180, 64, 14);
		contentPane.add(lblBalance);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(5, 208, 547, 14);
		contentPane.add(separator_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Proveedor.class.getResource("/Iconos_E_Imagenes/ANTERIOR.JPG")));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(260, 6, 56, 31);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(Proveedor.class.getResource("/Iconos_E_Imagenes/PRIMERO.JPG")));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(315, 6, 56, 31);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(Proveedor.class.getResource("/Iconos_E_Imagenes/Search.png")));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(370, 6, 56, 31);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(Proveedor.class.getResource("/Iconos_E_Imagenes/ULTIMO.JPG")));
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.setBounds(425, 6, 56, 31);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon(Proveedor.class.getResource("/Iconos_E_Imagenes/SIGUIENTE.JPG")));
		button_3.setHorizontalAlignment(SwingConstants.LEFT);
		button_3.setBounds(480, 6, 56, 31);
		contentPane.add(button_3);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(358, 217, 101, 31);
		contentPane.add(btnEliminar);
	}
}
