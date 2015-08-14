package br.com.travelmate.managerBean;

import br.com.travelmate.model.Contasreceber;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class BoletoMB implements Serializable{
     private List<Contasreceber> listarSelecionados;

    public List<Contasreceber> getListarSelecionados() {
        return listarSelecionados;
    }

    public void setListarSelecionados(List<Contasreceber> listarSelecionados) {
        this.listarSelecionados = listarSelecionados;
    }
     
     
    
}
