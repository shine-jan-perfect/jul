package com.jul.plugins

import com.jul.bean.FirebaseBean
import com.jul.utils.FbConfigInofUtils
import com.jul.utils.GsonUtils
import com.jul.utils.KeytoolUtils
import com.jul.utils.OpensslUtils
import com.jul.utils.PropertiesUtils
import org.gradle.api.Plugin
import org.gradle.api.Project

class ReStockingAppPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.extensions.create('keystoreInfo', KeystoreInfoExtension)

        project.task('reStockingApp',
                group: 'ReStockingApp', description: 'restocking app to google play store') {
            doLast {
                changeApplicationId(project)
                updateFirebaseJson(project)
                increaseAppVersion(project)
                generateKeystoreSaveInfo2Properties(project)
                printOpensslInfo(project)
            }
        }

        project.task('changeApplicationId') {
            group = "ReStockingApp"
            description = "auto change applicationId"

            doLast {
                changeApplicationId(project)
            }
        }

        project.task('updateFirebaseJson') {
            group = "ReStockingApp"
            description = "auto update firebase json file"

            doLast {
                updateFirebaseJson(project)
            }
        }

        project.task('increaseAppVersion') {
            group = "ReStockingApp"
            description = "auto increase app version"

            doLast {
                increaseAppVersion(project)
            }
        }

        project.task('generateKeystoreSaveInfo2Properties',
                group: 'ReStockingApp', description: 'save keystore info to *.properties file') {
            doLast {
                generateKeystoreSaveInfo2Properties(project)
            }
        }

        project.task('printOpensslInfo', group: 'ReStockingApp',
                description: 'print openssl info') {
            doLast {
                printOpensslInfo(project)
            }
        }
        project.task('writeFbConfigInfo', group: 'ReStockingApp',
                description: 'write Fb config file') {
            doLast {
                writeFbConfigInfo(project)
            }
        }
    }

    static void generateKeystoreSaveInfo2Properties(Project project) {
        KeytoolUtils.generateKeystore(project.keystoreInfo)

        PropertiesUtils.writeProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                project.keystoreInfo.propertiesInfo.aliasKey,
                project.keystoreInfo.alias)

        PropertiesUtils.writeProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                project.keystoreInfo.propertiesInfo.aliasKey,
                project.keystoreInfo.alias)

        def keystoreFileName = PropertiesUtils.readProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                project.keystoreInfo.propertiesInfo.keystoreFileNameKey).replaceAll("\\\\", "/")
        if (keystoreFileName.contains('/')) {
            def keystoreFileNameSuffix = keystoreFileName.substring(0, keystoreFileName.lastIndexOf("/"))
            PropertiesUtils.writeProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                    project.keystoreInfo.propertiesInfo.keystoreFileNameKey,
                    keystoreFileNameSuffix + File.separator + project.keystoreInfo.keystoreFileName)
        } else {
            PropertiesUtils.writeProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                    project.keystoreInfo.propertiesInfo.keystoreFileNameKey,
                    project.keystoreInfo.keystoreFileName)
        }

        PropertiesUtils.writeProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                project.keystoreInfo.propertiesInfo.keypassKey,
                project.keystoreInfo.keypass)

        PropertiesUtils.writeProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                project.keystoreInfo.propertiesInfo.storepassKey,
                project.keystoreInfo.storepass)
    }

    static void increaseAppVersion(Project project) {
        PropertiesUtils.writeProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                project.keystoreInfo.propertiesInfo.appInfo.versionCodeKey,
                project.keystoreInfo.propertiesInfo.appInfo.versionCode)

        PropertiesUtils.writeProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                project.keystoreInfo.propertiesInfo.appInfo.versionNameKey,
                project.keystoreInfo.propertiesInfo.appInfo.versionName)
    }

    static void changeApplicationId(Project project) {
        PropertiesUtils.writeProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                project.keystoreInfo.propertiesInfo.appInfo.applicationIdKey,
                project.keystoreInfo.propertiesInfo.appInfo.applicationId)
    }

    static void updateFirebaseJson(Project project) {
        /*def jsonStr = FileUtils.readFile(project.keystoreInfo.propertiesInfo.appInfo.firebaseJsonPath)
        def firebaseBean = new Gson().fromJson(jsonStr, FirebaseBean.class)
        firebaseBean.client[0].client_info.android_client_info.package_name =
                PropertiesUtils.readProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                        project.keystoreInfo.propertiesInfo.appInfo.applicationIdKey)
        FileUtils.writeFile(project.keystoreInfo.propertiesInfo.appInfo.firebaseJsonPath,
                new Gson().toJson(firebaseBean), false)*/
        def firebaseBean = GsonUtils.readJson(project.keystoreInfo.propertiesInfo.appInfo.firebaseJsonPath,
                FirebaseBean.class)
        firebaseBean.client[0].client_info.android_client_info.package_name =
                PropertiesUtils.readProperties(project.keystoreInfo.propertiesInfo.propertiesPath,
                        project.keystoreInfo.propertiesInfo.appInfo.applicationIdKey)
        GsonUtils.writeJson(project.keystoreInfo.propertiesInfo.appInfo.firebaseJsonPath,
                firebaseBean, false)
    }

    static void printOpensslInfo(Project project) {
        OpensslUtils.printOpensslInfo(PropertiesUtils.readKeystoreInfoFromProperties(project.keystoreInfo))
    }

    static void writeFbConfigInfo(Project project) {
        FbConfigInofUtils.writeFbConfigFile(PropertiesUtils.readKeystoreInfoFromProperties(project.keystoreInfo))
    }

}

class KeystoreInfoExtension {
    KeystoreInfoExtension() {
        this.extensions.create("propertiesInfo", PropertiesInfoExtension, "propertiesInfo")
        this.extensions.create("opensslInfo", OpensslInfoExtension, "opensslInfo")
        this.extensions.create("fbConfigInfo", FbConfigInfoExtension, "fbConfigInfo")
    }

    def keytoolPath = 'keytool'
    def keystoreDir = './app/keystore'
    def keystoreFileName = '*.jks'
    def alias = 'alias'
    def storepass = 'storepass'
    def keypass = 'keypass'
    def keyalg = 'RSA'
    def keysize = '2048'
    def validity = '1000'
    def dname = "CN=\'CN\',OU=\'OU\',O=\'O\',L=\'L\',ST=\'ST\',C=\'C\'"

    @Override
    String toString() {
        return "KeystoreInfoExtension{" +
                "keytoolPath=" + keytoolPath +
                ", keystoreDir=" + keystoreDir +
                ", keystoreFileName=" + keystoreFileName +
                ", alias=" + alias +
                ", storepass=" + storepass +
                ", keypass=" + keypass +
                ", keyalg=" + keyalg +
                ", keysize=" + keysize +
                ", validity=" + validity +
                ", dname=" + dname +
                '}'
    }
}

class PropertiesInfoExtension {
    PropertiesInfoExtension(String name) {
        println "name = " + name
        this.extensions.create("appInfo", AppInfoExtension, "appInfo")
    }

    def propertiesPath = './gradle.properties'

    def aliasKey = 'alias'
    def keystoreFileNameKey = 'keystore'
    def storepassKey = 'storepass'
    def keypassKey = 'keypass'

    @Override
    String toString() {
        return "PropertiesInfoExtension{" +
                "propertiesPath=" + propertiesPath +
                ", aliasKey=" + aliasKey +
                ", keystoreFileNameKey=" + keystoreFileNameKey +
                ", storepassKey=" + storepassKey +
                ", keypassKey=" + keypassKey +
                '}'
    }
}

class OpensslInfoExtension {
    OpensslInfoExtension(String name) {
        println "name = " + name
    }

    def opensslPath = 'openssl'

    @Override
    String toString() {
        return "OpensslInfoExtension{" +
                "opensslPath=" + opensslPath +
                '}'
    }
}
class FbConfigInfoExtension {
    FbConfigInfoExtension(String name) {
        println "name = " + name
    }

    def fbConfigFilePath = ''
    def fbConfigClassName = ''
    def fbConfigPrivacy = ''

    @Override
    String toString() {
        return "FbConfigInfoExtension{" +
                "fbConfigFilePath=" + fbConfigFilePath +
                '}'
    }
}

class AppInfoExtension {
    AppInfoExtension(String name) {
        println "name = " + name
    }

    def applicationIdKey = 'applicationId'
    def applicationId = 'xxx.xxx.xxx'

    def versionCodeKey = 'versionCode'
    def versionCode = '1'

    def versionNameKey = 'versionName'
    def versionName = '1.0.0'

    def firebaseJsonPath = ''

    @Override
    public String toString() {
        return "AppInfoExtension{" +
                "applicationIdKey=" + applicationIdKey +
                ", applicationId=" + applicationId +
                ", versionCodeKey=" + versionCodeKey +
                ", versionCode=" + versionCode +
                ", versionNameKey=" + versionNameKey +
                ", versionName=" + versionName +
                ", firebaseJsonPath=" + firebaseJsonPath +
                '}';
    }
}