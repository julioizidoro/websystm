package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.model.Ocrusoprodutos;
import br.com.travelmate.model.Ocurso;
import br.com.travelmate.model.Ocursoformapagamento;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EnviarEmailBean{
    
    private List<Ocrusoprodutos> listaProdutos;
    private Ocursoformapagamento formaPagamento;
    private Ocurso ocurso;
    private String corpo;
    private HtmlEmail email;
    
     public EnviarEmailBean(Ocurso ocurso,  List<Ocrusoprodutos> listaProdutos, Ocursoformapagamento formaPagamento) {
        this.ocurso = ocurso;
        this.formaPagamento = formaPagamento;
        this.listaProdutos = listaProdutos;
        criarCorpoEmail();
    }

    public List<Ocrusoprodutos> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Ocrusoprodutos> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Ocursoformapagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Ocursoformapagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Ocurso getOcurso() {
        return ocurso;
    }

    public void setOcurso(Ocurso ocurso) {
        this.ocurso = ocurso;
    }
    
   
    public void criarCorpoEmail() {
        corpo = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv= \"Content-Type \" content= \"text/html; charset=utf-8 \" /><title>WebSysTM</title>\n"
                + "                <style>  *{margin:0; padding:0; list-style: none; text-decoration: none; font-family: fantasy; font-size: 13px; color: #333;}\n"
                + "                .CSSTableGenerator {margin:0px;padding:0px;width:100%;box-shadow: 10px 10px 5px #888888;border:1px solid #ffffff;-moz-border-radius-bottomleft:0px;-webkit-border-bottom-left-radius:0px;border-bottom-left-radius:0px;-moz-border-radius-bottomright:0px;-webkit-border-bottom-right-radius:0px;border-bottom-right-radius:0px;-moz-border-radius-topright:0px;-webkit-border-top-right-radius:0px;border-top-right-radius:0px;-moz-border-radius-topleft:0px;-webkit-border-top-left-radius:0px;border-top-left-radius:0px;}.CSSTableGenerator table{border-collapse: collapse; border-spacing: 0;width:100%;height:100%;margin:0px;padding:0px;}.CSSTableGenerator tr:last-child td:last-child {-moz-border-radius-bottomright:0px;-webkit-border-bottom-right-radius:0px;border-bottom-right-radius:0px;}\n"
                + "                .CSSTableGenerator table tr:first-child td:first-child {-moz-border-radius-topleft:0px;-webkit-border-top-left-radius:0px;border-top-left-radius:0px;}.CSSTableGenerator table tr:first-child td:last-child {-moz-border-radius-topright:0px;-webkit-border-top-right-radius:0px;border-top-right-radius:0px;}.CSSTableGenerator tr:last-child td:first-child{-moz-border-radius-bottomleft:0px;-webkit-border-bottom-left-radius:0px;border-bottom-left-radius:0px;}.CSSTableGenerator tr:hover td{}\n"
                + "                .CSSTableGenerator tr:nth-child(odd){ background-color:#c4f2c4; }.CSSTableGenerator tr:nth-child(even)    { background-color:#ffffff; }.CSSTableGenerator td{vertical-align:middle;border:1px solid #ffffff;border-width:0px 1px 1px 0px;text-align:left;padding:6px;font-size:10px;font-family:Arial;font-weight:normal;color:#000000;}.CSSTableGenerator tr:last-child td{border-width:0px 1px 0px 0px;}.CSSTableGenerator tr td:last-child{\n"
                + "        border-width:0px 0px 1px 0px;}.CSSTableGenerator tr:last-child td:last-child{border-width:0px 0px 0px 0px;}.CSSTableGenerator tr:first-child td{background:-o-linear-gradient(bottom, #AFCA0B 5%, #98FB98 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #8cd18c), color-stop(1, #0ab25b) );\n"
                + "        background:-moz-linear-gradient( center top, #AFCA0B 5%, #98FB98 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#8cd18c\", endColorstr=\"#0ab25b\");	background: -o-linear-gradient(top,#8cd18c,0ab25b);background-color:#8cd18c;border:0px solid #ffffff;text-align:center;border-width:0px 0px 1px 1px;font-size:14px;font-family:Arial;font-weight:bold;color:#ffffff;}\n"
                + "                .CSSTableGenerator tr:first-child:hover td{background:-o-linear-gradient(bottom, #8cd18c 5%, #0ab25b 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #8cd18c), color-stop(1, #0ab25b) );background:-moz-linear-gradient( center top, #8cd18c 5%, #0ab25b 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#8cd18c\", endColorstr=\"#0ab25b\");	background: -o-linear-gradient(top,#8cd18c,0ab25b);background-color:#8cd18c;}\n"
                + "                .CSSTableGenerator tr:first-child td:first-child{border-width:0px 0px 1px 0px;}\n"
                + "                .CSSTableGenerator tr:first-child td:last-child{border-width:0px 0px 1px 1px;}\n"
                + "                </style></head><body><table border= \"0 \" cellspacing= \"0 \" cellpadding= \"0 \" style=\"margin-left:22%\">  <tr><td></td></tr> <br></br>\n"
                + "                  <tr><td width= \"750 \"><table border= \"0 \" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style= \"border:1px;border-style: solid;border-color:#AFCA0B;\">\n"
                + "                      <tr> <tr><td><table border= \"0 \" cellspacing= \"0 \" cellpadding= \"0 \" width=\"100%\"><tr><td bgcolor= \"#AFCA0B\" style=\"text-align: center\"><strong style=\"font-size: 15px;color:#333\" >Orçamento de Curso</strong><div align= \"center \">\n"
                + "                            </div></td></tr></table></td></tr><td width= \"100% \" bgcolor= \"\"><img src= \"http://www.travelmate.com.br/logoescola/logotravelmate.png \" width=\"204\" style=\"margin-left: 5%\"/></td></tr><tr> <td><table border= \"0 \" cellspacing= \"0 \" cellpadding= \"0 \" width= \"100% \"><tr><td >\n"
                + "                            <p class= \"curso-exterior \"><span class= \"curso-exterior \">"
                + "<strong style= \"margin-left: 5%;color: #257C67\">Nome: </strong>"+ ocurso.getCliente().getNome() +"<br />\n"
                + "                                <strong style= \"margin-left: 5%;color: #257C67\">Data do orçamento:</strong> "+ ocurso.getDataorcamento()+"<br />\n"
                + "                                <strong style= \"margin-left: 5%;color: #257C67\">Tipo de curso:</strong> "+ ocurso.getProdutosorcamento().getDescricao() +"<br />\n"
                + "                                <strong style= \"margin-left: 5%;color: #257C67\">Data de Início e Término:</strong> "+ ocurso.getDatainicio() +" á "+ ocurso.getDatatermino() +"<br />\n"
                + "                                <strong style= \"margin-left: 5%;color: #257C67\">Duração do curso:</strong> "+ ocurso.getNumerosemanas() +" Semana (s)<br /><br /><br />\n"
                + "                                <strong style= \"margin-left:5.3%;font-size: 17px;margin-top: -3%;color: #257C67;\">Valores</strong><br />\n";
              for (int i = 0; i < listaProdutos.size(); i++) {
               if (listaProdutos.get(i).getValorcoprodutos().getCoprodutos().getProdutosorcamento().getDescricao().equalsIgnoreCase("O")
                     || listaProdutos.get(i).getValorcoprodutos().getCoprodutos().getProdutosorcamento().getDescricao().equalsIgnoreCase("D")) {
                    corpo= corpo + "<table style=\"width:89%;margin-left: 5%;\" class=\"CSSTableGenerator\">\n"
                  + "                                    <tr >\n"
                  + "                                        <td style=\"color:#AFCA0B;font-size: 9px\"> t</td>\n"
                  + "                                        <td style=\"color:#114A3D;text-align: right\"> </td> \n"
                  + "                                    </tr>\n"
                  + "                                    <tr >\n"
                  + "                                        <td style=\"color:#114A3D \">"+ listaProdutos.get(i).getValorcoprodutos().getCoprodutos().getDescricao() +"</td>\n"
                  + "                                        <td style=\"color:#114A3D;text-align: right;width: 95px\">"+ listaProdutos.get(i).getValorcoprodutos().getValororiginal() +"</td> \n"
                  + "                                    </tr>\n"
                  + "                                </table>\n"
                  + "                                <table style=\"width:89%;margin-left: 5%;margin-top:-0.3%;background:#AFCA0B;\">\n"
                  + "                                    <tr >\n"
                  + "                                        <td style=\"color:#114A3D;font-size: 14px;font: bold 16px/19px Ariel, serif;\">Total</td>\n"
                  + "                                        <td style=\"color:#114A3D;text-align: right;font-size: 14px;font: bold 16px/19px Ariel, serif;\">US$ 2.735,00</td> \n"
                  + "                                    </tr>\n"
                  + "                                    <tr >\n"
                  + "                                        <td style=\"color:#114A3D;font-size: 14px;font: bold 16px/19px Ariel, serif;\">Total em Reais</td>\n"
                  + "                                        <td style=\"color:#114A3D;text-align: right;font-size: 14px;font: bold 16px/19px Ariel, serif;\">R$ 7.205,00</td> \n"
                  + "                                    </tr>\n"
                  + "                                </table>\n"
                  + "                                 <br></br> <br></br> <br></br>";
               }

            if (listaProdutos.get(i).getValorcoprodutos().getCoprodutos().getProdutosorcamento().getDescricao().equalsIgnoreCase("A")) {
                corpo = corpo + "<strong style= \"margin-left:5.3%;font-size: 17px;color: #257C67;\">Acomodação</strong><br />\n"
                        + "                                <table style=\"width:89%;margin-left: 5%;\" class=\"CSSTableGenerator\">\n"
                        + "                                    <tr >\n"
                        + "                                        <td style=\"color:#AFCA0B;font-size: 9px\"> t</td>\n"
                        + "                                        <td style=\"color:#114A3D;text-align: right\"> </td> \n"
                        + "                                    </tr>\n"
                        + "                                    <tr >\n"
                        + "                                        <td style=\"color:#114A3D \">"+ listaProdutos.get(i).getValorcoprodutos().getCoprodutos().getDescricao() +"</td>\n"
                        + "                                        <td style=\"color:#114A3D;text-align: right\">"+ listaProdutos.get(i).getValorcoprodutos().getValororiginal() +"</td> \n"
                        + "                                    </tr>\n"
                        + "                                </table>\n"
                        + "                                <br></br> <br></br> <br></br>";
            }
            if (listaProdutos.get(i).getValorcoprodutos().getCoprodutos().getProdutosorcamento().getDescricao().equalsIgnoreCase("S")) {
                corpo = corpo + "<strong style= \"margin-left:5.3%;;font-size: 17px;margin-top:2%;color: #257C67;\">Seguro Viagem</strong><br/>\n"
                        + "                                 <table style=\"width:89%;margin-left: 5%;\" class=\"CSSTableGenerator\">\n"
                        + "                                    <tr >\n"
                        + "                                        <td style=\"color:#114A3D;font-size: 10px\">Plano</td>\n"
                        + "                                        <td style=\"color:#114A3D;font-size: 10px\">Nº de Semanas</td>\n"
                        + "                                        <td style=\"color:#114A3D;font-size: 10px\">Valor</td>\n"
                        + "                                    </tr>\n"
                        + "                                     <tr >\n"
                        + "                                        <td style=\"color:#114A3D;font-size: 10px\">"+ listaProdutos.get(i).getValorcoprodutos().getCoprodutos().getDescricao() +"</td>\n"
                        + "                                        <td style=\"color:#114A3D;font-size: 10px;width:85px;text-align: center;\">"+ listaProdutos.get(i).getNumerosemanas() +"</td>\n"
                        + "                                        <td style=\"color:#114A3D;font-size: 10px;text-align: right;width:155px; \">"+ listaProdutos.get(i).getValorcoprodutos().getValororiginal() +"</td>\n"
                        + "                                    </tr>\n"
                        + "                                </table>\n"
                        + "                                <br></br> <br></br> <br></br>";
            }
            if (listaProdutos.get(i).getValorcoprodutos().getCoprodutos().getProdutosorcamento().getDescricao().equalsIgnoreCase("T")) {
                corpo = corpo + "<strong style= \"margin-left:5.3%;;font-size: 17px;margin-top: -3%;color: #257C67;\">Transfer</strong><br />\n"
                        + "                                <table style=\"width:89%;margin-left: 5%;\" class=\"CSSTableGenerator\">\n"
                        + "                                    <tr >\n"
                        + "                                        <td style=\"color:#AFCA0B;font-size: 9px;\"> t</td>\n"
                        + "                                        <td style=\"color:#114A3D;text-align: right;\"> </td> \n"
                        + "                                    </tr>\n"
                        + "                                    <tr >\n"
                        + "                                        <td style=\"color:#114A3D \">"+ listaProdutos.get(i).getValorcoprodutos().getCoprodutos().getDescricao() +"</td>\n"
                        + "                                        <td style=\"color:#114A3D;text-align: right;width: 95px\">"+ listaProdutos.get(i).getValorcoprodutos().getValororiginal() +"</td> \n"
                        + "                                    </tr>\n"
                        + "                                </table>\n"
                        + "                                 <br></br><br></br><br></br>";
            }
        corpo = corpo + "<table style=\"width:89%;margin-left: 5%;width:40%;margin-left:54%\" class=\"CSSTableGenerator\">\n"
                + "                                    <tr >\n"
                + "                                        <td style=\"color:#257C67;font-size: 17px;background: #AFCA0B\">Totais</td>\n"
                + "                                         <td style=\"font-size: 9px;background: #AFCA0B\"> </td>\n"
                + "                                    </tr>\n"
                + "                                    <tr >\n"
                + "                                        <td style=\"color:#114A3D;font-size: 14px\">Total</td>\n"
                + "                                        <td style=\"color:#114A3D;text-align: right;width:120px;font-size: 14px\">US$ 380,00</td> \n"
                + "                                    </tr>\n"
                + "                                     <tr style=\"background:#fff \">\n"
                + "                                        <td style=\"color:#114A3D;font-size: 14px\">Total em Reais</td>\n"
                + "                                        <td style=\"color:#114A3D;text-align: right;width: 120px;font-size: 14px\">R$ 780,00</td> \n"
                + "                                    </tr>\n"
                + "                                </table>\n"
                + "                                 <strong style= \"margin-left:5.3%;;font-size: 17px;margin-top:-3%;color: #257C67;\">Forma de Pagamento</strong><br />\n"
                + "                                 <table style=\"width:89%;margin-left: 5%;\" class=\"CSSTableGenerator\">\n"
                + "                                    <tr >\n"
                + "                                        <td style=\"color:#AFCA0B;font-size: 9px;\"> t</td>\n"
                + "                                    </tr>\n"
                + "                                    <tr >\n"
                + "                                        <td style=\"color:#114A3D \">Pagamento á vista  "+ ocurso.getTotalmoedaestrangeira() +"</td>\n"
                + "                                    </tr>\n"
                + "                                    <tr >\n"
                + "                                        <td style=\"color:#114A3D \">Parcelamento antes da viagem</td>\n"
                + "                                    </tr>\n"
                + "                                     <tr >\n"
                + "                                        <td style=\"color:#114A3D \">Parcelamento em cartão de crédito em até 12x</td>\n"
                + "                                    </tr>\n"
                + "                                     <tr >\n"
                + "                                        <td style=\"color:#114A3D \">Parcelamento em cheque, boleto ou débito em conta em até 24X via financeira*</td>\n"
                + "                                    </tr>\n"
                
                + "                                </table>\n"
                + "                                 <br></br>     <br></br>\n"
                + "</span></p></td><br></br> </tr></table> </td> </tr> <tr><td>\n"
                + "<div align= \"center \"><table border= \"0 \" cellspacing= \"0 \" cellpadding= \"0 \" width= \"100% \">\n"
                + "<tr> <td>&nbsp;</td> </tr></table></div></td> </tr> </table></td></tr></table><br></br><br></br></body></html>";
    }
    }
    
    public void enviarEmail() {
        email = new HtmlEmail();
        email.setHostName("smtp.travelmate.com.br");
        //email.setStartTLSEnabled(true);
        email.setSmtpPort(587);
        //email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("ti@travelmate.com.br", "20SimpleS78"));
        try {
            email.setFrom("systm@travelmate.com.br");
            listarDestinos();
        } catch (EmailException ex) {
            Logger.getLogger(EnviarEmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        email.setSubject("Orçamento de Curso");
        try {
            email.setHtmlMsg(corpo);
        } catch (EmailException ex) {
            Logger.getLogger(EnviarEmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EnviarEmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarDestinos() {
        try {
            email.addTo(ocurso.getCliente().getEmail());
        } catch (EmailException ex) {
            Logger.getLogger(EnviarEmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
