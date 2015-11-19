/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.Servico;

import br.com.travelmate.Interface.Servico;
import br.com.travelmate.Interface.Tiposervico;
import br.com.travelmate.facade.ServicoFacade;
import br.com.travelmate.facade.TipoServicoFacade;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class ServicoMB implements Serializable{
    
    private List<Servico> listaServico;
    private List<Tiposervico> listaTipoServico;
    private Tiposervico tipoServico;
    
    @PostConstruct
    public void init(){
        gerarListaServicos();;
        gerarListaTipoServico();
    }

    public List<Servico> getListaServico() {
        return listaServico;
    }

    public void setListaServico(List<Servico> listaServico) {
        this.listaServico = listaServico;
    }

    public List<Tiposervico> getListaTipoServico() {
        return listaTipoServico;
    }

    public void setListaTipoServico(List<Tiposervico> listaTipoServico) {
        this.listaTipoServico = listaTipoServico;
    }

    public Tiposervico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Tiposervico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public void mostrarMensagem(Exception ex, String erro, String titulo){
        FacesContext context = FacesContext.getCurrentInstance();
        erro = erro + " - " + ex;
        context.addMessage(null, new FacesMessage(titulo, erro));
    }
    
    public void gerarListaServicos(){
        String sql = null;
        if (tipoServico==null){
            sql = "Select s from Servico s order by s.nome";
        }else {
            sql = "Select s from Servico s where s.tiposervico.idtiposervico order by s.nome";
        }   
        ServicoFacade servicoFacade = new ServicoFacade();
        try {
            listaServico = servicoFacade.lista(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServicoMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro Listar", "ERRO");
        }
    }
    
    public void gerarListaTipoServico() {
        TipoServicoFacade tipoServicoFacade = new TipoServicoFacade();
        try {
            listaTipoServico = tipoServicoFacade.lista();
            if (listaTipoServico == null) {
                listaTipoServico = new ArrayList<Tiposervico>();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoServicoMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro listar ", "ERRO");
        }
    }
}
