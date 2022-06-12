package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import control.InicioControl;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class VInicio extends JFrame {
	private static final int ANCHO = 400;
	private static final int ALTO = 250;
	public static final String BTN_INICIAR = "Iniciar Sesion";
	public static final String BTN_CREAR = "Crear Usuario";
	
	private JTextField txtUsuario;
	private JPasswordField txtPwd;
	private JButton btnIniciarSesion;
	private JButton btnCrearUsuario;
	private JLabel lblLogo1;
	private JLabel lblLogo2;

	public VInicio() {
		getContentPane().setBackground(new Color(204, 255, 153));
		init();
	}

	public void init() {
		setTitle("Inicio PharmaLand");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(178, 106, 60, 17);
		getContentPane().add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(178, 166, 69, 17);
		getContentPane().add(lblPassword);
		
		txtUsuario = new JTextField();
		txtUsuario.setOpaque(false);
		txtUsuario.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtUsuario.setBackground(new Color(204, 255, 153));
		txtUsuario.setBounds(255, 105, 114, 21);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPwd = new JPasswordField();
		txtPwd.setOpaque(false);
		txtPwd.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPwd.setBackground(new Color(204, 255, 153));
		txtPwd.setBounds(255, 165, 114, 21);
		getContentPane().add(txtPwd);
		
		btnIniciarSesion = new JButton(BTN_INICIAR);
		btnIniciarSesion.setForeground(new Color(255, 255, 255));
		btnIniciarSesion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnIniciarSesion.setBorderPainted(false);
		btnIniciarSesion.setBackground(new Color(102, 102, 0));
		btnIniciarSesion.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnIniciarSesion.setBounds(135, 215, 132, 42);
		getContentPane().add(btnIniciarSesion);
		
		btnCrearUsuario = new JButton(BTN_CREAR);
		btnCrearUsuario.setBorderPainted(false);
		btnCrearUsuario.setBackground(new Color(102, 102, 0));
		btnCrearUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnCrearUsuario.setForeground(new Color(255, 255, 255));
		btnCrearUsuario.setBounds(277, 215, 146, 42);
		getContentPane().add(btnCrearUsuario);
		
		JLabel lblTitulo = new JLabel("PHARMALAND");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTitulo.setBackground(new Color(204, 255, 153));
		lblTitulo.setBounds(165, 42, 301, 31);
		getContentPane().add(lblTitulo);
		
		lblLogo1 = new JLabel("");
		lblLogo1.setIcon(new ImageIcon(getClass().getResource("/logo/Logo5.png")));
		lblLogo1.setBounds(29, 90, 125, 114);
		getContentPane().add(lblLogo1);
		
		lblLogo2 = new JLabel("");
		lblLogo2.setIcon(new ImageIcon(getClass().getResource("/logo/Logo5.png")));
		lblLogo2.setBounds(404, 92, 140, 112);
		getContentPane().add(lblLogo2);
		
		
		setSize(570, 340);
		centrarVentana();
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
		btnIniciarSesion.addActionListener(control);
		btnCrearUsuario.addActionListener(control);
	}
	
	public void limpiarDatos() {
		txtUsuario.setText(null);
		txtPwd.setText(null);
	}
	
	public String getNomUser() {
		String nombre = txtUsuario.getText();
		return nombre;
	}

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this,  mensaje, "Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}

	public String getContrasena() {
		return txtPwd.getText();
	}
}