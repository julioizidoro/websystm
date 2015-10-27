/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.financeiro;

import br.com.travelmate.facade.TerceirosFacade;
import br.com.travelmate.facade.UsuarioFacade;
import br.com.travelmate.model.Terceiros;
import br.com.travelmate.model.Usuario;
import br.com.travelmate.model.Vendas;
import br.com.travelmate.model.Vendascomissao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class VendasFinanceiroComissaoMB {
    
    private Vendas venda;
    private String titulo;
    private Vendascomissao vendascomissao;
    private List<Terceiros> listaTerceiros;
    
    @PostConstruct
    private void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        venda = (Vendas) session.getAttribute("venda");
        session.removeAttribute("venda");
        if (venda!=null){
            setTitulo("Venda No. " + String.valueOf(venda.getIdvendas()));
            vendascomissao = venda.getVendascomissaoList().get(0);
            gerarListaTerceiros();
        }
    }

    public Vendas getVenda() {
        return venda;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Vendascomissao getVendascomissao() {
        return vendascomissao;
    }

    public void setVendascomissao(Vendascomissao vendascomissao) {
        this.vendascomissao = vendascomissao;
    }

    public List<Terceiros> getListaTerceiros() {
        return listaTerceiros;
    }

    public void setListaTerceiros(List<Terceiros> listaTerceiros) {
        this.listaTerceiros = listaTerceiros;
    }
    
    public String nomeGerente(){
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        Usuario usuario = usuarioFacade.consultar(vendascomissao.getProdutos().getIdgerente());
        return usuario.getNome();
    }
    
    private void gerarListaTerceiros(){
        TerceirosFacade terceirosFacade = new TerceirosFacade();
        listaTerceiros = terceirosFacade.lista();
        if (listaTerceiros!=null){
            listaTerceiros = new ArrayList<Terceiros>();
        }
    }
}
