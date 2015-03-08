module.exports = function (app) {

	var senderoController = require('../backend/controllers/senderoController');
	var ratingController = require('../backend/controllers/ratingController');
	var commentController = require('../backend/controllers/commentController');
	var photoController = require('../backend/controllers/photoController');

	// GET
	// ---

	/*
	  Query String Example: /api/senderos?search=byGeo&latitude=4.815&longitude=16.2342&amount=10&difficulty="Alta"
	  Query Parameters:
	        |  key   | values | meaning 
	        ----------------------------
	        | search | byGeo |  Type of Filter
	        ---
	        | latitude | FLOAT | Latitude Geolocation
	        ---
	        | longitude | FLOAT | Longitude Geolocation
	        ---
	        | amount | INT | Number of results to return
	        ---
	        | maxDistance | FLOAT | Geo Radius Distance in meters to find by Closeness
	        ---
	        | maxLength | FLOAT | Maximum Length Sendero
	        ---
	        | minLength | FLOAT | Minimum Length Sendero
	        ---
	        | difficulty | Extrema, Alta, Media, Baja | Difficulty of Sendero
	        ---
	        | noCoordinates | BOOLEAN | Return or not the 'coordinates'
	        ---
	        | noWaterPoints | BOOLEAN | Return or not the 'water_points'
	*/
	app.get('/api/senderos', senderoController.getSenderos);
	
	app.get('/api/senderos/:idsendero', senderoController.getSendero);

	app.get('/api/senderos/:idsendero/comments', commentController.getComments);
	app.get('/api/senderos/:idsendero/rating', ratingController.getRatings);
	app.get('/api/senderos/:idsendero/photos', photoController.getPhotos);

	// POST
	// ----

	app.post('/api/senderos/:idsendero/rating/:idowner,:rating', ratingController.addRating);
	app.post('/api/senderos/:idsendero/comment/:idowner,:latitude,:longitude', commentController.addComment);
	app.post('/api/senderos/:idsendero/photo/:idowner,:latitude,:longitude,:url', photoController.addPhoto);


}