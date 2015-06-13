package br.ufc.jornal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity(name = "ROLE")
@Table(name = "ROLE")
public class Role {

	@Id
	@Column(name = ("ROLE_ID"), nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleId;

	@Column(name = ("ROLE"), nullable = false)
	private String role;

	// Relacionamentos

	@ManyToMany(mappedBy = "roles" , fetch = FetchType.LAZY)
	private List<Usuario> usuarioList;
	
	// Getters and Setters
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	// To String
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("ID: " + roleId);
		sb.append("Role: " + role);
		return sb.toString();
	}

}
