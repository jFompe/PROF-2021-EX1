package es.upm.grise.profundizacion.control_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {


	Library library;

	@BeforeEach
	public void setup() {
		library = new Library();
	}

	@Test
	public void test_addOneBook() {
		String title = "GOT";
		Book book = new Book(title);

		assertDoesNotThrow(() -> {
			library.addBook(book);
		});
	}

	@Test
	public void test_addTwoBooks() {
		String title1 = "GOT1";
		String title2 = "GOT2";
		Book book1 = new Book(title1);
		Book book2 = new Book(title2);

		assertDoesNotThrow(() -> {
			library.addBook(book1);
			library.addBook(book2);
		});
	}

	@Test
	public void test_addRepeatedBook() {
		String title = "GOT";
		Book book1 = new Book(title);
		Book book2 = new Book(title);

		assertThrows(DuplicatedBookException.class, () -> {
			library.addBook(book1);
			library.addBook(book2);
		});
	}

	@Test
	public void test_removeBookEmptyLibrary() {
		String title = "GOT";
		Book book = new Book(title);

		assertThrows(EmptyLibraryException.class, () -> {
			library.addBook(book);
			library.removeBook(book);
			library.getBook(title);
		});
	}

	@Test
	public void test_removeBook() {
		String title1 = "GOT";
		String title2 = "GOT2";
		Book book1 = new Book(title1);
		Book book2 = new Book(title2);

		assertThrows(NonExistingBookException.class, () -> {
			library.addBook(book1);
			library.addBook(book2);
			library.removeBook(book1);
			library.getBook(title1);
		});
	}

	@Test
	public void test_getBook() {
		String title = "GOT";
		Book book = new Book(title);
		try {
			library.addBook(book);
			assertEquals(library.getBook(title), book);
		} catch (DuplicatedBookException |
				NonExistingBookException |
				EmptyLibraryException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_getBookEmpty() {
		String title = "GOT";
		assertThrows(EmptyLibraryException.class, () -> {
			library.getBook(title);
		});
	}

	@Test
	public void test_getBookNonExisting() {
		String title = "GOT";
		String otherTitle = "GOT2";
		assertThrows(NonExistingBookException.class, () -> {
			library.addBook(new Book(title));
			library.getBook(otherTitle);
		});
	}

}
