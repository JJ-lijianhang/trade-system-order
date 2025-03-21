package com.futurebank.order.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileReq implements Serializable {
    private static final long serialVersionUID = -7983631016985371884L;

    private String fileName;

    private String filePath;
}
