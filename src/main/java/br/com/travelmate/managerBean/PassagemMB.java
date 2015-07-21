/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.model.Cliente;
import br.com.travelmate.model.Formapagamento;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Orcamento;
import br.com.travelmate.model.Parcelamentopagamento;
import br.com.travelmate.model.Passagemaerea;
import br.com.travelmate.model.Produtosorcamento;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class PassagemMB implements Serializable{

    @Inject 
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Passagemaerea> listaPassagem;
    private Passagemaerea passagem;
    private Cliente cliente;
    private List<Produtosorcamento> listaProdutoOrcamento;
    private Fornecedorcidade fornecedorCidade;
    private Orcamento orcamento;
    private Formapagamento formaPagamento;
    private List<Parcelamentopagamento> listaParcelamento;

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public List<Passagemaerea> getListaPassagem() {
        return listaPassagem;
    }

    public void setListaPassagem(List<Passagemaerea> listaPassagem) {
        this.listaPassagem = listaPassagem;
    }

    public Passagemaerea getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagemaerea passagem) {
        this.passagem = passagem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produtosorcamento> getListaProdutoOrcamento() {
        return listaProdutoOrcamento;
    }

    public void setListaProdutoOrcamento(List<Produtosorcamento> listaProdutoOrcamento) {
        this.listaProdutoOrcamento = listaProdutoOrcamento;
    }

    public Fornecedorcidade getFornecedorCidade() {
        return fornecedorCidade;
    }

    public void setFornecedorCidade(Fornecedorcidade fornecedorCidade) {
        this.fornecedorCidade = fornecedorCidade;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Formapagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Formapagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<Parcelamentopagamento> getListaParcelamento() {
        return listaParcelamento;
    }

    public void setListaParcelamento(List<Parcelamentopagamento> listaParcelamento) {
        this.listaParcelamento = listaParcelamento;
    }
    
    
    
    
}
