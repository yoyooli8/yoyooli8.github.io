var express = require('express');
var router = express.Router();

/* angulaJs test. */
router.get('/', function(req, res, next) {
  res.render('angularIndex', { title: 'Hello',layout:'angulaMain' });
});

module.exports = router;
