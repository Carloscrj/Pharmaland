package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import view.VInicio;
import view.VPrincipal;
import persistencia.FarmaciaPersistencia;
import model.Usuario;
import view.PPedido;
import view.PVenta;
import view.VCrear;

public class InicioControl implements ActionListener {
	
	private VInicio vInicio;
	private VPrincipal vPrincipal;
	private VCrear vCrear;
	private PPedido pPedido;
	private PVenta pVenta;
	private FarmaciaPersistencia fPersistencia;
	private int id;
	
	public InicioControl(VInicio vInicio, VPrincipal vPrincipal, VCrear vCrear, PPedido pPedido, PVenta pVenta, FarmaciaPersistencia fPersistencia) {
		this.vInicio = vInicio;
		this.vPrincipal = vPrincipal;
		this.vCrear = vCrear;
		this.pPedido = pPedido;
		this.pVenta = pVenta;
		this.fPersistencia = fPersistencia;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Usuario user = null;
	
		if (ev.getSource() instanceof JButton) {
			if (ev.getActionCommand().equals(VInicio.BTN_INICIAR)) {
				String nombreUsuario = vInicio.getNomUser();
				String contrasena = vInicio.getContrasena();
				
				if (nombreUsuario.trim().isEmpty() || contrasena.trim().isEmpty()) {
					vInicio.mostrarError("Debes rellenar los campos 'Usuario' y  'Password'");
					
				}
				else {
					user = fPersistencia.validarUsuario(nombreUsuario);
					
					if (user != null) {
						if (contrasena.equals(user.getPassword())) {
							abrirPrincipal();
							
							id = fPersistencia.obtenerIdEmple(nombreUsuario);
							
							if (id != 0) {
								fPersistencia.insertarId(id);
								
							}
						}
						else {
							vInicio.mostrarError("Password introducida no es correcta");
						}
					}
					else {
						vInicio.mostrarError("El usuario introducido no esta registrado");
					}
				}
			}
			else if (ev.getActionCommand().equals(VInicio.BTN_CREAR)) {
				abrirCrear();
			}
			else if (ev.getActionCommand().equals(VCrear.BTN_CREAR)) {
				String usuario = vCrear.getUser();
				String pwd = vCrear.getContrasena();
				String confirPwd = vCrear.getCContrasena();
				if (usuario.trim().isEmpty() || pwd.trim().isEmpty() || confirPwd.trim().isEmpty()) {
					vCrear.mostrarError("Debes rellenar todos los campos");
				}
				else if (usuario.length() < 4 || usuario.length() > 20) {
					vInicio.mostrarError("El nombre de usuario debe tener entre 4 y 20 caracteres");
				}
				else if (pwd.length() < 8 || pwd.length() > 25) {
					vInicio.mostrarError("Password debe tener entre 8 y 25 caracteres");
				}
				else {
					user = fPersistencia.validarUsuario(usuario);
					if (user == null) {
						boolean b = vCrear.comprobarContrasenas();
						
						if (b == false) {
							int num = fPersistencia.crearUsuarioNuevo(usuario, pwd);
							if (num == 1) {
								vCrear.mostrarMensaje("El nuevo usuario ha sido registrado correctamente");
								crearUsuario();
							}
							
						}
						else {
							vCrear.mostrarError("Password debe coincidir con el campo 'Confirmar password'");
						}
					}
					else {
						vCrear.mostrarError("El usuario que esta intentando introducir ya esta registrado. Por favor, introduzca otro");
					}
				}
			}
			else if (ev.getActionCommand().equals(VCrear.BTN_VOLVER)) {
				volverInicio();
			}
			else if (ev.getActionCommand().equals(PVenta.BTN_FILTRAR)) {
				pVenta.filtrarVenta(fPersistencia);
			}
			else if (ev.getActionCommand().equals(PVenta.BTN_ANADIR)) {
				pVenta.anadir();
			}
			else if (ev.getActionCommand().equals(PVenta.BTN_LIMPIAR_VENTA)) {
				pVenta.resetearValores(false);
			}
			else if (ev.getActionCommand().equals(PVenta.BTN_QUITAR)) {
				pVenta.quitar();
			}
			else if (ev.getActionCommand().equals(PVenta.BTN_VENTA)) {
				String tPago= pVenta.tipoDePago();
				fPersistencia.insertarTablaVenta(id, tPago);
				pVenta.venta(fPersistencia);
			}
			else if (ev.getActionCommand().equals(PPedido.BTN_FILTRAR)) {
				pPedido.filtrarPedido(fPersistencia);
			}
			else if (ev.getActionCommand().equals(PPedido.BTN_ANADIR)) {
				pPedido.anadir();
			}
			else if (ev.getActionCommand().equals(PPedido.BTN_LIMPIAR)) {
				pPedido.resetearValores(false);
			}
			else if (ev.getActionCommand().equals(PPedido.BTN_PEDIDO)) {
				pPedido.pedir(fPersistencia);
			}
			else if (ev.getActionCommand().equals(PPedido.BTN_QUITAR)) {
				pPedido.quitar();
			}
		}
		else if (ev.getSource() instanceof JMenuItem) {
			if (ev.getActionCommand().equals(VPrincipal.MNTM_PEDIDO)) {
				mostrarPedido();
			}
			else if (ev.getActionCommand().equals(VPrincipal.MNTM_VENTA)) {
				mostrarVenta();
			}
			else if (ev.getActionCommand().equals(VPrincipal.MNTM_CERRAR)) {
				int resp = vPrincipal.mostrarConfirmacion("Se va a cerrar la sesion ¿desea continuar?", 1);
				
				if (resp == 0) {
					vPrincipal.dispose();
					volverInicio();
					vPrincipal.borrarCont();
				}		
			}
		}
	}

		

	private void mostrarVenta() {
		pVenta.resetearValores(true);
		vPrincipal.cargarPanel(pVenta);
	}

	private void mostrarPedido() {
		pPedido.resetearValores(true);
		vPrincipal.cargarPanel(pPedido);
	}

	private void crearUsuario() {
		volverInicio();
	}

	private void abrirPrincipal() {
		vInicio.dispose();
		vInicio.limpiarDatos();
		vPrincipal.hacerVisible();
	}

	private void volverInicio() {
		vCrear.dispose();
		vCrear.limpiarDatos();
		vInicio.hacerVisible();
	}

	private void abrirCrear() {
		vInicio.dispose();
		vInicio.limpiarDatos();
		vCrear.hacerVisible();
	}

	public FarmaciaPersistencia getfPersistencia() {
		return fPersistencia;
	}
}
