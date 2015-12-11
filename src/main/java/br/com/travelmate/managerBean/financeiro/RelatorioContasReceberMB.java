package br.com.travelmate.managerBean.financeiro;

import br.com.travelmate.facade.UnidadeNegocioFacade;
import br.com.travelmate.model.Unidadenegocio;
import br.com.travelmate.util.Formatacao;
import br.com.travelmate.util.GerarRelatorio;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class RelatorioContasReceberMB  implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Unidadenegocio> listaUnidadeNegocio;
    private Unidadenegocio unidadenegocio;
    private Date dataInicio;
    private Date dataTermino;
    private String tipoRelatorio;
    
    
    @PostConstruct()
    public void init(){
        gerarListaUnidadeNegocio();
    }

    public List<Unidadenegocio> getListaUnidadeNegocio() {
        return listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<Unidadenegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
    }

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }
    
    public void gerarListaUnidadeNegocio(){
        UnidadeNegocioFacade unidadeNegocioFacade = new UnidadeNegocioFacade();
        listaUnidadeNegocio = unidadeNegocioFacade.listar();
        if (listaUnidadeNegocio==null){
            listaUnidadeNegocio = new ArrayList<Unidadenegocio>();
        }
    }
    
     public String gerarSql(){
        String sql = "SELECT distinct contasreceber.idcontasReceber, contasreceber.numeroDocumento, contasreceber.valorParcela, " +
                    "contasreceber.numeroparcelas,contasreceber.dataVencimento, contasreceber.juros, contasreceber.desagio," +
                    "contasreceber.tipodocumento, contasreceber.dataPagamento, contasreceber.valorpago, cliente.nome," +
                    "banco.nome as nomeBanco From "
            	+ " contasreceber join vendas on contasreceber.vendas_idvendas"
                + " join cliente on vendas.cliente_idcliente = cliente.idcliente"
                + " join unidadenegocio on vendas.unidadeNegocio_idunidadeNegocio = unidadenegocio.idUnidadeNegocio"
                + " join banco on contasreceber.banco_idbanco = banco.idbanco"
                + " where  contasreceber.datavencimento >='"  + Formatacao.ConvercaoDataSql(dataInicio) + " ' and contasreceber.datavencimento<='"
                        + Formatacao.ConvercaoDataSql(dataTermino) + "' ";
        if (unidadenegocio!=null){
            sql = sql + " and unidadenegocio.idunidadeNegocio=" + unidadenegocio.getIdunidadeNegocio();
        }
        if (tipoRelatorio!=null){
            if(tipoRelatorio.equalsIgnoreCase("Contas Recebidas")){
               sql = sql + " and contasreceber.valorpago>0";
            }else if(tipoRelatorio.equalsIgnoreCase("Contas em Aberto")){
               sql = sql + " and contasreceber.valorpago=0";
            }else sql = sql + " ";
        }
        sql = sql + " order by contasreceber.datavencimento ";
        return sql;
    }
    
    
    
    public String gerarRelatorio() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String caminhoRelatorio = "/reports/financeiro/contasreceber.jasper";  
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("sql", gerarSql());
        parameters.put("unidade", unidadenegocio.getNomeFantasia());
         parameters.put("titulo", tipoRelatorio);
        String periodo= "";
        if ((dataInicio!=null) && (dataTermino!=null)){
                periodo = "Período : " + Formatacao.ConvercaoDataPadrao(dataInicio) 
                        + "    " + Formatacao.ConvercaoDataPadrao(dataTermino);
        }
        parameters.put("periodo", periodo);
        GerarRelatorio gerarRelatorio = new GerarRelatorio();
        try {
            gerarRelatorio.gerarRelatorioSqlPDF(caminhoRelatorio, parameters, "contasreceber", null);
        } catch (JRException ex) {
            Logger.getLogger(RelatorioConciliacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioConciliacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public String fechar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
    
    
}
