var mongoose = require('mongoose'),
    Schema = mongoose.Schema;


var photoSchema = new Schema({
    url: String,
    id_sendero: String,
    id_owner: String,
    date: Date,
    geo: {type: [Number], index: '2d'},
    likes: {type: Number, default: 0}
});


//Export the schema
module.exports = mongoose.model('Photo', photoSchema)