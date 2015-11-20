/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Greici
 */
@Named
@ViewScoped
public class CancelamentoMB implements Serializable{
    
     public String reembolsoCredito() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 380);
        RequestContext.getCurrentInstance().openDialog("reembolso",options,null);
        return "";
    }
   
    
    
    
}
