module.exports = function (app) {

	var senderoController = require('../backend/controllers/senderoController');
	var ratingController = require('../backend/controllers/ratingController');

	// GET
	// ---

	//  maxDistance: Max Distance in meters (radius)
	//  amount: Numbers of results
	app.get('/ws/senderos/findByGeo/:latitude/:longitude/:maxDistance/:amount', senderoController.getGeoSenderos);
	
	app.get('/ws/senderos/all', senderoController.getAllSenderos);


	// POST
	// ----

	app.post('/ws/rating/add/:idsendero/:idowner/:rating', ratingController.addRating);


}