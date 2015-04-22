/*jslint node: true */
'use strict';

// Dependencies
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var UserSchema = new Schema({
	name: String,
	username: String,
	password: String,
	email: String,
	followers: [],
	following: [],
	posts: [],
	resetPasswordToken: String,
	resetPasswordExpires: Date
});

module.exports = mongoose.model('UserModel', UserSchema);