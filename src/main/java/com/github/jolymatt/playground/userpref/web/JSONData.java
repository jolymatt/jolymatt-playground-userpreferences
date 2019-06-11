package com.github.jolymatt.playground.userpref.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jolymatt.playground.userpref.domain.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Sample Class to generate some Json Data
 */
@Slf4j
public class JSONData {

    public static UserPreference getUPPreferenceData() {
        UserPreference userPreference = new UserPreference();

        //Setup User Main object
        userPreference.setUserId("1119066");
        userPreference.setId(UUID.randomUUID());
        Map<String, Object> userProperties = new HashMap<>();
        userProperties.put("href", "https://iheartmedia.okta.com/api/v1/users/00ub0oNGTSWTBKOLGLNX");
        userPreference.setProperties(userProperties);

        //Setup User global Preferences if there is any
        PropertyBundle globalPreferences = new PropertyBundle();

        Map<String, Object> globalProperties = new HashMap<>();
        globalProperties.put("nightMode", "true");
        globalProperties.put("uiTheme", "dark_red_iheart");
        globalProperties.put("accessibilityMode", "false");
        globalPreferences.setProperties(globalProperties);

        userPreference.setGlobalPreferences(Arrays.asList(globalPreferences));


        //Setup Application preferences
        ApplicationPreference ihmAmplifyAppPrefernces = new ApplicationPreference();
        ihmAmplifyAppPrefernces.setId(UUID.randomUUID());

        Map<String, String> metaProperties = new HashMap<>();
        metaProperties.put("name", "ihm-amplify-ui");
        metaProperties.put("version", "1.0");
        ihmAmplifyAppPrefernces.setMetaData(metaProperties);

        ApplicationPreference upAppPreferences = new ApplicationPreference();
        upAppPreferences.setId(UUID.randomUUID());

        Map<String, String> metaProperties1 = new HashMap<>();

        metaProperties1.put("name", "ihm-unified-proposal");
        metaProperties1.put("version", "1.0");
        upAppPreferences.setMetaData(metaProperties1);

        userPreference.setApplicationPreferences(Arrays.asList(ihmAmplifyAppPrefernces, upAppPreferences));

        //Setup Device Profile
        DevicePreference deskTopDevicePreferences = new DevicePreference();
        deskTopDevicePreferences.setId(UUID.randomUUID());

        Map<String, String> deviceMeta = new HashMap<String, String>();
        deviceMeta.put("type", "Desktop");
        deskTopDevicePreferences.setMetaData(deviceMeta);

        //Setup Component
        ComponentPreference defaultComponentPreferences = new ComponentPreference();
        defaultComponentPreferences.setId(UUID.randomUUID());

        Map<String, Object> defaultComponentPropteries = new HashMap<>();

        defaultComponentPropteries.put("sidenavExpanded", "true");

        Map<String, Object> advancedLineEditroProperties = new HashMap<>();
        defaultComponentPropteries.put("advanced-line-editor", advancedLineEditroProperties);

        Map<String, Object> displayColMap = new HashMap<>();
        displayColMap.put("aqhRating", true);
        displayColMap.put("aqhPersons", false);
        displayColMap.put("grp", true);
        displayColMap.put("cpp", true);
        advancedLineEditroProperties.put("displayedColumns", displayColMap);
        defaultComponentPreferences.setProperties(defaultComponentPropteries);
        deskTopDevicePreferences.setComponentPreferences(Arrays.asList(defaultComponentPreferences));
        ihmAmplifyAppPrefernces.setDevicePreferences(Arrays.asList(deskTopDevicePreferences));

        return userPreference;
    }

    public static void main(String[] args) {
        System.out.println(JSONData.getUPPreferenceData());
    }

    public static UserPreference getSampleData() {
        UserPreference userPref = null;
        String jsonString = "" +
                " {\n" +
                "    \"id\": \"69adadd8-e35a-4900-a1b2-8f6a0f739019\",\n" +
                "    \"userId\": \"USR00000000001\",\n" +
                "    \"propertyMap\": null,\n" +
                "    \"app_pref_list\": [\n" +
                "        {\n" +
                "            \"env_pref_list\": [\n" +
                "                {\n" +
                "                    \"comp_pref_list\": [\n" +
                "                        {\n" +
                "                            \"id\": \"69adadd8-e35a-4900-a1b2-8f6a0f739019\",\n" +
                "                            \"propertyMap\": {\n" +
                "                                \"grid_filter_config\": {\n" +
                "                                    \"column_1_filter\": \"San Antonio\",\n" +
                "                                    \"column_1_sort\": \"desc\"\n" +
                "                                },\n" +
                "                                \"grid_visible_cols\": \"20\",\n" +
                "                                \"name\": \"radio_market_grid\",\n" +
                "                                \"type\": \"GRID\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"309ca043-9192-4d1d-949f-4c1a928e7196\",\n" +
                "                    \"propertyMap\": null,\n" +
                "                    \"type\": \"Desktop\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"id\": \"309ca043-9192-4d1d-949f-4c1a928e7196\",\n" +
                "            \"propertyMap\": {\n" +
                "                \"name\": \"unfied_proposal\",\n" +
                "                \"version\": \"1.0\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"env_pref_list\": [\n" +
                "                {\n" +
                "                    \"comp_pref_list\": [\n" +
                "                        {\n" +
                "                            \"id\": \"69adadd8-e35a-4900-a1b2-8f6a0f739019\",\n" +
                "                            \"propertyMap\": {\n" +
                "                                \"grid_filter_config\": {\n" +
                "                                    \"column_1_filter\": \"San Antonio\",\n" +
                "                                    \"column_1_sort\": \"desc\"\n" +
                "                                },\n" +
                "                                \"grid_visible_cols\": \"20\",\n" +
                "                                \"name\": \"radio_market_grid\",\n" +
                "                                \"type\": \"GRID\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"309ca043-9192-4d1d-949f-4c1a928e7196\",\n" +
                "                    \"propertyMap\": null,\n" +
                "                    \"type\": \"Desktop\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"id\": \"309ca043-9192-4d1d-949f-4c1a928e7196\",\n" +
                "            \"propertyMap\": {\n" +
                "                \"name\": \"unfied_proposal\",\n" +
                "                \"version\": \"1.1\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"global_pref_list\": [\n" +
                "        {\n" +
                "            \"id\": \"309ca043-9192-4d1d-949f-4c1a928e7196\",\n" +
                "            \"propertyMap\": {\n" +
                "                \"night_mode\":\"true\",\n" +
                "                \"ui_theme\":\"dark_red_iheart\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "\n" +
                "}\n";
        try {
            ObjectMapper mapper = new ObjectMapper();
            userPref = mapper.readValue(jsonString, UserPreference.class);
            return userPref;
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return userPref;
    }


}
