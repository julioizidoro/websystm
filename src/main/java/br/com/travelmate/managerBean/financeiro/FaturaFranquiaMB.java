/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.financeiro;

import br.com.travelmate.facade.FaturaFranquiasFacade;
import br.com.travelmate.facade.UnidadeNegocioFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Faturafranquias;
import br.com.travelmate.model.Unidadenegocio;
import br.com.travelmate.model.Vendascomissao;
import br.com.travelmate.util.Formatacao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class FaturaFranquiaMB implements Serializable{
    
    @Inject 
    private UsuarioLogadoMB usuarioLogadoMB;
    private Unidadenegocio unidadenegocio;
    private List<Unidadenegocio> listaUnidadeNegocio;
    private List<Faturafranquias> listaFatura;
    
    
    @PostConstruct()
    public void init(){
        gerarListaUnidadeNegocio();
    }

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }

    public List<Unidadenegocio> getListaUnidadeNegocio() {
        return listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<Unidadenegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public List<Faturafranquias> getListaFatura() {
        return listaFatura;
    }

    public void setListaFatura(List<Faturafranquias> listaFatura) {
        this.listaFatura = listaFatura;
    }

    

    
    public void gerarListaUnidadeNegocio(){
        UnidadeNegocioFacade unidadeNegocioFacade = new UnidadeNegocioFacade();
        listaUnidadeNegocio = unidadeNegocioFacade.listar();
        if (listaUnidadeNegocio==null){
            listaUnidadeNegocio = new ArrayList<Unidadenegocio>();
        }
    }
    
    public void gerarListaFaturaFranquia(){
        FaturaFranquiasFacade faturaFranquiasFacade = new FaturaFranquiasFacade();
        if(unidadenegocio!=null){
            try {
                listaFatura = faturaFranquiasFacade.listar("Select f from Faturafranquias f where f.vendascomissao.vendas.unidadenegocio.idunidadeNegocio="+ unidadenegocio.getIdunidadeNegocio() +" order by f.vendascomissao.vendas.dataVenda");
            } catch (SQLException ex) {
                Logger.getLogger(FaturaFranquiaMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (listaFatura==null){
            listaFatura = new ArrayList<Faturafranquias>();
        }
    }
    
    public String calcularPercentualComissao(Vendascomissao vendaComissao){
        float percentual = 0.0f;
        if (vendaComissao!=null){
            if ((vendaComissao.getComissaofranquiabruta()>0) && (vendaComissao.getValorcomissionavel()>0)){
                percentual = vendaComissao.getComissaofranquiabruta() / vendaComissao.getValorcomissionavel();
                percentual = percentual * 100;
            }
            
        }
        return Formatacao.formatarFloatString(percentual);
    }
    
    public String gerarMesPagamento(Faturafranquias faturafranquias){
        Vendascomissao vendascomissao = faturafranquias.getVendascomissao();
        if (faturafranquias.getLiquidopagar() < 0) {
            if (vendascomissao.getDatainicioprograma() != null) {
                String dataInicio = Formatacao.ConvercaoDataPadrao(vendascomissao.getDatainicioprograma());
                String sAno = dataInicio.substring(6, 10);
                String sDia = dataInicio.substring(1, 3);
                String sMes = dataInicio.substring(4, 5);
                int mes = Integer.parseInt(sMes);
                int ano = Integer.parseInt(sAno);
                if (vendascomissao.getVendas().getUnidadenegocio().getMespagamento() == 1) {
                    if (mes == 12) {
                        mes = 1;
                        ano = ano + 1;
                    } else {
                        mes = mes + 1;
                    }
                } else {
                    if (mes == 11) {
                        mes = 01;
                        ano = ano + 1;
                    } else if (mes == 12) {
                        mes = 02;
                        ano = ano + 1;
                    } else {
                        mes = mes + 2;
                    }
                }
                dataInicio = Formatacao.nomeMes(mes) + "/" + String.valueOf(ano);
                return dataInicio;
            }
            return "";
        } else {
            String dataInicio = Formatacao.ConvercaoDataPadrao(vendascomissao.getVendas().getDataVenda());
            String sAno = dataInicio.substring(6, 10);
            String sDia = dataInicio.substring(1, 3);
            String sMes = dataInicio.substring(4, 5);
            int mes = Integer.parseInt(sMes);
            int ano = Integer.parseInt(sAno);
            if (mes == 12) {
                mes = 1;
                ano = ano + 1;
            } else {
                 mes = mes + 1;
            }
            dataInicio = Formatacao.nomeMes(mes) + "/" + String.valueOf(ano);
            return dataInicio;
        }
    }
}
