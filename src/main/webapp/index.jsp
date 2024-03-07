<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Desafio Java</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Formulário de Cliente</h1>
        <form id="clienteForm">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" class="form-control" id="nome" name="nome" placeholder="Digite seu Nome" required>
            </div>
            <div class="form-group">
                <label for="endereco">Endereço:</label>
                <input type="text" class="form-control" id="endereco" name="endereco" placeholder="Digite seu Endereço" required>
            </div>
            <div class="form-group">
                <label for="bairro">Bairro:</label>
                <input type="text" class="form-control" id="bairro" name="bairro" placeholder="Digite seu Bairro" required>
            </div>
            <div class="form-group">
                <label for="telefone">Telefone 1:</label>
                <input type="tel" class="form-control" id="telefone1" name="telefone1" placeholder="Digite seu Telefone no formato (99) 9999-9999">
            </div>
            <div class="form-group">
                <label for="telefone">Telefone 2:</label>
                <input type="tel" class="form-control" id="telefone2" name="telefone2" placeholder="Digite seu Telefone no formato (99) 9999-9999">
            </div>
            <div class="form-group">
                <label for="telefone">Telefone 3:</label>
                <input type="tel" class="form-control" id="telefone3" name="telefone3" placeholder="Digite seu Telefone no formato (99) 9999-9999">
            </div>
            <button id="saveCliente" class="btn btn-primary">Salvar</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="resources/js/service.js"></script>
    <script src="resources/js/controller.js"></script>
</body>
</html>
