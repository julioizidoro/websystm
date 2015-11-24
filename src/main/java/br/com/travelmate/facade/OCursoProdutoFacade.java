/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.dao.OCursoProdutoDao;
import br.com.travelmate.model.Ocrusoprodutos;
import java.sql.SQLException;

/**
 *
 * @author Wolverine
 */
public class OCursoProdutoFacade {
    
    private OCursoProdutoDao oCursoProdutoDao;
    
    public Ocrusoprodutos salvar(Ocrusoprodutos produto) throws SQLException{
        oCursoProdutoDao = new OCursoProdutoDao();
        return oCursoProdutoDao.salvar(produto);
    }
    
}
