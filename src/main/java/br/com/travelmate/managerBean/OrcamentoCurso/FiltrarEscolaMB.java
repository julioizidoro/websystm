package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.CambioFacade;
import br.com.travelmate.facade.CoProdutosFacade;
import br.com.travelmate.facade.FiltroOrcamentoProdutoFacade;
import br.com.travelmate.facade.FornecedorCidadeIdiomaFacade;
import br.com.travelmate.facade.FornecedorFeriasFacade;
import br.com.travelmate.facade.FornecedorLogoFacade;
import br.com.travelmate.facade.IdiomaFacade;
import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.facade.ValorCoProdutosFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Coprodutos;
import br.com.travelmate.model.Filtroorcamentoproduto;
import br.com.travelmate.model.Fornecedorcidadeidioma;
import br.com.travelmate.model.Fornecedorferias;
import br.com.travelmate.model.Fornecedorlogo;
import br.com.travelmate.model.Idioma;
import br.com.travelmate.model.Ocurso;
import br.com.travelmate.model.Pais;
import br.com.travelmate.model.Paisproduto;
import br.com.travelmate.model.Valorcoprodutos;
import br.com.travelmate.util.Formatacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class FiltrarEscolaMB implements Serializable{
    
   @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Paisproduto> listaPais;
    private Cidade cidade;
    private Pais pais;
    private Idioma idioma;
    private List<Idioma> listaIdiomas;
    private List<Filtroorcamentoproduto> listaProdutosOrcamento;
    private Ocurso ocurso;
    private List<FornecedorProdutosBean> listaFornecedorProdutosBean;
    
    
    @PostConstruct
    public void init(){
        int idProduto = 0;
        getUsuarioLogadoMB();
        PaisProdutoFacade paisProdutoFacade = new PaisProdutoFacade();
        idProduto = usuarioLogadoMB.getParametrosprodutos().getCursos();
        listaPais = paisProdutoFacade.listar(idProduto);
        gerarListaCursos();
        gerarListaIdioma();
        pais = new Pais();
        cidade = new Cidade();   
        ocurso = new Ocurso();
        idioma = new Idioma();
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }
    
    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public List<FornecedorProdutosBean> getListaFornecedorProdutosBean() {
        return listaFornecedorProdutosBean;
    }

    public void setListaFornecedorProdutosBean(List<FornecedorProdutosBean> listaFornecedorProdutosBean) {
        this.listaFornecedorProdutosBean = listaFornecedorProdutosBean;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }
    
    
    public List<Paisproduto> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Paisproduto> listaPais) {
        this.listaPais = listaPais;
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

    public List<Idioma> getListaIdiomas() {
        return listaIdiomas;
    }

    public void setListaIdiomas(List<Idioma> listaIdiomas) {
        this.listaIdiomas = listaIdiomas;
    }


    public List<Filtroorcamentoproduto> getListaProdutosOrcamento() {
        return listaProdutosOrcamento;
    }

    public void setListaProdutosOrcamento(List<Filtroorcamentoproduto> listaProdutosOrcamento) {
        this.listaProdutosOrcamento = listaProdutosOrcamento;
    }

    public Ocurso getOcurso() {
        return ocurso;
    }

    
    

    public void gerarListaIdioma(){
        IdiomaFacade idiomaFacade = new IdiomaFacade();
        String sql = "Select i from Idioma  i  order by i.descricao";
        listaIdiomas = idiomaFacade.listar(sql);
        if (listaIdiomas==null){
            listaIdiomas = new ArrayList<Idioma>();
        }
    }
    
    public String localizarFornecedorCidade(){
        String sql = "Select f from Fornecedorcidadeidioma f where f.idioma.ididioma=" + idioma.getIdidioma() + " and f.fornecedorcidade.cidade.idcidade=" +
                cidade.getIdcidade() + " and f.fornecedorcidade.produtos.idprodutos=" + usuarioLogadoMB.getParametrosprodutos().getCursos() +
                        " order by f.fornecedorcidade.peso desc";
        FornecedorCidadeIdiomaFacade fornecedorCidadeIdiomaFacade = new FornecedorCidadeIdiomaFacade();
        List<Fornecedorcidadeidioma> listaFornecedorCidadeIdioma = fornecedorCidadeIdiomaFacade.listar(sql);
        if (listaFornecedorCidadeIdioma!=null){
            if (listaFornecedorCidadeIdioma.size()>0){
                listarCoProdutos(listaFornecedorCidadeIdioma);
                FacesContext fc = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                session.setAttribute("listaFornecedorProdutosBean", listaFornecedorProdutosBean);
                return "resultadoFiltroOrcamento";
            }else {
                return null;
            }
        }else return null;
    }
    
    public void listarCoProdutos(List<Fornecedorcidadeidioma> listaFornecedorCidadeIdioma){
        FornecedorLogoFacade fornecedorLogoFacade = new FornecedorLogoFacade();
        for(int i=0;i<listaFornecedorCidadeIdioma.size();i++){
            FornecedorProdutosBean fpb = new FornecedorProdutosBean();
            fpb.setFornecedorCidade(listaFornecedorCidadeIdioma.get(i).getFornecedorcidade());
            Ocurso nocurso = new Ocurso();
            nocurso.setDatainicio(ocurso.getDatainicio());
            nocurso.setDatanascimento(ocurso.getDatanascimento());
            nocurso.setDatatermino(calcularDataTermino());
            nocurso.setFornecedorcidade(listaFornecedorCidadeIdioma.get(i).getFornecedorcidade());
            nocurso.setIdioma(ocurso.getIdioma());
            nocurso.setNivelidioma(ocurso.getNivelidioma());
            nocurso.setNumerosemanas(ocurso.getNumerosemanas());
            nocurso.setProdutosorcamento(ocurso.getProdutosorcamento());
            nocurso.setSexo(ocurso.getSexo());
            fpb.setoCurso(nocurso);
            Fornecedorlogo fornecedorlogo = fornecedorLogoFacade.consultar(listaFornecedorCidadeIdioma.get(i).getFornecedorcidade().getFornecedor().getIdfornecedor());
            fpb.setFornecedorlogo(fornecedorlogo);
            fpb.setListaObrigaroerios(gerarListaValorCoProdutos(fpb, "Obrigatorio"));
            fpb.setListaOpcionais(gerarListaValorCoProdutos(fpb, "Opcional"));
            fpb.setValorDesconto(0.0f);
            fpb.setValorMoedaEstrangeira(valorMoedaEstrangeira(fpb));
            fpb.setValorMoedaNacional(calcularValorMoedaNacioanl(fpb));
            listaFornecedorProdutosBean.add(fpb);
        }
        
    }
    
    public Float calcularValorMoedaNacioanl(FornecedorProdutosBean fornecedorProdutosBean){
        CambioFacade cambioFacade = new CambioFacade();
        Cambio cambio=null;
        Date data = new Date();
        while (cambio==null){
            cambio = cambioFacade.consultarCambioMoeda(Formatacao.ConvercaoDataSql(data), fornecedorProdutosBean.getFornecedorCidade().getCidade().getPais().getMoedas().getIdmoedas());
            try {
                data = Formatacao.SomarDiasDatas(data, -1);
            } catch (Exception ex) {
                Logger.getLogger(br.com.travelmate.managerBean.OrcamentoCurso.FiltrarEscolaMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        float valorMoeda = 0.0f;
        if (cambio!=null){
             valorMoeda = fornecedorProdutosBean.getValorMoedaEstrangeira() * cambio.getValor();
        }
        return valorMoeda;
    }
    
    public Float valorMoedaEstrangeira(FornecedorProdutosBean fornecedorProdutosBean){
        float total=0;
        for(int i=0;i<fornecedorProdutosBean.getListaObrigaroerios().size();i++){
            if (fornecedorProdutosBean.getListaObrigaroerios().get(i).getValorpromocional()>0){
                total = total + fornecedorProdutosBean.getListaObrigaroerios().get(i).getValorpromocional();
            }else total = total + fornecedorProdutosBean.getListaObrigaroerios().get(i).getValororiginal();
        }
        for(int i=0;i<fornecedorProdutosBean.getListaOpcionais().size();i++){
            if (fornecedorProdutosBean.getListaOpcionais().get(i).getValorpromocional()>0){
                total = total + fornecedorProdutosBean.getListaOpcionais().get(i).getValorpromocional();
            }else total = total + fornecedorProdutosBean.getListaOpcionais().get(i).getValororiginal();
        }
        return total;
    }
    
    public List<Valorcoprodutos> gerarListaValorCoProdutos(FornecedorProdutosBean fornecedorProdutosBean, String tipo){
        List<Valorcoprodutos> listaRetorno = new ArrayList<Valorcoprodutos>();
        List<Coprodutos> listaCoProdutos;
        CoProdutosFacade coProdutosFacade = new CoProdutosFacade();
        String sql = "Select c from Coprodutos c where c.fornecedorcidade.idfornecedorcidade=" + fornecedorProdutosBean.getFornecedorCidade().getIdfornecedorcidade() + 
                " and c.tipo=" + tipo;
        listaCoProdutos = coProdutosFacade.listar(sql);
        if (listaCoProdutos!=null){
            ValorCoProdutosFacade valorCoProdutosFacade = new ValorCoProdutosFacade();
            for(int i=0;i<listaCoProdutos.size();i++){
                Valorcoprodutos valorcoprodutos = null;
                sql = "Select v from  Valorcoprodutos v where v.datainicial>='" +
                        Formatacao.ConvercaoDataSql(new Date()) +"' and v.datafinal<='" +
                        Formatacao.ConvercaoDataSql(new Date()) + "' and v.numerosemanainicial>=" +
                        ocurso.getNumerosemanas() + " and and v.numerosemanainicial<=" + ocurso.getNumerosemanas() + " and v.tipodata='DI' and coprodutos";
                
                List<Valorcoprodutos> listaValorcoprodutoses = valorCoProdutosFacade.listar(sql);
                if (listaValorcoprodutoses!=null){
                    for(int n=0;n<listaValorcoprodutoses.size();n++){
                        if (valorcoprodutos==null){
                            valorcoprodutos = new Valorcoprodutos();
                            valorcoprodutos = listaValorcoprodutoses.get(n);
                        }else {
                            valorcoprodutos = compararValores(listaValorcoprodutoses.get(n), valorcoprodutos);
                        }
                        
                    }
                }
                listaRetorno.add(valorcoprodutos);
            }
        }
        return listaRetorno;
    }
    
    public Valorcoprodutos compararValores(Valorcoprodutos valorNovo, Valorcoprodutos valorAtual){
        if (valorNovo.getPromocional()){
            return valorNovo;
        }else return valorAtual;
    }
    
    private Date calcularDataTermino() {
        Date data = Formatacao.calcularDataFinal(ocurso.getDatainicio(), ocurso.getNumerosemanas(), null);
        int diaSemana = Formatacao.diaSemana(data);
        try {
            if (diaSemana == 1) {
                data = Formatacao.SomarDiasDatas(data, 2);
            } else if (diaSemana == 7) {
                data = Formatacao.SomarDiasDatas(data, 1);
            }
        } catch (Exception ex) {
            Logger.getLogger(br.com.travelmate.managerBean.OrcamentoCurso.FiltrarEscolaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "Select f from f.datafinal>='" + Formatacao.ConvercaoDataSql(ocurso.getDatainicio()) + "' and f.datafinal<='" + 
                Formatacao.ConvercaoDataSql(data) + "'";
        FornecedorFeriasFacade fornecedorFeriasFacade = new FornecedorFeriasFacade();
        List<Fornecedorferias> listaFornecedorferias = fornecedorFeriasFacade.listar(sql);
        if (listaFornecedorferias.size()>0){
            try {
                data = Formatacao.SomarDiasDatas(data, listaFornecedorferias.get(0).getNumerodias());
            } catch (Exception ex) {
                Logger.getLogger(br.com.travelmate.managerBean.OrcamentoCurso.FiltrarEscolaMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }
    
    public void gerarListaCursos(){
        FiltroOrcamentoProdutoFacade filtroOrcamentoProdutoFacade = new FiltroOrcamentoProdutoFacade();
        listaProdutosOrcamento = filtroOrcamentoProdutoFacade.pesquisar(usuarioLogadoMB.getParametrosprodutos().getCursos());
        if (listaProdutosOrcamento==null){
            listaProdutosOrcamento = new ArrayList<Filtroorcamentoproduto>();
        }
    }
    
}
