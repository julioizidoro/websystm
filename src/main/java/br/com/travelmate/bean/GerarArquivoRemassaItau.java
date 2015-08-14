/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.bean;

import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.util.Formatacao;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class GerarArquivoRemassaItau {
    
    private List<Contasreceber> listaCotnas;
    private FileWriter remessa;
    private UsuarioLogadoMB usuarioLogadoMB;
    private int numeroSequencial=0;
    private String branco = "                                        ";
    private String zeros = "000000000000000000000";

    public GerarArquivoRemassaItau(List<Contasreceber> listaCotnas, UsuarioLogadoMB usuarioLogadoMB) {
        this.listaCotnas = listaCotnas;
        this.usuarioLogadoMB = usuarioLogadoMB;
        iniciarRemessa();
    }
    
    private void iniciarRemessa(){
        if (this.listaCotnas==null){
            String sql = "Select c from Contasreceber c where c.boletogerado='SIM' and c.boletoenviado=0";
            ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
            this.listaCotnas = contasReceberFacade.listar(sql);
        }
        if (this.listaCotnas!=null){
            String nomeArquivo = usuarioLogadoMB.getUsuario().getLocalsalvar() + "\\" + gerarNomeArquivo();
            try {
                remessa = new FileWriter(new File(nomeArquivo));
                try {
                    lerConta();
                } catch (Exception ex) {
                    Logger.getLogger(GerarArquivoRemassaItau.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(GerarArquivoRemassaItau.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String gerarNomeArquivo(){
        String data = Formatacao.ConvercaoDataPadrao(new Date());
        data = data.substring(6, 10) + data.substring(3, 5) + data.substring(0, 2);
        data = data + ".REM";
        return data;
    }
    
    private void lerConta() throws IOException, Exception{
        for(int i=0;i<listaCotnas.size();i++){
            gerarHeader(listaCotnas.get(i));
            gerarDetalhe(listaCotnas.get(i));
            gerarMulta(listaCotnas.get(i));
            gerarTrailer();
        }
        remessa.close();
    }
    
    private void gerarHeader(Contasreceber conta) throws IOException{
        numeroSequencial = numeroSequencial + 1;
        remessa.write("0");
        remessa.write("1");
        remessa.write("REMESSA");
        remessa.write("01");
        remessa.write("COBRANCA       ");
        remessa.write(conta.getVendas().getUnidadenegocio().getBanco().getAgencia());
        remessa.write("00");
        remessa.write(conta.getVendas().getUnidadenegocio().getBanco().getConta());
        remessa.write(conta.getVendas().getUnidadenegocio().getBanco().getDigitoconta());
        remessa.write(branco.substring(0, 8));
        String nomeEmpresa = conta.getVendas().getUnidadenegocio().getRazaoSocial();
        nomeEmpresa = nomeEmpresa.toUpperCase();
        if (nomeEmpresa.length()<30){
            nomeEmpresa = nomeEmpresa + branco.substring(0, 30 - nomeEmpresa.length());
        }else nomeEmpresa = nomeEmpresa.substring(0,30);
        remessa.write(nomeEmpresa);
        remessa.write("341");
        remessa.write("BANCO ITAU S.A.");
        remessa.write(Formatacao.ConvercaoDataDDMMAA(new Date()));
        remessa.write(branco + branco + branco + branco + branco + branco + branco + branco.substring(0,14));
        String ns = "";
        if (numeroSequencial<10){
            ns = "00000" + String.valueOf(numeroSequencial);
        }else if (numeroSequencial<100){
            ns = "0000" + String.valueOf(numeroSequencial);
        }else ns = "000" + String.valueOf(numeroSequencial);
        remessa.write(ns + "\r\n");
    }
    
    private void gerarDetalhe(Contasreceber conta) throws IOException, Exception{
        numeroSequencial = numeroSequencial + 1;
        remessa.write("1");
        remessa.write("02");
        remessa.write(Formatacao.retirarPontos(conta.getVendas().getUnidadenegocio().getCnpj()));
        remessa.write(conta.getVendas().getUnidadenegocio().getBanco().getAgencia());
        remessa.write("00");
        remessa.write(conta.getVendas().getUnidadenegocio().getBanco().getConta());
        remessa.write(conta.getVendas().getUnidadenegocio().getBanco().getDigitoconta());
        remessa.write(branco.substring(0, 4));
        remessa.write("00");
        remessa.write(branco.substring(0, 25));
        remessa.write(conta.getNossonumero());
        remessa.write("0000000000000");
        remessa.write("109");
        remessa.write("000000000000000000000");
        remessa.write("I");
        remessa.write("01");
        remessa.write(conta.getNossonumero() + "  ");
        remessa.write(Formatacao.ConvercaoDataDDMMAA(conta.getDatavencimento()));
        String valor = Formatacao.formatarFloatString(conta.getValorparcela());
        valor = Formatacao.retirarPontos(valor);
        if (valor.length()<13){
            valor = zeros.substring(0, 13 - valor.length()) + valor;
        }
        remessa.write(valor);
        remessa.write("341");
        remessa.write("00000");
        remessa.write("01");
        remessa.write("N");
        remessa.write(Formatacao.ConvercaoDataDDMMAA(new Date()));
        remessa.write("00");
        remessa.write("00");
        remessa.write(Formatacao.retirarPontos(valorJuros(conta.getValorparcela(), conta.getVendas().getUnidadenegocio().getBanco().getValorjuros())+conta.getVendas().getUnidadenegocio().getBanco().getValorjuros()));
        remessa.write(Formatacao.ConvercaoDataDDMMAA(new Date()));
        remessa.write(zeros.substring(0, 13));
        remessa.write(zeros.substring(0, 13));
        remessa.write(zeros.substring(0, 13));
        remessa.write("01");
        remessa.write(Formatacao.retirarPontos(conta.getVendas().getCliente().getCpf())+ "   ");
        String nomeCliente = conta.getVendas().getCliente().getNome();
        nomeCliente = nomeCliente.toUpperCase();
        if (nomeCliente.length()<30){
            nomeCliente = nomeCliente + branco.substring(0, 30 -nomeCliente.length());
        }else nomeCliente.substring(0, 30);
        remessa.write(nomeCliente);
        remessa.write(branco.substring(0, 10));
        String logradouro = conta.getVendas().getCliente().getTipologradouro() + " " + conta.getVendas().getCliente().getLogradouro() +
                conta.getVendas().getCliente().getNumero();
        logradouro = logradouro.toUpperCase();
        if (logradouro.length()<40){
            logradouro = logradouro + branco.substring(0, 40 - logradouro.length());
        }else logradouro = logradouro.substring(0, 40);
        remessa.write(logradouro);
        String bairro = conta.getVendas().getCliente().getBairro();
        bairro = bairro.toUpperCase();
        if (bairro.length()<12){
            bairro = bairro + branco.substring(0, 12 - bairro.length());
        }else bairro = bairro.substring(0,12);
        remessa.write(bairro);
        remessa.write(Formatacao.retirarPontos(conta.getVendas().getCliente().getCep()));
        String cidade = conta.getVendas().getCliente().getCidade();
        cidade = cidade.toUpperCase();
        if (cidade.length()<15){
            cidade = cidade + branco.substring(0, 15 - cidade.length());
        }else cidade = cidade.substring(0, 15);
        remessa.write(cidade);
        remessa.write(conta.getVendas().getCliente().getEstado().toUpperCase());
        remessa.write(branco.substring(0,30));
        remessa.write("    ");
        remessa.write(Formatacao.SubtarirDatas(conta.getDatavencimento(), -1, "ddMMyy"));
        remessa.write("00");
        remessa.write(" ");
        String ns;
        if (numeroSequencial<10){
            ns = "00000" + String.valueOf(numeroSequencial);
        }else if (numeroSequencial<100){
            ns = "0000" + String.valueOf(numeroSequencial);
        }else ns = "000" + String.valueOf(numeroSequencial);
        remessa.write(ns + "\r\n");
    }
    
    private void gerarMulta(Contasreceber conta) throws IOException, Exception{
        numeroSequencial = numeroSequencial + 1;
        remessa.write("2");
        remessa.write("1");
        remessa.write(Formatacao.SubtarirDatas(conta.getDatavencimento(), -1, "ddMMyyyy"));
        remessa.write(Formatacao.retirarPontos(valorJuros(conta.getValorparcela(), conta.getVendas().getUnidadenegocio().getBanco().getValormulta())+ conta.getVendas().getUnidadenegocio().getBanco().getValormulta()));
        remessa.write(branco + branco + branco + branco + branco + branco + branco + branco + branco + branco.substring(0,11));
        String ns;
        if (numeroSequencial<10){
            ns = "00000" + String.valueOf(numeroSequencial);
        }else if (numeroSequencial<100){
            ns = "0000" + String.valueOf(numeroSequencial);
        }else ns = "000" + String.valueOf(numeroSequencial);
        remessa.write(ns + "\r\n");
    }
    
    private void gerarTrailer() throws IOException{
        numeroSequencial = numeroSequencial + 1;
        remessa.write("9");
        remessa.write(branco + branco + branco + branco + branco + branco + branco + branco + branco + branco.substring(0,33));
        String ns;
        if (numeroSequencial<10){
            ns = "00000" + String.valueOf(numeroSequencial);
        }else if (numeroSequencial<100){
            ns = "0000" + String.valueOf(numeroSequencial);
        }else ns = "000" + String.valueOf(numeroSequencial);
        remessa.write(ns + "\r\n");
    }
    
    private String valorJuros(Float valorConta, float juros){
        Float valorJuros = valorConta * (juros/100);
        String valor = Formatacao.retirarPontos(Formatacao.formatarFloatString(valorJuros));
        if (valor.length()<13){
            valor = zeros.substring(0, 13 - valor.length());
        }
        return valor;
    }
}
