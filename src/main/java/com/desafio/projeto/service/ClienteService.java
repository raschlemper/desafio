package com.desafio.projeto.service;

import java.util.ArrayList;
import java.util.List;

import com.desafio.projeto.dao.ClienteDAO;
import com.desafio.projeto.model.Cliente;
import com.desafio.projeto.model.Telefone;

/**
 * A classe {@code ClienteService} fornece métodos para realizar operações relacionadas 
 * aos clientes, como recuperar todos os clientes, encontrar um cliente pelo ID, pelo nome, 
 * inserir um novo cliente e inserir telefones associados a um cliente.
 */
public class ClienteService {
	
	private ClienteDAO clienteDAO;
	
	/**
     * Construtor padrão da classe {@code ClienteService}.
     */
	public ClienteService() {
		this.clienteDAO = new ClienteDAO();
	}

	/**
     * Retorna uma lista de todos os clientes.
     * 
     * @return uma lista de objetos {@code Cliente}
     * @throws Exception se ocorrer um erro ao recuperar os clientes
     */
	public List<Cliente> findAll() throws Exception {
        return clienteDAO.findAll();
	}
	
	/**
     * Retorna o cliente com o ID especificado.
     * 
     * @param id o ID do cliente a ser encontrado
     * @return o objeto {@code Cliente} encontrado
     * @throws Exception se ocorrer um erro ao encontrar o cliente
     */
    public Cliente findById(Long id) throws Exception {
        return clienteDAO.findById(id);
    }

    /**
     * Retorna o cliente com o nome especificado.
     * 
     * @param nome o nome do cliente a ser encontrado
     * @return o objeto {@code Cliente} encontrado
     * @throws Exception se ocorrer um erro ao encontrar o cliente
     */
	public Cliente findByNome(String nome) throws Exception {
        return clienteDAO.findByNome(nome);
	}

	 /**
     * Insere um novo cliente no banco de dados e insere os telefones associados a esse cliente.
     * 
     * @param cliente o objeto {@code Cliente} a ser inserido
     * @return o objeto {@code Cliente} inserido com o ID atualizado e os telefones associados
     * @throws Exception se ocorrer um erro ao inserir o cliente ou os telefones
     */
    public Cliente insert(Cliente cliente) throws Exception {
    	List<Telefone> telefones = cliente.getTelefones();
    	cliente = clienteDAO.insert(cliente);
    	cliente.setId(clienteDAO.getLastId());
    	telefones = insertTelefones(cliente, telefones);
    	cliente.setTelefones(telefones);
    	return cliente;
    }
    
    /**
     * Insere telefones associados a um cliente no banco de dados.
     * 
     * @param clienteRs o objeto {@code Cliente} com o ID atualizado
     * @param telefones a lista de telefones associados ao cliente
     * @return uma lista de objetos {@code Telefone} inseridos com os IDs atualizados
     * @throws Exception se ocorrer um erro ao inserir os telefones
     */
    private List<Telefone> insertTelefones(Cliente clienteRs, List<Telefone> telefones) throws Exception {
    	List<Telefone> lista = new ArrayList<Telefone>();
    	TelefoneService telefoneService = new TelefoneService();
		for (Telefone telefone : telefones) {
			telefone.setIdCliente(clienteRs.getId());
			telefone = telefoneService.insert(telefone);
			lista.add(telefone);
		}	
		return lista;
	}
    
}
