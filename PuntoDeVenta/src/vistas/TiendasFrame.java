package vistas;

import java.awt.EventQueue;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class TiendasFrame extends JFrame{

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JTextField textField_1;
	

	
	public TiendasFrame() {
		setTitle("Tiendas");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 574, 288);
		
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("New label");
		lblID.setBounds(10, 11, 46, 14);
		lblID.setText("ID");
		contentPane.add(lblID);
		
		JTextField tfID = new JTextField();
		tfID.setBounds(31, 11, 46, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		JLabel lblNombre = new JLabel("New label");
		lblNombre.setBounds(10, 36, 46, 14);
		lblNombre.setText("Nombre");
		contentPane.add(lblNombre);
		
		JTextField jtfNombre = new JTextField();
		jtfNombre.setBounds(54, 36, 86, 20);
		contentPane.add(jtfNombre);
		jtfNombre.setColumns(10);
		
		
	}
}