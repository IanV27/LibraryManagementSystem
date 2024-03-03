package umgc.mscs495.libmngntsys.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import umgc.mscs495.libmngntsys.DAO.UsersAccountsOperations;
import umgc.mscs495.libmngntsys.utils.AppLoggingUtil;
import umgc.mscs495.libmngntsys.utils.AppUtils;
import umgc.mscs495.libmngntsys.utils.LMSStatics;
import umgc.mscs495.libmngntsys.vo.Account;
import umgc.mscs495.libmngntsys.vo.UserRole;

/**
 * 
 * @Description This class is for drawing the login screen.
 * @author jimiewang
 * @CreateDate 01/20/2024
 */
public class LoginScreen extends JPanel {
	private JFrame parentFrame;
	private final Font loginFont = new Font("Serif", Font.ITALIC, 14);
	private int trialCount = 0;
	String loginErrMsg = "";
	
	public LoginScreen(JFrame nonMemberFrame) {
		this.parentFrame= nonMemberFrame; 
        setLayout(new GridBagLayout());
//        setLayout(new GridLayout(2, 1));
        add( drawPanel());
       
   }
	 
	 public JPanel drawPanel() {
		AppUtils appUtil = new AppUtils();
		AppLoggingUtil logging = new AppLoggingUtil();
		UsersAccountsOperations usrAccntOpts = new UsersAccountsOperations();
        JLabel msgLbl = new JLabel("", SwingConstants.CENTER);
        JPanel loginPanel = new JPanel(new GridLayout(8, 1));
        loginPanel.setBackground(Color.CYAN);
        loginPanel.setPreferredSize(new Dimension(400, 200));
        loginPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        //add login screen title
        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel titleLbl = new JLabel("Log In");
        titleLbl.setFont(new Font("Verdana", Font.PLAIN, 16));
        titlePanel.add(titleLbl);

        //add username input section
        loginPanel.add(titlePanel);
        JPanel usernamePanel = new JPanel(new FlowLayout());
        usernamePanel.setPreferredSize(new Dimension(400, 30));
        JLabel usernameLbl = new JLabel("User Name: ");
        usernameLbl.setFont(loginFont);
        usernamePanel.add(usernameLbl);
        JTextField usernameField = new JTextField(30); 
        usernameField.setFont(loginFont);
        usernamePanel.add(usernameField);
        loginPanel.add(usernamePanel);
        
        //add password input section
        JPanel passwordPanel = new JPanel(new FlowLayout());
        JLabel pwdLbl = new JLabel(" Password: ");
        pwdLbl.setFont(loginFont);
        passwordPanel.add(pwdLbl);
        JPasswordField pwdField = new JPasswordField(30); 
        pwdField.setFont(loginFont);
        passwordPanel.add(pwdField);
        loginPanel.add(passwordPanel);
       
        JPanel rolePanel = new JPanel(new FlowLayout());
        JLabel roleLbl = new JLabel("Role: ");
        roleLbl.setFont(loginFont);
        rolePanel.add(roleLbl);
        List<UserRole> userRoles = appUtil.getLibLoginUserRoles();
        
        Vector libRoles = new Vector();
        libRoles.addAll(userRoles);
       
        JComboBox roleSelBox = new JComboBox(libRoles);
        roleSelBox.setFont(loginFont);
        roleSelBox.setRenderer( new ItemRenderer() );
        rolePanel.add(roleSelBox);
        rolePanel.add(new JLabel("                                                                     "));
        loginPanel.add(rolePanel);
        loginPanel.add(new JPanel());
       
        //add button section
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        JButton loginBtn = new JButton("Log In");
        loginBtn.setFont(loginFont);
        buttonPanel.add(new JLabel());
        buttonPanel.add(loginBtn);
        JButton resetBtn = new JButton("Reset");
        resetBtn.setFont(loginFont);
        buttonPanel.add(resetBtn);
        buttonPanel.add(new JLabel());
		//add actionListener for login button
        loginBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
			//call the object of NewWindow and set visible true
				UserRole selectedRole = (UserRole)roleSelBox.getSelectedItem();
				int roleCode = selectedRole.getRoleID();
				String username = usernameField.getText();
				String password = String.valueOf(pwdField.getPassword());
				System.out.println(username +" " + password + roleCode);
				//input username and password validation
				if(username == null || username != null && username.trim().isEmpty()) {
					loginErrMsg = "Please input your username.";
				} else if(password == null || password != null && password.trim().isEmpty()) {
					loginErrMsg = "Please input your password.";
				} else {
					username = username.trim();
					//authenticate input user's account
					Account loginAccount = usrAccntOpts.getUserAccount(username, roleCode);
					if(loginAccount != null) {
						//check if the account has been locked
						if(loginAccount.getActiveFlg() == LMSStatics.INACTIVE_ACCOUNT) {
							loginErrMsg = "Your account has been locked please contact with Librarian.";
						} else {
							//validate the input password
							if(loginAccount.getPassword().equals(password)) {
								logging.log(username + " logged in successfully!");
								LMSStatics.setRoleCode(roleCode);
								parentFrame.dispose();
								MemberHomeScreen memberScreen = new MemberHomeScreen();
								memberScreen.showMenuScreen();
							} else {
								//max trial count check
								if(trialCount > LMSStatics.MAX_TRIAL_COUNT) {
									//lock user's account when exceed max trian count
									usrAccntOpts.disableUserAccount(username);
									logging.log(username + " account is locked up!");
								} else {
									trialCount += 1;
									logging.log(username + " logged in failure!");
								}
								loginErrMsg = "Input username or password not correct, please try a gain.";
							}
						}
					} else {
						loginErrMsg = "Input username or password not correct.";
					}
					
				}
				//display error message
				msgLbl.setText(loginErrMsg);
				if(roleCode == LMSStatics.MEMBER_ROLE_CODE) {
					
				} else if(roleCode == LMSStatics.LIBRARIAN_ROLE_CODE) {
					
				} else if(roleCode == LMSStatics.DBA_ROLE_CODE) {
					
				} else {
					
					System.out.println("Invalid login role selected");
				}
			}
		}); 
        resetBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//reset username, password and role selection
				usernameField.setText("");
				pwdField.setText("");
				msgLbl.setText("");
				roleSelBox.setSelectedIndex(0);
			}
		});       

        loginPanel.add(buttonPanel);
        JPanel warningMsgPanel = new JPanel(new GridLayout(1, 1));
        warningMsgPanel.add(msgLbl);
        loginPanel.add(warningMsgPanel);
        msgLbl.setForeground(Color.red);
        
        JPanel marginPanel = new JPanel(new FlowLayout());
        loginPanel.add(marginPanel);
       
       
        return loginPanel;
	 }
	 
	 /**
	  * Item renderer for role selection dropdown list
	  * @author jimiewang
	  *
	  */
	 class ItemRenderer extends BasicComboBoxRenderer
	    {
	        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	        {
	            super.getListCellRendererComponent(list, value, index,
	                isSelected, cellHasFocus);
	 
	            if (value != null)
	            {
	            	UserRole item = (UserRole)value;
	                setText( item.getRoleName().toUpperCase() );
	            }
	 
	            if (index == -1)
	            {
	            	UserRole item = (UserRole)value;
	                setText( "" + item.getRoleName().toUpperCase() );
	            }
	 
	 
	            return this;
	        }
	    }
}
