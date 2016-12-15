package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class CiudadesFrame extends JFrame {
	
	private JPanel contentPane;
	static String[] listaPais ={"-----","Abjasia","Acrotiri y Dhekelia","Afganistán","Albania","Alemania","Andorra","Angola","Anguila"};
	JComboBox comboBoxProvincia = new JComboBox();
	JComboBox comboBoxPais = new JComboBox(listaPais);
	JButton btnCancelar = new JButton("CANCELAR");
	JButton btnNuevo = new JButton("NUEVO");
	
	
	
	
	JTextField textFieldCiudad = new JTextField();
	
	public CiudadesFrame() {
		
		comboBoxPais.setEnabled(false);
		comboBoxProvincia.setEnabled(false);
		btnCancelar.setEnabled(false);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteFrame.class.getResource("/Iconos_E_Imagenes/GENTE.JPG")));
		setTitle("Ciudades");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 300, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion =JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

				if (opcion == 0){
				dispose();
				}
			}
		});
		btnSalir.setBounds(183, 109, 90, 31);
		contentPane.add(btnSalir);
		
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcion=JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar la accion?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

				if (opcion == 0){
					comboBoxPais.setEnabled(false);
					comboBoxProvincia.setEnabled(false);
					btnCancelar.setEnabled(false);
					btnNuevo.setEnabled(true);
				}
				
				
				
				
			}
		});
		btnCancelar.setBounds(1, 109, 93, 31);
		contentPane.add(btnCancelar);
		
		
		
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setHorizontalAlignment(SwingConstants.TRAILING);
		btnGuardar.setBounds(183, 79, 90, 31);
		contentPane.add(btnGuardar);
		
		
		
		
		
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxPais.setEnabled(true);
				comboBoxProvincia.setEnabled(true);
				btnNuevo.setEnabled(false);
				comboBoxProvincia.setEnabled(false);
				btnCancelar.setEnabled(true);
			}
			
		});
		
		
		btnNuevo.setIcon(null);
		btnNuevo.setMaximumSize(new Dimension(20, 9));
		btnNuevo.setBounds(1, 79, 93, 31);
		contentPane.add(btnNuevo);
		
		
		
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(91, 79, 95, 31);
		contentPane.add(btnModificar);
		
		
		
	
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(91, 109, 95, 31);
		contentPane.add(btnEliminar);
		
		
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(1, 0, 50, 14);
		contentPane.add(lblPais);
		
		comboBoxPais.setBounds(29, 0, 250, 20);
		contentPane.add(comboBoxPais);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setBounds(1, 20, 60, 14);
		contentPane.add(lblProvincia);
		
		comboBoxProvincia.setBounds(55, 20, 224, 20);
		contentPane.add(comboBoxProvincia );
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(1, 45, 60, 14);
		contentPane.add(lblCiudad);
		
		
		textFieldCiudad.setBounds(45, 40, 234, 26);
		textFieldCiudad.setEditable(false);
		contentPane.add(textFieldCiudad );	
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 67, 547, 14);
		contentPane.add(separator_1);
		
		
		comboBoxPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rellenaProvincia((String) comboBoxPais.getSelectedItem());
				
				
				if (comboBoxPais.getSelectedItem() == "-----"){
					textFieldCiudad.setText(null);
					textFieldCiudad.setEditable(false);
					comboBoxProvincia.setEnabled(false);
					
					}
				else
					comboBoxProvincia.setEnabled(true);
					
			}
			
					
		});
		
		
		
		
		comboBoxProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxProvincia.getSelectedItem() == "----" || comboBoxProvincia.getSelectedItem() == ""){
				textFieldCiudad.setText(null);
				textFieldCiudad.setEditable(false);
				}
				else
					textFieldCiudad.setEditable(true);
					
			}
		});
		
		
	
	};
	
	private void rellenaProvincia(String seleccionEnComboBoxPais) {
		comboBoxProvincia.removeAllItems();
		if (seleccionEnComboBoxPais.equals("Abjasia")) {
			comboBoxProvincia.addItem("----");
			comboBoxProvincia.addItem("B");
			comboBoxProvincia.addItem("C");
	   } else if (seleccionEnComboBoxPais.equals("Acrotiri y Dhekelia")) {
		   comboBoxProvincia.addItem("----");
		   comboBoxProvincia.addItem("2");
		   comboBoxProvincia.addItem("3");
	   		}
	}
}
