package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Fornecedorferias;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila Rodrigues
 */
public class FornecedorFeriasDao {
    private EntityManager manager;
    
    public Fornecedorferias salvar(Fornecedorferias fornecedorferias) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        fornecedorferias = manager.merge(fornecedorferias);
        //fechando uma transação
        manager.getTransaction().commit();
        return fornecedorferias;
    }
    
    
    public List<Fornecedorferias> listar(String sql)throws SQLException{
         manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Fornecedorferias> lista = null;
        if (q.getResultList().size()>0){
            lista =  q.getResultList();
        }
        manager.getTransaction().commit();
        return lista;
    }
    
}
