package br.ufc.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	
/*	public float getOfertas(){ Sem necessidade, pois as ofertas não são armazenadas no banco.
		
		String hql = "select d from CLASSIFICADO d where d.melhor_oferta = "
				+ "(select max(c.melhor_oferta) from CLASSIFICADO c where c.classificadoId = d.classificadoId ) ";
		List<Classificado> classificados_banco = this.manager.createQuery(hql, Classificado.class).getResultList();
		return classificados_banco.get(0).getMelhor_oferta();
	} */
	
	public Classificado getCass(Long id_cass){		
	    
        String hql = " SELECT c FROM CLASSIFICADO c WHERE c.classificadoId = :id_cass";  
		
		TypedQuery<Classificado> query = this.manager.createQuery(hql, Classificado.class);
		query.setParameter("id_cass", id_cass).getResultList();
		List<Classificado> classificados = query.getResultList();
		Classificado c = classificados.get(0);
		
		return c;
	}

	
}
