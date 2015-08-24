package br.com.travelmate.managerBean;
 
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
 
@ManagedBean
public class mapaMB implements Serializable {
    
    private MapModel marcacao;
  
    @PostConstruct
    public void init() {
        marcacao = new DefaultMapModel();
        
        LatLng coordenada = new LatLng(40.754778, -73.986508);
          
        
        marcacao.addOverlay(new Marker(coordenada, "EC"));
    }

    public MapModel getMarcacao() {
        return marcacao;
    }

}