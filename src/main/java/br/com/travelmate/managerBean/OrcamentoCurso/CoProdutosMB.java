package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.CoObrigatorioFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Coprodutos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CoProdutosMB implements Serializable{
    
    @Inject 
    private UsuarioLogadoMB usuarioLogadoMB;
    private Coprodutos coobrigatorio;
    private List<Coprodutos> listacoObrigatorio;

    @PostConstruct
    public void init(){
        String sql = "Select c from Coprodutos c order by c.tipodata";
        gerarListaCoObrigatorio(sql);
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Coprodutos getCoobrigatorio() {
        return coobrigatorio;
    }

    public void setCoobrigatorio(Coprodutos coobrigatorio) {
        this.coobrigatorio = coobrigatorio;
    }

    public List<Coprodutos> getListacoObrigatorio() {
        return listacoObrigatorio;
    }

    public void setListacoObrigatorio(List<Coprodutos> listacoObrigatorio) {
        this.listacoObrigatorio = listacoObrigatorio;
    }
    
    public List<Coprodutos> gerarListaCoObrigatorio(String sql){
        CoObrigatorioFacade coObrigatorioFacade = new CoObrigatorioFacade();
        List<Coprodutos> listaObrigatorio = coObrigatorioFacade.listar(sql);
        if (listaObrigatorio==null){
           listaObrigatorio = new ArrayList<Coprodutos>();
        }
        return listaObrigatorio;
    }
    
    public String cadProduto(){
         RequestContext.getCurrentInstance().openDialog("cadProduto");
        return "";
    }
    
}
