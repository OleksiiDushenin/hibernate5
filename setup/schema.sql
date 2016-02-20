-- Table: book_entity_id

DROP TABLE book_entity_id;

CREATE TABLE book_entity_id
(
  id bigserial NOT NULL,
  name character varying(256) NOT NULL,
  CONSTRAINT book_entity_id_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE book_entity_id
OWNER TO postgres;

-- Table: book_sequence_id

DROP TABLE book_sequence_id;

CREATE TABLE book_sequence_id
(
  id bigint NOT NULL,
  name character varying(256) NOT NULL,
  CONSTRAINT book_sequence_id_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE book_sequence_id
OWNER TO postgres;

-- Table: book_table_id

DROP TABLE book_table_id;

CREATE TABLE book_table_id
(
  id bigint NOT NULL,
  name character varying(256) NOT NULL,
  CONSTRAINT book_table_id_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE book_table_id
OWNER TO postgres;

-- Table: book_enhanced_sequence_id

DROP TABLE book_enhanced_sequence_id;

CREATE TABLE book_enhanced_sequence_id
(
  id bigint NOT NULL,
  name character varying(256) NOT NULL,
  CONSTRAINT book_enhanced_sequence_id_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE book_enhanced_sequence_id
OWNER TO postgres;

-- Sequence: hibernate_sequence

DROP SEQUENCE hibernate_sequence;

CREATE SEQUENCE hibernate_sequence
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE hibernate_sequence
OWNER TO postgres;


-- Sequence: hibernate_enhance_sequence

DROP SEQUENCE hibernate_enhance_sequence;

CREATE SEQUENCE hibernate_enhance_sequence
  INCREMENT 1000
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 30001
  CACHE 1;
ALTER TABLE hibernate_enhance_sequence
  OWNER TO postgres;

-- Table: hibernate_sequences

DROP TABLE hibernate_sequences;

CREATE TABLE hibernate_sequences
(
  sequence_name character varying(255) NOT NULL,
  next_val bigint,
  CONSTRAINT hibernate_sequences_pkey PRIMARY KEY (sequence_name)
)
WITH (
OIDS=FALSE
);
ALTER TABLE hibernate_sequences
OWNER TO postgres;
