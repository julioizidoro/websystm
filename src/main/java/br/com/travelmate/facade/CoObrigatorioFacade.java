package br.com.travelmate.facade;

import br.com.travelmate.dao.ClienteDao;
import br.com.travelmate.dao.CoObrigatorioDao;
import br.com.travelmate.model.Cliente;
import br.com.travelmate.model.Coprodutos;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CoObrigatorioFacade {
    
    private CoObrigatorioDao coObrigatorioDao;
    
    public Coprodutos salvar(Coprodutos coobrigatorio){
        coObrigatorioDao = new CoObrigatorioDao();
        try {
            return coObrigatorioDao.salvar(coobrigatorio);
        } catch (SQLException ex) {
            Logger.getLogger(CoObrigatorioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
   public List<Coprodutos> listar(String sql){
        coObrigatorioDao = new CoObrigatorioDao();
        try {
            return coObrigatorioDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CoObrigatorioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
