package ws.abhis.cometparkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ws.abhis.cometparkapi.logic.DatabaseConnect;

@Controller
@RequestMapping("/")
public class BaseController {
	private static final Logger logger = LogManager.getLogger(BaseController.class.getName());
	DatabaseConnect objDatabaseConnect = new DatabaseConnect();
	
	@RequestMapping(value = "/searchspaces/{lotId}/all", method = RequestMethod.GET)
	public String searchSpaces(ModelMap model, @PathVariable String lotId) {
		//Get centroid lat lon
		List<List<String>> centroidLatLon = new ArrayList<>();
		centroidLatLon = objDatabaseConnect.runSqlQuery("select lat, lon from tbl_parkinglots where lotid='" + lotId + "'");
		model.addAttribute("centroidlat", centroidLatLon.get(0).get(0));
		model.addAttribute("centroidlon", centroidLatLon.get(0).get(1));
		
		List<List<String>> spacesList = new ArrayList<>();
		spacesList = objDatabaseConnect.runSqlQuery("select id, lat, lon, leveltype, imageloc from tbl_parkingspaces where lotid='" + lotId + "'");
		
		String ret = "";
		
		if (spacesList.size() > 0) {
			for (int i=0; i<spacesList.size(); i++) {
				String lT = spacesList.get(i).get(3);
				String vDesc = "";
				if (lT.equals("ge")) {
					vDesc = "Green parking permit.";
				} else if (lT.equals("pu")) {
					vDesc = "Purple parking permit.";
				} else if (lT.equals("go")) {
					vDesc = "Gold parking permit.";
				} else if (lT.equals("or")) {
					vDesc = "Orange parking permit.";
				}
				
				
				ret += "['" + spacesList.get(i).get(0) + "', " + spacesList.get(i).get(1) + ", " + spacesList.get(i).get(2) + ", '" + lT + "', '" + vDesc + "', '" + spacesList.get(i).get(4) + "'],";
			}
			ret = ret.substring(0, ret.length()-1);
			model.addAttribute("latlonpairs", ret);
		}
		
		return "searchspaces";
	}
	
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcomeM(ModelMap model) {
		List<List<String>> retDb = objDatabaseConnect.runSqlQuery("select lat,lon,lotname,numid,lotid from tbl_parkinglots");
		String ret = "";
		if (retDb.size() > 0) {
			for (int i=0; i<retDb.size(); i++) {
				ret += "['" + retDb.get(i).get(2) + "', " + retDb.get(i).get(0) + ", " + retDb.get(i).get(1) + ", " + retDb.get(i).get(3) + ", '" + retDb.get(i).get(4) +  "'],";
			}
			ret = ret.substring(0, ret.length()-1);
			model.addAttribute("latlonpairs", ret);
			return "index";
		}
		model.addAttribute("latlonpairs", null);
		return "index";
	}
}
