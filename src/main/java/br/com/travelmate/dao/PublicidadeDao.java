/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Publicidade;

/**
 *
 * @author Wolverine
 */
public class PublicidadeDao {
    
    private EntityManager manager;
    
    public Publicidade salvar(Publicidade publicidade) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        publicidade = manager.merge(publicidade);
        manager.getTransaction().commit();
        return publicidade;
    }
    
    public Publicidade consultar(int idPublicidade) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Publicidade publicidade = manager.find(Publicidade.class, idPublicidade);
        manager.getTransaction().commit();
        return publicidade;
    }
    
    public List<Publicidade> listar() throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Publicidade  p order by p.descricao");
        manager.getTransaction().commit();
        if(q.getResultList().size()>0){
            return q.getResultList();
        }else return null;
    }
    
}
