/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.util.Formatacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wolverine
 */

@Named
@ViewScoped
public class ContasReceberMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Contasreceber> listaContas;
    private Contasreceber conta;
    private Float contasVencidas;
    private Float contasVencer;
    private Float contasVencendo;
     
    
    @PostConstruct
    public void init(){
        String sql = "Select c from Contasreceber c where recebimento.idrecebimento=1 order by c.datavencimento, c.vendas.cliente.nome";
        carregarContasReceber(sql);
        conta = new Contasreceber();
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public List<Contasreceber> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<Contasreceber> listaContas) {
        this.listaContas = listaContas;
    }

    public Contasreceber getConta() {
        return conta;
    }

    public void setConta(Contasreceber conta) {
        this.conta = conta;
    }

    public Float getContasVencidas() {
        return contasVencidas;
    }

    public void setContasVencidas(Float contasVencidas) {
        this.contasVencidas = contasVencidas;
    }

    public Float getContasVencer() {
        return contasVencer;
    }

    public void setContasVencer(Float contasVencer) {
        this.contasVencer = contasVencer;
    }

    public Float getContasVencendo() {
        return contasVencendo;
    }

    public void setContasVencendo(Float contasVencendo) {
        this.contasVencendo = contasVencendo;
    }
    
    
    public void carregarContasReceber(String sql){
        ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
        listaContas = contasReceberFacade.listar(sql);
        if (listaContas==null){
            listaContas = new ArrayList<Contasreceber>();
        }
    }
    
    public String iniciarCobranca(Contasreceber conta){
        this.conta = conta;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("venda", conta.getVendas());
        return "pagina de cobranca";
    }
    
    public String recebimento(){
        List<Contasreceber> lista = new ArrayList<Contasreceber>();
        for(int i=0;i<listaContas.size();i++){
            if (listaContas.get(i).isSelecionado()){
                lista.add(listaContas.get(i));
            }
        }
        if (lista.size()>0){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
            session.setAttribute("listarecebimento", lista);
            return "pagina de recebimento";
        }
        return " ";
    }
    
    public void calcularTotais(){
        contasVencendo=0.0f;
        contasVencer=0.0f;
        contasVencidas=0.0f;
        Date data = new Date();
        String sData = Formatacao.ConvercaoDataPadrao(data);
        data = Formatacao.ConvercaoStringDataBrasil(sData);
        for(int i=0;i<listaContas.size();i++){
            if (listaContas.get(i).getDatavencimento().equals(data)){
                contasVencendo = contasVencendo + listaContas.get(i).getValorparcela();
            }else if (listaContas.get(i).getDatavencimento().before(data)) {
                contasVencidas = contasVencidas + listaContas.get(i).getValorparcela();
            } else if (listaContas.get(i).getDatavencimento().after(new Date())) {
                contasVencer = contasVencer + listaContas.get(i).getValorparcela();
            }
        }
    }
}
