package formulariosPresentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Cliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblSexo;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 300);
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
		textField.setBounds(330, 33, 157, 20);
		contentPane.add(textField);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(264, 36, 46, 14);
		contentPane.add(lblApellido);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(330, 61, 157, 20);
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
		lblNewLabel.setBounds(264, 64, 62, 14);
		contentPane.add(lblNewLabel);
	}
}
