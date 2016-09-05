package mine.util;

public class StringUtils {

	public static boolean isNotBlank(String str){
		
		boolean isNotBlank = false;
		if(!(str == null)){
			if(!"".equals(str.trim())){
				
		    	isNotBlank = true;
			}
		}
		return isNotBlank;
		
	}
	public static boolean isBlank(String str){

		boolean isBlank = false;
		if(str == null|| "".equals(str.trim())){
			isBlank = true;
		}
		return isBlank;
		
	}
//	public static void main(String[] args){
//		
//		 String tr = "";
//		 System.err.println(StringUtils.isNotBlank(tr));
//	}
}
