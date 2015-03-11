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
	        ---
	*/
	app.get('/api/senderos', senderoController.getSenderos);
	
	/*
	  Query String Example: /api/senderos/1234?comments=10&ratings=20&photos=5
	  Query Parameters:
	        |  key   | values | meaning 
	        ----------------------------
	        | comments | INT |  Amount of comments
	        ---
	        | ratings | INT | Amount of ratings
	        ---
	        | photos | INT | Amount of photos
	        ---
	*/
	app.get('/api/senderos/:idsendero', senderoController.getSendero);

	app.get('/api/senderos/:idsendero/comments', commentController.getComments);
	app.get('/api/senderos/:idsendero/rating', ratingController.getRatings);
	app.get('/api/senderos/:idsendero/photos', photoController.getPhotos);

	// POST
	// ----

	app.post('/api/senderos/:idsendero/rating/:idowner', ratingController.addRating);
	app.post('/api/senderos/:idsendero/comment/:idowner', commentController.addComment);
	app.post('/api/senderos/:idsendero/photo/:idowner', photoController.addPhoto);


}