/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Ocrusoprodutos;
import java.sql.SQLException;
import javax.persistence.EntityManager;

/**
 *
 * @author Wolverine
 */
public class OCursoProdutoDao {
    
    private EntityManager manager;
    
    public Ocrusoprodutos salvar(Ocrusoprodutos produto) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        produto = manager.merge(produto);
        manager.getTransaction().commit();
        return produto;
    }
    
}
