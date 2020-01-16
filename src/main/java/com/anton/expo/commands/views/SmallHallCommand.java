package com.anton.expo.commands.views;

import com.anton.expo.commands.Command;
import com.anton.expo.enums.HallType;
import com.anton.expo.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SmallHallCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("hall", ServiceFactory.getHallService().getHallByType(HallType.SMALL));

        return new String[] {"hall", "forward"};
    }
}
