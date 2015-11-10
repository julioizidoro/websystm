package br.com.travelmate.facade;

import br.com.travelmate.dao.PaisDao;
import br.com.travelmate.model.Pais;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PaisFacade {
    
    PaisDao paisDao;
    
    public Pais salvar(Pais pais) {
        paisDao = new PaisDao();
        try {
            return paisDao.salvar(pais);
        } catch (SQLException ex) {
            Logger.getLogger(PaisFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Pais> listar(String nome) {
        paisDao = new PaisDao();
        try {
            return paisDao.listar(nome);
        } catch (SQLException ex) {
            Logger.getLogger(PaisFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
