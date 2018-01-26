import axios from 'axios';

export const getGraph = async () => {
  try {
    const response = await axios.get('/graph');
    return [null, response.data];
  } catch (e) {
    return [e, e.response.data];
  }
};

export const getTable = async (range) => {
  try {
    const response = await axios.get(`/${range}`);
    return [null, response.data];
  } catch (e) {
    return [e, e.response.data];
  }
};
