package com.anton.expo.commands.exposition;

import com.anton.expo.commands.Command;
import com.anton.expo.services.HallService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddExpositionFormCommand implements Command {
    private final HallService hallService;

    public AddExpositionFormCommand(HallService hallService) {
        this.hallService = hallService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("halls", hallService.getAllHalls());

        return new String[] {"add-exposition", "forward"};
    }
}
