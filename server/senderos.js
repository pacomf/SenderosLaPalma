var express = require('express');
var http = require('http');
var mongoose = require('mongoose');
var bodyParser = require('body-parser')
var app = express();

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }))
// parse application/json
app.use(bodyParser.json())

routes = require('./routes/webservices')(app);  

mongoose.connect('mongodb://localhost/senderos_db');

http.createServer(app).listen(7200);
console.log("Lanzado en puerto 7200...");

var senderoController = require('./backend/controllers/senderoController');
var ratingController = require('./backend/controllers/ratingController');

var senderoModel = require('./backend/models/senderoModel');
var ratingModel = require('./backend/models/ratingModel');



/*senderoModel.findById("54f0a6cac77e72441056d95f", function (err, sendero){
    if (sendero === null)
        return;
    sendero.rating.addToSet("wqe");
    sendero.rating.addToSet("12423v");
    //sendero.save();
    console.log("Fin");
})*/

//var geom = [ [ -17.764601, 28.683521 ], [ -17.764381, 28.683697 ], [ -17.764341, 28.683732 ], [ -17.763839, 28.684073 ], [ -17.763449, 28.684424 ], [ -17.763338, 28.684546 ], [ -17.763243, 28.684655 ], [ -17.763242, 28.684738 ], [ -17.76316, 28.684792 ], [ -17.762899, 28.685164 ], [ -17.762677, 28.685445 ], [ -17.762617, 28.685538 ], [ -17.762584, 28.685594 ], [ -17.762391, 28.685796 ], [ -17.761983, 28.686234 ], [ -17.761616, 28.686547 ], [ -17.761496, 28.68674 ], [ -17.761389, 28.687051 ], [ -17.761328, 28.687218 ], [ -17.761052, 28.687958 ], [ -17.760985, 28.688167 ], [ -17.761004, 28.688348 ], [ -17.760836, 28.688361 ], [ -17.760569, 28.688386 ], [ -17.760076, 28.688445 ], [ -17.759808, 28.688476 ], [ -17.759716, 28.68855 ], [ -17.759755, 28.688752 ], [ -17.759863, 28.688776 ], [ -17.760083, 28.688726 ], [ -17.760093, 28.688959 ], [ -17.760056, 28.689017 ], [ -17.760046, 28.689108 ], [ -17.760048, 28.689144 ], [ -17.760058, 28.689188 ], [ -17.760078, 28.689231 ], [ -17.760063, 28.689247 ], [ -17.760033, 28.689278 ], [ -17.760016, 28.689293 ], [ -17.759991, 28.689304 ], [ -17.760048, 28.689416 ], [ -17.760092, 28.689419 ], [ -17.760138, 28.689424 ], [ -17.760262, 28.689432 ], [ -17.760331, 28.689482 ], [ -17.760435, 28.689601 ], [ -17.76049, 28.689875 ], [ -17.760497, 28.690051 ], [ -17.760493, 28.690681 ], [ -17.760491, 28.690886 ], [ -17.760398, 28.691229 ], [ -17.760382, 28.691277 ], [ -17.760352, 28.691349 ], [ -17.760338, 28.691413 ], [ -17.760292, 28.691604 ], [ -17.760257, 28.691726 ], [ -17.760142, 28.692136 ], [ -17.760035, 28.692508 ], [ -17.759995, 28.692643 ], [ -17.759952, 28.692814 ], [ -17.759942, 28.692872 ] ];

//senderoController.addSendero("Sendero 1", "S1", "2", "6", "A4", "GLineal", "3", 28.420950, -16.498490, "16.45", "23.64", geom, geom);
//senderoController.addSendero("Sendero 2", "S2", "2", "6", "A4", "GLineal", "3", 28.420308, -16.500851, "16.45", "23.64", geom, geom);
//senderoController.addSendero("Sendero 3", "S3", "2", "6", "A4", "GLineal", "3", 28.426045, -16.468321, "16.45", "23.64", geom, geom);
//senderoController.addSendero("Sendero 3", "S3", "2", "6", "A4", "GLineal", "3", 28.421780, -16.499477, "16.45", "23.64", geom, geom);

//var odController = require('./backend/controllers/openDataController');
//odController.loadData(null);
//odController.loadExtraInfo('./senderosInfo.json');


