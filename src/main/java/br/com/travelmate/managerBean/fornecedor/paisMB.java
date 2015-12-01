package br.com.travelmate.managerBean.fornecedor;

import br.com.travelmate.facade.PaisFacade;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Pais;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class paisMB implements Serializable{
    
    private List<Pais> listaPais;
    private Pais pais;
    
    @PostConstruct
    public void init() {
        pais = new Pais();
        gerarListaPais();
    }

    public List<Pais> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Pais> listaPais) {
        this.listaPais = listaPais;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    public void gerarListaPais(){
        PaisFacade paisFacade = new PaisFacade();
        listaPais = paisFacade.listar("");
        if(listaPais==null){
            listaPais = new ArrayList<Pais>();
        }
    }
    
    public String cadPais(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 370);
        RequestContext.getCurrentInstance().openDialog("cadPais", options, null);
        return "";
    }
    
    public String cadCidade(){
        if(pais!=null){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("pais", pais);    
            RequestContext.getCurrentInstance().openDialog("cadCidade");
        }else{
            FacesMessage mensagem = new FacesMessage("Pais não selecionado! ", "campos obrigatorios não preenchidos.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String voltar(){
        return "consFornecedorPais";
    }
    
    public void retornoDialogNovoPais(SelectEvent event) {
        Pais pais = (Pais) event.getObject();
        listaPais.add(pais);
    }

    public void retornoDialogNovoCidade(SelectEvent event) {
        Cidade cidade = (Cidade) event.getObject();
        pais.getCidadeList().add(cidade);
    }
}
