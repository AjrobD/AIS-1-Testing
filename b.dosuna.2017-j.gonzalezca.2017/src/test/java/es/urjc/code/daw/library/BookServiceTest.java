package es.urjc.code.daw.library;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.urjc.code.daw.library.book.Book;
import es.urjc.code.daw.library.book.BookRepository;
import es.urjc.code.daw.library.book.BookService;
import es.urjc.code.daw.library.notification.NotificationService;


public class BookServiceTest {
	
	@Test
	public void givenBookService_whenABookIsAdded_thenUserIsAdded_and_thenNotificationIsSent() {
		//given
		BookRepository bookRepository = mock(BookRepository.class);
		NotificationService notificationService = mock(NotificationService.class);
		BookService bookService = new BookService(bookRepository,notificationService);
		Logger logger = mock(Logger.class);
		//when
		Book book = mock(Book.class);
		when(bookRepository.save(book)).thenReturn(book);
		bookService.save(book);
		//when(notificationService.notify("Book Event: book with title="+book.getTitle()+" was created")).then(logger.info("Book Event: book with title="+book.getTitle()+" was created"));
		doAnswer(invocation -> {
			Logger logger = LoggerFactory.getLogger(NotificationService.class);
			logger.info("Book Event: book with title="+book.getTitle()+" was created");
		}).when(notificationService).notify("Book Event: book with title="+book.getTitle()+" was created");
	}
}
