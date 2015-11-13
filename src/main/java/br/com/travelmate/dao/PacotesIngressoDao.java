package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Pacoteingresso;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PacotesIngressoDao {
    
    private EntityManager manager;
    
    public Pacoteingresso salvar(Pacoteingresso pacote) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        pacote = manager.merge(pacote);
        //fechando uma transação
        manager.getTransaction().commit();
        return pacote;
    }
    
    public Pacoteingresso consultar(int idTrecho) throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Pacoteingresso p where p.pacotetrecho.idpacotetrecho=" + idTrecho);
        manager.getTransaction().commit();
        if (q.getResultList().size() > 0) {
            return  (Pacoteingresso) q.getResultList().get(0);
        } else {
            return null;
        }
    }
    
    public void excluir(int idPacote) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Pacoteingresso pacote = manager.find(Pacoteingresso.class, idPacote);
        manager.remove(pacote);
        //fechando uma transação
        manager.getTransaction().commit();
    }
    
     public List<Pacoteingresso> listar(String sql) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("sql");
        List<Pacoteingresso> lista = null;
        if (q.getResultList().size()>0){
            lista = q.getResultList();
        }
        manager.getTransaction().commit();
        return lista;
    }
}
