/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Cobranca;
import br.com.travelmate.model.Historicocobranca;
import java.sql.SQLException;
import javax.persistence.EntityManager;

/**
 *
 * @author Wolverine
 */
public class CobrancaDao {
    
    private EntityManager manager;
    
    public Cobranca salvar(Cobranca cobranca)throws SQLException {
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        cobranca = manager.merge(cobranca);
        manager.getTransaction().commit();
        return cobranca;
    }
    
    public Historicocobranca salvar(Historicocobranca historicocobranca) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        historicocobranca = manager.merge(historicocobranca);
        manager.getTransaction().commit();
        return historicocobranca;
    }
    
    
    
}
