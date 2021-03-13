package com.crejo.fun.service;

import com.crejo.fun.exception.UserAlreadyExistsException;
import com.crejo.fun.model.Level;
import com.crejo.fun.model.Review;
import com.crejo.fun.model.User;
import com.crejo.fun.repository.UserRepository;

import java.util.List;

public class UserService {

    private static final UserRepository userRepository = new UserRepository();

    /**
     * Method to add a user if the user does not exist already
     *
     * @param firstName
     * @return
     */
    public static User addUser(String firstName){
        if(!checkIfUserAlreadyExists(firstName)){
            User user = new User(firstName);
            userRepository.save(user);
            System.out.println("Added User with name: " + firstName);
            return user;
        } else {
            String exceptionMessage = "User with name: " + firstName + " already exists";
            UserAlreadyExistsException e = new UserAlreadyExistsException(exceptionMessage);
            System.out.println("Exception Occurred - " + e.getClass().getName() + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Checks if the user already exists
     *
     * @param firstName
     * @return
     */
    private static boolean checkIfUserAlreadyExists(String firstName){
        List<User> users = userRepository.getAllUsers();
        return users.stream().anyMatch(user -> user.getFirstName().equals(firstName));
    }

    /**
     * Upgrades the user level if applicable by counting the number of reviews posted
     * against the Level review count
     *
     * @param user
     */
    public static void upgradeUserLevelIfApplicable(User user){
        List<Review> reviews = ReviewService.getReviewsAddedByUser(user);
        Level applicableUserLevel = null;
        int reviewsAddedCount = reviews.size();
        for(Level level : Level.values()){
            int reviewCountLevel = level.getNumberOfReviews();
            if(reviewsAddedCount >= reviewCountLevel){
                applicableUserLevel = level;
            }
        }
        if(applicableUserLevel != user.getLevel()){
            System.out.println("User: " + user.getFirstName() + " is changed to: " + applicableUserLevel +
                    " from: " + user.getLevel());
            user.setLevel(applicableUserLevel);
        }
    }
}
