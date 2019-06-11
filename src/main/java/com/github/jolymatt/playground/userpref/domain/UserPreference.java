package com.github.jolymatt.playground.userpref.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;



/**
 * The UserPreferences Domain class
 * @Author Joly Mathew
 */
@Getter
@Setter
@Document
public class UserPreference extends PropertyBundle {
    List<PropertyBundle> globalPreferences;
    List<ApplicationPreference> applicationPreferences;
    private String userId;
}
