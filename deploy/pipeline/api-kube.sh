#log in with api key
bx login --apikey 4T0Lg1gWj9nWPV_wQq-d0iuL2h-n3hGzE626FlZcmntD -a https://api.ng.bluemix.net

#get config from cluster
bx cs cluster-config flowers-subscription-cluster

#build container image locally
docker build -t registry.ng.bluemix.net/subscription-namespace/rules-engine:0601332 .

#push image to registry namespace
docker push registry.ng.bluemix.net/subscription-namespace/rules-engine:0601158

#update
kubectl set image deployment/rules-engine-deployment-530 rules-engine-deployment-530=registry.ng.bluemix.net/subscription-namespace/rules-engine:0530353

#deploy app in a single pod
kubectl run rules-engine-deployment-530 --image=registry.ng.bluemix.net/subscription-namespace/rules-engine:0530248

#expose deployment as nodeport service
kubectl expose deployment/rules-engine-deployment-530 --type=NodePort --port=8080 --name=rules-engine-service --target-port=8080

#get progress on workers
bx cs workers subscription-cluster

#apply kube yml file
kubectl apply -f deployment.yml

#run with java opts
docker run -e JAVA_OPTS='-Djavax.net.ssl.trustStore=mongo-trust.jks -Djavax.net.ssl.trustStorePassword=keypass' registry.ng.bluemix.net/subscription-namespace/rules-engine:0515115

#describe service
kubectl describe svc rules-engine-service

#get kube namespaces 
kubectl get namespaces

#get kube worker nodes
kubectl get nodes

#get kube worker node details
kubectl describe node 10.187.102.194

#how to restart a worker node if it goes down
bx cs worker-reload flowers-subscription-cluster kube-dal13-cr93a8c750d6fa49b28e952437f7059ace-w1

#get worker ids
ibmcloud cs workers flowers-subscription-cluster

#get worker load
kubectl top node

#setup istio
kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=grafana -o jsonpath='{.items[0].metadata.name}') 3000:3000 &