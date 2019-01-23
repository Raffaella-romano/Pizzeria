package adminDatabase;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProdottoBean;
import model.ProductModel;

@WebServlet("/AddProdotto")

public class AddProdotto extends HttpServlet {
private static final long serialVersionUID = 1L;


static String db = "pizzeria";
static String username = "root";
static String password = "root";


ProductModel model = new ProductModel(db,username,password);


public AddProdotto() {
	super();
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	System.out.println("Errore. This Servlet must be called with POST method.");
	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
int codice = Integer.parseInt(request.getParameter("codice"));
String nome = request.getParameter("nome");
String tipo = request.getParameter("tipo");
double prezzo = Double.parseDouble(request.getParameter("prezzo"));
String ingredienti = request.getParameter("ingredienti");




ProdottoBean bean = new ProdottoBean();
bean.setNome(nome);
bean.setPrezzo(prezzo);
bean.setCodice(codice);
bean.setComponenti(ingredienti);
bean.setTipo(tipo);

try {
	model.doAddProduct(bean);
}
catch (SQLException e) {
	e.printStackTrace();
	System.out.println("[AddProdotto.java] Errore: " +e);
}
RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newProdotto.jsp");
dispositivo.forward(request, response);
}
}
