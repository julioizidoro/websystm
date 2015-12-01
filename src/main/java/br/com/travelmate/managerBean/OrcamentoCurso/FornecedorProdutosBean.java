/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Fornecedorcidadeidioma;
import br.com.travelmate.model.Ocurso;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class FornecedorProdutosBean {
    
        private Fornecedorcidadeidioma fornecedorcidadeidioma;
        private List<ProdutosOrcamentoBean> listaObrigaroerios;
        private List<ProdutosOrcamentoBean> listaOpcionais;
        private List<ProdutosOrcamentoBean> listaAcomodacoes;
        private List<ProdutosOrcamentoBean> listaTransfer;
        private Ocurso ocurso;
        private Float valorMoedaEstrangeira;
        private String svalorMoedaEstrangeira;
        private Float valorMoedaNacional;
        private String svalorMoedaNacional;
        private Float valorDesconto;
        private String svalorDesconto;
        private Cambio cambio;

    public Fornecedorcidadeidioma getFornecedorcidadeidioma() {
        return fornecedorcidadeidioma;
    }

    public void setFornecedorcidadeidioma(Fornecedorcidadeidioma fornecedorcidadeidioma) {
        this.fornecedorcidadeidioma = fornecedorcidadeidioma;
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

    public List<ProdutosOrcamentoBean> getListaObrigaroerios() {
        return listaObrigaroerios;
    }

    public void setListaObrigaroerios(List<ProdutosOrcamentoBean> listaObrigaroerios) {
        this.listaObrigaroerios = listaObrigaroerios;
    }

    public List<ProdutosOrcamentoBean> getListaOpcionais() {
        return listaOpcionais;
    }

    public void setListaOpcionais(List<ProdutosOrcamentoBean> listaOpcionais) {
        this.listaOpcionais = listaOpcionais;
    }

    public List<ProdutosOrcamentoBean> getListaAcomodacoes() {
        return listaAcomodacoes;
    }

    public void setListaAcomodacoes(List<ProdutosOrcamentoBean> listaAcomodacoes) {
        this.listaAcomodacoes = listaAcomodacoes;
    }

    public List<ProdutosOrcamentoBean> getListaTransfer() {
        return listaTransfer;
    }

    public void setListaTransfer(List<ProdutosOrcamentoBean> listaTransfer) {
        this.listaTransfer = listaTransfer;
    }

    
    
    
    
}
