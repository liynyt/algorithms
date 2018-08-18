package coding;

public class ReplaceSpace {
	/**
	 * ÌâÄ¿£ºÌæ»»¿Õ¸ñ
	 * £¿
	 * @param string
	 */
	public void replaceSpace(String string) {
		String[] strings = string.split(" ");
		String string2 = new String();
		for(int i=0; i<strings.length; i++) {
			string2 += strings[i];
			if(i<string.length()-1) {
				string2 += "%20";
			}
		}
	}

}
