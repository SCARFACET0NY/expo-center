package com.anton.expo.commands.views.halls;

import com.anton.expo.commands.Command;
import com.anton.expo.enums.HallType;
import com.anton.expo.factory.ServiceFactory;
import com.anton.expo.repository.entity.Hall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LargeHallCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Hall hall = ServiceFactory.getHallService().getHallByType(HallType.LARGE);
        req.setAttribute("hall", hall);
        req.setAttribute("expositions", ServiceFactory.getExpositionService().getActiveExpositionsForHall(hall.getId()));
        req.getSession().setAttribute("origin", req.getRequestURI().substring(1));

        return new String[] {"hall", "forward"};
    }
}
