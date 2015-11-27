
    package br.com.travelmate.managerBean.OrcamentoCurso;

    import br.com.travelmate.facade.CambioFacade;
    import br.com.travelmate.facade.CoProdutosFacade;
    import br.com.travelmate.facade.FiltroOrcamentoProdutoFacade;
    import br.com.travelmate.facade.FornecedorCidadeIdiomaFacade;
    import br.com.travelmate.facade.FornecedorFeriasFacade;
    import br.com.travelmate.facade.IdiomaFacade;
    import br.com.travelmate.facade.PaisProdutoFacade;
    import br.com.travelmate.facade.PublicidadeFacade;
    import br.com.travelmate.facade.ValorCoProdutosFacade;
    import br.com.travelmate.managerBean.UsuarioLogadoMB;
    import br.com.travelmate.model.Cambio;
    import br.com.travelmate.model.Cidade;
    import br.com.travelmate.model.Cliente;
    import br.com.travelmate.model.Coprodutos;
    import br.com.travelmate.model.Filtroorcamentoproduto;
    import br.com.travelmate.model.Fornecedorcidadeidioma;
    import br.com.travelmate.model.Fornecedorferias;
    import br.com.travelmate.model.Idioma;
    import br.com.travelmate.model.Ocurso;
    import br.com.travelmate.model.Pacotes;
    import br.com.travelmate.model.Pais;
    import br.com.travelmate.model.Paisproduto;
    import br.com.travelmate.model.Publicidade;
    import br.com.travelmate.model.Valorcoprodutos;
    import br.com.travelmate.model.Vendas;
    import br.com.travelmate.util.Formatacao;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.annotation.PostConstruct;
    import javax.enterprise.context.SessionScoped;
    import javax.faces.application.FacesMessage;
    import javax.faces.context.FacesContext;
    import javax.inject.Inject;
    import javax.inject.Named;
    import javax.servlet.http.HttpSession;
    import org.primefaces.context.RequestContext;
    import org.primefaces.event.SelectEvent;

    @Named
    @SessionScoped
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
        private FornecedorProdutosBean fornecedorProdutosBean;
        private List<Publicidade> listaPublicidades;
        private Cliente cliente;
        private Cambio cambio;
        private boolean selecionarCliente;
        private String nomeBotao;


        @PostConstruct
        public void init() {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            ocurso = (Ocurso) session.getAttribute("ocurso");
            int idProduto = 0;
            getUsuarioLogadoMB();
            PaisProdutoFacade paisProdutoFacade = new PaisProdutoFacade();
            idProduto = usuarioLogadoMB.getParametrosprodutos().getCursos();
            listaPais = paisProdutoFacade.listar(idProduto);
            gerarListaCursos();
            gerarListaIdioma();
            gerarListaPublicidade();
            if (ocurso == null) {
                pais = new Pais();
                cidade = new Cidade();
                ocurso = new Ocurso();
                cambio = new Cambio();
                idioma = new Idioma();
                ocurso.setCliente(new Cliente());
                selecionarCliente = false;
                nomeBotao = "Pesquisar";
            } else {
                pais = ocurso.getFornecedorcidade().getCidade().getPais();
                cidade = ocurso.getFornecedorcidade().getCidade();
                idioma = ocurso.getIdioma();
                selecionarCliente = true;
                nomeBotao = "Editar";
                cambio = ocurso.getCambio();
            }
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

        public FornecedorProdutosBean getFornecedorProdutosBean() {
            return fornecedorProdutosBean;
        }

        public void setFornecedorProdutosBean(FornecedorProdutosBean fornecedorProdutosBean) {
            this.fornecedorProdutosBean = fornecedorProdutosBean;
        }

        public List<Publicidade> getListaPublicidades() {
            return listaPublicidades;
        }

        public void setListaPublicidades(List<Publicidade> listaPublicidades) {
            this.listaPublicidades = listaPublicidades;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

        public Cambio getCambio() {
            return cambio;
        }

        public void setCambio(Cambio cambio) {
            this.cambio = cambio;
        }

        public boolean isSelecionarCliente() {
            return selecionarCliente;
        }

        public void setSelecionarCliente(boolean selecionarCliente) {
            this.selecionarCliente = selecionarCliente;
        }

        public String getNomeBotao() {
            return nomeBotao;
        }

        public void setNomeBotao(String nomeBotao) {
            this.nomeBotao = nomeBotao;
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
            String sql=null;
            if (ocurso.getIdocurso()!=null){
                sql = "Select f from Fornecedorcidadeidioma f where f.idioma.ididioma=" + idioma.getIdidioma() + " and f.fornecedorcidade.cidade.idcidade=" +
                    cidade.getIdcidade() + " and f.fornecedorcidade.produtos.idprodutos=" + usuarioLogadoMB.getParametrosprodutos().getCursos() +
                        "f.fornecedorcidade.idfornecedorcidade=" + ocurso.getFornecedorcidade().getIdfornecedorcidade() + 
                            " order by f.fornecedorcidade.peso desc";
            }else {
                sql = "Select f from Fornecedorcidadeidioma f where f.idioma.ididioma=" + idioma.getIdidioma() + " and f.fornecedorcidade.cidade.idcidade=" +
                    cidade.getIdcidade() + " and f.fornecedorcidade.produtos.idprodutos=" + usuarioLogadoMB.getParametrosprodutos().getCursos() +
                            " order by f.fornecedorcidade.peso desc";
            }
            FornecedorCidadeIdiomaFacade fornecedorCidadeIdiomaFacade = new FornecedorCidadeIdiomaFacade();
            List<Fornecedorcidadeidioma> listaFornecedorCidadeIdioma = fornecedorCidadeIdiomaFacade.listar(sql);
            if (listaFornecedorCidadeIdioma!=null){
                if (listaFornecedorCidadeIdioma.size()>0){
                    listarCoProdutos(listaFornecedorCidadeIdioma);
                    FacesContext fc = FacesContext.getCurrentInstance();
                    HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                    session.setAttribute("listaFornecedorProdutosBean", listaFornecedorProdutosBean);
                    if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("listaEscolasMB")){
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("listaEscolasMB");
                    }
                    if (ocurso.getIdocurso()!=null){
                        return "resultadoFiltroOrcamento";
                    }else{
                        setFornecedorProdutosBean(listaFornecedorProdutosBean.get(0));
                        return "orcamentoCurso";
                    }
                }else {
                    return null;
                }
            }else return null;
        }

        public void listarCoProdutos(List<Fornecedorcidadeidioma> listaFornecedorCidadeIdioma){
            listaFornecedorProdutosBean = new ArrayList<FornecedorProdutosBean>();
            for(int i=0;i<listaFornecedorCidadeIdioma.size();i++){
                FornecedorProdutosBean fpb = new FornecedorProdutosBean();
                fpb.setFornecedorCidade(listaFornecedorCidadeIdioma.get(i).getFornecedorcidade());
                Ocurso nocurso = new Ocurso();
                if (ocurso.getDataorcamento()==null){
                    nocurso.setDataorcamento(new Date());
                }else {
                    nocurso.setDataorcamento(ocurso.getDataorcamento());
                }
                nocurso.setDatainicio(ocurso.getDatainicio());
                nocurso.setDatatermino(calcularDataTermino());
                nocurso.setFornecedorcidade(listaFornecedorCidadeIdioma.get(i).getFornecedorcidade());
                nocurso.setIdioma(ocurso.getIdioma());
                nocurso.setNivelidioma(ocurso.getNivelidioma());
                nocurso.setNumerosemanas(ocurso.getNumerosemanas());
                nocurso.setProdutosorcamento(ocurso.getProdutosorcamento());
                nocurso.setCliente(ocurso.getCliente());
                nocurso.setDataorcamento(ocurso.getDataorcamento());
                fpb.setCambio(consultarCambio(fpb));
                fpb.setOcurso(nocurso);
                fpb.setListaObrigaroerios(gerarListaValorCoProdutos(fpb, "Obrigatorio"));
                fpb.setListaOpcionais(gerarListaValorCoProdutos(fpb, "Opcional"));
                fpb.setListaAcomodacoes(gerarListaValorCoProdutos(fpb, "Acomodacao"));
                fpb.setListaTransfer(gerarListaValorCoProdutos(fpb, "Transfer"));
                fpb.setValorDesconto(0.0f);
                fpb.setValorMoedaEstrangeira(valorMoedaEstrangeira(fpb));
                fpb.setValorMoedaNacional(calcularValorMoedaNacioanl(fpb));
                fpb.setSvalorDesconto(Formatacao.formatarFloatString(fpb.getValorDesconto()));
                fpb.setSvalorMoedaEstrangeira(fpb.getCambio().getMoedas().getSigla() + " " +  Formatacao.formatarFloatString(fpb.getValorMoedaEstrangeira()));
                fpb.setSvalorMoedaNacional("R$ " + Formatacao.formatarFloatString(fpb.getValorMoedaNacional()));
                listaFornecedorProdutosBean.add(fpb);
            }

        }

        public Cambio consultarCambio(FornecedorProdutosBean fornecedorProdutosBean){
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
            if (cambio!=null){
                return cambio;
            }
            return null;
        }

        public Float calcularValorMoedaNacioanl(FornecedorProdutosBean fornecedorProdutosBean){
            float valorMoeda = 0.0f;
             if (fornecedorProdutosBean.getListaObrigaroerios() != null) {
                for (int i = 0; i < fornecedorProdutosBean.getListaObrigaroerios().size(); i++) {
                    if (fornecedorProdutosBean.getListaObrigaroerios() != null) {
                        valorMoeda = valorMoeda + (fornecedorProdutosBean.getListaObrigaroerios().get(i).getValorOriginalRS() * fornecedorProdutosBean.getCambio().getValor());
                    }
                }
            }

            if (fornecedorProdutosBean.getListaOpcionais() != null) {
                for (int i = 0; i < fornecedorProdutosBean.getListaOpcionais().size(); i++) {
                    valorMoeda = valorMoeda + (fornecedorProdutosBean.getListaObrigaroerios().get(i).getValorOriginalRS() * fornecedorProdutosBean.getCambio().getValor());
                }
                return valorMoeda;
            }
            return valorMoeda;
        }

        public Float valorMoedaEstrangeira(FornecedorProdutosBean fornecedorProdutosBean) {
            float total = 0;
            if (fornecedorProdutosBean.getListaObrigaroerios() != null) {
                for (int i = 0; i < fornecedorProdutosBean.getListaObrigaroerios().size(); i++) {
                    if (fornecedorProdutosBean.getListaObrigaroerios() != null) {
                        total = total + fornecedorProdutosBean.getListaObrigaroerios().get(i).getValorOrigianl();
                    }
                }
            }

            if (fornecedorProdutosBean.getListaOpcionais() != null) {
                for (int i = 0; i < fornecedorProdutosBean.getListaOpcionais().size(); i++) {
                    total = total + fornecedorProdutosBean.getListaOpcionais().get(i).getValorOrigianl();
                }
                return total;
            }
            return total;
        }

        public List<ProdutosOrcamentoBean> gerarListaValorCoProdutos(FornecedorProdutosBean fornecedorProdutosBean, String tipo){
            List<ProdutosOrcamentoBean> listaRetorno = new ArrayList<ProdutosOrcamentoBean>();
            List<Coprodutos> listaCoProdutos;
            CoProdutosFacade coProdutosFacade = new CoProdutosFacade();
            String sql = null;
            if (tipo.equalsIgnoreCase("Obrigatorio")){
                sql = "Select c from Coprodutos c where c.fornecedorcidade.idfornecedorcidade=" + fornecedorProdutosBean.getFornecedorCidade().getIdfornecedorcidade() + 
                    " and c.tipo='" + tipo + "' and c.produtosorcamento.idprodutosOrcamento=" + ocurso.getProdutosorcamento().getIdprodutosOrcamento();
            }else {
                sql = "Select c from Coprodutos c where c.fornecedorcidade.idfornecedorcidade=" + fornecedorProdutosBean.getFornecedorCidade().getIdfornecedorcidade() + 
                    " and c.tipo='" + tipo + "'";
            }

            listaCoProdutos = coProdutosFacade.listar(sql);
            if (listaCoProdutos!=null){
                for(int i=0;i<listaCoProdutos.size();i++){
                    ProdutosOrcamentoBean po = consultarValores("DI", listaCoProdutos.get(i).getIdcoprodutos(), fornecedorProdutosBean, new Date());
                    if (po!=null){
                        listaRetorno.add(po);
                    }
                    po = consultarValores("DM", listaCoProdutos.get(i).getIdcoprodutos(), fornecedorProdutosBean, ocurso.getDatainicio());
                    if (po!=null){
                        listaRetorno.add(po);
                    }
                    po = consultarValores("DS", listaCoProdutos.get(i).getIdcoprodutos(), fornecedorProdutosBean, ocurso.getDatainicio());
                    if (po!=null){
                        listaRetorno.add(po);
                    }
                }
            }
            return listaRetorno;
        }

        public ProdutosOrcamentoBean consultarValores(String tipoData, int idCoProdutos, FornecedorProdutosBean fornecedorProdutosBean, Date dataConsulta) {
            ValorCoProdutosFacade valorCoProdutosFacade = new ValorCoProdutosFacade();
            Valorcoprodutos valorcoprodutos = null;
            String sql = "Select v from  Valorcoprodutos v where v.datainicial<='"
                    + Formatacao.ConvercaoDataSql(ocurso.getDatainicio()) + "' and v.datafinal>='"
                    + Formatacao.ConvercaoDataSql(ocurso.getDatainicio()) + "' and v.numerosemanainicial<="
                    + ocurso.getNumerosemanas() + " and v.numerosemanafinal>=" + ocurso.getNumerosemanas() + " and v.tipodata='" + tipoData + "' and v.coprodutos.idcoprodutos=" + idCoProdutos;

            List<Valorcoprodutos> listaValorcoprodutoses = valorCoProdutosFacade.listar(sql);
            if (listaValorcoprodutoses != null) {
                for (int n = 0; n < listaValorcoprodutoses.size(); n++) {
                    if (valorcoprodutos == null) {
                        valorcoprodutos = new Valorcoprodutos();
                        valorcoprodutos = listaValorcoprodutoses.get(n);
                    } else {
                        valorcoprodutos = compararValores(listaValorcoprodutoses.get(n), valorcoprodutos);
                    }

                }
            }
            if (valorcoprodutos != null) {
                ProdutosOrcamentoBean po = new ProdutosOrcamentoBean();
                po.setValorcoprodutos(valorcoprodutos);
                po.setIdproduto(valorcoprodutos.getCoprodutos().getIdcoprodutos());
                int multiplicador =1;
                if (valorcoprodutos.getCobranca().equalsIgnoreCase("S")){
                    multiplicador = fornecedorProdutosBean.getOcurso().getNumerosemanas();
                }else if (valorcoprodutos.getCobranca().equalsIgnoreCase("D")){
                    multiplicador = fornecedorProdutosBean.getOcurso().getNumerosemanas() * 7;
                }
                if (valorcoprodutos.getValorpromocional() > 0) {
                    po.setValorOrigianl(valorcoprodutos.getValororiginal() * multiplicador);
                    po.setValorPromocional(valorcoprodutos.getValorpromocional() * multiplicador);
                } else {
                    float valor = (float) (valorcoprodutos.getValororiginal() * 1.1);
                    po.setValorOrigianl(valor * multiplicador);
                    po.setValorPromocional(valorcoprodutos.getValororiginal() * multiplicador);

                }
                po.setValorPromocionalRS(po.getValorPromocional() * fornecedorProdutosBean.getCambio().getValor());
                po.setValorOrigianl(po.getValorOrigianl() * fornecedorProdutosBean.getCambio().getValor());
                po.setValorOriginalRS(po.getValorOrigianl() * fornecedorProdutosBean.getCambio().getValor());
                po.setSelecionadoOpcional(true);
                return po;
            }
            return null;
        }

        public Valorcoprodutos compararValores(Valorcoprodutos valorNovo, Valorcoprodutos valorAtual){
            if (valorNovo.getPromocional()){
                return valorNovo;
            }else return valorAtual;
        }

        private Date calcularDataTermino() {
            Date data = Formatacao.calcularDataFinal(ocurso.getDatainicio(), ocurso.getNumerosemanas(), "semana");
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
            String sql = "Select f from Fornecedorferias f where f.datafinal>='" + Formatacao.ConvercaoDataSql(ocurso.getDatainicio()) + "' and f.datafinal<='" + 
                    Formatacao.ConvercaoDataSql(data) + "'";
            FornecedorFeriasFacade fornecedorFeriasFacade = new FornecedorFeriasFacade();
            List<Fornecedorferias> listaFornecedorferias = fornecedorFeriasFacade.listar(sql);
            if(listaFornecedorferias==null){
                listaFornecedorferias = new ArrayList<Fornecedorferias>();
            }
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
            String sql = "select f from Filtroorcamentoproduto f where f.produtos.idprodutos=" + 
                    usuarioLogadoMB.getParametrosprodutos().getCursos() + " and f.produtosorcamento.tipo='C' order by f.produtosorcamento.descricao";
            listaProdutosOrcamento = filtroOrcamentoProdutoFacade.pesquisar(sql);
            if (listaProdutosOrcamento==null){
                listaProdutosOrcamento = new ArrayList<Filtroorcamentoproduto>();
            }
        }

        public String pesquisarCliente(){
            Map<String,Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 650);
            RequestContext.getCurrentInstance().openDialog("consultarCliente", options, null);
            return "";
        }

        public void gerarListaPublicidade() {
            PublicidadeFacade publicidadeFacade = new PublicidadeFacade();
            try {
                listaPublicidades = publicidadeFacade.listar();
                if (listaPublicidades == null) {
                    listaPublicidades = new ArrayList<Publicidade>();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FiltrarEscolaMB.class.getName()).log(Level.SEVERE, null, ex);
                mostrarMensagem(ex, "Erro Listar Publicidade", "ERRO");
            }
        }

        public void mostrarMensagem(Exception ex, String erro, String titulo){
            FacesContext context = FacesContext.getCurrentInstance();
            erro = erro + " - " + ex;
            context.addMessage(null, new FacesMessage(titulo, erro));
        } 	

       public void retornoDialogNovo(SelectEvent event){
           Cliente cliente = (Cliente) event.getObject();
           ocurso.setCliente(cliente);
       }


        public String editarcambio() {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 260);
            RequestContext.getCurrentInstance().openDialog("editarcambio", options, null);
            return "";
        }    

    }
