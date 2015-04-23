// app.js
// Written by Ryan Brooks

// Setup
var express = require('express');
var app = express();
var config = require('./config/secrets').config();
var path = require('path');

var bodyParser = require('body-parser');
var mongoose = require('mongoose');
var passport = require('passport');
var cookieParser = require('cookie-parser');
var expressSession = require('express-session');
var Session = require('connect-mongo')(expressSession);

var User = require('./models/userModel');
var userController = require('./controllers/UserController');

var postController = require('./controllers/PostController');

// Configs
app.set('port', config.port);
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(passport.initialize());
app.use(cookieParser());
app.use(passport.initialize());
app.use(passport.session({ // http://stackoverflow.com/questions/11277779/passportjs-deserializeuser-never-called
	secret: 'grumpy cat',
	cookie: {
		secure: true
	}
}));
app.use(expressSession({
	store: new Session({
		url: config.mongoDB,
		maxAge: 300000
	}),
	// domain: '.local-inventive.io',
	secret: 'superSecret',
	resave: true,
    saveUninitialized: true
}));

// Routes
var router = express.Router();

// Auth
router.post('/api/user/signup', userController.signup);
router.post('/api/user/login', userController.login);
// Following
router.post('/api/user/follow', userController.follow);
router.post('/api/user/un-follow', userController.unFollow);
router.post('/api/user/add-follower', userController.addFollower);
router.post('/api/user/remove-follower', userController.removeFollower);
router.get('/api/user/get-followers', userController.getFollowers);
router.get('/api/user/get-following', userController.getFollowing);
router.get('/api/user/get-all-users', userController.getAllUsers);
// General User
router.get('/api/user/get-user', userController.get);
// Posts
router.post('/api/post/create', postController.create);
router.get('/api/posts/all', postController.getAllPosts);
router.get('/api/posts/locations', postController.getAllPostLocations);

// DB
var db = mongoose.connect(config.mongoDB, function(err) {
	if (err) { console.error('Could not establish connection with MongoDB' + err); }
});

app.use(express.static(path.join(__dirname, '../client/dist')));
app.use(express.static(path.join(__dirname, '../client/dist/assets')));
app.use(express.static(path.join(__dirname, '../client/user'))); // Exposes upload folder - refactor nameing

router.all('*', function(req, res, next) {
	res.header('Access-Control-Allow-Origin', '*');
	res.header('Access-Control-Allow-Headers', 'X-Requested-With, Authorization, Content-Type, Username, Password');
	next();
});

router.get('/*', function(req, res) {
	res.sendFile(path.join(__dirname, '../client/dist/index.html'));
});

// Register Routes -- Prefix with /api
app.use('/', router);

// Server
app.listen(app.get('port'), function() {
	console.log('Express listening to port', app.get('port'));
});

