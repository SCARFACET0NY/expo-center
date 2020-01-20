package com.anton.expo.commands.views.login;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getMethod().equals("POST")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (ServiceFactory.getUserService().verifyUser(username, password)) {
                req.getSession().setAttribute("user", ServiceFactory.getUserService().getUserByUsername(username));

                return new String[] {"", "redirect"};
            }
        }
        return new String[] {"login", "forward"};
    }
}
