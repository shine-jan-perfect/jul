package com.jul.utils

class PropertiesUtils {
    static def readKeystoreInfoFromProperties(def keystoreInfoExtension) {
        def inputStream = null
        try {
            def properties = new SafeProperties()
            inputStream = new FileInputStream(keystoreInfoExtension.propertiesInfo.propertiesPath)
            properties.load(inputStream)

            if (properties.containsKey(keystoreInfoExtension.propertiesInfo.aliasKey))
                keystoreInfoExtension.alias = properties.getProperty(keystoreInfoExtension.propertiesInfo.aliasKey)
            if (properties.containsKey(keystoreInfoExtension.propertiesInfo.keystoreFileNameKey))
                keystoreInfoExtension.keystoreFileName = properties.getProperty(keystoreInfoExtension.propertiesInfo.keystoreFileNameKey)
            if (properties.containsKey(keystoreInfoExtension.propertiesInfo.keypassKey))
                keystoreInfoExtension.keypass = properties.getProperty(keystoreInfoExtension.propertiesInfo.keypassKey)
            if (properties.containsKey(keystoreInfoExtension.propertiesInfo.storepassKey))
                keystoreInfoExtension.storepass = properties.getProperty(keystoreInfoExtension.propertiesInfo.storepassKey)

            if (properties.containsKey(keystoreInfoExtension.propertiesInfo.appInfo.applicationIdKey))
                keystoreInfoExtension.propertiesInfo.appInfo.applicationId = properties.getProperty(keystoreInfoExtension.propertiesInfo.appInfo.applicationIdKey)

            if (properties.containsKey(keystoreInfoExtension.propertiesInfo.appInfo.versionCodeKey))
                keystoreInfoExtension.propertiesInfo.appInfo.versionCode = properties.getProperty(keystoreInfoExtension.propertiesInfo.appInfo.versionCodeKey)
            if (properties.containsKey(keystoreInfoExtension.propertiesInfo.appInfo.versionNameKey))
                keystoreInfoExtension.propertiesInfo.appInfo.versionName = properties.getProperty(keystoreInfoExtension.propertiesInfo.appInfo.versionNameKey)

            keystoreInfoExtension
        } catch (IOException e) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
    }

    static def readProperties(def propertiesPath, def key) {
        def inputStream = null
        try {
            def properties = new SafeProperties()
            inputStream = new FileInputStream(propertiesPath)
            properties.load(inputStream)

            if (properties.containsKey(key))
                properties.getProperty(key)
            else
                null
        } catch (IOException e) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
    }

    static def writeProperties(def propertiesPath, def key, def value) {
        def inputStream = null
        def outputStream = null
        try {
            def properties = new SafeProperties()
            inputStream = new FileInputStream(propertiesPath)
            properties.load(inputStream)

            outputStream = new FileOutputStream(propertiesPath, false)

            if (properties.containsKey(key))
                properties.setProperty(key, value)

            properties.store(outputStream, null)
        } catch (IOException e) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
            if (outputStream != null) {
                outputStream.close()
            }
        }
    }
}