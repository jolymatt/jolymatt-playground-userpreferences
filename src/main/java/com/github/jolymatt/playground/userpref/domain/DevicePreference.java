package com.github.jolymatt.playground.userpref.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * The Device Preference Domain class
 * @author Joly Mathew
 */
@Getter
@Setter
public class DevicePreference extends PropertyBundle {
    private List<ComponentPreference> componentPreferences;
}
