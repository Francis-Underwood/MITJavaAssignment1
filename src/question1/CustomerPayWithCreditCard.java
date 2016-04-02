package question1;

public class CustomerPayWithCreditCard extends Customer {
	public CustomerPayWithCreditCard(String cid, String cname) {
		super(cid, cname);
		this.paymentMethod = "Credit Card";
	}
	public void PayWithCreditCard() {
		System.out.println("Payment is settled with credit card.");
	}
}
