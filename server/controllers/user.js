/*jslint node: true */
'use strict';

/**
 *
 * Each functional context is contained with a single controlled. In order to
 * prevent spaghetti controllers, the business logic is encapsulated into service
 * modules.
 *
 * The example below illustrates the a user controller, which exposes the update
 * functionality via the respective RESTful endpoint in `/server/app.js`
 *
 */

var userLib = require('../user');

var userController = {};

userController.update = update;

function update(req, res) {
    console.log('Updating user...');
    userLib.update(function(resp) {
        return res.json(resp);
    });
}

module.exports = userController;