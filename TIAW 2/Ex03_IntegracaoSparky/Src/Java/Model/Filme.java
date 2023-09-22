package model;

public class Filme {
  private Integer id;
  private String nome;
  private String genero;
  private Float avaliacao;
  
  public Filme() {
	  
  }
  
  public Filme(Integer id,String nome,String genero,Float avaliacao) {
	  this.id = id;
	  this.nome = nome;
	  this.genero = genero;
	  this.avaliacao = avaliacao;
  }
  
  public Integer getId() {
	  return id;
  }
  
  public String getNome() {
	  return nome;
  }
  
  public String getGenero() {
	  return genero;
  }
  
  public Float getAvaliacao() {
	  return avaliacao;
  }
  
  public void setId(Integer id) {
	this.id = id;  
  }
  
  public void setNome(String nome) {
	this.nome = nome;  
  }
  
  public void setGenero(String genero) {
	this.genero = genero;  
  }
  
  public void setAvaliacao(Float avaliacao) {
	this.avaliacao = avaliacao;  
  }
  
  public String toString() {
	  return "Filme [ Id = " + id + ", Nome = " + nome + ", Gênero = " + genero + ", Avaliação = " + avaliacao + " ]\n";
  }
}
