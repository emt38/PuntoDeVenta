package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AgregarArticuloDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_IdProducto;
	private JTextField textField_Producto;
	private JTextField textField_ArticuloCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarArticuloDialog dialog = new AgregarArticuloDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregarArticuloDialog() {
		this.setTitle("Agregar articulo");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblBuscarProducto = new JLabel("ID Producto");
			lblBuscarProducto.setBounds(26, 14, 81, 14);
			contentPanel.add(lblBuscarProducto);
		}
		{
			textField_IdProducto = new JTextField();
			textField_IdProducto.setBounds(117, 11, 45, 20);
			contentPanel.add(textField_IdProducto);
			textField_IdProducto.setColumns(10);
		}
		{
			JLabel lblProducto = new JLabel("Producto");
			lblProducto.setBounds(205, 14, 71, 14);
			contentPanel.add(lblProducto);
		}
		{
			textField_Producto = new JTextField();
			textField_Producto.setBounds(286, 11, 86, 20);
			contentPanel.add(textField_Producto);
			textField_Producto.setColumns(10);
		}
		{
			JLabel lblCantidad = new JLabel("Cantidad");
			lblCantidad.setBounds(26, 85, 71, 14);
			contentPanel.add(lblCantidad);
		}
		{
			textField_ArticuloCantidad = new JTextField();
			textField_ArticuloCantidad.setBounds(117, 79, 45, 20);
			contentPanel.add(textField_ArticuloCantidad);
			textField_ArticuloCantidad.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Agregar articulo");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
