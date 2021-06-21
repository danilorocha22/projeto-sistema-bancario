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
        System.out.println(obtido);
        assertEquals(esperado, obtido, 0.001);
    }

    @Test
    void depositoDinheiroTipoMovimentacaoCredito() {
        final Conta instance = new Conta();
        instance.depositoDinheiro(400);
        final List <Movimentacao> movimentacaoList = instance.getMovimentacoes();
        final Movimentacao m = movimentacaoList.get(0);
        final char esperado = 'C';
        final char obtido = m.getTipo();
        assertEquals(esperado, obtido);
    }

    @Test
    void depositoDinheiroMovimentacaoConfirmada() {
        final Conta instance = new Conta();
        instance.depositoDinheiro(400);
        final List<Movimentacao> movimentacaoList = instance.getMovimentacoes();
        final Movimentacao m = movimentacaoList.get(0);
        final boolean esperado = true;
        final boolean obtido = m.isConfirmada();
        assertEquals(esperado, obtido);
    }

    @Test
    void depositoDinheiroValorAtribuidoMovimentacao() {
        final double deposito = 400;
        final Conta instance = new Conta();
        instance.depositoDinheiro(deposito);
        final List<Movimentacao> movimentacaoList = instance.getMovimentacoes();
        final Movimentacao m = movimentacaoList.get(0);
        final double esperado = deposito;
        final double obtido = m.getValor();
        assertEquals(esperado, obtido);
    }

    @Test
    void depositoDinheiroMovimentacaoAdicionada() {
        final double deposito = 200;
        final Conta instance = new Conta();
        instance.depositoDinheiro(deposito);
        instance.depositoDinheiro(deposito);
        final List<Movimentacao> movimentacaoList = instance.getMovimentacoes();
        final int obtido = movimentacaoList.size();
        final int esperado = 2;
        assertEquals(esperado, obtido);
    }

    @Test
    void depositoDinheiroValorNegativo(){
        final Conta instance = new Conta();
        assertThrows(IllegalArgumentException.class,()-> instance.depositoDinheiro(-400));
    }

    @Test
    void saqueDinheiro() {
        final double limite = 500.7, deposito = 500.8, saque = 300.07, esperado = 701.43;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        instance.saque(saque);
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido, 0.001);
    }

    @Test
    void saqueDinheiroTipoMovimentacaoDebito() {
        final Conta instance = new Conta();
        instance.depositoDinheiro(400);
        instance.saque(100);
        final List <Movimentacao> movimentacaoList = instance.getMovimentacoes();
        final Movimentacao m = movimentacaoList.get(1);
        final char esperado = 'D';
        final char obtido = m.getTipo();
        assertEquals(esperado, obtido);
    }

    @Test
    void saqueDinheiroMovimentacaoConfirmada() {
        final Conta instance = new Conta();
        instance.depositoDinheiro(400);
        instance.saque(100);
        final List<Movimentacao> movimentacaoList = instance.getMovimentacoes();
        final Movimentacao m = movimentacaoList.get(1);
        final boolean esperado = true;
        final boolean obtido = m.isConfirmada();
        assertEquals(esperado, obtido);
    }

    @Test
    void saqueDinheiroValorAtribuidoMovimentacao() {
        final double deposito = 400, saque = 100;
        final Conta instance = new Conta();
        instance.depositoDinheiro(deposito);
        instance.saque(saque);
        final List<Movimentacao> movimentacaoList = instance.getMovimentacoes();
        final Movimentacao m = movimentacaoList.get(1);
        final double esperado = saque;
        final double obtido = m.getValor();
        assertEquals(esperado, obtido);
    }

    @Test
    void saqueDinheiroMovimentacaoAdicionada() {
        final double deposito = 200, saque = 300;
        final Conta instance = new Conta();
        instance.depositoDinheiro(deposito);
        instance.depositoDinheiro(deposito);
        instance.saque(300);
        final List<Movimentacao> movimentacaoList = instance.getMovimentacoes();
        final int obtido = movimentacaoList.size();
        final int esperado = 3;
        assertEquals(esperado, obtido);
    }

    @Test
    void saqueDinheiroValorNegativo(){
        final Conta instance = new Conta();
        assertThrows(IllegalArgumentException.class,()-> instance.saque(-400));
    }

}
