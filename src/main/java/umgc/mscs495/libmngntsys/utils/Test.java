package umgc.mscs495.libmngntsys.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import umgc.mscs495.libmngntsys.screens.MemberHomeScreen;
//import umgc.mscs495.libmngntsys.screens.NonmemberHomeScreen;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CredUtil crdutl = new CredUtil();
		try {
			//AppUtils appUtil = new AppUtils();
			//appUtil.getBookLanguages();
			//Test tst = new Test();
//			PicFrame userFrame = new PicFrame();
			JFrame userFrame = new JFrame();
			userFrame.setBackground(Color.cyan);
//			NonmemberHomeScreen nonmemscrn = new NonmemberHomeScreen(userFrame);
			MemberHomeScreen nonmemscrn = new MemberHomeScreen();
			nonmemscrn.showMenuScreen();
//		String decrykey = crdutl.getPropValue(crdutl.getConfigFileFullPath(), "loginkey");
//		String enppwd = crdutl.encrypt("dsfwe@sfs", decrykey);
//		String pwd = crdutl.decrypt(enppwd, decrykey);
//		System.out.println(enppwd);
//		System.out.println(pwd);
		} catch(Exception e) {
			
		}
	}
	
}

