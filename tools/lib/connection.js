const mysql = require('mysql');

exports.getConnection = () => mysql.createConnection({
  host: 'localhost',
  user: 'root',
});
