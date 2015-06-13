package br.ufc.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.ufc.jornal.model.Classificado;

@Repository
public class ClassificadoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void inserir(Classificado c){
		this.manager.persist(c);
	}
	
	public void alterar(Classificado c){
		this.manager.merge(c);
	}
	
	public List<Classificado> listar(){
		
		String hql = "select c from CLASSIFICADO c";
		List<Classificado> classificados = this.manager.createQuery(hql, Classificado.class).getResultList();
		
		return classificados;
	}
	
	public void remover(Classificado c){
		Classificado d = this.manager.find(Classificado.class, c.getClassificadoId());
		this.manager.remove(d);
	}
	
	public List<Classificado> getClassificado(){
		
		String hql = "select d from CLASSIFICADO d where d.melhor_oferta = (select max(melhor_oferta) from CLASSIFICADO ) ";
		List<Classificado> classificados_banco = this.manager.createQuery(hql, Classificado.class).getResultList();
		return classificados_banco;
	}

	public Classificado getNoticia(Long id){
		return this.manager.find(Classificado.class, id);
	}
}
