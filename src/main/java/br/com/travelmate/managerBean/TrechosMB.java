package br.com.travelmate.managerBean;

import br.com.travelmate.facade.PacoteCruzeiroFacade;
import br.com.travelmate.facade.PacoteTransferFacade;
import br.com.travelmate.facade.PacoteTremFacade;
import br.com.travelmate.facade.PacotesCarroFacade;
import br.com.travelmate.facade.PacotesHotelFacade;
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
    
    
    
    public String novoCarro(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            PacotesCarroFacade pacoteCarroFacade = new PacotesCarroFacade();
            pacotecarro = pacoteCarroFacade.consultar(pacotetrecho.getIdpacotetrecho());
            if(pacotecarro==null){
                pacotecarro = new Pacotecarro();
                pacotecarro.setPacotetrecho(pacotetrecho);
                return "pacoteCarro";
            }
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
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
    
    
    public String novoCruzeiro(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            PacoteCruzeiroFacade pacoteCruzeiroFacade = new PacoteCruzeiroFacade();
            pacotecruzeiro = pacoteCruzeiroFacade.consultar(pacotetrecho.getIdpacotetrecho());
            if(pacotecruzeiro==null){
                pacotecruzeiro = new Pacotecruzeiro();
                pacotecruzeiro.setPacotetrecho(pacotetrecho);
                return "pacoteCruzeiro";
            }
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
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
    
    
    
    public String novoHotel(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            PacotesHotelFacade pacoteHotelFacade = new PacotesHotelFacade();
            pacotehotel = pacoteHotelFacade.consultar(pacotetrecho.getIdpacotetrecho());
            if(pacotehotel==null){
                pacotehotel = new Pacotehotel();
                pacotehotel.setPacotetrecho(pacotetrecho);
                return "pacoteHotel";
            }
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
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
    
    
    public void novoTrem(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            PacoteTremFacade pacoteTremFacade = new PacoteTremFacade();
            pacotetrem = pacoteTremFacade.consultar(pacotetrecho.getIdpacotetrecho());
            if(pacotetrem==null){
                pacotetrem = new Pacotetrem();
                pacotetrem.setPacotetrecho(pacotetrecho);
            }
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
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
    
    
    public void novoTransfer(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            PacoteTransferFacade pacoteTransferFacade = new PacoteTransferFacade();
            pacotetransfer = pacoteTransferFacade.consultar(pacotetrecho.getIdpacotetrecho());
            if(pacotetransfer==null){
                pacotetransfer = new Pacotetransfer();
                pacotetransfer.setPacotetrecho(pacotetrecho);
            }
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
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
}