package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static edu.epam.project.controller.command.RequestParameter.USER_NAME_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

public class UnblockUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(BlockUserCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String currentPage = request.getHeader(REFERER);
        String userName = request.getParameter(USER_NAME_PARAMETER);
        try {
            if (userService.updateUserStatusByUserName(false, userName)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
