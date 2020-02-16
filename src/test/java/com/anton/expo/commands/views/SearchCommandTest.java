package com.anton.expo.commands.views;

import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.services.ExpositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchCommandTest {
    private static final long ID1 = 1L;
    private static final long ID2 = 2L;
    private static final String QUERY = "expo";
    private static final String NOT_FOUND = "not found";
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ExpositionService expositionService;
    SearchCommand searchCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        searchCommand = new SearchCommand(expositionService);
    }

    @Test
    void searchPageTest() throws Exception {
        List<Exposition> expositions = new ArrayList<>();
        Exposition exposition1 = Exposition.builder().id(ID1).build();
        Exposition exposition2 = Exposition.builder().id(ID2).build();

        expositions.add(exposition1);
        expositions.add(exposition2);

        when(request.getParameter("query")).thenReturn(QUERY);
        when(expositionService.searchExpositionsByTitle(anyString())).thenReturn(expositions);

        String[] searchPage = searchCommand.process(request, response);

        assertEquals("search",  searchPage[0]);
        assertEquals("forward",  searchPage[1]);

        verify(request).getParameter("query");
        verify(request).setAttribute("expositions", expositions);
        verify(request, never()).setAttribute("emptySearch", NOT_FOUND);
    }

    @Test
    void emptySearchPageTest() throws Exception {
        List<Exposition> emptyList = new ArrayList<>();

        when(request.getParameter("query")).thenReturn(QUERY);
        when(expositionService.searchExpositionsByTitle(anyString())).thenReturn(emptyList);

        String[] searchPage = searchCommand.process(request, response);

        assertEquals("search",  searchPage[0]);
        assertEquals("forward",  searchPage[1]);

        verify(request).getParameter("query");
        verify(request).setAttribute("emptySearch", NOT_FOUND);
        verify(request, never()).setAttribute("expositions", emptyList);
    }

}