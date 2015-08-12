package br.com.travelmate.bean;



import br.com.travelmate.util.GerarDacNossoNumero;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

public class DadosBoletoBean implements Serializable{
    
    private String nomeCedente;
    private String cnpjCedente;
    private String nomeSacado;
    private String digitoAgencias;
    private String agencias;
    private String numeroContas;
    private String digitoContas;
    private String carteiras;
    private BigDecimal valor;
    private Date dataDocumento;
    private Date dataVencimento;
    private int codigoVenda;
    private String nossoNumeros;
    private String numeroDocumentos;
    private String digitoNossoNumeros;
    private Boleto boleto;   
    private Endereco enderecoSacado;
    
    public String getNossoNumeros() {
        return nossoNumeros;
    }

    public void setNossoNumeros(String nossoNumeros) {
        this.nossoNumeros = nossoNumeros;
    }

    public String getNumeroDocumentos() {
        return numeroDocumentos;
    }

    public void setNumeroDocumentos(String numeroDocumentos) {
        this.numeroDocumentos = numeroDocumentos;
    }

    public String getDigitoNossoNumeros() {
        return digitoNossoNumeros;
    }

    public void setDigitoNossoNumeros(String digitoNossoNumeros) {
        this.digitoNossoNumeros = digitoNossoNumeros;
    }

    public Endereco getEnderecoSacado() {
        return enderecoSacado;
    }

    public void setEnderecoSacado(Endereco enderecoSacado) {
        this.enderecoSacado = enderecoSacado;
    }

    

    public String getNomeCedente() {
        return nomeCedente;
    }

    public void setNomeCedente(String nomeCedente) {
        this.nomeCedente = nomeCedente;
    }

    public String getCnpjCedente() {
        return cnpjCedente;
    }

    public void setCnpjCedente(String cnpjCedente) {
        this.cnpjCedente = cnpjCedente;
    }

    public String getNomeSacado() {
        return nomeSacado;
    }

    public void setNomeSacado(String nomeSacado) {
        this.nomeSacado = nomeSacado;
    }


    public String getDigitoAgencias() {
        return digitoAgencias;
    }

    public void setDigitoAgencias(String digitoAgencias) {
        this.digitoAgencias = digitoAgencias;
    }

    public String getAgencias() {
        return agencias;
    }

    public void setAgencias(String agencias) {
        this.agencias = agencias;
    }

    public String getNumeroContas() {
        return numeroContas;
    }

    public void setNumeroContas(String numeroContas) {
        this.numeroContas = numeroContas;
    }
    public String getDigitoContas() {
        return digitoContas;
    }

    public void setDigitoContas(String digitoContas) {
        this.digitoContas = digitoContas;
    }

    public String getCarteiras() {
        return carteiras;
    }

    public void setCarteiras(String carteiras) {
        this.carteiras = carteiras;
    }

   

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }
  
    
    public byte[] gerarBoleto() {
        criarBoleto();
        BoletoViewer boletoViewer = new BoletoViewer(boleto);
        return boletoViewer.getPdfAsByteArray();
    }
    
    
    public File gerarBoletoEmArquivo(String arquivo) {
        criarBoleto();
        BoletoViewer boletoViewer = new BoletoViewer(boleto);
        return boletoViewer.getPdfAsFile(arquivo);
    }
    
    public void criarBoleto() {
        GerarDacNossoNumero dac = new GerarDacNossoNumero(nossoNumeros,carteiras, agencias, numeroContas);
        this.digitoNossoNumeros = dac.getDac();
        ContaBancaria contaBancaria = criarContaBancaria();
        Sacado sacado = new Sacado(nomeSacado);
        sacado.addEndereco(enderecoSacado);
        Cedente cedente = new Cedente(nomeCedente, cnpjCedente);

        Titulo titulo = criarTitulo(contaBancaria, sacado, cedente);
        boleto = new Boleto(titulo);
        boleto.setInstrucaoAoSacado("Instruções de responsabilidade do BENEFIÁRIO. Qualquer dúvida sobre este boleto, contate o BENEFICIÁRIO.");
        String nossoNumeroExibicao = carteiras + "/" + nossoNumeros + "-" + digitoNossoNumeros;
        boleto.addTextosExtras("txtFcNossoNumero", nossoNumeroExibicao);
        boleto.addTextosExtras("txtRsNossoNumero", nossoNumeroExibicao);
        boleto.setInstrucao3("ATÉ O VENCIMENTO PAGUE PREFERENCIALMENTE NO ITAÚ");
        boleto.setInstrucao4("APÓS O VENCIMENTO PAGUE SOMENTE NO ITAÚ");
    }
    
    
    private ContaBancaria criarContaBancaria() {
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_ITAU.create());
        contaBancaria.setAgencia(new Agencia(Integer.valueOf(agencias), digitoAgencias));
        contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.valueOf(numeroContas), digitoContas));
        contaBancaria.setCarteira(new Carteira(Integer.valueOf(carteiras)));
        return contaBancaria;
    }
    
    private Titulo criarTitulo(ContaBancaria contaBancaria, Sacado sacado, Cedente cedente) {
        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);

//        String codigo = this.geradorDigitoVerificador.completarComZeros(String.valueOf(codigoVenda));
       titulo.setNumeroDoDocumento(numeroDocumentos);
       titulo.setNossoNumero(nossoNumeros);
        titulo.setDigitoDoNossoNumero(digitoNossoNumeros);
        titulo.setValor(valor);
        titulo.setDataDoDocumento(dataDocumento);
        titulo.setDataDoVencimento(dataVencimento);
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(Titulo.Aceite.N);
        return titulo;
    }
    
    private void enviarBoleto(byte[] pdf) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=boleto" + codigoVenda + ".pdf");

        try {
            OutputStream output = response.getOutputStream();
            output.write(pdf);
            response.flushBuffer();
        } catch (Exception e) {
            throw new RuntimeException("Erro gerando boleto", e);
        }

        FacesContext.getCurrentInstance().responseComplete();
    }
    

    public void emitir() {
        byte[] pdf = gerarBoleto();
        enviarBoleto(pdf);
    }
    
    public void gerarPDFS(List<Boleto> listaBoletos){
        File arquivo = new File("C:\\Julio\\boletos.pdf");
        BoletoViewer.groupInOnePDF(listaBoletos, arquivo);
    }
}
