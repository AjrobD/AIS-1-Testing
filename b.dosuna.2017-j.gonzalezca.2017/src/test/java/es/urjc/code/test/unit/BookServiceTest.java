package es.urjc.code.test.unit;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.urjc.code.daw.library.book.Book;
import es.urjc.code.daw.library.book.BookRepository;
import es.urjc.code.daw.library.book.BookService;
import es.urjc.code.daw.library.notification.NotificationService;


public class BookServiceTest {
	
	BookService bookService;
	NotificationService notificationService;
	BookRepository bookRepository;
	long count;
	
	//Book Service using doubles for arguments
	@BeforeEach
	public void set_Up_BookService() {
		this.bookRepository = mock(BookRepository.class);
		this.notificationService = mock(NotificationService.class);
		this.bookService = new BookService(bookRepository,notificationService);
		this.count = bookRepository.count();
	}
	
	@Test
	public void givenBookService_whenABookIsAdded_thenBookIsAddedToRepository_and_thenNotificationIsSent() {
		//given: done in set up
		//when
		Book book = mock(Book.class);
		when(bookRepository.save(book)).thenReturn(book);
		when(book.getTitle()).thenReturn("Blancanieves");
		bookService.save(book);
		//then
		verify(bookRepository).save(book);
		verify(notificationService).notify("Book Event: book with title="+book.getTitle()+" was created");
	}

}
