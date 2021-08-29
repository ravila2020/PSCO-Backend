package mx.com.oneproject.spco.respuesta;

public class AnsLogonOut {

	private String cr;
	private String descripcion;
	
		private String idEmpresa; 
		private String idRecinto; 
		private String idUsuario; 
		private String idPerfil;
		private boolean authenticated;
		private boolean bloqueado; 
		private String estadoUsuario;
		private String token;
		
		public String getCr() {
			return cr;
		}
		public void setCr(String cr) {
			this.cr = cr;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public String getIdEmpresa() {
			return idEmpresa;
		}
		public void setIdEmpresa(String idEmpresa) {
			this.idEmpresa = idEmpresa;
		}
		public String getIdRecinto() {
			return idRecinto;
		}
		public void setIdRecinto(String idRecinto) {
			this.idRecinto = idRecinto;
		}
		public String getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}
		public boolean isAuthenticated() {
			return authenticated;
		}
		public void setAuthenticated(boolean authenticated) {
			this.authenticated = authenticated;
		}
		public boolean isBloqueado() {
			return bloqueado;
		}
		public void setBloqueado(boolean bloqueado) {
			this.bloqueado = bloqueado;
		}
		public String getEstadoUsuario() {
			return estadoUsuario;
		}
		public void setEstadoUsuario(String estadoUsuario) {
			this.estadoUsuario = estadoUsuario;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getIdPerfil() {
			return idPerfil;
		}
		public void setIdPerfil(String idPerfil) {
			this.idPerfil = idPerfil;
		}		

		
		
}
