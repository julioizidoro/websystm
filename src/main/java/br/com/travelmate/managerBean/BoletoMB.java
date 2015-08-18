package br.com.travelmate.managerBean;

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

import br.com.travelmate.bean.DadosBoletoBean;
import br.com.travelmate.bean.GerarArquivoRemessaItau;
import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.util.Formatacao;

@Named
@ViewScoped
public class BoletoMB implements Serializable {

    private List<Contasreceber> listarSelecionados;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;

    public List<Contasreceber> getListarSelecionados() {
        return listarSelecionados;
    }

    public void setListarSelecionados(List<Contasreceber> listarSelecionados) {
        this.listarSelecionados = listarSelecionados;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }
    

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        listarSelecionados = (List<Contasreceber>) session.getAttribute("listaContas");
        session.removeAttribute("listaContas");
        if (this.listarSelecionados == null) {
            String sql = "Select c from Contasreceber c where c.boletogerado='SIM' and c.boletoenviado=0";
            ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
            this.listarSelecionados = contasReceberFacade.listar(sql);
        }
    }

    public void fechardialogBoletos() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

   

    public Boleto gerarClasseBoleto(Contasreceber conta) {
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
    
    public String gerarBoleto(){
        List<Boleto> listaBoletos = new ArrayList<Boleto>();
        if (listarSelecionados!=null){
            for(int i=0;i<listarSelecionados.size();i++){
                if ((listarSelecionados.get(i).getBoletogerado().equalsIgnoreCase("NAO"))){
                    listaBoletos.add(gerarClasseBoleto(listarSelecionados.get(i)));
                }
            }
        }
        if (listaBoletos.size()>0){
            DadosBoletoBean dadosBoletoBean = new DadosBoletoBean();
            dadosBoletoBean.gerarPDFS(listaBoletos);
        }
        return "";
    }
    
    public String gerarBoletoSegundaVia(){
        List<Boleto> listaBoletos = new ArrayList<Boleto>();
        if (listarSelecionados!=null){
            for(int i=0;i<listarSelecionados.size();i++){
                if ((listarSelecionados.get(i).getBoletogerado().equalsIgnoreCase("SIM"))){
                    listaBoletos.add(gerarClasseBoletoSegundaVia(listarSelecionados.get(i)));
                }
            }
        }
        if (listaBoletos.size()>0){
            DadosBoletoBean dadosBoletoBean = new DadosBoletoBean();
            dadosBoletoBean.gerarPDFS(listaBoletos);
        }
        return "";
    }
    
    public Boleto gerarClasseBoletoSegundaVia(Contasreceber conta) {
        DadosBoletoBean dadosBoletoBean = new DadosBoletoBean();
        dadosBoletoBean.setAgencias(conta.getVendas().getUnidadenegocio().getBanco().getAgencia());
        dadosBoletoBean.setCarteiras(conta.getVendas().getUnidadenegocio().getBanco().getCarteira());
        dadosBoletoBean.setCnpjCedente(conta.getVendas().getUnidadenegocio().getCnpj());
        dadosBoletoBean.setCodigoVenda(conta.getVendas().getIdvendas());
        dadosBoletoBean.setDataDocumento(conta.getDataEmissao());
        dadosBoletoBean.setDigitoAgencias(conta.getVendas().getUnidadenegocio().getBanco().getDigioagencia());
        dadosBoletoBean.setDigitoContas(conta.getVendas().getUnidadenegocio().getBanco().getDigitoconta());
        dadosBoletoBean.setDataVencimento(conta.getDatavencimento());
        dadosBoletoBean.setNomeCedente(conta.getVendas().getUnidadenegocio().getRazaoSocial());
        dadosBoletoBean.setNomeSacado(conta.getVendas().getCliente().getNome());
        dadosBoletoBean.setNumeroContas(conta.getVendas().getUnidadenegocio().getBanco().getConta());
        dadosBoletoBean.setNumeroDocumentos(conta.getNossonumero());
        dadosBoletoBean.setValor(Formatacao.converterFloatBigDecimal(conta.getValorparcela()));
        dadosBoletoBean.setNossoNumeros(conta.getNossonumero());
        dadosBoletoBean.setEnderecoSacado(new Endereco());
        dadosBoletoBean.getEnderecoSacado().setBairro(conta.getVendas().getCliente().getBairro());
        dadosBoletoBean.getEnderecoSacado().setCep(conta.getVendas().getCliente().getCep());
        dadosBoletoBean.getEnderecoSacado().setComplemento(conta.getVendas().getCliente().getComplemento());
        dadosBoletoBean.getEnderecoSacado().setLocalidade(conta.getVendas().getCliente().getCidade());
        dadosBoletoBean.getEnderecoSacado().setLogradouro(conta.getVendas().getCliente().getTipologradouro() + " " + conta.getVendas().getCliente().getLogradouro());
        dadosBoletoBean.getEnderecoSacado().setNumero(conta.getVendas().getCliente().getNumero());
        dadosBoletoBean.getEnderecoSacado().setUF(UnidadeFederativa.valueOfSigla(conta.getVendas().getCliente().getEstado()));
        dadosBoletoBean.criarBoleto();
        return dadosBoletoBean.getBoleto();
    }
    
    public String enviarBoleto(){
       List<Contasreceber> lista = new ArrayList<Contasreceber>();
       for(int i=0;i<listarSelecionados.size();i++){
           if(lista.get(i).isSelecionado()){
               lista.add(listarSelecionados.get(i));
           }
       }
       if(lista.size()==0){
           lista=listarSelecionados;
       }
       if(lista.size()>0){
             GerarArquivoRemessaItau arquivoRemessaItau = new GerarArquivoRemessaItau(lista, usuarioLogadoMB);
       }else{
            FacesMessage msg = new FacesMessage("Erro! ", "Nenhuma Conta Selecionada");
              FacesContext.getCurrentInstance().addMessage(null, msg);
       }
       return "";
    }
}
