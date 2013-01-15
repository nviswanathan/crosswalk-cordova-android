/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/
package org.apache.cordova;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Base64;
import android.util.Log;

public class BinaryEcho extends CordovaPlugin {

    /**
     * Executes the request.
     *
     * @param action        	The action to execute.
     * @param args          	JSONArry of arguments for the plugin.
     * @param callbackContext 	The callback context used when calling back into JavaScript.
     * @return              	True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        try {
            if (action.equals("echo")) {
                //CordovaJSONArray cdvArgs = (CordovaJSONArray) args;
                //byte[] data = cdvArgs.getArrayBuffer(0);
                Log.i("Braden", "BinaryEcho top");
                byte[] data = Base64.decode(args.getString(0), Base64.DEFAULT);
                Log.i("Braden", "byte[] retrieved: " + data.length);

                // Don't return any result now, since status results will be sent when events come in from broadcast receiver
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, data);
                Log.i("Braden", "PluginResult created");
                callbackContext.sendPluginResult(pluginResult);
                Log.i("Braden", "sendPluginResult() complete");
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
