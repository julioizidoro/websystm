package br.com.travelmate.managerBean;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.facade.SeguroViagemFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotes;
import br.com.travelmate.model.Paisproduto;
import br.com.travelmate.model.Seguroviagem;
import br.com.travelmate.model.Valoresseguro;
import br.com.travelmate.util.Formatacao;

@Named
@ViewScoped
public class SeguroViagemMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Cambio cambio;
    private Seguroviagem seguroviagem;
    private Fornecedorcidade fornecedorcidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private Valoresseguro valoresseguro;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Pacotes pacotes = (Pacotes) session.getAttribute("pacote");
        SeguroViagemFacade seguroViagemFacade = new SeguroViagemFacade();
        seguroviagem = seguroViagemFacade.consultar(pacotes.getVendas().getIdvendas());
        iniciarListaFornecedorCidade();
        if (seguroviagem == null) {
            seguroviagem = new Seguroviagem();
            seguroviagem.setVendas(pacotes.getVendas());
            cambio = new Cambio();
            fornecedorcidade = new Fornecedorcidade();
            valoresseguro = new Valoresseguro();
        }else{
            fornecedorcidade = seguroviagem.getValoresseguro().getFornecedorcidade();
            valoresseguro = seguroviagem.getValoresseguro();
        }
    }
    
    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public Seguroviagem getSeguroviagem() {
        return seguroviagem;
    }

    public void setSeguroviagem(Seguroviagem seguroviagem) {
        this.seguroviagem = seguroviagem;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }


    public List<Fornecedorcidade> getListaFornecedorCidade() {
        return listaFornecedorCidade;
    }

    public void setListaFornecedorCidade(List<Fornecedorcidade> listaFornecedorCidade) {
        this.listaFornecedorCidade = listaFornecedorCidade;
    }    

    public Valoresseguro getValoresseguro() {
        return valoresseguro;
    }

    public void setValoresseguro(Valoresseguro valoresseguro) {
        this.valoresseguro = valoresseguro;
    }
    
    public String salvarSeguro() throws SQLException{
        SeguroViagemFacade seguroViagemFacade = new SeguroViagemFacade();
        seguroviagem.setFornecedor(fornecedorcidade.getFornecedor());
        seguroviagem.setPlano(valoresseguro.getPlano());
        seguroviagem.setPossuiSeguro("Sim");
        seguroviagem.setSeguradora(valoresseguro.getFornecedorcidade().getFornecedor().getNome());
        seguroviagem.setValoresseguro(valoresseguro);
        seguroViagemFacade.salvar(seguroviagem);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        RequestContext.getCurrentInstance().closeDialog("cadpacotesoperadora");
        return "";
    }
    
    
    public void iniciarListaFornecedorCidade(){
        PaisProdutoFacade paisProdutoFacade = new PaisProdutoFacade();
        int idProduto = usuarioLogadoMB.getParametrosprodutos().getSeguroPrivado();
        List<Paisproduto> listaPais = paisProdutoFacade.listar(idProduto);
        if (listaPais!=null){
            listaFornecedorCidade =  listaPais.get(0).getProdutos().getFornecedorcidadeList();
        }
    }
    
    public void calcularDataTermino(){
        if (valoresseguro.getCobranca().equalsIgnoreCase("semana")){
            if ((seguroviagem.getDataInicio()!=null) && (seguroviagem.getNumeroSemanas()>0)){
                seguroviagem.setDataTermino(Formatacao.calcularDataFinal(seguroviagem.getDataInicio(), seguroviagem.getNumeroSemanas(), "semana"));
                seguroviagem.setValorSeguro(valoresseguro.getValorgross() * seguroviagem.getNumeroSemanas());
            }
        } else if (valoresseguro.getCobranca().equalsIgnoreCase("diaria")) {
            if ((seguroviagem.getDataInicio() != null) && (seguroviagem.getNumeroSemanas() > 0)) {
                seguroviagem.setDataTermino(Formatacao.calcularDataFinal(seguroviagem.getDataInicio(), seguroviagem.getNumeroSemanas(), "diaria"));
                Cambio cambio = new Cambio();
                for (int i = 0; i < usuarioLogadoMB.getListaCambio().size(); i++) {
                    if (usuarioLogadoMB.getListaCambio().get(i).getMoedas().getSigla().equalsIgnoreCase(valoresseguro.getMoedas().getSigla())) {
                        cambio = usuarioLogadoMB.getListaCambio().get(i);
                        i = 10000;
                    }
                }
                if (cambio != null) {
                    float valornacional = valoresseguro.getValorgross() * cambio.getValor();
                    seguroviagem.setValorSeguro(valornacional * seguroviagem.getNumeroSemanas());
                }
            }
        } else if (valoresseguro.getCobranca().equalsIgnoreCase("fechada")) {
            Cambio cambio = new Cambio();
            for (int i = 0; i < usuarioLogadoMB.getListaCambio().size(); i++) {
                if (usuarioLogadoMB.getListaCambio().get(i).getMoedas().getSigla().equalsIgnoreCase(valoresseguro.getMoedas().getSigla())) {
                    cambio = usuarioLogadoMB.getListaCambio().get(i);
                    i = 10000;
                }
            }
            if (cambio != null) {
                float valornacional = valoresseguro.getValorgross() * cambio.getValor();
                seguroviagem.setValorSeguro(valornacional);
            }
        }
    }
    
    public String cancelar(){
        RequestContext.getCurrentInstance().closeDialog("cadpacotesoperadora");
        return "";
    }
    
    public String excluir() {
        SeguroViagemFacade seguroViagemFacade = new SeguroViagemFacade();
        seguroViagemFacade.excluir(seguroviagem.getIdseguroViagem());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Excluído com Sucesso", ""));
        RequestContext.getCurrentInstance().closeDialog("cadpacotesoperadora");
        return "";
    }
}
