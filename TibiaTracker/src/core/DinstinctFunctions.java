package core;

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

}
