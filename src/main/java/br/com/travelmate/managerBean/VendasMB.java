package br.com.travelmate.managerBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class VendasMB  implements Serializable{
    
    public String informacoesAdicionais(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",570);
        RequestContext.getCurrentInstance().openDialog("informacoesAdicionais", options, null);
        return "";
    }
    
    public String produtoVendas(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",570);
        RequestContext.getCurrentInstance().openDialog("produtoVendas", options, null);
        return "";
    }
    
    public String visualizarContasReceber(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",620);
        RequestContext.getCurrentInstance().openDialog("visualizarContasReceber", options, null);
        return "";
    }
    
    public String editarComissao(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",850);
        RequestContext.getCurrentInstance().openDialog("editarComissoes", options, null);
        return "";
    }
    
    public String editarInformacoes(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",800);
        RequestContext.getCurrentInstance().openDialog("editarInformacoes", options, null);
        return "";
    }
    
    public String fecharEditarInformacoes(){
        RequestContext.getCurrentInstance().openDialog("editarInformacoes");
        return "";
    }
}
