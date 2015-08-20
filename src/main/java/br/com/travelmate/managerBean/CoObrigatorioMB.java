package br.com.travelmate.managerBean;

import br.com.travelmate.facade.CoObrigatorioFacade;
import br.com.travelmate.model.Coobrigatorio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CoObrigatorioMB implements Serializable{
    
    @Inject 
    private UsuarioLogadoMB usuarioLogadoMB;
    private Coobrigatorio coobrigatorio;
    private List<Coobrigatorio> listacoObrigatorio;

    @PostConstruct
    public void init(){
        String sql = "Select c from Coobrigatorio c order by c.tipodata";
        gerarListaCoObrigatorio(sql);
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Coobrigatorio getCoobrigatorio() {
        return coobrigatorio;
    }

    public void setCoobrigatorio(Coobrigatorio coobrigatorio) {
        this.coobrigatorio = coobrigatorio;
    }

    public List<Coobrigatorio> getListacoObrigatorio() {
        return listacoObrigatorio;
    }

    public void setListacoObrigatorio(List<Coobrigatorio> listacoObrigatorio) {
        this.listacoObrigatorio = listacoObrigatorio;
    }
    
    public List<Coobrigatorio> gerarListaCoObrigatorio(String sql){
        CoObrigatorioFacade coObrigatorioFacade = new CoObrigatorioFacade();
        List<Coobrigatorio> listaObrigatorio = coObrigatorioFacade.listar(sql);
        if (listaObrigatorio==null){
           listaObrigatorio = new ArrayList<Coobrigatorio>();
        }
        return listaObrigatorio;
    }
    
}
