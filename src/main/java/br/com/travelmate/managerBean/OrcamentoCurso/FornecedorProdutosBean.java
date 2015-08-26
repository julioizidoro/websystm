/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Valorcoprodutos;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class FornecedorProdutosBean {
    
        private Fornecedorcidade fornecedorCidade;
        private List<Valorcoprodutos> listaObrigaroerios;
        private List<Valorcoprodutos> listaOpcionais;
        private Date dataInicio;
        private Date dataTermino;
        private int numeroSemanas;
        private Float valorMoedaEstrangeira;
        private Float valorMoedaNacional;
        private Float valorDesconto;

    public Fornecedorcidade getFornecedorCidade() {
        return fornecedorCidade;
    }

    public void setFornecedorCidade(Fornecedorcidade fornecedorCidade) {
        this.fornecedorCidade = fornecedorCidade;
    }

    public List<Valorcoprodutos> getListaObrigaroerios() {
        return listaObrigaroerios;
    }

    public void setListaObrigaroerios(List<Valorcoprodutos> listaObrigaroerios) {
        this.listaObrigaroerios = listaObrigaroerios;
    }

    public List<Valorcoprodutos> getListaOpcionais() {
        return listaOpcionais;
    }

    public void setListaOpcionais(List<Valorcoprodutos> listaOpcionais) {
        this.listaOpcionais = listaOpcionais;
    }

    

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public int getNumeroSemanas() {
        return numeroSemanas;
    }

    public void setNumeroSemanas(int numeroSemanas) {
        this.numeroSemanas = numeroSemanas;
    }

    public Float getValorMoedaEstrangeira() {
        return valorMoedaEstrangeira;
    }

    public void setValorMoedaEstrangeira(Float valorMoedaEstrangeira) {
        this.valorMoedaEstrangeira = valorMoedaEstrangeira;
    }

    public Float getValorMoedaNacional() {
        return valorMoedaNacional;
    }

    public void setValorMoedaNacional(Float valorMoedaNacional) {
        this.valorMoedaNacional = valorMoedaNacional;
    }

    public Float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
        
        
    
}
