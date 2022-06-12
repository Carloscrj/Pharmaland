package model;

public class Medicamento {
	private int id;
	private String nombre;
	private String tipo;
	private String farmaceutica;
	private double precio;
	private int stock;
	
	public Medicamento(int id, String nombre, String tipo, String farmaceutica, double precio, int stock) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.farmaceutica = farmaceutica;
		this.precio = precio;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public String getFarmaceutica() {
		return farmaceutica;
	}

	public double getPrecio() {
		return precio;
	}

	public int getStock() {
		return stock;
	}
	
	public Object[] getRowData(){
		Object[] data = new Object[5];
		
		data[0] = nombre;
		data[1] = tipo;
		data[2] = farmaceutica;		
		data[3] = precio;
		data[4] = stock;
		
		return data;
	}
	
	public Object[] getRowData2(int cantidad){
		Object[] data = new Object[6];
		
		data[0] = nombre;
		data[1] = tipo;
		data[2] = farmaceutica;		
		data[3] = precio;
		data[4] = stock;
		data[5] = cantidad;
		
		return data;
	}

	@Override
	public String toString() {
		return "id: " + id + ", nombre: " + nombre + ", tipo: " + tipo + ", farmaceutica: " + farmaceutica
				+ ", precio: " + precio + ", stock: " + stock;
	}
}