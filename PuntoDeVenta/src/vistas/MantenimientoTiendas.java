package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelos.Ciudad;
import modelos.Pais;
import modelos.Provincia;
import modelos.Tienda;
import principal.ModoFormulario;
import principal.Utilidades;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class MantenimientoTiendas extends JDialog {
	private Tienda tienda;
	private ModoFormulario modo;
	private JTextField txtNombre;
	private JTextField txtSlogan;
	private JTextField txtDireccion;
	private JButton btnFinalizar;
	private JButton btnLimpiarCampos;
	private JComboBox<Pais> cbPais;
	private JComboBox<Provincia> cbProvincia;
	private JComboBox<Ciudad> cbCiudad;
	private List<Pais> paises = new ArrayList<>();
	private List<Provincia> provincias = new ArrayList<>();
	private List<Ciudad> ciudades = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MantenimientoTiendas dialog = new MantenimientoTiendas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setModo(ModoFormulario mode) {
		modo = mode;
		
		switch(mode) {
		
		default:
		case Agregar:
			cbPais.removeAllItems();
			cbProvincia.removeAllItems();
			cbCiudad.removeAllItems();
			for (Pais pais : paises) {
				cbPais.addItem(pais);
			}
			txtNombre.setText("");
			txtDireccion.setText("");
			txtSlogan.setText("");
			cbPais.setEnabled(true);
			cbProvincia.setEnabled(false);
			cbCiudad.setEnabled(false);
			btnFinalizar.setText("Agregar");
			break;
			
		case Editar:
			cbPais.removeAllItems();
			cbProvincia.removeAllItems();
			cbCiudad.removeAllItems();
			Provincia pro = tienda.getCiudad().getProvincia();
			Pais pais = pro.getPais();
			btnFinalizar.setText("Guardar Cambios");
			
			int index = -1;
			for (int i = 0; i < paises.size(); i++) {
				cbPais.addItem(paises.get(i));
				if(paises.get(i).getId() == pais.getId())
					index = i;
			}
			cbPais.setSelectedIndex(index);
			
			for (int i = 0; i < provincias.size(); i++) {
				cbProvincia.addItem(provincias.get(i));
				if(provincias.get(i).getId() == pro.getId())
					index = i;
			}
			cbProvincia.setSelectedIndex(index);
			
			for (int i = 0; i < ciudades.size(); i++) {
				cbCiudad.addItem(ciudades.get(i));
				if(ciudades.get(i).getId() == pro.getId())
					index = i;
			}
			cbCiudad.setSelectedIndex(index);
			txtDireccion.setText(tienda.getDireccion());
			txtNombre.setText(tienda.getNombre());
			txtSlogan.setText(tienda.getSlogan());
			break;
			
		case Eliminar:
			btnLimpiarCampos.setEnabled(false);
			cbPais.setEnabled(false);
			cbProvincia.setEnabled(false);
			cbCiudad.setEnabled(false);
			btnFinalizar.setText("Eliminar");
			break;
		
		}
	}
	
	public void agregarTienda() {
		this.tienda = new Tienda();
		setModo(ModoFormulario.Agregar);
	}
	
	public void editarTienda(Tienda tienda) {
		this.tienda = tienda;
		setModo(ModoFormulario.Editar);
	}
	
	public void eliminarTienda(Tienda tienda) {
		this.tienda = tienda;
		setModo(ModoFormulario.Eliminar);
	}

	/**
	 * Create the dialog.
	 */
	public MantenimientoTiendas() {
		setTitle("Mantenimiento de Tiendas");
		setBounds(100, 100, 515, 259);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Nombre");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setBounds(7, 32, 76, 16);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Slogan");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_1.setBounds(7, 60, 77, 20);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Direcci\u00F3n");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_2.setBounds(7, 91, 77, 16);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Ciudad");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_3.setBounds(6, 187, 77, 16);
		getContentPane().add(label_3);
		
		btnFinalizar = new JButton("Agregar");
		btnFinalizar.setBounds(328, 174, 157, 36);
		getContentPane().add(btnFinalizar);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(93, 27, 392, 28);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtSlogan = new JTextField();
		txtSlogan.setColumns(10);
		txtSlogan.setBounds(94, 57, 392, 28);
		getContentPane().add(txtSlogan);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(94, 86, 392, 28);
		getContentPane().add(txtDireccion);
		
		cbCiudad = new JComboBox<>();
		cbCiudad.setEnabled(false);
		cbCiudad.setBounds(93, 183, 225, 26);
		getContentPane().add(cbCiudad);
		
		JLabel lblPas = new JLabel("Pa\u00EDs");
		lblPas.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPas.setBounds(6, 122, 77, 16);
		getContentPane().add(lblPas);
		
		cbPais = new JComboBox<>();
		cbPais.setBounds(93, 118, 225, 26);
		getContentPane().add(cbPais);
		
		JLabel lblProvinciaOEstado = new JLabel("Provincia");
		lblProvinciaOEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProvinciaOEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProvinciaOEstado.setBounds(6, 154, 77, 16);
		getContentPane().add(lblProvinciaOEstado);
		
		cbProvincia = new JComboBox<>();
		cbProvincia.setEnabled(false);
		cbProvincia.setBounds(93, 150, 225, 26);
		getContentPane().add(cbProvincia);
		
		btnLimpiarCampos = new JButton("Limpiar Campos");
		btnLimpiarCampos.setBounds(328, 126, 157, 36);
		getContentPane().add(btnLimpiarCampos);
		
		
		Utilidades.loadUbicaciones(paises, provincias, ciudades);
	}
}
