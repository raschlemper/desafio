package com.desafio.projeto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A classe {@code ConnectionManager} é responsável por gerenciar a conexão com o banco 
 * de dados e criar as tabelas necessárias, se elas não existirem.
 */
public class ConnectionManager {

	private static final String URL = "jdbc:derby:memory:desafio;create=true";

	/**
     * Obtém uma conexão com o banco de dados.
     * 
     * @return uma conexão com o banco de dados
     */
    public static Connection getConnection() {
        try {
        	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(true);
            createTable(connection);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao obter conexão com o banco de dados.", e);
        }
    }

    /**
     * Fecha a conexão com o banco de dados.
     * 
     * @param connection a conexão a ser fechada
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
    /**
     * Cria as tabelas no banco de dados, se elas ainda não existirem.
     * 
     * @param connection a conexão com o banco de dados
     * @throws SQLException se ocorrer um erro ao criar as tabelas
     */
	private static void createTable(Connection connection) throws SQLException {
		createTableCliente(connection);
		createTableTelefone(connection);
	}
	
	/**
     * Cria a tabela "cliente" no banco de dados, se ela ainda não existir.
     * 
     * @param connection a conexão com o banco de dados
     * @throws SQLException se ocorrer um erro ao criar a tabela "cliente"
     */
	private static void createTableCliente(Connection connection) throws SQLException {
        try {        		
            String query = "SELECT 1 FROM cliente";
            PreparedStatement statement = connection.prepareStatement(query);         
            statement.executeQuery();
        } catch (SQLException e) {		
            String query = "CREATE TABLE cliente ("
            		+ "id INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
            		+ "nome VARCHAR(100) NOT NULL, "
            		+ "endereco VARCHAR(255), "
            		+ "bairro VARCHAR(100), "
            		+ "PRIMARY KEY(id))";
            PreparedStatement statement = connection.prepareStatement(query);         
            statement.executeUpdate();
        }
	}
	
	/**
     * Cria a tabela "telefone" no banco de dados, se ela ainda não existir.
     * 
     * @param connection a conexão com o banco de dados
     * @throws SQLException se ocorrer um erro ao criar a tabela "telefone"
     */
	private static void createTableTelefone(Connection connection) throws SQLException {
        try {       		
            String query = "SELECT 1 FROM telefone";
            PreparedStatement statement = connection.prepareStatement(query);         
            statement.executeQuery();
        } catch (SQLException e) {	
            String query = "CREATE TABLE telefone ("
            		+ "id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
            		+ "idCliente INT NOT NULL,"
            		+ "numero VARCHAR(20) NOT NULL,"
            		+ "PRIMARY KEY(id),"
            		+ "CONSTRAINT fk_telefone_cliente FOREIGN KEY (idCliente) REFERENCES cliente (id))";
            PreparedStatement statement = connection.prepareStatement(query);         
            statement.executeUpdate();
        }
	}
}
