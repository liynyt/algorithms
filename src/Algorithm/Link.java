package Algorithm;

import java.util.Random;

public class Link {
	private int srcNode;
	private int destNode;
	private double weight;
	Random random = new Random();
	private int SlotNumber;
	private int slotUnused;
	boolean[] slot_mask;//0:vacnt 1:occupied
	private int dis;
	private int covered;
	private boolean occupied;

    public Link(int srcNode, int destNode, double initialWeight) {
		super();
		this.srcNode = srcNode;
		this.destNode = destNode;
		this.weight = initialWeight;
		SlotNumber= random.nextInt(80) % (80-20+1) + 20;
		slotUnused = SlotNumber;
		slot_mask = new boolean[SlotNumber];
		covered= 0 ;
	}
    public Link(int srcNode, int destNode, int slotnum, double initialWeight) {
		super();
		this.srcNode = srcNode;
		this.destNode = destNode;
		this.weight = initialWeight;
		SlotNumber= slotnum;
		slotUnused = SlotNumber;
		slot_mask = new boolean[SlotNumber];
		covered= 0 ;
	}
    public Link(int srcNode, int destNode, double initialWeight,int slotNum, int dis) {
		super();
		this.srcNode = srcNode;
		this.destNode = destNode;
		this.weight = initialWeight;
		SlotNumber= slotNum;
		slotUnused = SlotNumber;
		slot_mask = new boolean[SlotNumber];
		this.dis = dis;
		covered= 0 ;
	}
    public Link(Link link ) {
    	this.srcNode = link.get_src();
		this.destNode = link.get_dest();
		this.weight = link.get_weight();
		this.SlotNumber = link.get_slotNumber();
		slotUnused = link.get_slotUnused();
		slot_mask = link.get_slot_mask();
		covered=link.get_covered();
    }


    public boolean get_occupied() {
    	return occupied;
    }
    public boolean compared(Link link) {
    	if(this.get_src() == link.get_src() && this.get_dest() == link.get_dest()){
    		return true;
    	}
    	else {
			return false;
		}
    }
    public int get_covered() {
    	return covered;
    }
    public int get_dis() {
    	return this.dis;
    }
    public int get_slotNumber() {
    	return this.SlotNumber;
    }
    public int get_slotUnused() {
    	return this.slotUnused;
    }
    public int get_src() {
    	return srcNode;
    }
    public int get_dest() {
    	return destNode;
    }
    public double get_weight() {
    	return weight;
    }
    

    public void reset_occupied() {
    	occupied = false;
    }
    public void set_occupied() {
    	occupied = true;
    }
    public void reset_covered() {
    	covered = 0;
    }
    public void set_covered() {
    	covered ++;
    }
    public void set_weight(double value) {
    	weight = value;
    }
    
	public boolean ifSlotVacant(int begin,int end){
        for(int i = begin;i != end + 1;i++){
            if(slot_mask[i])
                return false;
        }
        return true;
    }

	public void set_slot_mask(int startSlot) {
		this.slot_mask[startSlot] = true;
	}
	public void set_slotUnused(int value) {
		this.slotUnused = value;
	}
	public void cal_slotUnused() {
		int slotUnusedNum = 0;
		for(int s=0; s<this.SlotNumber; ) {
			if(!this.test_slot_mask(s)) {
				if(this.get_slotsUnusedNum(s) > slotUnusedNum) {
					slotUnusedNum = this.get_slotsUnusedNum(s);
				}
				s = s + this.get_slotsUnusedNum(s);
			}	
			else
				s++;
		}
		slotUnused = slotUnusedNum;
	}

	public boolean test_slot_mask(int startSlot) {
		return this.slot_mask[startSlot];
	}

	public void reset_slot_mask(int startSlot) {
		this.slot_mask[startSlot] = false;
	}
	public void reset_slot_mask(int startSlot, int slotNum) {
		for(int s=startSlot; s<startSlot+slotNum; s++) {
			slot_mask[s] = false;
		}
	}
	

	public void reset_slot_mask() {
		for(int i=0; i<this.slot_mask.length; i++) {
			this.slot_mask[i] = false;
		}		
	}
	public void set_slot_mask() {
		for(int i=0; i<this.slot_mask.length; i++) {
			this.slot_mask[i] = true;
		}		
	}
	public boolean[] get_slot_mask() {
		return this.slot_mask;
	}
	//if continuous slots is vacant
	public boolean ifslotsVacant(int slotNum) {
		if(slotUnused<slotNum) {
			return false;
		}
		for(int n=0; n<(SlotNumber-slotNum); n++) {
			if(!test_slot_mask(n)) {
				if(ifSlotVacant(n, n+slotNum)){
					return true;
				}
			}
		}
		return false;
	}

	public int get_slotsUnusedNum(int begin) {
		int count = 0;
		for(int i = begin; i< SlotNumber; i++) {
			if(!test_slot_mask(i)) {
				count++;
			}
			else
				break;
		}
		return count;
	}
	@Override
	public String toString() {
		return srcNode + "->" + destNode + " " + String.format("%5.2f", weight) +" " +slotUnused + "  " + dis;
	}
}
