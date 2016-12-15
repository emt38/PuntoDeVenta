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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import modelos.Pais;
import modelos.Producto;
import modelos.Provincia;
import modelos.Ciudad;




public class CiudadesFrame extends JFrame {
	
	private JPanel contentPane;
	JComboBox<Provincia> comboBoxProvincia = new JComboBox<>(new Provincia().listar().toArray(new Provincia[0]));
	JComboBox<Pais> comboBoxPais = new JComboBox<>(new Pais().listar().toArray(new Pais[0]));
	
	JButton btnCancelar = new JButton("CANCELAR");
	JButton btnNuevo = new JButton("NUEVO");
	JButton btnGuardar = new JButton("GUARDAR");
	JButton btnSalir = new JButton("SALIR");
	JButton btnModificar = new JButton("MODIFICAR");
	JButton btnEliminar = new JButton("ELIMINAR");
	JLabel lblProvincia = new JLabel("Provincia:");
	Pais miPais = new Pais();
	int iD;
	
	
	
	JTextField textFieldCiudad = new JTextField();
	
	public CiudadesFrame() {
		
		
		
		comboBoxPais.setEnabled(false);
		comboBoxProvincia.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnGuardar.setEnabled(false);
		btnEliminar.setEnabled(false);
		textFieldCiudad.setEnabled(false);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteFrame.class.getResource("/Iconos_E_Imagenes/GENTE.JPG")));
		setTitle("Ciudades");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 300, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
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
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
					textFieldCiudad.setEnabled(false);
					btnGuardar.setEnabled(false);
					
					
				}
				
				
				
				
			}
		});
		btnCancelar.setBounds(1, 109, 93, 31);
		contentPane.add(btnCancelar);
		
		
		
		
		
		btnGuardar.setHorizontalAlignment(SwingConstants.TRAILING);
		btnGuardar.setBounds(183, 79, 90, 31);
		contentPane.add(btnGuardar);
		
		
		
		
		
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				comboBoxPais.setEnabled(true);
				comboBoxProvincia.setEnabled(true);
				btnNuevo.setEnabled(false);
				btnCancelar.setEnabled(true);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnGuardar.setEnabled(true);
				comboBoxPais.setEditable(true);
				comboBoxProvincia.setEditable(true);
				textFieldCiudad.setEnabled(true);
				textFieldCiudad.setEditable(true);
				
			}
	
		});
		
		
		
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ValidarCampos() == true){
					
					int opcion=JOptionPane.showConfirmDialog(null, "¿Se guadaran los cambios?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				
					if(opcion == 0){
						if (miPais.buscar(iD) != null){
							comboBoxPais.setEnabled(false);
							comboBoxProvincia.setEnabled(false);
							btnCancelar.setEnabled(false);
							btnGuardar.setEnabled(false);
							btnNuevo.setEnabled(true);
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
							textFieldCiudad.setEnabled(false);
							JOptionPane.showMessageDialog(null, "Los cambios han sido modificados con exito");
							String nombre = comboBoxPais.getSelectedItem().toString();
							miPais.setNombre(nombre);
							miPais.actualizar();
							CiudadesFrame temp = new CiudadesFrame();
							temp.setVisible(true);
		
							dispose();
							
						}
						else 
							comboBoxPais.setEnabled(false);
							comboBoxProvincia.setEnabled(false);
							btnCancelar.setEnabled(false);
							btnGuardar.setEnabled(false);
							btnNuevo.setEnabled(true);
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
							textFieldCiudad.setEnabled(false);
							JOptionPane.showMessageDialog(null, "Los cambios han sido guardados con exito");
							String nombre = comboBoxPais.getSelectedItem().toString();
							miPais.setNombre(nombre);
							miPais.insertar();
							CiudadesFrame temp = new CiudadesFrame();
							temp.setVisible(true);
							dispose();
						
						
						
							textFieldCiudad.setText(Integer.toString(iD));
					}
					else
						JOptionPane.showMessageDialog(null, "Los cambios no han sido guardados");
	
					}
				}
			
		});
		
		btnNuevo.setIcon(null);
		btnNuevo.setMaximumSize(new Dimension(20, 9));
		btnNuevo.setBounds(1, 79, 93, 31);
		contentPane.add(btnNuevo);
		
		
		
		
		
		btnModificar.setBounds(91, 79, 95, 31);
		contentPane.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxPais.setEnabled(true);
				comboBoxProvincia.setEnabled(true);
				btnNuevo.setEnabled(false);
				btnCancelar.setEnabled(true);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(true);
				btnGuardar.setEnabled(true);
				
				
				textFieldCiudad.setEnabled(true);
				textFieldCiudad.setEditable(true);
				

			}
			
		});
		
		
		
		
	
		
		btnEliminar.setBounds(91, 109, 95, 31);
		contentPane.add(btnEliminar);
		
		
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(1, 0, 50, 14);
		contentPane.add(lblPais);
		
		comboBoxPais.setBounds(29, 0, 250, 20);
		contentPane.add(comboBoxPais);
		
		
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
				comboBoxPais.getSelectedItem();
				iD = comboBoxPais.getSelectedIndex();
					
			}
			
					
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					iD = comboBoxPais.getSelectedIndex();
					miPais.eliminar();
					CiudadesFrame temp = new CiudadesFrame();
					temp.setVisible(true);
					dispose();

			}
		});	
		
		
		comboBoxProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					
			}
		});		
	
	};
	
	private boolean ValidarCampos() {
		if(comboBoxPais.getSelectedItem()==null)
		{
			
			comboBoxPais.requestFocus();
			JOptionPane.showMessageDialog(null, "El campo Pais no puede estar en blanco");
			return false;
		}
		if(comboBoxProvincia.getSelectedItem() == null)
		{
			
			comboBoxProvincia.requestFocus();
			JOptionPane.showMessageDialog(null, "El campo Provincia no puede estar en blanco");
			return false;
		}
		if(textFieldCiudad.getText().length()==0)
		{
			comboBoxProvincia.requestFocus();
			JOptionPane.showMessageDialog(null, "El campo Ciudad no puede estar en blanco");
			return false;
		}
		return true;
		}
	public void paisExiste(){
		
			ArrayList<Pais>paises = new ArrayList<>(new Pais().listar());
			
		
	}
	
	
	
}
