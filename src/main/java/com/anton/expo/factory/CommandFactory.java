package com.anton.expo.factory;

import com.anton.expo.commands.Command;
import com.anton.expo.commands.exposition.*;
import com.anton.expo.commands.login.LogoutCommand;
import com.anton.expo.commands.mail.SendMailCommand;
import com.anton.expo.commands.ticket.*;
import com.anton.expo.commands.views.*;
import com.anton.expo.commands.views.halls.LargeHallCommand;
import com.anton.expo.commands.views.halls.MediumHallCommand;
import com.anton.expo.commands.views.halls.SmallHallCommand;
import com.anton.expo.commands.login.LoginCommand;
import com.anton.expo.commands.login.RegisterCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for Commands
 */
public class CommandFactory {
    private static final Map<String, Command> commandMap;

    static {
        commandMap = new HashMap<>();
        commandMap.put("",
                new HomeCommand(ServiceFactory.getHallService()));
        commandMap.put("addExposition",
                new AddExpositionCommand(ServiceFactory.getExpositionService()));
        commandMap.put("admin",
                new AdminCommand());
        commandMap.put("admin/add",
                new AddExpositionFormCommand(ServiceFactory.getHallService()));
        commandMap.put("admin/update",
                new UpdateExpositionFormCommand(ServiceFactory.getExpositionService(), ServiceFactory.getHallService()));
        commandMap.put("addTicket",
                new AddTicketCommand(ServiceFactory.getPaymentService()));
        commandMap.put("cart",
                new CartCommand());
        commandMap.put("chooseExposition",
                new ChooseExpositionCommand(ServiceFactory.getExpositionService(), ServiceFactory.getHallService()));
        commandMap.put("largeHall",
                new LargeHallCommand(ServiceFactory.getExpositionService(), ServiceFactory.getHallService()));
        commandMap.put("login",
                new LoginCommand(ServiceFactory.getUserService()));
        commandMap.put("logout",
                new LogoutCommand());
        commandMap.put("mediumHall",
                new MediumHallCommand(ServiceFactory.getExpositionService(), ServiceFactory.getHallService()));
        commandMap.put("pay",
                new PayTicketsCommand(ServiceFactory.getPaymentService()));
        commandMap.put("register",
                new RegisterCommand(ServiceFactory.getUserService()));
        commandMap.put("removeTicket",
                new RemoveTicketCommand(ServiceFactory.getPaymentService()));
        commandMap.put("purchaseHistory",
                new PurchaseHistoryCommand(ServiceFactory.getUserService()));
        commandMap.put("search",
                new SearchCommand(ServiceFactory.getExpositionService()));
        commandMap.put("sendMail",
                new SendMailCommand(ServiceFactory.getEmailService()));
        commandMap.put("setDate",
                new SetTicketDateCommand());
        commandMap.put("setQuantity",
                new TicketQuantityCommand(ServiceFactory.getPaymentService()));
        commandMap.put("smallHall",
                new SmallHallCommand(ServiceFactory.getExpositionService(), ServiceFactory.getHallService()));
        commandMap.put("updateExposition",
                new UpdateExpositionCommand(ServiceFactory.getExpositionService()));
    }

    /**
     * Returns {@code Command} for specified action
     * @param action - String that specifies path for {@code Command}
     * @return {@code Command}
     */
    public static Command getCommand(String action) {
        return commandMap.get(action);
    }
}
