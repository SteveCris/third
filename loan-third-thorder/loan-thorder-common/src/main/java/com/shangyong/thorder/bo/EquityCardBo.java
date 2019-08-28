package com.shangyong.thorder.bo;

import java.io.Serializable;

public class EquityCardBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String runcode;
	
	private String pgname;
	
	private String price;

	private String cardno;
	
	private String pass;

	public String getRuncode() {
		return runcode;
	}

	public void setRuncode(String runcode) {
		this.runcode = runcode;
	}

	public String getPgname() {
		return pgname;
	}

	public void setPgname(String pgname) {
		this.pgname = pgname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EquityCardBo [runcode=");
		builder.append(runcode);
		builder.append(", pgname=");
		builder.append(pgname);
		builder.append(", price=");
		builder.append(price);
		builder.append(", cardno=");
		builder.append(cardno);
		builder.append(", pass=");
		builder.append(pass);
		builder.append("]");
		return builder.toString();
	}
	
	
}
