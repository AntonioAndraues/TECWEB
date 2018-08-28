import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/lista")
public class Lista extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			 HttpServletResponse response)
					 throws ServletException, IOException {
		DAO dao;
		try {
			dao = new DAO();
			List<usuario> listausuario = dao.getLista();
			PrintWriter out = response.getWriter();
			out.println("<html><body><table border='1'>");
			out.println("<tr><td>ID</td><td>Nome</td>" +
					 "<td>Nascimento</td><td>Altura</td></tr>");
			for (usuario usuario : listausuario) {
				out.println("<tr><td>" + usuario.getIdPessoa() + "</td>");
				out.println("<td>" + usuario.getPrimeiroNome() + "</td>");
				out.println("<td>" + usuario.getUltimoNome() + "</td>");
				out.println("<td>" + usuario.getEmail() + "</td></tr>"); 
				out.println("<td>" + usuario.getSenha() + "</td></tr>"); 
			}
			out.println("</table></body></html>");
			dao.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
