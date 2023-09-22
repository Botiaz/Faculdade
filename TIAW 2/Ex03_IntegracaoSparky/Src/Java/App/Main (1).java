
package app;

import static spark.Spark.*;
import java.util.HashMap;
import spark.template.velocity.*;
import spark.ModelAndView;
import spark.Response;
import spark.Request;
import dao.FilmeDAO;
import model.Filme;

public class Main {
	private static FilmeDAO db = null;
	
  private static ModelAndView pageHome(Request req, Response res) {
	  HashMap <String, Object> model = new HashMap<>();
	  
	  model.put("filmes", db.listarOrderById());
	 
	  return new ModelAndView(model, "view/home.vm");
  }
  
  private static ModelAndView pageFilme(Request req, Response res) {
	  HashMap <String, Object> model = new HashMap<>();
	  
      Integer id = Integer.parseInt(req.params("id"));
	  
	  model.put("filme", db.getFilme(id));
	 
	  return new ModelAndView(model, "view/update.vm");
  }
  
  private static ModelAndView pageNew(Request req, Response res) {
	  HashMap <String, Object> model = new HashMap<>();
	 
	  return new ModelAndView(model, "view/new.vm");
  }

  private static Object createFilme(Request req, Response res) {
	  
	  Integer id = Integer.parseInt(req.queryParams("id"));
	  String nome = req.queryParams("nome");
	  String genero = req.queryParams("genero");
	  Float avaliacao = Float.parseFloat(req.queryParams("avaliacao"));
	  
	  db.insert(new Filme(id,nome,genero,avaliacao));
	  res.redirect("/filmes");
	  
	  return "ok";
  }
  
private static Object updateFilme(Request req, Response res) {
	  
	  Integer id = Integer.parseInt(req.params("id"));
	  String nome = req.queryParams("nome");
	  String genero = req.queryParams("genero");
	  Float avaliacao = Float.parseFloat(req.queryParams("avaliacao"));
	  
	  db.update(new Filme(id,nome,genero,avaliacao));
	  res.redirect("/filmes");
	  
	  return "ok";
  }

private static Object removeFilme(Request req, Response res) {
    Integer id = Integer.parseInt(req.params("id"));
    db.delete(id);
    res.redirect("/filmes");
    return "ok";
}

	
  public static void main(String[] args){
	  db =  new FilmeDAO();
	  VelocityTemplateEngine engine = new  VelocityTemplateEngine();
	 

	  get("/filmes", Main::pageHome,engine);
	  get("/filme/:id", Main::pageFilme, engine);
	  get("/filmes/new", Main::pageNew, engine);
	  
	  post("/filmes/new", Main::createFilme); 
	  post("/filme/:id/update", Main::updateFilme);
	  post("/filme/:id/remover", Main::removeFilme);
  }
}
