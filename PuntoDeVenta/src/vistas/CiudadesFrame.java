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
import javax.swing.JComponent;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




public class CiudadesFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JComboBox<Provincia> comboBoxProvincia = new JComboBox<>();
	JComboBox<Pais> comboBoxPais = new JComboBox<>();
	JComboBox<Ciudad> comboBoxCiudad = new JComboBox<>();
	JButton btnCancelar = new JButton("CANCELAR");
	JButton btnNuevo = new JButton("NUEVO");
	JButton btnGuardar = new JButton("GUARDAR");
	JButton btnSalir = new JButton("SALIR");
	JButton btnModificar = new JButton("MODIFICAR");
	JButton btnEliminar = new JButton("ELIMINAR");
	JLabel lblPais = new JLabel("Pais:");
	JLabel lblProvincia = new JLabel("Provincia:");
	JLabel lblCiudad = new JLabel("Ciudad:");
	Pais miPais = new Pais();
	private Ciudad ciudades = new Ciudad();
	Provincia provincias = new Provincia();
	private List<Pais> paises = new Pais().listar();
	private List<Provincia> miProvincia = new Provincia().listar();
	private List<Ciudad> miCiudad = new Ciudad().listar();
	
	int iD;
	ButtonGroup group = new ButtonGroup();
	JRadioButton RBCiudad = new JRadioButton();
	JRadioButton RBProvincia = new JRadioButton();
	JRadioButton RBPais = new JRadioButton();
	
	
	
	
	
	
	
	public CiudadesFrame() {
		
		
		group.add(RBPais);
		RBPais.setEnabled(false);
		RBProvincia.setEnabled(false);
		RBCiudad.setEnabled(false);
		group.add(RBProvincia);
		RBPais.setSelected(false);
		group.add(RBCiudad);
		actualizarPais();

		Desabilitar();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteFrame.class.getResource("/Iconos_E_Imagenes/GENTE.JPG")));
		setTitle("Ciudades");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 480, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNuevo.setIcon(null);
		btnNuevo.setMaximumSize(new Dimension(20, 9));
		btnNuevo.setBounds(350, 15, 90, 20);
		contentPane.add(btnNuevo);
		
		btnGuardar.setHorizontalAlignment(SwingConstants.TRAILING);
		btnGuardar.setBounds(350, 40, 90, 20);
		contentPane.add(btnGuardar);
		
		btnModificar.setHorizontalAlignment(SwingConstants.TRAILING);
		btnModificar.setBounds(350, 65, 100, 20);
		contentPane.add(btnModificar);
		
		btnEliminar.setBounds(350, 90, 90, 20);
		contentPane.add(btnEliminar);
		
		btnCancelar.setBounds(350, 115, 100, 20);
		contentPane.add(btnCancelar);
		
		btnSalir.setBounds(350, 140, 75, 20);
		contentPane.add(btnSalir);
		
		
		RBPais.setBounds(290, 18, 20,12);
		contentPane.add(RBPais);
		
		lblPais.setBounds(6, 15, 50, 14);
		contentPane.add(lblPais);
		
		comboBoxPais.setBounds(36, 15, 250, 20);
		contentPane.add(comboBoxPais);
		
		lblProvincia.setBounds(6, 40, 60, 14);
		contentPane.add(lblProvincia);
		
		
		RBProvincia.setBounds(290, 43, 20,12);
		contentPane.add(RBProvincia);
		
		comboBoxProvincia.setBounds(63, 40, 224, 20);
		contentPane.add(comboBoxProvincia );
		
		
		RBCiudad.setBounds(290, 68, 20,12);
		contentPane.add(RBCiudad);
		
		lblCiudad.setBounds(6, 65, 60, 14);
		contentPane.add(lblCiudad);
		
		comboBoxCiudad.setBounds(50, 65, 234, 20);
		comboBoxCiudad.setEditable(false);
		contentPane.add(comboBoxCiudad);	
		
		
		
		
		JSeparator separator_2 = new JSeparator(SwingConstants.VERTICAL);
		separator_2.setBounds(320, 0, 300, 175);
		contentPane.add(separator_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 90, 320, 14);
		contentPane.add(separator_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(320, 175, 145, 14);
		contentPane.add(separator_3);
		
	
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion =JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

				if (opcion == 0){
				dispose();
				}
			}
		});
		
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Modificar();
				
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcion=JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar la accion?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

				if (opcion == 0){
					comboBoxPais.setEnabled(false);
					comboBoxProvincia.setEnabled(false);
					comboBoxCiudad.setEnabled(false);
					RBPais.setEnabled(false);
					RBProvincia.setEnabled(false);
					RBCiudad.setEnabled(false);
					btnCancelar.setEnabled(true);
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					btnGuardar.setEnabled(true);
					btnCancelar.setEnabled(false);
					btnNuevo.setEnabled(true);
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(false);
					btnGuardar.setEnabled(false);
					group.clearSelection();
					
				}				
			}
		});
				
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Modificar();
				btnEliminar.setEnabled(false);
				btnGuardar.setEnabled(false);
				
				

			}
	
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
				}
			
		});
		
		 RBPais.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					if (btnEliminar.isEnabled() == true){
						
					}
					else
						btnGuardar.setEnabled(true);
					
					habilitaPais();
				}		
			}); 
		 
		 RBProvincia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					

					if (btnEliminar.isEnabled() == true){
						
					}
					else
						btnGuardar.setEnabled(true);
					
					habilitaProvincia();
				}		
			}); 
		 
		 RBCiudad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					

					if (btnEliminar.isEnabled() == true){
						
					}
					else
						btnGuardar.setEnabled(true);
					
					habilitaCiudad();
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
				
				if (RBPais.isSelected() == true  || RBProvincia.isSelected() == true || RBCiudad.isSelected() == true){
					eliminar();
				}
				else
					JOptionPane.showMessageDialog(null, "Debe seleccionar que desea borrar");
					
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
	
	public void habilitaPais(){
		comboBoxPais.setEnabled(true);	
		comboBoxPais.setEditable(true);		
		comboBoxProvincia.setEditable(false);			
		comboBoxProvincia.setEnabled(false);
		comboBoxCiudad.setEditable(false);	
		comboBoxCiudad.setEnabled(false);
	}
	
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
		
		miCiudad.forEach(p -> {
			if(p.getProvincia().getId() == seleccionEnComboBoxProvincia.getId())
				comboBoxCiudad.addItem(p);
		});
		
	}
	
	private void rellenaProvincia(Pais seleccionEnComboBoxPais) {
		comboBoxProvincia.removeAllItems();
		miProvincia.forEach(p -> {
			if(p.getPais().getId() == seleccionEnComboBoxPais.getId())
				comboBoxProvincia.addItem(p);
		});
		
	}

	
	public void habilitaCiudad(){
		comboBoxPais.setEnabled(false);	
		comboBoxPais.setEditable(false);		
		comboBoxProvincia.setEditable(false);			
		comboBoxProvincia.setEnabled(false);
		comboBoxCiudad.setEditable(true);	
		comboBoxCiudad.setEnabled(true);
	}
	
	public void habilitaProvincia(){
		comboBoxPais.setEnabled(false);	
		comboBoxPais.setEditable(false);		
		comboBoxProvincia.setEditable(true);			
		comboBoxProvincia.setEnabled(true);
		comboBoxCiudad.setEditable(false);	
		comboBoxCiudad.setEnabled(false);
	}
	
	public void eliminar(){
		if (RBPais.isSelected()){
			
			int opcion=JOptionPane.showConfirmDialog(null, "Si borra " + comboBoxPais.getSelectedItem() +  " sus provincias y ciudades seran borradas ¿Seguro que desea borrar el pais?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			
			if(opcion == 0){
				Pais temp = (Pais) comboBoxPais.getSelectedItem();
				temp.eliminar();
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
			int opcion=JOptionPane.showConfirmDialog(null, "Se borrara " +comboBoxCiudad.getSelectedItem() + " ¿Seguro que desea borrar esta ciudad?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			
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
	
	public void guardar(){
		if(ValidarCampos() == true){
			
			int opcion=JOptionPane.showConfirmDialog(null, "¿Se guadaran los cambios?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		
			if(opcion == 0){
				if(RBPais.isSelected()){
					
					 if (iD != -1){
												
						String nombre = comboBoxPais.getSelectedItem().toString();
						miPais.setNombre(nombre);
						miPais.setId(((Pais) comboBoxPais.getSelectedItem()).getId());
						miPais.actualizar();
						JOptionPane.showMessageDialog(null, "Los cambios han sido modificados con exito");	
						dispose();
						CiudadesFrame temp = new CiudadesFrame();
						temp.setVisible(true);
					
					 }
					 else{

							miPais.buscar(iD);
							miPais.setNombre(comboBoxPais.getSelectedItem().toString());
							miPais.insertar();
							JOptionPane.showMessageDialog(null, "Los cambios han sido guardados con exito");
							dispose();
							CiudadesFrame temp = new CiudadesFrame();
							temp.setVisible(true);
						
					 }
				}
				else if(RBProvincia.isSelected()){
						
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
				else if(RBCiudad.isSelected()){
					iD = (comboBoxProvincia.getSelectedIndex());
					
					
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
			else
				JOptionPane.showMessageDialog(null, "Los cambios no han sido guardados");
				Desabilitar();

			}
	}
	
	public void Modificar(){
		RBPais.setEnabled(true);
		RBProvincia.setEnabled(true);
		RBCiudad.setEnabled(true);
		btnNuevo.setEnabled(false);
		btnCancelar.setEnabled(true);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(true);
		btnGuardar.setEnabled(false);
	}
	
	private void Desabilitar(){
		
		comboBoxPais.setEnabled(false);
		comboBoxProvincia.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnGuardar.setEnabled(false);
		btnEliminar.setEnabled(false);
		comboBoxCiudad.setEnabled(false);
	}
	

	public void actualizarPais(){
		comboBoxPais = new JComboBox(new Pais().listar().toArray(new Pais[0]));
		comboBoxPais.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		rellenaProvincia((Pais)comboBoxPais.getSelectedItem());
		rellenaCiudad((Provincia)comboBoxProvincia.getSelectedItem());
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