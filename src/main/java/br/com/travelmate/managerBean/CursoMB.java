package br.com.travelmate.managerBean;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
    
    
    public String cadProdutoObrigatorio(){
         RequestContext.getCurrentInstance().openDialog("cadProdutoObrigatorio");
        return "";
    }
}
