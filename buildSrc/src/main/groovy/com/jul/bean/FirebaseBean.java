package com.jul.bean;

import java.util.List;

public class FirebaseBean {

    /**
     * project_info : {"project_number":"366128067430","firebase_url":"https://linzong223
     * .firebaseio.com","project_id":"linzong223","storage_bucket":"linzong223.appspot.com"}
     * client : [{"client_info":{"mobilesdk_app_id":"1:366128067430:android:bc932c50dbab2a56",
     * "android_client_info":{"package_name":"com.satuhati.lianyun3"}},"oauth_client":[{
     * "client_id":"366128067430-q03ge5vhrkjdbp2mhnuurua1h3e3381a.apps.googleusercontent.com",
     * "client_type":3}],"api_key":[{"current_key":"AIzaSyBRoVt_IsTtILcatj4a-FYProAPrn8Td5Q"}],
     * "services":{"analytics_service":{"status":1},"appinvite_service":{"status":1,
     * "other_platform_oauth_client":[]},"ads_service":{"status":2}}}]
     * configuration_version : 1
     */

    private ProjectInfoBean project_info;
    private String configuration_version;
    private List<ClientBean> client;

    public ProjectInfoBean getProject_info() {
        return project_info;
    }

    public void setProject_info(ProjectInfoBean project_info) {
        this.project_info = project_info;
    }

    public String getConfiguration_version() {
        return configuration_version;
    }

    public void setConfiguration_version(String configuration_version) {
        this.configuration_version = configuration_version;
    }

    public List<ClientBean> getClient() {
        return client;
    }

    public void setClient(List<ClientBean> client) {
        this.client = client;
    }

    public static class ProjectInfoBean {
        /**
         * project_number : 366128067430
         * firebase_url : https://linzong223.firebaseio.com
         * project_id : linzong223
         * storage_bucket : linzong223.appspot.com
         */

        private String project_number;
        private String firebase_url;
        private String project_id;
        private String storage_bucket;

        public String getProject_number() {
            return project_number;
        }

        public void setProject_number(String project_number) {
            this.project_number = project_number;
        }

        public String getFirebase_url() {
            return firebase_url;
        }

        public void setFirebase_url(String firebase_url) {
            this.firebase_url = firebase_url;
        }

        public String getProject_id() {
            return project_id;
        }

        public void setProject_id(String project_id) {
            this.project_id = project_id;
        }

        public String getStorage_bucket() {
            return storage_bucket;
        }

        public void setStorage_bucket(String storage_bucket) {
            this.storage_bucket = storage_bucket;
        }
    }

    public static class ClientBean {
        /**
         * client_info : {"mobilesdk_app_id":"1:366128067430:android:bc932c50dbab2a56",
         * "android_client_info":{"package_name":"com.satuhati.lianyun3"}}
         * oauth_client : [{"client_id":"366128067430-q03ge5vhrkjdbp2mhnuurua1h3e3381a.apps
         * .googleusercontent.com","client_type":3}]
         * api_key : [{"current_key":"AIzaSyBRoVt_IsTtILcatj4a-FYProAPrn8Td5Q"}]
         * services : {"analytics_service":{"status":1},"appinvite_service":{"status":1,
         * "other_platform_oauth_client":[]},"ads_service":{"status":2}}
         */

        private ClientInfoBean client_info;
        private ServicesBean services;
        private List<OauthClientBean> oauth_client;
        private List<ApiKeyBean> api_key;

        public ClientInfoBean getClient_info() {
            return client_info;
        }

        public void setClient_info(ClientInfoBean client_info) {
            this.client_info = client_info;
        }

        public ServicesBean getServices() {
            return services;
        }

        public void setServices(ServicesBean services) {
            this.services = services;
        }

        public List<OauthClientBean> getOauth_client() {
            return oauth_client;
        }

        public void setOauth_client(List<OauthClientBean> oauth_client) {
            this.oauth_client = oauth_client;
        }

        public List<ApiKeyBean> getApi_key() {
            return api_key;
        }

        public void setApi_key(List<ApiKeyBean> api_key) {
            this.api_key = api_key;
        }

        public static class ClientInfoBean {
            /**
             * mobilesdk_app_id : 1:366128067430:android:bc932c50dbab2a56
             * android_client_info : {"package_name":"com.satuhati.lianyun3"}
             */

            private String mobilesdk_app_id;
            private AndroidClientInfoBean android_client_info;

            public String getMobilesdk_app_id() {
                return mobilesdk_app_id;
            }

            public void setMobilesdk_app_id(String mobilesdk_app_id) {
                this.mobilesdk_app_id = mobilesdk_app_id;
            }

            public AndroidClientInfoBean getAndroid_client_info() {
                return android_client_info;
            }

            public void setAndroid_client_info(AndroidClientInfoBean android_client_info) {
                this.android_client_info = android_client_info;
            }

            public static class AndroidClientInfoBean {
                /**
                 * package_name : com.satuhati.lianyun3
                 */

                private String package_name;

                public String getPackage_name() {
                    return package_name;
                }

                public void setPackage_name(String package_name) {
                    this.package_name = package_name;
                }
            }
        }

        public static class ServicesBean {
            /**
             * analytics_service : {"status":1}
             * appinvite_service : {"status":1,"other_platform_oauth_client":[]}
             * ads_service : {"status":2}
             */

            private AnalyticsServiceBean analytics_service;
            private AppinviteServiceBean appinvite_service;
            private AdsServiceBean ads_service;

            public AnalyticsServiceBean getAnalytics_service() {
                return analytics_service;
            }

            public void setAnalytics_service(AnalyticsServiceBean analytics_service) {
                this.analytics_service = analytics_service;
            }

            public AppinviteServiceBean getAppinvite_service() {
                return appinvite_service;
            }

            public void setAppinvite_service(AppinviteServiceBean appinvite_service) {
                this.appinvite_service = appinvite_service;
            }

            public AdsServiceBean getAds_service() {
                return ads_service;
            }

            public void setAds_service(AdsServiceBean ads_service) {
                this.ads_service = ads_service;
            }

            public static class AnalyticsServiceBean {
                /**
                 * status : 1
                 */

                private int status;

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }

            public static class AppinviteServiceBean {
                /**
                 * status : 1
                 * other_platform_oauth_client : []
                 */

                private int status;
                private List<?> other_platform_oauth_client;

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public List<?> getOther_platform_oauth_client() {
                    return other_platform_oauth_client;
                }

                public void setOther_platform_oauth_client(List<?> other_platform_oauth_client) {
                    this.other_platform_oauth_client = other_platform_oauth_client;
                }
            }

            public static class AdsServiceBean {
                /**
                 * status : 2
                 */

                private int status;

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }

        public static class OauthClientBean {
            /**
             * client_id : 366128067430-q03ge5vhrkjdbp2mhnuurua1h3e3381a.apps.googleusercontent.com
             * client_type : 3
             */

            private String client_id;
            private int client_type;

            public String getClient_id() {
                return client_id;
            }

            public void setClient_id(String client_id) {
                this.client_id = client_id;
            }

            public int getClient_type() {
                return client_type;
            }

            public void setClient_type(int client_type) {
                this.client_type = client_type;
            }
        }

        public static class ApiKeyBean {
            /**
             * current_key : AIzaSyBRoVt_IsTtILcatj4a-FYProAPrn8Td5Q
             */

            private String current_key;

            public String getCurrent_key() {
                return current_key;
            }

            public void setCurrent_key(String current_key) {
                this.current_key = current_key;
            }
        }
    }
}
