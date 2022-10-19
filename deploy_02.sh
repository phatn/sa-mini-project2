#!/bin/sh

kubectl apply -f kubernetes/dependencies/mysql-pv.yml
kubectl apply -f kubernetes/dependencies/mysql-deployment.yml
kubectl apply -f kubernetes/dependencies/keycloak-deployment.yml

