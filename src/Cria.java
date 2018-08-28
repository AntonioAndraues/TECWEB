import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet("/cria")
public class Cria extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	 protected void doGet(HttpServletRequest request,
			 HttpServletResponse response)
					 throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.sendRedirect("register.html");
//		out.println("<html><body>");
//		out.println("<form method='post'>");
//		out.println("PrimeiroNome: <input type='text' name='primeironome'><br>");
//		out.println("UltimoNome: <input type='text' name='ultimonome'><br>");
//		out.println("Email: <input type='text' name='email'><br>");
//		out.println("Senha: <input type='text' name='senha'><br>");
//		out.println("<input type='submit' value='Submit'>");
//		out.println("</form>");
//		out.println("<body><html>");
	}
	@Override
	 protected void doPost(HttpServletRequest request,
	 HttpServletResponse response)
			 throws ServletException, IOException {
		try {
			DAO dao = new DAO();
			usuario usuario = new usuario();
			usuario.setPrimeiroNome(request.getParameter("primeironome")); 
			usuario.setUltimoNome(request.getParameter("ultimonome"));
			usuario.setEmail(request.getParameter("email")); 
			usuario.setSenha(request.getParameter("senha")); 
			dao.adiciona(usuario);
			PrintWriter out = response.getWriter();
//			out.println("<html><body>");
//			out.println("Usuario adicionado! Primeiro nome: " + usuario.getPrimeiroNome());
//			out.println("</body></html>");
			response.sendRedirect("login.html");
			dao.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
