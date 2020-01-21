package com.anton.expo.commands.login;

import com.anton.expo.commands.Command;
import com.anton.expo.enums.AccountStatus;
import com.anton.expo.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class RegisterCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getMethod().equals("POST")) {
            long id = ServiceFactory.getUserService().registerUser(req.getParameter("firstName"),
                    req.getParameter("lastName"), req.getParameter("phone"), req.getParameter("email"),
                    LocalDateTime.now(), Long.parseLong(req.getParameter("cardNumber")), req.getParameter("userName"),
                    req.getParameter("password"), AccountStatus.CUSTOMER);
            if (id > 0) {
                req.getSession().setAttribute("user", ServiceFactory.getUserService().getUserById(id));
                return new String[] {"", "redirect"};
            }
        }
        return new String[] {"register", "forward"};
    }
}
