package com.anton.expo.commands.views;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;
import com.anton.expo.repository.entity.Exposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String query = req.getParameter("query");
        if (query != null) {
            List<Exposition> expositions = ServiceFactory.getExpositionService().searchExpositionsByTitle(query);
            if (!expositions.isEmpty()) {
                req.setAttribute("expositions", expositions);
            } else {
                req.setAttribute("emptySearch", "not found");
            }
        }

        return new String[] {"search", "forward"};
    }
}
