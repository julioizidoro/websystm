/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Recebimento;
import java.sql.SQLException;
import javax.persistence.EntityManager;

/**
 *
 * @author Wolverine
 */
public class RecebimentoDao {
    
    private EntityManager manager;
    
    public Recebimento salvar(Recebimento recebimento)throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        recebimento = manager.merge(recebimento);
        manager.getTransaction().commit();
        return recebimento;
    }
    
}
