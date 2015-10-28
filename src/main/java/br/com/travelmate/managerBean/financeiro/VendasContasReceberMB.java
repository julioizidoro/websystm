/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.financeiro;

import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.model.Vendas;
import br.com.travelmate.util.Formatacao;
import java.io.Serializable;
import java.util.Date;
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
public class VendasContasReceberMB implements Serializable{
    
    private Vendas venda;
    private String titulo;
    
    @PostConstruct
    private void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        venda = (Vendas) session.getAttribute("venda");
        session.removeAttribute("venda");
        if (venda!=null){
            setTitulo("Venda No. " + String.valueOf(venda.getIdvendas()));
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
    
    public String gerarStatusImagem(Contasreceber conta){
        if (conta.getDatapagamento()!=null){
            return "../../resources/img/bolaVerde.png";
        }else{
            Date data = Formatacao.formatarDataAgora();
            if (data.before(conta.getDatavencimento())){
                return "../../resources/img/bolaVermelho.png";
            }else {
                return "../../resources/img/bolaAmarela.png";
            }
        }
    }
    
    public String gerarTitulo(Contasreceber conta){
        if (conta.getDatapagamento()!=null){
            return "Já recebido";
        }else{
            Date data = Formatacao.formatarDataAgora();
            if (data.before(conta.getDatavencimento())){
                return "Dentro do prazo";
            }else {
                return "Em atraso";
            }
        }
    }
}   
