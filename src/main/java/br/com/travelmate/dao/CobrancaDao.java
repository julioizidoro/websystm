package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Cobranca;
import br.com.travelmate.model.Historicocobranca;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    
    public Cobranca consultar(String sql)throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
          manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return (Cobranca) q.getSingleResult();
        } 
        return null;
    }
    
    
    
}
