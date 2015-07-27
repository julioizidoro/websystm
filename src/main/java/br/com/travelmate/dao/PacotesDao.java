/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Pacotes;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class PacotesDao {
    
    private EntityManager manager;
    
    public Pacotes salvar(Pacotes pacote) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        pacote = manager.merge(pacote);
        //fechando uma transação
        manager.getTransaction().commit();
        return pacote;
    }
    
     public void excluir(int idPacote) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Pacotes pacote = manager.find(Pacotes.class, idPacote);
        manager.remove(pacote);
        //fechando uma transação
        manager.getTransaction().commit();
    }
    
//    public List<Viewvendaspacotes> listarVendas(String sql) throws SQLException{
////        manager = ConectionFactory.getConnection();
////         manager.getTransaction().begin();
////        Query q = manager.createQuery(sql);
////        manager.getTransaction().commit();
////        return q.getResultList();
////    }
//    
    public Pacotes consultar(int idVenda) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Pacotes p where p.vendas=" + idVenda);
        manager.getTransaction().commit();
        if (q.getResultList().size() > 0) {
            return  (Pacotes) q.getResultList().get(0);
        } else {
            return null;
        }
    }
    
    public List<Pacotes> consultar(String sql) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        manager.getTransaction().commit();
        if (q.getResultList().size() > 0) {
            return  (List<Pacotes>) q.getResultList();
        } else {
            return null;
        }
    }
    
//    public Controlepacotes salvar(Controlepacotes controle) throws SQLException{
//        manager = ConectionFactory.getConnection();
//        //abrindo uma transação
//        manager.getTransaction().begin();
//        controle = manager.merge(controle);
//        //fechando uma transação
//        manager.getTransaction().commit();
//        return controle;
//    }
    
//    public void excluirControlePacotes(Controlepacotes controle) throws SQLException{
//        manager = ConectionFactory.getConnection();
//        //abrindo uma transação
//        manager.getTransaction().begin();
//        Controlepacotes controlePacotes = manager.find(Controlepacotes.class, controle.getIdcontrolePacotes());
//        manager.remove(controlePacotes);
//        //fechando uma transação
//        manager.getTransaction().commit();
//    }
//    
//    public Controlepacotes consultarControlePacotes(int idVendas) throws SQLException {
//        manager = ConectionFactory.getConnection();
//        manager.getTransaction().begin();
//        Query q = manager.createQuery("select c from Controlepacotes c where c.vendas=" + idVendas);
//        manager.getTransaction().commit();
//        if (q.getResultList().size() > 0) {
//            return (Controlepacotes) q.getResultList().get(0);
//        } else {
//            return null;
//        }
//    }
//    
//    public List<Viewcontrolepacotes> consultaControlePacotes(String sql) throws SQLException{
//        manager = ConectionFactory.getConnection();
//       manager.getTransaction().begin();
//       Query q = manager.createQuery(sql);
//        manager.getTransaction().commit();
//        return q.getResultList();
//    }
//    
//    public ResultSet ExportarExcel(String nomeRelatorio, String local, String porta, String senha, String banco, String usuario, String caminhoSalvarExcel, int idUnidade) throws IOException {
//        try {
//            ResultSet rs;
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            try {String conexao = "jdbc:mysql://" + local + ":" + porta + "/" + banco;
//                com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(conexao, usuario, senha);
//                com.mysql.jdbc.Statement stm = (com.mysql.jdbc.Statement) conn.createStatement();
//
//                stm.execute("USE systm"); //Nome do DATABASE A SER ACESSADO  
//                String sql =null;
//                if (idUnidade==0){
//                    sql = "Select * from viewExportarControlePacotes";
//                }else sql = "Select * from viewExportarControlePacotes where idUnidadeNegocio=" + idUnidade;
//                rs = stm.executeQuery(sql);
//                //GiroProdutoController giroProdutoController = new GiroProdutoController();
//                
//                    StringBuffer contenu; //// acho que seria melhor usar o StringBuilder
//                    contenu = new StringBuffer("");
//                    ResultSetMetaData rsMeta = rs.getMetaData();
//                    for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
//                    contenu.append(rsMeta.getColumnLabel(i) + "\t"); /// nesta linha imprime somente os nome dos campos da tabela  
//                }
//                contenu.append("\n"); // e temos que colocar todos os dados no StringBuffer  
//                rs.beforeFirst();
//                while (rs.next()) {
//                    for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
//                        contenu.append(rs.getString(i) + "\t"); /// aqui mostra todos os dados  
//                    }
//                    contenu.append("\n");
//
//                } //fim do while  
//                //agora, salvando o StringBuffer no arquivo  
//                FileWriter excelFile = new FileWriter(caminhoSalvarExcel); // nome do arquivo  
//                excelFile.write(new String(contenu)); //aqui ele passa a String para salvar  
//                excelFile.close();
//                JOptionPane.showMessageDialog(null, "Controle Exportador com Sucesso");
//                return rs;
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, ex);
//            }
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (InstantiationException ex) {
//            ex.printStackTrace();
//        } catch (IllegalAccessException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
    
}
