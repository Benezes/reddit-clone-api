package br.com.mnz.reddit.controller.handler.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class FieldMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;
}
