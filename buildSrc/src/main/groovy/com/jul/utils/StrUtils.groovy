package com.jul.utils

class StrUtils {
    static def generateRandomStr(int len) {
        def str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        def random = new Random()
        def sb = new StringBuffer()
        for (int i = 0; i < len; ++i) {
            sb.append(str.charAt(random.nextInt(str.length())))
        }
        sb.toString()
    }

    static def replaceNum2Letter(String str) {
        def sb = new StringBuffer()
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i)
            if (chr >= '0' && chr <= '9') { // 0-9
                sb.append((char) (chr + (char) ('a') - (char) ('0')))
            } else if (chr >= 'A' && chr <= 'Z') { // A-Z
                sb.append((char) (chr + (char) ('a') - (char) ('A')))
            } else { // a-z, others
                sb.append(chr)
            }
        }
        sb.toString()
    }
}