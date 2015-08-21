package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Fornecedorcidadeidioma;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class FornecedorCidadeIdiomaDao {
     private EntityManager manager;
    
    public Fornecedorcidadeidioma salvar(Fornecedorcidadeidioma fornecedorcidadeidioma) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        fornecedorcidadeidioma = manager.merge(fornecedorcidadeidioma);
        //fechando uma transação
        manager.getTransaction().commit();
        return fornecedorcidadeidioma;
    }
    
    
    public List<Fornecedorcidadeidioma> listar(String sql)throws SQLException{
         manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Fornecedorcidadeidioma> lista = null;
        if (q.getResultList().size()>0){
            lista =  q.getResultList();
        }
        manager.getTransaction().commit();
        return lista;
    }
    
}
