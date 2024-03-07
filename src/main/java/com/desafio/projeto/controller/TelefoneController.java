package com.desafio.projeto.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.desafio.projeto.dao.TelefoneDAO;
import com.desafio.projeto.model.Telefone;
import com.desafio.projeto.service.TelefoneService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A classe {@code TelefoneController} é uma extensão da classe {@code ActionServlet}  
 * que controla as solicitações relacionadas aos telefones dos clientes. 
 */
@WebServlet("/telefone/*")
public class TelefoneController extends ActionServlet {
	
    private static final long serialVersionUID = 1L;

    /**
     * Manipula solicitações HTTP GET relacionadas aos telefones dos clientes. Executa 
     * operações com base na ação fornecida.
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
			TelefoneService telefoneService = new TelefoneService();
			switch (action) {
	        	case "":
		        	List<Telefone> telefones = telefoneService.findAll();
					responseJson(response, telefones);
					break;
		        case "numero":
					Telefone telefone = telefoneService.findByNome(request.getParameter("numero"));
					responseJson(response, telefone);
		            break;
		        default:
		            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação desconhecida: " + action);
		    }		
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}	
	}

	/**
     * Manipula solicitações HTTP POST relacionadas aos telefones dos clientes. Executa 
     * operações com base na ação fornecida.
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
			Telefone telefone = getTelefone(data);
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefone = telefoneDAO.insert(telefone);
			responseJson(response, telefone);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
    }
	
	/**
     * Obtém um objeto {@code Telefone} com base nos dados fornecidos em um 
     * {@code JSONObject}.
     * 
     * @param jsonObject o {@code JSONObject} contendo os dados do telefone
     * @return o objeto {@code Telefone} criado com base nos dados fornecidos
     */
	private Telefone getTelefone(JSONObject jsonObject) {		
	    String numero = jsonObject.getString("numero");	
	    Long idCliente = jsonObject.getLong("idCliente");
	    return new Telefone(numero, idCliente);
	}
	
}
