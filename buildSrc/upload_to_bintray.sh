#!/bin/sh
# @author Summer
#

../gradlew clean
../gradlew build
../gradlew generatePomFileForMavenPublication
../gradlew publishMavenPublicationToMavenLocal
../gradlew bintrayUpload -PbintrayUser=$* -PbintrayKey=$* -PdryRun=false