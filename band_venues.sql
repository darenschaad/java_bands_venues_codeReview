--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bands; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE bands (
    id integer NOT NULL,
    band_name character varying,
    members integer
);


ALTER TABLE bands OWNER TO "Guest";

--
-- Name: bands_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE bands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_id_seq OWNER TO "Guest";

--
-- Name: bands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE bands_id_seq OWNED BY bands.id;


--
-- Name: bands_venues; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE bands_venues (
    id integer NOT NULL,
    band_id integer,
    venue_id integer,
    show_date character varying
);


ALTER TABLE bands_venues OWNER TO "Guest";

--
-- Name: bands_venues_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE bands_venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_venues_id_seq OWNER TO "Guest";

--
-- Name: bands_venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE bands_venues_id_seq OWNED BY bands_venues.id;


--
-- Name: venues; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE venues (
    id integer NOT NULL,
    venue_name character varying,
    location_id integer
);


ALTER TABLE venues OWNER TO "Guest";

--
-- Name: venues_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE venues_id_seq OWNER TO "Guest";

--
-- Name: venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE venues_id_seq OWNED BY venues.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY bands ALTER COLUMN id SET DEFAULT nextval('bands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY bands_venues ALTER COLUMN id SET DEFAULT nextval('bands_venues_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY venues ALTER COLUMN id SET DEFAULT nextval('venues_id_seq'::regclass);


--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY bands (id, band_name, members) FROM stdin;
1	Audioslave	\N
2	Bob Marley	\N
3	Damian Marley	\N
6	Solillaquists of Sound	\N
7	Talking Heads	\N
8	Paul Simon	\N
9	Max Romeo	\N
11	Mel Brown	\N
12	Tanlines	\N
\.


--
-- Name: bands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('bands_id_seq', 12, true);


--
-- Data for Name: bands_venues; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY bands_venues (id, band_id, venue_id, show_date) FROM stdin;
1	1	1	2016-03-03
2	1	4	\N
3	1	4	\N
4	1	3	\N
5	3	3	\N
6	3	4	\N
7	2	4	\N
8	8	5	\N
9	7	5	\N
10	9	2	\N
11	8	4	\N
12	11	8	\N
13	12	6	\N
14	6	6	\N
\.


--
-- Name: bands_venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('bands_venues_id_seq', 14, true);


--
-- Data for Name: venues; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY venues (id, venue_name, location_id) FROM stdin;
2	Roseland	\N
3	The Meadows	\N
4	The Gorge	\N
1	Rose Garden	\N
5	Crystal Ballroom	\N
6	The Goodfoot	\N
7	Mississippi Studios	\N
8	Jimmy Mak's	\N
\.


--
-- Name: venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('venues_id_seq', 8, true);


--
-- Name: bands_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY bands
    ADD CONSTRAINT bands_pkey PRIMARY KEY (id);


--
-- Name: bands_venues_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY bands_venues
    ADD CONSTRAINT bands_venues_pkey PRIMARY KEY (id);


--
-- Name: venues_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY venues
    ADD CONSTRAINT venues_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

