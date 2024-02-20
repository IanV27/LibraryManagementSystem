package umgc.mscs495.libmngntsys.screens;

import javax.swing.JFrame;

/**
 * 
 * @Description This class is for adding login action.
 * @author jimiewang
 * @CreateDate 01/20/2024
 */
public class LoginAction  extends NonmemberScreenManagerAction {
    public LoginAction(ScreenController controller, JFrame nonMemberFrame) {
        super(controller, new LoginScreen(nonMemberFrame));
        putValue(NAME, "Member Log In");
    }
}
