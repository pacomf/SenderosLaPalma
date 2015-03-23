var mongoose = require('mongoose'),
	utils = require('./utilities/utilsOpenData'),
	request = require("request"),
	fs = require('fs');
    senderoController = require('./senderoController');


exports.loadData = function(urlParam){
	var url = urlParam;
	if (utils.checkEmpty(url)){
		url = "http://www.opendatalapalma.es/datasets/ea9995fafe1f40e5ada6dba4fe2e1ff2_2.geojson";
	}

	request({
    	url: url,
    	json: true
	}, function (error, response, body) {
	    if (!error && response.statusCode === 200) {
	        // JSON is 'body'
	        var senderos = body.features;
	        /* TODO: - (x) Borrar TODO cada vez que se cargue un JSON, para CREAR TODO de nuevo.
	                 - ( ) O:
	                         * Actualizacion, ver si ya está un Sendero en BBDD y NO CREAR, sino ACTUALIZAR
	                         * Borrado, ver si ya no hay Senderos que antes había y BORRARLOS
	        */
	        // Borrar todos los senderos
	        senderoController.deleteAllSenderos();
	        for (var i=0; i<senderos.length; i++){
	        	senderoController.addSendero("", // name
	        								 senderos[i].properties.ID, // regular_name
	        								 senderos[i].properties.FECHA, // version
	        								 senderos[i].properties.LONGITUD, // length
	        		                         senderos[i].properties.TIPO,  // type
	        		                         senderos[i].properties.DIFICULTAD, //difficulty
	        		                         senderos[i].geometry.type, // geometry_type
	        		                         0, // likes
	        		                         senderos[i].geometry.coordinates[0][1], // geoStartLatitude
	        		                         senderos[i].geometry.coordinates[0][0], // geoStartLongitude
	        		                         senderos[i].geometry.coordinates[senderos[i].geometry.coordinates.length-1][1], // geoEndLatitude
	        		                         senderos[i].geometry.coordinates[senderos[i].geometry.coordinates.length-1][0], // geoEndLongitude
	        		                         senderos[i].geometry.coordinates, // coordinates
	        		                         [] // water_points
	        		                        ); 
	        }
	        console.log("Open Data Loaded!");
	    }
	});

}

exports.loadExtraInfo = function(file){
	var info = JSON.parse(fs.readFileSync(file, 'utf8')).senderos;
	for (var i=0; i<info.length; i++){
		senderoController.updateSendero(info[i].name, info[i].id, info[i].url);
	}
}