# ACES Listener for Ark

This an Ark implementation of the Aces Encoded Listener API.


## Run Application in Docker Container

Run application:

```
mvn spring-boot:run
```

## Authorization

This app uses HTTP Basic auth.

```
curl -X POST 'localhost:9091/accounts' \
-H 'Content-type: application/json' \
-d '{
 "userArkAddress": "ARNJJruY6RcuYCXcwWsu4bx9kyZtntqeAx"
}'
```

## Example Usage

Consumers register their Http callback endpoint by posting to the `subscriptions`
endpoint. Immediately following successful a subscription the encoded
listener will send all new Ark transactions to the registered callback
URL.

```bash
curl -X POST 'localhost:9091/subscriptions' \
-H 'Content-type: application/json' \
-d '{
  "callbackUrl": "http://localhost:9091/public/eventLogger",
  "minConfirmations": 5
}'
```

```json
{
  "id" : "1c2ddeb7-cf74-478d-8f77-2f326b8c2db4",
  "callbackUrl" : "http://localhost:9091/public/eventLogger",
  "createdAt" : "2017-10-24T04:15:17.091Z"
}
```