package coding;

public class RectCover {
	public int rectCover(int n ) {
		if(n==1) {
			return 1;
		}
		return rectCover(n-1) + rectCover(n-2);
	}

}
