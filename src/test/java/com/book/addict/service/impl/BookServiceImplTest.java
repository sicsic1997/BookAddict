package com.book.addict.service.impl;

import com.book.addict.domain.BookDashboardFilter;
import com.book.addict.dto.BookDTO;
import com.book.addict.dto.CategoryDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceImplTest {

    private BookServiceImplBookListMockGenerator bookServiceMockGenerator;
    private BookServiceImpl bookService;

    @Before
    public void setUp() {
        bookServiceMockGenerator = new BookServiceImplBookListMockGenerator();
        bookService = new BookServiceImpl();
    }

    @Test
    public void getFilteredResultsTestPriceRange() {

        List<BookDTO> bookList = bookServiceMockGenerator.getBooksForFilter();
        BookDashboardFilter bookDashboardFilter = bookServiceMockGenerator.getBookDashboardFilterEmptyTextFilter();
        List<BookDTO> filteredBookList = bookService.getFilteredResults(bookList, bookDashboardFilter);

        for (BookDTO bookDto:filteredBookList) {
            //Price range assertion
            Assert.assertTrue(
                    bookDto.getPrice()
                            .subtract(bookDashboardFilter.getMinPrice())
                            .compareTo(BigDecimal.valueOf(0)) > 0 &&
                            bookDto.getPrice()
                            .subtract(bookDashboardFilter.getMaxPrice())
                            .compareTo(BigDecimal.valueOf(0)) < 0);

        }

    }

    @Test
    public void getFilteredResultsTestCategories() {

        List<BookDTO> bookList = bookServiceMockGenerator.getBooksForFilter();
        BookDashboardFilter bookDashboardFilter = bookServiceMockGenerator.getBookDashboardFilterEmptyTextFilter();
        List<BookDTO> filteredBookList = bookService.getFilteredResults(bookList, bookDashboardFilter);

        for (BookDTO bookDto:filteredBookList) {
            //Price range assertion
            boolean matchesCategory = false;
            for (CategoryDTO categoryDTO: bookDto.getCategories()) {
                for (CategoryDTO categoryFilterDTO:bookDashboardFilter.getCategoryDTOList()) {
                    if(categoryDTO.getIdCategory() == categoryFilterDTO.getIdCategory()) {
                        matchesCategory = true;
                    }
                }
            }
            Assert.assertTrue(matchesCategory);

        }

    }

    @Test
    public void getFilteredResultsTestTextFilter() {

        List<BookDTO> bookList = bookServiceMockGenerator.getBooksForFilter();
        BookDashboardFilter bookDashboardFilter = bookServiceMockGenerator.getBookDashboardFilterNonEmptyTextFilter();
        List<BookDTO> filteredBookList = bookService.getFilteredResults(bookList, bookDashboardFilter);

        for (BookDTO bookDto:filteredBookList) {
            //Price range assertion
            Assert.assertTrue(bookDto.getBookName().toLowerCase().contains(bookDashboardFilter.getBookOrAuthorName().toLowerCase()) ||
            bookDto.getWriter().getDeWriter().toLowerCase().contains(bookDashboardFilter.getBookOrAuthorName().toLowerCase()));

        }

    }

    @Test(expected = NullPointerException.class)
    public void getFilteredResultsTestTextFilterNullFilter() {

        List<BookDTO> bookList = bookServiceMockGenerator.getBooksForFilter();
        BookDashboardFilter bookDashboardFilter = null;
        List<BookDTO> filteredBookList = bookService.getFilteredResults(bookList, bookDashboardFilter);

        for (BookDTO bookDto:filteredBookList) {
            //Price range assertion
            Assert.assertTrue(bookDto.getBookName().toLowerCase().contains(bookDashboardFilter.getBookOrAuthorName().toLowerCase()) ||
                    bookDto.getWriter().getDeWriter().toLowerCase().contains(bookDashboardFilter.getBookOrAuthorName().toLowerCase()));

        }

    }

    @Test(expected = NullPointerException.class)
    public void getFilteredResultsTestTextFilterNullBookList() {

        List<BookDTO> bookList = bookServiceMockGenerator.getBooksForFilter();
        BookDashboardFilter bookDashboardFilter = null;
        List<BookDTO> filteredBookList = bookService.getFilteredResults(bookList, bookDashboardFilter);

        for (BookDTO bookDto:filteredBookList) {
            //Price range assertion
            Assert.assertTrue(bookDto.getBookName().toLowerCase().contains(bookDashboardFilter.getBookOrAuthorName().toLowerCase()) ||
                    bookDto.getWriter().getDeWriter().toLowerCase().contains(bookDashboardFilter.getBookOrAuthorName().toLowerCase()));

        }

    }

}
