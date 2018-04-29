const config = require('../config/config.json');
const { get } = require('lodash/fp');

const getConfigValue = key => get(key, config);

module.exports = {
    get: () => config,
    getConfigValue
};