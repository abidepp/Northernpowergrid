// Empty constructor
function UserGpsState() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
UserGpsState.prototype.show = function(message, duration, successCallback, errorCallback) {
  var options = {};
  options.message = message;
  options.duration = duration;
  cordova.exec(successCallback, errorCallback, 'UserGpsState', 'show', [options]);
}

// Installation constructor that binds UserGpsState to window
UserGpsState.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.usergpsstate = new UserGpsState();
  return window.plugins.usergpsstate;
};
cordova.addConstructor(UserGpsState.install);