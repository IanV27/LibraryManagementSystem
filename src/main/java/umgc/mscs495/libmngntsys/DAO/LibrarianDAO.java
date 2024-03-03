/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package umgc.mscs495.libmngntsys.DAO;
import java.awt.Component;
import java.util.List;
import umgc.mscs495.libmngntsys.vo.Librarian;

/**
 *
 * @author ramuk
 */
public interface LibrarianDAO {
    public void save(Librarian librarian, Component parenComp);
    public void update(Librarian librarian, Component parenComp);
    public boolean delete(Librarian librarian);
    public Librarian get(String id);
    public List<Librarian> list();
}
