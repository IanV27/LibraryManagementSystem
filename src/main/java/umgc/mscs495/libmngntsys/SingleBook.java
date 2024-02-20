package umgc.mscs495.libmngntsys.vo;

/**
 * 
 * @Description This vo class is for a singlebook object.
 * @author jimiewang
 * @CreateDate 01/20/2024
 */

public class SingleBook extends Book {
	private String barcode;
	private int reserveFlg;
	private String dueDate;
	private String purchaseDate;
	private double price;

	/**
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}
	/**
	 * @param barcode the barcode to set
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	/**
	 * @return the reserveFlg
	 */
	public int getReserveFlg() {
		return reserveFlg;
	}
	/**
	 * @param reserveFlg the reserveFlg to set
	 */
	public void setReserveFlg(int reserveFlg) {
		this.reserveFlg = reserveFlg;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the purchaseDate
	 */
	public String getPurchaseDate() {
		return purchaseDate;
	}
	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
