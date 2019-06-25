/**
 * 
 */
package com.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZJankunas
 *
 */
@Getter
@Setter
public class Request
{
    @JsonProperty("ibans")
    private List<String> ibans;
}
