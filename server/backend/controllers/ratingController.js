var mongoose = require('mongoose'),
    ratingModel = require('../models/ratingModel');

var resOk = {"res" : "ok"};
var resErr = {"res" : "err"};

exports.addRating = function (req, res){
	var newRating= new ratingModel({
		id_sendero: req.params.idsendero,
    	id_owner: req.params.idowner,
    	rating: req.body.rating
	});
	newRating.save();
	console.log("Added Rating "+newRating._id);
	res.send(resOk);
}

exports.getRatings = function (req, res) {
	ratingModel.find({id_sendero: req.params.idsendero}).exec(function (err, ratings) {
		if (!err){
			res.send(ratings);
		} else {
			res.send(resErr);
		}
	});
};