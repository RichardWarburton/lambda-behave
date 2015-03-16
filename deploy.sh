# shutdown if something is wrong
set -e

SNAPSHOT_REPO=http://nexus001.ewr1.collective-media.net:8081/nexus/content/repositories/snapshots/
GROUP_ID=com.insightfullogic
ARTIFACT_ID=lambda-behave
VERSION=0.4-SNAPSHOT

mvn clean package

mvn deploy:deploy-file \
	-DgroupId=$GROUP_ID \
	-DartifactId=$ARTIFACT_ID \
	-Dversion=$VERSION \
	-Dpackaging=jar \
	-Dfile=lambda-behave/target/lambda-behave-$VERSION.jar \
	-DrepositoryId=nexus \
	-Durl=$SNAPSHOT_REPO \
	-Dfiles=lambda-behave/target/lambda-behave-$VERSION-sources.jar,lambda-behave/target/lambda-behave-$VERSION-javadoc.jar \
	-Dclassifiers=sources,javadoc \
	-Dtypes=jar,jar
