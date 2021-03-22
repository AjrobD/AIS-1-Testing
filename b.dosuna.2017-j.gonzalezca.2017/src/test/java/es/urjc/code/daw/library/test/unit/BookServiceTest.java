package es.urjc.code.daw.library.test.unit;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.urjc.code.daw.library.book.Book;
import es.urjc.code.daw.library.book.BookRepository;
import es.urjc.code.daw.library.book.BookService;
import es.urjc.code.daw.library.notification.NotificationService;

@DisplayName("Tests unitarios de la logica de la aplicacion")
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
	@DisplayName("Comprobar que cuando se guarda un libro utilizando BookService, se guarda en el repositorio y se lanza una notificacion")
	public void givenBookService_whenABookIsAdded_thenBookIsAddedToRepository_and_thenNotificationIsSent() {
		//Given: done in set up
		//When
		Book book = mock(Book.class);
		when(bookRepository.save(book)).thenReturn(book);
		when(book.getTitle()).thenReturn("Blancanieves");
		bookService.save(book);
		//Then
		verify(bookRepository).save(book);
		verify(notificationService).notify("Book Event: book with title="+book.getTitle()+" was created");
	}
	
	@Test
	@DisplayName("Comprobar que cuando se borra un libro utilizando BookService, se elimina del repositorio y se lanza una notificacion")
	public void givenBookService_whenABookIsDeleted_thenIsDeletedFromBookRepository_and_thenNotificationIsSent() {
		//Given
		Book book = mock(Book.class);
		when(book.getTitle()).thenReturn("Prueba");
		while(bookRepository.existsById(book.getId())) {
			book.setId(book.getId()+1);
		}
		when(bookRepository.save(book)).thenReturn(book);
		bookService.save(book);
		
		//When
		bookService.delete(book.getId());
		
		//Then
		verify(bookRepository, times(1)).deleteById(book.getId());
		when(bookRepository.existsById(book.getId())).thenReturn(false);
		verify(notificationService).notify("Book Event: book with id="+book.getId()+" was deleted");
	}
}
