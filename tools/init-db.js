const { promisify } = require('util');
const fs = require('fs');
const csvParse = require('csv-parse');
const { getConnection } = require('./lib/connection');

const connection = getConnection();

const readFileAsync = promisify(fs.readFile);
const csvParseAsync = promisify(csvParse);
const queryAsync = (...args) => new Promise((res, rej) => {
  connection.query(...args, (error, results, fields) => {
    if (error) return rej(error);
    res([results, fields]);
  });
});

const initTimesheet = async () => {
  const csv = await readFileAsync('../secrets/timesheet.csv');
  const [, ...parsed] = await csvParseAsync(csv);
  await queryAsync('create table timesheet (id SMALLINT UNSIGNED, date DATE, checkin DATETIME, checkout DATETIME)');

  for (const record of parsed) {
    let [id, date, checkin, checkout] = record;
    id = parseInt(id) || null;
    date = ((date != null) && date.length) ? date : null;
    checkin = ((checkin != null) && checkin.length) ? checkin : null;
    checkout = ((checkout != null) && checkout.length) ? checkout : null;
    await queryAsync('insert into timesheet values (?, ?, ?, ?)', [id, date, checkin, checkout]);
  }
};

const initEmployee = async () => {
  const csv = await readFileAsync('../secrets/employee_personaldata.csv');
  const [, ...parsed] = await csvParseAsync(csv);
  await queryAsync('create table employee (id SMALLINT UNSIGNED unique, birthday DATE, address VARCHAR(500))');

  for (const record of parsed) {
    let [id, birthday, address] = record;
    id = parseInt(id) || null;
    birthday = ((birthday != null) && birthday.length) ? birthday : null;
    address = ((address != null) && address.length) ? address : null;
    await queryAsync('insert into employee values (?, ?, ?)', [id, birthday, address]);
  }
};

(async () => {
  try {
    await queryAsync('drop database if exists test');
    await queryAsync('create database test');
    await queryAsync('use test');
    await Promise.all([initEmployee(), initTimesheet()]);
  } catch (e) {
    console.log(e);
  } finally {
    connection.end();
  }
})();
