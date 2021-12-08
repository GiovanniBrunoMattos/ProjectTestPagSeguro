package com.br.bank.utils;

public final class Paths {

    public static final String V1 = "/v1";

    public static final String CREATE_ACCOUNT = V1 + "/create_account";

    public static final String FIND_BY_ALL = "/v1/findAccountNumber";

    public static final String QUERY_BY_ACCOUNT_NUMBER = "/v1/findAccountNumber/{id}";

    public static final String DELETE_BY_ACCOUNT_NUMBER = "/v1/deleteAccount/{id}";

    public static final String UPDATE_BY_ACCOUNT_NUMBER ="/v1/updateAccount/{id}";

}
