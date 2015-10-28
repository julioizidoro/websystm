/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.bean.LerRetornoItauBean;
import br.com.travelmate.facade.CobrancaFacade;
import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.facade.HistoricoCobrancaFacade;
import br.com.travelmate.model.Cobranca;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.model.Historicocobranca;
import br.com.travelmate.model.Vendas;
import br.com.travelmate.util.Formatacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Wolverine
 */

@Named
@ViewScoped
public class ContasReceberMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Contasreceber> listaContas;
    private Contasreceber conta;
    private Float contasVencidas;	
    private Float contasVencer;
    private Float contasVencendo;
    private Vendas vendas;
    private Date dataAnterior;
    
    
    @PostConstruct
    public void init(){
       FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        conta = (Contasreceber) session.getAttribute("contarecebe");
        session.removeAttribute("contarecebe");
        String sql = "Select c from Contasreceber c where c.valorpago=0 order by c.datavencimento, c.vendas.cliente.nome";
        carregarContasReceber(sql);
        conta = new Contasreceber();
       
       
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public List<Contasreceber> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<Contasreceber> listaContas) {
        this.listaContas = listaContas;
    }

    public Contasreceber getConta() {
        return conta;
    }

    public void setConta(Contasreceber conta) {
        this.conta = conta;
    }

    public Float getContasVencidas() {
        return contasVencidas;
    }

    public void setContasVencidas(Float contasVencidas) {
        this.contasVencidas = contasVencidas;
    }

    public Float getContasVencer() {
        return contasVencer;
    }

    public void setContasVencer(Float contasVencer) {
        this.contasVencer = contasVencer;
    }

    public Float getContasVencendo() {
        return contasVencendo;
    }

    public void setContasVencendo(Float contasVencendo) {
        this.contasVencendo = contasVencendo;
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }
    
    public void carregarContasReceber(String sql){
        ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
        listaContas = contasReceberFacade.listar(sql);
        if (listaContas==null){
            listaContas = new ArrayList<Contasreceber>();
        }
    }
    
    
    
    public String iniciarCobranca(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("venda", conta.getVendas());
        return "cobranca";
    }
    
    public String recebimento(){
        List<Contasreceber> lista = new ArrayList<Contasreceber>();
        for(int i=0;i<listaContas.size();i++){
            if (listaContas.get(i).isSelecionado()){
                lista.add(listaContas.get(i));
            }
        }
        if (lista.size()>0){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
            session.setAttribute("listarecebimento", lista);
            return "recebimento";
        }
        return " ";
    }
    
    public void calcularTotais(){
        contasVencendo=0.0f;
        contasVencer=0.0f;
        contasVencidas=0.0f;
        Date data = new Date();
        String sData = Formatacao.ConvercaoDataPadrao(data);
        data = Formatacao.ConvercaoStringDataBrasil(sData);
        for(int i=0;i<listaContas.size();i++){
            if (listaContas.get(i).getDatavencimento().equals(data)){
                contasVencendo = contasVencendo + listaContas.get(i).getValorparcela();
            }else if (listaContas.get(i).getDatavencimento().before(data)) {
                contasVencidas = contasVencidas + listaContas.get(i).getValorparcela();
            } else if (listaContas.get(i).getDatavencimento().after(new Date())) {
                contasVencer = contasVencer + listaContas.get(i).getValorparcela();
            }
        }
    }
    public String visualizar(Vendas vendas){
        this.vendas = vendas;
        return null;
    }
    public String voltar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "consContasReceber";
    }
    
    public String voltarRecimento(){
        return "consContasReceber";
    }
    
    public String editar(){
        if (this.conta!=null){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("contareceber", conta);       
            RequestContext.getCurrentInstance().openDialog("adicionarContasReceber");
        }
        return "";
    }
    
    
    public String cobranca(){
        return "cobranca";
    }
    
    public String dialogBoletos() {
        List<Contasreceber> lista = new ArrayList<>();
        for (int i = 0; i < listaContas.size(); i++) {
            if ((listaContas.get(i).isSelecionado()) && (listaContas.get(i).getTipodocumento().equalsIgnoreCase("Boleto"))) {
                if ((listaContas.get(i).getDataalterada()) && (!listaContas.get(i).getBoletoenviado())) {
                    lista.add(listaContas.get(i));
                } else {
                    if ((listaContas.get(i).getBoletocancelado()) && (!listaContas.get(i).getBoletoenviado())) {
                        lista.add(listaContas.get(i));
                    } else if (!listaContas.get(i).getBoletoenviado()) {
                        lista.add(listaContas.get(i));
                    }
                }
            }
        }
        if (lista.size() == 0) {
            lista = null;
        }
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("listaContas", lista);
        RequestContext.getCurrentInstance().openDialog("boletos");
        return "";
    }
    
   
    
    
    public String retornarBoletoGerado(Contasreceber conta){
        String retorno;
        if(conta.getBoletogerado().equalsIgnoreCase("SIM")){
            retorno = "../../resources/img/bolaVerde.png";
        }else if(conta.getBoletogerado().equalsIgnoreCase("NAO")){
            retorno = "../../resources/img/bolaVermelha.png";
        }else {
            retorno = "../../resources/img/bolaAmarela.png";
        }
        return retorno;
    }
    
    public String retornarStatusConta(Contasreceber conta){
        String retorno;
        Date data = new Date();
        String dataPadrao = Formatacao.ConvercaoDataPadrao(data);
        Date dataNova = Formatacao.ConvercaoStringData(dataPadrao);
        if(conta.getDatavencimento().equals(data)){
            retorno = "../../resources/img/bolaAmarela.png";
        }else if(conta.getDatavencimento().before(data)){
            retorno = "../../resources/img/bolaVermelha.png";
        }else {
            retorno = "../../resources/img/bolaVerde.png";
        }
        return retorno;
    }
    
    public String retornarTitleStatusConta(Contasreceber conta){
        String retorno;
        Date data = new Date();
        String dataPadrao = Formatacao.ConvercaoDataPadrao(data);
        Date dataNova = Formatacao.ConvercaoStringData(dataPadrao);
        if(conta.getDatavencimento().equals(dataNova)){
            retorno = "A vencer";
        }else if(conta.getDatavencimento().before(dataNova)){
            retorno = "Vencido";
        }else {
            retorno = "Recebido";
        }
        return retorno;
    }
    
    public String retornarTipoDocumento(Contasreceber conta){
        String retorno;
        if(conta.getTipodocumento().equalsIgnoreCase("Dinheiro")){
            retorno = "../../resources/img/dinheiros.png";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Boleto")){
            retorno = "../../resources/img/boleto.png";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Cartão de Crédito")){
            retorno = "../../resources/img/credito.png";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Cartão de Crédito Autorizado")){
            retorno = "../../resources/img/creditoautorizado.png";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Cartão de Débito")){
            retorno = "../../resources/img/debito.png";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Cheque")){
            retorno = "../../resources/img/holerite.png";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Deposito")){
            retorno = "../../resources/img/deposito.png";
        }else{
            retorno = "../../resources/img/financiamento.png";
        }
        return retorno;
    }
    
    public String titleTipoDocumento(Contasreceber conta){
        String retorno;
        if(conta.getTipodocumento().equalsIgnoreCase("Dinheiro")){
            retorno = "Dinheiro";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Boleto")){
            retorno = "Boleto";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Cartão de Crédito")){
            retorno = "Cartão de Crédito";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Cartão de Crédito Autorizado")){
            retorno = "Cartão de Crédito Autorizado";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Cartão de Débito")){
            retorno = "Cartão de Débito";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Cheque")){
            retorno = "Cheque";
        }else if(conta.getTipodocumento().equalsIgnoreCase("Deposito")){
            retorno = "Deposito";
        }else{
            retorno = "Financiamento Banco";
        }
        return retorno;
    }
    
     public void uploadRetorno(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Sucesso! ", event.getFile().getFileName() + " carregado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        UploadedFile uFile = event.getFile();
        lerRetorno(uFile);
    }

    public String lerRetorno(UploadedFile retorno) {
        try {
            LerRetornoItauBean lerRetornoItauBean = new LerRetornoItauBean(Formatacao.converterUploadedFileToFile(retorno));
        } catch (Exception ex) {
            Logger.getLogger(ContasReceberMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void cancelado(Contasreceber contasreceber){
        if (contasreceber.getBoletoenviado()) {
            contasreceber.setBoletoenviado(Boolean.FALSE);
            contasreceber.setBoletocancelado(Boolean.TRUE);
            ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
            contasReceberFacade.salvar(contasreceber);
            String sql = "Select c from Cobranca c where c.vendas.idvendas=" + contasreceber.getVendas().getIdvendas();
            CobrancaFacade cobrancaFacade = new CobrancaFacade();
            Cobranca cobranca = cobrancaFacade.consultar(sql);
            if (cobranca == null) {
                cobranca = new Cobranca();
                cobranca.setVendas(contasreceber.getVendas());
                cobranca = cobrancaFacade.salvar(cobranca);
            }
            Historicocobranca historicocobranca = new Historicocobranca();
            historicocobranca.setAssunto("Boleto Cancelado por " + usuarioLogadoMB.getUsuario().getNome());
            historicocobranca.setCobranca(cobranca);
            historicocobranca.setContato("Sistema");
            historicocobranca.setData(new Date());
            historicocobranca.setUsuario(usuarioLogadoMB.getUsuario());
            HistoricoCobrancaFacade historicocobrancaFacade = new HistoricoCobrancaFacade();
            historicocobrancaFacade.salvar(historicocobranca);
            FacesMessage msg = new FacesMessage("Sucesso! ", "Boleto cancelado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            FacesMessage msg = new FacesMessage("Erro! ", "Boleto não foi enviado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public String confirmaAlterarDataVencimento(){
        if (conta.getBoletoenviado()) {
            conta.setDataalterada(Boolean.TRUE);
            conta.setBoletoenviado(Boolean.FALSE);
        }
        ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
        contasReceberFacade.salvar(conta);
        String sql = "Select c from Cobranca c where c.vendas.idvendas=" + conta.getVendas().getIdvendas();
        CobrancaFacade cobrancaFacade = new CobrancaFacade();
        Cobranca cobranca = cobrancaFacade.consultar(sql);
        if (cobranca == null) {
            cobranca = new Cobranca();
            cobranca.setVendas(conta.getVendas());
            cobranca = cobrancaFacade.salvar(cobranca);
        }
        Historicocobranca historicocobranca = new Historicocobranca();
        historicocobranca.setAssunto("Data de vencimento alterada de " + Formatacao.ConvercaoDataPadrao(dataAnterior) + " por " + usuarioLogadoMB.getUsuario().getNome());
        historicocobranca.setCobranca(cobranca);
        historicocobranca.setContato("Sistema");
        historicocobranca.setData(new Date());
        historicocobranca.setUsuario(usuarioLogadoMB.getUsuario());
        HistoricoCobrancaFacade historicocobrancaFacade = new HistoricoCobrancaFacade();
        historicocobrancaFacade.salvar(historicocobranca);
        FacesMessage msg = new FacesMessage("Sucesso! ", "Data de Vencimento Alterada.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        sql = "Select c from Contasreceber c where c.valorpago=0 order by c.datavencimento, c.vendas.cliente.nome";
        carregarContasReceber(sql);
        return "consContasReceber";
    }
    
    public String openDialogAlterarData(Contasreceber contasreceber){
        conta = contasreceber;
        if (conta!=null){
            dataAnterior =conta.getDatavencimento();
        }
        return null;
    }
    
    public String adicionarContasReceber(){
        RequestContext.getCurrentInstance().openDialog("adicionarContasReceber");
        return "";
    }
    
    public void retornoDialogoNovo(SelectEvent event){
        Contasreceber conta = (Contasreceber) event.getObject();
        listaContas.add(conta);
    }
    
    public void retornoDialogoEditar(SelectEvent event){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        String linha = (String) session.getAttribute("linha");
        Contasreceber conta = (Contasreceber) event.getObject();
        listaContas.set(Integer.parseInt(linha), conta);
    }
    
    
     
    
}
