const { promisify } = require('util');
const fs = require('fs');
const csvParse = require('csv-parse');
const mysql = require('mysql');

const connection = mysql.createConnection({
  host: process.env.DB_HOST || 'localhost',
  user: 'root',
});

const readFileAsync = promisify(fs.readFile);
const csvParseAsync = promisify(csvParse);
const queryAsync = (...args) => new Promise((res, rej) => {
  connection.query(...args, (error, results, fields) => {
    if (error) return rej(error);
    res([results, fields]);
  });
});

const initTimesheet = async () => {
  const csv = await readFileAsync('secrets/timesheet.csv');
  const [, ...parsed] = await csvParseAsync(csv);
  const allNum = parsed.length;
  await queryAsync('create table timesheet (id SMALLINT UNSIGNED, date DATE, checkin DATETIME, checkout DATETIME)');

  while (parsed.length) {
    const part = parsed.splice(0, 10000);
    await Promise.all(part.map(([id, date, checkin, checkout]) => {
      id = parseInt(id) || null;
      date = ((date != null) && date.length) ? date : null;
      checkin = ((checkin != null) && checkin.length) ? checkin : null;
      checkout = ((checkout != null) && checkout.length) ? checkout : null;
      return queryAsync('insert into timesheet values (?, ?, ?, ?)', [id, date, checkin, checkout]);
    }));
    console.log(new Date().toLocaleTimeString(), '[timesheet] imported', allNum - parsed.length, '/', allNum);
  }
};

const initEmployee = async () => {
  const csv = await readFileAsync('secrets/employee_personaldata.csv');
  const [, ...parsed] = await csvParseAsync(csv);
  await queryAsync('create table employee (id SMALLINT UNSIGNED unique, birthday DATE, address VARCHAR(500))');

  for (let [id, birthday, address] of parsed) {
    id = parseInt(id) || null;
    birthday = ((birthday != null) && birthday.length) ? birthday : null;
    address = ((address != null) && address.length) ? address : null;
    await queryAsync('insert into employee values (?, ?, ?)', [id, birthday, address]);
  }
  console.log(new Date().toLocaleTimeString(), '[employee_personaldata] imported all records', parsed.length);
};

const initRetirement_file = async (filepath) => {
  const csv = await readFileAsync(filepath);
  const [, ...parsed] = await csvParseAsync(csv);
  for (let [, id, gender, period] of parsed) {
    id = parseInt(id) || null;
    if (!id) continue;
    await queryAsync('insert into retirement values (?, ?, ?)', [id, gender, period]);
  }
  console.log(new Date().toLocaleTimeString(), '[retirement] imported all records in', filepath);
};

const initRetirement = async () => {
  await queryAsync('create table retirement (id SMALLINT UNSIGNED not null, gender VARCHAR(20), period VARCHAR(20))');

  await initRetirement_file('secrets/retirement_history_2017.csv');
  await initRetirement_file('secrets/retirement_history_Turn-over 5.2016.csv');
  await initRetirement_file('secrets/retirement_history_Turn-over 6.2016.csv');
};

(async () => {
  try {
    await queryAsync('drop database if exists test');
    await queryAsync('create database test');
    await queryAsync('use test');
    await Promise.all([initEmployee(), initRetirement(), initTimesheet()]);
  } catch (e) {
    console.log(e);
  } finally {
    connection.end();
  }
})();
