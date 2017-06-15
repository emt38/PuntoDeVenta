package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelos.Pais;
import modelos.Provincia;
import modelos.Ciudad;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import principal.RobotFingers;

public class CiudadesFrame extends JFrame {

	//Declaracion de Botones, Combo Box, Objetos, entre otros.
	private JPanel contentPane;
	private JComboBox<Provincia> comboBoxProvincia = new JComboBox<>();
	private static JComboBox<Pais> comboBoxPais = new JComboBox<>();
	private JComboBox<Ciudad> comboBoxCiudad = new JComboBox<>();
	private JButton btnCancelar = new JButton("CANCELAR");
	private static JButton btnNuevo = new JButton("NUEVO");
	private static JButton btnGuardar = new JButton("GUARDAR");
	private JButton btnSalir = new JButton("SALIR");
	private JButton btnModificar = new JButton("MODIFICAR");
	private JButton btnEliminar = new JButton("ELIMINAR");
	private JLabel lblPais = new JLabel("Pais:");
	private JLabel lblProvincia = new JLabel("Provincia:");
	private JLabel lblCiudad = new JLabel("Ciudad:");
	private Pais miPais = new Pais();
	private Ciudad ciudades = new Ciudad();
	private Provincia provincias = new Provincia();
	private List<Provincia> miProvincia = new Provincia().listar();
	private List<Ciudad> miCiudad = new Ciudad().listar();
	private int iD;
	private ButtonGroup group = new ButtonGroup();
	private JRadioButton RBCiudad = new JRadioButton();
	private JRadioButton RBProvincia = new JRadioButton();
	private static JRadioButton RBPais = new JRadioButton();
	static RobotFingers miRobot = new RobotFingers();
	
	
	public CiudadesFrame() {
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteFrame.class.getResource("/Iconos_E_Imagenes/GENTE.JPG")));
		setTitle("Ciudades");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 480, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		group.add(RBPais);
		RBPais.setEnabled(false);
		RBProvincia.setEnabled(false);
		RBCiudad.setEnabled(false);
		group.add(RBProvincia);
		RBPais.setSelected(false);
		group.add(RBCiudad);

		actualizarPais();
		Desabilitar();
		
		btnNuevo.setIcon(null);
		btnNuevo.setMaximumSize(new Dimension(20, 9));
		btnNuevo.setBounds(350, 15, 90, 20);
		contentPane.add(btnNuevo);
		
		
		getBtnGuardar().setHorizontalAlignment(SwingConstants.TRAILING);
		getBtnGuardar().setBounds(350, 40, 90, 20);
		contentPane.add(getBtnGuardar());
		
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
		
		getComboBoxPais().setBounds(36, 15, 250, 20);
		contentPane.add(getComboBoxPais());
		
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
					getComboBoxPais().setEnabled(false);
					comboBoxProvincia.setEnabled(false);
					comboBoxCiudad.setEnabled(false);
					RBPais.setEnabled(false);
					RBProvincia.setEnabled(false);
					RBCiudad.setEnabled(false);
					btnCancelar.setEnabled(true);
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					getBtnGuardar().setEnabled(true);
					btnCancelar.setEnabled(false);
					btnNuevo.setEnabled(true);
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(false);
					getBtnGuardar().setEnabled(false);
					group.clearSelection();
					
				}				
			}
		});
				
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Modificar();
				btnEliminar.setEnabled(false);
				getBtnGuardar().setEnabled(false);
			}
	
		});
		
		getBtnGuardar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				guardar();
				}
			
		});
		
		 RBPais.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					if (btnEliminar.isEnabled() == true){
						
					}
					else
						getBtnGuardar().setEnabled(true);
					
					habilitaPais();
				}		
			}); 
		 
		 RBProvincia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					

					if (btnEliminar.isEnabled() == true){
						
					}
					else
						getBtnGuardar().setEnabled(true);
					
					habilitaProvincia();
				}		
			}); 
		 
		 RBCiudad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					

					if (btnEliminar.isEnabled() == true){
						
					}
					else
						getBtnGuardar().setEnabled(true);
					
					habilitaCiudad();
				}		
			}); 
		
		getComboBoxPais().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				iD = getComboBoxPais().getSelectedIndex();
				
				if (iD != -1)
					rellenaProvincia((Pais) getComboBoxPais().getSelectedItem());	
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
	


	// Metodo para validar los campos 
	private boolean ValidarCampos() {
		if(getComboBoxPais().getSelectedItem()==null)
		{
			getComboBoxPais().requestFocus();
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
	
	
	
	//Metodos para llenar el Combo Box de provincia
	private void rellenaProvincia(Pais seleccionEnComboBoxPais) {
		comboBoxProvincia.removeAllItems();
		miProvincia.forEach(p -> {
			if(p.getPais().getId() == seleccionEnComboBoxPais.getId())
				comboBoxProvincia.addItem(p);
		});
			
	}

	//Metodos para llenar el Combo Box de Ciudad.
	private void rellenaCiudad(Provincia seleccionEnComboBoxProvincia) {
		comboBoxCiudad.removeAllItems();
		
		miCiudad.forEach(p -> {
			if(p.getProvincia().getId() == seleccionEnComboBoxProvincia.getId())
				comboBoxCiudad.addItem(p);
		});
		
	}
	
	//Metodos para habilitar el Combo Box de Pais
	public void habilitaPais(){
		getComboBoxPais().setEnabled(true);	
		getComboBoxPais().setEditable(true);		
		comboBoxProvincia.setEditable(false);			
		comboBoxProvincia.setEnabled(false);
		comboBoxCiudad.setEditable(false);	
		comboBoxCiudad.setEnabled(false);
	}
	
	//Metodos para habilitar el Combo Box de Provincia.
	public void habilitaProvincia(){
		getComboBoxPais().setEnabled(false);	
		getComboBoxPais().setEditable(false);		
		comboBoxProvincia.setEditable(true);			
		comboBoxProvincia.setEnabled(true);
		comboBoxCiudad.setEditable(false);	
		comboBoxCiudad.setEnabled(false);
	}
		
	//Metodos para habilitar el Combo Box de Ciudad.
	public void habilitaCiudad(){
		getComboBoxPais().setEnabled(false);	
		getComboBoxPais().setEditable(false);		
		comboBoxProvincia.setEditable(false);			
		comboBoxProvincia.setEnabled(false);
		comboBoxCiudad.setEditable(true);	
		comboBoxCiudad.setEnabled(true);
	}
	
	//Metodo para el Boton Eliminar.
	public void eliminar(){
		if (RBPais.isSelected()){
			
			int opcion=JOptionPane.showConfirmDialog(null, "Si borra " + getComboBoxPais().getSelectedItem() +  " sus provincias y ciudades seran borradas ¿Seguro que desea borrar el pais?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			
			if(opcion == 0){
				Pais temp = (Pais) getComboBoxPais().getSelectedItem();
				temp.eliminar();
				JOptionPane.showMessageDialog(null,  getComboBoxPais().getSelectedItem() + " fue borrado con exito");
				dispose();
				CiudadesFrame temp1 = new CiudadesFrame();
				temp1.setLocationRelativeTo(null);
				btnNuevo.setEnabled(true);
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
			temp1.setLocationRelativeTo(null);
			btnNuevo.setEnabled(true);
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
				temp1.setLocationRelativeTo(null);
				btnNuevo.setEnabled(true);
				temp1.setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "La accion fue cancelada");
			}
		}
	}
	
	//Metodo para el Boton Guardar.
	public void guardar(){
		if(ValidarCampos() == true){
			
			int opcion=JOptionPane.showConfirmDialog(null, "¿Se guadaran los cambios?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		
			if(opcion == 0){
				if(RBPais.isSelected()){
					
					 if (iD != -1){
												
						String nombre = getComboBoxPais().getSelectedItem().toString();
						miPais.setNombre(nombre);
						miPais.setId(((Pais) getComboBoxPais().getSelectedItem()).getId());
						miPais.actualizar();
						JOptionPane.showMessageDialog(null, "Los cambios han sido modificados con exito");	
						dispose();
						CiudadesFrame temp = new CiudadesFrame();
						temp.setLocationRelativeTo(null);
						btnNuevo.setEnabled(true);
						temp.setVisible(true);
					
					 }
					 else{

							miPais.buscar(iD);
							miPais.setNombre(getComboBoxPais().getSelectedItem().toString());
							miPais.insertar();
							JOptionPane.showMessageDialog(null, "Los cambios han sido guardados con exito");
							dispose();
							CiudadesFrame temp = new CiudadesFrame();
							temp.setLocationRelativeTo(null);
							btnNuevo.setEnabled(true);
							temp.setVisible(true);
						
					 }
				}
				else if(RBProvincia.isSelected()){
						
						provincias.setNombre(comboBoxProvincia.getSelectedItem().toString());
						System.out.println(comboBoxProvincia.getSelectedItem().toString());
						provincias.setPais((Pais) getComboBoxPais().getSelectedItem());
						provincias.setId(iD);
						provincias.insertar();
						JOptionPane.showMessageDialog(null, "Los cambios han sido guardados con exito");
						dispose();
						CiudadesFrame temp = new CiudadesFrame();
						temp.setLocationRelativeTo(null);
						btnNuevo.setEnabled(true);
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
						temp.setLocationRelativeTo(null);
						btnNuevo.setEnabled(true);
						temp.setVisible(true);
				}
				
			}
			else
				JOptionPane.showMessageDialog(null, "Los cambios no han sido guardados");
				

			}
	}
	
	//Metodo para el Boton Modificar
	public void Modificar(){
		RBPais.setEnabled(true);
		RBProvincia.setEnabled(true);
		RBCiudad.setEnabled(true);
		btnNuevo.setEnabled(false);
		btnCancelar.setEnabled(true);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(true);
		getBtnGuardar().setEnabled(false);
	}
	
	//Metodo para desabilitar los Combo Box y Botones.
	private void Desabilitar(){
		
		getComboBoxPais().setEnabled(false);
		comboBoxProvincia.setEnabled(false);
		btnCancelar.setEnabled(false);
		getBtnGuardar().setEnabled(false);
		btnEliminar.setEnabled(false);
		comboBoxCiudad.setEnabled(false);
	}

	//Metodo Para actualizar el Combo Box de Pais.
	public void actualizarPais(){
		setComboBoxPais(new JComboBox(new Pais().listar().toArray(new Pais[0])));
		getComboBoxPais().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		rellenaProvincia((Pais)getComboBoxPais().getSelectedItem());
		rellenaCiudad((Provincia)comboBoxProvincia.getSelectedItem());
	}

	public static JRadioButton getRBPais() {
		return RBPais;
	}



	public static void setRBPais(JRadioButton rBPais) {
		RBPais = rBPais;
	}



	public static JComboBox<Pais> getComboBoxPais() {
		return comboBoxPais;
	}



	public static void setComboBoxPais(JComboBox<Pais> comboBoxPais) {
		CiudadesFrame.comboBoxPais = comboBoxPais;
	}



	public static JButton getBtnGuardar() {
		return btnGuardar;
	}



	public static void setBtnGuardar(JButton btnGuardar) {
		CiudadesFrame.btnGuardar = btnGuardar;
	}



	public static Component getBtnNuevo() {
	
		return btnNuevo;
	}
}