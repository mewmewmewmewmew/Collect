/**
 *
 * IMPORTANT DO NOT COMMIT || IMPORTANT DO NOT COMMIT
 *
 * Do not commit this file to git. This file is only here as a example of
 * where to store your secrets i.e. API keys and other sensitive information.
 * To prevent this file from being commited, add this file to your .gitignore
 *
 */

module.exports = {
	db: process.env.MONGODB || 'mongodb://localhost:27017/mean',
	port: process.env.port || 9001
};