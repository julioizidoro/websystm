/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.bean;

import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.util.Formatacao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Wolverine
 */
public class LerRetornoItauBean {
    
   private UploadedFile arquivo;
    private String nomeArquivo;

    public UploadedFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(UploadedFile arquivo) {
        this.arquivo = arquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    
    
    
    public LerRetornoItauBean(File retorno) {
        try {
            lerArquivo(retorno);
        } catch (Exception ex) {
            Logger.getLogger(LerRetornoItauBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private  void lerArquivo(File retorno) throws Exception{
        FileReader fileReaderRetorno = new FileReader(retorno);
        BufferedReader leitor = new BufferedReader(fileReaderRetorno);
        String linha ="";
        
        while (linha != null) {
            linha = leitor.readLine(); //le a primeira linha
            linha = leitor.readLine(); // lea segunda linha
            registarDados(linha);
            linha = leitor.readLine(); // lea seugnda linha;
        }
        
    }
    
    private void registarDados(String linha){
        String nossoNumero = linha.substring(63, 71);
        nossoNumero = nossoNumero.trim();
        String dataPagamento = linha.substring(110, 116);
        dataPagamento.trim();
        String juros = linha.substring(266, 288);
        juros.trim();
        String valorPago = linha.substring(253,266);
        valorPago.trim();
        System.out.println("teste");
    }
    
    public void registarRecebimento(String nossoNumero, String dataPagamento, String valorPago, String juros){
        ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
        String sql = "Select c from Contasreceber c where c.nossonumero='" + nossoNumero + "'"; 
        Contasreceber conta = contasReceberFacade.consultar(sql);
        if (conta!=null){
            Float vJuros = Formatacao.formatarStringfloat(juros);
            if (vJuros>0){
                conta.setJuros(converterJuros(juros));
            }
            conta.setValorpago(converterValorPago(valorPago));
            conta.setDatapagamento(converterData(dataPagamento));
            conta.setDataRetorno(new Date());
            contasReceberFacade.salvar(conta);
        }
    }
    
    public Date converterData(String sData){
        String dataPagamento = sData.substring(0, 6) + "20" + sData.substring(6, 8);
        return Formatacao.ConvercaoStringData(dataPagamento);
    }
    
    public Float converterJuros(String juros){
        juros = juros.substring(0, juros.length()-2) + "," +   juros.substring(juros.length()-2, juros.length());
        return Formatacao.formatarStringfloat(juros);
    }
    
    public Float converterValorPago(String valorPago){
        valorPago = valorPago.substring(0, valorPago.length()-2) + "," +   valorPago.substring(valorPago.length()-2, valorPago.length());
        return Formatacao.formatarStringfloat(valorPago);
    }
    
    public void upload01(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Sucesso! ", event.getFile().getFileName() + " upload.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setArquivo(event.getFile());
        setNomeArquivo(event.getFile().getFileName());
    }
    
}
