package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DinstinctFunctions {

	public String CharDTtoMySQLDT(String strLastLogin) {

		try {
			if (!strLastLogin.equals("never logged in")) {
				String strMonth = "";

				switch (strLastLogin.substring(0, 3)) {
				case "Jan":
					strMonth = "01";
					break;
				case "Feb":
					strMonth = "02";
					break;
				case "Mar":
					strMonth = "03";
					break;
				case "Apr":
					strMonth = "04";
					break;
				case "May":
					strMonth = "05";
					break;
				case "Jun":
					strMonth = "06";
					break;
				case "Jul":
					strMonth = "07";
					break;
				case "Aug":
					strMonth = "08";
					break;
				case "Sep":
					strMonth = "09";
					break;
				case "Oct":
					strMonth = "10";
					break;
				case "Nov":
					strMonth = "11";
					break;
				case "Dec":
					strMonth = "12";
					break;
				default:
					strMonth = "00";
				}

				String strDay = strLastLogin.substring(4, 6);
				String strYear = strLastLogin.substring(7, 11);
				String strTime = strLastLogin.substring(13, 21);

				String strFinalDateTime = strDay + "-" + strMonth + "-" + strYear + " " + strTime;

				return strFinalDateTime;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(strLastLogin);
		}
		return null;
	}

	public static String getWebsiteContent(URL url) {
		try {
			int responseCode = 0;
			String content = "";
			while (responseCode != 200) {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

				responseCode = conn.getResponseCode();

				if (responseCode == 200) {
					// open the stream and put it into BufferedReader
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "ISO-8859-1"));

					String inputLine;

					while ((inputLine = br.readLine()) != null) {
						content += inputLine;
					}
					br.close();
					Thread.sleep(500);
				} else {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					System.out.println(dtf.format(now) + ": " + responseCode);
					dtf = null;
					now = null;
				}
				conn.disconnect();
				conn = null;

				return content;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
