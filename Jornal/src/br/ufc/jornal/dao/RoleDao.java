package br.ufc.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.ufc.jornal.model.Role;

@Repository
public class RoleDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void adicionar(Role r){
		this.manager.persist(r);
	}
	
	public void atualizar(Role r){
		this.manager.merge(r);
	}
	
	public List<Role> listar(){
		String hql = "select r from ROLE r";
		return manager.createQuery(hql,Role.class).getResultList();
		 
	}
	
	public void remover(Long id){
		Role r1  = this.manager.find(Role.class, new Long(id)); 
		this.manager.remove(r1);
	}
	
	public Role getRoleId(Long id){
		return this.manager.find(Role.class, new Long(id)); // Criar um novo id
	}
	
	public Long getRoleNome(String nome){
		
		String hql = " SELECT r FROM ROLE r WHERE r.role = :nome"; // 
		
		TypedQuery<Role> query = this.manager.createQuery(hql, Role.class);
		query.setParameter("nome", nome).getResultList();
		List<Role> rolesUsuario = query.getResultList();
		Role r = rolesUsuario.get(0);
		
		return r.getRoleId();	
	
	}
	
	public List<Role> usuarioRoles(Long id_usuario){ // Em hql utiliza-se da entidade ou seja da classe da aplicação e não da tabela do banco, Mas defini que usariam da tabela
		
		// Uma consulta usando JOIN FETCH nesse caso pega todos os papeis do usuário passado. 
		String hql = " SELECT r FROM ROLE r JOIN FETCH  r.usuarioList u WHERE u.usuario_id = :id_usuario"; // 
	
		TypedQuery<Role> query = this.manager.createQuery(hql, Role.class);
		query.setParameter("id_usuario", id_usuario).getResultList();
		List<Role> rolesUsuario = query.getResultList();
		
		return rolesUsuario;
		
	}
	
	
	
}
