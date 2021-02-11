package edu.epam.project.sevice;

import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    boolean add(Movie movie) throws ServiceException;

    boolean deleteMovieByTitle(String title) throws ServiceException;

    Optional<Movie> findMovieByTitle(String title) throws ServiceException;

    List<Movie> findAllMovies() throws ServiceException;

    Optional<Movie> findMovieById(long movieId) throws ServiceException;

    boolean leaveComment(String userName, long movieId, String comment) throws ServiceException;

    List<Comment> findCommentsByMovieId(long movieId) throws ServiceException;

    boolean removeComment(long movieId, long userId, String comment) throws ServiceException;
}
