package vistas;

import java.awt.BorderLayout; 

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelos.Cliente;

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
import java.nio.channels.NonWritableChannelException;
import java.text.DecimalFormat;

public class ClienteFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JTextField txtIdentificacion;
	private JTextField txtCelular;
	private JTextField txtTelefono;
	private JTextField txtFechaIngreso;
	private JTextField txtTasaDescuento;
	private JComboBox cmbSexo;
	private boolean modificar;
	private Cliente objCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteFrame frame = new ClienteFrame();
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
	private void LimpiarCampos() {
		txtApellido.setText("");
		txtCelular.setText("");
		txtCodigo.setText("");
		txtDireccion.setText("");
		txtFechaIngreso.setText("");
		txtIdentificacion.setText("");
		txtNombre.setText("");
		txtTasaDescuento.setText("");
		txtTelefono.setText("");
	}

	private boolean ValidarCampos() {
		if(modificar)
		{
			if(txtCodigo.getText().length()==0)
			{
				
				txtCodigo.requestFocus();
				return false;
			}
			 
		}
		if(txtNombre.getText().length()==0)
		{
			
			txtNombre.requestFocus();
			return false;
		}if(txtApellido.getText().length()==0)
		{
			
			txtApellido.requestFocus();
			return false;
		}
		if(txtIdentificacion.getText().length()==0)
		{
			
			txtIdentificacion.requestFocus();
			return false;
		}
		if(txtTasaDescuento.getText().length()==0)
		{
			
			txtTasaDescuento.requestFocus();
			return false;
		}
		if(txtTasaDescuento.getText().length()!=0)
		{
			if(ValidaNumeros(txtTasaDescuento.getText()))
				return true;
			else {
				txtTasaDescuento.requestFocus();
				return false;
			}
			
		}
		return true;
	}

	private boolean ValidaNumeros(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
		 
	}
	public ClienteFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteFrame.class.getResource("/Iconos_E_Imagenes/GENTE.JPG")));
		setTitle("CLIENTE");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 568, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSALIR = new JButton("SALIR");
		btnSALIR.setBounds(451, 217, 101, 31);
		btnSALIR.addActionListener(new ActionListener() {			
			 
			public void actionPerformed(ActionEvent e) {
				if(txtCodigo.getText()!="")
				{
					int opcion =JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?","Alerta!", 
							JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

					if (opcion == 0){
					dispose();
					}
				}
				
			}
		});
		contentPane.add(btnSALIR);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(269, 217, 101, 31);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setHorizontalAlignment(SwingConstants.TRAILING);
		btnGuardar.setBounds(182, 217, 101, 31);
		contentPane.add(btnGuardar);
		 btnGuardar.addActionListener(new ActionListener() {			
			
			public void actionPerformed(ActionEvent e) {
				
				if(ValidarCampos())
				{
					int opcion =JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea registrar el cliente?","Registro de Cliente",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);					

					if (opcion == 0){
						objCliente = new Cliente();
						objCliente.setApellido(txtApellido.getText());
						objCliente.setCelular(txtCelular.getText());
						objCliente.setDireccion(txtDireccion.getText());
						objCliente.setIdentificacion(txtIdentificacion.getText()); 
						objCliente.setNombre(txtNombre.getText()); 
						objCliente.setSexo(cmbSexo.getSelectedItem().toString());
						objCliente.setTasaDescuento(Float.parseFloat(txtTasaDescuento.getText()));
						objCliente.setTelefono(txtTelefono.getText());	
					    objCliente.insertar();
					    LimpiarCampos();
					   JOptionPane.showConfirmDialog(null, "REGISTRO REGISTRADO CORRECTAMENTE","CONFIRMACION",
								  JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
					  
					} 
				}
				
			}

			
		});
		   
		JButton btnNuevo = new JButton("NUEVO");
		btnNuevo.setIcon(null);
		btnNuevo.setSelectedIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/NUEVO.JPG")));
		btnNuevo.setMaximumSize(new Dimension(20, 9));
		btnNuevo.setBounds(5, 217, 101, 31);
		contentPane.add(btnNuevo);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(93, 217, 101, 31);
		btnModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ValidarCampos())
				{
					int opcion =JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea modificar el cliente?","Registro de Cliente",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);					

					if (opcion == 0){
						objCliente = new Cliente();
						objCliente.setApellido(txtApellido.getText());
						objCliente.setCelular(txtCelular.getText());
						objCliente.setDireccion(txtDireccion.getText());
						objCliente.setIdentificacion(txtIdentificacion.getText()); 
						objCliente.setNombre(txtNombre.getText()); 
						objCliente.setSexo(cmbSexo.getSelectedItem().toString());
						objCliente.setTasaDescuento(Float.parseFloat(txtTasaDescuento.getText()));
						objCliente.setTelefono(txtTelefono.getText());	
						objCliente.setId(Integer.parseInt(txtCodigo.getText()));
					    objCliente.actualizar();
					    LimpiarCampos();
					   JOptionPane.showConfirmDialog(null, "REGISTRO ATUALIAZDO CORRECTAMENTE","CONFIRMACION",
								  JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
					  
					} 
				}
				
			}
		});
		contentPane.add(btnModificar);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		 
		txtCodigo.setBounds(60, 11, 197, 20);
		contentPane.add(txtCodigo);
		
		JLabel lblCodigo = new JLabel("CODIGO:");
		lblCodigo.setBounds(5, 14, 56, 14);
		contentPane.add(lblCodigo);
		
		JLabel lblNomre = new JLabel("NOMBRE:");
		lblNomre.setBounds(5, 55, 64, 14);
		contentPane.add(lblNomre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 52, 312, 20);
		 
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("APELLIDO:");
		lblApellido.setBounds(5, 80, 64, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(133, 77, 312, 20);
		 
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblDirecion = new JLabel("DIRECION:");
		lblDirecion.setBounds(5, 105, 64, 14);
		contentPane.add(lblDirecion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(133, 102, 403, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblCedulaRnc = new JLabel("CEDULA / RNC:");
		lblCedulaRnc.setBounds(5, 130, 83, 14);
		contentPane.add(lblCedulaRnc);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setBounds(133, 127, 164, 20);
		contentPane.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 42, 547, 14);
		contentPane.add(separator);
		
		JLabel lblTelefono = new JLabel("TELEFONO:");
		lblTelefono.setBounds(5, 155, 73, 14);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 152, 164, 20);		 
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		  cmbSexo = new JComboBox();
		  cmbSexo.setBounds(372, 127, 164, 20);
		contentPane.add(cmbSexo);
		cmbSexo.addItem("SELECCIONAR");
		cmbSexo.addItem("FEMENINO");
		cmbSexo.addItem("MASCULINO");
		
		JLabel lblSexo = new JLabel("SEXO:");
		lblSexo.setBounds(302, 130, 46, 14);
		contentPane.add(lblSexo);
		
		JLabel lblCelular = new JLabel("CELULAR:");
		lblCelular.setBounds(302, 155, 64, 14);
		contentPane.add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setBounds(372, 152, 164, 20);
		 
		contentPane.add(txtCelular);
		txtCelular.setColumns(10);
		
		txtFechaIngreso = new JTextField();
		txtFechaIngreso.setBounds(133, 173, 164, 27);
	
		contentPane.add(txtFechaIngreso);
		txtFechaIngreso.setColumns(10);
		
		JLabel lblFechaDeIngreso = new JLabel("FECHA DE INGRESO:");
		lblFechaDeIngreso.setBounds(5, 180, 118, 20);
		contentPane.add(lblFechaDeIngreso);
		
		txtTasaDescuento = new JTextField();
		txtTasaDescuento.setColumns(10);
		 
		txtTasaDescuento.setBounds(372, 177, 164, 20);
		contentPane.add(txtTasaDescuento);
		
		JLabel lblTasaDescuento = new JLabel("T-Desc:");
		lblTasaDescuento.setBounds(302, 180, 64, 14);
		contentPane.add(lblTasaDescuento);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(5, 208, 547, 14);
		contentPane.add(separator_1);
		
		JButton btnAnterior = new JButton("");
		btnAnterior.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/ANTERIOR.JPG")));
		btnAnterior.setHorizontalAlignment(SwingConstants.LEFT);
		btnAnterior.setBounds(260, 6, 56, 31);
		contentPane.add(btnAnterior);
		
		JButton btnPrimero = new JButton("");
		btnPrimero.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/PRIMERO.JPG")));
		btnPrimero.setHorizontalAlignment(SwingConstants.LEFT);
		btnPrimero.setBounds(315, 6, 56, 31);
		contentPane.add(btnPrimero);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/Search.png")));
		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setBounds(370, 6, 56, 31);
		contentPane.add(btnBuscar);
		
		JButton btnUltimo = new JButton("");
		btnUltimo.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/ULTIMO.JPG")));
		btnUltimo.setHorizontalAlignment(SwingConstants.LEFT);
		btnUltimo.setBounds(425, 6, 56, 31);
		contentPane.add(btnUltimo);
		
		JButton btnSigte = new JButton("");
		btnSigte.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Iconos_E_Imagenes/SIGUIENTE.JPG")));
		btnSigte.setHorizontalAlignment(SwingConstants.LEFT);
		btnSigte.setBounds(480, 6, 56, 31);
		contentPane.add(btnSigte);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(358, 217, 101, 31);
		contentPane.add(btnEliminar);
	}
}
