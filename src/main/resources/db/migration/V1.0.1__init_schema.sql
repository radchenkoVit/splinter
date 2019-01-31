create table users (
  id IDENTITY,
  name VARCHAR(100),
  last_name  VARCHAR(255),
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  CONSTRAINT users_PK PRIMARY KEY (id)
);

create table messages(
  id IDENTITY,
  text VARCHAR(255) NOT NULL,
  tag VARCHAR(255) NOT NULL,
  CONSTRAINT messages_PK PRIMARY KEY (id)
);

create table roles (
  id IDENTITY,
  role_type VARCHAR(255) NOT NULL,
  user_id BIGINT,
  CONSTRAINT roles_PK PRIMARY KEY (id),
  CONSTRAINT roles_FK FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT roles_users_UQ UNIQUE (user_id, role_type)
);