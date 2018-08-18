package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactionPackage {
	private Map<String, Integer[]> goods;
	private int num;
	private int weight;
	private int valSum;
	private String[] goodsSort;
	private List<String> goodsList;
	public FactionPackage(Map<String, Integer[]> goods, int weight) {
		this.goods = goods;
		this.weight = weight;
		this.num = goods.size();
		valSum = 0;
		goodsSort = new String[goods.size()];
		goodsList = new ArrayList<>();
		sortGoods(goods);
	}
	public Map<String, Integer[]> getGoods(){
		return goods;
	}
	public int getWeight() {
		return weight;
	}
	public String[] getGoodsSort() {
		return goodsSort;
	}
	public List<String> getGoodsList() {
		return goodsList;
	}
	public void sortGoods(Map<String, Integer[]> goods) {
		int n = 0;
		for(String goods1:goods.keySet()) {
			if(n < goods.size()) {
				goodsSort[n++] = goods1;
			}
		}
		for(int i=0; i< goodsSort.length; i++) {
			for(int j=i; j<goodsSort.length; j++) {
				if(goods.get(goodsSort[j])[0]*1.0/goods.get(goodsSort[j])[1] > goods.get(goodsSort[i])[0]*1.0/goods.get(goodsSort[i])[1]) {
					String temp = goodsSort[i];
					goodsSort[i] = goodsSort[j];
					goodsSort[j] = temp;
				}
			}
		}
	}
	public void packGoods( Map<String, Integer[]> goods, int weight) {
		int cap = 0;
		for(String string : goods.keySet()) {
			Integer[] good = goods.get(string);
			goodsList.add(string);
			if(good[1] + cap >= weight) {
				
				return;
			}
			cap += good[1];
		}
	}
	public static void main(String[] args) {
		Map<String, Integer[]> goods = new HashMap<>();
		Integer[] goods1 = {60,10};
		
		Integer[] goods2 = {100,20};
		Integer[] goods3 = {120,30};
		goods.put("3", goods3);
		goods.put("2", goods2);
		goods.put("1", goods1);
		FactionPackage factionPackage = new FactionPackage(goods, 20);

		factionPackage.packGoods(factionPackage.getGoods(), factionPackage.getWeight());
		for(int i = 0; i<factionPackage.getGoodsList().size(); i++) {
			System.out.println(factionPackage.getGoodsList().get(i));
		}
	}

}
