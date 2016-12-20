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
import java.awt.Font;

public class TiendasFrame extends JFrame{

	private JPanel contentPane;
	

	
	public TiendasFrame() {
		setTitle("Tiendas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(6, 36, 76, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblCiudad = new JLabel("Slogan");
		lblCiudad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCiudad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCiudad.setBounds(6, 64, 77, 20);
		contentPane.add(lblCiudad);
		
		JLabel label = new JLabel("Ciudad");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setBounds(6, 127, 77, 16);
		contentPane.add(label);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDireccin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDireccin.setBounds(5, 99, 77, 16);
		contentPane.add(lblDireccin);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(293, 165, 157, 36);
		contentPane.add(btnAgregar);
		
		
	}
}
