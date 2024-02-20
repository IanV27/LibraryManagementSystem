package umgc.mscs495.libmngntsys.screens.member;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @Description This class is for drawing adding member screen.
 * @author jimiewang
 * @CreateDate 01/28/2024
 */
public class AddMemberScreen extends JPanel {
	private JFrame parentFrame = null;
	
	public AddMemberScreen(JFrame frame) {
		this.parentFrame = frame; 
        setLayout(new GridBagLayout());
//      setLayout(new GridLayout(2, 1));
      add( drawPanel());
		drawPanel();
	}
	
	public JPanel drawPanel() {
        JPanel addMemberPanel = new JPanel(new GridLayout(6, 1));
        addMemberPanel.setPreferredSize(new Dimension(500, 230));

        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel titleLbl = new JLabel("New Member");
        titleLbl.setFont(new Font("Verdana", Font.PLAIN, 16));
        //titlePanel.add(new JLabel());
        titlePanel.add(titleLbl);
        addMemberPanel.add(titlePanel);
        
        JPanel namePanel = new JPanel(new GridLayout(1, 2));
        JPanel firNamePanel = new JPanel(new FlowLayout());
        JLabel firNmLbl = new JLabel("First Name: ");
        firNamePanel.add(firNmLbl);
        JTextField firNameField = new JTextField(20);
        firNamePanel.add(firNameField);
        namePanel.add(firNamePanel);
        JPanel lastNmPanel = new JPanel(new FlowLayout());
        JLabel lstNmLbl = new JLabel("Last Name: ");
        lastNmPanel.add(lstNmLbl);
        JTextField lstNmField = new JTextField(20);
        lastNmPanel.add(lstNmField);
        namePanel.add(lastNmPanel);
        addMemberPanel.add(namePanel);

        JPanel addressPanel = new JPanel(new GridLayout(1,2));
        JPanel emailPanel = new JPanel(new FlowLayout());
        JLabel emailLbl = new JLabel("Email : ");
        emailPanel.add(emailLbl);
        JTextField emailField = new JTextField(20);
        emailPanel.add(emailField);
        addressPanel.add(emailPanel);
        JPanel pwdPanel = new JPanel(new FlowLayout());
        JLabel pwdLbl = new JLabel("Create Password: ");
        pwdPanel.add(pwdLbl);
        JPasswordField pwdField = new JPasswordField(15);
        pwdPanel.add(pwdField);
        addressPanel.add(pwdPanel);
        addMemberPanel.add(addressPanel);

        JPanel livAddrPanel = new JPanel(new GridLayout(1,1));
//        JPanel memIDPanel = new JPanel(new FlowLayout());
//        JLabel memberIDLbl = new JLabel("ID: ");
//        memIDPanel.add(memberIDLbl);
//        JTextField memberIDField = new JTextField(7);
//        memIDPanel.add(memberIDField);

        JPanel livingAddrPanel = new JPanel(new FlowLayout());
        JLabel memberIDLbl = new JLabel("ID: ");
        livingAddrPanel.add(memberIDLbl);
        JTextField memberIDField = new JTextField(7);
        livingAddrPanel.add(memberIDField);
        livingAddrPanel.add(new JLabel("         "));
        JLabel livAddressLbl = new JLabel("Address: ");
        livingAddrPanel.add(livAddressLbl);
        JTextField livAddressField = new JTextField(30);
        livingAddrPanel.add(livAddressField);
//        livingAddrPanel.add(new JLabel("                                         "));
        livAddrPanel.add(livingAddrPanel);
//        JPanel emptyPanel = new JPanel(new FlowLayout());
//        livingAddrPanel.add(new JLabel(""));
//        livAddrPanel.add(emptyPanel);
        addMemberPanel.add(livAddrPanel);

	       JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
	       JButton saveBtn = new JButton("Save");
	       buttonPanel.add(new JLabel());
	       buttonPanel.add(saveBtn);
	       JButton resetBtn = new JButton("Reset");
	       buttonPanel.add(resetBtn);
	       buttonPanel.add(new JLabel());
			//add actionListener
	       saveBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
				//call the object of NewWindow and set visible true
					//NewWindow frame = new NewWindow();
					//frame.setVisible(true);
					//set default close operation
					//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//parentFrame.dispose();
					/*MemberHomeScreen memberScreen = new MemberHomeScreen();
					memberScreen.showMenuDemo();*/
				    //dispose();
				}
			});
	       addMemberPanel.add(buttonPanel);
//	       JPanel marginPanel = new JPanel(new FlowLayout());
//	       addMemberPanel.add(marginPanel);
        
		return addMemberPanel;
		 
	 }
}
