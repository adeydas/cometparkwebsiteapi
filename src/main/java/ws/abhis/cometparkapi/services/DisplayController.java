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
@RequestMapping("/display")
public class DisplayController {
	private static final Logger logger = LogManager.getLogger(DisplayController.class.getName());
	
	@RequestMapping(value = "/sensor/all", method = RequestMethod.GET)
	public String displayAllSensor(ModelMap model) {
		DatabaseConnect objDatabaseConnect = new DatabaseConnect();
		List<List<String>> retSel = objDatabaseConnect.runSqlQuery("select * from tbl_SensorStatus");
		
		if (retSel.size() <= 0) {
			model.addAttribute("status", "No sensor data found");
		} else {
			String retM = "<table border=\"1\">";
			retM += "<tr><td><strong>Sensor</strong></td><td><strong>Status</strong></td></tr>";
			for (int i=0; i<retSel.size(); i++) {
				String status = retSel.get(i).get(1);
				String toPrint = "";
				if (status.equals("true")) {
					toPrint = "Occupied";
				} else {
					toPrint = "Vacant";
				}
				retM += "<tr><td>" + retSel.get(i).get(0) + "</td><td>" + toPrint + "</td></tr>";
			}
			retM += "</table>";
			model.addAttribute("status", retM);
		}
		
		return "display";
	}
}
