/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.bean.CalcularComissao;
import br.com.travelmate.facade.VendasComissaoFacade;
import br.com.travelmate.model.Vendascomissao;
import br.com.travelmate.util.Formatacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class VendasComissaoMB implements Serializable{
    
    private Vendascomissao vendascomissao;
    private List<Vendascomissao> listaVendasComissao;
    private String tituloDialog;
    private Date dataInicial;
    private Date dataFinal;
    private CalcularComissao calcularComissao;

    public VendasComissaoMB() {
    }
    
    @PostConstruct
    public void init(){
        this.vendascomissao = new Vendascomissao();
        String sql = "Select v from Vendascomissao v order by v.vendas.dataVenda";
        gerarListaVendaComissao(sql);
    }
    
    public List<Vendascomissao> getListaVendasComissao() {
        return listaVendasComissao;
    }

    public void setListaVendasComissao(List<Vendascomissao> listaVendasComissao) {
        this.listaVendasComissao = listaVendasComissao;
    }
    
    public Vendascomissao getVendascomissao() {
        return vendascomissao;
    }

    public void setVendascomissao(Vendascomissao vendascomissao) {
        this.vendascomissao = vendascomissao;
    }

    public String getTituloDialog() {
        tituloDialog();
        return tituloDialog;
    }

    public void setTituloDialog(String tituloDialog) {
        this.tituloDialog = tituloDialog;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public CalcularComissao getCalcularComissao() {
        return calcularComissao;
    }

    public void setCalcularComissao(CalcularComissao calcularComissao) {
        this.calcularComissao = calcularComissao;
    }

    
    
    
    public void gerarListaVendaComissao(String sql){
        VendasComissaoFacade vendasComissaoFacade = new VendasComissaoFacade();
        listaVendasComissao = vendasComissaoFacade.listar(sql);
        vendascomissao = listaVendasComissao.get(0);
        if (listaVendasComissao==null){
            listaVendasComissao = new ArrayList<Vendascomissao>();
        }
    }
    public String editar(){
        return "editarComissaovendas";
    }
    public String visualizar(Vendascomissao vendascomissao){
        this.vendascomissao = vendascomissao;
        return null;
    }
    
    public String tituloDialog(){
        tituloDialog = "Venda No. " + vendascomissao.getVendas().getIdvendas();
        return tituloDialog;
    }
    
    public String calculoComissao(){
        calcularComissao = new CalcularComissao(Formatacao.ConvercaoDataSql(dataInicial),
            Formatacao.ConvercaoDataSql(dataFinal));
        return "";
    }
}
