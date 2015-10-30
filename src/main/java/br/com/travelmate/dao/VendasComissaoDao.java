package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Vendascomissao;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class VendasComissaoDao {
    
    private EntityManager manager;
    
    public Vendascomissao salvar(Vendascomissao vendasComissao) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        vendasComissao = manager.merge(vendasComissao);
        //fechando uma transação
        manager.getTransaction().commit();
        return vendasComissao;
    }
    
    public Vendascomissao getVendaComissao(int idVenda)throws SQLException{
         manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Query q = manager.createQuery("Select v from Vendascomissao v where v.vendas.idvendas=" + idVenda);
        //fechando uma transação
        Vendascomissao vendascomissao = null;
        if (q.getResultList().size()>0){
            vendascomissao = (Vendascomissao) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return vendascomissao;
    }
    
    public void excluir(int idVendaComissao) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Vendascomissao vendasComissao = manager.find(Vendascomissao.class, idVendaComissao);
        manager.remove(vendasComissao);
        //fechando uma transação
        manager.getTransaction().commit();
    }
    
    public List<Vendascomissao> listar(String sql)throws SQLException{
         manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Vendascomissao> lista = null;
        if (q.getResultList().size()>0){
            lista =  q.getResultList();
        }
        manager.getTransaction().commit();
        return lista;
    }
    
}
