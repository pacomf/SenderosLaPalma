var mongoose = require('mongoose'),
	senderoModel = require('../models/senderoModel'),
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
		    	rating: parseFloat(req.body.rating)
			});
			newRating.save();
			console.log("Added Rating "+newRating._id);
			senderoModel.findById(req.params.idsendero).exec(function (err, sendero) {
				if ((sendero !== null) && (sendero !== undefined)){
					if (sendero.rating == 0){
						sendero.nratings = 1;
						sendero.rating = req.body.rating;
					} else {
						var arating = sendero.rating*sendero.nratings;
						sendero.nratings = sendero.nratings+1;
						sendero.rating = ((arating+parseFloat(req.body.rating))/sendero.nratings);					
					}
					sendero.save();
					console.log("Update Rating Sendero "+sendero._id);
					resOk.rating = sendero.rating;
					res.send(resOk);
				} else {
					res.send(resErr);
				}
			});
		} else {
			rating.date = new Date();
			var prating = rating.rating;
			rating.rating = req.body.rating;
			rating.save();
			console.log("Update Rating "+rating._id);
			senderoModel.findById(req.params.idsendero).exec(function (err, sendero) {
				if ((sendero !== null) && (sendero !== undefined)){
					sendero.rating = ((sendero.rating*sendero.nratings)-prating)/(sendero.nratings-1);
					sendero.nratings = sendero.nratings-1;
					var arating = sendero.rating*sendero.nratings;
					sendero.nratings = sendero.nratings+1;
					sendero.rating = ((arating+parseFloat(req.body.rating))/sendero.nratings);
					sendero.save();
					console.log("Update Rating Sendero "+sendero._id);
					resOk.rating = sendero.rating;
					res.send(resOk);
				} else {
					res.send(resErr);
				}
			});
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