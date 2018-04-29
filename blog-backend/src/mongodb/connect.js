/* global process */

'use strict';

let mongoose = require('mongoose');
let config = require('../config');
let loggerModule = require('../logger');
let { once } = require('lodash/fp');

let NODE_ENV = process.env.NODE_ENV || 'development';
let enviromentConfig = config.getConfigValue(`database.config.${NODE_ENV}`);

const logger = once(() =>
  loggerModule.get().child({ module: 'mongoose connect' })
);

const options = {
  reconnectTries: Number.MAX_VALUE, // Never stop trying to reconnect
  reconnectInterval: enviromentConfig.reconnectInterval, // Reconnect every 500ms
  poolSize: enviromentConfig.poolSize, // Maintain up to 10 socket connections
  user: enviromentConfig.username,
  pass: enviromentConfig.password
};

mongoose.connect(enviromentConfig.connectionUrl, options, err => {
  if (err) {
    logger().error(err);
  } else {
    logger().info('Connected to mongo successfully :D');
  }
});
