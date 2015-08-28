/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.bean.DadosBoletoBean;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@SessionScoped
public class MenuMB implements Serializable{

    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	public String comissaoVendas(){
        return "consultacomissaovenda";
    }
    
    public String contasPagar(){
        return "consultacontaspagar";
    }
    
    public String contasRecebers(){
        DadosBoletoBean dadosBoletoBean = new DadosBoletoBean();
        dadosBoletoBean.emitir();
        return "";
    }
    public String contasReceber(){
//        DadosBoletoBean dadosBoletoBean = new DadosBoletoBean();
//        dadosBoletoBean.emitir();
        return "consultacontasreceber";
    }
    public String passagem(){
        return "consultapassagens";
    }
    public String pacoteso(){
        return "consultapacotesoperadora";
    }
    public String pacotesa(){
        return "consultapacotesagencia";
    }
    public String cliente(){
        return "consultacliente";
    }
    public String fornecedor(){
        return "consultafornecedor";
    }
    
    public String produtos(){
        return "consultaprodutos";
    }
    
    public String curso(){
        return "consultafichacurso";
    }
    public String autorizacao(){
        return "autorizacaodebito";
    }
    
    public String produtoCurso(){
        return "orcamentocurso";
    }
    
    public String orcamentoCurso(){
        return "filtrarorcamento";
    }
}
