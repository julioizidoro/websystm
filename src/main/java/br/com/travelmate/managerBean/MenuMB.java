/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

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
        return "consContasPagar";
    }
    
    public String contasReceber(){
        return "consContasReceber";
    }
    public String passagem(){
        return "consPassagem";
    }
    public String adcPassagem(){
        return "cadPassagem";
    }
    public String cliente(){
        return "consCliente";
    }
    public String fornecedor(){
        return "consFornecedor";
    }
    public String curso(){
        return "consCurso";
    }
}
