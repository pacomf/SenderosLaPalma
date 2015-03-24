var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

var ratingSchema = new Schema({
    id_sendero: String,
    id_owner: String,
    date: Date,
    rating: Number
});


//Export the schema
module.exports = mongoose.model('Rating', ratingSchema)