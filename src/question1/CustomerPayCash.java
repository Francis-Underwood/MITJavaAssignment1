package question1;

public class CustomerPayCash extends Customer {
	private static final long serialVersionUID = 7758754305202307218L;
	public CustomerPayCash(String cid, String cname) {
		super(cid, cname);
		this.setPaymentMethod(CustomerFactory.PAYMENTMETHOD_CASH);
	}
	public void PayCash() {
		System.out.println("Payment is settled with cash.");
	}
}
