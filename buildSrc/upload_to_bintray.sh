#!/bin/sh
# @author Summer Heart
#

../gradlew clean build
../gradlew generatePomFileForMavenPublication
../gradlew publishMavenPublicationToMavenLocal
../gradlew bintrayUpload -PbintrayUser=$* -PbintrayKey=$* -PdryRun=false