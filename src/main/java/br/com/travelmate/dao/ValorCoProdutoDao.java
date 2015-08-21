package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Valorcoprodutos;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila Rodrigues
 */
public class ValorCoProdutoDao {
    
    private EntityManager manager;
    
    public Valorcoprodutos salvar(Valorcoprodutos valorcoproduto) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        valorcoproduto = manager.merge(valorcoproduto);
        //fechando uma transação
        manager.getTransaction().commit();
        return valorcoproduto;
    }
    
    
    public List<Valorcoprodutos> listar(String sql)throws SQLException{
         manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Valorcoprodutos> lista = null;
        if (q.getResultList().size()>0){
            lista =  q.getResultList();
        }
        manager.getTransaction().commit();
        return lista;
    }
}
