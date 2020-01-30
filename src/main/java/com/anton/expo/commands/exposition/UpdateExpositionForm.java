package com.anton.expo.commands.exposition;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateExpositionForm implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("expositions", ServiceFactory.getExpositionService().getAllActiveExpositions());
        req.setAttribute("halls", ServiceFactory.getHallService().getAllHalls());

        return new String[] {"update-exposition", "forward"};
    }
}
