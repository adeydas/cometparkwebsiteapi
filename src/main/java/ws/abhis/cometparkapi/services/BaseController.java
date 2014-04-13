package ws.abhis.cometparkapi.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ws.abhis.cometparkapi.logic.DatabaseConnect;

@Controller
@RequestMapping("/")
public class BaseController {
	private static final Logger logger = LogManager.getLogger(BaseController.class.getName());
	DatabaseConnect objDatabaseConnect = new DatabaseConnect();
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcomeM(ModelMap model) {
		List<List<String>> retDb = objDatabaseConnect.runSqlQuery("select lat,lon,lotname,numid from tbl_parkinglots");
		String ret = "";
		if (retDb.size() > 0) {
			for (int i=0; i<retDb.size(); i++) {
				ret += "['" + retDb.get(i).get(2) + "', " + retDb.get(i).get(0) + ", " + retDb.get(i).get(1) + ", " + retDb.get(i).get(3) + "],";
			}
			ret = ret.substring(0, ret.length()-1);
			model.addAttribute("latlonpairs", ret);
			return "index";
		}
		model.addAttribute("latlonpairs", null);
		return "index";
	}
}
