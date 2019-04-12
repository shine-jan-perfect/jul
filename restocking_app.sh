#!/bin/sh
# @author Ruijin Ma
#

#git pull
#
#./gradlew -x test reStockingApp
#
#git add .

properties_file=./gradle.properties

function getProperty {
   prop_key=$1
   prop_value=`cat $properties_file | grep "$prop_key" | cut -d'=' -f2`
   echo $prop_value
}
echo "# Reading property from properties_file"

echo $(getProperty "applicationId")
echo $(getProperty "versionCode")

#. ./gradle.properties
#echo $applicationId
#echo $versionCode
#git commit -m"appId -> $applicationId, versionCode -> $versionCode"
#
#git push