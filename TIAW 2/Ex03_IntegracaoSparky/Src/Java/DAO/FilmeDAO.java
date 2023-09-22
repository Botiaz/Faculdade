package dao;

import model.Filme;
import java.sql.*;
import java.util.*;

public class FilmeDAO {
  private Connection conn;
  
  public FilmeDAO() {
	 boolean status = false;
	  try {
		  Class.forName("org.postgresql.Driver");
		  String dbURL = "jdbc:postgresql://localhost:5432/teste";
		  String username = "postgres";
		  String password = "ti@cc";
		  this.conn = DriverManager.getConnection(dbURL,username,password);
		  if(conn != null) {
			  status = true;
			  System.out.println("Connection: " + status);
		  }
	  }catch(ClassNotFoundException | SQLException e) {
		  System.out.println(e);
	 }
	  
  }
  
  public boolean insert(Filme filme) {
		boolean status = false;
		try {  
			Statement st = conn.createStatement();
			String sql = "INSERT INTO filmes (codigo, nome, avaliacao, genero) "
				       + "VALUES ("+filme.getId()+ ", '" + filme.getNome() + "', '"  
				       + filme.getAvaliacao() + "', '" + filme.getGenero() + "');";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
}

public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conn.createStatement();
			String sql = "DELETE FROM filmes WHERE codigo = " + codigo;				
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
}

public boolean update(Filme filme) {
	boolean status = false;
	try {  
		Statement st = conn.createStatement();
		String sql = "UPDATE filmes SET nome = '" + filme.getNome() + "', avaliacao = '"  
			       + filme.getAvaliacao() + "', genero = '" + filme.getGenero() + "'"
				   + " WHERE codigo = " + filme.getId();			
		st.executeUpdate(sql);
		st.close();
		status = true;
	} catch (SQLException u) {  
		throw new RuntimeException(u);
	}
	return status;
}
  
public List<Filme> listarOrderById(){
	return listar("codigo");
}

public List<Filme> listarOrderByNome(){
	return listar("nome");
}

public List<Filme> listarOrderByAvaliacao(){
	return listar("avaliacao");
}

public List<Filme> listarOrderByGenero(){
	return listar("genero");
}

private List<Filme> listar(String orderBy){
	  List<Filme> filmes = new ArrayList<>();
	  
	  try {
		  Statement st = conn.createStatement();
          String sql = "SELECT * FROM filmes" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy)) + ";";
          ResultSet resultado = st.executeQuery(sql);
          while(resultado.next()) {
        	  int codigo = resultado.getInt("codigo");
              String nome = resultado.getString("nome");
              Float avaliacao = resultado.getFloat("avaliacao");
              String genero = resultado.getString("genero");

              Filme filme = new Filme(codigo, nome, genero, avaliacao);
              filmes.add(filme);
          }
          st.close();
          resultado.close();
	  }catch(SQLException e) {
		  System.out.println(e);
	  }
	  return filmes;
  }
  
  public Filme getFilme(int id) {
	  Filme filme = null;
	  try {
		  Statement st = conn.createStatement();
          String sql = "SELECT * FROM filmes WHERE codigo = " + id + ";";
          ResultSet resultado = st.executeQuery(sql);
          
          while(resultado.next()) {
        	int codigo = resultado.getInt("codigo");
            String nome = resultado.getString("nome");
            Float avaliacao = resultado.getFloat("avaliacao");
            String genero = resultado.getString("genero"); 
            filme = new Filme(codigo, nome, genero, avaliacao);
          }
	  }catch(SQLException e) {
		  System.out.println(e);
	  }
	  
	return filme;
  }
  
}
