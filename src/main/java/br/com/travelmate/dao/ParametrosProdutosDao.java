package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Parametrosprodutos;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class ParametrosProdutosDao {
    
    private EntityManager manager;
    
    public Parametrosprodutos salvar(Parametrosprodutos parametrosprodutos) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        parametrosprodutos = manager.merge(parametrosprodutos);
        manager.getTransaction().commit();
        return parametrosprodutos;
    }
    
    public Parametrosprodutos consultar() throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Parametrosprodutos  p");
        manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return (Parametrosprodutos) q.getSingleResult();
        }
        return null;
    }
}
