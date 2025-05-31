#!/bin/bash

# CONFIGURA TU USUARIO DE DOCKER HUB
DOCKER_USER=itzmarlon13

# ETIQUETAS DE IMÁGENES
SERVICES=("get" "post" "patch" "delete")

echo "🚀 Iniciando build y push de imágenes..."

for SERVICE in "${SERVICES[@]}"; do
  echo "📦 $SERVICE: construyendo imagen..."
  docker build -t $DOCKER_USER/${SERVICE}-products-ms ./"$SERVICE"

  echo "📤 $SERVICE: haciendo push a Docker Hub..."
  docker push $DOCKER_USER/${SERVICE}-products-ms
done

echo "✅ Imágenes construidas y subidas."

echo "🧼 Eliminando recursos previos en Kubernetes..."
kubectl delete all --all
kubectl delete pvc --all
kubectl delete pv --all
kubectl delete secret --all

echo "🔐 Aplicando secret de MariaDB..."
kubectl apply -f kubernetes/mariadb/mariadb-secret.yaml

echo "💾 Aplicando volumen de MariaDB..."
kubectl apply -f kubernetes/mariadb/mariadb-pv.yaml
kubectl apply -f kubernetes/mariadb/mariadb-pvc.yaml

echo "🐬 Desplegando MariaDB..."
kubectl apply -f kubernetes/mariadb/mariadb-deployment.yaml
kubectl apply -f kubernetes/mariadb/mariadb-service.yaml

echo "⏳ Esperando 20 segundos para que MariaDB esté listo..."
sleep 20

echo "🚀 Desplegando microservicios..."

for SERVICE in "${SERVICES[@]}"; do
  echo "📤 $SERVICE: Deployment"
  kubectl apply -f kubernetes/$SERVICE/deployment.yaml

  echo "🌐 $SERVICE: Service"
  kubectl apply -f kubernetes/$SERVICE/service.yaml
done

echo "✅ Todos los microservicios desplegados."

echo "📄 Verifica con: kubectl get pods, services"

echo "🌐 (Opcional) Aplica Ingress si tienes:"
echo "kubectl apply -f kubernetes/ingress/ingress.yaml"
