package com.qa.ims.persistence.domain;

public class Item {
	private Long Itemid;
	private String Itemname;
	private String Itemmake;
	private double Itemprice;

	public Item(Long itemid, String itemname, String itemmake, double itemprice) {
		Itemid = itemid;
		Itemname = itemname;
		Itemmake = itemmake;
		Itemprice = itemprice;
	}
	public Item(String itemname, String itemmake, double itemprice) {
		Itemname = itemname;
		Itemmake = itemmake;
		Itemprice = itemprice;
	}
	
	public Long getItemid() {
		return Itemid;
	}
	public void setItemid(Long itemid) {
		Itemid = itemid;
	}
	public String getItemname() {
		return Itemname;
	}
	public void setItemname(String itemname) {
		Itemname = itemname;
	}
	public String getItemmake() {
		return Itemmake;
	}
	public void setItemmake(String itemmake) {
		Itemmake = itemmake;
	}
	public double getItemprice() {
		return Itemprice;
	}
	
	public void setItemprice(double itemprice) {
		Itemprice = itemprice;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (Itemid == null) {
			if (other.Itemid != null)
				return false;
		} else if (!Itemid.equals(other.Itemid))
			return false;
		if (Itemmake == null) {
			if (other.Itemmake != null)
				return false;
		} else if (!Itemmake.equals(other.Itemmake))
			return false;
		if (Itemname == null) {
			if (other.Itemname != null)
				return false;
		} else if (!Itemname.equals(other.Itemname))
			return false;
		if (Double.doubleToLongBits(Itemprice) != Double.doubleToLongBits(other.Itemprice))
			return false;
		return true;
	}
	public String toString() {
		return "id:" + Itemid + " Item name:" + Itemname + " make: " + Itemmake + "Price: " + Itemprice;
	}
	
	
}
