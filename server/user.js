/*jslint node: true */
'use strict';

/**
 *
 * Services work to move all business logic from controllers and encapsulate reusable
 * application wide functionlity.
 *
 */

(function(User) {
    require('./user/account')(User);

    User.getUser = getUser;

    function getUser(id, callback) {
        console.log('Utility function: returning to return user');
    }
}(exports));