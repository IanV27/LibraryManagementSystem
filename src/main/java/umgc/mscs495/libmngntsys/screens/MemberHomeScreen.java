package umgc.mscs495.libmngntsys.screens;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import umgc.mscs495.libmngntsys.screens.book.Book1Add;
import umgc.mscs495.libmngntsys.screens.book.Book1Delete;
import umgc.mscs495.libmngntsys.screens.book.Book1GUI;
import umgc.mscs495.libmngntsys.screens.book.Book1Reserve;
import umgc.mscs495.libmngntsys.screens.librarian.AddForm;
import umgc.mscs495.libmngntsys.screens.librarian.EditLibrarian;
import umgc.mscs495.libmngntsys.screens.librarian.ViewLibrarian;
import umgc.mscs495.libmngntsys.screens.member.AddUserPage;
import umgc.mscs495.libmngntsys.screens.member.DeleteUserPage;
import umgc.mscs495.libmngntsys.screens.member.EditUserPage;
import umgc.mscs495.libmngntsys.screens.member.ViewUserPage;
import umgc.mscs495.libmngntsys.utils.AppUtils;
import umgc.mscs495.libmngntsys.utils.LMSStatics;

/**
 * 
 * @Description This class is for drawing the logged in home screen.
 * @author jimiewang
 * @CreateDate 01/25/2024
 */
public class MemberHomeScreen {
	JFrame mainFrame = null;
	public MemberHomeScreen() {
		mainFrame = new JFrame();
	}
	
	/**
	 * Initialize the screen display.
	 */
	private void prepareGUI() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		AppUtils appUtil = new AppUtils();
		mainFrame.setSize(dimension);
		mainFrame.setTitle("Library Management System");
        ImageIcon img = new ImageIcon(appUtil.getAppIcon());
        mainFrame.setIconImage(img.getImage());
		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.setResizable(false);
	    JLabel headerLabel = new JLabel("",JLabel.CENTER );
	    JLabel statusLabel = new JLabel("",JLabel.CENTER);        
	    statusLabel.setSize(350,100);
	      
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	      JPanel controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());
	      
	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(statusLabel);
	      mainFrame.setVisible(true);  
		
	}
	
	/**
	 * Create menus with action listener.
	 */
	public void showMenuScreen(){
		prepareGUI();
	    //create a menu bar
	    final JMenuBar menuBar = new JMenuBar();
	    menuBar.setFont(new Font("Serif", Font.BOLD, 18));
	    //create menus
	    JMenu memberMenu = new JMenu("Members");
	    memberMenu.setMnemonic(KeyEvent.VK_M);
	    JMenu librarianMenu = new JMenu("Librarian");
	    librarianMenu.setMnemonic(KeyEvent.VK_L);
	    JMenu bookMenu = new JMenu("Books");
	    bookMenu.setMnemonic(KeyEvent.VK_B);
	    final JMenu logoutMenu = new JMenu("Log Out");
	    logoutMenu.setMnemonic(KeyEvent.VK_O);
	    JMenuItem logoutItem = new JMenuItem("Log Out");
	    logoutItem.setActionCommand("Logout");
	    logoutMenu.add(logoutItem);
	    
	    //create menu items
	    JMenuItem newMemberMenuItem = new JMenuItem("Add Members");
	    newMemberMenuItem .setMnemonic(KeyEvent.VK_N);
	    newMemberMenuItem .setActionCommand("NewMembers");

	    JMenuItem editMemberMenuItem = new JMenuItem("Edit Members");
	    editMemberMenuItem.setActionCommand("EditMembers");

	    JMenuItem delMemberMenuItem = new JMenuItem("Delete Members");
	    delMemberMenuItem.setActionCommand("DeleteMembers");

	    JMenuItem viewMemberMenuItem = new JMenuItem("View Members");
	    viewMemberMenuItem.setActionCommand("ViewMembers");
//	      JMenuItem exitMenuItem = new JMenuItem("Exit");
//	      exitMenuItem.setActionCommand("Exit");

	    JMenuItem addLibMenuItem = new JMenuItem("Add Librarians");
	    addLibMenuItem.setActionCommand("AddLibrarians");

	    JMenuItem editLibMenuItem = new JMenuItem("Edit Librarians");
	    editLibMenuItem.setActionCommand("EditLibrarians");

	    JMenuItem delLibMenuItem = new JMenuItem("Delete Librarians");
	    delLibMenuItem.setActionCommand("DeleteLibrarians");

	    JMenuItem viewLibMenuItem = new JMenuItem("View Librarians");
	    viewLibMenuItem.setActionCommand("ViewLibrarians");
	      
	    JMenuItem addBookMenuItem = new JMenuItem("Add Books");
	    addBookMenuItem.setActionCommand("AddBooks");

	    JMenuItem delBookMenuItem = new JMenuItem("Delete Books");
	    delBookMenuItem.setActionCommand("DeleteBooks");

	    JMenuItem searchBookMenuItem = new JMenuItem("Search Books");
	    searchBookMenuItem.setActionCommand("SearchBooks");
	      
	    JMenuItem reserveBookMenuItem = new JMenuItem("Reserve Books");
	    reserveBookMenuItem.setActionCommand("ReserveBooks");
//	      JMenuItem subAboutMenuItem = new JMenuItem("Help");
//	      subAboutMenuItem.setActionCommand("Help");
	      
	    MenuItemListener menuItemListener = new MenuItemListener();

	    newMemberMenuItem.addActionListener(menuItemListener);
	    editMemberMenuItem.addActionListener(menuItemListener);
	    delMemberMenuItem.addActionListener(menuItemListener);
	    viewMemberMenuItem.addActionListener(menuItemListener);
	    addLibMenuItem.addActionListener(menuItemListener);
	    editLibMenuItem.addActionListener(menuItemListener);
	    delLibMenuItem.addActionListener(menuItemListener);
	    viewLibMenuItem.addActionListener(menuItemListener);
	    addBookMenuItem.addActionListener(menuItemListener);
	    delBookMenuItem.addActionListener(menuItemListener);
	    searchBookMenuItem.addActionListener(menuItemListener);
	    reserveBookMenuItem.addActionListener(menuItemListener);
	    logoutItem.addActionListener(menuItemListener);

	    final JCheckBoxMenuItem showWindowMenu = new JCheckBoxMenuItem("Show About", true);
	    showWindowMenu.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	            
	            if(showWindowMenu.getState()){
	               menuBar.add(bookMenu);
	            } else {
//	               menuBar.remove(aboutMenu);
	            }
	            mainFrame.revalidate();
	            mainFrame.repaint();
	         }
	      });
	      //add menu items to menus
	      memberMenu.add(viewMemberMenuItem);
	      memberMenu.add(newMemberMenuItem);
	      memberMenu.add(editMemberMenuItem);
	      memberMenu.add(delMemberMenuItem);
	      librarianMenu.add(viewLibMenuItem);       
	      librarianMenu.add(addLibMenuItem);        
	      librarianMenu.add(editLibMenuItem);        
	      librarianMenu.add(delLibMenuItem);        
	      
	      if(LMSStatics.getRoleCode() == 1) {
		      bookMenu.add(searchBookMenuItem);
	      }
	      if(LMSStatics.getRoleCode() == 2 || LMSStatics.getRoleCode() == 3) {
		      bookMenu.add(addBookMenuItem);
		      bookMenu.add(delBookMenuItem);
		      bookMenu.add(searchBookMenuItem);
		      bookMenu.add(reserveBookMenuItem);
	      }

	      //add menu to menubar
	      if(LMSStatics.getRoleCode() == 1) {
		      menuBar.add(bookMenu);       
	      }
	      if(LMSStatics.getRoleCode() == 2) {
		      menuBar.add(memberMenu);
		      menuBar.add(bookMenu);       
	      }
	      if(LMSStatics.getRoleCode() == 3) {
		      menuBar.add(memberMenu);
		      menuBar.add(librarianMenu);
		      menuBar.add(bookMenu);       
	      }	      
	      menuBar.add(logoutMenu);

	      //add menubar to the frame
	      mainFrame.setJMenuBar(menuBar);
	      mainFrame.setVisible(true);     
	   }

	   class MenuItemListener implements ActionListener {
		      public void actionPerformed(ActionEvent e) { 
		         
		         //Deleting member screen display
		         if(e.getActionCommand().equals("DeleteMembers")) {
		        	 DeleteUserPage delMemScrn = new DeleteUserPage();
		        	 delMemScrn.setLocationRelativeTo(null);
		        	 delMemScrn.setVisible(true);
		         }
		         
		         //Updating member information screen display
		         if(e.getActionCommand().equals("EditMembers")) {
		        	 EditUserPage editMemScrn = new EditUserPage();
		        	 editMemScrn.setLocationRelativeTo(null);
		        	 editMemScrn.setVisible(true);
		         }
		         
		         //Adding member screen display
		         if(e.getActionCommand().equals("NewMembers")) {
		        	 AddUserPage addMemScrn = new AddUserPage();
//		        	 AddMemberScreen addMemScrn = new AddMemberScreen();
//		        	 DisplayMemberScreens displayMemScrn = new DisplayMemberScreens();
//		        	 displayMemScrn.displayAddMemberWindow();
		        	 addMemScrn.setLocationRelativeTo(null);
		        	 addMemScrn.setVisible(true);
		         }
		         
		         //Viewing member screen display
		         if(e.getActionCommand().equals("ViewMembers")) {
		        	 ViewUserPage viewMemScrn = new ViewUserPage();
		        	 viewMemScrn.setLocationRelativeTo(null);
		        	 viewMemScrn.setVisible(true);
		         }
		         
		         //Adding librarian screen display
		         if(e.getActionCommand().equals("AddLibrarians")) {
		        	 AddForm addLib = new AddForm();
		        	 addLib.setLocationRelativeTo(null);
		        	 addLib.setVisible(true);
		         }

		         //Editing librarian screen display
		         if(e.getActionCommand().equals("EditLibrarians")) {
		        	 EditLibrarian editLib = new EditLibrarian();
		        	 editLib.setLocationRelativeTo(null);
		        	 editLib.setVisible(true);
		         }

		         //Deleting librarian screen display
		         if(e.getActionCommand().equals("DeleteLibrarians")) {
		        	 ViewLibrarian libHomeScren = new ViewLibrarian();
		        	 libHomeScren.setLocationRelativeTo(null);
		        	 libHomeScren.setVisible(true);
		         }

		         //Viewing librarian screen display
		         if(e.getActionCommand().equals("ViewLibrarians")) {
		        	 ViewLibrarian viewLib = new ViewLibrarian();
		        	 viewLib.setLocationRelativeTo(null);
		        	 viewLib.setVisible(true);
		         }

		         //Adding book screen display
		         if(e.getActionCommand().equals("AddBooks")) {
		        	 Book1Add addBookScren = new Book1Add();
		        	 addBookScren.setLocationRelativeTo(null);
		        	 addBookScren.setVisible(true);
		         }
		         
		         //Deleting book screen display
		         if(e.getActionCommand().equals("DeleteBooks")) {
		        	 Book1Delete delBookScren = new Book1Delete();
		        	 delBookScren.setLocationRelativeTo(null);
		        	 delBookScren.setAlwaysOnTop(true);
		        	 delBookScren.setVisible(true);
		         }

		         //Searching book screen display
		         if(e.getActionCommand().equals("SearchBooks")) {
		        	 Book1GUI searchBook = new Book1GUI();
		        	 searchBook.setLocationRelativeTo(null);
		        	 searchBook.setVisible(true);
		         }
		         
		         //Reserving book screen display
		         if(e.getActionCommand().equals("ReserveBooks")) {
		        	 Book1Reserve bookReserve = new Book1Reserve();
		        	 bookReserve.setLocationRelativeTo(null);
		        	 bookReserve.setVisible(true);
		         }
		         
		         //Log out
		         if(e.getActionCommand().equals("Logout")) {
		        	 mainFrame.dispose();
		        	 new NonmemberHomeScreen(mainFrame);
		         }
		      }    
		   }
	   
}
