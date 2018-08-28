import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DAO {
	private Connection connection = null;
	public DAO() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/meus_dados", "root", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public List<usuario> getLista() {
	List<usuario> listausuarios = new ArrayList<usuario>();
	PreparedStatement stmt;
	try {
		stmt = connection.
				prepareStatement("SELECT * FROM usuario");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			try {
				usuario usuario = new usuario();
				usuario.setIdPessoa(rs.getInt("id_pessoa"));
				usuario.setPrimeiroNome(rs.getString("primeiro_nome"));
				usuario.setUltimoNome(rs.getString("ultimo_nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				listausuarios.add(usuario);
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	return listausuarios;
	
}
public void close() {
try {
	connection.close();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
public void adiciona(usuario usuario) {
	String sql = "INSERT INTO usuario" +
			"(primeiro_nome,ultimo_nome,email,senha) values(?,?,?,?)";
	try {
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1,usuario.getPrimeiroNome());
		stmt.setString(2,usuario.getUltimoNome());
		stmt.setString(3,usuario.getEmail());
		stmt.setString(4,usuario.getSenha());
		stmt.execute();
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void altera(usuario usuario) {
	try {
		String sql = "UPDATE usuario SET " +
				 "primeiro_nome=?,ultimo_nome=?, email=?, senha=? WHERE id_pessoa=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, usuario.getPrimeiroNome());
		stmt.setString(2, usuario.getUltimoNome());
		stmt.setString(3, usuario.getEmail());
		stmt.setString(4, usuario.getSenha());
		stmt.setInt(5, usuario.getIdPessoa());
		stmt.execute();
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void remove(Integer id) {
	try {
		PreparedStatement stmt = connection
				.prepareStatement("DELETE FROM usuario WHERE id_pessoa=?");
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public usuario loga(usuario usuario) {
	try {
		PreparedStatement stmt = connection
				.prepareStatement("SELECT * FROM usuario WHERE email=? AND senha=?");
//		stmt.setString(1,"'"+usuario.getEmail()+"'");
//		stmt.setString(2, "'"+usuario.getSenha()+"'");
		stmt.execute();
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs.getRow());
		System.out.println(usuario.getSenha());
		System.out.println(usuario.getEmail());

		if(rs.getRow()>=1){
			usuario.setIdPessoa(rs.getInt("id_pessoa"));
			usuario.setPrimeiroNome(rs.getString("primeiro_nome"));
			usuario.setUltimoNome(rs.getString("ultimo_nome"));
			
		    }
		else {
			usuario.setPrimeiroNome("USUARIO INVALIDO OU SENHA INVALIDA");
		}
		stmt.close();
		rs.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return usuario;
			}
}
