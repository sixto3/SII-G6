package es.uma.g6.Auxiliares;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class products2 {
	
	
	private accountHolder accountHolder;
	
	
	private String	productNumber; 
	
	private String status;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;

	public accountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(accountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}


	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    

	
	
}
