package ru.job4j.dream.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class RegServletTest {

    @Test
    public void whenDoGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher(req.getContextPath() + "/reg.jsp")).thenReturn(dispatcher);

        new RegServlet().doGet(req, resp);

        verify(dispatcher).forward(req, resp);
    }

    @Test
    public void whenRegistrationPassed() throws ServletException, IOException {
        Store store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("email")).thenReturn("any@email");
        when(req.getParameter("password")).thenReturn("password");

        new RegServlet().doPost(req, resp);

        verify(resp).sendRedirect(req.getContextPath() + "/login.jsp");
        assertThat(store.findByEmail("any@email").getName(), is("name"));
    }

    @Test
    public void whenRegistrationFailed() throws ServletException, IOException {
        Store store = MemStore.instOf();
        store.save(new User(0, "name", "email", "password"));

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("email")).thenReturn("email");
        when(req.getParameter("password")).thenReturn("password");
        when(req.getRequestDispatcher("reg.jsp")).thenReturn(dispatcher);

        new RegServlet().doPost(req, resp);

        verify(dispatcher).forward(req, resp);
    }
}