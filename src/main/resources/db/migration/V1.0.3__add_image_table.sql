create table images(
  id IDENTITY,
  path VARCHAR(255) NOT NULL,
  message_id BIGINT,
  CONSTRAINT images_PK PRIMARY KEY (id),
  CONSTRAINT images_FK FOREIGN KEY (message_id) REFERENCES messages
);