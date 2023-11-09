package experiments;

import java.util.Date;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
		Date date = new Date();
		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
		
		String dateText = date.toString();
		String dateTextWithoutSpaces = dateText.replace(" ", "_");
		String dateTextWithoutSpacesAndColons = dateTextWithoutSpaces.replace(":", "_");
		System.out.println(dateTextWithoutSpacesAndColons);
			
		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
*/		
		//System.getProperties().list(System.out);
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("java.version"));
		
	}

}
