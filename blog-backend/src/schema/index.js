let { makeExecutableSchema } = require('graphql-tools');
let resolvers = require('./resolvers');
let types = require('./types');

let typeDefs = [...types];

let schema = makeExecutableSchema({ typeDefs, resolvers });

module.exports = schema;
