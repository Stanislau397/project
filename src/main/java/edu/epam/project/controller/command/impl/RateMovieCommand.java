package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.controller.command.SessionAttribute;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.RatingService;
import edu.epam.project.sevice.impl.RatingServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RateMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RateMovieCommand.class);
    private RatingService ratingService = new RatingServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = request.getHeader(RequestParameter.REFERER);
        int rating = Integer.parseInt(request.getParameter(RequestParameter.SCORE));
        long movieId = Long.parseLong(request.getParameter(RequestParameter.MOVIE_ID));
        String userName = (String) session.getAttribute(SessionAttribute.USER_NAME);
        try {
            if (ratingService.rateMovie(movieId, userName, rating)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(page);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
