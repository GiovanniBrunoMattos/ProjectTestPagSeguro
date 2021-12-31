package com.br.bank.utils;

public final class Paths {

    public static final String V1 = "/v1";

    public static final String ID = "id";

    public static final String START_BRACKET = "/{";

    public static final String END_BRACKET = "}";

    public static final String FIND_ACCOUNT_NUMBER = "/findAccountNumber";

    public static final String FIND_ACCOUNT_NAME = "/findAccountByName";

    public static final String FIND_AGENCY_RELEASEDOVERDRAFT_NAME = "/findAccount";

    public static final String CREATE_ACCOUNT = "/createAccount";

    public static final String DELETE_ACCOUNT = "/deleteAccount";

    public static final String UPDATE_ACCOUNT = "/updateAccount";

    public static final String V1_CREATE_ACCOUNT = V1 + CREATE_ACCOUNT;

    public static final String V1_FIND_BY_ALL = V1 + FIND_ACCOUNT_NUMBER;

    public static final String V1_QUERY_BY_NAME = V1 + FIND_ACCOUNT_NAME + START_BRACKET + ID + END_BRACKET;

    public static final String V1_QUERY_BY_AGENCY_RELEASEDOVERDRAFT_NAME = V1 + FIND_AGENCY_RELEASEDOVERDRAFT_NAME;

    public static final String V1_QUERY_BY_ACCOUNT_NUMBER = V1 + FIND_ACCOUNT_NUMBER + START_BRACKET + ID + END_BRACKET;

    public static final String V1_DELETE_BY_ACCOUNT_NUMBER = V1 + DELETE_ACCOUNT + START_BRACKET + ID + END_BRACKET ;

    public static final String V1_UPDATE_BY_ACCOUNT_NUMBER = V1 + UPDATE_ACCOUNT + START_BRACKET + ID + END_BRACKET;

}
