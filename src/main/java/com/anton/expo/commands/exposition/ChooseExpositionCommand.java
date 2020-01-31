package com.anton.expo.commands.exposition;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseExpositionCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("exposition", ServiceFactory.getExpositionService().getExpositionById(
                Long.parseLong(req.getParameter("exposition_id"))
        ));
        req.setAttribute("expositions", ServiceFactory.getExpositionService().getAllActiveExpositions());
        req.setAttribute("halls", ServiceFactory.getHallService().getAllHalls());

        return new String[] {"update-exposition", "forward"};
    }
}
