package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.COMMENT;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;

import static edu.epam.project.controller.command.SessionAttribute.USER_NAME;

public class RemoveCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RemoveCommentCommand.class);
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String currentPage = request.getHeader(REFERER);
        String comment = request.getParameter(COMMENT);
        String userName = request.getParameter(USER_NAME);
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        try {
            if (commentService.removeComment(movieId, userName, comment)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        }
        return router;
    }
}
