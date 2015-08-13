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

    public GerarArquivoRemassaItau(List<Contasreceber> listaCotnas, UsuarioLogadoMB usuarioLogadoMB) {
        this.listaCotnas = listaCotnas;
        this.usuarioLogadoMB = usuarioLogadoMB;
        if (listaCotnas==null){
            String sql = "Select c From Contasreceber c where c.boletogerado=TRUE and boeltoentiado=FALSE";
            ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
            this.listaCotnas = contasReceberFacade.listar(sql);
        }
        if (listaCotnas!=null){
            String nomeArquivo = usuarioLogadoMB.getUsuario().getLocalsalvar() + "\\" + gerarNomeArquivo();
            try {
                FileWriter remessa = new FileWriter(new File(nomeArquivo));
                //metodo para gerar arquivo
            } catch (IOException ex) {
                Logger.getLogger(GerarArquivoRemassaItau.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String gerarNomeArquivo(){
        String data = Formatacao.ConvercaoDataPadrao(new Date());
        data = data.substring(6, 10) + data.substring(3, 5) + data.substring(0, 2);
        String hora = Formatacao.foramtarHoraString();
        data = data + "_" + hora + ".REM";
        return data;
    }
    
}
