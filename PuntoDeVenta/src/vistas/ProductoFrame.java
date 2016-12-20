package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelos.Cliente;
import modelos.Producto;

public class ProductoFrame extends JFrame implements WindowFocusListener {
	// DECLARACION DE JTextField Y OBJECTOS
	private JPanel contentPane;
	private JSeparator separator;
	private JTextField txtCodigoId;
	private Producto objProducto;
	public static Producto objProductoObtenido;
	// DECLARACIONES DE JLabel
	private JLabel lblCodigo;

	// Declaracion de Botones
	private JButton btnSALIR;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnAnterior;
	private JButton btnPrimero;
	private JButton btnBuscar;
	private JButton btnUltimo;
	private JButton btnSigte;
	private JButton btnEliminar;
	private JTextField txtCodigoProdu;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JLabel lblCosto;
	private JTextField txtCosto;
	private JLabel lblTimpuesto;
	private JTextField txtTasaImpuesto;
	private JLabel lblStock;
	private JTextField txtStock;
	// OTROS
	private ArrayList<Producto> misProductos;
	private int conteo = 0;
	private int opcion = 0;
	private static DefaultTableModel modelo;
	private boolean modificar = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductoFrame frame = new ProductoFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	// METODOS
	private void deshabilitarEdicion() {
		btnPrimero.setEnabled(true);
		btnAnterior.setEnabled(true);
		btnSigte.setEnabled(true);
		btnUltimo.setEnabled(true);
		btnNuevo.setEnabled(true);
		btnModificar.setEnabled(true);
		btnGuardar.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnEliminar.setEnabled(true);
		btnBuscar.setEnabled(true);

		txtCodigoId.setEnabled(false);
		txtCodigoProdu.setEnabled(false);
		txtCosto.setEnabled(false);
		txtDescripcion.setEnabled(false);
		txtPrecio.setEnabled(false);
		txtStock.setEnabled(false);
		txtTasaImpuesto.setEnabled(false);

	}

	private void habilitarEdicion() {
		btnPrimero.setEnabled(false);
		btnAnterior.setEnabled(false);
		btnSigte.setEnabled(false);
		btnUltimo.setEnabled(false);
		btnNuevo.setEnabled(false);
		btnModificar.setEnabled(false);
		btnGuardar.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnEliminar.setEnabled(false);
		btnBuscar.setEnabled(false);

		txtCodigoId.setEnabled(true);
		txtCodigoProdu.setEnabled(true);
		txtCosto.setEnabled(true);
		txtDescripcion.setEnabled(true);
		txtPrecio.setEnabled(true);
		txtStock.setEnabled(true);
		txtTasaImpuesto.setEnabled(true);
	}

	private void mostrarClienteObtenido() {
		limpiarCampos();
		txtCodigoId.setText(objProductoObtenido.getId() + "");
		txtCodigoProdu.setText(objProductoObtenido.getCodigo() + "");
		txtCosto.setText(objProductoObtenido.getCosto() + "");
		txtDescripcion.setText(objProductoObtenido.getDescripcion() + "");
		txtPrecio.setText(objProductoObtenido.getPrecio() + "");
		txtStock.setText(objProductoObtenido.getInventario() + "");
		txtTasaImpuesto.setText(objProductoObtenido.getTasaImpuesto() + "");
	}

	private void mostrarRegistros() {
		if (misProductos == null) {
			objProducto = new Producto();
			misProductos = (ArrayList<Producto>) objProducto.listar("");
		}
		txtCodigoId.setText(misProductos.get(conteo).getId() + "");
		txtCodigoProdu.setText(misProductos.get(conteo).getCodigo() + "");
		txtCosto.setText(misProductos.get(conteo).getCosto() + "");
		txtDescripcion.setText(misProductos.get(conteo).getDescripcion() + "");
		txtStock.setText(misProductos.get(conteo).getInventario() + "");
		txtTasaImpuesto.setText(misProductos.get(conteo).getTasaImpuesto() + "");

	}

	private boolean ValidarCampos() {

		if (txtCodigoProdu.getText().length() == 0) {

			txtCodigoProdu.requestFocus();
			return false;
		}

		if (txtDescripcion.getText().length() == 0) {

			txtDescripcion.requestFocus();
			return false;
		}

		if (txtCosto.getText().length() == 0) {

			txtCosto.requestFocus();
			return false;
		}

		if (txtPrecio.getText().length() == 0) {

			txtPrecio.requestFocus();
			return false;
		}

		if (txtCosto.getText().length() != 0) {

			if (!ValidaNumeros(txtCosto.getText())) {

				txtCosto.requestFocus();
				return false;
			}
		}

		if (txtPrecio.getText().length() != 0) {

			if (!ValidaNumeros(txtPrecio.getText())) {

				txtPrecio.requestFocus();
				return false;
			}

		}

		return true;
	}

	private boolean ValidaNumeros(String cadena) {
		try {
			Float.parseFloat(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}

	}

	private void limpiarCampos() {
		txtCodigoId.setText("");
		txtCodigoProdu.setText("");
		txtCosto.setText("");
		txtDescripcion.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
		txtTasaImpuesto.setText("");

	}

	public ProductoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowFocusListener(this);
		btnSALIR = new JButton("SALIR");
		btnSALIR.setBounds(451, 164, 101, 31);
		contentPane.add(btnSALIR);
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(269, 164, 101, 31);
		contentPane.add(btnCancelar);
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setHorizontalAlignment(SwingConstants.TRAILING);
		btnGuardar.setBounds(182, 164, 101, 31);
		contentPane.add(btnGuardar);
		btnNuevo = new JButton("NUEVO");
		btnNuevo.setIcon(null);
		btnNuevo.setSelectedIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/NUEVO.JPG")));
		btnNuevo.setMaximumSize(new Dimension(20, 9));
		btnNuevo.setBounds(5, 164, 101, 31);
		contentPane.add(btnNuevo);

		btnAnterior = new JButton("");
		btnAnterior.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/ANTERIOR.JPG")));
		btnAnterior.setHorizontalAlignment(SwingConstants.LEFT);
		btnAnterior.setBounds(315, 6, 56, 31);
		contentPane.add(btnAnterior);

		btnPrimero = new JButton("");
		btnPrimero.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/PRIMERO.JPG")));
		btnPrimero.setHorizontalAlignment(SwingConstants.LEFT);
		btnPrimero.setBounds(260, 6, 56, 31);
		contentPane.add(btnPrimero);

		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/Search.png")));
		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setBounds(370, 6, 56, 31);
		contentPane.add(btnBuscar);

		btnUltimo = new JButton("");
		btnUltimo.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/ULTIMO.JPG")));
		btnUltimo.setHorizontalAlignment(SwingConstants.LEFT);
		btnUltimo.setBounds(480, 6, 56, 31);
		;
		contentPane.add(btnUltimo);

		btnSigte = new JButton("");
		btnSigte.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/SIGUIENTE.JPG")));
		btnSigte.setHorizontalAlignment(SwingConstants.LEFT);
		btnSigte.setBounds(425, 6, 56, 31);
		contentPane.add(btnSigte);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(358, 164, 101, 31);
		contentPane.add(btnEliminar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(93, 164, 101, 31);
		contentPane.add(btnModificar);
		txtCodigoId = new JTextField();
		txtCodigoId.setColumns(10);
		txtCodigoId.setBounds(93, 11, 167, 20);
		contentPane.add(txtCodigoId);

		lblCodigo = new JLabel("CODIGO-ID:");
		lblCodigo.setBounds(5, 14, 68, 14);
		contentPane.add(lblCodigo);

		separator = new JSeparator();
		separator.setBounds(5, 42, 547, 14);
		contentPane.add(separator);

		JLabel lblCodigo_1 = new JLabel("COD-PROD");
		lblCodigo_1.setBounds(5, 51, 68, 14);
		contentPane.add(lblCodigo_1);

		txtCodigoProdu = new JTextField();
		txtCodigoProdu.setBounds(93, 48, 343, 20);
		contentPane.add(txtCodigoProdu);
		txtCodigoProdu.setColumns(10);

		lblDescripcion = new JLabel("DESCRIPCION:");
		lblDescripcion.setBounds(5, 75, 80, 14);
		contentPane.add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(93, 72, 449, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		lblPrecio = new JLabel("PRECIO:");
		lblPrecio.setBounds(5, 100, 46, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(93, 97, 174, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);

		lblCosto = new JLabel("COSTO:");
		lblCosto.setBounds(277, 100, 46, 14);
		contentPane.add(lblCosto);

		txtCosto = new JTextField();
		txtCosto.setColumns(10);
		txtCosto.setBounds(318, 97, 224, 20);
		contentPane.add(txtCosto);

		lblTimpuesto = new JLabel("TASA-IMPUESTO:");
		lblTimpuesto.setBounds(5, 126, 101, 14);
		contentPane.add(lblTimpuesto);

		txtTasaImpuesto = new JTextField();
		txtTasaImpuesto.setBounds(93, 123, 174, 20);
		contentPane.add(txtTasaImpuesto);
		txtTasaImpuesto.setColumns(10);

		lblStock = new JLabel("STOCK:");
		lblStock.setBounds(277, 126, 46, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setText("");
		txtStock.setBounds(318, 123, 224, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(5, 151, 547, 12);
		contentPane.add(separator_1);
		
		deshabilitarEdicion();
		mostrarRegistros();
		
		// EVENTOS DE LOS BOTONES
				btnNuevo.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						limpiarCampos();
						habilitarEdicion();

					}
				});
				btnSALIR.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtCodigoId.getText() != "") {
							int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Alerta!",
									JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

							if (opcion == 0) {
								dispose();
							}
						}

					}
				});
				
				btnGuardar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						if (ValidarCampos()) {

							if (modificar) {
								opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea actualizar el cliente?",
										"Actualizacion de Cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								if (opcion == 0) {
									objProducto = new Producto();
									objProducto.setId(Integer.parseInt(txtCodigoId.getText()));
									objProducto.setCodigo((txtCodigoProdu.getText()));
									objProducto.setCosto(Float.parseFloat(txtCosto.getText()));
									objProducto.setDescripcion(txtDescripcion.getText());
									objProducto.setInventario(Float.parseFloat(txtStock.getText()));
									objProducto.setPrecio(Float.parseFloat(txtPrecio.getText()));
									objProducto.setTasaImpuesto(Float.parseFloat(txtTasaImpuesto.getText()));
									
									objProducto.actualizar();
									JOptionPane.showConfirmDialog(null, "REGISTRO ACTUALIZADO CORRECTAMENTE", "CONFIRMACION",
											JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
									deshabilitarEdicion();
									conteo = 0;
									misProductos = null;
									mostrarRegistros();
								}
							} else {
								opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea registrar el cliente?",
										"Registro de Cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								if (opcion == 0) {
									objProducto = new Producto();									 
									objProducto.setCodigo((txtCodigoProdu.getText()));
									objProducto.setCosto(Float.parseFloat(txtCosto.getText()));
									objProducto.setDescripcion(txtDescripcion.getText());
									objProducto.setInventario(Float.parseFloat(txtStock.getText()));
									objProducto.setPrecio(Float.parseFloat(txtPrecio.getText()));
									objProducto.setTasaImpuesto(Float.parseFloat(txtTasaImpuesto.getText()));
									objProducto.insertar();
									JOptionPane.showConfirmDialog(null, "REGISTRO REGISTRADO CORRECTAMENTE", "CONFIRMACION",
											JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
									deshabilitarEdicion();
									conteo = 0;
									misProductos = null;
									mostrarRegistros();

								}
							}
						}

					}
				});
				btnModificar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						habilitarEdicion();
						modificar = true;
					}
				});
				btnCancelar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						limpiarCampos();
						deshabilitarEdicion();
						mostrarRegistros();
					}
				});
				btnEliminar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el cliente?",
								"Registro de Cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcion == 0) {
							objProducto = new Producto();
							objProducto.setId(Integer.parseInt(txtCodigoId.getText()));
							objProducto.eliminar();
							JOptionPane.showConfirmDialog(null, "Cliente eliminado", "Eliminando cliete",
									JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE);
							deshabilitarEdicion();
							conteo = 0;
							misProductos = null;
							mostrarRegistros();

						}
					}
				});
				

		// EVENTOS BOTONES MENU DE NAVEGACION

		btnAnterior.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (conteo <= 0) {
					return;
				} else {
					conteo -= 1;
					mostrarRegistros();
				}

			}
		});

		btnPrimero.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conteo = 0;
				mostrarRegistros();

			}
		});
		btnSigte.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (conteo < misProductos.size() - 1) {
					conteo += 1;
					mostrarRegistros();
				} else {
					return;
				}

			}
		});
		btnUltimo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conteo = misProductos.size() - 1;
				mostrarRegistros();

			}
		});
		btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// objFrmConsultaCliente = new ConsultarClienteFrame();
				// objFrmConsultaCliente.setAlwaysOnTop(false);
				// objFrmConsultaCliente.setVisible(true);

			}
		});
	}

	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		
		if(objProductoObtenido !=null){
			mostrarClienteObtenido();
		}

	}

	@Override
	public void windowLostFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
