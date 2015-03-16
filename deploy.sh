# shutdown if something is wrong
set -e

if [ $# -eq 0 ]
  then
    echo "Error: Please provide deployment repository url, e.g.:"
    echo "./deploy.sh http://your_host:8080/nexus/content/repositories/snapshots"
    exit 1
fi

REPOSITORY=$1
GROUP_ID=com.insightfullogic
ARTIFACT_ID=lambda-behave
VERSION=0.4-SNAPSHOT

mvn clean package

mvn deploy:deploy-file \
	-DgroupId=$GROUP_ID \
	-DartifactId=$ARTIFACT_ID \
	-Dversion=$VERSION \
	-Dpackaging=jar \
	-Dfile=lambda-behave/target/$ARTIFACT_ID-$VERSION.jar \
	-DrepositoryId=nexus \
	-Durl=$REPOSITORY \
	-Dfiles=lambda-behave/target/$ARTIFACT_ID-$VERSION-sources.jar,lambda-behave/target/$ARTIFACT_ID-$VERSION-javadoc.jar \
	-Dclassifiers=sources,javadoc \
	-Dtypes=jar,jar
