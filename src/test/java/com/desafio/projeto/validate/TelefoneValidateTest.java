package com.desafio.projeto.validate;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.desafio.projeto.model.Telefone;

public class TelefoneValidateTest {	

    private TelefoneValidate telefoneValidate;

    @Before
    public void setUp() {
        telefoneValidate = TelefoneValidate.getInstance();
    }

    @Test
    public void testValidarTelefoneNulo() {
        Telefone telefone = null;

        Exception exception = assertThrows(Exception.class, () -> telefoneValidate.validarNulo(telefone));
        assertTrue(exception.getMessage().contains("O telefone do cliente não pode ser nulo."));
    }

    @Test
    public void testValidarNumeroTelefoneNulo() {
        Telefone telefone = new Telefone();
        telefone.setNumero(null);

        Exception exception = assertThrows(Exception.class, () -> telefoneValidate.validarNulo(telefone));
        assertTrue(exception.getMessage().contains("O telefone do cliente não pode ser nulo."));
    }

    @Test
    public void testValidarFormatoTelefoneCorreto() {
        Telefone telefone = new Telefone();
        telefone.setNumero("(11) 1234-5678");

        assertDoesNotThrow(() -> telefoneValidate.validarFormato(telefone));
    }

    @Test
    public void testValidarFormatoTelefoneIncorreto() {
        Telefone telefone = new Telefone();
        telefone.setNumero("1234-5678");

        Exception exception = assertThrows(Exception.class, () -> telefoneValidate.validarFormato(telefone));
        assertTrue(exception.getMessage().contains("O formato do número do telefone esta errado."));
    }

    @Test
    public void testValidarTelefoneListaVazia() {
        List<Telefone> telefones = new ArrayList<>();

        assertDoesNotThrow(() -> telefoneValidate.validar(telefones));
    }

    @Test
    public void testValidarTelefoneListaCorreta() {
        List<Telefone> telefones = new ArrayList<>();
        Telefone telefone1 = new Telefone("(11) 1234-5678");
        Telefone telefone2 = new Telefone("(22) 5678-1234");
        telefones.add(telefone1);
        telefones.add(telefone2);

        assertDoesNotThrow(() -> telefoneValidate.validar(telefones));
    }
    
}
