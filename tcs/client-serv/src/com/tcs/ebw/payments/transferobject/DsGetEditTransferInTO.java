/**
 * 
 */
package com.tcs.ebw.payments.transferobject;

import com.tcs.ebw.transferobject.EBWTransferObject;

/**
 * @author : 224703
 * **************   Revision History ************************
 * Modified-By |  Modified-Date |  Project-Phase  | Changes
 *
 * **********************************************************
 */
public class DsGetEditTransferInTO extends EBWTransferObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4408398851954592059L;
	private String paypayref = null;
	private String paybatchref = null;
	private String lifeUserId = null;

	/**
	 * @return the paypayref
	 */
	public String getPaypayref() {
		return paypayref;
	}
	/**
	 * @param paypayref the paypayref to set
	 */
	public void setPaypayref(String paypayref) {
		this.paypayref = paypayref;
	}
	/**
	 * @return the paybatchref
	 */
	public String getPaybatchref() {
		return paybatchref;
	}
	/**
	 * @param paybatchref the paybatchref to set
	 */
	public void setPaybatchref(String paybatchref) {
		this.paybatchref = paybatchref;
	}
	/**
	 * @return the lifeUserId
	 */
	public String getLifeUserId() {
		return lifeUserId;
	}
	/**
	 * @param lifeUserId the lifeUserId to set
	 */
	public void setLifeUserId(String lifeUserId) {
		this.lifeUserId = lifeUserId;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
		final String TAB = "\r\n";

		String retValue = "";

		retValue = "DsGetEditTransferInTO ( "
			+ super.toString() + TAB
			+ "paypayref = " + this.paypayref + TAB
			+ "paybatchref = " + this.paybatchref + TAB
			+ "lifeUserId = " + this.lifeUserId + TAB
			+ " )";

		return retValue;
	}

}
