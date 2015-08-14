package br.com.travelmate.managerBean;

import br.com.travelmate.bean.DadosBoletoBean;
import br.com.travelmate.bean.LerRetornoItauBean;
import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.model.Contasreceber;
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
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.jrimum.bopepo.Boleto;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

@Named
@ViewScoped
public class BoletoMB implements Serializable {

    private List<Contasreceber> listarSelecionados;

    public List<Contasreceber> getListarSelecionados() {
        return listarSelecionados;
    }

    public void setListarSelecionados(List<Contasreceber> listarSelecionados) {
        this.listarSelecionados = listarSelecionados;
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

    public void uploadRetorno(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Sucesso! ", event.getFile().getFileName() + " upload.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        File retorno = (File) event.getFile();
        lerRetorno(retorno);
    }

    public String lerRetorno(File retorno) {
        LerRetornoItauBean lerRetornoItauBean = new LerRetornoItauBean(retorno);
        return null;
    }

    public Boleto gerarBoleto(Contasreceber conta) {
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
                if (listarSelecionados.get(i).isSelecionado()){
                    listaBoletos.add(gerarBoleto(listarSelecionados.get(i)));
                }
            }
        }
        if (listaBoletos.size()>0){
            DadosBoletoBean dadosBoletoBean = new DadosBoletoBean();
            dadosBoletoBean.gerarPDFS(listaBoletos);
        }
        return "";
    }
}
