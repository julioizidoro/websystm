/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.bean.DadosBoletoBean;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Wolverine
 */
@Named
@SessionScoped
public class MenuMB implements Serializable{

    private static final long serialVersionUID = 1L;
	
    public String comissaoVendas(){
        return "comissaovenda";
    }
    
    public String contasPagar(){
        return "consultacontaspagar";
    }
    
    public String planoConta(){
        return "consplanoconta";
    }
    
    public String vendas(){
        return "vendas";
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
    
    public String faturafranquia(){
        return "faturafranquia";
    }
    
    public String highSchool(){
        return "consultaHighSchool";
    }
    
    public String ladies(){
        return "consultaLadies";
    }
    
    public String cursosTeens(){
        return "consultaProgramasTeens";
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
    
    public String conciliacaoBancaria(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",490);
        RequestContext.getCurrentInstance().openDialog("conciliacaobancaria", options, null);
        return "";
    }
    
    public String pagamentos(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",450);
        RequestContext.getCurrentInstance().openDialog("pagamentos", options, null);
        return "";
    }
    
    public String relatorioVendas(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",450);
        RequestContext.getCurrentInstance().openDialog("filtrarvendas", options, null);
        return "";
    }
    
    public String comissaoconsultor(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",450);
        RequestContext.getCurrentInstance().openDialog("comissaoconsultor", options, null);
        return "";
    }
    
    public String comissaogerente(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",450);
        RequestContext.getCurrentInstance().openDialog("comissaogerente", options, null);
        return "";
    }
    
    public String relatoriosContasReceber(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",450);
        RequestContext.getCurrentInstance().openDialog("filtrarcontasreceber", options, null);
        return "";
    }
     public String comissaoterceiros(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",490);
        RequestContext.getCurrentInstance().openDialog("comissaoterceiros", options, null);
        return "";
    }
    
}
