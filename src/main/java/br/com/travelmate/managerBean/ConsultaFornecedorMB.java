package br.com.travelmate.managerBean;

import br.com.travelmate.model.Fornecedorcidade;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ConsultaFornecedorMB implements Serializable{
    
    private Fornecedorcidade fornecedorcidade;
    private String retorno;

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }
    
    
    
   
}
