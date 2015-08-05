/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Pacotepassagem;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class PacotesPassagemDao {
    
    private EntityManager manager;
    
    public Pacotepassagem salvar(Pacotepassagem pacote) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        pacote = manager.merge(pacote);
        //fechando uma transação
        manager.getTransaction().commit();
        return pacote;
    }
    
    public Pacotepassagem consultar(int idPacote) throws SQLException{
        manager = ConectionFactory.getConnection();
          manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Pacotepassagem p where p.pacotetrecho.idpacotetrecho=" + idPacote);
         manager.getTransaction().commit();
        if (q.getResultList().size() > 0) {
            return  (Pacotepassagem) q.getResultList().get(0);
        } else {
            return null;
        }
    }
    
    
    public void excluir(int idPacote) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Pacotepassagem pacote = manager.find(Pacotepassagem.class, idPacote);
        manager.remove(pacote);
        //fechando uma transação
        manager.getTransaction().commit();
    }
    
}
