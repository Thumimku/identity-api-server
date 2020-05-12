package org.wso2.carbon.identity.api.server.fetch.remote.common;


/**
 * TODO
 */
public class RemoteFetchConfigurationConstants {

    public static final String REMOTE_FETCH_CONFIGURATION_MANAGEMENT_PREFIX = "RFC-";
    public static final String ERROR_CODE_DELIMITER = "-";
    public static final String FREQUENCY = "frequency";
    public static final String URI = "uri";
    public static final String BRANCH = "branch";
    public static final String DIRECTORY = "directory";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String USER_NAME = "userName";

    public static final String TYPE_REPOSITORY_MANAGER = "GIT";
    public static final String TYPE_ACTION_LISTENER = "POLLING";
    public static final String TYPE_CONFIG_DEPLOYER = "SP";

    public static final String DEPLOYED = "DEPLOYED";
    public static final String ERROR_DEPLOYING = "ERROR_DEPLOYING";


    public static final String REMOTE_FETCH_CONFIGURATION_PATH_COMPONENT = "/remote-fetch/configs";


    /**
     * Enum for error messages.
     */
    public enum ErrorMessage {

        ERROR_CODE_ERROR_LISTING_RF_CONFIGS("65001",
                "Unable to list existing remote fetch configurations.",
                "Server encountered an error while listing remote fetch configurations."),
        ERROR_CODE_ERROR_ADDING_RF_CONFIG("65002",
                "Unable to add remote fetch configuration.",
                "Server encountered an error while adding the remote fetch configuration."),
        ERROR_CODE_ERROR_RETRIEVING_RF_CONFIG("65003",
                "Unable to retrieve remote fetch configuration.",
                "Server encountered an error while retrieving the remote fetch configuration for identifier %s."),
        ERROR_CODE_ERROR_DELETING_RF_CONFIGS("65004",
                "Unable to delete remote fetch configuration.",
                "Server encountered an error while deleting the emote fetch configuration for the identifier %s."),
        ERROR_CODE_ERROR_UPDATING_RF_CONFIG("65005",
                "Unable to update remote fetch configuration.",
                "Server encountered an error while updating the remote fetch configuration for identifier %s."),
        ERROR_CODE_ERROR_TRIGGER_REMOTE_FETCH("65006",
                "Unable to trigger remote fetch .",
                "Server encountered an error while triggering the remote fetch for identifier %s."),
        ERROR_CODE_ERROR_STATUS_REMOTE_FETCH("65007",
                "Unable to get status for remote fetch .",
                "Server encountered an error while getting status for the remote fetch for identifier %s."),
        ERROR_CODE_RE_CONFIG_NOT_FOUND("60002", "Resource not found.",
                "Unable to find a resource matching the provided " +
                "remote fetch configuration identifier %s.");

        private final String code;
        private final String message;
        private final String description;

        ErrorMessage(String code, String message, String description) {

            this.code = code;
            this.message = message;
            this.description = description;
        }

        public String getCode() {

            return REMOTE_FETCH_CONFIGURATION_MANAGEMENT_PREFIX + code;
        }

        public String getMessage() {

            return message;
        }

        public String getDescription() {

            return description;
        }

        @Override
        public String toString() {

            return code + " | " + message;
        }
    }
}
