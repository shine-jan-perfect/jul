package com.jul.utils

import com.jul.plugins.KeystoreInfoExtension

class OpensslUtils {
    static def printOpensslInfo(KeystoreInfoExtension keystoreInfoExtension) {
        def process = null

        def brInput = null
        def brErr = null

        def keystoreFile = new File(keystoreInfoExtension.keystoreDir + File.separator + keystoreInfoExtension.keystoreFileName)
        def keystoreFilePath = keystoreFile.getCanonicalPath()
        println 'keystoreInfoExtension -> ' + keystoreInfoExtension.toString()
        println 'keystoreFilePath -> ' + keystoreFilePath
        /*try {
            String[] batchCommand = new String[3]

            println "os -> " + OsUtils.getOS()
            if (OsUtils.isWindows()) {
                batchCommand[0] = 'cmd'
                batchCommand[1] = '/c'
            } else {
                batchCommand[0] = '/bin/bash'
                batchCommand[1] = '-c'
            }
            def command = "\"$keystoreInfoExtension.keytoolPath\"" +
                            " -exportcert -alias " +
                            keystoreInfoExtension.alias +
                            " -keystore " +
                            keystoreFilePath +
                            " -storepass " +
                            keystoreInfoExtension.storepass +
                            " -keypass " +
                            keystoreInfoExtension.keypass +
                            " | " +
                            "\"$keystoreInfoExtension.opensslInfo.opensslPath\"" +
                            " sha1 -binary | " +
                            "\"$keystoreInfoExtension.opensslInfo.opensslPath\"" +
                            " base64"
            batchCommand[2] = command

            process = Runtime.getRuntime().exec(batchCommand)

            brInput = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"))
            def line = null
            while ((line = brInput.readLine()) != null) {
                println line
            }

            brErr = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GBK"))
            line = null
            while ((line = brErr.readLine()) != null) {
                println line
            }
        } catch (IOException e) {
            e.printStackTrace()
        } finally {
            if (brInput != null) {
                brInput.close()
            }
            if (brErr != null) {
                brErr.close()
            }
        }*/
        ProcessUtils.execPip("\"$keystoreInfoExtension.keytoolPath\"" +
                " -exportcert" +
                " -alias $keystoreInfoExtension.alias" +
                " -keystore \"$keystoreFilePath\"" +
                " -storepass $keystoreInfoExtension.storepass" +
                " -keypass $keystoreInfoExtension.keypass" +
                " | " +
                "\"$keystoreInfoExtension.opensslInfo.opensslPath\" sha1 -binary" +
                " | " +
                "\"$keystoreInfoExtension.opensslInfo.opensslPath\" base64", ProcessUtils.ENCODING_GBK)
    }
}