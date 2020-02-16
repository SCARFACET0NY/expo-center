package com.anton.expo.commands.exposition;

import com.anton.expo.commands.Command;
import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.services.ExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class UpdateExpositionCommand implements Command {
    private final ExpositionService expositionService;

    public UpdateExpositionCommand(ExpositionService expositionService) {
        this.expositionService = expositionService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("exposition_id") != null) {
            Exposition exposition = expositionService.getExpositionById(Long.parseLong(
                    req.getParameter("exposition_id")));
            if (exposition != null) {
                exposition.setTitle(req.getParameter("title"));
                exposition.setDescription(req.getParameter("description"));
                exposition.setPrice(Double.parseDouble(req.getParameter("price")));
                exposition.setImagePath(req.getParameter("image_path"));
                exposition.setStartDate(LocalDate.parse(req.getParameter("start_date")));
                exposition.setEndDate(LocalDate.parse(req.getParameter("end_date")));
                exposition.setHallId(Long.parseLong(req.getParameter("hall_id")));

                expositionService.updateExposition(exposition);
            }
        }

        return new String[] {"admin", "redirect"};
    }
}
