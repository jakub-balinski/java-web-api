package com.balinski.api_project.servlet;

import com.balinski.api_project.database.DaoManager;
import com.balinski.api_project.database.dao.DaoException;
import com.balinski.api_project.database.model.Actor;
import com.balinski.api_project.util.JsonHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class ActorServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        try {
            List<Actor> actors = new DaoManager().getActorDao().getAll();
            String response = JsonHelper.mergeFromList(actors);
            writer.print(response);
        } catch (DaoException e) {
            writer.print("{\"error\":true,\"data\":[]}");
        } catch (NullPointerException e) {
            writer.print("{\"error\":false,\"data\":[]}");
        }
    }
}