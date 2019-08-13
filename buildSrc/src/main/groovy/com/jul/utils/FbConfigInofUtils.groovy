package com.jul.utils

import com.jul.plugins.KeystoreInfoExtension

class FbConfigInofUtils {
    static def writeFbConfigFile(KeystoreInfoExtension keystoreInfoExtension) {

        def fbConfigHash = OpensslUtils.printOpensslInfo(keystoreInfoExtension)

        def fbConfigFilePath = keystoreInfoExtension.fbConfigInfo.fbConfigFilePath

        println 'fbConfigFilePath -> ' + fbConfigFilePath
        //check file path and name
        if (fbConfigFilePath?.trim()) {

            def appId = PropertiesUtils.readProperties(keystoreInfoExtension.propertiesInfo.propertiesPath,
                    keystoreInfoExtension.propertiesInfo.appInfo.applicationIdKey)
            def fbConfigText = "\n" +
                    "FB相关配置\n" +
                    "\n" +
                    "隐私协议：$keystoreInfoExtension.fbConfigInfo.fbConfigPrivacy\n" +
                    "GP包名： $appId\n" +
                    "类名：$keystoreInfoExtension.fbConfigInfo.fbConfigClassName\n" +
                    "密钥散列：$fbConfigHash\n" +
                    "\n" +
                    "\n"
           def file= new File(fbConfigFilePath)
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs()
            }
            FileUtils.writeFile(fbConfigFilePath, fbConfigText, false)

            println 'writeFbConfigFile -> ' + fbConfigText

        } else {
            throw new IllegalStateException("fbConfigFileDir should be a file basic path")
        }


    }
}