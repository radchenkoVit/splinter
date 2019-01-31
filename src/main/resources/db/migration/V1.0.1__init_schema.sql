create table users(
  id IDENTITY,
  name VARCHAR(100) NOT NULL,
  CONSTRAINT users_PK PRIMARY KEY (id)
);

create table messages(
  id IDENTITY,
  text VARCHAR(255) NOT NULL,
  tag VARCHAR(255) NOT NULL,
  CONSTRAINT messages_PK PRIMARY KEY (id)
);