package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.DATA_CONVERSION;
import org.omg.CORBA.PRIVATE_MEMBER;

import modelos.Cliente;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import principal.Utilidades;

public class ConsultarClienteFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscarCliente;
	private JTable objTable;
	private JScrollPane scrollPane ; 
	private Cliente objCliente;
	private SimpleDateFormat formato;
		
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarClienteFrame frame = new ConsultarClienteFrame();
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
	
	  public static Date ParseFecha(String fecha)
	    {
	        SimpleDateFormat formato = new SimpleDateFormat("yyy-mm-dd");
	        Date fechaDate = null;
	        try {
	            fechaDate = formato.parse(fecha);
	        } 
	        catch (ParseException ex) 
	        {
	            System.out.println(ex);
	        }
	        return fechaDate;
	    }
	public ConsultarClienteFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CLIENTE:");
		lblNewLabel.setBounds(10, 203, 56, 14);
		contentPane.add(lblNewLabel);
		
		txtBuscarCliente = new JTextField();
		txtBuscarCliente.setBounds(71, 200, 575, 20);
		contentPane.add(txtBuscarCliente);
		txtBuscarCliente.setColumns(10);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcion =JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?","Alerta!", 
						JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

				if (opcion == 0){
				dispose();
				}
			}
		});
		btnSalir.setBounds(533, 230, 113, 23);
		contentPane.add(btnSalir);
		
		JButton btnSelecionar = new JButton("SELECIONAR");
		btnSelecionar.setBounds(422, 230, 113, 23);
		contentPane.add(btnSelecionar);
		
		JLabel lblConsultaDeClientes = new JLabel("CONSULTA DE CLIENTES");
		lblConsultaDeClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConsultaDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeClientes.setBounds(209, 11, 228, 14);
		contentPane.add(lblConsultaDeClientes);
		
		  scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 35, 646, 154);
		contentPane.add(scrollPane);		
		construirTabla();
		objTable.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent e) {
			      if(e.getClickCount()==2){
			    	  objCliente = new Cliente();
			    	 
			    	  objCliente.setId(Integer.parseInt(objTable.getValueAt(objTable.getSelectedRow(),0).toString()));
			    	  objCliente.setNombre(objTable.getValueAt(objTable.getSelectedRow(),1)+"");
			    	  objCliente.setApellido(objTable.getValueAt(objTable.getSelectedRow(),2)+"");
			    	  objCliente.setDireccion(objTable.getValueAt(objTable.getSelectedRow(),3)+"");
			    	  objCliente.setCelular(objTable.getValueAt(objTable.getSelectedRow(),4)+"");
			    	  objCliente.setIdentificacion(objTable.getValueAt(objTable.getSelectedRow(),5)+"");
			    	  objCliente.setSexo(objTable.getValueAt(objTable.getSelectedRow(),6)+"");
			    	  objCliente.setTasaDescuento(Float.parseFloat
			    			  ((objTable.getValueAt(objTable.getSelectedRow(),7).toString())));
			    	  String strFecha = (objTable.getValueAt(objTable.getSelectedRow(),8).toString());
			      
			    	  objCliente.setClienteDesde(ParseFecha(strFecha));
			         System.out.println(objCliente.getClienteDesde());
			      }
			       
			}});
	}

	private void construirTabla() {
		String titulo []={"CODIGO", "NOMBRE", "APELLIDO", "DIRECCION", "CELULAR", "CEDULA/RNC", "SEXO", "%DESC", "FECHA"};
		String datos[][]=getDatosMatriz();
		objTable = new JTable(datos,titulo){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }}; //return false: Desabilitar edicion 
		
		scrollPane.setViewportView(objTable);
		
	}

	private String[][] getDatosMatriz() {
		Cliente objCliente = new Cliente();
	    ArrayList<Cliente> misClientes=(ArrayList<Cliente>)objCliente.listar("");
	 
		String matrizInf[][]= new String [misClientes.size()][9];
		
		for(int i=0; i< misClientes.size(); i++){
			matrizInf[i][0]=misClientes.get(i).getId()+"";
			matrizInf[i][1]=misClientes.get(i).getNombre()+"";
			matrizInf[i][2]=misClientes.get(i).getApellido()+"";
			matrizInf[i][3]=misClientes.get(i).getDireccion()+"";
			matrizInf[i][4]=misClientes.get(i).getCelular()+"";
			matrizInf[i][5]=misClientes.get(i).getIdentificacion()+"";
			matrizInf[i][6]=misClientes.get(i).getSexo()+"";
			matrizInf[i][7]=misClientes.get(i).getTasaDescuento()+"";
			matrizInf[i][8]=misClientes.get(i).getClienteDesde()+"";
		}
		return matrizInf;
		 
	}
}
