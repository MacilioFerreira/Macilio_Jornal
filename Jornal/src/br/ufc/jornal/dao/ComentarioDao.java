package br.ufc.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.ufc.jornal.model.Comentario;

@Repository
public class ComentarioDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void inserir(Comentario c){
		this.manager.persist(c);		
	}
	
	public void alterar(Comentario c){
		this.manager.merge(c);
	}
	
	public List<Comentario> listar(){
		
		String hql = "select c from COMENTARIO c";
		List<Comentario> comentarios = this.manager.createQuery(hql, Comentario.class).getResultList();
		
		return comentarios;
	}
	
	public void remover(Comentario c){
		Comentario comentario = this.manager.find(Comentario.class,c.getComentId() );
		this.manager.remove(comentario);
	}
	
	public Comentario getNoticia(Long id){
		return this.manager.find(Comentario.class, id);
	}
}
