package com.ega.smartoutlet.httpRequests;


public class RequestsHandler {

    public static class LoginRequest {
        private String username;
        private String password;

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

    }

    public static class SaveDeviceRequest {
        private String deviceName;
        private String deviceUUid;

        public SaveDeviceRequest(String deviceName, String deviceUuid) {
            this.deviceName = deviceName;
            this.deviceUUid = deviceUuid;
        }
    }


}
