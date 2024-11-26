package com.example.project.utilites;

/**
 * @author Devlin Hamill
 * CS 460
 */

public class Constants {
    /**
     * creates a global variable that holds the user table name on the firebase
     */
    public static final String KEY_COLLECTION_USERS = "User";
    /**
     * creates a global variable that contains the firstname data within the user table
     */
    public static final String KEY_FIRST_NAME = "firstname";
    /**
     * creates a global variable that contains the lastname data within the user table
     */
    public static final String KEY_LAST_NAME = "lastname";
    /**
     * creates a global variable that contains the email data within the user table
     */
    public static final String KEY_EMAIL = "email";
    /**
     * creates a global variable that contains the password data within the user table
     */
    public static final String KEY_PASSWORD = "password";
    /**
     * creates a global variable for the unique user id for each person that signed up within the firebase
     */
    public static final String KEY_USER_ID = "userid";
    /**
     * a constant global variable track that is used to track if users will be signed in or not on the firebase
     */
    public static final String KEY_IS_SIGNED_IN = "isSignedIn";
    /**
     * a constant global variable track that will be used to declare chatAppPrefrences
     */
    public static final String KEY_PREFERENCE_NAME = "chatAppPreference";
    /**
     * a global variable used to declare image data within the firebase
     */
    public static final String KEY_IMAGE = "image";
    /**
     * keeps track of the users permission level
     */
    public static final String KEY_PERMISSION = "permission";

    public static final String KEY_USER = "user";
    /**
     * Holds the chats in the Firebase
     */
    public static final String KEY_COLLECTION_CHAT = "chat";
    /**
     * keeps track of the sender in a chat
     */
    public static final String KEY_SENDER_ID = "senderId";
    /**
     * keeps track of the receiver in a chat
     */
    public static final String KEY_RECIEVER_ID = "recieverId";
    /**
     * keeps track of the messages in a chat
     */
    public static final String KEY_MESSAGE = "message";
    /**
     * keeps track of the date from a message
     */
    public static final String KEY_TIMESTAMP = "timestamp";
}
