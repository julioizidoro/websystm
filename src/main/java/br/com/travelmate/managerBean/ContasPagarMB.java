package br.com.travelmate.managerBean;

import br.com.travelmate.facade.BancoFacade;
import br.com.travelmate.facade.ContasPagarFacade;
import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.facade.PlanoContaFacade;
import br.com.travelmate.facade.UnidadeNegocioFacade;
import br.com.travelmate.model.Banco;
import br.com.travelmate.model.Contaspagar;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.model.Planoconta;
import br.com.travelmate.model.Unidadenegocio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class ContasPagarMB implements Serializable{
    
    public ContasPagarMB() {
        
    }
    
    public String cadastrarContaPagar(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",570);
        RequestContext.getCurrentInstance().openDialog("confContasPagar", options, null);
        return "";
    }
    
    
     
     
    
}
