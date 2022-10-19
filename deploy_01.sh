#!/bin/sh

minikube start --mount-string="keycloak/realms/import:/opt/keycloak/data/import" --mount
kubectl apply -f kubernetes/config/mysql-init-configmap.yml
kubectl apply -f kubernetes/config/service-configmap.yml
kubectl apply -f kubernetes/config/service-secret.yml

