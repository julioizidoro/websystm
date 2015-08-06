package br.com.travelmate.facade;

import br.com.travelmate.dao.SeguroViagemDao;
import br.com.travelmate.model.Seguroviagem;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeguroViagemFacade {
    
    SeguroViagemDao seguroViagemDao;
    
     public Seguroviagem salvar(Seguroviagem seguroViagem) {
        seguroViagemDao =new SeguroViagemDao();
        try {
            return seguroViagemDao.salvar(seguroViagem);
        } catch (SQLException ex) {
            Logger.getLogger(SeguroViagemFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Seguroviagem consultar(int idVenda) {
        seguroViagemDao = new SeguroViagemDao();
        try {
            return seguroViagemDao.consultar(idVenda);
        } catch (SQLException ex) {
            Logger.getLogger(SeguroViagemFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void excluir(int idSeguroViagem) {
        seguroViagemDao = new SeguroViagemDao();
        try {
            seguroViagemDao.excluir(idSeguroViagem);
        } catch (SQLException ex) {
            Logger.getLogger(SeguroViagemFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
