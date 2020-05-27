package org.jh.com.commun;

import java.time.format.DateTimeFormatter;

public final class Constant {
    private Constant(){}
    public static final String THE_ACCOUNT_NOT_YET_INITIALIZED = "The Account not yet initialized";
    public static final String THIS_OPERATION_CAN_T_BE_DONE = "This operation can't be done";
    public static final String HEADER_FORMAT = "Operation  |  date  |  amount  |  balance";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final String EUROPE_PARIS = "Europe/Paris";
}
