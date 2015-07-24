package br.com.travelmate.managerBean;

import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.facade.FornecedorCidadeFacade;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.model.Fornecedorcidade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ConsultaFornecedorMB implements Serializable{
    
    private Fornecedorcidade fornecedorcidade;

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }
    
    
    
   
}
