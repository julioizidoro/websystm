/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class LerRetornoItauBean {
    
    
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
        String linha = leitor.readLine();
        System.out.println(linha);
    }
    
    
    
}
