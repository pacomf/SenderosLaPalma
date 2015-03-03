var mongoose = require('mongoose'),
    ratingModel = require('../models/ratingModel');

exports.addRating = function (req, res){
	var newRating= new ratingModel({
		id_sendero: req.params.idsendero,
    	id_owner: req.params.idowner,
    	rating: req.params.rating
	});
	newRating.save();
	console.log("Added Rating "+newRating._id);
}