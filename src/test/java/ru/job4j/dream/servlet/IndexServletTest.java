package ru.job4j.dream.servlet;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class IndexServletTest {

    @Test
    public void whenDoGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("index.jsp")).thenReturn(dispatcher);

        new IndexServlet().doGet(req, resp);

        verify(dispatcher).forward(req, resp);
    }
}