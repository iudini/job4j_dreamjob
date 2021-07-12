package ru.job4j.dream.servlet;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.Store;
import ru.job4j.dream.store.MemStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {

    @Test
    public void whenCreatePost() throws IOException, ServletException {
        Store store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("n");
        when(req.getParameter("description")).thenReturn("d");

        new PostServlet().doPost(req, resp);

        Post result = store.findAllPosts().iterator().next();
        Assert.assertThat(result.getName(), Is.is("n"));
        Assert.assertThat(result.getDescription(), Is.is("d"));
    }

    @Test
    public void whenDoGet() throws ServletException, IOException {
        Store store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("posts.jsp")).thenReturn(dispatcher);

        new PostServlet().doGet(req, resp);

        verify(dispatcher).forward(req, resp);
    }
}