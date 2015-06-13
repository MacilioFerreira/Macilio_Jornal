package br.ufc.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.ufc.jornal.model.Noticia;

@Repository
public class NoticiaDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void inserir(Noticia n){		
		this.manager.persist(n);
		
	}
	
	public void alterar(Noticia n){		
		this.manager.merge(n);
		
	}

	public List<Noticia> listar(){
		
		String hql = "select n from NOTICIA n";
		List<Noticia> noticias = this.manager.createQuery(hql, Noticia.class).getResultList();
		
		return noticias;
	}
	
	public void remover(Noticia n){
		Noticia not = this.manager.find(Noticia.class, n.getNoticiaId());
		this.manager.remove(not);
	}
	
	public Noticia getNoticia(Long id){
		return this.manager.find(Noticia.class, id);
	}
}