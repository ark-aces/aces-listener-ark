# Aces Encoded Listener for Ark

This an Ark implementation of the Aces Encoded Listener API.


## Run Application in Docker Container

Install `ark-java-client` dependency to your local maven repository:

```
cd ~
git clone git@github.com:bradyo/ark-java-client.git
cd ~/ark-java-client
mvn install
```

```
cd ~
git clone git@github.com:ark-aces/aces-encoded-listener-ark.git
cd ~/aces-encoded-listener-ark
mvn package
docker build -t aces-encoded-listener-ark .
docker run -p 8085:8085 aces-encoded-listener-ark
```

## Example Usage

Consumers register their Http callback endpoint by posting to the `subscriptions`
endpoint. Immediately following successful a subscription the encoded
listener will send all new Ark transactions to the registered callback
URL.

```shell
curl -X POST 'localhost:8085/subscriptions' \
-H 'Content-type: application/json' \
-d '{
  "callbackUrl": "http://localhost:8085/event-logger",
  "minConfirmations": 5
}'
```

```
{
  "identifier" : "TwpEVgS64WKG4WalMgBk",
  "callbackUrl" : "http://localhost:8085/event-logger",
  "createdAt" : "2017-10-24T04:15:17.091Z"
}
```