/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.Interface.Tiposervico;
import br.com.travelmate.connection.ConectionFactory;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class TipoServicoDao {
    
    private EntityManager manager;
    
    public Tiposervico salvar(Tiposervico tiposervico) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        tiposervico = manager.merge(tiposervico);
        manager.getTransaction().commit();
        return tiposervico;
    }
    
    public List<Tiposervico> lista() throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select t from Tiposervico t order by t.nome");
        manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return q.getResultList();
        }
        return null;
    }
    
}
