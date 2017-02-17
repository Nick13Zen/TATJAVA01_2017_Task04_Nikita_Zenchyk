package com.epam.task3.controller;

import com.epam.task3.controller.command.Command;
import com.epam.task3.controller.command.impl.AddNews;
import com.epam.task3.controller.exception.ControllerRuntimeExeption;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class Controller {
    private static final Logger logger = LogManager.getLogger(AddNews.class.getName());

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final NewsService newsService = serviceFactory.getNewsService();

    private final CommandProvider provider = new CommandProvider();

    private final char paramDelimiter = '-';

    public String executeTask(String request) {
        String commandName;
        Command executionCommand;

        commandName = request.substring(0, request.indexOf(paramDelimiter));
        executionCommand = provider.getCommand(commandName);

        String response;
        String requestToExecute = request.split(String.valueOf(paramDelimiter))[1];
        response = executionCommand.execute(requestToExecute);

        return response;
    }

    public void init() {
        try {
            newsService.init();
        } catch (ServiceException e) {
            logger.error(e);
            throw new ControllerRuntimeExeption(e);
        }
    }

    public void destroy() {
        try {
            newsService.destroy();
        } catch (ServiceException e) {
            logger.error(e);
            throw new ControllerRuntimeExeption(e);
        }
    }
}
