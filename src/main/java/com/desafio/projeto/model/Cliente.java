package com.desafio.projeto.model;

import java.util.List;

/**
 * A classe {@code Cliente} representa um cliente com atributos como ID, nome, 
 * endereço, bairro e uma lista de telefones.
 */
public class Cliente {
	
    private Long id;
    private String nome;
    private String endereco;
    private String bairro; 
    private List<Telefone> telefones;
    
    /**
     * Construtor padrão da classe {@code Cliente}.
     */
    public Cliente() {
		super();
	}

    /**
     * Construtor da classe {@code Cliente} que inicializa o nome, endereço e bairro.
     * 
     * @param nome     o nome do cliente
     * @param endereco o endereço do cliente
     * @param bairro   o bairro do cliente
     */
	public Cliente(String nome, String endereco, String bairro) {
		this.nome = nome;
		this.endereco = endereco;
		this.bairro = bairro;
	}

	/**
     * Construtor da classe {@code Cliente} que inicializa o nome, endereço, bairro e 
     * a lista de telefones.
     * 
     * @param nome      o nome do cliente
     * @param endereco  o endereço do cliente
     * @param bairro    o bairro do cliente
     * @param telefones a lista de telefones do cliente
     */
	public Cliente(String nome, String endereco, String bairro, List<Telefone> telefones) {
		this.nome = nome;
		this.endereco = endereco;
		this.bairro = bairro;
		this.telefones = telefones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

}
