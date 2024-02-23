package umgc.mscs495.libmngntsys.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import umgc.mscs495.libmngntsys.utils.AppLoggingUtil;
import umgc.mscs495.libmngntsys.utils.AppUtils;
import umgc.mscs495.libmngntsys.utils.LibMemberDatabaseConnection;
import umgc.mscs495.libmngntsys.vo.Book;

/**
 * 
 * @Description This class is for drawing the book search screen for non-member.
 * @author jimiewang
 * @CreateDate 01/25/2024
 */
public class BookSearchScreen extends JPanel {
	private boolean DEBUG = true;
	private final Font searchFont = new Font("Serif", Font.PLAIN, 14);
	public BookSearchScreen() {
//         setLayout(new GridBagLayout());
//       setLayout(new GridLayout(2, 1, 10, 2));
       setLayout(new GridLayout(1, 1));
         add( drawPanel());
         
     }
	 
	public JPanel drawPanel() {
		AppUtils appUtil = new AppUtils();
		AppLoggingUtil logging = new AppLoggingUtil();
//         JPanel bookSearchPanel = new JPanel(new GridLayout(3, 2, 10, 2));
        ////JPanel bookSearchPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        JPanel bookSearchPanel = new JPanel(new GridLayout(1, 1));
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        bookSearchPanel.setSize(new Dimension(width, 1000));

        JPanel conditionPanel = new JPanel(new GridLayout(5, 2));
        conditionPanel.setSize(new Dimension(width, 250));
        //add book name section
        JPanel bookTitleAutherPanel = new JPanel(new GridLayout(1, 3));
        JPanel bookTitlePanel = new JPanel(new FlowLayout());
        JLabel bookNmLbl = new JLabel("Book Name: ");
        bookNmLbl.setFont(searchFont);
        bookTitlePanel.add(bookNmLbl);
        JTextField bookNameField = new JTextField(50);
        bookNameField.setFont(searchFont);
        bookTitlePanel.add(bookNameField);
        bookTitleAutherPanel.add(bookTitlePanel);
        
        //add author section
        JPanel authorPanel = new JPanel(new FlowLayout());
        JLabel authorLbl = new JLabel("Author: ");
        authorLbl.setFont(searchFont);
        authorPanel.add(authorLbl);
        JTextField authorField = new JTextField(50);
        authorField.setFont(searchFont);
        authorPanel.add(authorField);
        bookTitleAutherPanel.add(authorPanel);

        //add publisher section
        JPanel publishPanel = new JPanel(new GridLayout(1,3));
        JPanel publisherPanel = new JPanel(new FlowLayout());
        JLabel publisherLbl = new JLabel("Publisher: ");
        publisherLbl.setFont(searchFont);
        publisherPanel.add(publisherLbl);
        JTextField publisherField = new JTextField(15);
        publisherField.setFont(searchFont);
        publisherPanel.add(publisherField);
        publishPanel.add(publisherPanel);
        //add publish year section
        JPanel publishYrPanel = new JPanel(new FlowLayout());
        JLabel publishYrLbl = new JLabel("Publisher Year: ");
        publishYrLbl.setFont(searchFont);
        publishYrPanel.add(publishYrLbl);
        //Initialize publish year dropdown list values
        Calendar now = Calendar.getInstance(Locale.US);
        String publishYearArr[] = new String[now.get(Calendar.YEAR) - 1919];
        publishYearArr[0] = "Select Year";
        for(int year=now.get(Calendar.YEAR); year > 1920; year--) {
        	publishYearArr[now.get(Calendar.YEAR) - year + 1] = "" + year;
        }
        JComboBox publisherCombox = new JComboBox(publishYearArr);
        publisherCombox.setFont(searchFont);
        publishYrPanel.add(publisherCombox);
        publishPanel.add(publishYrPanel);
        
        //Add languages select options
        JPanel languagePanel = new JPanel(new FlowLayout());
        JLabel languageLbl = new JLabel("Language: ");
        languageLbl.setFont(searchFont);
        languagePanel.add(languageLbl);
        List<String> languagesLst = appUtil.getBookLanguages();
        String[] languagesArr = languagesLst.toArray(String[]::new);
        JComboBox languagesCombox = new JComboBox(languagesArr);
        languagesCombox.setFont(searchFont);
        languagePanel.add(languagesCombox);
        publishPanel.add(languagePanel);
        
        //Add buttons section
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6));
        buttonPanel.add(new JLabel());
        //add search button
        JButton searchBtn = new JButton("Search");
        searchBtn.setFont(searchFont);
        buttonPanel.add(searchBtn);
        buttonPanel.add(new JLabel());
        buttonPanel.add(searchBtn);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        //add reset button
        JButton resetBtn = new JButton("Reset");
        resetBtn.setFont(searchFont);
        buttonPanel.add(resetBtn);
        buttonPanel.add(new JLabel());
        conditionPanel.add(bookTitleAutherPanel);
        conditionPanel.add(publishPanel);
        conditionPanel.add(new JLabel(""));
        conditionPanel.add(buttonPanel);
         
        //define reset button action
        BookSearchTableModel bookTableModel = new BookSearchTableModel();
        resetBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				publisherField.setText("");
				bookNameField.setText("");
				authorField.setText("");
				languagesCombox.setSelectedIndex(0);
				publisherCombox.setSelectedIndex(0);
				Object[][] data = {{"", "", "", "", ""}};
				bookTableModel.setSearchResult(data);
				bookTableModel.fireTableDataChanged();
			}
        	
		});
        //define search button action
        searchBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//get all input parameters for book search
				String bookname = bookNameField.getText().trim();
				String author = authorField.getText().trim();
				String publisher = publisherField.getText().trim();
				int publishYear = 0;
				if(publisherCombox.getSelectedIndex() > 0) {
					publishYear = Integer.parseInt((String)publisherCombox.getSelectedItem());
				}
				String language = (String)languagesCombox.getSelectedItem();
				//search books based on the input parameters.
				String query = "Select Title, Author, Publisher, Language, PublishYear from Books where Title like ? "
						+ "And Author like ? And Publisher like ? And language = ? ";
				
				if(publishYear > 0) {
					query = query + " And PublishYear = ? ";
				}
				List<Book> booksLst = new ArrayList<>();
				try {
					//search books based on the input parameters.
					Connection conn = LibMemberDatabaseConnection.getConnection();
					PreparedStatement prepStnt = conn.prepareStatement(query);
					prepStnt.setString(1, "%" + bookname + "%");
					prepStnt.setString(2, "%" + author + "%");
					prepStnt.setString(3, "%" + publisher + "%");
					prepStnt.setString(4, language);
					if(publishYear > 0) {
						prepStnt.setInt(5, publishYear);
					}
					ResultSet booksRes = prepStnt.executeQuery();
					while(booksRes.next()) {
						Book book = new Book();
						book.setBookTitle(booksRes.getString("Title"));
						book.setAuthor(booksRes.getString("Author"));
						book.setPublisher(booksRes.getString("Publisher"));
						book.setPublishYear(booksRes.getInt("PublishYear"));
						book.setLanguage(booksRes.getString("Language"));
						booksLst.add(book);
						System.out.println(booksRes.getString("Title"));
					}
				} catch(Exception e) {
					logging.log(e.getMessage());
				}
				//Place book search result to the data resource of datatable for display.
				Object[][] booksInfo = null;
				if(booksLst.size() > 0) {
					booksInfo = new Object[booksLst.size()][5];
					for(int i=0; i< booksLst.size(); i++) {
						Book book = booksLst.get(i);
						booksInfo[i][0] = book.getBookTitle();
						booksInfo[i][1] = book.getAuthor();
						booksInfo[i][2] = book.getPublisher();
						booksInfo[i][3] = String.valueOf(book.getPublishYear());
						booksInfo[i][4] = book.getLanguage();
					}
				} else {
					booksInfo = new Object[][]{{"", "", "", "", ""}};
				}
				//Pass search result into table model.
				bookTableModel.setSearchResult(booksInfo);
				bookTableModel.fireTableDataChanged();
			}
		});
        //Define data table fitures
	    JTable table = new JTable(bookTableModel);
	    JTableHeader header = table.getTableHeader();
	    header.setFont(searchFont);
	    header.setOpaque(false);
	    header.setBackground(Color.LIGHT_GRAY);
	    table.setFont(searchFont);
	    table.setPreferredScrollableViewportSize(new Dimension(width, 100));
	    table.setFillsViewportHeight(true);
	    //define display table row colors
	    table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
	    	//define the table row background colors
	        @Override
	        public Component getTableCellRendererComponent(JTable table, 
	                Object value, boolean isSelected, boolean hasFocus,
	                int row, int column) {
	            Component c = super.getTableCellRendererComponent(table, 
	                value, isSelected, hasFocus, row, column);
	            c.setBackground(row%2==0 ? Color.CYAN : Color.BLUE);                        
	            return c;
	        };
	    });
	    //define display tabl sorting feature
	    TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
	    table.setRowSorter(sorter);
	    List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	    
	    //Add sorting for display table, when user clicking on table header, the table rows
	    //will be sorted either descendently or ascendently based on alphabet sequence
	    int columnIndexToSort = 1;
	    sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
	    sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
	    //adding sorting listener
	    sorter.addRowSorterListener(new RowSorterListener() {
	        @Override
	        public void sorterChanged(RowSorterEvent evt) {
	            int indexOfNoColumn = 0;
	            for (int i = 0; i < table.getRowCount(); i++) {
	               // table.setValueAt(i + 1, i, indexOfNoColumn);
	            }
	        }
	    });    

	    sorter.setSortKeys(sortKeys);
	    sorter.setSortable(0, false);
	    sorter.sort();
	    //Create the scroll pane and add the table to it.
	    table.setPreferredScrollableViewportSize(table.getPreferredSize());   
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setViewportView(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.LINE_AXIS));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));
        resultPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        resultPanel.add(scrollPane);
        resultPanel.setSize(new Dimension(width, 400));
        
         JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
        		 conditionPanel, resultPanel);
         splitPane.setOneTouchExpandable(false);
         splitPane.setDividerLocation(150);
         splitPane.setContinuousLayout(true);
         splitPane.setPreferredSize(new Dimension(width, height));
	     bookSearchPanel.add(splitPane);
         
		 return bookSearchPanel;
	 }

	class BookSearchTableModel extends AbstractTableModel {
	    private String[] columnNames = { "Book Name", "Auhtor", "Publisher",
	        "Publish Year", "Language" };
	    private Object[][] books = {{"", "", "", "", ""}};

	    public void setSearchResult(Object[][] result) {
	    	this.books = result;
	    }
	    
	    public Object[][] getSearchResult() {
	    	return this.books;
	    }
	    
	    public int getColumnCount() {
	      return columnNames.length;
	    }

	    public int getRowCount() {
	      return books.length;
	    }

	    public String getColumnName(int col) {
	      return columnNames[col];
	    }

	    public Object getValueAt(int row, int col) {
	      return books[row][col];
	    }

	    /**
	     * JTable uses this method to determine the default renderer/ editor for
	     * each cell. If we didn't implement this method, then the last column
	     * would contain text ("true"/"false"), rather than a check box.
	     */
	    public Class getColumnClass(int c) {
	      return getValueAt(0, c).getClass();
	    }

	    /**
	     * Don't need to implement this method unless your table's editable.
	     */
	    public boolean isCellEditable(int row, int col) {
	      //Note that the data/cell address is constant,
	      //no matter where the cell appears onscreen.
	        return false;
	    }

	}

}
