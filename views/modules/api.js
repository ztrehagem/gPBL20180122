import axios from 'axios';

export const get = async (id) => {
  try {
    const response = await axios.get(`/hello?id=${id}`);
    return [null, response.data];
  } catch (e) {
    return [e, e.response.data];
  }
}
