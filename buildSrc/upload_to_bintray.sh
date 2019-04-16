#!/bin/sh
# @author Summer Heart
#

../gradlew clean
../gradlew generatePomFileForMavenPublication
../gradlew publishMavenPublicationToMavenLocal
../gradlew bintrayUpload -PbintrayUser=$* -PbintrayKey=$* -PdryRun=false