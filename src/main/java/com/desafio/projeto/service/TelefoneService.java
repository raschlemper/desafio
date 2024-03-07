package com.desafio.projeto.service;

import java.util.List;

import com.desafio.projeto.dao.TelefoneDAO;
import com.desafio.projeto.model.Telefone;

/**
 * A classe {@code TelefoneService} fornece métodos para realizar operações relacionadas 
 * aos telefones, como recuperar todos os telefones, encontrar um telefone pelo ID, 
 * pelo número e inserir um novo telefone.
 */
public class TelefoneService {
	
	private TelefoneDAO telefoneDAO;
	
	/**
     * Construtor padrão da classe {@code TelefoneService}.
     */
	public TelefoneService() {
		this.telefoneDAO = new TelefoneDAO();
	}

	/**
     * Retorna uma lista de todos os telefones.
     * 
     * @return uma lista de objetos {@code Telefone}
     * @throws Exception se ocorrer um erro ao recuperar os telefones
     */
	public List<Telefone> findAll() throws Exception {
        return telefoneDAO.findAll();
	}
	
	/**
     * Retorna o telefone com o ID especificado.
     * 
     * @param id o ID do telefone a ser encontrado
     * @return o objeto {@code Telefone} encontrado
     * @throws Exception se ocorrer um erro ao encontrar o telefone
     */
    public Telefone findById(Long id) throws Exception {
        return telefoneDAO.findById(id);
    }

    /**
     * Retorna o telefone com o número especificado.
     * 
     * @param numero o número do telefone a ser encontrado
     * @return o objeto {@code Telefone} encontrado
     * @throws Exception se ocorrer um erro ao encontrar o telefone
     */
	public Telefone findByNome(String numero) throws Exception {
        return telefoneDAO.findByNumero(numero);
	}

	/**
     * Insere um novo telefone no banco de dados.
     * 
     * @param telefone o objeto {@code Telefone} a ser inserido
     * @return o objeto {@code Telefone} inserido
     * @throws Exception se ocorrer um erro ao inserir o telefone
     */
    public Telefone insert(Telefone telefone) throws Exception {
        return telefoneDAO.insert(telefone);
    }
    
}
