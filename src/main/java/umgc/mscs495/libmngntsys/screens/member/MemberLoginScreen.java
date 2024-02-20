package umgc.mscs495.libmngntsys.screens.member;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @Description This class is for drawing member authentication screen.
 * @author jimiewang
 * @CreateDate 01/27/2024
 */
public class MemberLoginScreen extends JPanel {
	private JFrame parentFrame = null;
	public MemberLoginScreen(JFrame frame) {
		this.parentFrame = frame; 
        setLayout(new GridBagLayout());
//      setLayout(new GridLayout(2, 1));
      add( drawPanel());
		drawPanel();
	}
	
	 public JPanel drawPanel() {
	       JPanel loginPanel = new JPanel(new GridLayout(6, 1));
	       loginPanel.setBackground(Color.CYAN);
	       loginPanel.setPreferredSize(new Dimension(400, 200));
	       //loginPanel.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));
	       //loginPanel.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLUE));
	       loginPanel.setBorder(BorderFactory.createRaisedBevelBorder());

	       JPanel titlePanel = new JPanel(new FlowLayout());
	       JLabel titleLbl = new JLabel("Please Login");
	       titleLbl.setFont(new Font("Verdana", Font.PLAIN, 16));
	       //titlePanel.add(new JLabel());
	       titlePanel.add(titleLbl);
	       //titlePanel.add(new JLabel());

	       loginPanel.add(titlePanel);
	       JPanel usernamePanel = new JPanel(new FlowLayout());
	       usernamePanel.setPreferredSize(new Dimension(400, 30));
	       JLabel usernameLbl = new JLabel("User Name: ");
	       usernamePanel.add(usernameLbl);
	       JTextField usernameField = new JTextField(30); 
	       usernamePanel.add(usernameField);
	       loginPanel.add(usernamePanel);
	       
	       JPanel passwordPanel = new JPanel(new FlowLayout());
	       JLabel pwdLbl = new JLabel("Password: ");
	       passwordPanel.add(pwdLbl);
	       JPasswordField pwdField = new JPasswordField(30); 
	       passwordPanel.add(pwdField);
	       loginPanel.add(passwordPanel);
	       
	       JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
	       JButton loginBtn = new JButton("Confirm");
	       buttonPanel.add(new JLabel());
	       buttonPanel.add(loginBtn);
	       JButton resetBtn = new JButton("Reset");
	       buttonPanel.add(resetBtn);
	       buttonPanel.add(new JLabel());
			//add actionListener
	       loginBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
				//call the object of NewWindow and set visible true
					//NewWindow frame = new NewWindow();
					//frame.setVisible(true);
					//set default close operation
					//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					parentFrame.dispose();
					/*MemberHomeScreen memberScreen = new MemberHomeScreen();
					memberScreen.showMenuDemo();*/
				    //dispose();
				}
			});       
	       loginPanel.add(buttonPanel);
	       JPanel marginPanel = new JPanel(new FlowLayout());
	       loginPanel.add(marginPanel);
	       
	       
	       return loginPanel;
		 }
	 
/*    public void browseFiles2() {  
	    JFrame frame = new JFrame();
    	   JTextField tField;
    	   JPasswordField pwField;
    	   JFormattedTextField formattedField;
        JPanel tfPanel = new JPanel(new GridLayout(3, 2, 10, 2));
        tfPanel.setBorder(BorderFactory.createTitledBorder("Text Fields: "));
   
        // Regular text field (Row 1)
        tfPanel.add(new JLabel("  JTextField: "));
        tField = new JTextField(10);
        tfPanel.add(tField);
        tField.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              tArea.append("\nYou have typed " + tField.getText());
           }
        });
        JButton button = new JButton("Show Second Window");
        button.addActionListener(event -> {
//            if (mainFrame == null) {
//         	   mainFrame = new SecondWindow(mainFrame);
//            }
     	   userFrame.dispose();
     	   showMenuDemo();
        });      
        tfPanel.add(button);
        // Password field (Row 2)
        tfPanel.add(new JLabel("  JPasswordField: "));
        pwField = new JPasswordField(10);
        tfPanel.add(pwField);
        pwField.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              tArea.append("\nYou password is " + new String(pwField.getPassword()));
           }
        });
   
        // Formatted text field (Row 3)
        tfPanel.add(new JLabel("  JFormattedTextField"));
        formattedField = new JFormattedTextField(java.util.Calendar
              .getInstance().getTime());
        tfPanel.add(formattedField);
        frame.add(tfPanel);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setFocusable(true);
        frame.setFocusableWindowState(true);
        frame.setAlwaysOnTop(true);
        frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        frame.getRootPane().disable();
        frame.pack(); 
	    frame.setVisible(true);
    }*/

}
