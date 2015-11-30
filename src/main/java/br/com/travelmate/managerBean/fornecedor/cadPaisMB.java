package br.com.travelmate.managerBean.fornecedor;

import br.com.travelmate.facade.CambioFacade;
import br.com.travelmate.facade.PaisFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Moedas;
import br.com.travelmate.model.Pais;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class cadPaisMB implements Serializable{
    
    private List<Moedas> listaMoedas;
    private Pais pais;
    private Moedas moedas;
    
    @PostConstruct
    public void init() {
        pais = new Pais();
        gerarListaMoedas();
    }

    public List<Moedas> getListaMoedas() {
        return listaMoedas;
    }

    public void setListaMoedas(List<Moedas> listaMoedas) {
        this.listaMoedas = listaMoedas;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Moedas getMoedas() {
        return moedas;
    }

    public void setMoedas(Moedas moedas) {
        this.moedas = moedas;
    }
    
    public String salvar(){
        if(moedas!=null){    
            pais.setMoedas(moedas);
            PaisFacade paisFacade = new PaisFacade();
            pais = paisFacade.salvar(pais);
            FacesMessage mensagem = new FacesMessage("Salvo com Sucesso! ", "Pais cadastrado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            RequestContext.getCurrentInstance().closeDialog(pais);
            
        }else{
             FacesMessage mensagem = new FacesMessage("Faltam informações! ", "Moeda não selecionada");
             FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public void gerarListaMoedas(){
        CambioFacade cambioFacade = new CambioFacade();
        listaMoedas = cambioFacade.listaMoedas();
        if(listaMoedas==null){
            listaMoedas = new ArrayList<Moedas>();
        }
    }
    
    public String cancelarCadastro(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
}
