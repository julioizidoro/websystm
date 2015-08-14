/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.Interface;

import br.com.travelmate.model.Contasreceber;
import java.io.IOException;

/**
 *
 * @author Wolverine
 */
public interface ArquivoRemessaItau {
    
    String gerarHeader(Contasreceber conta, int numeroSequencial)throws IOException;
    String gerarDetalhe(Contasreceber conta, int numeroSequencial)throws IOException, Exception;
    String gerarMulta(Contasreceber conta, int numeroSequencial)throws IOException, Exception;
    String gerarTrailer(int numeroSequencial)throws IOException;
}
