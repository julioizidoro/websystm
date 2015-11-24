/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.dao.OCursoFormaPagamentoDao;
import br.com.travelmate.model.Ocursoformapagamento;
import java.sql.SQLException;

/**
 *
 * @author Wolverine
 */
public class OCursoFormaPagamentoFacade {
    
    private OCursoFormaPagamentoDao oCursoFormaPagamentoDao;
    
    public Ocursoformapagamento salvar(Ocursoformapagamento formaPagamento) throws SQLException{
        oCursoFormaPagamentoDao = new OCursoFormaPagamentoDao();
        return oCursoFormaPagamentoDao.salvar(formaPagamento);
    }
    
}
