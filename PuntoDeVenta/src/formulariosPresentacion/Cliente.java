package formulariosPresentacion;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class Cliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblSexo;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(31, 36, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(87, 33, 167, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(336, 33, 194, 20);
		contentPane.add(textField);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(264, 36, 62, 14);
		contentPane.add(lblApellido);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(336, 61, 194, 20);
		contentPane.add(textField_1);
		
		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(31, 64, 46, 14);
		contentPane.add(lblSexo);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Femenino");
		rdbtnNewRadioButton.setBounds(87, 60, 80, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(169, 60, 80, 23);
		contentPane.add(rdbtnMasculino);
		
		JLabel lblNewLabel = new JLabel("Cedula/RNC:");
		lblNewLabel.setBounds(255, 64, 71, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(31, 136, 62, 14);
		contentPane.add(lblDireccion);
		
		textField_2 = new JTextField();
		textField_2.setBounds(87, 133, 443, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(31, 88, 46, 14);
		contentPane.add(lblTelefono);
		
		textField_3 = new JTextField();
		textField_3.setBounds(87, 85, 167, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(278, 88, 46, 14);
		contentPane.add(lblCelular);
		
		textField_4 = new JTextField();
		textField_4.setBounds(336, 85, 194, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(31, 113, 46, 14);
		contentPane.add(lblEmail);
		
		textField_5 = new JTextField();
		textField_5.setBounds(87, 110, 443, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(441, 164, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(347, 164, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(248, 164, 89, 23);
		contentPane.add(btnEditar);
	}
}
