package ws.abhis.cometparkapi.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ws.abhis.cometparkapi.logic.DatabaseConnect;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LogManager
			.getLogger(AdminController.class.getName());
	DatabaseConnect objDatabaseConnect = new DatabaseConnect();
	
	@RequestMapping(value = "/lot/delete", method = RequestMethod.POST)
	public String parkingLotDelete(ModelMap model,
			@RequestParam("id") String id,
			@RequestParam("latitude") String latitude,
			@RequestParam("longitude") String longitude,
			@RequestParam("lotname") String lotname,
			@RequestParam("numid") String numid) {

		try {
			Object o = objDatabaseConnect
					.runSqlQuery("delete from tbl_parkinglots where lotid='" + id + "'");
			model.addAttribute("optional", "Space deleted successfully");
		} catch (Exception e) {
			model.addAttribute("optional", "Failed to delete space");
		}

		return "parkinglot";
	}
	
	@RequestMapping(value = "/lot/update", method = RequestMethod.POST)
	public String parkingLotUpdate(ModelMap model,
			@RequestParam("id") String id,
			@RequestParam("latitide") String latitude,
			@RequestParam("longitude") String longitude,
			@RequestParam("lotname") String lotname,
			@RequestParam("numid") String numid) {
		

		String r = "update tbl_parkinglots set lat='" + latitude + "', lon='"
				+ longitude + "', lotname='" + lotname + "', numid='" + numid
				+ "' where lotid='" + id + "'";
		logger.debug(r);

		try {
			objDatabaseConnect.runSqlQuery(r);
			model.addAttribute("Lot updated successfully");
		} catch (Exception e) {
			model.addAttribute("optional", "Failed to update space");
		}

		return "parkinglot";
	}
	
	
	@RequestMapping(value = "/lot/insert", method = RequestMethod.POST)
	public String parkingLotInsert(ModelMap model,
			@RequestParam("id") String id,
			@RequestParam("latitide") String latitude,
			@RequestParam("longitude") String longitude,
			@RequestParam("lotname") String lotname,
			@RequestParam("numid") String numid) {
		try {
			objDatabaseConnect
					.runSqlQuery("insert into tbl_parkinglots (lotid,lat,lon,lotname,numid) values ('"
							+ id
							+ "','"
							+ latitude
							+ "','"
							+ longitude
							+ "','"
							+ lotname + "','" + numid + "')");
			model.addAttribute("optional", "Lot added successfully");
		} catch (Exception e) {
			model.addAttribute("optional", "Could not add lot");
		}
		return "parkinglot";
	}

	@RequestMapping(value = "/lot", method = RequestMethod.GET)
	public String parkingLot(ModelMap model) {
		List<List<String>> retDb = objDatabaseConnect
				.runSqlQuery("select lotid, lat, lon, lotname, numid from tbl_parkinglots");
		String ret = "";
		if (retDb.size() > 0) {
			ret = "<table border=\"1\"><tr><td>ID</td><td>Latitude</td><td>Longitude</td><td>Lot Name</td><td>Num ID</td></tr>";
			for (int i = 0; i < retDb.size(); i++) {
				ret += "<form method=\"post\" name=\"tesla\" id=\"tesla\" action=\"http://abhis.ws:8080/cometparkapi/admin/lot/delete\">";
				ret += "<tr><td><input type=\"text\" name=\"id\" id=\"id\" value=\""
						+ retDb.get(i).get(0)
						+ "\" /></td><td><input type=\"text\" name=\"latitude\" id=\"latitude\" value=\""
						+ retDb.get(i).get(1)
						+ "\" /></td><td><input type=\"text\" name=\"longitude\" id=\"longitude\" value=\""
						+ retDb.get(i).get(2)
						+ "\" /></td><td><input type=\"text\" name=\"lotname\" id=\"lotname\" value=\""
						+ retDb.get(i).get(3)
						+ "\" /></td><td><input type=\"text\" name=\"numid\" id=\"numid\" value=\""
						+ retDb.get(i).get(4)
						+ "\" /></td><td><input type=\"submit\" value=\"Delete\" /></td></tr>";
				ret += "</form>";
			}
			ret += "</table>";
		} else {
			ret = "No data found";
		}

		model.addAttribute("optional", ret);

		return "parkinglot";
	}

	@RequestMapping(value = "/controller/delete", method = RequestMethod.POST)
	public String parkingspotDelete(ModelMap model,
			@RequestParam("id") String id) {
		String ret = getParkingSpacesList();
		model.addAttribute("rets", ret);

		try {
			Object o = objDatabaseConnect
					.runSqlQuery("delete from tbl_parkingspaces where id=" + id);
			model.addAttribute("optional", "Space deleted successfully");
		} catch (Exception e) {
			model.addAttribute("optional", "Failed to delete space");
		}

		return "parkingspot";
	}

	@RequestMapping(value = "/controller/update", method = RequestMethod.POST)
	public String parkingspotUpdate(ModelMap model,
			@RequestParam("id") String id,
			@RequestParam("latitude") String latitude,
			@RequestParam("longitude") String longitude,
			@RequestParam("plevel") String plevel,
			@RequestParam("lotid") String lotid) {
		String ret = getParkingSpacesList();
		model.addAttribute("rets", ret);

		String r = "update tbl_parkingspaces set lat='" + latitude + "', lon='"
				+ longitude + "', leveltype='" + plevel + "', lotid='" + lotid
				+ "' where id=" + id;
		logger.debug(r);

		try {
			objDatabaseConnect.runSqlQuery("update tbl_parkingspaces set lat='"
					+ latitude + "', lon='" + longitude + "', leveltype='"
					+ plevel + "', lotid='" + lotid + "' where id=" + id);
			model.addAttribute("optional", "Space updated successfully");
		} catch (Exception e) {
			model.addAttribute("optional", "Failed to update space");
		}

		return "parkingspot";
	}

	@RequestMapping(value = "/controller/insert", method = RequestMethod.POST)
	public String parkingspotInsert(ModelMap model,
			@RequestParam("id") String id,
			@RequestParam("latitide") String latitude,
			@RequestParam("longitude") String longitude,
			@RequestParam("lotid") String lotid,
			@RequestParam("leveltype") String leveltype) {
		String ret = getParkingSpacesList();
		model.addAttribute("rets", ret);

		try {
			objDatabaseConnect
					.runSqlQuery("insert into tbl_parkingspaces (id, lat, lon, leveltype, lotid, imageloc) values ("
							+ id
							+ ", '"
							+ latitude
							+ "', '"
							+ longitude
							+ "', '"
							+ leveltype
							+ "', '"
							+ lotid
							+ "', '"
							+ leveltype + "-n.png')");
			model.addAttribute("optional", "Space added successfully");
		} catch (Exception e) {
			model.addAttribute("optional", "Failed to add space");
		}

		return "parkingspot";
	}

	@RequestMapping(value = "/controller/{id}/view", method = RequestMethod.GET)
	public String parkingspotViewDetails(ModelMap model, @PathVariable String id) {
		String ret = getParkingSpacesList();
		model.addAttribute("rets", ret);
		List<List<String>> retDb = objDatabaseConnect
				.runSqlQuery("select * from tbl_parkingspaces where lotid='"
						+ id + "'");

		if (retDb.size() > 0) {
			ret = "<table border=\"1\"><tr><td>ID</td><td>Latitude</td><td>Longitude</td><td>Parking level</td><td>Lot ID</td></tr>";
			for (int i = 0; i < retDb.size(); i++) {
				ret += "<form method=\"post\" name=\"tesla\" id=\"tesla\">";
				ret += "<tr><td><input type=\"text\" name=\"id\" id=\"id\" value=\""
						+ retDb.get(i).get(0)
						+ "\" /></td><td><input type=\"text\" name=\"latitude\" id=\"latitude\" value=\""
						+ retDb.get(i).get(1)
						+ "\" /></td><td><input type=\"text\" name=\"longitude\" id=\"longitude\" value=\""
						+ retDb.get(i).get(2)
						+ "\" /></td><td><input type=\"text\" name=\"plevel\" id=\"plevel\" value=\""
						+ retDb.get(i).get(3)
						+ "\" /></td><td><input type=\"text\" name=\"lotid\" id=\"lotid\" value=\""
						+ retDb.get(i).get(4)
						+ "\" /></td><td><input type=\"submit\" value=\"Edit\" onclick=\"return subform('e');\" /></td><td><input type=\"submit\" value=\"Delete\" onclick=\"return subform('d');\" /></td></tr>";
				ret += "</form>";
			}
			ret += "</table>";
		} else {
			ret = "No data found";
		}

		model.addAttribute("optional", ret);

		return "parkingspot";
	}

	private String getParkingSpacesList() {
		List<List<String>> parkingspotRet = objDatabaseConnect
				.runSqlQuery("select lotid from tbl_parkinglots");
		String ret = "";
		if (parkingspotRet.size() > 0) {
			for (int i = 0; i < parkingspotRet.size(); i++) {
				ret += "<option value=\"" + parkingspotRet.get(i).get(0)
						+ "\">" + parkingspotRet.get(i).get(0) + "</option>";
			}
		} else {
			ret += "<option>--None found--</option>";
		}
		return ret;
	}

	@RequestMapping(value = "/controller", method = RequestMethod.GET)
	public String parkingspotHome(ModelMap model) {
		String ret = getParkingSpacesList();
		model.addAttribute("rets", ret);
		return "parkingspot";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String loginWelcome(ModelMap model) {
		return "admin-home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginInputs(ModelMap model) {
		return "admin-login";
	}

	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	public String adminLoginCheck(ModelMap model,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		List<List<String>> adminRet = objDatabaseConnect
				.runSqlQuery("select password, role from tbl_adminusers where username='"
						+ username + "'");
		if (adminRet.size() > 0) {
			if (password.equals(adminRet.get(0).get(0))) {

				return "management-home";
			} else {
				return "admin-login";
			}
		} else {
			return "admin-login";
		}
	}
}
