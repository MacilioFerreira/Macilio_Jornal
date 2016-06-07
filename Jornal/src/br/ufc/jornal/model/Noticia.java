package br.ufc.jornal.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity(name = "NOTICIA")
@Table(name = "NOTICIA")
public class Noticia {

	@Id 
	@Column (name = ("NOTICIA_ID"), nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long noticiaId;
	
	@Column (name = ("TITULO"), nullable = false)
	private String titulo;
	
	@Column (name = ("SUBTITULO"), nullable = false)
	private String subtitulo;
	
	@Column (name = ("TEXTO"), nullable = false)
	private String texto;
		
	@Version
	@Column (name = ("DATA_NOTICIA"), nullable = false)
	private Timestamp dataNoticia;
	
	@Column (name = "SECAO_ID", nullable = false, insertable=false, updatable=false)
	private Long secaoId;
	
	@Column (name = ("VISIVEL"), nullable = false)
	private boolean isVisivel;
	
	@Column(name="CAMINHO_IMAGEM")
	private String caminho_imagem;
	
	private String nomeImagem;
	
	// Relacionamentos
	
	// Muitas notícias podem estar para uma única seção
	@ManyToOne(optional = false, cascade=CascadeType.ALL)
	@JoinColumn(name = "secao_id", referencedColumnName = "secao_id") // ID_SECAO referencia ID
	private Secao secao;
	
	// Várias Notícias podem ter o mesmo autor
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "autor_id", referencedColumnName = "usuario_id")
	private Usuario autorNoticia;
	
	// Um noticia pode ter várias comentários..
	@OneToMany(mappedBy = "noticiaDeOrigem", targetEntity = Comentario.class, fetch = FetchType.LAZY)
	private List<Comentario> comentarios;

	// Getters e Setters	
	public String getTitulo() {
		return titulo;
	}

	public Long getNoticiaId() {
		return noticiaId;
	}

	public void setNoticiaId(Long noticiaId) {
		this.noticiaId = noticiaId;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	
	public Timestamp getDataNoticia() {
		return dataNoticia;
	}

	public void setDataNoticia(Timestamp dataNoticia) {
		this.dataNoticia = dataNoticia;
	}

	public Long getSecaoId() {
		return this.secaoId;
	}

	public void setSecaoId(Long secaoId) {
		this.secaoId = secaoId;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Usuario getAutorNoticia() {
		return autorNoticia;
	}

	public void setAutorNoticia(Usuario autorNoticia) {
		this.autorNoticia = autorNoticia;
	}


	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	
	public String getCaminho_imagem() {
		return caminho_imagem;
	}

	public void setCaminho_imagem(String caminho_imagem) {
		this.caminho_imagem = caminho_imagem;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}
	
	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	// To String
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("Id: " + noticiaId);
		sb.append("Titulo: " + titulo);
		sb.append("Subtitulo: " + subtitulo);
		sb.append("Texto: " + texto);
		sb.append("Data da Notícia: " + dataNoticia);
		sb.append("Id Seção: " + secaoId);
		
		return sb.toString();
	}

	
	
	
}
