package question1;

//factory pattern
public final class CustomerFactory {
	public static final String PAYMENTMETHOD_CASH = "cash";
	public static final String PAYMENTMETHOD_CREDITCARD = "credit card";
	public Customer createCustomer(String cid, String cname, String payMethd) {
		switch (payMethd) {
			case PAYMENTMETHOD_CASH:
				return new CustomerPayCash(cid, cname);
			case PAYMENTMETHOD_CREDITCARD:
				//break;
			default:
				return new CustomerPayWithCreditCard(cid, cname);
		}
	}
}
