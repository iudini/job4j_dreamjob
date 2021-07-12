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
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class AuthServletTest {

    @Test
    public void whenLoginPassed() throws ServletException, IOException {
        Store store = MemStore.instOf();
        store.save(new User(0, "root", "root@local", "root"));

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        PowerMockito.when(req.getParameter("email")).thenReturn("root@local");
        PowerMockito.when(req.getParameter("password")).thenReturn("root");
        PowerMockito.when(req.getSession()).thenReturn(session);

        new AuthServlet().doPost(req, resp);
        verify(resp).sendRedirect(req.getContextPath() + "/posts.do");
    }

    @Test
    public void whenLoginFailed() throws ServletException, IOException {
        Store store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        PowerMockito.when(req.getParameter("email")).thenReturn("root@local");
        PowerMockito.when(req.getParameter("password")).thenReturn("root");
        PowerMockito.when(req.getSession()).thenReturn(session);
        PowerMockito.when(req.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);

        new AuthServlet().doPost(req, resp);
        verify(dispatcher).forward(req, resp);
    }
}