package org.example.models;

public class UserModels {

    public static enum UserRole {
        Admin,
        Manager,
        Associate;
    }

    public static enum Status {
        Active,
        Inactive;
    }

    public static class User {
        private int user_id;
        private String user_name;
        private String email;
        private String phone;
        private String password;
        private UserRole user_role;
        private Status status;

        public User(){}

        public User(int user_id, String user_name, String email, String phone, String password, UserRole user_role, Status status) {
            this.user_id = user_id;
            this.user_name = user_name;
            this.email = email;
            this.phone = phone;
            this.password = password;
            this.user_role = user_role;
            this.status = status;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public UserRole getUser_role() {
            return user_role;
        }

        public void setUser_role(UserRole user_role) {
            this.user_role = user_role;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }
    }
}