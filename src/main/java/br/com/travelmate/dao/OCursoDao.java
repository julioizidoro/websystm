/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Ocurso;
import java.sql.SQLException;
import javax.persistence.EntityManager;

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
    
}
