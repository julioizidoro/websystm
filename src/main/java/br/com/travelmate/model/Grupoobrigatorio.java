package br.com.travelmate.model;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@Table(name = "grupoobrigatorio")
@NamedQueries({
    @NamedQuery(name = "Grupoobrigatorio.findAll", query = "SELECT g FROM Grupoobrigatorio g")})
	public class Grupoobrigatorio implements Serializable {
	    private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "idgrupoobrigatorio")
	    private Integer idgrupoobrigatorio;
	    @JoinColumn(name = "coprodutos_idcoprodutos", referencedColumnName = "idcoprodutos")
	    @ManyToOne(optional = false)
	    private Coprodutos coprodutos;
	    @JoinColumn(name = "produtosOrcamento_idprodutosOrcamento", referencedColumnName = "idprodutosOrcamento")
	    @ManyToOne(optional = false)
	    private Produtosorcamento produtosorcamento;

	    public Grupoobrigatorio() {
	    }
	
	    public Grupoobrigatorio(Integer idgrupoobrigatorio) {
	        this.idgrupoobrigatorio = idgrupoobrigatorio;
	    }
	
	    public Integer getIdgrupoobrigatorio() {
	        return idgrupoobrigatorio;
	    }
	
	    public void setIdgrupoobrigatorio(Integer idgrupoobrigatorio) {
	        this.idgrupoobrigatorio = idgrupoobrigatorio;
	    }
	
	    public Coprodutos getCoprodutos() {
	        return coprodutos;
	    }
	
	    public void setCoprodutos(Coprodutos coprodutos) {
	        this.coprodutos = coprodutos;
	    }
	
	    public Produtosorcamento getProdutosorcamento() {
	        return produtosorcamento;
	    }
	
	    public void setProdutosorcamento(Produtosorcamento produtosorcamento) {
	        this.produtosorcamento = produtosorcamento;
	    }
	
	    @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (idgrupoobrigatorio != null ? idgrupoobrigatorio.hashCode() : 0);
	        return hash;
	    }
	
	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof Grupoobrigatorio)) {
	            return false;
	        }
	        Grupoobrigatorio other = (Grupoobrigatorio) object;
	        if ((this.idgrupoobrigatorio == null && other.idgrupoobrigatorio != null) || (this.idgrupoobrigatorio != null && !this.idgrupoobrigatorio.equals(other.idgrupoobrigatorio))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "br.com.travelmate.model.Grupoobrigatorio[ idgrupoobrigatorio=" + idgrupoobrigatorio + " ]";
	    }
    
}