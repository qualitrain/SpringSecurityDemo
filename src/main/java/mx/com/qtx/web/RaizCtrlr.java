package mx.com.qtx.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mx.com.qtx.rest.CatalogosRest;

@Controller
public class RaizCtrlr {

	private static Logger bitacora = LoggerFactory.getLogger(RaizCtrlr.class);
	
	public RaizCtrlr() {
		bitacora.info("Controlador instanciado:RaizCtrlr()");
	}
	@GetMapping("/")
	public String getVistaRaiz(Model modelo) {
		return "vistaRaiz";
	}
	@GetMapping("/admin")
	public String getVistaAdmin(Model modelo) {
		return "vistaAdmin";
	}
	@GetMapping("/logistica")
	public String getVistaLogistica(Model modelo) {
		return "vistaLogistica";
	}
	
	@GetMapping("/info")
	public String getVistaInfo(Model modelo) {
		return "vistaInfo";
	}
}
