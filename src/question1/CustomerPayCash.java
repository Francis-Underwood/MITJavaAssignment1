package question1;

public class CustomerPayCash extends Customer {
	public CustomerPayCash(String cid, String cname) {
		super(cid, cname);
		this.paymentMethod = "Cash";
	}
	public void PayCash() {
		System.out.println("Payment is settled with cash.");
	}
}
