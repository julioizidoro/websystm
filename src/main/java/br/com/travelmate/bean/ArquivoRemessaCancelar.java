/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.bean;

import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.util.Formatacao;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Wolverine
 */
public class ArquivoRemessaCancelar {
    
    private String branco = "                                        ";
    private String zeros = "000000000000000000000";
    
    public String gerarHeader(Contasreceber conta, int numeroSequencial) throws IOException{
        String linha="";
        linha = linha  + ("0");
        linha = linha  + ("1");
        linha = linha  + ("REMESSA");
        linha = linha  + ("01");
        linha = linha  + ("COBRANCA       ");
        linha = linha  + (conta.getVendas().getUnidadenegocio().getBanco().getAgencia());
        linha = linha  + ("00");
        linha = linha  + (conta.getVendas().getUnidadenegocio().getBanco().getConta());
        linha = linha  + (conta.getVendas().getUnidadenegocio().getBanco().getDigitoconta());
        linha = linha  + (branco.substring(0, 8));
        String nomeEmpresa = conta.getVendas().getUnidadenegocio().getRazaoSocial();
        nomeEmpresa = nomeEmpresa.toUpperCase();
        if (nomeEmpresa.length()<30){
            nomeEmpresa = nomeEmpresa + branco.substring(0, 30 - nomeEmpresa.length());
        }else nomeEmpresa = nomeEmpresa.substring(0,30);
        linha = linha  + (nomeEmpresa);
        linha = linha  + ("341");
        linha = linha  + ("BANCO ITAU S.A.");
        linha = linha  + (Formatacao.ConvercaoDataDDMMAA(new Date()));
        linha = linha  + (branco + branco + branco + branco + branco + branco + branco + branco.substring(0,14));
        String ns = "";
        if (numeroSequencial<10){
            ns = "00000" + String.valueOf(numeroSequencial);
        }else if (numeroSequencial<100){
            ns = "0000" + String.valueOf(numeroSequencial);
        }else ns = "000" + String.valueOf(numeroSequencial);
        linha = linha  + (ns + "\r\n");
        return linha;
    }
    
    public String gerarDetalhe(Contasreceber conta, int numeroSequencial) throws IOException, Exception{
        String linha="";
        linha = linha  + ("1");
        linha = linha  + ("00");
        linha = linha  + ("00000000000000");
        linha = linha  + (conta.getVendas().getUnidadenegocio().getBanco().getAgencia());
        linha = linha  + ("00");
        linha = linha  + (conta.getVendas().getUnidadenegocio().getBanco().getConta());
        linha = linha  + (conta.getVendas().getUnidadenegocio().getBanco().getDigitoconta());
        linha = linha  + (branco.substring(0, 4));
        linha = linha  + ("00");
        linha = linha  + (branco.substring(0, 25));
        linha = linha  + (conta.getNossonumero());
        linha = linha  + ("0000000000000");
        linha = linha  + ("109");
        linha = linha  + ("000000000000000000000");
        linha = linha  + ("I");
        linha = linha  + ("02");
        linha = linha  + ("          ");
        linha = linha  + ("000000");
        linha = linha  + ("0000000000000");
        linha = linha  + ("000");
        linha = linha  + ("00000");
        linha = linha  + ("00");
        linha = linha  + (" ");
        linha = linha  + ("000000");
        linha = linha  + ("00");
        linha = linha  + ("00");
        linha = linha  + ("000000000000");
        linha = linha  + ("000000");
        linha = linha  + (zeros.substring(0, 13));
        linha = linha  + (zeros.substring(0, 13));
        linha = linha  + (zeros.substring(0, 13));
        linha = linha  + ("00");
        linha = linha  + ("              ");
        linha = linha  + (branco.substring(0, 30));
        linha = linha  + (branco.substring(0, 10));
        linha = linha  + (branco.substring(0, 40));
        linha = linha  + (branco.substring(0, 12));
        linha = linha  + (branco.substring(0, 8));
        linha = linha  + (branco.substring(0, 15));
        linha = linha  + (branco.substring(0, 2));
        linha = linha  + (branco.substring(0,30));
        linha = linha  + ("    ");
        linha = linha  + ("000000");
        linha = linha  + ("00");
        linha = linha  + (" ");
        String ns;
        if (numeroSequencial<10){
            ns = "00000" + String.valueOf(numeroSequencial);
        }else if (numeroSequencial<100){
            ns = "0000" + String.valueOf(numeroSequencial);
        }else ns = "000" + String.valueOf(numeroSequencial);
        linha = linha  + (ns + "\r\n");
        return linha;
    }
    
    public String gerarMulta(Contasreceber conta, int numeroSequencial) throws IOException, Exception{
        return null;
    }
    
    public String gerarTrailer(int numeroSequencial) throws IOException{
        String linha="";
        linha = linha  + ("9");
        linha = linha  + (branco + branco + branco + branco + branco + branco + branco + branco + branco + branco.substring(0,33));
        String ns;
        if (numeroSequencial<10){
            ns = "00000" + String.valueOf(numeroSequencial);
        }else if (numeroSequencial<100){
            ns = "0000" + String.valueOf(numeroSequencial);
        }else ns = "000" + String.valueOf(numeroSequencial);
        linha = linha  + (ns + "\r\n");
        return linha;
    }
}
