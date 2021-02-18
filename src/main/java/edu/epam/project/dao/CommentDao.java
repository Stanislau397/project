package edu.epam.project.dao;

import edu.epam.project.entity.Comment;
import edu.epam.project.exception.DaoException;

import java.util.List;

public interface CommentDao {

    boolean leaveCommentByUserId(long movieId, String userName, String comment, String postDate) throws DaoException;

    List<Comment> findCommentsByMovieId(long movieId) throws DaoException;

    boolean removeComment(long movieId, String userName, String comment) throws DaoException;

    int countUserCommentsByUserName(String userName) throws DaoException;
}