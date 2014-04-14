package ws.abhis.cometparkapi.services;

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
@RequestMapping("/receive")
public class ReceiveController {
	private static final Logger logger = LogManager
			.getLogger(ReceiveController.class.getName());

	@RequestMapping(value = "/add/{id}/{status}", method = RequestMethod.GET)
	public String receiveFromController(ModelMap model, @PathVariable String id, @PathVariable String status) {
		DatabaseConnect objDatabaseConnect = new DatabaseConnect();
		List<List<String>> retSel = objDatabaseConnect
				.runSqlQuery("select * from tbl_SensorStatus where sensorid='"
						+ id + "'");
		if (retSel == null) {
			model.addAttribute("status", "Internal error");
		} else {
			if (retSel.size() <= 0) {
				retSel = objDatabaseConnect
						.runSqlQuery("insert into tbl_SensorStatus (sensorid, status) values ('"+id+"','"+status+"')");
				if (retSel == null) {
					model.addAttribute("status", "Internal error");
				} else {
					model.addAttribute("status", "Updated");
				}
			} else {
				retSel = objDatabaseConnect
						.runSqlQuery("update tbl_SensorStatus set status='"+status+"' where sensorid='"+id+"'");
				
				retSel = objDatabaseConnect.runSqlQuery("select imageloc from tbl_parkingspaces where id=" + id);
				String img = retSel.get(0).get(0);
				String[] imgs = img.split("-");
				
				String r = "";
				if (status.equals("true")) {
					r = imgs[0] + "-y.png";
				} else {
					r = imgs[0] + "-n.png";
				}
				
				retSel = objDatabaseConnect.runSqlQuery("update tbl_parkingspaces set imageloc='" + r + "' where id=" + id);
				if (retSel == null) {
					model.addAttribute("status", "Internal error");
				} else {
					model.addAttribute("status", "Updated");
				}
			}
		}
		return "message";
	}
}
