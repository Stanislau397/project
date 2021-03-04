package edu.epam.project.service;

import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.ServiceException;

import java.util.List;

public interface CommentService {

    boolean leaveComment(String userName, long movieId, String comment) throws ServiceException;

    List<Comment> findCommentsByMovieId(long movieId) throws ServiceException;

    List<Movie> findCommentsByUserName(String userName) throws ServiceException;

    boolean removeComment(long movieId, String userName, String comment) throws ServiceException;

    int countUserCommentsByUserName(String userName) throws ServiceException;
}