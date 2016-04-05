package question1;

public class CustomerPayWithCreditCard extends Customer {
	private static final long serialVersionUID = 6754485916422625113L;
	public CustomerPayWithCreditCard(String cid, String cname) {
		super(cid, cname);
		this.setPaymentMethod(CustomerFactory.PAYMENTMETHOD_CREDITCARD);
	}
	public void PayWithCreditCard() {
		System.out.println("Payment is settled with credit card.");
	}
}
