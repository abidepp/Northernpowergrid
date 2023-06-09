package com.northernpowergrid.cordova.plugin;
// The native Toast API
import android.widget.Toast;
import android.location.LocationManager;
import android.util.Log;
// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class UserGpsState extends CordovaPlugin {
  private static final String DURATION_LONG = "long";
  @Override
  public boolean execute(String action, JSONArray args,
    final CallbackContext callbackContext) {
      // Verify that the user sent a 'show' action
      if (!action.equals("show")) {
        callbackContext.error("\"" + action + "\" is not a recognized action.");
        return false;
      }
      String message;
      String duration;
      int gpsState;

      try {
        JSONObject options = args.getJSONObject(0);
        message = options.getString("message");
        duration = options.getString("duration");
      } catch (JSONException e) {
        callbackContext.error("Error encountered: " + e.getMessage());
        return false;
      }

      //check device gps state
      LocationManager locationManager = (LocationManager) cordova.getActivity().getSystemService(cordova.getActivity().LOCATION_SERVICE);
      gpsState = (locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) ? 1: 0;

      // Create the toast
      // Toast toast = Toast.makeText(cordova.getActivity(), gpsState,
      //   DURATION_LONG.equals(duration) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
      // // Display toast
      // toast.show();

      // Send a positive result to the callbackContext
      PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, gpsState);
      callbackContext.sendPluginResult(pluginResult);
      return true;
  }
}