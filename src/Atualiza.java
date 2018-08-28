import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet("/atualiza")
public class Atualiza extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
	 HttpServletResponse response)
			 throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("ID: <input type='number' name='id'><br>");
		out.println("Nome: <input type='text' name='nome'><br>");
		out.println("Ultimo Nome: <input type='text' name='ultimo_nome'><br>");
		out.println("Email: <input type='text' name='email'><br>");
		out.println("Nova Senha: <input type='text' name='senha'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
	}
	@Override
	protected void doPost(HttpServletRequest request,
	 HttpServletResponse response)
			 throws ServletException, IOException {
		try {
			DAO dao = new DAO();
			usuario usuario = new usuario();
			usuario.setIdPessoa(Integer.valueOf(request.getParameter("id")));
			usuario.setPrimeiroNome(request.getParameter("nome"));
			usuario.setUltimoNome(request.getParameter("ultimo_nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			dao.altera(usuario);
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("atualizado" + usuario.getPrimeiroNome());
			out.println("</body></html>");
			dao.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
