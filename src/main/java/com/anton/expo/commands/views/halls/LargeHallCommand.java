package com.anton.expo.commands.views.halls;

import com.anton.expo.commands.Command;
import com.anton.expo.enums.HallType;
import com.anton.expo.repository.entity.Hall;
import com.anton.expo.services.ExpositionService;
import com.anton.expo.services.HallService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LargeHallCommand implements Command {
    private final ExpositionService expositionService;
    private final HallService hallService;

    public LargeHallCommand(ExpositionService expositionService, HallService hallService) {
        this.expositionService = expositionService;
        this.hallService = hallService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Hall hall = hallService.getHallByType(HallType.LARGE);
        req.setAttribute("hall", hall);
        req.setAttribute("expositions", expositionService.getActiveExpositionsForHall(hall.getId()));
        req.getSession().setAttribute("origin", req.getRequestURI().substring(1));

        return new String[] {"hall", "forward"};
    }
}
