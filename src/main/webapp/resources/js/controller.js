$(document).ready(function() {
	
    $('#saveCliente').on('click', async (event) => {
		event.preventDefault(); 
		let validacao = await validarClienteForm();				
        if(validacao) {
	     	inserirClienteForm();			
		}
    });

    // Função para inserir o formulário de cliente
    let inserirClienteForm = () => {	
        let clienteData = createData();
        insertCliente(clienteData)
			.done(function(data) {
			  	if(!data) return;
			  	$('#clienteForm')[0].reset();
			  	alert(createMsg(data));
			});        
    };
    
    let createMsg = (data) => {
		let msg =  "Cliente cadastrado com sucesso! \n";
			msg += "Nome: " + data.nome + "\n";
		    msg += "Endereço: " + data.endereco + "\n";
		    msg += "Bairro: " + data.bairro + "\n";
	    for(let i=0; i<data.telefones.length; i++) {
			let telefone = data.telefones[i];
			msg += "Telefone " + i+1 + ": " + telefone.numero + "\n";
		}
		return msg;   
	}
    
    let createData = () => {
		var nome = $('#nome').val().trim();
        var endereco = $('#endereco').val().trim();
        var bairro = $('#bairro').val().trim();
        var telefones = getTelefones();		        
        return {
            nome: nome,
            endereco: endereco,
            bairro: bairro,
            telefones: telefones
        };		
	}
	
	let getTelefones = () => {
        var telefones = [];
        for(let i=1; i<=3; i++) {
			let telefone = $('#telefone' + i).val().trim();
			if(telefone) { telefones.push({"numero": telefone}); }
		}
		return telefones;
	}

    // Função para validar o formulário de cliente
    let validarClienteForm = async () => {	
		let validacaoCliente = await validarClienteNomeForm();	
		let valicacaoTelefone = await validarClienteTelefoneForm();
        if(!validacaoCliente || !valicacaoTelefone) return false;	
        return true;
    }

    // Validação do Nome
    let validarClienteNomeForm = async () => {		
        var nome = $('#nome').val().trim();
        // Verificar se o nome é maior que 10 caracteres
        if (nome.length < 10) {
            alert("O nome do cliente deve ter no mínimo 10 caracteres.");
            return false;
        }
        // Verificar se o nome já existe
        if (await nomeExistente(nome)) {
            alert("Já existe um cliente com esse nome.");
            return false;
        }
        return true;
    }

    // Validação de Telefone
    let validarClienteTelefoneForm = async () => {  
        let validacao1 = await validarClienteTelefone(1);
        let validacao2 = await validarClienteTelefone(2);
        let validacao3 = await validarClienteTelefone(3);
        if(!validacao1 || !validacao2 || !validacao3) return false;	
        return true;
    }
    
    let validarClienteTelefone = async (index) => {      
        var telefone = $('#telefone'+index).val().trim();
        if (telefone.length > 0 && await telefoneExistente(telefone)) {
            alert("Esse número de telefone " + index + " já está vinculado a outro cliente.");
            return false;
        }
        return true;
    }

    let nomeExistente = async (nome) => {
		return await findClienteByNome(nome)
			.done(function(data) {
			  	if(data) return true;
			  	return false;
			});
    }

    let telefoneExistente = async (telefone) => {
		return await findClienteByTelefone(telefone)
			.done(function(data) {
			  	if(data) return true;
			  	return false;
			});
    }
    
});