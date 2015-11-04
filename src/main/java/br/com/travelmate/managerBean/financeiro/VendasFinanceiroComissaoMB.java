/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.financeiro;

import br.com.travelmate.facade.TerceirosFacade;
import br.com.travelmate.facade.UsuarioFacade;
import br.com.travelmate.facade.VendasComissaoFacade;
import br.com.travelmate.model.Terceiros;
import br.com.travelmate.model.Usuario;
import br.com.travelmate.model.Vendas;
import br.com.travelmate.model.Vendascomissao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class VendasFinanceiroComissaoMB implements Serializable{
    
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
        if (usuario==null){
            return "sem nome";
        }else return usuario.getNome();
    }
    
    private void gerarListaTerceiros(){
        TerceirosFacade terceirosFacade = new TerceirosFacade();
        listaTerceiros = terceirosFacade.lista();
        if (listaTerceiros!=null){
            listaTerceiros = new ArrayList<Terceiros>();
        }
    }
    
    public String editarInformacoes(String campo){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("campoAlteracao", campo);
        session.setAttribute("venda", venda);
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",800);
        RequestContext.getCurrentInstance().openDialog("editarInformacoes", options, null);
        return "";
    }
    
    public void retornoDialog(SelectEvent event){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        String campoAlterado = (String) session.getAttribute("campoAlteracao");
        Float novoValor = (Float) session.getAttribute("novoValor");
        session.removeAttribute("campoAlteracao");
        session.removeAttribute("novoValor");
        setNovoValor(campoAlterado, novoValor);
    }
    
    private void setNovoValor(String campo, float novoValor){
        if (campo.equalsIgnoreCase("comissaotm")){
            vendascomissao.setComissaotm(novoValor);
        }else if (campo.equalsIgnoreCase("desagio")){
            vendascomissao.setDesagio(novoValor);
        }else if (campo.equalsIgnoreCase("comissaofranquia")){
            vendascomissao.setComissaofraquia(novoValor);
        }else if (campo.equalsIgnoreCase("incentivo")){
            vendascomissao.setIncentivo(novoValor);
        }else if (campo.equalsIgnoreCase("comissaoterceitos")){
            vendascomissao.setComissaoterceiros(novoValor);
        }else if (campo.equalsIgnoreCase("comissaoconsultor")){
            vendascomissao.setComissaoemissor(novoValor);
        }else if (campo.equalsIgnoreCase("comissaogerente")){
            vendascomissao.setComissaogerente(novoValor);
        }
    }
    
    public void cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void confirmar(){
        VendasComissaoFacade vendasComissaoFacade = new VendasComissaoFacade();
        vendasComissaoFacade.salvar(vendascomissao);
        RequestContext.getCurrentInstance().closeDialog(null);
    }
}
