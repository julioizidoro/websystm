package br.com.travelmate.managerBean;

import br.com.travelmate.facade.PacoteCruzeiroFacade;
import br.com.travelmate.facade.PacoteIngressoFacade;
import br.com.travelmate.facade.PacotePasseioFacade;
import br.com.travelmate.facade.PacoteTransferFacade;
import br.com.travelmate.facade.PacoteTremFacade;
import br.com.travelmate.facade.PacotesCarroFacade;
import br.com.travelmate.facade.PacotesHotelFacade;
import br.com.travelmate.facade.PaisFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotecarro;
import br.com.travelmate.model.Pacotecruzeiro;
import br.com.travelmate.model.Pacotehotel;
import br.com.travelmate.model.Pacoteingresso;
import br.com.travelmate.model.Pacotepasseio;
import br.com.travelmate.model.Pacotes;
import br.com.travelmate.model.Pacotetransfer;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Pacotetrem;
import br.com.travelmate.model.Pais;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class TrechosMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Pacotehotel pacotehotel;
    private Pacotetrem pacotetrem;
    private Pacotetransfer pacotetransfer;
    private Pacotecruzeiro pacotecruzeiro;
    private Pacotecarro pacotecarro;
    private Pacoteingresso pacoteingresso;
    private Pacotepasseio pacotepasseio;
    private Cambio cambio;
    private List<Pacoteingresso> listaPacoteIngresso;
    private List<Pacotepasseio> listaPacotePasseio;
    private Fornecedorcidade fornecedorcidade;
    private List<Pais> listaPais;
    private Cidade cidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private Pais pais;
    private String tipoPacote;

    public TrechosMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        String tipo = (String) session.getAttribute("tipoOepracaoPacote");
        tipoPacote = (String) session.getAttribute("tipoPacote");
        session.removeAttribute("tipoOperacaoPacote");
        session.removeAttribute("tipoPacote");
        if (tipo != null) {
            PaisFacade paisFacade = new PaisFacade();
            listaPais = paisFacade.listar("");
            Pacotetrecho pacotetrecho = (Pacotetrecho) session.getAttribute("pacoteTrecho");
            session.removeAttribute("pacoteTrecho");
            int idProduto = 0;
            if (pacotetrecho!=null){
                idProduto = pacotetrecho.getPacotes().getVendas().getProdutos().getIdprodutos();
            }
            if (tipo.equalsIgnoreCase("carro")) {
                PacotesCarroFacade pacoteCarroFacade = new PacotesCarroFacade();
                pacotecarro = pacoteCarroFacade.consultar(pacotetrecho.getIdpacotetrecho());
                if (pacotecarro == null) {
                    pacotecarro = new Pacotecarro();
                    pacotecarro.setPacotetrecho(pacotetrecho);
                } else {
                    cambio = pacotecarro.getCambio();
                    fornecedorcidade = pacotecarro.getFornecedorcidade();
                }
            } else if (tipo.equalsIgnoreCase("cruzeiro")) {
                PacoteCruzeiroFacade pacoteCruzeiroFacade = new PacoteCruzeiroFacade();
                pacotecruzeiro = pacoteCruzeiroFacade.consultar(pacotetrecho.getIdpacotetrecho());
                if (pacotecruzeiro == null) {
                    pacotecruzeiro = new Pacotecruzeiro();
                    pacotecruzeiro.setPacotetrecho(pacotetrecho);
                } else {
                    cambio = pacotecruzeiro.getCambio();
                    fornecedorcidade = pacotecruzeiro.getFornecedorcidade();
                }
            } else if (tipo.equalsIgnoreCase("hotel")) {
                PacotesHotelFacade pacoteHotelFacade = new PacotesHotelFacade();
                pacotehotel = pacoteHotelFacade.consultar(pacotetrecho.getIdpacotetrecho());
                if (pacotehotel == null) {
                    pacotehotel = new Pacotehotel();
                    pacotehotel.setPacotetrecho(pacotetrecho);
                } else {
                    cambio = pacotehotel.getCambio();
                    fornecedorcidade = pacotehotel.getFornecedorcidade();
                }
            } else if (tipo.equalsIgnoreCase("ingresso")) {
                listaPacoteIngresso = pacotetrecho.getPacoteingressoList();
                pacoteingresso = new Pacoteingresso();
                pacoteingresso.setPacotetrecho(pacotetrecho);
                if (listaPacoteIngresso == null) {
                    listaPacoteIngresso = new ArrayList<Pacoteingresso>();
                } else {
                    cambio = pacoteingresso.getCambio();
                    fornecedorcidade = pacoteingresso.getFornecedorcidade();
                }
            } else if (tipo.equalsIgnoreCase("passagem")) {

            } else if (tipo.equalsIgnoreCase("passeio")) {
                pacotepasseio = new Pacotepasseio();
                pacotepasseio.setPacotetrecho(pacotetrecho);
                listaPacotePasseio = pacotetrecho.getPacotepasseioList();
                if (listaPacotePasseio == null) {
                    listaPacotePasseio = new ArrayList<Pacotepasseio>();
                } else {
                    cambio = pacotepasseio.getCambio();
                    fornecedorcidade = pacotepasseio.getFornecedorcidade();
                }
            } else if (tipo.equalsIgnoreCase("trem")) {
                PacoteTremFacade pacoteTremFacade = new PacoteTremFacade();
                pacotetrem = pacoteTremFacade.consultar(pacotetrecho.getIdpacotetrecho());
                if (pacotetrem == null) {
                    pacotetrem = new Pacotetrem();
                    pacotetrem.setPacotetrecho(pacotetrecho);
                } else {
                    cambio = pacotetrem.getCambio();
                    fornecedorcidade = pacotetrem.getFornecedorcidade();
                }
            } else if (tipo.equalsIgnoreCase("transfer")) {
                PacoteTransferFacade pacoteTransferFacade = new PacoteTransferFacade();
                pacotetransfer = pacoteTransferFacade.consultar(pacotetrecho.getIdpacotetrecho());
                if (pacotetransfer == null) {
                    pacotetransfer = new Pacotetransfer();
                    pacotetransfer.setPacotetrecho(pacotetrecho);
                } else {
                    cambio = pacotetransfer.getCambio();
                    fornecedorcidade = pacotetransfer.getFornecedorcidade();
                }
            }
            if (fornecedorcidade!=null){
                pais = fornecedorcidade.getCidade().getPais();
                cidade  = fornecedorcidade.getCidade();
                listarFornecedorCidade(idProduto);
            }else {
                fornecedorcidade = new Fornecedorcidade();
                pais = new Pais();
                cidade = new Cidade();
            }
        }
    }
    
    public Pacotecarro getPacotecarro() {
        return pacotecarro;
    }

    public void setPacotecarro(Pacotecarro pacotecarro) {
        this.pacotecarro = pacotecarro;
    }

    public Pacotehotel getPacotehotel() {
        return pacotehotel;
    }

    public void setPacotehotel(Pacotehotel pacotehotel) {
        this.pacotehotel = pacotehotel;
    }

    public Pacotetrem getPacotetrem() {
        return pacotetrem;
    }

    public void setPacotetrem(Pacotetrem pacotetrem) {
        this.pacotetrem = pacotetrem;
    }

    public Pacotetransfer getPacotetransfer() {
        return pacotetransfer;
    }

    public void setPacotetransfer(Pacotetransfer pacotetransfer) {
        this.pacotetransfer = pacotetransfer;
    }

    public Pacotecruzeiro getPacotecruzeiro() {
        return pacotecruzeiro;
    }

    public void setPacotecruzeiro(Pacotecruzeiro pacotecruzeiro) {
        this.pacotecruzeiro = pacotecruzeiro;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Pacoteingresso getPacoteingresso() {
        return pacoteingresso;
    }

    public void setPacoteingresso(Pacoteingresso pacoteingresso) {
        this.pacoteingresso = pacoteingresso;
    }

    public Pacotepasseio getPacotepasseio() {
        return pacotepasseio;
    }

    public void setPacotepasseio(Pacotepasseio pacotepasseio) {
        this.pacotepasseio = pacotepasseio;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public List<Pacoteingresso> getListaPacoteIngresso() {
        return listaPacoteIngresso;
    }

    public void setListaPacoteIngresso(List<Pacoteingresso> listaPacoteIngresso) {
        this.listaPacoteIngresso = listaPacoteIngresso;
    }

    public List<Pacotepasseio> getListaPacotePasseio() {
        return listaPacotePasseio;
    }

    public void setListaPacotePasseio(List<Pacotepasseio> listaPacotePasseio) {
        this.listaPacotePasseio = listaPacotePasseio;
    }

    public List<Pais> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Pais> listaPais) {
        this.listaPais = listaPais;
    }

    public List<Fornecedorcidade> getListaFornecedorCidade() {
        return listaFornecedorCidade;
    }

    public void setListaFornecedorCidade(List<Fornecedorcidade> listaFornecedorCidade) {
        this.listaFornecedorCidade = listaFornecedorCidade;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }


    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    public void listarFornecedorCidade(int idProduto){
        if (usuarioLogadoMB!=null){
            idProduto = usuarioLogadoMB.getParametrosprodutos().getPacotes();
        }
        if ((idProduto>0) && (cidade!=null)){
            listaFornecedorCidade = GerarListas.listarFornecedorCidade(idProduto, cidade.getIdcidade());
        }
    }
    
    public void iniciarPacoteSessao(Pacotes pacote){
        FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacote);
    }
    
    public String salvarCarro(){
        PacotesCarroFacade pacotesCarroFacade = new PacotesCarroFacade();
        pacotecarro.setFornecedorcidade(fornecedorcidade);
        pacotecarro.setCambio(cambio);
        pacotecarro = pacotesCarroFacade.salvar(pacotecarro);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        iniciarPacoteSessao(pacotecarro.getPacotetrecho().getPacotes());
        return "cadPacote";
    }
    
    
    
    
    public String salvarCruzeiro(){
        PacoteCruzeiroFacade pacoteCruzeiroFacade = new PacoteCruzeiroFacade();
        pacotecruzeiro.setFornecedorcidade(fornecedorcidade);
        pacotecruzeiro.setCambio(cambio);
        pacotecruzeiro = pacoteCruzeiroFacade.salvar(pacotecruzeiro);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "consultapacote";
    }
    
    
    
    
    
    public String salvarHotel(){
        PacotesHotelFacade pacoteHotelFacade = new PacotesHotelFacade();
        pacotehotel.setFornecedorcidade(fornecedorcidade);
        pacotehotel.setCambio(cambio);
        pacotehotel = pacoteHotelFacade.salvar(pacotehotel);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "consultapacote";
    }
    
    
    
    
    public String salvarTrem(){
        PacoteTremFacade pacoteTremFacade = new PacoteTremFacade();
        pacotetrem.setFornecedorcidade(fornecedorcidade);
        pacotetrem.setCambio(cambio);
        pacotetrem = pacoteTremFacade.salvar(pacotetrem);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "consultapacote";
    }
    
    
    
    
    public String salvarTransfer(){
        PacoteTransferFacade pacoteTransferFacade = new PacoteTransferFacade();
        pacotetransfer.setFornecedorcidade(fornecedorcidade);
        pacotetransfer.setCambio(cambio);
        pacotetransfer = pacoteTransferFacade.salvar(pacotetransfer);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "consultapacote";
    }
    
    public String salvarIngresso(){
        PacoteIngressoFacade pacoteIngressoFacade = new PacoteIngressoFacade();
        pacoteingresso.setFornecedorcidade(fornecedorcidade);
        pacoteingresso.setCambio(cambio);
        pacoteingresso = pacoteIngressoFacade.salvar(pacoteingresso);
        listaPacoteIngresso.add(pacoteingresso);
        Pacotetrecho pacotetrecho = pacoteingresso.getPacotetrecho();
        pacoteingresso = new Pacoteingresso();
        pacoteingresso.setPacotetrecho(pacotetrecho);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "";
    }
    
    public String salvarPasseio(){
        PacotePasseioFacade pacotePasseioFacade = new PacotePasseioFacade();
        pacotepasseio.setFornecedorcidade(fornecedorcidade);
        pacotepasseio.setCambio(cambio);
        pacotepasseio = pacotePasseioFacade.salvar(pacotepasseio);
        listaPacotePasseio.add(pacotepasseio);
        Pacotetrecho pacotetrecho = pacotepasseio.getPacotetrecho();
        pacotepasseio = new Pacotepasseio();
        pacotepasseio.setPacotetrecho(pacotetrecho);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "";
    }
    
    
    public String imagemAereo(Pacotetrecho pacotetrecho) {
//        if(pacoteaereo!=null){
//            return "../../resources/img/aereoverde.png";
//        }else{
            return "../../resources/img/aereovermelho.png";
    //    }
    }
    
    public String imagemCarro(Pacotetrecho pacotetrecho) {
         boolean verdade = true;
        if (pacotetrecho.getPacotecarroList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotecarroList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/carroverde.png";
        }else{
             return "../../resources/img/carrovermelho.png";
        }
    }
    
    public String imagemCruzeiro(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotecruzeiroList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotecruzeiroList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/cruzeiroverde.png";
        }else{
            return "../../resources/img/cruzeirovermelho.png";
        }
    }
    
    public String imagemHotel(Pacotetrecho pacotetrecho) {
         boolean verdade = true;
        if (pacotetrecho.getPacotehotelList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotehotelList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/hotelverde.png";
        } else {
            return "../../resources/img/hotelvermelho.png";
        }

    }
    
    public String imagemIngresso(Pacotetrecho pacotetrecho) {
         boolean verdade = true;
        if (pacotetrecho.getPacoteingressoList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacoteingressoList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/ingressoverde.png";
        }else{
            return "../../resources/img/ingressovermelho.png";
        }
    }
    
    public String imagemPasseio(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotepasseioList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotepasseioList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/passeioverdeb.png";
        }else{
             return "../../resources/img/passeiovermelhob.png";
         }
    }
    
    public String imagemTransfer(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotetransferList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotetransferList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/tranferverde.png";
        }else{
             return "../../resources/img/transfervermelho.png";
        }
    }
    
    public String imagemTrem(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotetremList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotetremList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/tremverde.png";
        }else{
              return "../../resources/img/tremvermelho.png";
         }
    }
}







