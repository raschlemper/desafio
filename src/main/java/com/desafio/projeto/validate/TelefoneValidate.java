package com.desafio.projeto.validate;

import java.util.List;

import com.desafio.projeto.model.Telefone;

/**
 * A classe {@code TelefoneValidate} fornece métodos para validar objetos do tipo {@code Telefone}.
 * Esta classe segue o padrão Singleton.
 */
public class TelefoneValidate {
	
    private static TelefoneValidate instance;
    
    /**
     * Construtor padrão da classe {@code TelefoneValidate}.
     */
    public TelefoneValidate() {
    	super();
    }

    /**
     * Retorna a única instância da classe {@code TelefoneValidate}.
     * 
     * @return a instância única da classe {@code TelefoneValidate}
     */
    public static TelefoneValidate getInstance() {
        if (instance == null) {
            instance = new TelefoneValidate();
        }
        return instance;
    }
    
    /**
     * Valida uma lista de objetos do tipo {@code Telefone}.
     * 
     * @param telefones a lista de objetos {@code Telefone} a ser validada
     * @throws Exception se ocorrer um erro durante a validação
     */
	public void validar(List<Telefone> telefones) throws Exception {     
		for (Telefone telefone : telefones) {      
			validarNulo(telefone);
			validarFormato(telefone);
		}
	}
    
    /**
     * Valida se o número de telefone é nulo.
     * 
     * @param telefone o objeto {@code Telefone} a ser validado
     * @return {@code true} se o número de telefone não for nulo, {@code false} caso contrário
     * @throws Exception se o número de telefone for nulo
     */
	public boolean validarNulo(Telefone telefone) throws Exception {  
		if (telefone == null || telefone.getNumero() == null) {
			throw new Exception("O telefone do cliente não pode ser nulo.");
		}
		return true;
	}
    
    /**
     * Valida o formato do número de telefone.
     * 
     * @param telefone o objeto {@code Telefone} a ser validado
     * @return {@code true} se o formato do número de telefone for válido, {@code false} caso contrário
     * @throws Exception se o formato do número de telefone for inválido
     */
	public boolean validarFormato(Telefone telefone) throws Exception { 
        // formato "(XX) XXXX-XXXX"
        String regex = "\\(\\d{2}\\)\\s\\d{4}-\\d{4}";
        if(!telefone.getNumero().matches(regex)) {
            throw new Exception("O formato do número do telefone esta errado.");
        }
		return true;
    }

}
