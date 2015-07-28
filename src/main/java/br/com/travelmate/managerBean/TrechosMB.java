package br.com.travelmate.managerBean;

import br.com.travelmate.facade.PacoteCruzeiroFacade;
import br.com.travelmate.facade.PacoteIngressoFacade;
import br.com.travelmate.facade.PacotePasseioFacade;
import br.com.travelmate.facade.PacoteTransferFacade;
import br.com.travelmate.facade.PacoteTremFacade;
import br.com.travelmate.facade.PacotesCarroFacade;
import br.com.travelmate.facade.PacotesHotelFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotecarro;
import br.com.travelmate.model.Pacotecruzeiro;
import br.com.travelmate.model.Pacotehotel;
import br.com.travelmate.model.Pacoteingresso;
import br.com.travelmate.model.Pacotepasseio;
import br.com.travelmate.model.Pacotetransfer;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Pacotetrem;
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
    @Inject
    private ConsultaFornecedorMB consultaFornecedorMB;
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

    public TrechosMB() {
        FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        String tipo = (String) session.getAttribute("tipoOepracaoPacote");
        if (tipo!=null){
        Pacotetrecho pacotetrecho = (Pacotetrecho) session.getAttribute("pacoteTrecho");
        if (tipo.equalsIgnoreCase("carro")) {
            PacotesCarroFacade pacoteCarroFacade = new PacotesCarroFacade();
            pacotecarro = pacoteCarroFacade.consultar(pacotetrecho.getIdpacotetrecho());
            if (pacotecarro == null) {
                pacotecarro = new Pacotecarro();
                pacotecarro.setPacotetrecho(pacotetrecho);
            }
        } else if (tipo.equalsIgnoreCase("cruzeiro")) {
            PacoteCruzeiroFacade pacoteCruzeiroFacade = new PacoteCruzeiroFacade();
            pacotecruzeiro = pacoteCruzeiroFacade.consultar(pacotetrecho.getIdpacotetrecho());
            if(pacotecruzeiro==null){
                pacotecruzeiro = new Pacotecruzeiro();
                pacotecruzeiro.setPacotetrecho(pacotetrecho);
            }
        }else if (tipo.equalsIgnoreCase("hotel")){
            PacotesHotelFacade pacoteHotelFacade = new PacotesHotelFacade();
            pacotehotel = pacoteHotelFacade.consultar(pacotetrecho.getIdpacotetrecho());
            if(pacotehotel==null){
                pacotehotel = new Pacotehotel();
                pacotehotel.setPacotetrecho(pacotetrecho);
            }
        }else if (tipo.equalsIgnoreCase("ingresso")){
            listaPacoteIngresso = pacotetrecho.getPacoteingressoList();
            pacoteingresso = new Pacoteingresso();
            pacoteingresso.setPacotetrecho(pacotetrecho);
            if (listaPacoteIngresso==null){
                listaPacoteIngresso = new ArrayList<Pacoteingresso>();
            }
        }else if (tipo.equalsIgnoreCase("passagem")){
            
        }else if (tipo.equalsIgnoreCase("passeio")){
            pacotepasseio=new Pacotepasseio();
            pacotepasseio.setPacotetrecho(pacotetrecho);
            listaPacotePasseio = pacotetrecho.getPacotepasseioList();
            if (listaPacotePasseio==null){
                listaPacotePasseio = new ArrayList<Pacotepasseio>();
            }
        }else if (tipo.equalsIgnoreCase("trem")){
            PacoteTremFacade pacoteTremFacade = new PacoteTremFacade();
            pacotetrem = pacoteTremFacade.consultar(pacotetrecho.getIdpacotetrecho());
            if(pacotetrem==null){
                pacotetrem = new Pacotetrem();
                pacotetrem.setPacotetrecho(pacotetrecho);
            }
        }else if (tipo.equalsIgnoreCase("transfer")){
            PacoteTransferFacade pacoteTransferFacade = new PacoteTransferFacade();
            pacotetransfer = pacoteTransferFacade.consultar(pacotetrecho.getIdpacotetrecho());
            if(pacotetransfer==null){
                pacotetransfer = new Pacotetransfer();
                pacotetransfer.setPacotetrecho(pacotetrecho);
            }
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

    public ConsultaFornecedorMB getConsultaFornecedorMB() {
        return consultaFornecedorMB;
    }

    public void setConsultaFornecedorMB(ConsultaFornecedorMB consultaFornecedorMB) {
        this.consultaFornecedorMB = consultaFornecedorMB;
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
    
    
    
    
    
    public String salvarCarro(){
        PacotesCarroFacade pacotesCarroFacade = new PacotesCarroFacade();
        pacotecarro.setFornecedorcidade(consultaFornecedorMB.getFornecedorcidade());
        pacotecarro = pacotesCarroFacade.salvar(pacotecarro);
        consultaFornecedorMB.setFornecedorcidade(new Fornecedorcidade());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "consultapacote";
    }
    
    
    
    
    public String salvarCruzeiro(){
        PacoteCruzeiroFacade pacoteCruzeiroFacade = new PacoteCruzeiroFacade();
        pacotecruzeiro.setFornecedorcidade(consultaFornecedorMB.getFornecedorcidade());
        pacotecruzeiro = pacoteCruzeiroFacade.salvar(pacotecruzeiro);
        consultaFornecedorMB.setFornecedorcidade(new Fornecedorcidade());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "consultapacote";
    }
    
    
    
    
    
    public String salvarHotel(){
        PacotesHotelFacade pacoteHotelFacade = new PacotesHotelFacade();
        pacotehotel.setFornecedorcidade(consultaFornecedorMB.getFornecedorcidade());
        pacotehotel = pacoteHotelFacade.salvar(pacotehotel);
        consultaFornecedorMB.setFornecedorcidade(new Fornecedorcidade());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "consultapacote";
    }
    
    
    
    
    public String salvarTrem(){
        PacoteTremFacade pacoteTremFacade = new PacoteTremFacade();
        pacotetrem.setFornecedorcidade(consultaFornecedorMB.getFornecedorcidade());
        pacotetrem = pacoteTremFacade.salvar(pacotetrem);
        consultaFornecedorMB.setFornecedorcidade(new Fornecedorcidade());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "consultapacote";
    }
    
    
    
    
    public String salvarTransfer(){
        PacoteTransferFacade pacoteTransferFacade = new PacoteTransferFacade();
        pacotetransfer.setFornecedorcidade(consultaFornecedorMB.getFornecedorcidade());
        pacotetransfer = pacoteTransferFacade.salvar(pacotetransfer);
        consultaFornecedorMB.setFornecedorcidade(new Fornecedorcidade());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "consultapacote";
    }
    
    public String salvarIngresso(){
        PacoteIngressoFacade pacoteIngressoFacade = new PacoteIngressoFacade();
        pacoteingresso.setFornecedorcidade(consultaFornecedorMB.getFornecedorcidade());
        pacoteingresso = pacoteIngressoFacade.salvar(pacoteingresso);
        listaPacoteIngresso.add(pacoteingresso);
        Pacotetrecho pacotetrecho = pacoteingresso.getPacotetrecho();
        pacoteingresso = new Pacoteingresso();
        pacoteingresso.setPacotetrecho(pacotetrecho);
        consultaFornecedorMB.setFornecedorcidade(new Fornecedorcidade());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "";
    }
    
    public String salvarPasseio(){
        PacotePasseioFacade pacotePasseioFacade = new PacotePasseioFacade();
        pacotepasseio.setFornecedorcidade(consultaFornecedorMB.getFornecedorcidade());
        pacotepasseio = pacotePasseioFacade.salvar(pacotepasseio);
        listaPacotePasseio.add(pacotepasseio);
        Pacotetrecho pacotetrecho = pacotepasseio.getPacotetrecho();
        pacotepasseio = new Pacotepasseio();
        pacotepasseio.setPacotetrecho(pacotetrecho);
        consultaFornecedorMB.setFornecedorcidade(new Fornecedorcidade());
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