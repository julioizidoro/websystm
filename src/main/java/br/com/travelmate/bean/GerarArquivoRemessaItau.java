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
public class GerarArquivoRemessaItau {
    
    private List<Contasreceber> listaCotnas;
    private FileWriter remessa;
    private UsuarioLogadoMB usuarioLogadoMB;
    private int numeroSequencial=0;

    public GerarArquivoRemessaItau(List<Contasreceber> lista, UsuarioLogadoMB usuarioLogadoMB) {
        this.listaCotnas = lista;
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
                    Logger.getLogger(ArquivoRemessaNormal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(ArquivoRemessaNormal.class.getName()).log(Level.SEVERE, null, ex);
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
            
        }
        remessa.close();
    }
    
    
}
