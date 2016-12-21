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
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MantenimientoTiendas extends JDialog {
	private Tienda tienda;
	private Tienda back;
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
	private List<String> errores = new ArrayList<>();

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
			cbPais.addItem(new Pais(0, "Seleccione un País"));
			for (Pais pais : paises) {
				cbPais.addItem(pais);
			}
			txtNombre.setText("");
			txtDireccion.setText("");
			txtSlogan.setText("");
			txtNombre.setEnabled(true);
			txtDireccion.setEnabled(true);
			txtSlogan.setEnabled(true);
			cbPais.setEnabled(true);
			cbProvincia.setEnabled(false);
			cbCiudad.setEnabled(false);
			btnFinalizar.setText("Agregar");
			break;
			
		case Visualizar:
			cbPais.removeAllItems();
			cbProvincia.removeAllItems();
			cbCiudad.removeAllItems();
			Provincia prov = tienda.getCiudad().getProvincia();
			Pais pa = prov.getPais();
			cbPais.addItem(pa);
			cbCiudad.addItem(tienda.getCiudad());
			cbProvincia.addItem(prov);
			
			cbPais.setSelectedIndex(0);
			cbCiudad.setSelectedIndex(0);
			cbProvincia.setSelectedIndex(0);
			txtDireccion.setText(tienda.getDireccion());
			txtNombre.setText(tienda.getNombre());
			txtSlogan.setText(tienda.getSlogan());
			
			cbPais.setEnabled(false);
			cbCiudad.setEnabled(false);
			cbProvincia.setEnabled(false);
			txtDireccion.setEnabled(false);
			txtNombre.setEnabled(false);
			txtSlogan.setEnabled(false);
			btnLimpiarCampos.setEnabled(false);
			btnFinalizar.setText("Cerrar");
			break;
			
		case Editar:
			cbPais.removeAllItems();
			cbProvincia.removeAllItems();
			cbCiudad.removeAllItems();
			Provincia pro = back.getCiudad().getProvincia();
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
			txtDireccion.setText(back.getDireccion());
			txtNombre.setText(back.getNombre());
			txtSlogan.setText(back.getSlogan());
			break;
			
		case Eliminar:
			cbPais.removeAllItems();
			cbProvincia.removeAllItems();
			cbCiudad.removeAllItems();
			Provincia provd = tienda.getCiudad().getProvincia();
			Pais pad = provd.getPais();
			cbPais.addItem(pad);
			cbCiudad.addItem(tienda.getCiudad());
			cbProvincia.addItem(provd);
			
			cbPais.setSelectedIndex(0);
			cbCiudad.setSelectedIndex(0);
			cbProvincia.setSelectedIndex(0);
			txtDireccion.setText(tienda.getDireccion());
			txtNombre.setText(tienda.getNombre());
			txtSlogan.setText(tienda.getSlogan());
			
			cbPais.setEnabled(false);
			cbCiudad.setEnabled(false);
			cbProvincia.setEnabled(false);
			txtDireccion.setEnabled(false);
			txtNombre.setEnabled(false);
			txtSlogan.setEnabled(false);
			btnLimpiarCampos.setEnabled(false);
			btnFinalizar.setText("Eliminar");
			break;
		
		}
	}
	
	public void agregarTienda() {
		this.tienda = new Tienda();
		setModo(ModoFormulario.Agregar);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setVisible(true);
	}
	
	public void editarTienda(Tienda tienda) {
		if(!tienda.equals(this.tienda)) {
			this.tienda = tienda;
			this.back = new Tienda(tienda.getId(), tienda.getNombre(), tienda.getDireccion(), tienda.getSlogan(), tienda.getCiudad());
		}
		setModo(ModoFormulario.Editar);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setVisible(true);
	}
	
	public void eliminarTienda(Tienda tienda) {
		if(!tienda.equals(this.tienda))
			this.tienda = tienda;
		
		setModo(ModoFormulario.Eliminar);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setVisible(true);
	}
	
	public void visualizarTienda(Tienda tienda) {
		if(!tienda.equals(this.tienda))
			this.tienda = tienda;
		setModo(ModoFormulario.Visualizar);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public MantenimientoTiendas() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(modo == ModoFormulario.Visualizar) {
					MantenimientoTiendas.this.dispose();
					return;
				}

					
				if(!tienda.esValida(errores)) {
					JOptionPane.showMessageDialog(MantenimientoTiendas.this, errores.toArray(new String[0]), "Validez de Datos", JOptionPane.WARNING_MESSAGE);
					return;
				}
					
				
				switch(modo) {
				default:
				case Agregar:
					if(tienda.insertar()) {
						JOptionPane.showMessageDialog(MantenimientoTiendas.this, "¡Se ha agregado la tienda satisfactoriamente!", "Éxito :)", JOptionPane.PLAIN_MESSAGE);
						MantenimientoTiendas.this.dispose();
						return;
					}
					
					break;
					
				case Editar:
					if(tienda.actualizar()) {
						JOptionPane.showMessageDialog(MantenimientoTiendas.this, "¡Se han guardado los cambios satisfactoriamente!", "Éxito :)", JOptionPane.PLAIN_MESSAGE);
						MantenimientoTiendas.this.dispose();
						return;
					}
					break;
					
				case Eliminar:
					if(JOptionPane.showConfirmDialog(MantenimientoTiendas.this, "Seguro que desea eliminar la tienda?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION && tienda.eliminar()) {
						JOptionPane.showMessageDialog(MantenimientoTiendas.this, "¡Se ha eliminado la tienda satisfactoriamente!", "Éxito :)", JOptionPane.WARNING_MESSAGE);
						MantenimientoTiendas.this.dispose();
						return;
					}
					break;
					
				}
				
				JOptionPane.showMessageDialog(MantenimientoTiendas.this, "Ha ocurrido un error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnFinalizar.setBounds(328, 174, 157, 36);
		getContentPane().add(btnFinalizar);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				tienda.setNombre(txtNombre.getText());
			}
		});
		txtNombre.setBounds(93, 27, 392, 28);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtSlogan = new JTextField();
		txtSlogan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tienda.setSlogan(txtSlogan.getText());
			}
		});
		txtSlogan.setColumns(10);
		txtSlogan.setBounds(94, 57, 392, 28);
		getContentPane().add(txtSlogan);
		
		txtDireccion = new JTextField();
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tienda.setDireccion(txtDireccion.getText());
			}
		});
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(94, 86, 392, 28);
		getContentPane().add(txtDireccion);
		
		cbCiudad = new JComboBox<>();
		cbCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tienda.setCiudad((Ciudad)cbCiudad.getSelectedItem());
			}
		});
		cbCiudad.setEnabled(false);
		cbCiudad.setBounds(93, 183, 225, 26);
		getContentPane().add(cbCiudad);
		
		JLabel lblPas = new JLabel("Pa\u00EDs");
		lblPas.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPas.setBounds(6, 122, 77, 16);
		getContentPane().add(lblPas);
		
		cbPais = new JComboBox<>();
		cbPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pais temp = (Pais)cbPais.getSelectedItem();
				if(temp != null && cbPais.getSelectedIndex() != -1) {
					cbCiudad.removeAllItems();
					cbCiudad.setEnabled(false);
					cbProvincia.removeAllItems();
					List<Provincia> pros = Utilidades.provinciasDePais(temp, provincias);
					for(Provincia p : pros) {
						cbProvincia.addItem(p);
					}
					cbProvincia.setEnabled(true);
				}
			}
		});
		cbPais.setBounds(93, 118, 225, 26);
		getContentPane().add(cbPais);
		
		JLabel lblProvinciaOEstado = new JLabel("Provincia");
		lblProvinciaOEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProvinciaOEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProvinciaOEstado.setBounds(6, 154, 77, 16);
		getContentPane().add(lblProvinciaOEstado);
		
		cbProvincia = new JComboBox<>();
		cbProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbProvincia.getSelectedIndex() != -1) {
					cbCiudad.removeAllItems();
					cbCiudad.setEnabled(true);
					List<Ciudad> cities = Utilidades.ciudadesDeProvincia((Provincia)cbProvincia.getSelectedItem(), ciudades); 
					for(Ciudad c : cities) {
						cbCiudad.addItem(c);
					}
				}
			}
		});
		cbProvincia.setEnabled(false);
		cbProvincia.setBounds(93, 150, 225, 26);
		getContentPane().add(cbProvincia);
		
		btnLimpiarCampos = new JButton("Limpiar Campos");
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(back != null) {
					txtDireccion.setText(back.getDireccion());
					txtNombre.setText(back.getNombre());
					txtSlogan.setText(back.getSlogan());
				} else {
					txtDireccion.setText("");
					txtNombre.setText("");
					txtSlogan.setText("");
				}
			}
		});
		btnLimpiarCampos.setBounds(328, 126, 157, 36);
		getContentPane().add(btnLimpiarCampos);
		
		
		Utilidades.loadUbicaciones(paises, provincias, ciudades);
	}
}
