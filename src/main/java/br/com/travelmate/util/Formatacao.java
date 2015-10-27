/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;

import org.primefaces.model.UploadedFile;

import br.com.travelmate.facade.CambioFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Produtos;

/**
 *
 * @author Wolverine
 */
public class Formatacao {

    
    public static String ConvercaoDataSql(Date data) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String dataFormatada = df.format(data);
        return dataFormatada;
    }
    
    public static String ConvercaoDataDDMMAA(Date data) {
        DateFormat df = new SimpleDateFormat("ddMMyy");
        String dataFormatada = df.format(data);
        return dataFormatada;
    }
    
    public static String ConvercaoDataDDMMAAAA(Date data) {
        DateFormat df = new SimpleDateFormat("ddMMyyyy");
        String dataFormatada = df.format(data);
        return dataFormatada;
     }

    public static String ConvercaoDataPadrao(Date data) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = df.format(data);
        return dataFormatada;
    }

    public static String formatarFloatString(Float valor){
        NumberFormat format = new DecimalFormat("#,##0.00");
        format.setMinimumFractionDigits(2);
        String valorFormatado = format.format(valor);
        return valorFormatado;
    }

    public static String formatarDoubleString(Double valor){
        NumberFormat format = new DecimalFormat("#,##0.00");
        format.setMinimumFractionDigits(2);
        String valorFormatado = format.format(valor);
        return valorFormatado;
    }

    public static String formatarValorCambio(Float valor){
        NumberFormat format = new DecimalFormat("#,##0.0000");
        format.setMinimumFractionDigits(4);
        String valorFormatado = format.format(valor);
        return valorFormatado;
    }

    public static Float formatarStringfloat(String valor){
        String novoValor = "";
        for(int i=0;i<valor.length();i++){
            if (valor.charAt(i)==','){
                novoValor = novoValor + ".";
            }else if (!(valor.charAt(i)=='.')){
                novoValor = novoValor + valor.charAt(i);
            }
        }
        return Float.parseFloat(novoValor);
    }

    public static Double formatarStringDouble(String valor) {
        String novoValor = "";
        for (int i = 0; i < valor.length(); i++) {
            if (valor.charAt(i) == ',') {
                novoValor = novoValor + ".";
            } else if (!(valor.charAt(i) == '.')) {
                novoValor = novoValor + valor.charAt(i);
            }
        }
        return Double.parseDouble(novoValor);
    }

    public static JComboBox preencherComobox(List lista, JComboBox combo, boolean nulo, String valorNulo){
        if (nulo){
            combo.addItem(valorNulo);
        }
        for (int i=0;i<lista.size();i++){
            combo.addItem(lista.get(i));
        }
        return combo;
    }


  public static Integer calcularNumeroSemanas(Date dataInicial, Date dataFinal){
      int resultado =0;
      resultado =  (int)((dataFinal.getTime() - dataInicial.getTime()) / 86400000L);
      int numeroSemanas = (resultado % 7);
      if (numeroSemanas>0){
          numeroSemanas = resultado / 7;
          numeroSemanas = numeroSemanas + 1;
      }
      return numeroSemanas;
  }

  public static Date calcularDataFinal(Date dataInicial, int numeroSemanas, String tipo){
       SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
       Calendar c = new GregorianCalendar();
       c.setTime(dataInicial);
       if (tipo.equalsIgnoreCase("semana")) {
          numeroSemanas = numeroSemanas * 7;
          numeroSemanas = numeroSemanas - 1;
      }
       if (numeroSemanas>0){
            c.add(Calendar.DAY_OF_MONTH, numeroSemanas);
       }
       return (c.getTime());
  }

  public static String valorPorExtenso(double vlr) {
    if (vlr == 0)
       return("ZERO");
    String ve = String.valueOf(vlr);
    String nve = "";
    for (int i=0;i<ve.length();i++){
        if (ve.charAt(i)!='.'){
            nve = nve + ve.charAt(i);
        }else {
            int numeroI = ve.length()-i;
            if (numeroI>3){
                nve = nve + ve.charAt(i) + ve.substring(i+1, i+4);
            }else {
                if (numeroI==2){
                    nve = nve + ve.charAt(i) + ve.substring(i+1, i+2);
                }else {
                    if (numeroI==3){
                        nve = nve + ve.charAt(i) + ve.substring(i+1, i+3);
                    }
                }
            }

            i=1000;
        }
    }
    vlr = Double.parseDouble(nve);
    long inteiro = (long)Math.abs(vlr); // parte inteira do valor
    double resto = vlr - inteiro;       // parte fracionária do valor
    String vresto = String.valueOf(resto);
    if (vresto.length()>5){
        vresto = vresto.substring(0, 5);
    }
    double valorvResto = Double.parseDouble(vresto);
    if (valorvResto>0.990){
        resto =0.0;
        inteiro = inteiro + 1;
    }else{
        resto = Double.parseDouble(vresto);
    }



    String vlrS = String.valueOf(inteiro);
    if (vlrS.length() > 15)
       return("Erro: valor superior a 999 trilhões.");

    String s = "", saux, vlrP;
    String centavos = String.valueOf((int)Math.round(resto * 100));

    String[] unidade = {"", "UM", "DOIS", "TRÊS", "QUATRO", "CINCO",
             "SEIS", "SETE", "OITO", "NOVE", "DEZ", "ONZE",
             "DOZE", "TREZE", "QUATORZE", "QUINZE", "DEZESSEIS",
             "DEZESSETE", "DEZOITO", "DEZENOVE"};
    String[] centena = {"", "CENTO", "DUZENTOS", "TREZENTOS",
             "QUATROCENTOS", "QUINHENTOS", "SEISCENTOS",
             "SETECENTOS", "OITOCENTOS", "NOVECENTOS"};
    String[] dezena = {"", "", "VINTE", "TRINTA", "QUARENTA", "CINQUENTA",
             "SESSENTA", "SETENTA", "OITENTA", "NOVENTA"};
    String[] qualificaS = {"", "MIL", "MILHÃO", "BILHÃO", "TRILHÃO"};
    String[] qualificaP = {"", "MIL", "MILHÕES", "BILHÕES", "TRILHÕES"};


// definindo o extenso da parte inteira do valor
    int n, unid, dez, cent, tam, i = 0;
    boolean umReal = false, tem = false;
    while (!vlrS.equals("0")) {
      tam = vlrS.length();
// retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
// 1a. parte = 789 (centena)
// 2a. parte = 456 (mil)
// 3a. parte = 123 (milhões)
      if (tam > 3) {
         vlrP = vlrS.substring(tam-3, tam);
         vlrS = vlrS.substring(0, tam-3);
      }
      else { // última parte do valor
        vlrP = vlrS;
        vlrS = "0";
      }
      if (!vlrP.equals("000")) {
         saux = "";
         if (vlrP.equals("100"))
            saux = "CEM";
         else {
           n = Integer.parseInt(vlrP, 10);  // para n = 371, tem-se:
           cent = n / 100;                  // cent = 3 (centena trezentos)
           dez = (n % 100) / 10;            // dez  = 7 (dezena setenta)
           unid = (n % 100) % 10;           // unid = 1 (unidade um)
           if (cent != 0)
              saux = centena[cent];
           if ((n % 100) <= 19) {
              if (saux.length() != 0)
                 saux = saux + " E " + unidade[n % 100];
              else saux = unidade[n % 100];
           }
           else {
              if (saux.length() != 0)
                 saux = saux + " E " + dezena[dez];
              else saux = dezena[dez];
              if (unid != 0) {
                 if (saux.length() != 0)
                    saux = saux + " E " + unidade[unid];
                 else saux = unidade[unid];
              }
           }
         }
         if (vlrP.equals("1") || vlrP.equals("001")) {
            if (i == 0) // 1a. parte do valor (um real)
               umReal = true;
            else saux = saux + " " + qualificaS[i];
         }
         else if (i != 0)
                 saux = saux + " " + qualificaP[i];
         if (s.length() != 0)
            s = saux + ", " + s;
         else s = saux;
      }
      if (((i == 0) || (i == 1)) && s.length() != 0)
         tem = true; // tem centena ou mil no valor
      i = i + 1; // próximo qualificador: 1- mil, 2- milhão, 3- bilhão, ...
    }

    if (s.length() != 0) {
       if (umReal)
          s = s + " REAL";
       else if (tem)
               s = s + " REAIS";
            else s = s + " DE REAIS";
    }

// definindo o extenso dos centavos do valor
    if (!centavos.equals("0")) { // valor com centavos
       if (s.length() != 0) // se não é valor somente com centavos
          s = s + " E ";
       if (centavos.equals("1"))
          s = s + "UM CENTAVO";
       else {
         n = Integer.parseInt(centavos, 10);
         if (n <= 19)
            s = s + unidade[n];
         else {             // para n = 37, tem-se:
           unid = n % 10;   // unid = 37 % 10 = 7 (unidade sete)
           dez = n / 10;    // dez  = 37 / 10 = 3 (dezena trinta)

           s = s + dezena[dez];
           if (unid != 0)
              s = s + " E " + unidade[unid];
         }
         s = s + " CENTAVOS";
       }
    }
    return(s);
  }

  public static boolean validaCPF(String s_aux) {
        String ncpf = "";
        for (int i=0;i<s_aux.length();i++){
           if ((s_aux.charAt(i)!='.') && (s_aux.charAt(i)!='-')){
                ncpf+=s_aux.charAt(i);
           }
        }
        s_aux=ncpf;

//------- Rotina para CPF
        if (s_aux.length() == 11) {
            int d1, d2;
            int digito1, digito2, resto;
            int digitoCPF;
            String nDigResult;
            d1 = d2 = 0;
            digito1 = digito2 = resto = 0;
            for (int n_Count = 1; n_Count < s_aux.length() - 1; n_Count++) {
                digitoCPF = Integer.valueOf(s_aux.substring(n_Count - 1, n_Count)).intValue();
//--------- Multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
                d1 = d1 + (11 - n_Count) * digitoCPF;
//--------- Para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
                d2 = d2 + (12 - n_Count) * digitoCPF;
            }

//--------- Primeiro resto da divisão por 11.
            resto = (d1 % 11);
//--------- Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
            if (resto < 2) {
                digito1 = 0;
            } else {
                digito1 = 11 - resto;
            }
            d2 += 2 * digito1;
//--------- Segundo resto da divisão por 11.
            resto = (d2 % 11);
//--------- Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
            if (resto < 2) {
                digito2 = 0;
            } else {
                digito2 = 11 - resto;
            }
//--------- Digito verificador do CPF que está sendo validado.
            String nDigVerific = s_aux.substring(s_aux.length() - 2, s_aux.length());
//--------- Concatenando o primeiro resto com o segundo.
            nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
//--------- Comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
            return nDigVerific.equals(nDigResult);
        } //-------- Rotina para CNPJ
        else if (s_aux.length() == 14) {
            int soma = 0;
            int aux=0;
            int dig=0;
            String cnpj_calc = s_aux.substring(0, 12);
            char[] chr_cnpj = s_aux.toCharArray();
//--------- Primeira parte
            for (int i = 0; i < 4; i++) {
                if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                    soma += (chr_cnpj[i]) - 4 * (6 - (i + 1));
                }
            }
            for (int i = 0; i < 8; i++) {
                if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                    soma += (chr_cnpj[i + 4]) - 4 * (10 - (i + 1));
                }
            }
            dig = 11 - (soma % 11);
            cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
//--------- Segunda parte
            soma = 0;
            for (int i = 0; i < 5; i++) {
                if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                    soma += (chr_cnpj[i]) - 4 * (7 - (i + 1));
                }
            }
            for (int i = 0; i < 8; i++) {
                if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                    soma += (chr_cnpj[i + 5]) - 4 * (10 - (i + 1));
                }
            }
            dig = 11 - (soma % 11);
            cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
            return s_aux.equals(cnpj_calc);
        } else {
            return false;
        }
    }



    public static Date SomarDiasDatas(Date data, int dias) throws Exception{
       Calendar c = new GregorianCalendar();
       c.setTime(data);
       c.add(Calendar.DAY_OF_MONTH, dias);
       return c.getTime();
  }
  


    public static String SubtarirDatas(Date data, int dias, String formato){
       SimpleDateFormat sd = new SimpleDateFormat(formato);
       Calendar c = new GregorianCalendar();
       c.setTime(data);
       int dobroDias = dias * 2;
       int contaDias = dias - dobroDias;
       c.add(Calendar.DAY_OF_MONTH, contaDias);
       return sd.format(c.getTime());
  }

  public static String retornaDataInicia(int mes){
      if (mes==1){
          return "01-01";
      }else if (mes==2){
          return "02-01";
      }else if (mes==3){
          return "02-01";
      }else if (mes==4){
          return "04-01";
      }else if (mes==5){
          return "05-01";
      }else if (mes==6){
          return "06-01";
      }else if (mes==7){
          return "07-01";
      }else if (mes==8){
          return "08-01";
      }else if (mes==9){
          return "09-01";
      }else if (mes==10){
          return "10-01";
      }else if (mes==11){
          return "11-01";
      }else if (mes==12){
          return "12-01";
      }else return "0";
  }

  public static String retornaDataFinal(int mes){
      if (mes==1){
          return "01-31";
      }else if (mes==2){
          return "02-28";
      }else if (mes==3){
          return "02-31";
      }else if (mes==4){
          return "04-30";
      }else if (mes==5){
          return "05-31";
      }else if (mes==6){
          return "06-30";
      }else if (mes==7){
          return "07-31";
      }else if (mes==8){
          return "08-31";
      }else if (mes==9){
          return "09-30";
      }else if (mes==10){
          return "10-31";
      }else if (mes==11){
          return "11-30";
      }else if (mes==12){
          return "12-31";
      }else return "0";
  }

   public static String retornaDataFinalBarras(int mes){
      if (mes==1){
          return "01/31";
      }else if (mes==2){
          return "02/28";
      }else if (mes==3){
          return "02/31";
      }else if (mes==4){
          return "04/30";
      }else if (mes==5){
          return "05/31";
      }else if (mes==6){
          return "06/30";
      }else if (mes==7){
          return "07/31";
      }else if (mes==8){
          return "08/31";
      }else if (mes==9){
          return "09/30";
      }else if (mes==10){
          return "10/31";
      }else if (mes==11){
          return "11/30";
      }else if (mes==12){
          return "12/31";
      }else return "0";
  }

  public static String nomeMes(int mes){
      if (mes==1){
          return "JANEIRO";
      }else if (mes==2){
          return "FEVEREIRO";
      }else if (mes==3){
          return "MARÇO";
      }else if (mes==4){
          return "ABRIL";
      }else if (mes==5){
          return "MAIO";
      }else if (mes==6){
          return "JUNHO";
      }else if (mes==7){
          return "JULHO";
      }else if (mes==8){
          return "AGOSTO";
      }else if (mes==9){
          return "SETEMBRO";
      }else if (mes==10){
          return "OUTUBRO";
      }else if (mes==11){
          return "NOVEMBRO";
      }else if (mes==12){
          return "DEZEMBRO";
      }else return "nao encontrado";
  }

  public static int subtrairDatas(Date dataInicial, Date dataFinal){
      int resultado =0;
      resultado =  (int)((dataInicial.getTime() - dataFinal.getTime()) / 86400000L);
      return resultado;
  }

  public static String foramtarHoraString(){
      DateFormat formato = new SimpleDateFormat("HH:mm:ss");
      String formattedDate = formato.format(new Date());
      return formattedDate;
  }

  public static int retornaNumeroMes(String mes){
      if (mes.equalsIgnoreCase("Janeiro")){
          return 1;
      }else if (mes.equalsIgnoreCase("Fevereiro")){
          return 2;
      }else if (mes.equalsIgnoreCase("Março")){
          return 3;
      }else if (mes.equalsIgnoreCase("Abril")){
          return 4;
      }else if (mes.equalsIgnoreCase("Maio")){
          return 5;
      }else if (mes.equalsIgnoreCase("Junho")){
          return 6;
      }else if (mes.equalsIgnoreCase("Julho")){
          return 7;
      }else if (mes.equalsIgnoreCase("Agosto")){
          return 8;
      }else if (mes.equalsIgnoreCase("Setembro")){
          return 9;
      }else if (mes.equalsIgnoreCase("Outubro")){
          return 10;
      }else if (mes.equalsIgnoreCase("Novembro")){
          return 11;
      }else if (mes.equalsIgnoreCase("Dezembro")){
          return 12;
      }else return 0;
  }

  public static int diaSemana(Date data){
       Calendar calendario = new GregorianCalendar();
       calendario.setTime(data);
       int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
       return diaSemana;
  }

  public static Date ConvercaoStringData(String data){
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Date dataFormatada = null;
        try {
            dataFormatada = df.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Formatacao.class.getName()).log(Level.SEVERE, null, ex);
        }
      return dataFormatada;
  }

    public static Time converterStringHora(String hora) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        Date data = formatador.parse(hora);
        Time time = new Time(data.getTime());
        return time;
    }

    public static String calcularIdade(Date dataNascimento){
        String dn = Formatacao.ConvercaoDataSql(dataNascimento);
        String da = Formatacao.ConvercaoDataSql(new Date());
        String an =  dn.substring(0, 4);
        String aa = da.substring(0,4);
        int anoNascimento = Integer.parseInt(an);
        int anoAtual = Integer.parseInt(aa);
        if (dataNascimento.after(dataNascimento)){
            anoAtual = anoAtual - 1;
        }
        int idade = anoAtual - anoNascimento;
        return String.valueOf(idade);
    }




//    public static void emitirEmailGerencial(String clietne, String unidade, String consultor, Vendas venda, String texto01, String valor, String tipoAviso, String valorCambio, String moeda,String fornecedor,  String obsTM, String tipoVenda, String motivo){
//        Formatacao formatacao = new Formatacao();
//        String texto= texto01 + "\r\n";
//        texto = texto + "Unidade           : " + unidade + "\r\n";
//        texto = texto + "Cliente           : " + clietne + "\r\n";
//        texto = texto + "Consultor         : " + consultor + "\r\n";
//        texto = texto + "Valor da Venda R$ : " + valor + "\r\n";
//        texto = texto + "Valor Câmbio R$   : " + valorCambio + "\r\n";
//        texto = texto + "Moeda             : " + moeda + "\r\n";
//        texto = texto + tipoVenda + "\r\n";
//        texto = texto + "\r\n";
//        texto = texto  + "\r\n";
//        texto = texto + "Motivo            : " + motivo + "\r\n";
//        texto = texto  + "Observações TM\r\n";
//        texto = texto + obsTM;
//        formatacao.enviarEmailNotificacaoProduto(venda.getProdutos(), texto, tipoAviso);
//    }










    public static String getCaminho(String caminho){
    // vamos obter o índice da última aparição do separador de caminho
    int pos = caminho.lastIndexOf(File.separatorChar);
    if (pos > -1) {
       return caminho.substring(0, pos);
    }
    // por padrão vamos retornar uma string vazia
    return "";
  }

  public static String carregarCambioDia() {
        String scambio=null;
        String data = Formatacao.ConvercaoDataSql(new Date());
        CambioFacade cambioFacade = new CambioFacade();
        List<Cambio>listaCambio = listaCambio = cambioFacade.listar(data);
        int contador = 0;
        if (listaCambio == null) {
            while (listaCambio == null){
                try {
                    data = Formatacao.SubtarirDatas(new Date(), contador, "yyyy/MM/dd");
                } catch (Exception ex) {
                    Logger.getLogger(Formatacao.class.getName()).log(Level.SEVERE, null, ex);
                }
                listaCambio = cambioFacade.listar(data);
                contador++;
            }
        }
        String iata = "5,000";
        String usd = "0,000";
        String gbp = "0,000";
        String cad = "0,000";
        String aud = "0,000";
        String nzd = "0,000";
        String chf = "0,000";
        String euro = "0,000";
        if (listaCambio!=null){
            Date dataCambio = listaCambio.get(0).getData();
            for(int i=0;i<listaCambio.size();i++){
                if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("IATA")){
                    iata = Formatacao.formatarValorCambio(listaCambio.get(i).getValor());
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("USD")){
                    usd = Formatacao.formatarValorCambio(listaCambio.get(i).getValor());
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("EUR")){
                    euro = Formatacao.formatarValorCambio(listaCambio.get(i).getValor());
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("GBP")){
                    gbp = Formatacao.formatarValorCambio(listaCambio.get(i).getValor());
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("cad")){
                    cad = Formatacao.formatarValorCambio(listaCambio.get(i).getValor());
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("aud")){
                    aud = Formatacao.formatarValorCambio(listaCambio.get(i).getValor());
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("nzd")){
                    nzd = Formatacao.formatarValorCambio(listaCambio.get(i).getValor());
                }else if (listaCambio.get(i).getMoedas().getSigla().equalsIgnoreCase("chf")){
                    chf = (Formatacao.formatarValorCambio(listaCambio.get(i).getValor()));
                }
            }
        }
        scambio = "IATA " + iata + "   UDS " + usd + "   EUR " + euro  + "   GBP " + gbp + "   CAD " + cad + "   AUD " + aud + "   NZD " + nzd + "   CHF " + chf;
        return scambio;
    }
  
  public static Date ConvercaoStringDataBrasil(String data) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada = null;
        try {
            dataFormatada = df.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Formatacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataFormatada;
    }
  
    public static String gerarNumeroDocumentoBoleto(String numeroVenda, String numeroParcela){
        if (numeroParcela.length()==1){
            numeroParcela = "0" + numeroParcela;
        }
        String numero = numeroVenda + numeroParcela;
        for(int i=0;i<8;i++){
            if (numero.length()<8){
                numero= "0" + numero;
            }else {
                i=100;
            }
        }
        return numero;
    }
    
    public static BigDecimal converterFloatBigDecimal(Float valor){
        Double dobuleValor = valor.doubleValue();
        BigDecimal bigDecimalValor = new BigDecimal(dobuleValor);
        return bigDecimalValor;
    }
    
    public static String retirarPontos(String dado){
        String formatado = "";
        char c = ' ';
        if (dado != null) {
            for (int i = 0; i < dado.length(); i++) {
                c = dado.charAt(i);
                if ((c != '.') && (c != ',') && (c != '-') && (c != '/') && (c != '(') && (c != ')')) {
                    formatado += c;
                }
            }
        }
        return formatado;
    }
    
    public static BufferedReader converterUploadedFileToFile(UploadedFile uploadedFile) throws Exception {
        InputStream is = uploadedFile.getInputstream();
        BufferedReader bfReader = null;
        bfReader = new BufferedReader(new InputStreamReader(is));
        return bfReader;
    }
    
    public static Date formatarDataAgora(){
        Date data = new Date();
        String sData = Formatacao.ConvercaoDataPadrao(data);
        data = Formatacao.ConvercaoStringData(sData);
        return data;
    }
    
   
}
