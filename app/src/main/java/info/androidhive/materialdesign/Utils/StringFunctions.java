package info.androidhive.materialdesign.Utils;

public class StringFunctions {
	
	public static String StringUpperCase(String temp)
	{
		StringBuffer sb = new StringBuffer();
         sb.append(Character.toUpperCase(temp.charAt(0))).append(temp.substring(1)).append(" ");
                  
      return sb.toString().trim();		
		
	}

}
