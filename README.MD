# SA Mini Project2
## Members
1. Tuan Anh Nguyen
2. Tan Phat Nguyen

### Video for the quick demo
https://mum0-my.sharepoint.com/:v:/g/personal/tan_nguyen_miu_edu/EWVch0qxhZBFhJ886aM8LvQBLfhd2EgaM0nipukncYUoyw?e=NLgUma

## Build project
Ensure minikube is up and running and run below command one by one
```
$ cd sa-mini-project2
$ sh deploy_01.sh
$ sh deploy_02.sh
$ sh deploy_03.sh
```

## Run Project
1. Get IP and port of authentication service
```
$ minikube service authentication-service --url
```
```
3. Get JWT token
```
$ curl --location --request POST 'http://192.168.64.2:30082/api/authenticate' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "username": "phatnguyen",
    "password": "123"
}'
```

4. Get IP and port of order service

```
$ minikube service authentication-service --url
```

5. Place an order
```
$ curl --location --request POST 'http://192.168.64.2:30085/api/orders' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJveWcweWxpOUozRk0tQmRXMXBwN0taTm5XLVJ0ZE5KLVI4Y0cwb3lhNEgwIn0.eyJleHAiOjE2NjYxODcyNTMsImlhdCI6MTY2NjE1MTI1MywianRpIjoiMjQ3Njc0YjUtODAxNi00MzQ1LWJhOTAtMzNiYWMzNDI0Yjk1IiwiaXNzIjoiaHR0cDovL2tleWNsb2FrLXNlcnZpY2U6ODA4MC9yZWFsbXMvc2EtbWluaS1wcm9qZWN0Mi1yZWFsbS1wcm9kIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjFkYzUxZTliLTA2MGUtNDQ4Ny05YTI2LTRjZjYwYjgyZTc4YyIsInR5cCI6IkJlYXJlciIsImF6cCI6InNhLW1pbmktcHJvamVjdDIiLCJzZXNzaW9uX3N0YXRlIjoiZTNmZDZhYjctNzc4MC00MGMwLTk5NmYtZGRhYTBjYjAwM2IxIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLXNhLW1pbmktcHJvamVjdDItcmVhbG0tcHJvZCIsIm9mZmxpbmVfYWNjZXNzIiwiQ1VTVE9NRVIiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgc2EtbWluaS1wcm9qZWN0Mi1zY29wZSBwcm9maWxlIiwic2lkIjoiZTNmZDZhYjctNzc4MC00MGMwLTk5NmYtZGRhYTBjYjAwM2IxIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLXNhLW1pbmktcHJvamVjdDItcmVhbG0tcHJvZCIsIm9mZmxpbmVfYWNjZXNzIiwiQ1VTVE9NRVIiLCJ1bWFfYXV0aG9yaXphdGlvbiJdLCJuYW1lIjoiUGhhdCBOZ3V5ZW4iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJwaGF0bmd1eWVuIiwiZ2l2ZW5fbmFtZSI6IlBoYXQiLCJmYW1pbHlfbmFtZSI6Ik5ndXllbiIsImVtYWlsIjoicGhhdG5ndXllbkBvdXRsb29rLmNvbSJ9.b2u38c3PNscYlRYJLDUBE_TIv5oCvh1qGav6WFw4rMw7hM6WbLJqHdcj4cd_ROhiCaocRq29dejrEG9K-B8glcSShceyVVGssITqTEhKTz7-9hDcxTTNyPQfkhC768lMbh5EdUzjTKRkdjR0y8EJzvOUyY21dZkqU8eBN3eRarvwkDdwN_M8Ca7CQuTzsNHa3cqp8e07cPqp2kZWvIgv4qb5XS3umjFg6aXuOrPk_UA6RCHVHB_EESePFg2eGzpxjX78mJ0KFSmQWCjNaW1KFGtPrE3g6Crd1stfGhg4HLxm0YtapzV1us57vKL591NLgBlO_foD_BhwbiThdDYRfQ' \
--header 'Content-Type: application/json' \
--data-raw '{
"paymentType": "CREDIT",
"payment":
{"cardNumber": "1234", "cardSecurityCode": "123", "cardExpires": "2022"},

    "orderItems": [
        {
            "productId": 1,
            "quantity": 95
        },
        {
            "productId": 2,
            "quantity": 20
        },
        {
            "productId": 3,
            "quantity": 70
        }
    ]
}'
```
6. Verify result in logs

7. Shutdown all services
```
$ sh shutdown_services.sh
```

8. System design
- Keycloak for authentication, it will verify username and passowrd and issue JWT token
- Flow to place an order
  1. Order -> Product to get product details and their qualtities
  2. Order to call payment service to pass order detail
  3. Payment service will call paypal, credit or bank service depends on payment type
  4. Paypal, bank or credit service will call shipping service
  5. If stock of product is lower than a threshold, then log a messsge

