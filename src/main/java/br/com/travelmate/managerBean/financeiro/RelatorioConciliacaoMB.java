/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.financeiro;

import br.com.travelmate.facade.BancoFacade;
import br.com.travelmate.facade.UnidadeNegocioFacade;
import br.com.travelmate.model.Banco;
import br.com.travelmate.model.Unidadenegocio;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class RelatorioConciliacaoMB implements Serializable{
    
    
    private List<Unidadenegocio> listaUnidadeNegocio;
    private List<Banco> listaBanco;
    private Date dataInicio;
    private Date dataTermino;
    private Unidadenegocio unidadenegocio;
    private Banco banco;
    
    @PostConstruct()
    public void init(){
        gerarListaBanco();
        gerarListaUnidadeNegocio();
    }

    public List<Unidadenegocio> getListaUnidadeNegocio() {
        return listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<Unidadenegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
    }

    public List<Banco> getLsitaBanco() {
        return listaBanco;
    }

    public void setLsitaBanco(List<Banco> listaBanco) {
        this.listaBanco = listaBanco;
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

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
    
    public void gerarListaUnidadeNegocio(){
        UnidadeNegocioFacade unidadeNegocioFacade = new UnidadeNegocioFacade();
        listaUnidadeNegocio = unidadeNegocioFacade.listar();
        if (listaUnidadeNegocio==null){
            listaUnidadeNegocio = new ArrayList<Unidadenegocio>();
        }
    }
    
    public void gerarListaBanco(){
        BancoFacade bancoFacade = new BancoFacade();
        listaBanco = bancoFacade.listar();
        if (listaBanco == null) {
            listaBanco = new ArrayList<Banco>();
        }
    }
    
    public String gerarRelatorio() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String caminhoRelatorio = "/reports/financeiro/conciliacao.jasper";  
        Map parameters = new HashMap();
        parameters.put("dataInicial", dataInicio);
        parameters.put("dataFinal", dataTermino);
        parameters.put("idUnidade", unidadenegocio.getIdunidadeNegocio());
        parameters.put("idBanco", banco.getIdbanco());
        GerarRelatorio gerarRelatorio = new GerarRelatorio();
        try {
            gerarRelatorio.gerarRelatorioSqlPDF(caminhoRelatorio, parameters, "Conciliação", null);
        } catch (JRException ex) {
            Logger.getLogger(RelatorioConciliacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioConciliacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public String fechar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
}
    

