package br.com.travelmate.managerBean;


import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CursoMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
