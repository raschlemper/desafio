package com.desafio.projeto.validate;

import com.desafio.projeto.model.Cliente;

/**
 * A classe {@code ClienteValidate} fornece métodos para validar objetos do tipo {@code Cliente}.
 * Esta classe segue o padrão Singleton.
 */
public class ClienteValidate {
	
    private static ClienteValidate instance;
    
    /**
     * Construtor privado da classe {@code ClienteValidate}.
     */
    private ClienteValidate() {
    	super();
    }

    /**
     * Retorna a única instância da classe {@code ClienteValidate}.
     * 
     * @return a instância única da classe {@code ClienteValidate}
     */
    public static ClienteValidate getInstance() {
        if (instance == null) {
            instance = new ClienteValidate();
        }
        return instance;
    }
	
    /**
     * Valida um objeto do tipo {@code Cliente}.
     * 
     * @param cliente o objeto {@code Cliente} a ser validado
     * @throws Exception se ocorrer um erro durante a validação
     */
	public void validar(Cliente cliente) throws Exception { 
	}

}
