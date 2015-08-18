/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.bean.PassageirosBean;
import br.com.travelmate.model.Cliente;
import br.com.travelmate.model.Formapagamento;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Orcamento;
import br.com.travelmate.model.Pacotepassagem;
import br.com.travelmate.model.Parcelamentopagamento;
import br.com.travelmate.model.Passagemaerea;
import br.com.travelmate.model.Produtosorcamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class PassagemMB implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject 
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Passagemaerea> listaPassagem;
    private Passagemaerea passagem;
    private Cliente cliente;
    private List<Produtosorcamento> listaProdutoOrcamento;
    private Fornecedorcidade fornecedorCidade;
    private Orcamento orcamento;
    private Formapagamento formaPagamento;
    private PassageirosBean passageirosBean;
    private List<Parcelamentopagamento> listaParcelamento;
    private List<PassageirosBean> listaPassageirosBean;
    private Pacotepassagem pacotepassagem;

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public List<Passagemaerea> getListaPassagem() {
        return listaPassagem;
    }

    public void setListaPassagem(List<Passagemaerea> listaPassagem) {
        this.listaPassagem = listaPassagem;
    }

    public Passagemaerea getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagemaerea passagem) {
        this.passagem = passagem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produtosorcamento> getListaProdutoOrcamento() {
        return listaProdutoOrcamento;
    }

    public void setListaProdutoOrcamento(List<Produtosorcamento> listaProdutoOrcamento) {
        this.listaProdutoOrcamento = listaProdutoOrcamento;
    }

    public Fornecedorcidade getFornecedorCidade() {
        return fornecedorCidade;
    }

    public void setFornecedorCidade(Fornecedorcidade fornecedorCidade) {
        this.fornecedorCidade = fornecedorCidade;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Formapagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Formapagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<Parcelamentopagamento> getListaParcelamento() {
        return listaParcelamento;
    }

    public void setListaParcelamento(List<Parcelamentopagamento> listaParcelamento) {
        this.listaParcelamento = listaParcelamento;
    }

    public PassageirosBean getPassageirosBean() {
        return passageirosBean;
    }

    public void setPassageirosBean(PassageirosBean passageirosBean) {
        this.passageirosBean = passageirosBean;
    }

    public List<PassageirosBean> getListaPassageirosBean() {
        return listaPassageirosBean;
    }

    public void setListaPassageirosBean(List<PassageirosBean> listaPassageirosBean) {
        this.listaPassageirosBean = listaPassageirosBean;
    }

    public Pacotepassagem getPacotepassagem() {
        return pacotepassagem;
    }

    public void setPacotepassagem(Pacotepassagem pacotepassagem) {
        this.pacotepassagem = pacotepassagem;
    }
    
    
    
    public String novaPassagem(){
        return "cadPassagem";
    }
    
    public String pacotenovo(){
        return "cadPacoteOperadora";
    }
    public String pacotenovos(){
        return "cadPacote";
    }
    
    public String adicionarPassageiroBean(){
        if (passageirosBean.getNome().length()>0){
            if (listaPassageirosBean==null){
                listaPassageirosBean = new ArrayList<PassageirosBean>();
            }
            if (listaPassageirosBean.size()<10){
                listaPassageirosBean.add(passageirosBean);
                passageirosBean = new PassageirosBean();
            }else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Excedeu nº passageiros", ""));
            }
        }
        return "";
    }
    
    public String removerPassageiroBean(String linha){
        int nlinha = Integer.parseInt(linha);
        if (nlinha>=0){
            listaPassageirosBean.remove(nlinha);
        }
        return "";
    }
    
    
    public void iniciarListaPassageiros(){
        listaPassageirosBean = new ArrayList<PassageirosBean>();
        if (pacotepassagem.getPax01()!=null){
            if (pacotepassagem.getPax01().length()>0){
                passageirosBean = new PassageirosBean();
                passageirosBean.setNome(pacotepassagem.getPax01());
                if (pacotepassagem.getTipopax1()!=null){
                    passageirosBean.setTipo(pacotepassagem.getTipopax1());
                }
                if (pacotepassagem.getDataNascimentopax01()!=null){
                    passageirosBean.setDataNascimento(pacotepassagem.getDataNascimentopax01());
                }
                if (pacotepassagem.getPassaporte01()!=null){
                    passageirosBean.setPassaporte(pacotepassagem.getPassaporte01());
                }
                listaPassageirosBean.add(passageirosBean);
            }
        }
        if (pacotepassagem.getPax02()!=null){
            if (pacotepassagem.getPax02().length()>0){
                passageirosBean = new PassageirosBean();
                passageirosBean.setNome(pacotepassagem.getPax02());
                if (pacotepassagem.getTipopax2()!=null){
                    passageirosBean.setTipo(pacotepassagem.getTipopax2());
                }
                if (pacotepassagem.getDataNascimentopax02()!=null){
                    passageirosBean.setDataNascimento(pacotepassagem.getDataNascimentopax02());
                }
                if (pacotepassagem.getPassaporte02()!=null){
                    passageirosBean.setPassaporte(pacotepassagem.getPassaporte02());
                }
                listaPassageirosBean.add(passageirosBean);
            }
        }
        if (pacotepassagem.getPax03()!=null){
            if (pacotepassagem.getPax03().length()>0){
                passageirosBean = new PassageirosBean();
                passageirosBean.setNome(pacotepassagem.getPax03());
                if (pacotepassagem.getTipopax3()!=null){
                    passageirosBean.setTipo(pacotepassagem.getTipopax3());
                }
                if (pacotepassagem.getDataNascimentopax03()!=null){
                    passageirosBean.setDataNascimento(pacotepassagem.getDataNascimentopax03());
                }
                if (pacotepassagem.getPassaporte03()!=null){
                    passageirosBean.setPassaporte(pacotepassagem.getPassaporte03());
                }
                listaPassageirosBean.add(passageirosBean);
            }
        }
        if (pacotepassagem.getPax04()!=null){
            if (pacotepassagem.getPax04().length()>0){
                passageirosBean = new PassageirosBean();
                passageirosBean.setNome(pacotepassagem.getPax04());
                if (pacotepassagem.getTipopax4()!=null){
                    passageirosBean.setTipo(pacotepassagem.getTipopax4());
                }
                if (pacotepassagem.getDataNascimentopax04()!=null){
                    passageirosBean.setDataNascimento(pacotepassagem.getDataNascimentopax04());
                }
                if (pacotepassagem.getPassaporte04()!=null){
                    passageirosBean.setPassaporte(pacotepassagem.getPassaporte04());
                }
                listaPassageirosBean.add(passageirosBean);
            }
        }
        if (pacotepassagem.getPax05()!=null){
            if (pacotepassagem.getPax05().length()>0){
                passageirosBean = new PassageirosBean();
                passageirosBean.setNome(pacotepassagem.getPax05());
                if (pacotepassagem.getTipopax5()!=null){
                    passageirosBean.setTipo(pacotepassagem.getTipopax5());
                }
                if (pacotepassagem.getDataNascimentopax05()!=null){
                    passageirosBean.setDataNascimento(pacotepassagem.getDataNascimentopax05());
                }
                if (pacotepassagem.getPassaporte05()!=null){
                    passageirosBean.setPassaporte(pacotepassagem.getPassaporte05());
                }
                listaPassageirosBean.add(passageirosBean);
            }
        }
        if (pacotepassagem.getPax06()!=null){
            if (pacotepassagem.getPax06().length()>0){
                passageirosBean = new PassageirosBean();
                passageirosBean.setNome(pacotepassagem.getPax06());
                if (pacotepassagem.getTipopax6()!=null){
                    passageirosBean.setTipo(pacotepassagem.getTipopax6());
                }
                if (pacotepassagem.getDataNascimentopax06()!=null){
                    passageirosBean.setDataNascimento(pacotepassagem.getDataNascimentopax06());
                }
                if (pacotepassagem.getPassaporte06()!=null){
                    passageirosBean.setPassaporte(pacotepassagem.getPassaporte06());
                }
                listaPassageirosBean.add(passageirosBean);
            }
        }
        if (pacotepassagem.getPax07()!=null){
            if (pacotepassagem.getPax07().length()>0){
                passageirosBean = new PassageirosBean();
                passageirosBean.setNome(pacotepassagem.getPax07());
                if (pacotepassagem.getTipopax7()!=null){
                    passageirosBean.setTipo(pacotepassagem.getTipopax7());
                }
                if (pacotepassagem.getDataNascimentopax07()!=null){
                    passageirosBean.setDataNascimento(pacotepassagem.getDataNascimentopax07());
                }
                if (pacotepassagem.getPassaporte07()!=null){
                    passageirosBean.setPassaporte(pacotepassagem.getPassaporte07());
                }
                listaPassageirosBean.add(passageirosBean);
            }
        }
        if (pacotepassagem.getPax08()!=null){
            if (pacotepassagem.getPax08().length()>0){
                passageirosBean = new PassageirosBean();
                passageirosBean.setNome(pacotepassagem.getPax08());
                if (pacotepassagem.getTipopax8()!=null){
                    passageirosBean.setTipo(pacotepassagem.getTipopax8());
                }
                if (pacotepassagem.getDataNascimentopax08()!=null){
                    passageirosBean.setDataNascimento(pacotepassagem.getDataNascimentopax08());
                }
                if (pacotepassagem.getPassaporte08()!=null){
                    passageirosBean.setPassaporte(pacotepassagem.getPassaporte08());
                }
                listaPassageirosBean.add(passageirosBean);
            }
        }
        if (pacotepassagem.getPax09()!=null){
            if (pacotepassagem.getPax09().length()>0){
                passageirosBean = new PassageirosBean();
                passageirosBean.setNome(pacotepassagem.getPax09());
                if (pacotepassagem.getTipopax9()!=null){
                    passageirosBean.setTipo(pacotepassagem.getTipopax9());
                }
                if (pacotepassagem.getDataNascimentopax09()!=null){
                    passageirosBean.setDataNascimento(pacotepassagem.getDataNascimentopax09());
                }
                if (pacotepassagem.getPassaporte09()!=null){
                    passageirosBean.setPassaporte(pacotepassagem.getPassaporte09());
                }
                listaPassageirosBean.add(passageirosBean);
            }
        }
        if (pacotepassagem.getPax10()!=null){
            if (pacotepassagem.getPax10().length()>0){
                passageirosBean = new PassageirosBean();
                passageirosBean.setNome(pacotepassagem.getPax10());
                if (pacotepassagem.getTipopax10()!=null){
                    passageirosBean.setTipo(pacotepassagem.getTipopax10());
                }
                if (pacotepassagem.getDataNascimentopax10()!=null){
                    passageirosBean.setDataNascimento(pacotepassagem.getDataNascimentopax10());
                }
                if (pacotepassagem.getPassaporte10()!=null){
                    passageirosBean.setPassaporte(pacotepassagem.getPassaporte10());
                }
                listaPassageirosBean.add(passageirosBean);
            }
        }
    }
}
