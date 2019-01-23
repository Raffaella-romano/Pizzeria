package adminDatabase;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdmAccountModel;
import model.ClientModel;

import model.OrdineModel;
import model.ProductModel;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   static String db = "pizzeria";
   static String username = "root";
   static String password = "root";
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String primaryKey = request.getParameter("primaryKey");
		String table = request.getParameter("table");
		
		System.out.println("Pk= " + primaryKey + ",tabella= " + table + ".");
		boolean result = false ;
		
	switch (table) {
	case "adminaccount": {
			
		AdmAccountModel model = new AdmAccountModel(db,username,password);
		System.out.println("Tabella " + table );
		
	try {
			result = model.doDeleteString(primaryKey);
		}
	catch (SQLException e) {
			e.printStackTrace();
		}
		break;
		}
		
	case "cliente": {
		ClientModel model = new ClientModel(db,username,password);
		System.out.println("Tabella " + table);
		
	try {
			result= model.doDeleteString(primaryKey);
		}
	catch (SQLException e) {
			e.printStackTrace();
		}
		
		break;
		}

	case "ordine": {
		OrdineModel model = new OrdineModel(db,username,password);
		System.out.println("Tabella" + table);
	try {
	result = model.doDeleteInt(Integer.parseInt(primaryKey));
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
		break;
	}
	
	case "prodotto": {
		ProductModel model = new ProductModel(db,username,password);
		System.out.println("Tabella" + table);
	try {
	result = model.doDeleteInt(Integer.parseInt(primaryKey));
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
		break;
	}
	default : {
		System.out.println("Tabella sconosciuta. Impossibile eliminare.");
		break;
	}
}
	System.out.println("Deleted item [PrimaryKey:" + primaryKey +"] from table " + table +": " + result);
	//Trasferisco sulla pagina di inserimento
	RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/DynamicTab?tab=" + table);
	dispositivo.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
