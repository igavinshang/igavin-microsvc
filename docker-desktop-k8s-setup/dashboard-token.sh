TOKEN=$(kubectl -n kube-system describe secret default| awk '$1=="token:"{print $2}')
kubectl config set-credentials docker-desktop --token="${TOKEN}"
echo $TOKEN