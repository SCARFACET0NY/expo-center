package com.anton.expo.commands.exposition;

import com.anton.expo.commands.Command;
import com.anton.expo.services.ExpositionService;
import com.anton.expo.services.HallService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseExpositionCommand implements Command {
    private final ExpositionService expositionService;
    private final HallService hallService;

    public ChooseExpositionCommand(ExpositionService expositionService, HallService hallService) {
        this.expositionService = expositionService;
        this.hallService = hallService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("exposition", expositionService.getExpositionById(
                Long.parseLong(req.getParameter("exposition_id"))
        ));
        req.setAttribute("expositions", expositionService.getAllActiveExpositions());
        req.setAttribute("halls", hallService.getAllHalls());

        return new String[] {"update-exposition", "forward"};
    }
}
