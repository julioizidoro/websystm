/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Ocurso;
import br.com.travelmate.model.Valorcoprodutos;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class FornecedorProdutosBean {
    
        private Fornecedorcidade fornecedorCidade;
        private List<Valorcoprodutos> listaObrigaroerios;
        private List<Valorcoprodutos> listaOpcionais;
        private Ocurso ocurso;
        private Float valorMoedaEstrangeira;
        private String svalorMoedaEstrangeira;
        private Float valorMoedaNacional;
        private String svalorMoedaNacional;
        private Float valorDesconto;
        private String svalorDesconto;
        private Cambio cambio;
        

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

    public Ocurso getOcurso() {
        return ocurso;
    }

    public void setOcurso(Ocurso ocurso) {
        this.ocurso = ocurso;
    }

    public String getSvalorMoedaEstrangeira() {
        return svalorMoedaEstrangeira;
    }

    public void setSvalorMoedaEstrangeira(String svalorMoedaEstrangeira) {
        this.svalorMoedaEstrangeira = svalorMoedaEstrangeira;
    }

    public String getSvalorMoedaNacional() {
        return svalorMoedaNacional;
    }

    public void setSvalorMoedaNacional(String svalorMoedaNacional) {
        this.svalorMoedaNacional = svalorMoedaNacional;
    }

    public String getSvalorDesconto() {
        return svalorDesconto;
    }

    public void setSvalorDesconto(String svalorDesconto) {
        this.svalorDesconto = svalorDesconto;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    
    
    
    
}
