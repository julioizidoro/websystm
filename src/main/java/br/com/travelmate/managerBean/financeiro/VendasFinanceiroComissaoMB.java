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
            novoValorComissaoTM(novoValor);
        }else if (campo.equalsIgnoreCase("desagio")){
            novoValorDessagio(novoValor);
        }else if (campo.equalsIgnoreCase("comissaofranquia")){
            novoValorComissaoFranquia(novoValor);
        }else if (campo.equalsIgnoreCase("incentivo")){
            novoValorIncentivo(novoValor);
        }else if (campo.equalsIgnoreCase("comissaoterceitos")){
            novoValorComissaoTerceiros(novoValor);
        }else if (campo.equalsIgnoreCase("comissaoconsultor")){
            novoValorComissaoConsultor(novoValor);
        }else if (campo.equalsIgnoreCase("comissaogerente")){
            novoValorComissaoGerente(novoValor);
        }
    }
    
    public String cancelar(){
        return "consVendas";
    }
    
    public void confirmar(){
        VendasComissaoFacade vendasComissaoFacade = new VendasComissaoFacade();
        vendasComissaoFacade.salvar(vendascomissao);
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void novoValorComissaoTM(float novoValor){
         vendascomissao.setComissaotm(novoValor);
         vendascomissao.setValorfornecedor(venda.getValor() - (vendascomissao.getComissaotm() + vendascomissao.getTaxatm()));
         float valorComissao = vendascomissao.getComissaotm() + vendascomissao.getTaxatm();
         valorComissao = valorComissao - (vendascomissao.getDesagio() + vendascomissao.getDescontotm() + vendascomissao.getDescontoloja() + vendascomissao.getLiquidofranquia());
         valorComissao = (float) (valorComissao * (venda.getProdutos().getComissaogerente()/100));
         vendascomissao.setComissaogerente(valorComissao);
         calcularLiquidoVendas();
    }
    
    public void novoValorDessagio(float novoValor){
        //Comissão Franquia = % comissão franquia * Valor Comissionavel + 50% Taxa TM – 50% Desconto Matriz – 100% Desconto Loja + Incentivo
        vendascomissao.setDesagio(novoValor);
        float comissaoFranquia = vendascomissao.getComissaofranquiabruta() + (vendascomissao.getTaxatm()/2);
        comissaoFranquia = comissaoFranquia - ((vendascomissao.getDescontotm()/2) + vendascomissao.getDescontoloja() + vendascomissao.getDesagio());
        vendascomissao.setComissaofraquia(comissaoFranquia);
        vendascomissao.setLiquidofranquia(vendascomissao.getComissaofraquia() + vendascomissao.getIncentivo());
        
        //Comissão Gerente = % comissão gerente * (Comissão TM + Taxa TM – Deságio – Desconto Matriz – Desconto Loja – Líquido FRQ)
        float comissaoGerente = vendascomissao.getComissaotm() + vendascomissao.getTaxatm();
         comissaoGerente = comissaoGerente - (vendascomissao.getDesagio() + vendascomissao.getDescontotm() + vendascomissao.getDescontoloja() + vendascomissao.getLiquidofranquia());
         comissaoGerente = (float) (comissaoGerente * (venda.getProdutos().getComissaogerente()/100));
         vendascomissao.setComissaogerente(comissaoGerente);
         
         //Comissão Consultor = % comissão consultor * Líquido FRQ
         vendascomissao.setComissaoemissor(vendascomissao.getLiquidofranquia() * (venda.getUnidadenegocio().getPerconsultor().floatValue()/100));
         calcularLiquidoVendas();
        
    }
    
    public void novoValorComissaoFranquia(float novoValor){
        vendascomissao.setComissaofraquia(novoValor);
        vendascomissao.setLiquidofranquia(vendascomissao.getComissaofraquia() + vendascomissao.getIncentivo());
        float comissaoGerente = vendascomissao.getComissaotm() + vendascomissao.getTaxatm();
         comissaoGerente = comissaoGerente - (vendascomissao.getDesagio() + vendascomissao.getDescontotm() + vendascomissao.getDescontoloja() + vendascomissao.getLiquidofranquia());
         comissaoGerente = (float) (comissaoGerente * (venda.getProdutos().getComissaogerente()/100));
         vendascomissao.setComissaogerente(comissaoGerente);
        vendascomissao.setComissaoemissor(vendascomissao.getLiquidofranquia() * (venda.getUnidadenegocio().getPerconsultor().floatValue()/100));
         calcularLiquidoVendas();
    }
    
    public void novoValorIncentivo(float novoValor){
        vendascomissao.setIncentivo(novoValor);
        vendascomissao.setLiquidofranquia(vendascomissao.getComissaofraquia() + vendascomissao.getIncentivo());
        float comissaoGerente = vendascomissao.getComissaotm() + vendascomissao.getTaxatm();
         comissaoGerente = comissaoGerente - (vendascomissao.getDesagio() + vendascomissao.getDescontotm() + vendascomissao.getDescontoloja() + vendascomissao.getLiquidofranquia());
         comissaoGerente = (float) (comissaoGerente * (venda.getProdutos().getComissaogerente()/100));
         vendascomissao.setComissaogerente(comissaoGerente);
        vendascomissao.setComissaoemissor(vendascomissao.getLiquidofranquia() * (venda.getUnidadenegocio().getPerconsultor().floatValue()/100));
         calcularLiquidoVendas();
    }
    
    public void novoValorComissaoTerceiros(float novoValor){
        vendascomissao.setComissaoterceiros(novoValor);
         calcularLiquidoVendas();
    }
    
    public void novoValorComissaoConsultor(float novoValor){
        vendascomissao.setComissaoemissor(novoValor);
         calcularLiquidoVendas();
    }
    
    public void novoValorComissaoGerente(float novoValor){
        vendascomissao.setComissaogerente(novoValor);
         calcularLiquidoVendas();
    }
    
    public void calcularLiquidoVendas(){
        float somar = vendascomissao.getComissaotm() + vendascomissao.getTaxatm();
        float subtrair = vendascomissao.getDescontotm() + vendascomissao.getComissaoemissor() + 
                vendascomissao.getComissaogerente() + vendascomissao.getComissaoterceiros() +
                vendascomissao.getComissaofraquia() + vendascomissao.getDesagio();
        vendascomissao.setLiquidovendas(somar - subtrair);
    }
}
