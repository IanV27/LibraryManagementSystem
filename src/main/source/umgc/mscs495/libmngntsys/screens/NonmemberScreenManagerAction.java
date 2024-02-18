package umgc.mscs495.libmngntsys.screens;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

/**
 * 
 * @Description This class is for defining action control of home screen.
 * @author jimiewang
 * @CreateDate 01/20/2024
 */
public abstract class NonmemberScreenManagerAction extends AbstractAction {

       private ScreenController controller;
       private JPanel panel;

       public NonmemberScreenManagerAction(ScreenController controller, JPanel panel) {
           this.controller = controller;
           this.panel = panel;
       }

       @Override
       public void actionPerformed(ActionEvent e) {
           controller.show(panel);
       }

}
