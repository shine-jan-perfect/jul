# Usage
---

### Project build.gradle
```
...
buildscript {
    repositories {
        jcenter()
        ...
    }
    
    dependencies {
        classpath 'com.jul.plugin:android-keytool-openssl:6.0.0'
        ...
    }
    ...
}
...
```

### Project gradle.properties
```
...
customerId=10000customerId

applicationId=com.baaaacustomerid.cabjaebcabeifc

versionName=1.0.1
versionCode=2

signing_keyAlias=baaaacustomerid
signing_certificate=10000customerId20190412014852.jks
signing_certificatePassword=Aja0dCaGMf
signing_storePassword=jwtjC3yjuQ
...
```

### Module build.gradle
```
...
apply plugin: 'com.jul.plugins.ReStockingAppPlugin'

import com.jul.utils.StrUtils
import com.jul.utils.AutoIncreaseVersionUtils

def customerIdKeyGlobal = 'customerId'

def applicationIdKeyGlobal = 'applicationId'
def versionNameKeyGlobal = 'versionName'
def versionCodeKeyGlobal = 'versionCode'

def aliasKeyGlobal = 'signing_keyAlias'
def keystoreFileNameKeyGlobal = 'signing_certificate'
def keypassKeyGlobal = 'signing_certificatePassword'
def storepassKeyGlobal = 'signing_storePassword'

keystoreInfo {
    def customerId = rootProject.properties[customerIdKeyGlobal]

    keytoolPath = 'keytool'
    keystoreDir = "${rootProject.rootDir}/app/keystore"
    keystoreFileName = "${customerId + new SimpleDateFormat('yyyyMMddhhmmss').format(new Date()) + '.jks'}"
    alias = StrUtils.replaceNum2Letter(customerId)
    storepass = StrUtils.generateRandomStr(10)
    keypass = StrUtils.generateRandomStr(10)
    keyalg = 'RSA'
    keysize = '2048'
    validity = '1000'
    dname = "CN=\'$customerId\',OU=\'$customerId\',O=\'$customerId\',L=\'indonesia\',ST=\'indonesia\',C=\'62\'"

    propertiesInfo {
        propertiesPath = rootProject.file('./gradle.properties') as String

        aliasKey = aliasKeyGlobal
        keystoreFileNameKey = keystoreFileNameKeyGlobal
        storepassKey = storepassKeyGlobal
        keypassKey = keypassKeyGlobal

        appInfo {
            applicationIdKey = applicationIdKeyGlobal
            applicationId = "com.${StrUtils.replaceNum2Letter(customerId)}.${StrUtils.replaceNum2Letter(new SimpleDateFormat('yyyyMMddhhmmss').format(new Date()))}"

            versionCodeKey = versionCodeKeyGlobal
            versionCode = AutoIncreaseVersionUtils.increaseVersionCode(project.properties[versionCodeKeyGlobal])

            versionNameKey = versionNameKeyGlobal
            versionName = AutoIncreaseVersionUtils.increaseVersionName(project.properties[versionNameKeyGlobal])

            firebaseJsonPath = rootProject.file('./app/google-services.json') as String
        }
    }

    opensslInfo {
        opensslPath = 'openssl'
    }
}
...
```

### CommandLines
Generate keystore:
```
./gradlew generateKeystoreSaveInfo2Properties
```

Print key hash:
```
./gradlew printOpensslInfo
```

Change applicationId:
```
./gradlew changeApplicationId
```

Increase versionCode and versionName:
```
./gradlew increaseAppVersion
```

Update google-services.json:
```
./gradlew updateFirebaseJson
```

Generate keystore, print key hash, increase versionCode and VersionName:
```
./gradlew reStockingApp
```