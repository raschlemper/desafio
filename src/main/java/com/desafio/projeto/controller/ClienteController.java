package com.desafio.projeto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.desafio.projeto.model.Cliente;
import com.desafio.projeto.model.Telefone;
import com.desafio.projeto.service.ClienteService;
import com.desafio.projeto.validate.TelefoneValidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A classe {@code ClienteController} é uma extensão da classe {@code ActionServlet} 
 * que controla as solicitações relacionadas aos clientes. 
 */
@WebServlet("/cliente/*")
public class ClienteController extends ActionServlet {
	
    private static final long serialVersionUID = 1L;

    /**
     * Manipula solicitações HTTP GET relacionadas aos clientes. Executa operações 
     * com base na ação fornecida.
     * 
     * @param request  o objeto {@code HttpServletRequest}
     * @param response o objeto {@code HttpServletResponse}
     * @param action   a ação extraída do caminho da solicitação
     * @param data     o {@code JSONObject} analisado do corpo da solicitação
     * @throws ServletException se o servlet encontrar dificuldades
     * @throws IOException      se ocorrer uma exceção de E/S
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response, String action, JSONObject data) 
			throws ServletException, IOException {
		try {
			ClienteService clienteService = new ClienteService();
			switch (action) {
	        	case "":
		        	List<Cliente> clientes = clienteService.findAll();
					responseJson(response, clientes);
					break;
		        case "nome":
		        	Cliente cliente = clienteService.findByNome(request.getParameter("nome"));
					responseJson(response, cliente);
		            break;
		        default:
		            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação desconhecida: " + action);
		    }		
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}	
	}

	/**
     * Manipula solicitações HTTP POST relacionadas aos clientes. Executa operações 
     * com base na ação fornecida.
     * 
     * @param request  o objeto {@code HttpServletRequest}
     * @param response o objeto {@code HttpServletResponse}
     * @param action   a ação extraída do caminho da solicitação
     * @param data     o {@code JSONObject} analisado do corpo da solicitação
     * @throws ServletException se o servlet encontrar dificuldades
     * @throws IOException      se ocorrer uma exceção de E/S
     */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, String action, JSONObject data) 
    		throws ServletException, IOException {
		try {	 
			Cliente cliente = getCliente(data);
			TelefoneValidate.getInstance().validar(cliente.getTelefones());
			ClienteService clienteService = new ClienteService();
			cliente = clienteService.insert(cliente);
			responseJson(response, cliente);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
    }
	
	/**
     * Obtém um objeto {@code Cliente} com base nos dados fornecidos em um 
     * {@code JSONObject}.
     * 
     * @param cliente o {@code JSONObject} contendo os dados do cliente
     * @return o objeto {@code Cliente} criado com base nos dados fornecidos
     */
	private Cliente getCliente(JSONObject cliente) {		
	    String nome = cliente.getString("nome");
	    String endereco = cliente.getString("endereco");
	    String bairro = cliente.getString("bairro");
	    List<Telefone> telefones = getTelefones(cliente);
	    return new Cliente(nome, endereco, bairro, telefones);
	}
	
	/**
     * Obtém uma lista de objetos {@code Telefone} com base nos dados fornecidos em um 
     * {@code JSONObject}.
     * 
     * @param cliente o {@code JSONObject} contendo os dados do cliente, incluindo os telefones
     * @return uma lista de objetos {@code Telefone} criados com base nos dados fornecidos
     */
	private List<Telefone> getTelefones(JSONObject cliente) {
	    JSONArray telefonesArray = cliente.getJSONArray("telefones");	    
	    List<Telefone> telefones = new ArrayList<>();
	    for (int i = 0; i < telefonesArray.length(); i++) {
	    	JSONObject telefone = telefonesArray.getJSONObject(i);
	        telefones.add(new Telefone(telefone.getString("numero")));
	    }
        return telefones;
	}

}
