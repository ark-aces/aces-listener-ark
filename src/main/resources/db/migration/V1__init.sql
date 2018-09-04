CREATE TABLE events (
  pid BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMP,
  data TEXT,
  id VARCHAR(255),
  transaction_id VARCHAR(255)
);
CREATE TABLE subscription_events (
  pid BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMP,
  status VARCHAR(255),
  tries INT4,
  event_entity_pid INT8,
  subscription_entity_pid INT8
);
CREATE TABLE subscriptions (
  pid BIGSERIAL PRIMARY KEY,
  callback_url VARCHAR(255),
  created_at TIMESTAMP,
  id VARCHAR(255),
  min_confirmations INT4,
  status VARCHAR(255),
  recipient_address VARCHAR(255)
);
CREATE TABLE unsubscribes (
  pid BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMP,
  id VARCHAR(255),
  subscription_entity_pid INT8
);
ALTER TABLE subscription_events ADD FOREIGN KEY (event_entity_pid) REFERENCES events;
ALTER TABLE subscription_events ADD FOREIGN KEY (subscription_entity_pid) REFERENCES subscriptions;
ALTER TABLE unsubscribes ADD FOREIGN KEY (subscription_entity_pid) REFERENCES subscriptions;

