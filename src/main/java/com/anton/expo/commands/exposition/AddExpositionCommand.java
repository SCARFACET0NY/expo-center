package com.anton.expo.commands.exposition;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;
import com.anton.expo.repository.entity.Exposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class AddExpositionCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Exposition exposition = ServiceFactory.getExpositionService().createExposition(req.getParameter("title"),
                req.getParameter("description"), Double.parseDouble(req.getParameter("price")),
                req.getParameter("image_path"), LocalDate.parse(req.getParameter("start_date")),
                LocalDate.parse(req.getParameter("end_date")), Long.parseLong(req.getParameter("hall_id")));
        ServiceFactory.getExpositionService().saveExposition(exposition);

        return new String[] {"admin", "redirect"};
    }
}
