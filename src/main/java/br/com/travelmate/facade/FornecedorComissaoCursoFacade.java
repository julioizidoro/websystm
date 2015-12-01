/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.dao.FornecedorComissaoCursoDao;
import br.com.travelmate.dao.FornecedorDao;
import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Fornecedorcomissaocurso;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class FornecedorComissaoCursoFacade {
    
    FornecedorComissaoCursoDao fornecedorComissaoCursoDao;
    
    
    public Fornecedorcomissaocurso salvar(Fornecedorcomissaocurso fornecedor) {
        fornecedorComissaoCursoDao = new FornecedorComissaoCursoDao();
        try {
            return fornecedorComissaoCursoDao.salvar(fornecedor);
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorComissaoCursoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
   public List<Fornecedorcomissaocurso> listar(int idFornecedor, int idPais) {
        fornecedorComissaoCursoDao = new FornecedorComissaoCursoDao();
        try {
            return fornecedorComissaoCursoDao.listar(idFornecedor, idPais);
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorComissaoCursoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
