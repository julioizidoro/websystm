/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.bean.FinalizarPacoteOperadora;
import br.com.travelmate.facade.ClienteFacade;
import br.com.travelmate.facade.FormaPagamentoFacade;
import br.com.travelmate.facade.FornecedorCidadeFacade;
import br.com.travelmate.facade.PacoteTrechoFacade;
import br.com.travelmate.facade.PacotesFacade;
import br.com.travelmate.facade.ParcelamentoPagamentoFacade;
import br.com.travelmate.facade.ProdutoFacade;
import br.com.travelmate.facade.VendasFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Cliente;
import br.com.travelmate.model.Contaspagar;
import br.com.travelmate.model.Formapagamento;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotes;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Parcelamentopagamento;
import br.com.travelmate.model.Produtos;
import br.com.travelmate.model.Unidadenegocio;
import br.com.travelmate.model.Vendas;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class CadPacoteAgenciaMB implements Serializable {

    private Pacotes pacotes;
    private List<Unidadenegocio> listaUnidadeNegocio;
    private List<Pacotetrecho> listaTrecho;
    private Pacotetrecho pacotetrecho;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Cambio cambio;
    private boolean btniniciar = false;
    private boolean btnfinalizar = true;
    private Cliente cliente;
    private List<Cliente> listaCliente;
    private Formapagamento formapagamento;
    private Parcelamentopagamento parcelamentopagamento;
    private List<Parcelamentopagamento> listaParcelamento;
    private float saldoParcelar;
    private Vendas vendass;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        pacotes = (Pacotes) session.getAttribute("pacote");
        session.removeAttribute("pacote");
        if (this.pacotes == null) {
            pacotes = new Pacotes();
            listaTrecho = new ArrayList<Pacotetrecho>();
            formapagamento = new Formapagamento();
        } else {
            cambio = pacotes.getCambio();
            cliente = pacotes.getCliente();
            listaTrecho = pacotes.getPacotetrechoList();
        }
        pacotetrecho = new Pacotetrecho();
        listaUnidadeNegocio = GerarListas.listarUnidade();
        parcelamentopagamento = new Parcelamentopagamento();
        
        vendass = new Vendas();
    }

    public Pacotes getPacotes() {
        return pacotes;
    }

    public void setPacotes(Pacotes pacotes) {
        this.pacotes = pacotes;
    }

    public List<Unidadenegocio> getListaUnidadeNegocio() {
        return listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<Unidadenegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
    }

    public List<Pacotetrecho> getListaTrecho() {
        return listaTrecho;
    }

    public void setListaTrecho(List<Pacotetrecho> listaTrecho) {
        this.listaTrecho = listaTrecho;
    }

    public Pacotetrecho getPacotetrecho() {
        return pacotetrecho;
    }

    public void setPacotetrecho(Pacotetrecho pacotetrecho) {
        this.pacotetrecho = pacotetrecho;
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

    public boolean isBtniniciar() {
        return btniniciar;
    }

    public void setBtniniciar(boolean btniniciar) {
        this.btniniciar = btniniciar;
    }

    public boolean isBtnfinalizar() {
        return btnfinalizar;
    }

    public void setBtnfinalizar(boolean btnfinalizar) {
        this.btnfinalizar = btnfinalizar;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaCliente() {
        if(listaCliente==null){
            gerarListaCliente();
        }
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
    public Formapagamento getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(Formapagamento formapagamento) {
        this.formapagamento = formapagamento;
    }

    public Parcelamentopagamento getParcelamentopagamento() {
        return parcelamentopagamento;
    }

    public void setParcelamentopagamento(Parcelamentopagamento parcelamentopagamento) {
        this.parcelamentopagamento = parcelamentopagamento;
    }

    public List<Parcelamentopagamento> getListaParcelamento() {
        return listaParcelamento;
    }

    public void setListaParcelamento(List<Parcelamentopagamento> listaParcelamento) {
        this.listaParcelamento = listaParcelamento;
    }

    public float getSaldoParcelar() {
        return saldoParcelar;
    }

    public void setSaldoParcelar(float saldoParcelar) {
        this.saldoParcelar = saldoParcelar;
    }

    public Vendas getVendas() {
        return vendass;
    }

    public void setVendas(Vendas vendass) {
        this.vendass = vendass;
    }
    
    
    
    public void gerarListaCliente(){
        ClienteFacade clienteFacade = new ClienteFacade();
        listaCliente = clienteFacade.listar("select c from Cliente c where c.unidadenegocio.idunidadeNegocio="
                +usuarioLogadoMB.getUsuario().getUnidadenegocio().getIdunidadeNegocio()+
                " order by c.nome");
        if (listaCliente==null){
         listaCliente = new ArrayList<Cliente>();
        }
    }
    

    public String iniciarPacote() {
        if (pacotes.getVendas() == null) {
            FornecedorCidadeFacade fornecedorCidadeFacade = new FornecedorCidadeFacade();
            Fornecedorcidade fornecedorcidade = fornecedorCidadeFacade.getFornecedorCidade(usuarioLogadoMB.getParametrosprodutos().getFornecedorpacote());
            ProdutoFacade produtoFacade = new ProdutoFacade();
            Produtos produto = produtoFacade.consultar(usuarioLogadoMB.getParametrosprodutos().getPacotes());
            ClienteFacade clienteFacade = new ClienteFacade();
            Cliente cliente = clienteFacade.consultar(usuarioLogadoMB.getParametrosprodutos().getClientepacote());
            vendass.setCliente(cliente);
            vendass.setDataVenda(new Date());
            vendass.setFornecedor(fornecedorcidade.getFornecedor());
            vendass.setFornecedorcidade(fornecedorcidade);
            vendass.setProdutos(produto);
            vendass.setSituacao("Processo");
            vendass.setUnidadenegocio(usuarioLogadoMB.getUsuario().getUnidadenegocio());
            vendass.setUsuario(usuarioLogadoMB.getUsuario());
            vendass.setValor(0.0f);
            vendass.setVendasMatriz("S");
            vendass.setVendaimportada(0);
            vendass.setObsCancelar("");
            vendass.setUsuariocancelamento(0);
            vendass.setObstm("");
            VendasFacade vendasFacade = new VendasFacade();
            vendass = vendasFacade.salvar(vendass);
            pacotes.setVendas(vendass);
            formapagamento.setVendas(vendass);
            FormaPagamentoFacade formaPagamentoFacade = new FormaPagamentoFacade();
            formapagamento = formaPagamentoFacade.salvar(formapagamento);
        }
        
        pacotes.setOperacao("agencia");
        pacotes.setUsuario(usuarioLogadoMB.getUsuario());
        pacotes.setCambio(cambio);
        pacotes.setCliente(pacotes.getVendas().getCliente());
        pacotes.setUnidadenegocio(usuarioLogadoMB.getUsuario().getUnidadenegocio());
        PacotesFacade pacotesFacade = new PacotesFacade();
        pacotes = pacotesFacade.salvar(pacotes);
        return "";
    }

    public String adicionarTrecho() {
        pacotetrecho.setPacotes(pacotes);
        PacoteTrechoFacade pacoteTrechoFacade = new PacoteTrechoFacade();
        pacoteTrechoFacade.salvar(pacotetrecho);
        String sql = "Select p from Pacotetrecho p where p.pacotes.idpacotes=" + pacotes.getIdpacotes();
        listaTrecho = GerarListas.listarPacoteTrecho(sql);
        pacotetrecho = new Pacotetrecho();
        return "";
    }

    public String finalizar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.removeAttribute("tipoOepracaoPacote");
        session.removeAttribute("pacoteTrecho");
        FinalizarPacoteOperadora finalizarPacoteOperadora = new FinalizarPacoteOperadora(listaTrecho, cambio);
        pacotes.setValorgross(finalizarPacoteOperadora.getValorGross());
        pacotes.setValornet(finalizarPacoteOperadora.getValorNet());
        pacotes.setValormoedanacional(pacotes.getValorgross() * pacotes.getCambio().getValor());
        pacotes.setComissaoloja(finalizarPacoteOperadora.getComissaoloja());
        Double valor = pacotes.getValorgross().doubleValue() - pacotes.getValornet().doubleValue();
        pacotes.setComissao(valor);
        PacotesFacade pacotesFacade = new PacotesFacade();
        pacotes = pacotesFacade.salvar(pacotes);
        return null;
    }

    public String novoCarro(Pacotetrecho pacotetrecho) {
        if (pacotetrecho != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("pacoteTrecho", pacotetrecho);
            Map<String,Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 700);
            RequestContext.getCurrentInstance().openDialog("pacotecarro", options, null);
        } else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }

    public String novoCruzeiro(Pacotetrecho pacotetrecho) {
        if (pacotetrecho != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("pacoteTrecho", pacotetrecho);
            // return "pacotecruzeiro";
            RequestContext.getCurrentInstance().openDialog("pacotecruzeiro");
        } else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }

    public String novoHotel(Pacotetrecho pacotetrecho) {
        if (pacotetrecho != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("pacoteTrecho", pacotetrecho);
            Map<String,Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 700);
            RequestContext.getCurrentInstance().openDialog("pacotehotel", options, null);
            return "";
        } else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }

    public String novoTrem(Pacotetrecho pacotetrecho) {
        if (pacotetrecho != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("pacoteTrecho", pacotetrecho);
            //return "pacotetrem";
            RequestContext.getCurrentInstance().openDialog("pacotetrem");
            return "";
        } else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }

    public String novoTransfer(Pacotetrecho pacotetrecho) {
        if (pacotetrecho != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("pacoteTrecho", pacotetrecho);
            // return "pacotetransfer";
            RequestContext.getCurrentInstance().openDialog("pacotetransfer");
            return "";
        } else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }

    public String novoIngresso(Pacotetrecho pacotetrecho) {
        if (pacotetrecho != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("pacoteTrecho", pacotetrecho);
            // return "pacoteingresso";
            RequestContext.getCurrentInstance().openDialog("pacoteingresso");
            return "";
        } else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }

    public String novoPasseio(Pacotetrecho pacotetrecho) {
        if (pacotetrecho != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("pacoteTrecho", pacotetrecho);
            // return "pacotepasseio";
            RequestContext.getCurrentInstance().openDialog("pacotepasseio");
            return "";
        } else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }

    public String novoSeguro() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("pacote", pacotes);
        RequestContext.getCurrentInstance().openDialog("pacoteseguro");
        return "";
    }

    public String novoPassagem(Pacotetrecho pacotetrecho) {
        if (pacotetrecho != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("pacoteTrecho", pacotetrecho);
            //return "pacoteaereo";
            RequestContext.getCurrentInstance().openDialog("pacoteaereo");
            return "";
        } else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }

    public String imagemAereo(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotepassagemList() == null) {
            verdade = false;
        } else if (pacotetrecho.getPacotepassagemList().size() == 0) {
            verdade = false;
        }
        if (verdade) {
            return "../../resources/img/aereoverde.png";
        } else {
            return "../../resources/img/aereovermelho.png";
        }
    }

    public String imagemCarro(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotecarroList() == null) {
            verdade = false;
        } else if (pacotetrecho.getPacotecarroList().size() == 0) {
            verdade = false;
        }
        if (verdade) {
            return "../../resources/img/carroverde.png";
        } else {
            return "../../resources/img/carrovermelho.png";
        }
    }

    public String imagemCruzeiro(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotecruzeiroList() == null) {
            verdade = false;
        } else if (pacotetrecho.getPacotecruzeiroList().size() == 0) {
            verdade = false;
        }
        if (verdade) {
            return "../../resources/img/cruzeiroverde.png";
        } else {
            return "../../resources/img/cruzeirovermelho.png";
        }
    }

    public String imagemHotel(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotehotelList() == null) {
            verdade = false;
        } else if (pacotetrecho.getPacotehotelList().size() == 0) {
            verdade = false;
        }
        if (verdade) {
            return "../../resources/img/hotelverde.png";
        } else {
            return "../../resources/img/hotelvermelho.png";
        }

    }

    public String imagemIngresso(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacoteingressoList() == null) {
            verdade = false;
        } else if (pacotetrecho.getPacoteingressoList().size() == 0) {
            verdade = false;
        }
        if (verdade) {
            return "../../resources/img/ingressoverde.png";
        } else {
            return "../../resources/img/ingressovermelho.png";
        }
    }

    public String imagemPasseio(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotepasseioList() == null) {
            verdade = false;
        } else if (pacotetrecho.getPacotepasseioList().size() == 0) {
            verdade = false;
        }
        if (verdade) {
            return "../../resources/img/passeioverdeb.png";
        } else {
            return "../../resources/img/passeiovermelhob.png";
        }
    }

    public String imagemTransfer(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotetransferList() == null) {
            verdade = false;
        } else if (pacotetrecho.getPacotetransferList().size() == 0) {
            verdade = false;
        }
        if (verdade) {
            return "../../resources/img/tranferverde.png";
        } else {
            return "../../resources/img/transfervermelho.png";
        }
    }

    public String imagemTrem(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotetremList() == null) {
            verdade = false;
        } else if (pacotetrecho.getPacotetremList().size() == 0) {
            verdade = false;
        }
        if (verdade) {
            return "../../resources/img/tremverde.png";
        } else {
            return "../../resources/img/tremvermelho.png";
        }
    }

    public String imagemSeguro(Pacotetrecho pacotetrecho) {

        return "../../resources/img/segurovermelho.png";
    }

    public void verificarBotoes() {
        if (btniniciar) {
            btniniciar = false;
            btnfinalizar = true;
        } else {
            btniniciar = true;
            btnfinalizar = false;
        }
    }
    
    public String importarPacote(){
        RequestContext.getCurrentInstance().openDialog("importarPacoteOperadora");
        return "";
    }
    
    public void retornoDialogNovo(SelectEvent event){
       Pacotes pacote = (Pacotes) event.getObject();
       setPacotes(pacote);
       listaTrecho = pacote.getPacotetrechoList();
       cambio = pacote.getCambio();
       
   }
    
    public void calcularSaldoParcelar() {
        Float saldo = formapagamento.getValorTotal() + formapagamento.getValorJuros();
        saldoParcelar = saldo;
    }
    
    public void calcularParcelamentoPagamento() {
        if (listaParcelamento != null) {
            Float valorParcelado = 0.0f;
            for (int i = 0; i < listaParcelamento.size(); i++) {
                valorParcelado = valorParcelado + listaParcelamento.get(i).getValorParcelamento();
            }
            Float saldo = (formapagamento.getValorTotal() + formapagamento.getValorJuros()) - valorParcelado;
            saldoParcelar = saldo;
        }
    }
    
    public String validarFormaPagamento(){
        String msg = "";
        Date data=null;
        if (parcelamentopagamento.getDiaVencimento().equals(data)){
            msg = msg + "Data primeiro vencimento obrigatória";
        }
        if (parcelamentopagamento.getFormaPagamento().equalsIgnoreCase(null)){
            msg = msg + "Campo forma de pagamento obrigatório";
        }
        return msg;
    }
    
    public void carregarListaParcelamento(){
        List<Parcelamentopagamento> listanova=null;
        listanova = listaParcelamento;
        if (formapagamento != null) {
            ParcelamentoPagamentoFacade parcalamentoPagamentoFacade = new ParcelamentoPagamentoFacade();
            listaParcelamento = parcalamentoPagamentoFacade.listar(formapagamento.getIdformaPagamento());
            if (listaParcelamento == null) {
                listaParcelamento = new ArrayList<Parcelamentopagamento>();
            }
        }else {
                listaParcelamento = new ArrayList<Parcelamentopagamento>();
        }
        if (listanova != null) {
            for (int i = 0; i < listanova.size(); i++) {
                if (listanova.get(i).getIdparcemlamentoPagamento() == null) {
                    listaParcelamento.add(listanova.get(i));
                }
            }
        }
    }
    
    public String adicionarParcelamento(){
        if(formapagamento!=null){
            parcelamentopagamento.setFormapagamento(formapagamento);
            ParcelamentoPagamentoFacade parcelamentoPagamentoFacade = new ParcelamentoPagamentoFacade();
            parcelamentopagamento = parcelamentoPagamentoFacade.salvar(parcelamentopagamento);
        }else{
            FacesMessage msg = new FacesMessage("Atenção! ", "Primeiramente Inície o pacote.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "";
    }
    
    public String salvar(){
        PacotesFacade pacotesFacade = new PacotesFacade();
        pacotes = pacotesFacade.salvar(pacotes);
        return "consultapacote";
    }
    
    public String cancelar(){
        return "consultapacote";
    }
    
    public void retornoDialogNovo() {
        imagemAereo(pacotetrecho);
        imagemCarro(pacotetrecho);
        imagemCruzeiro(pacotetrecho);
        imagemHotel(pacotetrecho);
        imagemIngresso(pacotetrecho);
        imagemPasseio(pacotetrecho);
        imagemSeguro(pacotetrecho);
        imagemTransfer(pacotetrecho);
        imagemTrem(pacotetrecho);
    }
}
