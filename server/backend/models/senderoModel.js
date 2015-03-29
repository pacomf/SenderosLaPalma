var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

var senderoSchema = new Schema({
    name: String,
    regular_name: String,
    version: String,
    length: Number, 
    type: String, 
    difficulty: String,
    geometry_type: String,
    rating: {type: Number, default: 0},
    nratings: {type: Number, default: 0},
    likes: {type: Number, default: 0},
    geoStart: {type: [Number], index: '2d'},
    geoEnd: {type: [Number], index: '2d'},
    coordinates: [Schema.Types.Mixed],
    water_points: [Schema.Types.Mixed]
});


//Export the schema
module.exports = mongoose.model('Sendero', senderoSchema);