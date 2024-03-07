package com.desafio.projeto.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A classe {@code ActionServlet} é uma extensão abstrata de {@code HttpServlet} 
 * <p>
 * As subclasses devem implementar os métodos abstratos {@code doGet} e {@code doPost} 
 * para definir o comportamento de manipulação de solicitações GET e POST, respectivamente.
 */
public abstract class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
     * Manipula solicitações HTTP GET invocando o método {@code doGet} da subclasse 
     * com os parâmetros fornecidos.
     * 
     * @param request  o objeto {@code HttpServletRequest}
     * @param response o objeto {@code HttpServletResponse}
     * @throws ServletException se o servlet encontrar dificuldades
     * @throws IOException      se ocorrer uma exceção de E/S
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		String action = getAction(request);		
		JSONObject jsonObject = getJSONObject(request);	
		doGet(request, response, action, jsonObject);
	}

	/**
     * Manipula solicitações HTTP POST invocando o método {@code doPost} da subclasse 
     * com os parâmetros fornecidos.
     * 
     * @param request  o objeto {@code HttpServletRequest}
     * @param response o objeto {@code HttpServletResponse}
     * @throws ServletException se o servlet encontrar dificuldades
     * @throws IOException      se ocorrer uma exceção de E/S
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		String action = getAction(request);		
		JSONObject jsonObject = getJSONObject(request);	
		doPost(request, response, action, jsonObject);
	}

	/**
     * Recupera a ação do caminho da solicitação.
     * 
     * @param request o objeto {@code HttpServletRequest}
     * @return a ação extraída do caminho da solicitação
     */
	private String getAction(HttpServletRequest request) {
    	String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if(pathParts.length == 0) return "";
            return pathParts[1];
        }
        return "";
    }

	/**
     * Analisa dados JSON do corpo da solicitação em um {@code JSONObject}.
     * 
     * @param request o objeto {@code HttpServletRequest}
     * @return um {@code JSONObject} analisado do corpo da solicitação
     * @throws IOException se ocorrer uma exceção de E/S
     */
	private JSONObject getJSONObject(HttpServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
	    StringBuilder stringBuilder = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        stringBuilder.append(line);
	    }
	    reader.close();
	    String jsonString = stringBuilder.toString();
	    if(jsonString == null || jsonString.equals("")) return new JSONObject();
	    JSONObject jsonObject = new JSONObject(jsonString);
		return jsonObject;
	}
	
	/**
     * Envia uma resposta JSON usando o objeto fornecido.
     * 
     * @param response o objeto {@code HttpServletResponse}
     * @param objeto   o objeto a ser serializado em JSON
     * @throws IOException se ocorrer uma exceção de E/S
     */
	protected void responseJson(HttpServletResponse response, Object objeto) throws IOException {	
		response.setContentType("application/json");
		Gson gson = new Gson();
        String json = gson.toJson(objeto);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();		
	}
	
	/**
     * Método abstrato a ser implementado por subclasses para manipular solicitações 
     * HTTP GET.
     * 
     * @param request  o objeto {@code HttpServletRequest}
     * @param response o objeto {@code HttpServletResponse}
     * @param action   a ação extraída do caminho da solicitação
     * @param data     o {@code JSONObject} analisado do corpo da solicitação
     * @throws ServletException se o servlet encontrar dificuldades
     * @throws IOException      se ocorrer uma exceção de E/S
     */
	protected abstract void doGet(HttpServletRequest request, HttpServletResponse response, String action, 
			JSONObject data) throws ServletException, IOException;
	
	/**
     * Método abstrato a ser implementado por subclasses para manipular solicitações 
     * HTTP POST.
     * 
     * @param request  o objeto {@code HttpServletRequest}
     * @param response o objeto {@code HttpServletResponse}
     * @param action   a ação extraída do caminho da solicitação
     * @param data     o {@code JSONObject} analisado do corpo da solicitação
     * @throws ServletException se o servlet encontrar dificuldades
     * @throws IOException      se ocorrer uma exceção de E/S
     */
	protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, String action, 
			JSONObject data) throws ServletException, IOException;

}
