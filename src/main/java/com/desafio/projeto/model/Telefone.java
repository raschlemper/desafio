package com.desafio.projeto.model;

/**
 * A classe {@code Telefone} representa um número de telefone associado a um cliente, 
 * com atributos como ID do telefone, ID do cliente e número de telefone.
 */
public class Telefone {
	
    private Long id;    
    private Long idCliente;    
    private String numero;

    /**
     * Construtor padrão da classe {@code Telefone}.
     */
	public Telefone() {
		super();
	}

	/**
     * Construtor da classe {@code Telefone} que inicializa o número de telefone e o 
     * ID do cliente associado.
     * 
     * @param numero    o número de telefone
     * @param idCliente o ID do cliente associado
     */
	public Telefone(String numero, Long idCliente) {
		this.numero = numero;
		this.idCliente = idCliente;
	}

	/**
     * Construtor da classe {@code Telefone} que inicializa apenas o número de telefone.
     * 
     * @param numero o número de telefone
     */
	public Telefone(String numero) {
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

}
