package br.com.travelmate.managerBean;

import br.com.travelmate.bean.AutorizacaoDebitoBean;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class AutorizacaoDebitoMB implements Serializable{
    
   private AutorizacaoDebitoBean autorizacaoDebitoBean;

    public AutorizacaoDebitoBean getAutorizacaoDebitoBean() {
        return autorizacaoDebitoBean;
    }

    public void setAutorizacaoDebitoBean(AutorizacaoDebitoBean autorizacaoDebitoBean) {
        this.autorizacaoDebitoBean = autorizacaoDebitoBean;
    }

    
}
