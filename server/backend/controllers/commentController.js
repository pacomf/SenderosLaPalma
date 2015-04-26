var mongoose = require('mongoose'),
	utils = require('./utilities/conversionDataModel'),
    commentModel = require('../models/commentModel');

var resOk = {"res" : "ok"};
var resErr = {"res" : "err"};

exports.addComment = function (req, res){
	var newComment= new commentModel({
		id_sendero: req.params.idsendero,
    	id_owner: req.params.idowner,
    	name_owner: req.body.nameowner,
    	date: new Date(),
    	geo: utils.toGeo(req.body.latitude, req.body.longitude), 
    	description: req.body.description
	});
	newComment.save();
	console.log("Added Comment "+newComment._id);
	resOk.date = newComment.date;
	res.send(resOk);
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