/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.CambioFacade;
import br.com.travelmate.facade.ParametrosProdutosFacade;
import br.com.travelmate.facade.UsuarioFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Parametrosprodutos;
import br.com.travelmate.model.Usuario;
import br.com.travelmate.util.Criptografia;
import br.com.travelmate.util.Formatacao;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@SessionScoped
public class UsuarioLogadoMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
    private Parametrosprodutos parametrosprodutos;
    private String novaSenha;
    private String confirmaNovaSenha;
    private List<Cambio> listaCambio;
    private String iata;
    private String uds;
    private String eur;
    private String gbp;
    private String cad;
    private String aud;
    private String nzd;
    private String chf;
    private String datacambio;

    @PostConstruct
    public void ini(){
        usuario = new Usuario();
        ParametrosProdutosFacade parametrosProdutosFacade = new ParametrosProdutosFacade();
        parametrosprodutos = parametrosProdutosFacade.consultar();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    public Parametrosprodutos getParametrosprodutos() {
        return parametrosprodutos;
    }

    public void setParametrosprodutos(Parametrosprodutos parametrosprodutos) {
        this.parametrosprodutos = parametrosprodutos;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmaNovaSenha() {
        return confirmaNovaSenha;
    }

    public void setConfirmaNovaSenha(String confirmaNovaSenha) {
        this.confirmaNovaSenha = confirmaNovaSenha;
    }

    public List<Cambio> getListaCambio() {
        return listaCambio;
    }

    public void setListaCambio(List<Cambio> listaCambio) {
        this.listaCambio = listaCambio;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getUds() {
        return uds;
    }

    public void setUds(String uds) {
        this.uds = uds;
    }

    public String getEur() {
        return eur;
    }

    public void setEur(String eur) {
        this.eur = eur;
    }

    public String getGbp() {
        return gbp;
    }

    public void setGbp(String gbp) {
        this.gbp = gbp;
    }

    public String getCad() {
        return cad;
    }

    public void setCad(String cad) {
        this.cad = cad;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getNzd() {
        return nzd;
    }

    public void setNzd(String nzd) {
        this.nzd = nzd;
    }

    public String getChf() {
        return chf;
    }

    public void setChf(String chf) {
        this.chf = chf;
    }

    public String getDatacambio() {
        return datacambio;
    }

    public void setDatacambio(String datacambio) {
        this.datacambio = datacambio;
    }
    
    public String validarUsuario(){
        if ((usuario.getLogin()==null) && (usuario.getSenha()==null)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Acesso Nrgado."));
        }else{
            String senha = "";
            try {
                senha = Criptografia.encript(usuario.getSenha());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setSenha(senha);
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            usuario = usuarioFacade.consultar(usuario.getLogin(), usuario.getSenha());
            if (usuario==null){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
            }else {
                usuario.setSenha("");
                carregarCambioDia();
                return "inicial";
            }
        }
        usuario = new Usuario();
        return "";
    }
    
    
     public void erroLogin(String mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, ""));
    }
     
    public void validarTrocarSenha(){
        if ((usuario.getLogin()!=null) && (usuario.getSenha()==null)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Login Invalido."));
        }else{
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            usuario = usuarioFacade.consultar(usuario.getLogin(), usuario.getSenha());
            if (usuario==null){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
            }
        }
    }
    
    public String confirmaNovaSenha() {
        if ((novaSenha.length() > 0) && (confirmaNovaSenha.length() > 0)) {
            if (novaSenha.equalsIgnoreCase(confirmaNovaSenha)) {
                UsuarioFacade usuarioFacade = new UsuarioFacade();
                usuario.setSenha(novaSenha);
                usuario = usuarioFacade.salvar(usuario);
                novaSenha = "";
                confirmaNovaSenha = "";
                return "inicial";
            } else {
                novaSenha = "";
                confirmaNovaSenha = "";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
        }
        return "";
    }
    
    public String cancelarTrocaSenha(){
        usuario = new Usuario();
        novaSenha="";
        confirmaNovaSenha="";
        return "index";
    }
    
    public void carregarCambioDia() {
        
        String data = Formatacao.ConvercaoDataSql(new Date());
        CambioFacade cambioFacade = new CambioFacade();
        listaCambio = cambioFacade.listar(data);
        int contador = 0;
        if (listaCambio == null) {
            while (listaCambio == null){
                try {
                    data = Formatacao.SubtarirDatas(new Date(), contador, "yyyy/MM/dd");
                } catch (Exception ex) {
                    Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
                }
                listaCambio = cambioFacade.listar(data);
                contador++;
            }
        }
        
        
        if (listaCambio==null){
            datacambio = "Erro";
            iata = "0,0000";
            uds = "0,0000";
            eur = "0,0000";
            gbp = "0,0000";
            cad = "0,0000";
            aud = "0,0000";
            nzd = "0,0000";
            chf = "0,0000";
        }else {
           datacambio = (Formatacao.ConvercaoDataPadrao(listaCambio.get(0).getData()));
            for(int i=0;i<listaCambio.size();i++){
                if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("IATA")){
                    iata = (Formatacao.formatarValorCambio(listaCambio.get(i).getValor()));
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("USD")){
                    uds=(Formatacao.formatarValorCambio(listaCambio.get(i).getValor()));
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("EUR")){
                    eur=(Formatacao.formatarValorCambio(listaCambio.get(i).getValor()));
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("GBP")){
                    gbp=(Formatacao.formatarValorCambio(listaCambio.get(i).getValor()));
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("cad")){
                    cad=(Formatacao.formatarValorCambio(listaCambio.get(i).getValor()));
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("aud")){
                    aud=(Formatacao.formatarValorCambio(listaCambio.get(i).getValor()));
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("nzd")){
                    nzd=(Formatacao.formatarValorCambio(listaCambio.get(i).getValor()));
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("chf")){
                    chf=(Formatacao.formatarValorCambio(listaCambio.get(i).getValor()));
                }
            }
        }
        
    }
    
    public String deslogar(){
        usuario.setIdusuario(null);
        Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
        sessionMap.clear();  
        return "index";
    }
    
}
