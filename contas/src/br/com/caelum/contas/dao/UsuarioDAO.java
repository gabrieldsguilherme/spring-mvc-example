package br.com.caelum.contas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.caelum.contas.ConnectionFactory;
import br.com.caelum.contas.modelo.Usuario;

public class UsuarioDAO {
	private static final int USUARIO_SENHA_INDEX = 2;
	private static final int USUARIO_LOGIN_INDEX = 1;
	private static final String SELECT_TEMPLATE = "select %s from %s";
	private static final String INSERT_TEMPLATE = "insert into %s values %s";
	
	private Connection connection;

	public UsuarioDAO() {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean existeUsuario(Usuario usuario) {
		validarUsuarioNull(usuario);
		
		try {
			String select = String.format(SELECT_TEMPLATE, "*", "usuarios where login = ? and senha = ?");
			PreparedStatement stmt = this.connection.prepareStatement(select);
			stmt.setString(USUARIO_LOGIN_INDEX, usuario.getLogin());
			stmt.setString(USUARIO_SENHA_INDEX, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();

			boolean encontrado = rs.next();
			rs.close();
			stmt.close();
			
			return encontrado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insere(Usuario usuario) {
		validarUsuarioNull(usuario);
		
		try {
			String insert = String.format(INSERT_TEMPLATE, "usuarios (login, senha)", "(?, ?)");
			PreparedStatement stmt = this.connection.prepareStatement(insert);
			stmt.setString(USUARIO_LOGIN_INDEX, usuario.getLogin());
			stmt.setString(USUARIO_SENHA_INDEX, usuario.getSenha());
			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	
	private void validarUsuarioNull(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo");
		}
	}
}

