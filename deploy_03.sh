#!/bin/sh

kubectl apply -f kubernetes/services/account-deployment.yml
kubectl apply -f kubernetes/services/authentication-deployment.yml
kubectl apply -f kubernetes/services/bank-deployment.yml
kubectl apply -f kubernetes/services/credit-deployment.yml
kubectl apply -f kubernetes/services/order-deployment.yml
kubectl apply -f kubernetes/services/payment-deployment.yml
kubectl apply -f kubernetes/services/paypal-deployment.yml
kubectl apply -f kubernetes/services/product-deployment.yml
kubectl apply -f kubernetes/services/shipping-deployment.yml