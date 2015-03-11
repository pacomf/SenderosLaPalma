var mongoose = require('mongoose'),
    Schema = mongoose.Schema;


var commentSchema = new Schema({
    id_sendero: String,
    id_owner: String,
    geo: {type: [Number], index: '2d'},
    likes: {type: Number, default: 0},
    description: String
});


//Export the schema
module.exports = mongoose.model('Comment', commentSchema)