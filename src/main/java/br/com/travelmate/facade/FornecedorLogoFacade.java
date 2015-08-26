/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.dao.ContasReceberDao;
import br.com.travelmate.dao.FornecedorLogoDao;
import br.com.travelmate.model.Fornecedorlogo;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamila Rodrigues
 */
public class FornecedorLogoFacade {
    
    private FornecedorLogoDao fornecedorLogoDao;
    
    public Fornecedorlogo salvar(Fornecedorlogo fornecedorlogo) {
        fornecedorLogoDao = new FornecedorLogoDao();
        try {
            return fornecedorLogoDao.salvar(fornecedorlogo);
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorLogoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public Fornecedorlogo consultar(int idFornecedorLogo) {
        fornecedorLogoDao = new FornecedorLogoDao();
        try {
            return fornecedorLogoDao.consultar(idFornecedorLogo);
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorLogoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
