#!/bin/sh

kubectl delete -f kubernetes/services/account-deployment.yml
kubectl delete -f kubernetes/services/authentication-deployment.yml
kubectl delete -f kubernetes/services/bank-deployment.yml
kubectl delete -f kubernetes/services/credit-deployment.yml
kubectl delete -f kubernetes/services/order-deployment.yml
kubectl delete -f kubernetes/services/payment-deployment.yml
kubectl delete -f kubernetes/services/paypal-deployment.yml
kubectl delete -f kubernetes/services/product-deployment.yml
kubectl delete -f kubernetes/services/shipping-deployment.yml

kubectl delete -f kubernetes/dependencies/mysql-pv.yml
kubectl delete -f kubernetes/dependencies/mysql-deployment.yml
kubectl delete -f kubernetes/dependencies/keycloak-deployment.yml

kubectl delete -f kubernetes/config/mysql-init-configmap.yml
kubectl delete -f kubernetes/config/service-configmap.yml
kubectl delete -f kubernetes/config/service-secret.yml
