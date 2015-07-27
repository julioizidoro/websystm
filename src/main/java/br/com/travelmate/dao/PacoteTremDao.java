package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Pacotetrem;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PacoteTremDao {
    
    private EntityManager manager;
    
    public Pacotetrem salvar(Pacotetrem pacote) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        pacote = manager.merge(pacote);
        //fechando uma transação
        manager.getTransaction().commit();
        return pacote;
    }
    
    public Pacotetrem consultar(int idTrecho) throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Pacotetrem p where p.pacotetrecho.idpacotetrecho=" + idTrecho);
        manager.getTransaction().commit();
        if (q.getResultList().size() > 0) {
            return  (Pacotetrem) q.getResultList().get(0);
        } else {
            return null;
        }
    }
    
    public void excluir(int idPacote) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Pacotetrem pacote = manager.find(Pacotetrem.class, idPacote);
        manager.remove(pacote);
        //fechando uma transação
        manager.getTransaction().commit();
    }
    
}
