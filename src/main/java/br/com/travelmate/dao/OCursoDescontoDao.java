package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Ocursodesconto;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class OCursoDescontoDao {
    
     private EntityManager manager;
    
    public Ocursodesconto salvar(Ocursodesconto  ocursodesconto) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        ocursodesconto = manager.merge(ocursodesconto);
        manager.getTransaction().commit();
        return ocursodesconto;
    }
    
    public List<Ocursodesconto> listar(String sql)throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        manager.getTransaction().commit();
        return q.getResultList();
    }
    
}
