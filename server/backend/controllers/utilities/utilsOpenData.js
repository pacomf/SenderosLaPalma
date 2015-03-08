function checkEmpty(object){
	if ((object === null) || (object === undefined) || (object === '')){
		return true;
	}
	return false;
}

exports.checkEmpty = checkEmpty;