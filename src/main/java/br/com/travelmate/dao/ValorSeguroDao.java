package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Valoresseguro;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ValorSeguroDao {
    private EntityManager manager;
    
     public Valoresseguro salvar(Valoresseguro valor) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        valor = manager.merge(valor);
        //fechando uma transação
        manager.getTransaction().commit();
        return valor;
    }
    
    public Valoresseguro consultar(int idvalor) throws SQLException{
       manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Query q = manager.createQuery("select v from Valoresseguro  v where v.idvaloresseguro=" + idvalor);
        //fechando uma transação
         manager.getTransaction().commit();
        if(q.getResultList().size()>0){
            return (Valoresseguro) q.getResultList().get(0);
        }else return null;
    }
    
    public List<Valoresseguro> listar(int idFornecedor) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Query q = manager.createQuery("select v from Valoresseguro  v where v.fornecedorcidade.fornecedor.idfornecedor=" + idFornecedor);
        //fechando uma transação
         manager.getTransaction().commit();
        return q.getResultList();
    }
}
