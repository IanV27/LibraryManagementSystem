package umgc.mscs495.libmngntsys.screens;

/**
 * 
 * @Description This class is for adding book searching action.
 * @author jimiewang
 * @CreateDate 01/20/2024
 */
public class BookSearchAction  extends NonmemberScreenManagerAction {
    public BookSearchAction(ScreenController controller) {
        super(controller, new BookSearchScreen());
        putValue(NAME, "Regular Book Search");
    }
}
