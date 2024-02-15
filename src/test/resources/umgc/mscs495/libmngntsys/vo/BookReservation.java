package umgc.mscs495.libmngntsys.vo;

/**
 * 
 * @Description This class is for book object.
 * @author jimiewang
 * @CreateDate 01/20/2024
 */
public class BookReservation {
	private String barcode;
	private String createDate; //yyyy-mm-dd
	private String dueDate; //yyyy-mm-dd
	private String returnDate;  //yyyy-mm-dd
	private double fine;

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
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	 * @return the returnDate
	 */
	public String getReturnDate() {
		return returnDate;
	}
	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	/**
	 * @return the fine
	 */
	public double getFine() {
		return fine;
	}
	/**
	 * @param fine the fine to set
	 */
	public void setFine(double fine) {
		this.fine = fine;
	}
}
