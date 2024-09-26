package com.ega.smartoutlet.httpRequests;

import com.ega.smartoutlet.models.GroupModel;
import com.ega.smartoutlet.models.SensorReadings;

import java.util.List;

public class ResponseHandler {

    public static class LoginResponse {

        private boolean error;
        private int code;
        private LoginData data;
        private String message;

        public LoginData getData() {
            return data;
        }

        public String getMessage() {
            return message;
        }

        public  class LoginData {
            private String fullname;
            private String username;
            private String token;

            public String getFullname() {
                return fullname;
            }

            public String getUsername() {
                return username;
            }

            public String getToken() {
                return token;
            }

        }

    }

    public static class NewDeviceResponse {
        private boolean error;
        private int code;
        private String message;

        public NewDeviceResponse(boolean error, int code, String message) {
            this.error = error;
            this.code = code;
            this.message = message;
        }
    }


    public static class DevicesResponse {

        private List<Device> content;
        private int pageNumber;
        private int pageSize;
        private boolean last;
        private int totalPages;
        private int totalElements;
        private boolean empty;


        public static class Device {
            private int id;

            private String deviceName;
            private String uuid;
            private String deviceUuid;
            private boolean state;
            private boolean registered;
            private double timer;

            public String getUuid() {
                return uuid;
            }

            public String getDeviceUuid() {
                return deviceUuid;
            }

            public boolean isState() {
                return state;
            }

            public boolean isRegistered() {
                return registered;
            }

            public double getTimer() {
                return timer;
            }

            public String getDeviceName() {
                return deviceName;
            }

            // Getters and Setters
        }

        public List<Device> getContent() {
            return content;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public boolean isLast() {
            return last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public boolean isEmpty() {
            return empty;
        }
    }

    public static class SensorReadingsResponse {
        private List<SensorReadings> content;

        // Getter and setter
        public List<SensorReadings> getContent() {
            return content;
        }

        public void setContent(List<SensorReadings> content) {
            this.content = content;
        }
    }

    public static class DeviceGroupResponse {
        private List<GroupModel> content;
        private Pageable pageable;
        private int totalElements;
        private int totalPages;
        private boolean last;
        private int size;
        private float groupEnergyConsumed;

        public float getGroupEnergyConsumed() {
            return groupEnergyConsumed;
        }

        public void setGroupEnergyConsumed(float groupEnergyConsumed) {
            this.groupEnergyConsumed = groupEnergyConsumed;
        }



        public List<GroupModel> getContent() {
            return content;
        }

        public void setContent(List<GroupModel> content) {
            this.content = content;
        }

        public Pageable getPageable() {
            return pageable;
        }

        public void setPageable(Pageable pageable) {
            this.pageable = pageable;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public Sort getSort() {
            return sort;
        }

        public void setSort(Sort sort) {
            this.sort = sort;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        private int number;
        private Sort sort;
        private  int numberOfElements;
        private boolean empty;


        public class Pageable{
            private  int pageNumber;
            private int pageSize;
            private Sort sort;
            private int numberOfElements;

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public Sort getSort() {
                return sort;
            }

            public void setSort(Sort sort) {
                this.sort = sort;
            }

            public int getNumberOfElements() {
                return numberOfElements;
            }

            public void setNumberOfElements(int numberOfElements) {
                this.numberOfElements = numberOfElements;
            }

            public boolean isEmpty() {
                return Empty;
            }

            public void setEmpty(boolean empty) {
                Empty = empty;
            }

            private boolean Empty;
        }

        public  class Sort{
            private  boolean sorted;
            private boolean unsorted;

            public boolean isSorted() {
                return sorted;
            }

            public void setSorted(boolean sorted) {
                this.sorted = sorted;
            }

            public boolean isUnsorted() {
                return unsorted;
            }

            public void setUnsorted(boolean unsorted) {
                this.unsorted = unsorted;
            }

            public boolean isEmpty() {
                return empty;
            }

            public void setEmpty(boolean empty) {
                this.empty = empty;
            }

            private boolean empty;
        }
    }


}
