let findClienteByNome = (nome) => {
	return $.ajax({
        "url": "cliente/nome",
		"method": "GET",
	 	"timeout": 0,
	  	"headers": {
	    	"Content-Type": "application/json"
	  	},
	  	"data": $.param({ nome: nome }),
        success: function(result) {            
			return result;
        },
        error: function() {
            alert("Erro ao verificar o nome do cliente.");
        }
    });
}

let findClienteByTelefone = (numero) => {
	return $.ajax({
        "url": "telefone/numero",
		"method": "GET",
	 	"timeout": 0,
	  	"headers": {
	    	"Content-Type": "application/json"
	  	},
	  	"data": $.param({ numero: numero }),
        success: function(result) {
			return result;
        },
        error: function() {
            alert("Erro ao verificar o telefone do cliente.");
        }
    });
}

let insertCliente = (cliente) => {
	return $.ajax({
        type: 'POST',
        url: 'cliente/',
        contentType: 'application/json',
        data: JSON.stringify(cliente),
        success: function(result) {
            return result;
        },
        error: function() {
            alert("Erro ao cadastrar o cliente.");
        }
    });
}
