package br.ufc.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.ufc.jornal.model.Usuario;

@Repository
public class UsuarioDao{
	
	@PersistenceContext
	private EntityManager manager;

		public void adicionar(Usuario user) {
			this.manager.persist(user);
	}

	public void remover(Usuario user) {
		
		Usuario u = this.manager.find(Usuario.class, user.getId());
		this.manager.remove(u);
	}

	public List<Usuario> listar() {
		
		String hql = "select u from USUARIO u ";
		List<Usuario> users = manager.createQuery(hql,Usuario.class).getResultList();
		return users;
	}

	public void alterar(Usuario user) {
		this.manager.merge(user);
		
	}
	
	public Usuario getUserId(Long id){
		return this.manager.find(Usuario.class, id);
	}
	
	public Usuario getUserLogin(String login){
		String sql = "select u from USUARIO u where u.login = :login";
		TypedQuery<Usuario> query = this.manager.createQuery(sql,Usuario.class);
		query.setParameter("login", login).getResultList();
		List<Usuario> user = query.getResultList();
		if(user.size() == 0){
			return null;
		}else{
			return user.get(0); 
		}
	}
	
	
}
