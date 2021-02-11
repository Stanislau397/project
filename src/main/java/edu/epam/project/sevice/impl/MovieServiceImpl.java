package edu.epam.project.sevice.impl;

import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.impl.MovieDaoImpl;
import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {

    public static final Logger logger = LogManager.getLogger(MovieServiceImpl.class);
    private MovieDao movieDao = new MovieDaoImpl();

    @Override
    public boolean add(Movie movie) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = movieDao.add(movie);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean deleteMovieByTitle(String title) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = movieDao.deleteMovieByTitle(title);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public Optional<Movie> findMovieByTitle(String title) throws ServiceException {
        Optional<Movie> isFound;
        try {
            isFound = movieDao.findMovieByTitle(title);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
    }

    @Override
    public List<Movie> findAllMovies() throws ServiceException {
        List<Movie> allMovies;
        try {
            allMovies = movieDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return allMovies;
    }

    @Override
    public Optional<Movie> findMovieById(long movieId) throws ServiceException {
        Optional<Movie> isFound;
        try {
            isFound = movieDao.findMovieById(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
    }

    @Override
    public boolean leaveComment(String userName, long movieId, String comment) throws ServiceException {
        boolean isLeft;
        try {
            isLeft = movieDao.leaveCommentByUserId(movieId, userName, comment);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isLeft;
    }

    @Override
    public List<Comment> findCommentsByMovieId(long movieId) throws ServiceException {
        List<Comment> comments;
        try {
            comments = movieDao.findCommentsByMovieId(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return comments;
    }

    @Override
    public boolean removeComment(long movieId, long userId, String comment) throws ServiceException {
        boolean isRemoved;
        try {
            isRemoved = movieDao.removeComment(movieId, userId, comment);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRemoved;
    }
}
