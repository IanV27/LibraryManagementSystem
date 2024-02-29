package umgc.mscs495.libmngntsys.DAO;
import umgc.mscs495.libmngntsys.vo.Book;
import umgc.mscs495.libmngntsys.vo.BookReservation;
import umgc.mscs495.libmngntsys.vo.Librarian;

import java.util.List;

import umgc.mscs495.libmngntsys.vo.Librarian;

public interface BooksDAO {
	 public Book search(Book book);
	 public BookReservation reserve(BookReservation bookreservation);
//	 public List<Book> list();
//	 public void reserve(Book book);
	 }
