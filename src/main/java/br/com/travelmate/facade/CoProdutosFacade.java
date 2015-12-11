package br.com.travelmate.facade;


import br.com.travelmate.dao.CoProdutosDao;
import br.com.travelmate.model.Coprodutos;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CoProdutosFacade {
    
    private CoProdutosDao coObrigatorioDao;
    
    public Coprodutos salvar(Coprodutos coobrigatorio){
        coObrigatorioDao = new CoProdutosDao();
        try {
            return coObrigatorioDao.salvar(coobrigatorio);
        } catch (SQLException ex) {
            Logger.getLogger(CoProdutosFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
   public List<Coprodutos> listar(String sql){
        coObrigatorioDao = new CoProdutosDao();
        try {
            return coObrigatorioDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CoProdutosFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
