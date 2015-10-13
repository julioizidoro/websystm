package br.com.travelmate.managerBean;

import br.com.travelmate.model.Contaspagar;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class ContasPagarMB implements Serializable{
    
    private Contaspagar conta;

    public Contaspagar getConta() {
        return conta;
    }

    public void setConta(Contaspagar conta) {
        this.conta = conta;
    }
    
   
    
    public String cadastrarContaPagar(){
        return "confContasPagar";
    }
    
    public String confirmarContaPagar(){
        return "confContasPagar";
    }
}
