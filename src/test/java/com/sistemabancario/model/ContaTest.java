package com.sistemabancario.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("all")
public class ContaTest {
    
    @Test
    void setNumeroValido() {
        final Conta instance = new Conta();
        final String esperado = "12345-6";
        instance.setNumero(esperado);
        final String obtido = instance.getNumero();
        assertEquals(esperado, obtido);
    }

    @Test
    void setNumeroInvalidoNaoArmazena() {
        final Conta instance = new Conta();
        final String invalido = "123";
        assertThrows(IllegalArgumentException.class,
                () -> instance.setNumero(invalido));
        final String obtido = instance.getNumero();
        assertNotEquals(invalido, obtido);
    }

    @Test
    void setNumeroNulo() {
        final Conta instance = new Conta();
        final String invalido = null;
        assertThrows(NullPointerException.class,
                () -> instance.setNumero(invalido));
        //Confere se número da conta é nulo
        final String obtido = instance.getNumero();
        assertEquals(invalido, obtido);
    }

    @Test
    void instanciaPadraoPouponca() {
        final Conta instance = new Conta();
        assertFalse(instance.isPoupanca());
    }

    @Test
    void setLimiteContaEspecial() {
        final Conta instance = new Conta();
        instance.setEspecial(true);
        final double esperado = 1000;
        instance.setLimite(esperado);
        final double obtido = instance.getLimite();
        assertEquals(esperado, obtido);
    }

    @Test
    void setLimiteContaNaoEspecial() {
        final Conta instance = new Conta();
        final double limite = 1000;
        assertThrows(IllegalStateException.class, () -> instance.setLimite(limite));
        //Confere se o limite foi alterado
        final double obtido = instance.getLimite();
        assertNotEquals(limite, obtido);
    }

    @Test
    void historicoNotNull() {
        final Conta instance = new Conta();
        assertNotNull(instance.getMovimentacoes());
    }

    @Test
    void getSaldoTotal() {
        final double limite = 500;
        final double esperado = limite;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido);
    }

    @Test
    void depositoDinheiro() {
        final double limite = 500.6, deposito = 500.8, esperado = 1001.4;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido, 0.001);
    }

    @Test
    void depositoDinheiroTipoMovimentacao() {
        final double limite = 500, deposito = 500;
        final char esperado = 'C';
        final Conta instance = new Conta();
        final Movimentacao movimentacao = new Movimentacao(instance);
        movimentacao.setTipo('C');
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        final char obtido = movimentacao.getTipo();
        assertEquals(esperado, obtido);
    }

    @Test
    void depositoDinheiroMovimentacaoConfirmada() {
        final double limite = 500, deposito = 500;
        final boolean esperado = true;
        final Conta instance = new Conta();
        final Movimentacao movimentacao = new Movimentacao(instance);
        movimentacao.setTipo('C');
        movimentacao.setConfirmada(true);
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        final boolean obtido = movimentacao.isConfirmada();
        assertEquals(esperado, obtido);
    }

    @Test
    void depositoDinheiroValorAtribuido() {
        final double limite = 500, deposito = 500;
        final double esperado = limite + deposito;
        final Conta instance = new Conta();
        final Movimentacao movimentacao = new Movimentacao(instance);
        movimentacao.setTipo('C');
        movimentacao.setConfirmada(true);
        movimentacao.setValor(esperado);
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        final double obtido = movimentacao.getValor();
        assertEquals(esperado, obtido);
    }

}
