package umgc.mscs495.libmngntsys.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * 
 * @Description This class is for adding the input text length for JTextFiled component.
 * @author jimiewang
 * @CreateDate 01/28/2024
 * 
 */
public class JTextFieldCharLimit extends PlainDocument {
	private static final long serialVersionUID = 1L;
	private int textLimit;
	
	public JTextFieldCharLimit(int limit) {
		super();
		this.textLimit = limit;
	}
	
	public void insertString(int offst, String str, AttributeSet attr) throws BadLocationException {
		if(str == null) return;
		
		if(getLength() + str.length()  <= textLimit) {
			super.insertString(offst, str, attr);
		}
	}
}
