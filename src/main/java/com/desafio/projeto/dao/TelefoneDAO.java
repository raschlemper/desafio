package com.desafio.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafio.projeto.model.Telefone;

/**
 * A classe {@code TelefoneDAO} fornece métodos para interagir com a tabela de telefones 
 * no banco de dados. 
 */
public class TelefoneDAO {
	
	/**
     * Retorna uma lista de todos os telefones presentes na tabela de telefones.
     * 
     * @return uma lista de objetos {@code Telefone}
     * @throws Exception se ocorrer um erro durante a consulta ao banco de dados
     */
    public List<Telefone> findAll() throws Exception {
    	Connection connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM telefone";
        try {
            PreparedStatement statement = connection.prepareStatement(query);            
            try (ResultSet resultSet = statement.executeQuery()) {            	
                return createTelefoneList(resultSet);                
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new Exception("Problemas ao pesquisar os registros de Telefone.");
        }
    }
	
    /**
     * Retorna o telefone com o ID especificado.
     * 
     * @param id o ID do telefone a ser encontrado
     * @return o objeto {@code Telefone} encontrado
     * @throws Exception se ocorrer um erro durante a consulta ao banco de dados
     */
    public Telefone findById(Long id) throws Exception {
    	Connection connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM telefone WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
             statement.setLong(1, id);             
            try (ResultSet resultSet = statement.executeQuery()) {            	
                return createTelefone(resultSet);                
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new Exception("Problemas ao pesquisar os registros de Telefone.");
        }
    }
	
    /**
     * Retorna o telefone com o número especificado.
     * 
     * @param numero o número do telefone a ser encontrado
     * @return o objeto {@code Telefone} encontrado
     * @throws Exception se ocorrer um erro durante a consulta ao banco de dados
     */
    public Telefone findByNumero(String numero) throws Exception {
    	Connection connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM telefone WHERE numero = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
             statement.setString(1, numero);             
            try (ResultSet resultSet = statement.executeQuery()) {            	
                return createTelefone(resultSet);                
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new Exception("Problemas ao pesquisar os registros de Telefone.");
        }
    }

    /**
     * Insere um novo telefone na tabela de telefones.
     * 
     * @param telefone o objeto {@code Telefone} a ser inserido
     * @return o objeto {@code Telefone} inserido com o ID atualizado
     * @throws Exception se ocorrer um erro durante a inserção no banco de dados
     */
    public Telefone insert(Telefone telefone) throws Exception {
    	Connection connection = ConnectionManager.getConnection();
        String query = "INSERT INTO telefone (idCliente, numero) VALUES (?, ?)";
        try {
             PreparedStatement statement = connection.prepareStatement(query);            
            statement.setLong(1, telefone.getIdCliente());
            statement.setString(2, telefone.getNumero());            
            statement.executeUpdate();
        	return telefone;     	
        } catch (SQLException e) {
        	e.printStackTrace();
        	connection.rollback();     
            throw new Exception("Problemas ao inserir os registros de Telefone.");
        }
    }
    
    /**
     * Retorna o último ID inserido na tabela de telefones.
     * 
     * @return o último ID inserido na tabela de telefones
     * @throws Exception se ocorrer um erro durante a consulta ao banco de dados
     */
    public Long getLastId() throws Exception {
    	Connection connection = ConnectionManager.getConnection();
        String query = "SELECT MAX(id) as id FROM telefone";
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

    
    private List<Telefone> createTelefoneList(ResultSet resultSet) throws SQLException {
        List<Telefone> telefones = new ArrayList<>();
    	while(resultSet.next()) {
    		telefones.add(createTelefoneByResultSet(resultSet));
        }
        return telefones;
	}
    
    private Telefone createTelefone(ResultSet resultSet) throws SQLException {
    	Telefone telefone = null;
    	if(resultSet.next()) {
    		telefone = createTelefoneByResultSet(resultSet);
        }
        return telefone;
	}
    
    private Telefone createTelefoneByResultSet(ResultSet resultSet) throws SQLException {
        Telefone telefone = new Telefone();
        telefone.setId(resultSet.getLong("id"));
        telefone.setIdCliente(resultSet.getLong("idCliente"));
        telefone.setNumero(resultSet.getString("numero"));
        return telefone;
	}

}
