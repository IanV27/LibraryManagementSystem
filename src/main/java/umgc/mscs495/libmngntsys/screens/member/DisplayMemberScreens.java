package umgc.mscs495.libmngntsys.screens.member;

import java.awt.Dialog;

import javax.swing.JFrame;

public class DisplayMemberScreens {
	public void displayLoginConfirmWindow() {
		JFrame frame = new JFrame();
        frame.add(new MemberLoginScreen(frame));
        frame.setFocusable(true);
        frame.setFocusableWindowState(true);
        frame.setAlwaysOnTop(true);
        frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        frame.getRootPane().disable();
        frame.pack(); 
	    frame.setVisible(true);
		
	}
	
	public void displayAddMemberWindow() {
		JFrame frame = new JFrame();
        frame.add(new AddMemberScreen(frame));
        frame.setFocusable(true);
        frame.setFocusableWindowState(true);
        frame.setAlwaysOnTop(true);
        frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        frame.getRootPane().disable();
        frame.pack(); 
	    frame.setVisible(true);
		
	}
	
}
