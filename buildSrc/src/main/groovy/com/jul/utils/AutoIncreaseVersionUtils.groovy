package com.jul.utils

class AutoIncreaseVersionUtils {
    static def increaseVersionCode(String versionCodeStr) {
        def couldIncreasedNum = Integer.parseInt(versionCodeStr)
        if (couldIncreasedNum < Integer.MAX_VALUE) {
            couldIncreasedNum += 1
        } else {
            couldIncreasedNum = 1
        }
        String.valueOf(couldIncreasedNum)
    }

    static def increaseVersionName(String versionNameStr) {
        def versionNameArray = versionNameStr.split("\\.")
        println versionNameArray

        for (def i = versionNameArray.length - 1; i >= 0; i--) {
            def couldIncreasedNumStr = versionNameArray[i]
            def couldIncreasedNum = Integer.parseInt(couldIncreasedNumStr)
            if (couldIncreasedNum < Integer.MAX_VALUE) {
                couldIncreasedNum += 1
                versionNameArray[i] = String.valueOf(couldIncreasedNum)
                break
            } else if (couldIncreasedNum >= Integer.MAX_VALUE && i == 0) {
                for (def j = versionNameArray.length - 1; j >= 0; j--) {
                    if (j == 0) {
                        versionNameArray[j] = "1"
                    } else {
                        versionNameArray[j] = "0"
                    }
                }
            }
        }

        def increasedVersionName = new StringBuilder()
        for (def i = 0; i < versionNameArray.length; i++) {
            if (i == versionNameArray.length - 1) {
                increasedVersionName.append(versionNameArray[i])
            } else {
                increasedVersionName.append(versionNameArray[i]).append(".")
            }
        }

        increasedVersionName.toString()
    }

}