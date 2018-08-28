import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet("/loga")
public class Loga extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
	 HttpServletResponse response)
			 throws ServletException, IOException {
	}
	@Override
	protected void doPost(HttpServletRequest request,
	 HttpServletResponse response)
			 throws ServletException, IOException {
		try {
			DAO dao = new DAO();
			usuario usuario = new usuario();
			usuario.setEmail(request.getParameter("email")); 
			usuario.setSenha(request.getParameter("senha")); 
			dao.loga(usuario);
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("logado " + usuario.getPrimeiroNome());
			out.println("</body></html>");
			dao.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
