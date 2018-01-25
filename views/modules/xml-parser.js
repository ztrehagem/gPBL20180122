import xml2js from 'xml2js';

export const parse = xmlString => new Promise((res, rej) => {
  const parser = new xml2js.Parser({
    explicitArray: false,
  });
  parser.parseString(xmlString, (err, result) => {
    if (err) return rej(err);
    res(result);
  });
});
