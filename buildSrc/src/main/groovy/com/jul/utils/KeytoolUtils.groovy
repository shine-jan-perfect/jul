package com.jul.utils

import com.jul.plugins.KeystoreInfoExtension

class KeytoolUtils {
    static def generateKeystore(KeystoreInfoExtension keystoreInfoExtension) {
        def process = null
        def brInput = null
        def brErr = null

        if (keystoreInfoExtension.keystoreDir.startsWith(".")
                || keystoreInfoExtension.keystoreDir.startsWith('\\')
                || keystoreInfoExtension.keystoreFileName.startsWith('/')
                || keystoreInfoExtension.keystoreFileName.startsWith('\\')
                || keystoreInfoExtension.keystoreFileName.startsWith('.')) {
            throw new IllegalStateException("keystoreDir should be keystore file basic path," +
                    " keystoreFileName should be keystore file name")
        }

        def keystoreFile = new File(keystoreInfoExtension.keystoreDir + File.separator
                + keystoreInfoExtension.keystoreFileName)
        def keystoreFilePath = keystoreFile.getCanonicalPath()
        /*try {
            def processBuilder = new ProcessBuilder(
                    keystoreInfoExtension.keytoolPath,
                    "-genkey",
                    "-keystore",
                    keystoreFilePath,
                    "-alias",
                    keystoreInfoExtension.alias,
                    "-storepass",
                    keystoreInfoExtension.storepass,
                    "-keypass",
                    keystoreInfoExtension.keypass,
                    "-keyalg",
                    keystoreInfoExtension.keyalg,
                    "-keysize",
                    keystoreInfoExtension.keysize,
                    "-validity",
                    keystoreInfoExtension.validity,
                    "-dname",
                    keystoreInfoExtension.dname)
            processBuilder.redirectErrorStream(true)
            println processBuilder.command()
            process = processBuilder.start()

            def isInput = process.getInputStream()
            brInput = new BufferedReader(new InputStreamReader(isInput, "gbk"))
            def str = null
            while ((str = brInput.readLine()) != null) {
                print str
            }

            def isErr = process.getErrorStream()
            brErr = new BufferedReader(new InputStreamReader(isErr, "gbk"))
            str = null
            while ((str = brErr.readLine()) != null) {
                print str
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
        ProcessUtils.exec("\"$keystoreInfoExtension.keytoolPath\"" +
                " -genkey" +
                " -keystore \"$keystoreFilePath\"" +
                " -alias $keystoreInfoExtension.alias" +
                " -storepass $keystoreInfoExtension.storepass" +
                " -keypass $keystoreInfoExtension.keypass" +
                " -keyalg $keystoreInfoExtension.keyalg" +
                " -keysize $keystoreInfoExtension.keysize" +
                " -validity $keystoreInfoExtension.validity" +
                " -dname $keystoreInfoExtension.dname", ProcessUtils.ENCODING_GBK)
    }
}