# Aces Encoded Listener for Ark

This an Ark implementation of the Aces Encoded Listener API.


## Run Application in Docker Container

Install `ark-java-client` dependency to your local maven repository:

```bash
cd ~
git clone git@github.com:bradyo/ark-java-client.git
cd ~/ark-java-client
mvn install
```

Build the application `jar` package:

```bash
cd ~
git clone git@github.com:ark-aces/aces-listener-ark.git
cd ~/aces-listener-ark
mvn package
```

Build and run docker container:

```bash
docker build -t aces-listener-ark .
docker run -p 9091:9091 aces-listener-ark
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
  "callbackUrl": "http://localhost:9091/event-logger",
  "minConfirmations": 5
}'
```

```json
{
  "id" : "1c2ddeb7-cf74-478d-8f77-2f326b8c2db4",
  "callbackUrl" : "http://localhost:9091/event-logger",
  "createdAt" : "2017-10-24T04:15:17.091Z"
}
```
