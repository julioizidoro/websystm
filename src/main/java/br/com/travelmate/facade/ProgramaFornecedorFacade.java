/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.Interface.Programafornecedor;
import br.com.travelmate.dao.ProgramaFornecedorDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class ProgramaFornecedorFacade {
    
    private ProgramaFornecedorDao programaFornecedorDao;
    
    public Programafornecedor salvar(Programafornecedor programafornecedor) throws SQLException{
        programaFornecedorDao = new ProgramaFornecedorDao();
        return programaFornecedorDao.salvar(programafornecedor);
    }
    
    public List<Programafornecedor> lista(String sql) throws SQLException{
        programaFornecedorDao = new ProgramaFornecedorDao();
        return programaFornecedorDao.lista(sql);
    }
    
}
