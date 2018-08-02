package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetInfos {

	public Map<String, String> getFullCharacterInfo(String strCharName) {
		try {
			URL url = new URL(
					"https://secure.tibia.com/community/?subtopic=characters&name=" + strCharName.replace(" ", "+"));
			int responseCode = 0;
			String content = "";
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			while (responseCode != 200) {
				responseCode = conn.getResponseCode();

				if (responseCode == 200) {
					// open the stream and put it into BufferedReader
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "ISO-8859-1"));

					String inputLine;

					while ((inputLine = br.readLine()) != null) {
						content += inputLine;
					}
					br.close();
					conn.disconnect();
					conn = null;
				}
			}


			if (content.contains("does not exist.")) {
				// TODO
				// Player does not exist
			} else {
				String strName = "", strLevel = "", strVocation = "", strWorld = "", strSex = "",
						strAchievementPoints = "", strGuildMembership = "", strLastLogin = "", strAccountStatus = "",
						strFormerNames = "", strFormerWorld = "", strMarriedTo = "", strResidence = "", strHouse = "";

				// NAME
				String patternName = "<td[\\s]*width=20%>Name:</td><td>[\\s\\w'-]*";
				Pattern rName = Pattern.compile(patternName);
				Matcher mName = rName.matcher(content);

				while (mName.find()) {
					strName = mName.group(0).split("<td>")[1].trim();
				}

				// LEVEL
				String patternLevel = "<td>Level:</td><td>[\\d]*";
				Pattern rLevel = Pattern.compile(patternLevel);
				Matcher mLevel = rLevel.matcher(content);

				while (mLevel.find()) {
					strLevel = mLevel.group(0).split("><td>")[1].trim();
				}

				// VOCATION
				String patternVocation = "<td>Vocation:</td><td>[\\s\\w]*";
				Pattern rVocation = Pattern.compile(patternVocation);
				Matcher mVocation = rVocation.matcher(content);

				while (mVocation.find()) {
					strVocation = mVocation.group(0).split("><td>")[1].trim();
				}

				// WORLD
				String patternWorld = "<td>World:</td><td>[\\s\\w]*";
				Pattern rWorld = Pattern.compile(patternWorld);
				Matcher mWorld = rWorld.matcher(content);

				while (mWorld.find()) {
					strWorld = mWorld.group(0).split("><td>")[1].trim();
				}

				// SEX
				String patternSex = "<td>Sex:</td><td>[\\s\\w]*";
				Pattern rSex = Pattern.compile(patternSex);
				Matcher mSex = rSex.matcher(content);

				while (mSex.find()) {
					strSex = mSex.group(0).split("><td>")[1].trim();
				}

				// RESIDENCE
				String patternResidence = "<td>Residence:</td><td>[\\s\\w]*";
				Pattern rResidence = Pattern.compile(patternResidence);
				Matcher mResidence = rResidence.matcher(content);

				while (mResidence.find()) {
					strResidence = mResidence.group(0).split("><td>")[1].trim();
				}

				// ACHIEVEMENT POINTS
				String patternAchievementPoints = "<nobr>Achievement Points:</nobr></td><td>[\\s\\d]*";
				Pattern rAchievementPoints = Pattern.compile(patternAchievementPoints);
				Matcher mAchievementPoints = rAchievementPoints.matcher(content);

				while (mAchievementPoints.find()) {
					strAchievementPoints = mAchievementPoints.group(0).split("><td>")[1].trim();
				}

				// LAST LOGIN
				String patternLastLogin = "<td>Last Login:</td><td>[\\s\\w&#;,:]*";
				Pattern rLastLogin = Pattern.compile(patternLastLogin);
				Matcher mLastLogin = rLastLogin.matcher(content);

				while (mLastLogin.find()) {
					String p2 = mLastLogin.group(0).split(">")[3].replace(">", "").replace("&#160;", " ").trim();
					strLastLogin = p2;
				}

				// ACCOUNT STATUS
				String patternAccountStatus = "<td>Account&#160;Status:</td><td>[\\s\\w]*";
				Pattern rAccountStatus = Pattern.compile(patternAccountStatus);
				Matcher mAccountStatus = rAccountStatus.matcher(content);

				while (mAccountStatus.find()) {
					strAccountStatus = mAccountStatus.group(0).split("><td>")[1].trim();
				}

				// HOUSE
				String patternHouse = "<td>House:</td><td>[\\s]*[<][\\w\\s]*[=\"]*[\\w:/.?=&;+\"\\s]*[>][\\w\\s]*[</a>\\s]*[(\\w\\s]*[)]*";
				Pattern rHouse = Pattern.compile(patternHouse);
				Matcher mHouse = rHouse.matcher(content);

				while (mHouse.find()) {
					strHouse = mHouse.group(0).split("\" >")[1].replace("</a>", "");
				}

				// GUILD MEMBERSHIP
				String patternGuildMembership = "<td>Guild&#160;Membership:</td><td>[\\s\\w]*[<][\\w\\s]*[=\"]*[\\w:/.?=&;+\"\\s]*[>][\\w\\s]*[&#\\d;]*[\\w]*";
				Pattern rGuildMembership = Pattern.compile(patternGuildMembership);
				Matcher mGuildMembership = rGuildMembership.matcher(content);

				while (mGuildMembership.find()) {
					strGuildMembership = mGuildMembership.group(0).split("Guild&#160;Membership:</td><td>")[1]
							.replace("</a></td", "").replaceAll("<a[\\s]*href[\\s]*=[\\s]*\"[\\w:/.?=&+;\"\\s]*", "")
							.replace("&#160;", " ").replace(">", "");
				}

				// FORMER NAMES
				String patternFormerNames = "<td>Former Names:</td><td>[\\s\\w,]*";
				Pattern rFormerNames = Pattern.compile(patternFormerNames);
				Matcher mFormerNames = rFormerNames.matcher(content);

				while (mFormerNames.find()) {
					strFormerNames = mFormerNames.group(0).split("><td>")[1].trim().replace(",", " and ");
				}

				// FORMER WORLD
				String patternFormerWorld = "<td>Former World:</td><td>[\\s\\w]*";
				Pattern rFormerWorld = Pattern.compile(patternFormerWorld);
				Matcher mFormerWorld = rFormerWorld.matcher(content);

				while (mFormerWorld.find()) {
					strFormerWorld = mFormerWorld.group(0).split("><td>")[1].trim();
				}

				// MARRIED TO
				String patternMarriedTo = "<td>Married To:</td><td>[\\w\\s<=\":/.?&>+]*[&#160;]*[\\w]*";
				Pattern rMarriedTo = Pattern.compile(patternMarriedTo);
				Matcher mMarriedTo = rMarriedTo.matcher(content);

				while (mMarriedTo.find()) {
					strMarriedTo = mMarriedTo.group(0).split("&name=")[1].split("\">")[0].replace("+", " ").trim();
				}

				Map<String, String> mapInformations = new HashMap<String, String>();

				mapInformations.put("name", strName);
				mapInformations.put("level", strLevel);
				mapInformations.put("vocation", strVocation);
				mapInformations.put("world", strWorld);
				mapInformations.put("sex", strSex);
				mapInformations.put("residence", strResidence);
				mapInformations.put("achievement", strAchievementPoints);
				DinstinctFunctions df = new DinstinctFunctions();
				mapInformations.put("last_login", df.CharDTtoMySQLDT(strLastLogin));
				df = null;
				mapInformations.put("account_status", strAccountStatus);

				if (strHouse.equals("")) {
					mapInformations.put("house", null);
				} else {
					mapInformations.put("house", strHouse);
				}

				if (strGuildMembership.equals("")) {
					mapInformations.put("guild", null);
				} else {
					mapInformations.put("guild", strGuildMembership);
				}

				if (strFormerNames.equals("")) {
					mapInformations.put("former_names", null);
				} else {
					mapInformations.put("former_names", strFormerNames);
				}

				if (strFormerWorld.equals("")) {
					mapInformations.put("former_worlds", null);
				} else {
					mapInformations.put("former_worlds", strFormerWorld);
				}

				if (strMarriedTo.equals("")) {
					mapInformations.put("married_to", null);
				} else {
					mapInformations.put("married_to", strMarriedTo);
				}

				return mapInformations;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getWorldsName() {
		try {
			URL url = new URL("https://secure.tibia.com/community/?subtopic=worlds");
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
				}

				conn.disconnect();
				conn = null;
			}
			
			ArrayList<String> worlds = new ArrayList<String>();

			String pattern = "world=[\\w]*";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(content);

			while (m.find()) {
				worlds.add(m.group(0).replace("world=", ""));
			}

			return worlds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getPlayersByWorld(String strWorldName) {
		try {
			URL url = new URL("https://secure.tibia.com/community/?subtopic=worlds&world="+strWorldName);
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
				}

				conn.disconnect();
				conn = null;
			}
			
			ArrayList<String> players = new ArrayList<String>();

			String pattern = "&name=[\\w\\s+]*";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(content);

			while (m.find()) {
				players.add(m.group(0).replace("&name=", ""));
			}

			return players;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
