package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.InicioControl;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class VCrear extends JFrame {
	private static final int ANCHO = 400;
	private static final int ALTO = 350;
	public static final String BTN_CREAR = "Crear";
	public static final String BTN_VOLVER = "Volver";
	
	private JTextField txtUsuario;
	private JPasswordField txtPwd;
	private JButton btnCrear;
	private JButton btnVolver;
	private JPasswordField txtPwdC;

	public VCrear() {
		getContentPane().setBackground(new Color(204, 255, 153));
		init();
		centrarVentana();
	}

	public void init() {
		setTitle("Crear Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(99, 40, 75, 17);
		getContentPane().add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(93, 90, 98, 17);
		getContentPane().add(lblPassword);
		
		txtUsuario = new JTextField();
		txtUsuario.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtUsuario.setBackground(new Color(204, 255, 153));
		txtUsuario.setBounds(162, 39, 114, 21);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPwd = new JPasswordField();
		txtPwd.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPwd.setBackground(new Color(204, 255, 153));
		txtPwd.setBounds(162, 89, 114, 21);
		getContentPane().add(txtPwd);
		
		btnCrear = new JButton(BTN_CREAR);
		btnCrear.setBorderPainted(false);
		btnCrear.setForeground(new Color(255, 255, 255));
		btnCrear.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCrear.setBackground(new Color(102, 102, 0));
		btnCrear.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCrear.setBounds(66, 195, 113, 45);
		getContentPane().add(btnCrear);
		
		btnVolver = new JButton(BTN_VOLVER);
		btnVolver.setBorderPainted(false);
		btnVolver.setBackground(new Color(102, 102, 0));
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnVolver.setBounds(189, 195, 128, 45);
		getContentPane().add(btnVolver);
		
		JLabel lblPasswordC = new JLabel("Confirmar Password:");
		lblPasswordC.setBounds(38, 146, 136, 17);
		getContentPane().add(lblPasswordC);
		
		txtPwdC = new JPasswordField();
		txtPwdC.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPwdC.setBackground(new Color(204, 255, 153));
		txtPwdC.setBounds(172, 145, 114, 21);
		getContentPane().add(txtPwdC);
		
		setSize(400, 315);
	}
	
	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO, ALTO));
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = this.getPreferredSize();
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void setControlador(InicioControl control) {
		btnCrear.addActionListener(control);
		btnVolver.addActionListener(control);
	}
	
	public void limpiarDatos() {
		txtUsuario.setText(null);
		txtPwd.setText(null);
		txtPwdC.setText(null);
	}
	
	public String getUser() {
		return txtUsuario.getText();
	}

	public String getContrasena() {
		return txtPwd.getText();
	}

	public String getCContrasena() {
		return txtPwdC.getText();
	}
	
	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this,  mensaje, "Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}

	public boolean comprobarContrasenas() {
		boolean b;
		
		if (txtPwd.getText().equals(txtPwdC.getText())) {
			b = false;
		}else {
			b = true;
		}
		
		return b;
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}
}