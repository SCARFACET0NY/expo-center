package com.anton.expo.commands.views;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;
import com.anton.expo.repository.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PurchaseHistoryCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        int pageNumber = req.getParameter("page") == null ? 0 : Integer.parseInt(req.getParameter("page"));

        if (user != null) {
            req.setAttribute("purchases",
                    ServiceFactory.getUserService().getPurchasesPageByUserId(user.getId(), pageNumber));
            req.setAttribute("numOfPages", ServiceFactory.getUserService().getNumberOfPagesByUserId(user.getId()));
            req.setAttribute("page", pageNumber);
        }

        return new String[] {"purchase-history", "forward"};
    }
}
