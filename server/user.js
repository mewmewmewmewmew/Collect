/*jslint node: true */
'use strict';

var bCrypt = require('bcrypt-nodejs');
var mongoose = require('mongoose');
var UserModel = mongoose.model('UserModel');

/**
 * User object that acts as a library for all
 * user related actions. Some functions are private
 * others are exposed to the controller
 */
(function(User) {

    require('./user/account')(User);

    User.getUser = getUser;
    User.getNewUser = getNewUser;
    User.getUserByQuery = getUserByQuery;
    User.isValidPassword = isValidPassword;
    User.createHash = createHash;

    function getUser(id, callback) {
        UserModel.findById(id, function(err, user) {
            return callback(err, user);
        });
    }

    function getUserByQuery(query, callback) {
    	UserModel.findOne(query, function(err, user) {
    		return callback(err, user);
    	});
    }

    function getNewUser(callback) {
        callback({
            status: true,
            user: new UserModel()
        });
    }

    function isValidPassword(user, password) {
        return bCrypt.compareSync(password, user.password);
    }

    function createHash(password) {
        return bCrypt.hashSync(password, bCrypt.genSaltSync(10), null);
    }

}(exports));