package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.EventQueue;

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
	JComboBox comboBoxProvincia = new JComboBox();
	JComboBox<Pais> comboBoxPais = new JComboBox<>();
	
	JButton btnCancelar = new JButton("CANCELAR");
	JButton btnNuevo = new JButton("NUEVO");
	JButton btnGuardar = new JButton("GUARDAR");
	JButton btnSalir = new JButton("SALIR");
	JButton btnModificar = new JButton("MODIFICAR");
	JButton btnEliminar = new JButton("ELIMINAR");
	JLabel lblProvincia = new JLabel("Provincia:");
	Pais miPais = new Pais();
	Provincia provincias = new Provincia();
	private List<Pais> paises = new Pais().listar();
	private List<Provincia> miProvincia = new Provincia().listar();
	private List<Ciudad> miCiudad = new Ciudad().listar();
	private Ciudad ciudades = new Ciudad();
	int iD;
	ButtonGroup group = new ButtonGroup();
	JRadioButton RBCiudad = new JRadioButton();
	JRadioButton RBProvincia = new JRadioButton();
	JRadioButton RBPais = new JRadioButton();
	
	
	
	
	
	JComboBox<Ciudad> comboBoxCiudad = new JComboBox();
	
	public CiudadesFrame() {
		
		
		group.add(RBPais);
		group.add(RBProvincia);
		group.add(RBCiudad);
		actualizarPais();
		comboBoxPais.setSelectedIndex(0);
		comboBoxPais.setEnabled(false);
		comboBoxProvincia.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnGuardar.setEnabled(false);
		btnEliminar.setEnabled(false);
		comboBoxCiudad.setEnabled(false);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteFrame.class.getResource("/Iconos_E_Imagenes/GENTE.JPG")));
		setTitle("Ciudades");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 320, 177);
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
					btnCancelar.setEnabled(false);
					btnNuevo.setEnabled(true);
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
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
		
				btnNuevo.setEnabled(false);
				btnCancelar.setEnabled(true);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnGuardar.setEnabled(true);
				
				JComboBox comboBoxProvincia = new JComboBox();
				JComboBox comboBoxPais = new JComboBox(new Pais().listar().toArray());
			}
	
		});
		
		
		
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ValidarCampos() == true){
					
					int opcion=JOptionPane.showConfirmDialog(null, "¿Se guadaran los cambios?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				
					if(opcion == 0){
						if(RBPais.isSelected()){
							 if (iD != -1){
								btnCancelar.setEnabled(false);
								btnGuardar.setEnabled(false);
								btnNuevo.setEnabled(true);
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);							
								String nombre = comboBoxPais.getSelectedItem().toString();
								miPais.setNombre(nombre);
								miPais.setId(iD);
								miPais.actualizar();
								JOptionPane.showMessageDialog(null, "Los cambios han sido modificados con exito");
								dispose();
								CiudadesFrame temp = new CiudadesFrame();
								temp.setVisible(true);
							}
							 else{
								 	btnCancelar.setEnabled(false);
									btnGuardar.setEnabled(false);
									btnNuevo.setEnabled(true);
									btnModificar.setEnabled(true);
									btnEliminar.setEnabled(true);
									miPais.setNombre(comboBoxPais.getSelectedItem().toString());
									miPais.insertar();
									JOptionPane.showMessageDialog(null, "Los cambios han sido guardados con exito");
									dispose();
									CiudadesFrame temp = new CiudadesFrame();
									temp.setVisible(true);
							 }
						}
						else if(RBProvincia.isSelected()){
							
							if (iD != -1){
								btnCancelar.setEnabled(false);
								btnGuardar.setEnabled(false);
								btnNuevo.setEnabled(true);
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);							
								String nombre = comboBoxProvincia.getSelectedItem().toString();
								provincias.setNombre(nombre);
								provincias.setPais((Pais) comboBoxPais.getSelectedItem());
								provincias.setId(iD);
								provincias.actualizar();
								JOptionPane.showMessageDialog(null, "Los cambios han sido modificados con exito");
								dispose();
								CiudadesFrame temp = new CiudadesFrame();
								temp.setVisible(true);
							}
							else{
								btnCancelar.setEnabled(false);
								btnGuardar.setEnabled(false);
								btnNuevo.setEnabled(true);
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);
								provincias.setNombre(comboBoxProvincia.getSelectedItem().toString());
								System.out.println(comboBoxProvincia.getSelectedItem().toString());
								provincias.setPais((Pais) comboBoxPais.getSelectedItem());
								provincias.setId(iD);
								provincias.insertar();
								JOptionPane.showMessageDialog(null, "Los cambios han sido guardados con exito");
								dispose();
								CiudadesFrame temp = new CiudadesFrame();
								temp.setVisible(true);
							}
						}
						else if(RBCiudad.isSelected()){
							iD = (comboBoxProvincia.getSelectedIndex());
							if (iD != -1){
								btnCancelar.setEnabled(false);
								btnGuardar.setEnabled(false);
								btnNuevo.setEnabled(true);
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);							
								String nombre = comboBoxCiudad.getSelectedItem().toString();
								ciudades.setNombre(nombre);
								ciudades.setProvincia((Provincia) comboBoxProvincia.getSelectedItem());
								ciudades.setId(iD);
								ciudades.actualizar();
								JOptionPane.showMessageDialog(null, "Los cambios han sido modificados con exito");
								dispose();
								CiudadesFrame temp = new CiudadesFrame();
								temp.setVisible(true);
							}
							else{
								btnCancelar.setEnabled(false);
								btnGuardar.setEnabled(false);
								btnNuevo.setEnabled(true);
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);
								ciudades.setNombre(comboBoxCiudad.getSelectedItem().toString());
								ciudades.setProvincia((Provincia) comboBoxProvincia.getSelectedItem());
								ciudades.setId(iD);
								ciudades.insertar();
								JOptionPane.showMessageDialog(null, "Los cambios han sido guardados con exito");
								dispose();
								CiudadesFrame temp = new CiudadesFrame();
								temp.setVisible(true);
							}
							
						}
						
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
				
				btnNuevo.setEnabled(false);
				btnCancelar.setEnabled(true);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(true);
				btnGuardar.setEnabled(true);
				comboBoxPais.setEditable(false);
			}
			
		});
		
		btnEliminar.setBounds(91, 109, 95, 31);
		contentPane.add(btnEliminar);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(1, 0, 50, 14);
		contentPane.add(lblPais);
		
		comboBoxPais.setBounds(29, 0, 250, 20);
		contentPane.add(comboBoxPais);
		
		
		RBPais.setSelected(true);
		RBPais.setBounds(280,5, 20,12);
		contentPane.add(RBPais);
		
		lblProvincia.setBounds(1, 20, 60, 14);
		contentPane.add(lblProvincia);
		
		
		RBProvincia.setSelected(true);
		RBProvincia.setBounds(280,22, 20,12);
		contentPane.add(RBProvincia);
		
		comboBoxProvincia.setBounds(55, 20, 224, 20);
		contentPane.add(comboBoxProvincia );
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(1, 45, 60, 14);
		contentPane.add(lblCiudad);
		
		
		comboBoxCiudad.setBounds(45, 40, 234, 20);
		comboBoxCiudad.setEditable(false);
		contentPane.add(comboBoxCiudad);	
		
		
		RBCiudad.setSelected(false);
		RBCiudad.setBounds(280,42, 20,12);
		contentPane.add(RBCiudad);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 67, 547, 14);
		contentPane.add(separator_1);
		
		
		
		 RBPais.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					comboBoxPais.setEnabled(true);	
					comboBoxPais.setEditable(true);		
					comboBoxProvincia.setEditable(false);			
					comboBoxProvincia.setEnabled(false);
					comboBoxCiudad.setEditable(false);	
					comboBoxCiudad.setEnabled(false);
				}		
			}); 
		 
		 RBProvincia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					comboBoxPais.setEnabled(false);	
					comboBoxPais.setEditable(false);		
					comboBoxProvincia.setEditable(true);			
					comboBoxProvincia.setEnabled(true);
					comboBoxCiudad.setEditable(false);	
					comboBoxCiudad.setEnabled(false);	
				}		
			}); 
		 
		 RBCiudad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					comboBoxPais.setEnabled(false);	
					comboBoxPais.setEditable(false);		
					comboBoxProvincia.setEditable(false);			
					comboBoxProvincia.setEnabled(false);
					comboBoxCiudad.setEditable(true);	
					comboBoxCiudad.setEnabled(true);
				}		
			}); 
		
		comboBoxPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				iD = comboBoxPais.getSelectedIndex();
				
				if (iD != -1)
					rellenaProvincia((Pais) comboBoxPais.getSelectedItem());	
			}		
		});
		
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (RBPais.isSelected()){
						
						int opcion=JOptionPane.showConfirmDialog(null, "Si borra " + comboBoxPais.getSelectedItem() +  " sus provincias y ciudades seran borradas ¿Seguro que desea borrar el pais?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
						
						if(opcion == 0){
							Pais temp = (Pais) comboBoxPais.getSelectedItem();
							temp.eliminar();
							
							Provincia temp2 = (Provincia) comboBoxPais.getSelectedItem();
							List<Provincia> provinciasDePais = new ArrayList<>();
							miProvincia.forEach(p -> {
								if(p.getPais().getId() == temp2.getId())
									temp2.buscar(p.getId());
									temp2.eliminar();
							});
							
							JOptionPane.showMessageDialog(null,  comboBoxPais.getSelectedItem() + " fue borrado con exito");
							dispose();
							CiudadesFrame temp1 = new CiudadesFrame();
							temp1.setVisible(true);
						}
						else{
							JOptionPane.showMessageDialog(null, "La accion fue cancelada");
						}
					}
					else if (RBProvincia.isSelected()){
						int opcion=JOptionPane.showConfirmDialog(null, "Si borra " + comboBoxProvincia.getSelectedItem() +" sus ciudades seran borradas ¿Seguro que desea borrar la provincia?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
						
						if(opcion == 0){
						Provincia temp = (Provincia)comboBoxProvincia.getSelectedItem();
						temp.eliminar();
						
						Ciudad temp2 = (Ciudad) comboBoxProvincia.getSelectedItem();
						List<Ciudad> ciudadesDeProvincia = new ArrayList<>();
						miCiudad.forEach(p -> {
							if(p.getProvincia().getId() == temp.getId())
								temp2.eliminar();
						});
						
						JOptionPane.showMessageDialog(null,  comboBoxProvincia.getSelectedItem() + " fue borrado con exito");
						dispose();
						CiudadesFrame temp1 = new CiudadesFrame();
						temp1.setVisible(true);
						}
						else{
							JOptionPane.showMessageDialog(null, "La accion fue cancelada");
						}
					}
					else if (RBCiudad.isSelected()){
						int opcion=JOptionPane.showConfirmDialog(null, "Se " +comboBoxCiudad.getSelectedItem() + " sus ciudades seran borradas ¿Seguro que desea borrar la provincia?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
						
						if(opcion == 0){
							Ciudad temp = (Ciudad)comboBoxCiudad.getSelectedItem();
							temp.eliminar();
							JOptionPane.showMessageDialog(null,  comboBoxCiudad.getSelectedItem() + " fue borrado con exito");
							dispose();
							CiudadesFrame temp1 = new CiudadesFrame();
							temp1.setVisible(true);
						}
						else{
							JOptionPane.showMessageDialog(null, "La accion fue cancelada");
						}
					}
			}
		});	
		
		
		comboBoxProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				iD = comboBoxProvincia.getSelectedIndex();

				
				if (iD != -1)
					rellenaCiudad((Provincia) comboBoxProvincia.getSelectedItem());
			
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
		if(comboBoxCiudad.getSelectedItem() == null)
		{
			comboBoxProvincia.requestFocus();
			JOptionPane.showMessageDialog(null, "El campo Ciudad no puede estar en blanco");
			return false;
		}
		return true;
	}
	
	private void rellenaCiudad(Provincia seleccionEnComboBoxProvincia) {
		comboBoxCiudad.removeAllItems();
		List<Ciudad> provinciasDeCiudad = new ArrayList<>();
		miCiudad.forEach(p -> {
			if(p.getProvincia().getId() == seleccionEnComboBoxProvincia.getId())
				comboBoxCiudad.addItem(p);
		});
		
	}
	
	private void rellenaProvincia(Pais seleccionEnComboBoxPais) {
		comboBoxProvincia.removeAllItems();
		List<Provincia> provinciasDePais = new ArrayList<>();
		miProvincia.forEach(p -> {
			if(p.getPais().getId() == seleccionEnComboBoxPais.getId())
				comboBoxProvincia.addItem(p);
		});
		
	}

	
	public void actualizarPais(){
		comboBoxPais = new JComboBox(new Pais().listar().toArray(new Pais[0]));
		rellenaProvincia((Pais)comboBoxPais.getSelectedItem());
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					CiudadesFrame frame = new CiudadesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
}