package question1;

import java.io.Serializable;

public class Customer implements Serializable {
	
	private static final long serialVersionUID = 2367786313981532429L;
	private String cid;
	private String cname;
	private String paymentMethod;
	
	public Customer(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;		
	}
	
	public String getCid() {
		return cid;
	}
	
	public String getCname() {
		return cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
}