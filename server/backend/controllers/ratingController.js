var mongoose = require('mongoose'),
    ratingModel = require('../models/ratingModel');

var resOk = {"res" : "ok"};
var resErr = {"res" : "err"};

exports.addRating = function (req, res){
	ratingModel.findOne({id_sendero: req.params.idsendero, id_owner: req.params.idowner}).exec(function (err, rating) {
		if ((rating === null) || (rating === undefined)){
			var newRating= new ratingModel({
				id_sendero: req.params.idsendero,
		    	id_owner: req.params.idowner,
		    	date: new Date(),
		    	rating: req.body.rating
			});
			newRating.save();
			console.log("Added Rating "+newRating._id);
			resOk.date = newRating.date;
			res.send(resOk);
		} else {
			rating.date = new Date();
			rating.rating = req.body.rating;
			rating.save();
			resOk.date = rating.date;
			res.send(resOk);
		}
	});
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