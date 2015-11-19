/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.Interface.Programafornecedor;
import br.com.travelmate.Interface.Servico;
import br.com.travelmate.connection.ConectionFactory;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class ProgramaFornecedorDao {
    
    private EntityManager manager;
    
    public Programafornecedor salvar(Programafornecedor programafornecedor) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        programafornecedor = manager.merge(programafornecedor);
        manager.getTransaction().commit();
        return programafornecedor;
    }
    
    public List<Programafornecedor> lista(String sql) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return q.getResultList();
        }
        return null;
    }
    
}
