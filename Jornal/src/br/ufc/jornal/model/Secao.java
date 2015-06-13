package br.ufc.jornal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "SECAO")
@Table(name = "SECAO")
public class Secao {

	@Id
	@Column(name = ("SECAO_ID"), nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long secaoId;

	@Column(name = ("TITULO"), nullable = false)
	private String titulo;

	@Column(name = ("DESCRICAO"), nullable = false)
	private String descricao;

	// Relacionamentos

	// Um para Muitos.. Uma seção pode ter muitas Notícias
	@OneToMany(mappedBy = "secao", targetEntity = Noticia.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Noticia> noticias;

	// To String
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("ID: " + secaoId);
		sb.append("Titulo: " + titulo);
		sb.append("Descrição: " + descricao);

		return sb.toString();

	}

	// Getters and Setters
	public Long getSecaoId() {
		return secaoId;
	}

	public void setSecaoId(Long secaoId) {
		this.secaoId = secaoId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

}