package Algorithm;

public class Package {
	private int totalNum;
	private int totalWeight;
	private int[] values;
	private int[] weights;
	private int[][] finalValues;
	private int[] easyFinalValues;
	private int finalValue;
	public Package(int[] values, int[] weights, int totalWeight) {
		this.totalNum = values.length;
		this.totalWeight = totalWeight;
		this.values = values;
		this.weights = weights;
		easyFinalValues = new int[totalWeight +1];
		finalValues = new int[totalNum + 1][totalWeight + 1];
		finalValue = 0;
	}
	public int getValue() {
		return finalValue;
	}
	public void easyPack() {
		for(int i = 0; i<= totalNum; i++) {
//			for(int weight = 0; weight<= totalWeight; weight++) {
			for(int weight = totalWeight; weight>= 0; weight--) {//get weight in decrease order to make sure: 
//				f[v]=max{f[v],f[v-c[i]]}<==>f[i][v]=max{f[i-1][v],f[i- 1][v-c[i]]}  
//				i=1,easyFinalValues[weight] = [0,w[0],w[0],w[0],w[0]] and i=2 get easyFinalValues[weight] equals FinalValues[i-1][weight]
				if(i ==0 || weight == 0) {
					easyFinalValues[weight] = 0;
				}
				else {
					if(weights[i-1] <= weight) {
						easyFinalValues[weight] = Math.max(easyFinalValues[weight], easyFinalValues[weight- weights[i-1]] + values[i-1]);
					}
					else {
						easyFinalValues[weight] = easyFinalValues[weight];
					}
				}
			}
		}
		finalValue = easyFinalValues[totalWeight];
	}
	public void pack() {
		for(int i = 0; i<= totalNum; i++) {
			for(int weight = 0; weight<= totalWeight; weight++) {
				if(i ==0 || weight == 0) {
					finalValues[i][weight] =0;
				}
				else {
					if(weights[i-1] > weight) {// goods i is not in bag，because the weight of bag is lower than weight of i
												//(第i个物品一定不在bag中，因为现在的重量小于i的重量)
						finalValues[i][weight] = finalValues[i-1][weight];
					}
					else {// the best value is in [i] or [i-1]
						finalValues[i][weight] = (int)Math.max(finalValues[i-1][weight], values[i-1] + finalValues[i-1][weight-weights[i-1]]);
					}
				}
			}
		}
		finalValue = finalValues[totalNum][totalWeight];
	}
	public static void main(String[] arg0) {
		int[] values = {6,10,12};
		int[] weights = {1,2,3};
		Package package1 = new Package(values, weights, 5);
//		package1.pack();
		package1.easyPack();
		System.out.println(package1.getValue());
	}

}
