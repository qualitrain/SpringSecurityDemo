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
		bitacora.info("instancia creada:RaizCtrlr()");
	}
	@GetMapping("/")
	public String getWelcomeFile(Model modelo) {
		return "raizHtml";
	}
	
}
