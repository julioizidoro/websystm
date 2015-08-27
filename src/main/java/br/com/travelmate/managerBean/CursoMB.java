package br.com.travelmate.managerBean;

import br.com.travelmate.managerBean.OrcamentoCurso.FornecedorProdutosBean;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CursoMB implements Serializable{
    
    @Inject 
    private UsuarioLogadoMB usuarioLogadoMB;

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }
    
    public String cadastrarFicha(){
        return "cadFichaCurso";
    }
    
   
    
    public String pesquisar(){
        return "resultadoFiltroOrcamento";
    }
    
}
