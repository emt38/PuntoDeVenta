package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelos.Articulo;
import modelos.Producto;

public class AgregarArticuloFrame extends JFrame implements ActionListener {
	
	private final JPanel contentPane = new JPanel();
	private JTextField textField_IdProducto;
	private JTextField textField_Producto;
	private JTextField textField_ArticuloCantidad;
	private Producto producto = new Producto();
	private List<Producto> productos = producto.listar();
	private String productoDescripcion = "0";
	JButton btnBuscar = new JButton("Buscar");
	JButton cancelButton = new JButton("Cancel");
	JButton okButton = new JButton("Agregar articulo");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarArticuloFrame frame = new AgregarArticuloFrame();
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
	public AgregarArticuloFrame() {
		
		this.setTitle("Agregar articulo");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);
		{
			JLabel lblBuscarProducto = new JLabel("ID Producto");
			lblBuscarProducto.setBounds(26, 14, 81, 14);
			contentPane.add(lblBuscarProducto);
		}
		{
			textField_IdProducto = new JTextField();
			textField_IdProducto.setBounds(117, 14, 45, 20);
			contentPane.add(textField_IdProducto);
			textField_IdProducto.setColumns(10);
			
			
		}
		{
			JLabel lblProducto = new JLabel("Producto");
			lblProducto.setBounds(257, 17, 71, 14);
			contentPane.add(lblProducto);
		}
		{
			textField_Producto = new JTextField();
			textField_Producto.setBounds(338, 14, 86, 20);
			contentPane.add(textField_Producto);
			textField_Producto.setColumns(10);
			textField_Producto.setEditable(false);
		}
		{
			JLabel lblCantidad = new JLabel("Cantidad de articulos");
			lblCantidad.setBounds(26, 174, 136, 14);
			contentPane.add(lblCantidad);
		}
		{
			textField_ArticuloCantidad = new JTextField();
			textField_ArticuloCantidad.setBounds(172, 171, 45, 20);
			contentPane.add(textField_ArticuloCantidad);
			textField_ArticuloCantidad.setColumns(10);
		}
		
		
		btnBuscar.setBounds(26, 39, 89, 23);
		contentPane.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (CajaFrame.isNumeric(textField_IdProducto.getText())){
					String idProducto = textField_IdProducto.getText();
					for(Producto products : productos){
						if(products.getId() == Integer.parseInt(idProducto)){
							productoDescripcion = products.getDescripcion();
							producto = products;
						}
					}
					textField_Producto.setText(productoDescripcion);
					textField_ArticuloCantidad.setText("1");
				}
			}
		});
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (CajaFrame.isNumeric(textField_ArticuloCantidad.getText())){
							Articulo articulo = new Articulo();
							articulo.setCantidad(Integer.parseInt(textField_ArticuloCantidad.getText()));
							articulo.setProducto(producto);
							articulo.setValor(producto.getPrecio());
							articulo.totalizar();
							
							CajaFrame.pasarArticulo(articulo);
							setVisible(false);
						}
					}
				});
			}
			{
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						System.exit(0);	
					}
				});
			}
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane.setLayout(new BorderLayout(0, 0));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
