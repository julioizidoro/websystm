package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.OCursoFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Ocurso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped
public class ConsultaOrcamentoMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject 
    private UsuarioLogadoMB usuarioLogadoMB;
    private Ocurso ocurso;
    private List<Ocurso> listaOcurso;
    private String nomeCliente=" ";
    
    
    @PostConstruct
    public void init(){
        gerarListaOrcamento();
        ocurso = new Ocurso();
    }

    public List<Ocurso> getListaOcurso() {
        return listaOcurso;
    }

    public void setListaOcurso(List<Ocurso> listaOcurso) {
        this.listaOcurso = listaOcurso;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Ocurso getOcurso() {
        return ocurso;
    }

    public void setOcurso(Ocurso ocurso) {
        this.ocurso = ocurso;
    }
    
    public String novo(){
        return "filtrarorcamento";
    }
    
    public void gerarListaOrcamento(){
        String sql = null;
        if (usuarioLogadoMB.getUsuario().getTipo().equalsIgnoreCase("Gerencial")){
            sql = "Select o from Ocurso o where o.cliente.nome like '%" + nomeCliente + "%' order by o.dataorcamento";
        }else {
            sql = "Select o from Ocurso o where o.idunidadenegocio=" + usuarioLogadoMB.getUsuario().getUnidadenegocio().getIdunidadeNegocio() +  
                     " ando.cliente.nome like '%" + nomeCliente + "%' order by o.dataorcamento";
        }
        OCursoFacade ocursofacade = new OCursoFacade();
        listaOcurso = ocursofacade.listar(sql);
        if (listaOcurso==null){
            listaOcurso = new ArrayList<Ocurso>();
        }
    }
}
