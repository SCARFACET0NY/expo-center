package com.anton.expo.factory;

import com.anton.expo.commands.Command;
import com.anton.expo.commands.login.LogoutCommand;
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
        commandMap.put("", new HomeCommand());
        commandMap.put("largeHall", new LargeHallCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("mediumHall", new MediumHallCommand());
        commandMap.put("register", new RegisterCommand());
        commandMap.put("smallHall", new SmallHallCommand());
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
