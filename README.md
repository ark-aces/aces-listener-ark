# ACES Listener Ark

This an Ark implementation of the ACES Listener API. Consumers create subscriptions to register a
callback URL. The listener app then scans the ARK blockchain and sends new transactions to subscribers.


## Run Application 

Run application in development:

```
mvn spring-boot:run
```


To run the application in a live environment, you can build a jar package using `mvn package` and then
run the jar generated under `/target` build directory with you custom configuration:

```
java -jar application.jar --spring.config.location=file:/etc/my-application-config.yml
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