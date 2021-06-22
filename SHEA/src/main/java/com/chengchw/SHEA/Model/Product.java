package com.chengchw.SHEA.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "Product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String prodName;

	
	@Max(100000)
	private int marketPrice;

	@Max(100000)
	private int iniPrice;
	
	@Min(1)
	private int bidPrice;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date expireDate;
	
	@NotBlank(message = "Brand is mandatory")
	private String brand;
	
	@NotBlank
	private String damageSeverity;
	
	@NotBlank
    private String madeDate;
	
	@NotBlank
	private String category;
	
	@NotBlank
	private String state;
	
	private String prodDetail;
	
	private Long currentBidId;
	
	private ArrayList<Integer> historyPrice;
	
	
	

    @ElementCollection
    @CollectionTable(name = "guest_price_mapping", 
      joinColumns = {@JoinColumn(name = "guest_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "guest_name")
    @Column(name = "mapping")
	private Map<Guest, Integer> guestPriceMapping;
	
	
	private int currentPrice;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "prod_bidder",
			   joinColumns = @JoinColumn(name = "prod_id"),
			   inverseJoinColumns = @JoinColumn(name = "bidder_id"))
	private List<Guest> bidders;
	
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_id")
	private Provider owner;

	
	public Product() {}
	
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getProdName() {
		return prodName;
	}


	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public int getIniPrice() {
		return iniPrice;
	}


	public void setIniPrice(int iniPrice) {
		this.iniPrice = iniPrice;
	}


	public int getBidPrice() {
		return bidPrice;
	}


	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}


	public Date getExpireDate() {
		return this.expireDate;
	}


	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getDamageSeverity() {
		return damageSeverity;
	}


	public void setDamageSeverity(String damageSeverity) {
		this.damageSeverity = damageSeverity;
	}


	public String getMadeDate() {
		return madeDate;
	}


	public void setMadeDate(String madeDate) {
		this.madeDate = madeDate;
	}


	public String getProdDetail() {
		return prodDetail;
	}


	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}


	public int getCurrentPrice() {
		return currentPrice;
	}


	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}


	public List<Guest> getBidders() {
		return bidders;
	}
	
	public Guest getCurrentBidder() {
		
		
		List<Guest> allBidder = this.getBidders();
		
		if (allBidder.size() == 0) {
			
			return null;
		}
		else {
			
			return allBidder.get(allBidder.size()-1);
		}
		
	}


	public void setBidders(List<Guest> bidders) {
		this.bidders = bidders;
	}
	
	public void addBidders(Guest bidder) {
		
		this.bidders.add(bidder);
		
	}


	public Provider getOwner() {
		return owner;
	}


	public void setOwner(Provider owner) {
		this.owner = owner;
	}




	public String getCategory() {
		return category;
	}




	public void setCategory(String category) {
		this.category = category;
	}




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public int getMarketPrice() {
		return marketPrice;
	}




	public void setMarketPrice(int marketPrice) {
		this.marketPrice = marketPrice;
	}




	public List<Integer> getHistoryPrice() {
		return historyPrice;
	}




	public void setHistoryPrice( ArrayList<Integer> historyPrice) {
		this.historyPrice = historyPrice;
	}
	
	public void addToHistoryPrice(Integer price) {
		
		if(this.historyPrice!=null) {
			
			this.historyPrice.add(price);
		}
		
		else {
			
			ArrayList<Integer> pricelist = new ArrayList<Integer>();
			pricelist.add(price);
			this.historyPrice = pricelist;
			
		}
	}




	public Map<Guest, Integer> getGuestPriceMapping() {
		
		/*
		 * if(this.guestPriceMapping == null) { Guest blankguest = new Guest();
		 * List<Integer> blanklist=new ArrayList<Integer>(); HashMap<Guest,
		 * List<Integer>> blankmap = new HashMap<Guest, List<Integer>>();
		 * blankmap.put(blankguest, blanklist); return blankmap; }
		 */
		
		return guestPriceMapping;
	}




	public void setGuestPriceMapping(Map<Guest, Integer> guestPriceMapping) {
		this.guestPriceMapping = guestPriceMapping;
	}
	
	public void updateGuestPrice(Guest guest, Integer price) {
		
		if(this.guestPriceMapping == null) {
			
			
//			List<Integer> inilist = new ArrayList<Integer>();
//			inilist.add(price);
			Map<Guest, Integer> newmap = new HashMap<Guest, Integer>();
			//System.out.println("here");
			newmap.put(guest, price);
			//System.out.println("here2");
			this.guestPriceMapping = newmap;
			//System.out.println("here3");
			
			
			
			
		}
		
		else if(this.guestPriceMapping.containsKey(guest)) {
			
			
			//pricelist.add(price);
			
			this.guestPriceMapping.put(guest, price);		
		}
		
		else {
			
			//List <Integer> pricelist = new ArrayList<Integer>();
			//pricelist.add(price);
			this.guestPriceMapping.put(guest, price);	
		}	
	}




	public Long getCurrentBidId() {
		
		if(this.currentBidId == null) {
			
			return (long) -1;
		}
		
		return currentBidId;
	}




	public void setCurrentBidId(Long currentBidId) {
		this.currentBidId = currentBidId;
	}
	
	
	
	
	
	
	
	
	

	
	

}
