'use strict';

const bunyan = require('bunyan');
const { once } = require('lodash/fp');
const { VError } = require('verror');
const configModule = require('./config');
const config = configModule.get();

const intialize = () => {
  let logger;
  try {
    logger = bunyan.createLogger(config.logger);
  } catch (err) {
    throw new VError(err, 'failed to create logger');
  }
  return logger;
};

module.exports = {
  get: once(intialize)
};
