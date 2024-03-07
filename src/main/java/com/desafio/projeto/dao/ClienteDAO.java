package com.desafio.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafio.projeto.model.Cliente;

/**
 * A classe {@code ClienteDAO} fornece métodos para interagir com a tabela de clientes 
 * no banco de dados. 
 */
public class ClienteDAO {
	
	/**
     * Retorna uma lista de todos os clientes presentes na tabela de clientes.
     * 
     * @return uma lista de objetos {@code Cliente}
     * @throws Exception se ocorrer um erro durante a consulta ao banco de dados
     */
    public List<Cliente> findAll() throws Exception {
    	Connection connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM cliente";
        try {        		
            PreparedStatement statement = connection.prepareStatement(query);         
            try (ResultSet resultSet = statement.executeQuery()) {            	
                return createClienteList(resultSet);                
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new Exception("Problemas ao pesquisar os registros de Cliente.");
        }
    }
	
    /**
     * Retorna o cliente com o ID especificado.
     * 
     * @param id o ID do cliente a ser encontrado
     * @return o objeto {@code Cliente} encontrado
     * @throws Exception se ocorrer um erro durante a consulta ao banco de dados
     */
    public Cliente findById(Long id) throws Exception {
    	Connection connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM cliente WHERE id = ?";
        try {    		
	         PreparedStatement statement = connection.prepareStatement(query);
	         statement.setLong(1, id);             
	         try (ResultSet resultSet = statement.executeQuery()) {            	
                return createCliente(resultSet);                
	         }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new Exception("Problemas ao pesquisar os registros de Cliente.");
        }
    }
	
    /**
     * Retorna o cliente com o nome especificado.
     * 
     * @param nome o nome do cliente a ser encontrado
     * @return o objeto {@code Cliente} encontrado
     * @throws Exception se ocorrer um erro durante a consulta ao banco de dados
     */
    public Cliente findByNome(String nome) throws Exception {
    	Connection connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM cliente WHERE nome = ?";
        try {     		
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nome);             
        	try (ResultSet resultSet = statement.executeQuery()) {            	
                return createCliente(resultSet);                
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new Exception("Problemas ao pesquisar os registros de Cliente.");
        }
    }

    /**
     * Insere um novo cliente na tabela de clientes.
     * 
     * @param cliente o objeto {@code Cliente} a ser inserido
     * @return o objeto {@code Cliente} inserido com o ID atualizado
     * @throws Exception se ocorrer um erro durante a inserção no banco de dados
     */
	public Cliente insert(Cliente cliente) throws Exception {
    	Connection connection = ConnectionManager.getConnection();
        String query = "INSERT INTO cliente (nome, endereco, bairro) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEndereco());
            statement.setString(3, cliente.getBairro());            
            statement.executeUpdate(); 
            return cliente; 
        } catch (SQLException e) {
        	e.printStackTrace();    	
            throw new Exception("Problemas ao inserir os registros de Cliente.");
		}
    }
    
	/**
     * Retorna o último ID inserido na tabela de clientes.
     * 
     * @return o último ID inserido na tabela de clientes
     * @throws Exception se ocorrer um erro durante a consulta ao banco de dados
     */
    public Long getLastId() throws Exception {
    	Connection connection = ConnectionManager.getConnection();
        String query = "SELECT MAX(id) as id FROM cliente";
        try {     		
            PreparedStatement statement = connection.prepareStatement(query);
        	try (ResultSet resultSet = statement.executeQuery()) {   
            	if(resultSet.next()) {         	
            		return resultSet.getLong("id");      
            	}
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new Exception("Problemas ao pesquisar os registros de Cliente.");
        }
		return null;

	}

	private List<Cliente> createClienteList(ResultSet resultSet) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
    	while(resultSet.next()) {
    		clientes.add(createClienteByResultSet(resultSet));
        }
        return clientes;
	}
    
    private Cliente createCliente(ResultSet resultSet) throws SQLException {
    	Cliente cliente = null;
    	if(resultSet.next()) {
    		cliente = createClienteByResultSet(resultSet);
        }
        return cliente;
	}
    
    private Cliente createClienteByResultSet(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getLong("id"));
        cliente.setNome(resultSet.getString("nome"));
        cliente.setEndereco(resultSet.getString("endereco"));
        cliente.setBairro(resultSet.getString("bairro"));
        return cliente;
	}

}
