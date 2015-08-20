package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Coobrigatorio;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class CoObrigatorioDao {
    private EntityManager manager;
    
    public Coobrigatorio salvar(Coobrigatorio coObrigatorio) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        coObrigatorio = manager.merge(coObrigatorio);
        //fechando uma transação
        manager.getTransaction().commit();
        return coObrigatorio;
    }
    
    
    public List<Coobrigatorio> listar(String sql)throws SQLException{
         manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Coobrigatorio> lista = null;
        if (q.getResultList().size()>0){
            lista =  q.getResultList();
        }
        manager.getTransaction().commit();
        return lista;
    }
}
