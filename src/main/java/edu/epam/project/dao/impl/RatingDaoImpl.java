package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.RatingDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class RatingDaoImpl implements RatingDao {

    private static final Logger logger = LogManager.getLogger(RatingDaoImpl.class);

    @Override
    public double countAverageMovieRating(long movieId) throws DaoException {
        double average = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.COUNT_AVERAGE_RATING)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                average = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return average;
    }

    @Override
    public boolean rateMovie(long movieId, long userId, double score) throws DaoException {
        boolean isRated;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQuery.RATE_MOVIE)) {
            statement.setLong(1, movieId);
            statement.setLong(2, userId);
            statement.setDouble(3, score);
            int result = statement.executeUpdate();
            isRated = (result == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRated;
    }
}
