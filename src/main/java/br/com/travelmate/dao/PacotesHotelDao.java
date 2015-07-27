/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Pacotehotel;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class PacotesHotelDao {
    
    private EntityManager manager;
    
    public Pacotehotel salvar(Pacotehotel pacote) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        pacote = manager.merge(pacote);
        //fechando uma transação
        manager.getTransaction().commit();
        return pacote;
    }
    
    public Pacotehotel consultar(int idTrecho) throws SQLException {
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Pacotehotel p where p.pacotetrecho.idpacotetrecho=" + idTrecho);
        manager.getTransaction().commit();
        if (q.getResultList().size() > 0) {
            return (Pacotehotel) q.getResultList().get(0);
        } else {
            return null;
        }
    }
    
    public void excluir(int idPacote) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Pacotehotel pacote = manager.find(Pacotehotel.class, idPacote);
        manager.remove(pacote);
        //fechando uma transação
        manager.getTransaction().commit();
    }
    
}
