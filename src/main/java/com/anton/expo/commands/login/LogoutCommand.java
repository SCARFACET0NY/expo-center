package com.anton.expo.commands.login;

import com.anton.expo.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        return new String[] {"", "redirect"};
    }
}
