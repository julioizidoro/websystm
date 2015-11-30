/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Fornecedor;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila
 */
public class FornecedorDao {
    private EntityManager manager;
    
    public Fornecedor salvar(Fornecedor fornecedor) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        fornecedor = manager.merge(fornecedor);
        //fechando uma transação
        manager.getTransaction().commit();
        return fornecedor;
    }
    
    public List<Fornecedor> listar(String sql) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Fornecedor> listaFornecedor = q.getResultList();
        manager.getTransaction().commit();
        return listaFornecedor;
    }
}
