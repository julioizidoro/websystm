package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Seguroviagem;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class SeguroViagemDao {
    
    private EntityManager manager;
    
    public Seguroviagem salvar(Seguroviagem seguroViagem) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        seguroViagem = manager.merge(seguroViagem);
        //fechando uma transação
        manager.getTransaction().commit();
        return seguroViagem;
    }
    
    public Seguroviagem consultar(int idVenda) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select s from Seguroviagem s where s.vendas=" + idVenda);
        manager.getTransaction().commit();
        if (q.getResultList().size() > 0) {
            return  (Seguroviagem) q.getResultList().get(0);
        } else {
            return null;
        }
    }
    
    public void excluir(int idSeguroViagem) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Seguroviagem seguroViagem = manager.find(Seguroviagem.class, idSeguroViagem);
        //fechando uma transação
        manager.remove(seguroViagem);
        manager.getTransaction().commit();
    }
    
  
}
