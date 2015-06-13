package br.ufc.jornal.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity (name = "CLASSIFICADO")
@Table(name = "CLASSIFICADO")
public class Classificado {

	@Id
	@Column (name = "CLASSIFICADO_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long classificadoId;
	
	@Column (name = ("TITULO"), nullable = false)
	private String titulo;
	
	@Column (name = ("TEXTO"), nullable = false)
	private String texto;
	
	@Column (name = ("PRECO"), nullable = false)
	private Float preco;
	
	@Column (name = ("TELEFONE"), nullable = false)
	private String telefone;
	
	@Column (name = ("MELHOR_OFERTA"), nullable = false)
	private Float melhor_oferta;
	
	@Column (name = ("DATA_OFERTA"), nullable = false)
	private Timestamp data_oferta;
	
	@Column (name = ("AUTOR_ID"), nullable = false, insertable=false, updatable=false)
	private String autor_id;
	
	// Relacionamentos
	
	// Vários pedidos em um classificado pode ter só um autor
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "autor_id", referencedColumnName = "usuario_id")
	private Usuario autor;
	
	
	public Long getClassificadoId() {
		return classificadoId;
	}



	public void setClassificadoId(Long classificadoId) {
		this.classificadoId = classificadoId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Float getMelhor_oferta() {
		return melhor_oferta;
	}

	public void setMelhor_oferta(Float melhor_oferta) {
		this.melhor_oferta = melhor_oferta;
	}

	public Timestamp getData_oferta() {
		return data_oferta;
	}

	public void setData_oferta(Timestamp data_oferta) {
		this.data_oferta = data_oferta;
	}

	public String getAutorId() {
		return autor_id;
	}

	public void setAutorId(String autorId) {
		this.autor_id = autorId;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	// To String
	@Override
	public String toString() {
	
		StringBuffer sb = new StringBuffer();
		sb.append("ID: " + classificadoId);
		sb.append("Titulo: " + titulo);
		sb.append("Texto: " + texto);
		sb.append("Preço: " + preco);
		sb.append("Telefone: " + telefone);
		sb.append("Melhor Oferta: " + melhor_oferta);
		sb.append("Data da Oferta: " + data_oferta);
		sb.append("Autor da Oferta: " + autor_id);		
		
		return sb.toString();
	}
	
}
