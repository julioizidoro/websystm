/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.CobrancaFacade;
import br.com.travelmate.model.Cobranca;
import br.com.travelmate.model.Historicocobranca;
import br.com.travelmate.model.Vendas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class CobrancaMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject 
    private UsuarioLogadoMB usuarioLogadoMB;
    private Vendas venda;
    private Cobranca cobranca;
    private List<Historicocobranca> listaHistorico;
    private Historicocobranca historico;
    
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);  
        venda = (Vendas) session.getAttribute("venda");
        session.removeAttribute("venda");
        if (venda==null){
            venda = new Vendas();
        }else {
            if (venda.getCobrancaList().size()>0){
                cobranca = venda.getCobrancaList().get(0);
            }
        }
        if (cobranca==null){
            cobranca = new Cobranca();
            listaHistorico = new ArrayList<Historicocobranca>();
        }else {
            listaHistorico = cobranca.getHistoricocobrancaList();
        }
        historico = new Historicocobranca();
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Vendas getVenda() {
        return venda;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
    }

    public Cobranca getCobranca() {
        return cobranca;
    }

    public void setCobranca(Cobranca cobranca) {
        this.cobranca = cobranca;
    }

    public List<Historicocobranca> getListaHistorico() {
        return listaHistorico;
    }

    public void setListaHistorico(List<Historicocobranca> listaHistorico) {
        this.listaHistorico = listaHistorico;
    }

    public Historicocobranca getHistorico() {
        return historico;
    }

    public void setHistorico(Historicocobranca historico) {
        this.historico = historico;
    }
    
    
    
    public String salvarFoneCobranca(){
         CobrancaFacade cobrancaFacade = new CobrancaFacade();
         cobranca.setVendas(venda);
         cobranca = cobrancaFacade.salvar(cobranca);
         FacesMessage mensagem = new FacesMessage("Salvo com Sucesso! ", "Fone de Contato salvo.");
         FacesContext.getCurrentInstance().addMessage(null, mensagem);
         return "consContasReceber";
    }
    
     
    public void verHistorico(Historicocobranca historico){
        this.historico = historico;
    }
    
    public String voltar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
    
    public String cancelar(){
        return "consContasReceber";
    }
    
    public void novoHistorico(){
        historico = new Historicocobranca();
        historico.setData(new Date());
    }
    
    public String salvarHitorico(){
        CobrancaFacade cobrancaFacade = new CobrancaFacade();
        if (cobranca.getIdcobranca()==null){
            cobranca.setVendas(venda);
            cobranca = cobrancaFacade.salvar(cobranca);
            
        }
        historico.setData(new Date());
        historico.setCobranca(cobranca);
        historico.setUsuario(usuarioLogadoMB.getUsuario());
        historico = cobrancaFacade.salvar(historico);
        cobranca.getHistoricocobrancaList().add(historico);
        FacesMessage mensagem = new FacesMessage("Salvo com Sucesso! ", "Historico de Cobrança Salvo.");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        return "consContasReceber";
    }
    
    
    
    public String editarHistorico() { 
        if (historico!=null){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("historico", historico);
            session.setAttribute("cobranca", cobranca);
        }
        return "";
    }
    

}


