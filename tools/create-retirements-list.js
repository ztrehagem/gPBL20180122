const { promisify } = require('util');
const fs = require('fs');
const csvParse = require('csv-parse');

const readFileAsync = promisify(fs.readFile);
const csvParseAsync = promisify(csvParse);

(async () => {
  const csves = await Promise.all([
    readFileAsync('secrets/retirement_history_2017.csv'),
    readFileAsync('secrets/retirement_history_Turn-over 5.2016.csv'),
    readFileAsync('secrets/retirement_history_Turn-over 6.2016.csv'),
  ]);
  const parsedes = await Promise.all(csves.map(csv => csvParseAsync(csv)));
  const ids = parsedes.map(([, ...parsed]) => parsed.map(p => parseInt(p[1])).filter(id => !!id)).reduce((ids, list) => [...ids, ...list], []);
  const uniqueIds = ids.reduce((uniques, id) => uniques.includes(id) ? uniques : [...uniques, id], []);
  fs.writeFileSync('secrets/retirements.txt', uniqueIds.join(','));
})();
