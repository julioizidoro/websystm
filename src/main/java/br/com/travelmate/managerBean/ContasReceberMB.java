/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.bean.DadosBoletoBean;
import br.com.travelmate.bean.ArquivoRemessaNormal;
import br.com.travelmate.bean.LerRetornoItauBean;
import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.model.Vendas;
import br.com.travelmate.util.Formatacao;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.jrimum.bopepo.Boleto;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Wolverine
 */

@Named
@ViewScoped
public class ContasReceberMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Contasreceber> listaContas;
    private Contasreceber conta;
    private Float contasVencidas;
    private Float contasVencer;
    private Float contasVencendo;
    private Vendas vendas;
    
    @PostConstruct
    public void init(){
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
    
    public String iniciarCobranca(Contasreceber conta){
        this.conta = conta;
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
        return "consContasReceber";
    }
    
    public String editar(){
        return "editarContasReceber";
    }
    public String cobranca(){
        return "cobranca";
    }
    
    public String gerarBoleto(){
        List<Boleto> listaBoletos = new ArrayList<Boleto>();
        if (listaContas!=null){
            for(int i=0;i<listaContas.size();i++){
                if (listaContas.get(i).isSelecionado()){
                    listaBoletos.add(gerarBoleto(listaContas.get(i)));
                }
            }
        }
        if (listaBoletos.size()>0){
            DadosBoletoBean dadosBoletoBean = new DadosBoletoBean();
            dadosBoletoBean.gerarPDFS(listaBoletos);
        }
        return "";
    }
    
    public Boleto gerarBoleto(Contasreceber conta){
        DadosBoletoBean dadosBoletoBean = new DadosBoletoBean();
        dadosBoletoBean.setAgencias(conta.getVendas().getUnidadenegocio().getBanco().getAgencia());
        dadosBoletoBean.setCarteiras(conta.getVendas().getUnidadenegocio().getBanco().getCarteira());
        dadosBoletoBean.setCnpjCedente(conta.getVendas().getUnidadenegocio().getCnpj());
        dadosBoletoBean.setCodigoVenda(conta.getVendas().getIdvendas());
        dadosBoletoBean.setDataDocumento(new Date());
        dadosBoletoBean.setDigitoAgencias(conta.getVendas().getUnidadenegocio().getBanco().getDigioagencia());
        dadosBoletoBean.setDigitoContas(conta.getVendas().getUnidadenegocio().getBanco().getDigitoconta());
        dadosBoletoBean.setDataVencimento(conta.getDatavencimento());
        dadosBoletoBean.setNomeCedente(conta.getVendas().getUnidadenegocio().getRazaoSocial());
        dadosBoletoBean.setNomeSacado(conta.getVendas().getCliente().getNome());
        dadosBoletoBean.setNumeroContas(conta.getVendas().getUnidadenegocio().getBanco().getConta());
        dadosBoletoBean.setNumeroDocumentos(Formatacao.gerarNumeroDocumentoBoleto(conta.getNumerodocumento(), String.valueOf(conta.getNumeroparcelas())));
        dadosBoletoBean.setValor(Formatacao.converterFloatBigDecimal(conta.getValorparcela()));
        dadosBoletoBean.setNossoNumeros(dadosBoletoBean.getNumeroDocumentos());
        dadosBoletoBean.setEnderecoSacado(new Endereco());
        dadosBoletoBean.getEnderecoSacado().setBairro(conta.getVendas().getCliente().getBairro());
        dadosBoletoBean.getEnderecoSacado().setCep(conta.getVendas().getCliente().getCep());
        dadosBoletoBean.getEnderecoSacado().setComplemento(conta.getVendas().getCliente().getComplemento());
        dadosBoletoBean.getEnderecoSacado().setLocalidade(conta.getVendas().getCliente().getCidade());
        dadosBoletoBean.getEnderecoSacado().setLogradouro(conta.getVendas().getCliente().getTipologradouro() + " " + conta.getVendas().getCliente().getLogradouro());
        dadosBoletoBean.getEnderecoSacado().setNumero(conta.getVendas().getCliente().getNumero());
        dadosBoletoBean.getEnderecoSacado().setUF(UnidadeFederativa.valueOfSigla(conta.getVendas().getCliente().getEstado()));
        dadosBoletoBean.criarBoleto();
        ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
        conta.setNossonumero(dadosBoletoBean.getNossoNumeros());
        conta.setDataEmissao(new Date());
        conta.setBoletogerado("SIM");
        contasReceberFacade.salvar(conta);
        return dadosBoletoBean.getBoleto();
    }
    
    public String lerRetorno(File retorno){
        LerRetornoItauBean lerRetornoItauBean = new LerRetornoItauBean(retorno);
        return null;
    }
    
    
    
    public String dialogBoletos(){
        RequestContext.getCurrentInstance().openDialog("boleto");
        return "";
    }
    
    public void uploadRetorno(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Sucesso! ", event.getFile().getFileName() + " upload.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        File retorno = (File) event.getFile();
        lerRetorno(retorno);
    }
    
    public void fechardialogBoletos(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void gerarArquivoRemessa(){
        ArquivoRemessaNormal  arquivoRemessa  = new ArquivoRemessaNormal(null, usuarioLogadoMB);
    }
     
    public String retornarBoletoGerado(Contasreceber conta){
        String retorno = "Não";
        if(conta.getBoletoenviado()==true){
            retorno = "Sim";
        }
        return retorno;
    }
}
