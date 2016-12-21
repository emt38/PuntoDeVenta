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
import java.util.stream.Collectors;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.DATA_CONVERSION;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.PortableInterceptor.ObjectReferenceTemplateSeqHolder;

 

import modelos.Cliente;
import modelos.Producto;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import principal.Utilidades;

public class ConsultarProductosFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscarCliente;
	private JTable objTable;
	private JScrollPane scrollPane ; 
	public  Producto objProducto;	
	private ArrayList<Producto> misProductos;
	private JButton btnSalir ;
	private JButton btnSelecionar;
	
		
	 

	/**
	 * Launch the application.
	 */
	
	private void construirTabla() {
		String titulo []={"CODIGO-ID", "CODIGO-PRODUCT", "COSTO", "PRECIO", "TASA-IMPUESTO", "INVENTARIO"};
		String datos[][]=getDatosMatriz();
		
		objTable = new JTable(datos,titulo){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }}; //return false: Desabilitar edicion 
		
		scrollPane.setViewportView(objTable);
		
	}

	private String[][] getDatosMatriz() {
		objProducto = new Producto();
		misProductos =(ArrayList<Producto>)objProducto.listar("");
	 
		String matrizInf[][]= new String [misProductos.size()][5];
		
		for(int i=0; i< misProductos.size(); i++){
			matrizInf[i][0]=misProductos.get(i).getId()+"";
			matrizInf[i][1]=misProductos.get(i).getCodigo()+"";
			matrizInf[i][2]=misProductos.get(i).getDescripcion()+"";
			matrizInf[i][3]=misProductos.get(i).getCosto()+"";
			matrizInf[i][4]=misProductos.get(i).getInventario()+"";
			matrizInf[i][5]=misProductos.get(i).getPrecio()+"";
			matrizInf[i][6]=misProductos.get(i).getTasaImpuesto()+"";
			
		}
		return matrizInf;
		 
	}
	private void seleccionarFila(){
		
		int Id=(Integer.parseInt(objTable.getValueAt(objTable.getSelectedRow(),0).toString()));
  	  for(int i=0; i < misProductos.size(); i++)
  	  {
  		  if(misProductos.get(i).getId() ==Id){
  			objProducto = new Producto();
  			objProducto.setCodigo((misProductos.get(i).getCodigo()));
  			objProducto.setCosto((misProductos.get(i).getCosto()));
  			objProducto.setDescripcion((misProductos.get(i).getDescripcion()));
  			objProducto.setInventario((misProductos.get(i).getInventario()));
  			objProducto.setId(Id);
  			objProducto.setPrecio(misProductos.get(i).getPrecio());
  			objProducto.setTasaImpuesto(misProductos.get(i).getTasaImpuesto());
  			 
  			   break;
  			  
  		  }
  			  
  	  } 
	ProductoFrame.objProductoObtenido=objProducto;
  
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarProductosFrame frame = new ConsultarProductosFrame();
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
	public ConsultarProductosFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		 
		
		btnSalir= new JButton("SALIR");
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
		
		btnSelecionar = new JButton("SELECIONAR");
		btnSelecionar.setBounds(422, 230, 113, 23);
		contentPane.add(btnSelecionar);
		
		btnSelecionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				
				switch (objTable.getSelectedRowCount()) {
				case 0:
					JOptionPane.showConfirmDialog(null, "¿Tiene que selecionar una fila?","Alerta!", 
							JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					 
					break;

				default:
					seleccionarFila();					
					dispose();			 
					 
					break;
				}
			}
		});
		
		JLabel lblConsultaDeClientes = new JLabel("CONSULTA DE PRODUCTOS");
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
			    	 
			    	  seleccionarFila();
			    	  dispose();
			      }
			       
			}

			});
	}

	
}
