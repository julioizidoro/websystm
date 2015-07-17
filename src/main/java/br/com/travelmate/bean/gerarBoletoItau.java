/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.bean;

import br.com.travelmate.model.Banco;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.model.Unidadenegocio;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class gerarBoletoItau {
    
    private List<Contasreceber> listaContas;
    private Banco banco;
    private FileWriter arquivo;
    
    public List<Contasreceber> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<Contasreceber> listaContas) {
        this.listaContas = listaContas;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
    
    public void gerarCedente() throws IOException{
        File verArquivo = new File("C:\\DELL\\cedente.ini");
        if (verArquivo.exists()){
            verArquivo.delete();
        }
        Unidadenegocio unidade = banco.getUnidadenegocioList().get(0);
        arquivo = new FileWriter("C:\\DELL\\cedente.ini", true);
        BufferedWriter escrever = new BufferedWriter(arquivo);
        escrever.write("[Cedente]");
        escrever.newLine();
        escrever.write("Nome=" + unidade.getRazaoSocial());
        escrever.write("[Cedente]");
        escrever.newLine();
        escrever.write("CNPJCPF=" + unidade.getCnpj());
        escrever.newLine();

        escrever.write("Logradouro=" + unidade.getTipo() + " " + unidade.getLogradouro()); 
        escrever.newLine();
//
        escrever.write("Numero=" + unidade.getNumero());
        escrever.newLine();
//
        escrever.write("Bairro=" + unidade.getBairro());
        escrever.newLine();
//
        escrever.write("Cidade=" + unidade.getCidade());
        escrever.newLine();
//CEP=18.270-000
        escrever.write("CEP=" + unidade.getCep());
        escrever.newLine();
//Complemento=Sala 10
        escrever.write("Complemento" + unidade.getComplemento());
        escrever.newLine();
//UF=SP
        escrever.write("UF=" + unidade.getEstado());
        escrever.newLine();
//RespEmis=0
        escrever.write("RespEmis=0");
        escrever.newLine();
//TipoPessoa=1
        escrever.write("TipoPessoa=2");
        escrever.newLine();
//CodigoCedente=123456
        escrever.write("CodigoCedente=123456");
        escrever.newLine();
//LayoutBol=3
        escrever.write("LayoutBol=0");
        escrever.newLine();
//CaracTitulo=0
        escrever.write("CaracTitulo=0");
        escrever.newLine();
//
//[Conta]
        escrever.write("[Conta]");
        escrever.newLine();
//Conta=99999
        escrever.write("Conta=" + banco.getConta());
        escrever.newLine();
//DigitoConta=9
        escrever.write("DigitoConta=4");
        escrever.newLine();
//Agencia=9999
        escrever.write("Agencia=" + banco.getAgencia());
        escrever.newLine();
//DigitoAgencia=9
        escrever.write("DigitoAgencia=0");
        escrever.newLine();
//
//[Banco]
        escrever.write("[Banco]");
        escrever.newLine();
//Numero=237
        escrever.write("Numero=" + banco.getNumero());
        escrever.newLine();
//CNAB=1
        escrever.write("CNAB=1");
        escrever.newLine();
//IndiceACBr=5
        escrever.write("IndiceACBr=6");
        escrever.newLine();
    

        
        
        escrever.close();
        arquivo.close();
    }
    
    
    
}
