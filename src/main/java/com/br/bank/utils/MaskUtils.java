package com.br.bank.utils;

import static org.apache.commons.lang3.StringUtils.isAllBlank;
import static org.apache.commons.lang3.StringUtils.repeat;

public class MaskUtils {

    private static String maskTreatment(String text, int length){
        if(isAllBlank(text)){
            return null;
        }
        int textSize = text.length() - length;

        if (textSize > 0){
            return repeat("*", textSize) +
                    text.substring(textSize);
        }
        return repeat("*", text.length());
    }
}
