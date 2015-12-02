package br.com.travelmate.managerBean;

import br.com.travelmate.model.Cambio;
import br.com.travelmate.relatorios.turismo.AutorizacaoCartaoFactory;
import br.com.travelmate.relatorios.turismo.AutorizacaoDebitoBean;
import br.com.travelmate.util.Formatacao;
import br.com.travelmate.util.GerarRelatorio;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named
@ViewScoped
public class AutorizacaoDebitoMB implements Serializable{
    
  
	private static final long serialVersionUID = 1L;
@Inject
   private UsuarioLogadoMB usuarioLogadoMB;
   private AutorizacaoDebitoBean autorizacaoDebitoBean;
   private Cambio cambio;

    public AutorizacaoDebitoMB() {
        autorizacaoDebitoBean = new AutorizacaoDebitoBean();
        cambio = new Cambio();
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }
   
   

    public AutorizacaoDebitoBean getAutorizacaoDebitoBean() {
        return autorizacaoDebitoBean;
    }

    public void setAutorizacaoDebitoBean(AutorizacaoDebitoBean autorizacaoDebitoBean) {
        this.autorizacaoDebitoBean = autorizacaoDebitoBean;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }
    
    public void gerarRelatorioAutorizacao() throws JRException, IOException{
        if (cambio != null) {
            autorizacaoDebitoBean.setMoeda(cambio.getMoedas().getSigla());
            autorizacaoDebitoBean.setValorcambio(Formatacao.formatarFloatString(cambio.getValor()));
        }
        String caminhoRelatorio = "/reports/turismo/autorizacaoCartao.jasper";  
        Map parameters = new HashMap();
        String nomeArquivo = "AutorizacaoCartao.pdf";
        parameters.put("cidade", usuarioLogadoMB.getUsuario().getUnidadenegocio().getCidade());
        parameters.put("unidade", usuarioLogadoMB.getUsuario().getUnidadenegocio().getNomeFantasia());
        //parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));
        List<AutorizacaoDebitoBean> lista = new ArrayList<AutorizacaoDebitoBean>();
        lista.add(autorizacaoDebitoBean);
        AutorizacaoCartaoFactory.setListaAutorizacaoCartao(lista);
        JRDataSource jrds = new JRBeanCollectionDataSource(AutorizacaoCartaoFactory.getListaAutorizacaoCartao());
        GerarRelatorio gerarRelatorio = new GerarRelatorio();
        gerarRelatorio.gerarRelatorioDSPDF(caminhoRelatorio, parameters, jrds, nomeArquivo);
    }
    
    public String fecahrTela(){
        return "inicial";
    }

    
}
