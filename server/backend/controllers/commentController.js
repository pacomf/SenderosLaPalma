var mongoose = require('mongoose'),
	utils = require('./utilities/conversionDataModel'),
    commentModel = require('../models/commentModel');

exports.addComment = function (req, res){
	var newComent= new commentModel({
		id_sendero: req.params.idsendero,
    	id_owner: req.params.idowner,
    	geo: utils.toGeo(req.params.latitude, req.params.longitude)
	});
	newComment.save();
	console.log("Added Comment "+newComment._id);
}

exports.getComments = function (req, res) {
	commentModel.find({id_sendero: req.params.idsendero}).exec(function (err, comments) {
		if (!err){
			res.send(comments);
		} else {
			res.send(resErr);
		}
	});
};