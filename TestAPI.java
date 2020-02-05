import java.util.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.HashMap;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.lang.StringBuilder;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TestAPI
{
	public static void main(String[] args)
	{
		URL url;

		int rookieYear;

		try {
			url = new URL("https://frc-api.firstinspires.org/v2.0/2019/teams?teamNumber=" + "900" + "????");
		}
		catch (MalformedURLException ex)
		{
			ex.printStackTrace();
			return;
		}

		rankArray(900,2002);
	}

	static String getInputString()
	{
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		scanner.close();
		return input;
	}

	static String getJSONRequest(URL url)
	{
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			return "";
		}

		try{
			String auth = "ofugi" + ":" + "0865BEFF-4886-4A7C-BC75-7B21B4C3AEBC";
			byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
			String authHeaderValue = "Basic " + new String(encodedAuth);
			con.setRequestProperty("Authorization", authHeaderValue);

			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			return sb.toString();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return "";
	}

	private static int[] rankArray(int teamNum, int teamRookieYear) {
		int[] rankArray = new int[2020-teamRookieYear];
		try {
			for(int i = teamRookieYear; i < 2020; i++) {
				
				rankArray[i-teamRookieYear] = i-teamRookieYear+1;
				for(int j = 0; j < rankArray.length; j++) System.out.println(rankArray[j]);
				//rankArray[i-teamRookieYear] = getJSONRequest(new URL(String.format("https://frc-api.firstinspires.org/v2.0/%d/rankings/district/%d", i, teamNum)));
				//System.out.println(getJSONRequest(new URL(String.format("https://frc-api.firstinspires.org/v2.0/%d/rankings/district/??teamNumber=%d??", i, teamNum))));

			}
		} catch (Exception e) {}
		return null;
	}	

}
