package com.github.jolymatt.playground.userpref.domain;

import com.github.jolymatt.playground.userpref.util.DataUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
/**
 * The Generic Preferences Bundle domain class
 * @author Joly Mathew
 */

public class PropertyBundle implements Serializable {

    @Id
    private UUID id;

    private Map<String, Object> properties;

    private Map<String, String> metaData;

    @Override
    /**
     * Overriden toString method to print Json Strings in indented style
     * @return String
     */
    public String toString() {
        return DataUtil.toJsonString(this);
    }
}
