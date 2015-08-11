/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.util.GerarDacNossoNumero;
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

    
    
    public String comissaoVendas(){
        return "consultacomissaovendas";
    }
    
    public String contasPagar(){
        return "consultacontaspagar";
    }
    
    public String contasReceber(){
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
    public String curso(){
        return "consultafichacurso";
    }
    public String autorizacao(){
        return "autorizacaodebito";
    }
}
