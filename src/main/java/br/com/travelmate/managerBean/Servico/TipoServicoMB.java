/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.Servico;

import br.com.travelmate.Interface.Tiposervico;
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
public class TipoServicoMB implements Serializable{
    
    private List<Tiposervico> listaTipoServico;
    private Tiposervico tiposervico;
    
    
    @PostConstruct
    public void init(){
        gerarListaTipoServico();;
        tiposervico = new Tiposervico();
    }

    public List<Tiposervico> getListaTipoServico() {
        return listaTipoServico;
    }

    public void setListaTipoServico(List<Tiposervico> listaTipoServico) {
        this.listaTipoServico = listaTipoServico;
    }

    public Tiposervico getTiposervico() {
        return tiposervico;
    }

    public void setTiposervico(Tiposervico tiposervico) {
        this.tiposervico = tiposervico;
    }
    
    public void mostrarMensagem(Exception ex, String erro, String titulo){
        FacesContext context = FacesContext.getCurrentInstance();
        erro = erro + " - " + ex;
        context.addMessage(null, new FacesMessage(titulo, erro));
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
    
    public void salvar(){
        TipoServicoFacade tipoServicoFacade = new TipoServicoFacade();
        try {
            tipoServicoFacade.salvar(tiposervico);
            tiposervico = new Tiposervico();
            gerarListaTipoServico();
        } catch (SQLException ex) {
            Logger.getLogger(TipoServicoMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro ao Salvar", "ERRO");
        }
    }
    
    public void editar(Tiposervico tiposervico){
        this.tiposervico = tiposervico;
    }
    
    public void cancelar(){
        this.tiposervico = new Tiposervico();
    }
}
