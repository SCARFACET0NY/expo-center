package com.anton.expo.commands.views;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.DaoFactory;
import com.anton.expo.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("halls", ServiceFactory.getHallService().getAllHalls());

        return new String[] {"index", "forward"};
    }
}
