#!/bin/bash
# uncomment to debug the script
# set -x
# copy the script below into your app code repo (e.g. ./scripts/check_vulnerabilities.sh) and 'source' it from your pipeline job
#    source ./scripts/check_vulnerabilities.sh
# alternatively, you can source it from online script:
#    source <(curl -sSL "https://raw.githubusercontent.com/open-toolchain/commons/master/scripts/check_vulnerabilities.sh")
# ------------------
# source: https://raw.githubusercontent.com/open-toolchain/commons/master/scripts/check_vulnerabilities.sh
# Input env variables (can be received via a pipeline environment properties.file.

echo "list"
ls
cat build.properties


echo "IMAGE_NAME=${IMAGE_NAME}"
echo "BUILD_NUMBER=${BUILD_NUMBER}"
echo "REGISTRY_URL=${REGISTRY_URL}"
echo "REGISTRY_NAMESPACE=${REGISTRY_NAMESPACE}"

#View build properties
# cat build.properties
# also run 'env' command to find all available env variables
# or learn more about the available environment variables at:
# https://console.bluemix.net/docs/services/ContinuousDelivery/pipeline_deploy_var.html#deliverypipeline_environment

bx cr images
PIPELINE_IMAGE_URL=$REGISTRY_URL/$REGISTRY_NAMESPACE/$IMAGE_NAME:$BUILD_NUMBER
echo -e "Checking vulnerabilities in image: ${PIPELINE_IMAGE_URL}"
for ITER in {1..30}
do
  set +e
  STATUS=$( bx cr va -e -o json ${PIPELINE_IMAGE_URL} | jq '.[0].status' )
  set -e
  if [[ ${STATUS} == *OK* ]]; then
    break
  fi
  echo -e "${ITER} STATUS ${STATUS} : A vulnerability report was not found for the specified image."
  echo "Either the image doesn't exist or the scan hasn't completed yet. "
  echo "Waiting for scan to complete.."
  sleep 10
done
set +e
bx cr va ${PIPELINE_IMAGE_URL}
set -e
[[ $(bx cr va -e -o json ${PIPELINE_IMAGE_URL} | jq '.[0].status') == *OK* ]] || { echo "ERROR: The vulnerability scan was not successful, check the OUTPUT of the command and try again."; exit 1; }
