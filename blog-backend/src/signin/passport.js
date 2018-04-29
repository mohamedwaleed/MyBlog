let LocalStratgy = require('passport-local');
let passport = require('passport');
let user = require('../models/user');

const signinOptions = {
  usernameField: 'email'
};

const signinStratrgy = new LocalStratgy(
  signinOptions,
  (email, password, done) => {
    user.findOne({ email: email }, function(err, user) {
      if (err) {
        return done(err, false);
      }
      if (!user) {
        return done(null, false);
      }
      if (
        !user.comparePassword(password, () => {
          return done(null, false);
        })
      );
      return done(null, user);
    });
  }
);

passport.use(signinStratrgy);
