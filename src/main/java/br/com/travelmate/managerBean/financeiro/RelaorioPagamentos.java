/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.financeiro;

import br.com.travelmate.facade.UnidadeNegocioFacade;
import br.com.travelmate.model.Unidadenegocio;
import br.com.travelmate.util.Formatacao;
import br.com.travelmate.util.GerarRelatorio;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class RelaorioPagamentos implements Serializable{
    
    private List<Unidadenegocio> listaUnidadeNegocio;
    private Date dataInicio;
    private Date dataTermino;
    private Unidadenegocio unidadenegocio;
    private String competencia;
    
    
    @PostConstruct()
    public void init(){
        gerarListaUnidadeNegocio();
    }

    public List<Unidadenegocio> getListaUnidadeNegocio() {
        return listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<Unidadenegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
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

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }
    
    public void gerarListaUnidadeNegocio(){
        UnidadeNegocioFacade unidadeNegocioFacade = new UnidadeNegocioFacade();
        listaUnidadeNegocio = unidadeNegocioFacade.listar();
        if (listaUnidadeNegocio==null){
            listaUnidadeNegocio = new ArrayList<Unidadenegocio>();
        }
    }
    
    public String gerarSql(){
        String sql = "SELECT distinct contaspagar.datacompensacao, planoconta.descricao as planocontas, contaspagar.descricao,"
                 + " contaspagar.valorentrada, contaspagar.valorsaida, contaspagar.competencia"
                + " From "
            	+ " contaspagar join unidadenegocio on contaspagar.unidadeNegocio_idunidadeNegocio = unidadenegocio.idunidadeNegocio"
                + " join planoconta on contaspagar.planoconta_idplanoconta = planoconta.idplanoconta"
                + "where ";
        if ((dataInicio!=null) && (dataTermino!=null)){
            sql = sql + " contaspagar.data >='"  + Formatacao.ConvercaoDataSql(dataInicio) + " ' and contaspagar.data<='"
                        + Formatacao.ConvercaoDataSql(dataTermino) + "' ";
        }else {
            sql = sql + " contaspagar.competencia='" + competencia + "' ";
        }
        if (unidadenegocio!=null){
            sql = sql + " contaspagar.unidadeNegocio_idunidadeNegocio=" + unidadenegocio.getIdunidadeNegocio();
        }
        sql = sql + "Group by contaspagar.planoContas_idplanoContas, contaspagar.dataCompensacao, contaspagar.descricao, contaspagar.valorEntrada, contaspgar.valorSaida, planocontas.descricao, unidade.nomefantasia, contaspagar.compentencia ";
        sql = sql + " order by contaspagar.dataCompensacao, planoconta.idplanoconta, contaspagar.descricao, contaspagar.valorEntrada, contaspgar.valorSaida, planocontas.descricao, unidade.nomefantasia, contaspagar.compentencia ";
        return sql;
    }
    
    
    
    public String gerarRelatorio(HttpServletRequest request, HttpServletResponse response) {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String caminhoRelatorio = "/reports/financeiro/Pagamentos.jasper";  
        Map parameters = new HashMap();
        parameters.put("sql", gerarSql());
        parameters.put("unidade", unidadenegocio.getNomeFantasia());
        String periodo= "";
        if ((dataInicio!=null) && (dataTermino!=null)){
                periodo = "Período : " + Formatacao.ConvercaoDataPadrao(dataInicio) 
                        + "    " + Formatacao.ConvercaoDataPadrao(dataTermino);
            }else periodo = "Competência : " + competencia;
        parameters.put("periodo", periodo);
        GerarRelatorio gerarRelatorio = new GerarRelatorio();
        try {
            gerarRelatorio.gerarRelatorioSqlPDF(caminhoRelatorio, parameters, "Pagamentos.pdf", null);
        } catch (JRException ex) {
            Logger.getLogger(RelatorioConciliacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioConciliacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
