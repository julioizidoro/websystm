/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Ocurso;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class OCursoDao {
    
    private EntityManager manager;
    
    public Ocurso salvar(Ocurso  ocurso) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        ocurso = manager.merge(ocurso);
        manager.getTransaction().commit();
        return ocurso;
    }
    
    public List<Ocurso> listar(String sql)throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        manager.getTransaction().commit();
        return q.getResultList();
    }
}
