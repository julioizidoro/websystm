package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Ocrusoprodutos;
import br.com.travelmate.model.Paisproduto;
import br.com.travelmate.model.Seguroviagem;
import br.com.travelmate.model.Valoresseguro;
import br.com.travelmate.util.Formatacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class OrcamentoCursoMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    @Inject 
    private FiltrarEscolaMB filtrarEscolaMB;
    private ProdutosOrcamentoBean produtosOrcamentoBean;
    private boolean seguroSelecionado = false;
    private boolean obrigatorioSelecionado = true;
    private Seguroviagem seguroviagem;
    private Fornecedorcidade fornecedorcidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private float valorTotal;
    private float valorTotalRS;
    private Valoresseguro valorSeguro;
    private String acomodacaoHabiliada;
   
    

    @PostConstruct
    public void init() {
        int idProduto=0;
        getUsuarioLogadoMB();
        PaisProdutoFacade paisProdutoFacade = new PaisProdutoFacade();
        idProduto = usuarioLogadoMB.getParametrosprodutos().getSeguroPrivado();
        List<Paisproduto> listaPais = paisProdutoFacade.listar(idProduto);
        listaFornecedorCidade =  listaPais.get(0).getProdutos().getFornecedorcidadeList();
        if (seguroviagem == null) {
            seguroviagem = new Seguroviagem();
            fornecedorcidade = new Fornecedorcidade();
            valorSeguro = new Valoresseguro();
        }else{
            fornecedorcidade = seguroviagem.getValoresseguro().getFornecedorcidade();
        }
        getFiltrarEscolaMB();
        if (filtrarEscolaMB.getFornecedorProdutosBean()!=null){
            calcularTotais();
        }
    }
    
    
    public FiltrarEscolaMB getFiltrarEscolaMB() {
        return filtrarEscolaMB;
    }

    public void setFiltrarEscolaMB(FiltrarEscolaMB filtrarEscolaMB) {
        this.filtrarEscolaMB = filtrarEscolaMB;
    }
    

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    
    public boolean isSeguroSelecionado() {
        return seguroSelecionado;
    }

    public void setSeguroSelecionado(boolean seguroSelecionado) {
        this.seguroSelecionado = seguroSelecionado;
    }

    public boolean isObrigatorioSelecionado() {
        return obrigatorioSelecionado;
    }

    public void setObrigatorioSelecionado(boolean obrigatorioSelecionado) {
        this.obrigatorioSelecionado = obrigatorioSelecionado;
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

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public float getValorTotalRS() {
        return valorTotalRS;
    }

    public void setValorTotalRS(float valorTotalRS) {
        this.valorTotalRS = valorTotalRS;
    }


    public Valoresseguro getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(Valoresseguro valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public String getAcomodacaoHabiliada() {
        return acomodacaoHabiliada;
    }

    public void setAcomodacaoHabiliada(String acomodacaoHabiliada) {
        this.acomodacaoHabiliada = acomodacaoHabiliada;
    }

    public ProdutosOrcamentoBean getProdutosOrcamentoBean() {
        return produtosOrcamentoBean;
    }

    public void setProdutosOrcamentoBean(ProdutosOrcamentoBean produtosOrcamentoBean) {
        this.produtosOrcamentoBean = produtosOrcamentoBean;
    }
    
    
    
    public String habilitarSeguro(){
        if(seguroSelecionado){
            return acomodacaoHabiliada="false";
        }
        return acomodacaoHabiliada="true";
    }
    
   
    
     public String srcLogo(Fornecedor fornecedor){
        String logo ="";
        if (fornecedor!=null){
            logo = "http://www.travelmate.com.br/logoescola/" + fornecedor.getLogo();
        }
        return logo;
    }
     
     public String retornarValorString(Float valor, String sigla){
         String svalor = "";
         if(valor!=null){
             svalor = sigla + " " + Formatacao.formatarFloatString(valor);
         }
         return svalor; 
     }
     
     public void selecionarSeguro(){
         if (seguroSelecionado){
             seguroviagem = new Seguroviagem();
         }else {
             seguroviagem = null;
             calcularTotais();
         }
     }
     
     public void calcularTotais(){
         float total =0.0f;
         float totalRS=0.0f;
         if (seguroviagem!=null){
             if (seguroviagem.getValorSeguro()!=null){
                 totalRS = totalRS + seguroviagem.getValorSeguro();
                 total = total + (seguroviagem.getValorSeguro() / filtrarEscolaMB.getFornecedorProdutosBean().getCambio().getValor());
             }
         }
         
         for(int i=0;i<filtrarEscolaMB.getFornecedorProdutosBean().getListaObrigaroerios().size();i++){
             total = total + filtrarEscolaMB.getFornecedorProdutosBean().getListaObrigaroerios().get(i).getValorOrigianl();
             totalRS = totalRS + filtrarEscolaMB.getFornecedorProdutosBean().getListaObrigaroerios().get(i).getValorOriginalRS();
         }
         for(int i=0;i<filtrarEscolaMB.getFornecedorProdutosBean().getListaOpcionais().size();i++){
             if (filtrarEscolaMB.getFornecedorProdutosBean().getListaOpcionais().get(i).isSelecionadoOpcional()){
                total = total + filtrarEscolaMB.getFornecedorProdutosBean().getListaOpcionais().get(i).getValorOrigianl();
                totalRS = totalRS + filtrarEscolaMB.getFornecedorProdutosBean().getListaOpcionais().get(i).getValorOriginalRS();
             }
         }
         
         valorTotal = total;
         valorTotalRS = totalRS;
     }
     
     public void calcularValorSeguroViagem(){
         if (seguroviagem!=null){
             if (seguroviagem.getNumeroSemanas()!=null){
                 if (seguroviagem.getNumeroSemanas()>0){
                     if (seguroviagem.getValoresseguro()!=null){
                         seguroviagem.setValorSeguro(seguroviagem.getNumeroSemanas() * seguroviagem.getValoresseguro().getValorgross());
                     }
                 }
             }
         }
     }
     
     public void calcularDataTermino(){
         seguroviagem.setValoresseguro(valorSeguro);
        if (seguroviagem.getValoresseguro().getCobranca().equalsIgnoreCase("semana")){
            if ((seguroviagem.getDataInicio()!=null) && (seguroviagem.getNumeroSemanas()>0)){
                seguroviagem.setDataTermino(Formatacao.calcularDataFinal(seguroviagem.getDataInicio(), seguroviagem.getNumeroSemanas(), "semana"));
                seguroviagem.setValorSeguro(seguroviagem.getValoresseguro().getValorgross() * seguroviagem.getNumeroSemanas());
            }
        } else if (seguroviagem.getValoresseguro().getCobranca().equalsIgnoreCase("diaria")) {
            if ((seguroviagem.getDataInicio() != null) && (seguroviagem.getNumeroSemanas() > 0)) {
                seguroviagem.setDataTermino(Formatacao.calcularDataFinal(seguroviagem.getDataInicio(), seguroviagem.getNumeroSemanas(), "diaria"));
                Cambio cambio = new Cambio();
                for (int i = 0; i < usuarioLogadoMB.getListaCambio().size(); i++) {
                    if (usuarioLogadoMB.getListaCambio().get(i).getMoedas().getSigla().equalsIgnoreCase(seguroviagem.getValoresseguro().getMoedas().getSigla())) {
                        cambio = usuarioLogadoMB.getListaCambio().get(i);
                        i = 10000;
                    }
                }
                if (cambio != null) {
                    float valornacional = seguroviagem.getValoresseguro().getValorgross() * cambio.getValor();
                    seguroviagem.setValorSeguro(valornacional * seguroviagem.getNumeroSemanas());
                }
            }
        } else if (seguroviagem.getValoresseguro().getCobranca().equalsIgnoreCase("fechada")) {
            Cambio cambio = new Cambio();
            for (int i = 0; i < usuarioLogadoMB.getListaCambio().size(); i++) {
                if (usuarioLogadoMB.getListaCambio().get(i).getMoedas().getSigla().equalsIgnoreCase(seguroviagem.getValoresseguro().getMoedas().getSigla())) {
                    cambio = usuarioLogadoMB.getListaCambio().get(i);
                    i = 10000;
                }
            }
            if (cambio != null) {
                float valornacional = seguroviagem.getValoresseguro().getValorgross() * cambio.getValor();
                seguroviagem.setValorSeguro(valornacional);
                calcularTotais();
            }
        }
        
    }
     
    public String voltar(){
        return "resultadoFiltroOrcamento";
    }
    
    
    public void calcularValorAcomodacao(ProdutosOrcamentoBean produtosOrcamentoBean) {
        Double numeroSemanas = produtosOrcamentoBean.getNumeroSemanas();
        produtosOrcamentoBean.setValorOrigianl(numeroSemanas.floatValue() * produtosOrcamentoBean.getValorcoprodutos().getValororiginal());
        produtosOrcamentoBean.setValorOriginalRS(numeroSemanas.floatValue() * (produtosOrcamentoBean.getValorcoprodutos().getValororiginal() * filtrarEscolaMB.getFornecedorProdutosBean().getCambio().getValor()));
        calcularTotais();
        valorTotal = valorTotal + produtosOrcamentoBean.getValorOrigianl();
        valorTotalRS = valorTotalRS + produtosOrcamentoBean.getValorOriginalRS();

    }
    
    public void calcularValorTransfer(ProdutosOrcamentoBean produtosOrcamentoBean){
          calcularTotais();
            valorTotal = valorTotal + produtosOrcamentoBean.getValorOrigianl();
            valorTotalRS = valorTotalRS + produtosOrcamentoBean.getValorOriginalRS();
       
    }
    
    public String finalizarOrcamentoCurso(){
        gerarListaProdutosSelecionados();
        return "finalizarOrcamentoCurso";
    }
    
    public void gerarListaProdutosSelecionados(){
        List<Ocrusoprodutos> listaProdutos = new ArrayList<Ocrusoprodutos>();
        List<ProdutosOrcamentoBean> listaObrigaroerios = filtrarEscolaMB.getFornecedorProdutosBean().getListaObrigaroerios();
        for(int i=0;i<listaObrigaroerios.size();i++){
            Ocrusoprodutos produto = new Ocrusoprodutos();
            produto.setNumerosemanas(listaObrigaroerios.get(i).getNumeroSemanas());
            produto.setValorcoprodutos(listaObrigaroerios.get(i).getValorcoprodutos());
            produto.setValororiginal(listaObrigaroerios.get(i).getValorOrigianl());
            produto.setValorpromocional(listaObrigaroerios.get(i).getValorPromocional());
            listaProdutos.add(produto);   
        }
        List<ProdutosOrcamentoBean> listaOpcionais = filtrarEscolaMB.getFornecedorProdutosBean().getListaOpcionais();
        for (int i = 0; i < listaOpcionais.size(); i++) {
            if (listaOpcionais.get(i).isSelecionado()) {
                Ocrusoprodutos produto = new Ocrusoprodutos();
                produto.setNumerosemanas(listaOpcionais.get(i).getNumeroSemanas());
                produto.setValorcoprodutos(listaOpcionais.get(i).getValorcoprodutos());
                produto.setValororiginal(listaOpcionais.get(i).getValorOrigianl());
                produto.setValorpromocional(listaOpcionais.get(i).getValorPromocional());
            }
        }
        List<ProdutosOrcamentoBean> listaAcomodacoes = filtrarEscolaMB.getFornecedorProdutosBean().getListaAcomodacoes();
        for (int i = 0; i < listaAcomodacoes.size(); i++) {
            if (listaAcomodacoes.get(i).isSelecionado()) {
                Ocrusoprodutos produto = new Ocrusoprodutos();
                produto.setNumerosemanas(listaAcomodacoes.get(i).getNumeroSemanas());
                produto.setValorcoprodutos(listaAcomodacoes.get(i).getValorcoprodutos());
                produto.setValororiginal(listaAcomodacoes.get(i).getValorOrigianl());
                produto.setValorpromocional(listaAcomodacoes.get(i).getValorPromocional());
            }
        }
        List<ProdutosOrcamentoBean> listaTransfer = filtrarEscolaMB.getFornecedorProdutosBean().getListaTransfer();
        for (int i = 0; i < listaTransfer.size(); i++) {
            if (listaAcomodacoes.get(i).isSelecionado()) {
                Ocrusoprodutos produto = new Ocrusoprodutos();
                produto.setNumerosemanas(listaTransfer.get(i).getNumeroSemanas());
                produto.setValorcoprodutos(listaTransfer.get(i).getValorcoprodutos());
                produto.setValororiginal(listaTransfer.get(i).getValorOrigianl());
                produto.setValorpromocional(listaTransfer.get(i).getValorPromocional());
            }
        }
        filtrarEscolaMB.getFornecedorProdutosBean().getOcurso().setTotalmoedaestrangeira(valorTotal);
        filtrarEscolaMB.getFornecedorProdutosBean().getOcurso().setTotalmoedanacional(valorTotalRS);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("listaProdutos", listaProdutos);
        session.setAttribute("ocurso", filtrarEscolaMB.getFornecedorProdutosBean().getOcurso());
    }
}
