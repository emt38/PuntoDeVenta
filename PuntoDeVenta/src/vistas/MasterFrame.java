package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Master {

	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//
//	}

	/**
	 * Create the application.
	 */
	public Master() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente temp = new Cliente();
				temp.setVisible(true);
			}
		});
		btnClientes.setBounds(50, 89, 89, 23);
		frame.getContentPane().add(btnClientes);
		
		JButton btnProveedores = new JButton("Proveedores");
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proveedor temp = new Proveedor();
				temp.setVisible(true);
			}
		});
		btnProveedores.setBounds(269, 89, 89, 23);
		frame.getContentPane().add(btnProveedores);
	}
}
