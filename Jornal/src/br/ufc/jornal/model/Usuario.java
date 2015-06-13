package br.ufc.jornal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/* 
 Mapeando a Entidade Usuário para a Tabela USUARIO
 */

@Entity(name = "USUARIO")
@Table(name = "USUARIO")
public class Usuario {

	@Id
	// Significa, que Id, é a chave primária.
	@Column(name = ("USUARIO_ID"), nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long usuario_id;

	@Column(name = ("LOGIN"), nullable = false, length = 20)
	private String login;
    
	@Column(name = ("SENHA"), nullable = false, length = 8)
	private String senha;

    @Column(name = ("NOME"), nullable = false, length = 50)
	private String nome;

	@Column(name = ("EMAIL"), nullable = false)
	private String email;
	
	private Long roleId;

	// Relacionamentos
	// Usuário pode ter vários pedido em um classificado
	@OneToMany(mappedBy = "autor", targetEntity = Classificado.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Classificado> classificados;

	// Um usuário pode adicionar várias noticias..
	@OneToMany(mappedBy = "autorNoticia", targetEntity = Noticia.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Noticia> noticias;

	// Um usuário pode adicionar várias comentários..
	@OneToMany(mappedBy = "autorComentario", targetEntity = Comentario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comentario> comentarios;

	// Usuário => Role
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "USUARIO_ROLE", 
		joinColumns = @JoinColumn(name = "usuario_id" ,referencedColumnName = "usuario_id" ), 
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id" ) )
	private List<Role> roles;

	// Getters and Setters
	public Long getId() {
		return usuario_id;
	}

	public void setId(Long id) {
		this.usuario_id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}

	public List<Classificado> getClassificados() {
		return classificados;
	}

	public void setClassificados(List<Classificado> classificados) {
		this.classificados = classificados;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	public Long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public boolean user_role(String role){
		for (Role r : roles) {
			if (r.getRole().equals(role)) {
				return true;
			}
		}
		
		return false;
	}

	
	

	// To String
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("ID: " + usuario_id);
		sb.append("Login: " + login);
		sb.append("Senha: " + senha);
		sb.append("Nome: " + nome);
		sb.append("E-mail: " + email);

		return sb.toString();
	}

}
