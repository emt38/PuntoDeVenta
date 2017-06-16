package vistas;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelos.Cliente;
import modelos.Suplidor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SuplidoresFrame extends JFrame implements WindowFocusListener {
	// Declaracion de JTextField, cJComboBoxmb y objetos
	private JPanel contentPane;
	private JSeparator separator;
	private JSeparator separator_1;
	private JTextField txtCodigo;
	public JTextField txtNombre;
	public JTextField txtApellido;
	public JTextField txtDireccion;
	public JTextField txtIdentificacion;
	public JTextField txtCelular;
	private JTextField txtTelefono;
	private JTextField txtEmpresa;
	private JTextField txtRnc;
	public JComboBox cmbSexo;
	private boolean modificar = false;
	private Suplidor objSuplidor;
	public static Suplidor objSuplidorObtenido;

	private ConsultaSuplidoresJFrame objFrmConsultaSuplidor;

	// Declaracion de Botones Y LABEL
	private JButton btnSALIR;
	private JButton btnCancelar;
	public JButton btnGuardar;
	public JButton btnNuevo;
	public JButton btnModificar;
	private JButton btnAnterior;
	private JButton btnPrimero;
	private JButton btnBuscar;
	private JButton btnUltimo;
	private JButton btnSigte;
	private JButton btnEliminar;
	private JLabel lblCodigo;
	private JLabel lblNomre;
	private JLabel lblApellido;
	private JLabel lblDirecion;
	private JLabel lblCedulaRnc;
	private JLabel lblTelefono;
	private JLabel lblSexo;
	private JLabel lblCelular;
	private JLabel lblFechaDeIngreso;
	private JLabel lblTasaDescuento;

	// OTROS
	private ArrayList<Suplidor> misSuplidor;
	private int conteo = 0;
	private int opcion = 0;
	private static DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					SuplidoresFrame frame = new SuplidoresFrame();
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

		txtApellido.setEnabled(false);
		txtCelular.setEnabled(false);
		txtCodigo.setEnabled(false);
		txtDireccion.setEnabled(false);
		txtEmpresa.setEnabled(false);
		txtIdentificacion.setEnabled(false);
		txtNombre.setEnabled(false);
		txtRnc.setEnabled(false);
		txtTelefono.setEnabled(false);
		cmbSexo.setEnabled(false);
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

		txtApellido.setEnabled(true);
		txtCelular.setEnabled(true);
		txtCodigo.setEnabled(false);
		txtDireccion.setEnabled(true);
		txtEmpresa.setEnabled(true);
		txtIdentificacion.setEnabled(true);
		txtNombre.setEnabled(true);
		txtRnc.setEnabled(true);
		txtTelefono.setEnabled(true);
		cmbSexo.setEnabled(true);
	}

	private void mostrarSuplidorObtenido() {
		limpiarCampos();

		txtCodigo.setText(objSuplidorObtenido.getId() + "");
		txtApellido.setText(objSuplidorObtenido.getApellido() + "");
		txtCelular.setText(objSuplidorObtenido.getCelular() + "");
		txtDireccion.setText(objSuplidorObtenido.getDireccion() + "");
		txtEmpresa.setText(objSuplidorObtenido.getEmpresa() + "");
		txtIdentificacion.setText(objSuplidorObtenido.getIdentificacion() + "");
		txtNombre.setText(objSuplidorObtenido.getNombre() + "");
		txtRnc.setText(objSuplidorObtenido.getRnc() + "");
		txtTelefono.setText(objSuplidorObtenido.getTelefono() + "");
		String aux = objSuplidorObtenido.getSexo() + "";
		String sexo = aux.toUpperCase();

		switch (sexo) {
		case "FEMENINO":
			cmbSexo.setSelectedIndex(1);
			break;

		default:
			cmbSexo.setSelectedIndex(2);
			break;
		}
		objSuplidorObtenido = null;
		conteo=0;

	}

	private void mostrarRegistros() {
		if (misSuplidor == null) {
			objSuplidor = new Suplidor();
			misSuplidor = (ArrayList<Suplidor>) objSuplidor.listar("");
		}
		
			txtCodigo.setText(misSuplidor.get(conteo).getId() + "");
			txtApellido.setText(misSuplidor.get(conteo).getApellido() + "");
			txtCelular.setText(misSuplidor.get(conteo).getCelular() + "");
			txtDireccion.setText(misSuplidor.get(conteo).getDireccion() + "");
			txtEmpresa.setText(misSuplidor.get(conteo).getEmpresa() + "");
			txtIdentificacion.setText(misSuplidor.get(conteo).getIdentificacion() + "");
			txtNombre.setText(misSuplidor.get(conteo).getNombre() + "");
			txtRnc.setText(misSuplidor.get(conteo).getRnc() + "");
			txtTelefono.setText(misSuplidor.get(conteo).getTelefono() + "");
			String aux = misSuplidor.get(conteo).getSexo() + "";
			String sexo = aux.toUpperCase();

			switch (sexo) {
			case "FEMENINO":
				cmbSexo.setSelectedIndex(1);
				break;

			default:
				cmbSexo.setSelectedIndex(2);
				break;
			}
			
		}
			
		
	private boolean ValidarCampos() {
		if (modificar) {
			if (txtCodigo.getText().length() == 0) {

				txtCodigo.requestFocus();
				return false;
			}

		}
		if (txtNombre.getText().length() == 0) {

			txtNombre.requestFocus();
			return false;
		}
		if (txtApellido.getText().length() == 0) {

			txtApellido.requestFocus();
			return false;
		}
		if (txtIdentificacion.getText().length() == 0) {

			txtIdentificacion.requestFocus();
			return false;
		}

		return true;
	}

	private void limpiarCampos() {
		txtApellido.setText("");
		txtCelular.setText("");
		txtCodigo.setText("");
		txtDireccion.setText("");
		txtEmpresa.setText("");
		txtIdentificacion.setText("");
		txtNombre.setText("");
		txtRnc.setText("");
		txtTelefono.setText("");
		cmbSexo.setSelectedIndex(0);
	}
	
	public SuplidoresFrame() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SuplidoresFrame.class.getResource("/Iconos_E_Imagenes/GENTE.JPG")));
		setTitle("SUPLIDOR");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 568, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowFocusListener(this);

		// AGREGANDO COMPONENTES A EL FORMULARIO
		btnSALIR = new JButton("SALIR");
		btnSALIR.setBounds(451, 217, 101, 31);
		contentPane.add(btnSALIR);
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(269, 217, 101, 31);
		contentPane.add(btnCancelar);
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setHorizontalAlignment(SwingConstants.TRAILING);
		btnGuardar.setBounds(182, 217, 101, 31);
		contentPane.add(btnGuardar);
		btnNuevo = new JButton("NUEVO");
		btnNuevo.setIcon(null);
		btnNuevo.setSelectedIcon(new ImageIcon(SuplidoresFrame.class.getResource("/Iconos_E_Imagenes/NUEVO.JPG")));
		btnNuevo.setMaximumSize(new Dimension(20, 9));
		btnNuevo.setBounds(5, 217, 101, 31);
		contentPane.add(btnNuevo);

		btnAnterior = new JButton("");
		btnAnterior.setIcon(new ImageIcon(SuplidoresFrame.class.getResource("/Iconos_E_Imagenes/ANTERIOR.JPG")));
		btnAnterior.setHorizontalAlignment(SwingConstants.LEFT);
		btnAnterior.setBounds(315, 6, 56, 31);
		contentPane.add(btnAnterior);

		btnPrimero = new JButton("");
		btnPrimero.setIcon(new ImageIcon(SuplidoresFrame.class.getResource("/Iconos_E_Imagenes/PRIMERO.JPG")));
		btnPrimero.setHorizontalAlignment(SwingConstants.LEFT);
		btnPrimero.setBounds(260, 6, 56, 31);
		contentPane.add(btnPrimero);

		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(SuplidoresFrame.class.getResource("/Iconos_E_Imagenes/Search.png")));
		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setBounds(370, 6, 56, 31);
		contentPane.add(btnBuscar);

		btnUltimo = new JButton("");
		btnUltimo.setIcon(new ImageIcon(SuplidoresFrame.class.getResource("/Iconos_E_Imagenes/ULTIMO.JPG")));
		btnUltimo.setHorizontalAlignment(SwingConstants.LEFT);
		btnUltimo.setBounds(480, 6, 56, 31);
		;
		contentPane.add(btnUltimo);

		btnSigte = new JButton("");
		btnSigte.setIcon(new ImageIcon(SuplidoresFrame.class.getResource("/Iconos_E_Imagenes/SIGUIENTE.JPG")));
		btnSigte.setHorizontalAlignment(SwingConstants.LEFT);
		btnSigte.setBounds(425, 6, 56, 31);
		contentPane.add(btnSigte);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(358, 217, 101, 31);
		contentPane.add(btnEliminar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(93, 217, 101, 31);
		contentPane.add(btnModificar);
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(60, 8, 197, 27);
		contentPane.add(txtCodigo);

		lblCodigo = new JLabel("CODIGO:");
		lblCodigo.setBounds(5, 14, 56, 14);
		contentPane.add(lblCodigo);

		lblNomre = new JLabel("NOMBRE:");
		lblNomre.setBounds(5, 58, 64, 14);
		contentPane.add(lblNomre);

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 52, 312, 27);

		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		lblApellido = new JLabel("APELLIDO:");
		lblApellido.setBounds(5, 83, 64, 14);
		contentPane.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(133, 77, 312, 27);

		contentPane.add(txtApellido);
		txtApellido.setColumns(10);

		lblDirecion = new JLabel("DIRECION:");
		lblDirecion.setBounds(5, 108, 64, 14);
		contentPane.add(lblDirecion);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(133, 102, 403, 27);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);

		lblCedulaRnc = new JLabel("CEDULA:");
		lblCedulaRnc.setBounds(5, 133, 83, 14);
		contentPane.add(lblCedulaRnc);

		txtIdentificacion = new JTextField();
		txtIdentificacion.setBounds(133, 127, 164, 27);
		contentPane.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);

		separator = new JSeparator();
		separator.setBounds(5, 42, 547, 14);
		contentPane.add(separator);

		lblTelefono = new JLabel("TELEFONO:");
		lblTelefono.setBounds(5, 158, 73, 14);
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 152, 164, 27);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);

		cmbSexo = new JComboBox();
		cmbSexo.setBounds(372, 127, 164, 27);
		contentPane.add(cmbSexo);
		cmbSexo.addItem("SELECCIONAR");
		cmbSexo.addItem("FEMENINO");
		cmbSexo.addItem("MASCULINO");

		lblSexo = new JLabel("SEXO:");
		lblSexo.setBounds(302, 133, 46, 14);
		contentPane.add(lblSexo);

		lblCelular = new JLabel("CELULAR:");
		lblCelular.setBounds(302, 158, 64, 14);
		contentPane.add(lblCelular);

		txtCelular = new JTextField();
		txtCelular.setBounds(372, 152, 164, 27);

		contentPane.add(txtCelular);
		txtCelular.setColumns(10);

		txtEmpresa = new JTextField();
		txtEmpresa.setBounds(133, 177, 164, 27);
		contentPane.add(txtEmpresa);

		lblFechaDeIngreso = new JLabel("EMPRESA:");
		lblFechaDeIngreso.setBounds(5, 180, 118, 20);
		contentPane.add(lblFechaDeIngreso);

		txtRnc = new JTextField();
		txtRnc.setColumns(10);
		txtRnc.setBounds(372, 177, 164, 27);
		contentPane.add(txtRnc);

		lblTasaDescuento = new JLabel("RNC:");
		lblTasaDescuento.setBounds(302, 183, 64, 14);
		contentPane.add(lblTasaDescuento);

		separator_1 = new JSeparator();
		separator_1.setBounds(5, 208, 547, 14);
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
				if (txtCodigo.getText() != "") {
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
					// objSuplidor = new Suplidor();
					if (modificar) {
						opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea actualizar el suplidor?",
								"Actualizacion de Suplidor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcion == 0) {
							objSuplidor = new Suplidor();
							objSuplidor.setId(Integer.parseInt(txtCodigo.getText()));
							objSuplidor.setApellido(txtApellido.getText());
							objSuplidor.setCelular(txtCelular.getText());
							objSuplidor.setDireccion(txtDireccion.getText());
							objSuplidor.setNombre(txtNombre.getText());
							objSuplidor.setEmpresa(txtEmpresa.getText());
							objSuplidor.setRnc(txtRnc.getText());
							objSuplidor.setIdentificacion(txtIdentificacion.getText());
							objSuplidor.setTelefono(txtTelefono.getText());
							switch (cmbSexo.getSelectedIndex()) {
							case 1:
								objSuplidor.setSexo("Femenino");
								break;
							default:
								objSuplidor.setSexo("Masculino");
								break;
							}
							objSuplidor.actualizar();
							JOptionPane.showConfirmDialog(null, "REGISTRO ACTUALIZADO CORRECTAMENTE", "CONFIRMACION",
									JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
							deshabilitarEdicion();
							conteo = 0;
							objSuplidor = null;
							mostrarRegistros();
						}
					} else {
						opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea registrar el cliente?",
								"Registro de Cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcion == 0) {
							objSuplidor = new Suplidor();
							objSuplidor.setApellido(txtApellido.getText());
							objSuplidor.setCelular(txtCelular.getText());
							objSuplidor.setDireccion(txtDireccion.getText());
							objSuplidor.setNombre(txtNombre.getText());
							objSuplidor.setEmpresa(txtEmpresa.getText());
							objSuplidor.setRnc(txtRnc.getText());
							objSuplidor.setIdentificacion(txtIdentificacion.getText());
							objSuplidor.setTelefono(txtTelefono.getText());
							switch (cmbSexo.getSelectedIndex()) {
							case 1:
								objSuplidor.setSexo("Femenino");
								break;
							default:
								objSuplidor.setSexo("Masculino");
								break;
							}
							objSuplidor.insertar();
							JOptionPane.showConfirmDialog(null, "REGISTRO REGISTRADO CORRECTAMENTE", "CONFIRMACION",
									JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
							deshabilitarEdicion();
							conteo = 0;
							misSuplidor = null;
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
					objSuplidor = new Suplidor();
					objSuplidor.setId(Integer.parseInt(txtCodigo.getText()));
					objSuplidor.eliminar();
					JOptionPane.showConfirmDialog(null, "Cliente eliminado", "Eliminando cliete",
							JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE);
					deshabilitarEdicion();
					conteo = 0;
					misSuplidor = null;
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
				if (conteo < misSuplidor.size() - 1) {
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
				conteo = misSuplidor.size() - 1;
				mostrarRegistros();

			}
		});
		btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				objFrmConsultaSuplidor = new ConsultaSuplidoresJFrame();
				objFrmConsultaSuplidor.setAlwaysOnTop(false);
				objFrmConsultaSuplidor.setVisible(true);

			}
		});
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {

		if (objSuplidorObtenido != null) {
			mostrarSuplidorObtenido();
		}
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
