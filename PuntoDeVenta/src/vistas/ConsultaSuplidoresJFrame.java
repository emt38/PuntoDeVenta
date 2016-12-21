package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
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
import javax.swing.table.TableRowSorter;

import org.omg.CORBA.DATA_CONVERSION;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.PortableInterceptor.ObjectReferenceTemplateSeqHolder;

 

import modelos.Cliente;
import modelos.Suplidor;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import principal.Utilidades;

public class ConsultaSuplidoresJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscarCliente;
	private JTable objTable;
	private JScrollPane scrollPane ; 
	public  Suplidor objSuplidor;	
	private ArrayList<Suplidor> misSuplidor;
	private JButton btnSalir ;
	private JButton btnSelecionar;
	
		
	 
	private TableRowSorter trsfiltro;
	private String filtro;

	/**
	 * Launch the application.
	 */
	public void filtro() {
		trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscarCliente.getText(), 1));
	}
	private void construirTabla() {
		String titulo []={"CODIGO", "NOMBRE", "APELLIDO", "DIRECCION","TELEFONO", "CELULAR", "SEXO", "RNC", "EMPRESA"};
		String datos[][]=getDatosMatriz();
		 
		objTable = new JTable(datos,titulo){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }}; //return false: Desabilitar edicion 
		
		scrollPane.setViewportView(objTable);
		
	}

	private String[][] getDatosMatriz() {
		objSuplidor = new Suplidor();
		misSuplidor =(ArrayList<Suplidor>)objSuplidor.listar("");
	 
		String matrizInf[][]= new String [misSuplidor.size()][9];
		
		for(int i=0; i< misSuplidor.size(); i++){
			matrizInf[i][0]=misSuplidor.get(i).getId()+"";
			matrizInf[i][1]=misSuplidor.get(i).getNombre()+"";
			matrizInf[i][2]=misSuplidor.get(i).getApellido()+"";
			matrizInf[i][3]=misSuplidor.get(i).getDireccion()+"";
			matrizInf[i][3]=misSuplidor.get(i).getTelefono()+"";
			matrizInf[i][4]=misSuplidor.get(i).getCelular()+"";			
			matrizInf[i][6]=misSuplidor.get(i).getSexo()+"";
			matrizInf[i][7]=misSuplidor.get(i).getRnc()+"";
			matrizInf[i][8]=misSuplidor.get(i).getEmpresa()+"";
		}
		return matrizInf;
		 
	}
	private void seleccionarFila(){
		
		int Id=(Integer.parseInt(objTable.getValueAt(objTable.getSelectedRow(),0).toString()));
  	  for(int i=0; i < misSuplidor.size(); i++)
  	  {
  		  if(misSuplidor.get(i).getId() ==Id){
  			objSuplidor = new Suplidor();
  			objSuplidor.setApellido(misSuplidor.get(i).getApellido());
  			objSuplidor.setCelular(misSuplidor.get(i).getCelular());  			 
  			objSuplidor.setDireccion(misSuplidor.get(i).getDireccion());
  			objSuplidor.setId(Id);
  			objSuplidor.setIdentificacion(misSuplidor.get(i).getIdentificacion());
  			objSuplidor.setEmpresa(misSuplidor.get(i).getEmpresa());
  			objSuplidor.setNombre(misSuplidor.get(i).getNombre());
  			objSuplidor.setSexo(misSuplidor.get(i).getSexo());
  			objSuplidor.setRnc(misSuplidor.get(i).getRnc());
  			objSuplidor.setTelefono(misSuplidor.get(i).getTelefono());
  			   break;
  			  
  		  }
  			  
  	  } 
	SuplidoresFrame.objSuplidorObtenido=objSuplidor;
  
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaSuplidoresJFrame frame = new ConsultaSuplidoresJFrame();
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
	public ConsultaSuplidoresJFrame() {
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
		txtBuscarCliente.addKeyListener(new KeyAdapter() {
			public void keyReleased(final KeyEvent e) {
				if (objTable == null) {
					objTable = new JTable();
				}
				trsfiltro = new TableRowSorter(objTable.getModel());
				objTable.setRowSorter(trsfiltro);
				String cadena = (txtBuscarCliente.getText()).toUpperCase();
				txtBuscarCliente.setText(cadena);
				repaint();
				filtro();
			}
		});
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
		
		JLabel lblConsultaDeSuplidores = new JLabel("CONSULTA DE SUPLIDORES");
		lblConsultaDeSuplidores.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConsultaDeSuplidores.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeSuplidores.setBounds(209, 11, 228, 14);
		contentPane.add(lblConsultaDeSuplidores);
		
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
