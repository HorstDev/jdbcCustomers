CREATE TABLE "customer" (
      id          BIGSERIAL       PRIMARY KEY,
      fio         TEXT            NOT NULL,
      phone       TEXT,
      address     TEXT,
      created     TIMESTAMP       DEFAULT now()
);

CREATE TABLE "order" (
    id              BIGSERIAL           PRIMARY KEY,
    name            TEXT                NOT NULL,
    created         TIMESTAMP           DEFAULT now(),
    customerId      BIGINT              NOT NULL,
    FOREIGN KEY(customerId) REFERENCES customer(id) ON DELETE CASCADE
);