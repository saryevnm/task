package com.atonin.task.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @XmlElement
    private Long id;
    @XmlElement
    private Long p_id;
    @XmlElement
    private Integer status;
    @XmlElement
    private String message;
    @XmlElement
    private Date dts;

    public Response(String message) {
        this.message = message;
    }


}
