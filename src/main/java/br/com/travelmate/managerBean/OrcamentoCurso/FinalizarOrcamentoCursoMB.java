/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.ClienteFacade;
import br.com.travelmate.facade.CoeficienteJurosFacade;
import br.com.travelmate.facade.OCursoFacade;
import br.com.travelmate.facade.OCursoFormaPagamentoFacade;
import br.com.travelmate.facade.OCursoProdutoFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;	
import br.com.travelmate.model.Coeficientejuros;
import br.com.travelmate.model.Ocrusoprodutos;
import br.com.travelmate.model.Ocurso;
import br.com.travelmate.model.Ocursoformapagamento;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class FinalizarOrcamentoCursoMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private FiltrarEscolaMB FiltrarEscolaMB;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Ocrusoprodutos> listaProdutos;
    private Ocursoformapagamento formaPagamento;
    private Ocurso ocurso;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        listaProdutos = (List<Ocrusoprodutos>) session.getAttribute("listaProdutos");
        ocurso = (Ocurso) session.getAttribute("ocurso");
        session.removeAttribute("ocurso");
        session.removeAttribute("listaProdutos");
        if (formaPagamento==null){
            formaPagamento = new Ocursoformapagamento();
            formaPagamento.setNumeroParcelas02(0);
            formaPagamento.setFinaciamento(0.0f);
            formaPagamento.setNumeroParcelas03(0);
            formaPagamento.setNumeroParcelasFinanciamento(0);
            formaPagamento.setPercentualEntrada2(0.0);
            formaPagamento.setPercentualEntrada3(0.0);
            formaPagamento.setPercentualSaldo2(0.0);
            formaPagamento.setPercentualSaldo3(0.0);
            formaPagamento.setValorEntrada2(0.0f);
            formaPagamento.setValorEntrada3(0.0f);
            formaPagamento.setValorParcela02(0.0f);
            formaPagamento.setValorParcela03(0.0f);
            formaPagamento.setValorSaldo2(0.0f);
            formaPagamento.setValorSaldo3(0.0f);
            formaPagamento.setAVista(ocurso.getTotalmoedanacional());
        }
        
    }

    public List<Ocrusoprodutos> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Ocrusoprodutos> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Ocursoformapagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Ocursoformapagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Ocurso getOcurso() {
        return ocurso;
    }

    public void setOcurso(Ocurso ocurso) {
        this.ocurso = ocurso;
    }
    
    
    
    public void inicializarValores(){
        formaPagamento.setAVista(ocurso.getTotalmoedanacional());
    } 
    
    public void mostrarMensagem(Exception ex, String erro, String titulo){
        FacesContext context = FacesContext.getCurrentInstance();
        erro = erro + " - " + ex;
        context.addMessage(null, new FacesMessage(titulo, erro));
    } 
    
    public void calcularParcelamento() throws SQLException{
        Double valorSaldo=0.0;
        Double saldo = 0.0;
        Double valorEntrada=0.0;
        if (formaPagamento.getPercentualEntrada2()>0){
            valorEntrada = ocurso.getTotalmoedanacional() * (formaPagamento.getPercentualEntrada2()/100);
            saldo = 100 - formaPagamento.getPercentualEntrada2();
            valorSaldo = ocurso.getTotalmoedanacional() - valorEntrada;
            formaPagamento.setValorEntrada2(valorEntrada.floatValue());
            formaPagamento.setValorSaldo2(valorSaldo.floatValue());
            formaPagamento.setPercentualSaldo2(saldo);
        }
        valorSaldo = 0.0;
        if (formaPagamento.getNumeroParcelas02() > 0) {
            valorSaldo = formaPagamento.getValorSaldo2().doubleValue();
            if (valorSaldo > 0) {
                valorSaldo = valorSaldo / formaPagamento.getNumeroParcelas02();
                formaPagamento.setValorParcela02(valorSaldo.floatValue());
            }
        }

        
        //Opção 03
        
        valorSaldo=0.0;
        saldo = 0.0;
        valorEntrada=0.0;
        if (formaPagamento.getPercentualEntrada3()>0){
            valorEntrada = ocurso.getTotalmoedanacional() * (formaPagamento.getPercentualEntrada3()/100);
            saldo = 100- formaPagamento.getPercentualEntrada3();
            valorSaldo = ocurso.getTotalmoedanacional() - valorEntrada;
            formaPagamento.setValorEntrada3(valorEntrada.floatValue());
            formaPagamento.setValorSaldo3(valorSaldo.floatValue());
            formaPagamento.setPercentualSaldo3(saldo.doubleValue());
        }
        valorSaldo =0.0;
        if (formaPagamento.getNumeroParcelas03()>0){
            if(formaPagamento.getValorSaldo3()>0){
                 valorSaldo = formaPagamento.getValorSaldo3().doubleValue();
                 if (valorSaldo>0){
                     CoeficienteJurosFacade coneficienteJurosFacade = new CoeficienteJurosFacade();
                     Coeficientejuros  cf = coneficienteJurosFacade.consultar(formaPagamento.getNumeroParcelas03());
                     valorSaldo = (double) (valorSaldo * cf.getCoeficiente());
                     formaPagamento.setValorParcela03(valorSaldo.floatValue());
                 }
            }
        }
        
        //opção 04

        if (formaPagamento.getNumeroParcelasFinanciamento()>0){
            CoeficienteJurosFacade coneficienteJurosFacade = new CoeficienteJurosFacade();
            Coeficientejuros  cf = coneficienteJurosFacade.consultar(formaPagamento.getNumeroParcelasFinanciamento());
            Double valor = ocurso.getTotalmoedanacional() * cf.getCoeficiente();
            formaPagamento.setFinaciamento(valor.floatValue());
        }
    }
    
    public void finalziar(){
        OCursoFormaPagamentoFacade oCursoFormaPagamentoFacade = new OCursoFormaPagamentoFacade();
        try {
            formaPagamento = oCursoFormaPagamentoFacade.salvar(formaPagamento);
        } catch (SQLException ex) {
            Logger.getLogger(FinalizarOrcamentoCursoMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro Salvar Forma de Pagamento", "ERRO");
        }
        ClienteFacade clienteFacade = new ClienteFacade();
        ocurso.getCliente().setUnidadenegocio(usuarioLogadoMB.getUsuario().getUnidadenegocio());
        ocurso.setCliente(clienteFacade.salvar(ocurso.getCliente()));
        OCursoProdutoFacade oCursoProdutoFacade = new OCursoProdutoFacade();
        OCursoFacade orCursoFacade = new OCursoFacade();
        try {
            ocurso.setOcursoformapagamento(formaPagamento);
            ocurso.setCambio(FiltrarEscolaMB.getFornecedorProdutosBean().getCambio());
            ocurso.setFornecedorcidadeidioma(FiltrarEscolaMB.getFornecedorProdutosBean().getFornecedorcidadeidioma());
            ocurso = orCursoFacade.salvar(ocurso);
        } catch (SQLException ex) {
            Logger.getLogger(FinalizarOrcamentoCursoMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro Salvar Orçamento", "ERRO");
        }
        
        for(int i=0;i<listaProdutos.size();i++){
            try {
                oCursoProdutoFacade.salvar(listaProdutos.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(FinalizarOrcamentoCursoMB.class.getName()).log(Level.SEVERE, null, ex);
                mostrarMensagem(ex, "Erro Salvar Produto", "ERRO");
            }
        }
    }
    
    public String adicionarDestinarios(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("listaProdutos", listaProdutos);
        session.setAttribute("ocurso", ocurso);
        session.setAttribute("formaPagamento", formaPagamento);
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 400);
        RequestContext.getCurrentInstance().openDialog("enviarEmailDestinarios", options, null);
        return "";
    }
    
}
