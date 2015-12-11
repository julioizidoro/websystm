package br.com.travelmate.managerBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class PlanoContasMB implements Serializable{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String novo(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",400);
        RequestContext.getCurrentInstance().openDialog("planoconta", options, null);
        return "";
    }
    
}
