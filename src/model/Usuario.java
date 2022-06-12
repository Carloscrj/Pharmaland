package model;

public class Usuario {
	private String password;
	private String apeNom;
	
	public Usuario(String password, String apeNom) {
		this.password = password;
		this.apeNom = apeNom;
	}

	public String getApeNom() {
		return apeNom;
	}

	public String getPassword() {
		return password;
	}

}