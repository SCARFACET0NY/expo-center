package com.anton.expo.commands.views;

import com.anton.expo.commands.Command;
import com.anton.expo.repository.entity.User;
import com.anton.expo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PurchaseHistoryCommand implements Command {
    private final UserService userService;

    public PurchaseHistoryCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        int pageNumber = req.getParameter("page") == null ? 0 : Integer.parseInt(req.getParameter("page"));

        if (user != null) {
            req.setAttribute("purchases", userService.getPurchasesPageByUserId(user.getId(), pageNumber));
            req.setAttribute("numOfPages", userService.getNumberOfPagesByUserId(user.getId()));
            req.setAttribute("page", pageNumber);
        }

        return new String[] {"purchase-history", "forward"};
    }
}
