--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: call_tasks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.call_tasks (
    id uuid NOT NULL,
    is_done boolean,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    order_id uuid NOT NULL
);


ALTER TABLE public.call_tasks OWNER TO postgres;

--
-- Name: couriers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.couriers (
    id uuid NOT NULL
);


ALTER TABLE public.couriers OWNER TO postgres;

--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id uuid NOT NULL,
    order_number character varying(8) NOT NULL,
    courier_id uuid
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Data for Name: call_tasks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.call_tasks (id, is_done, created_at, order_id) FROM stdin;
2104eeb5-246b-4d22-b4c9-e7c7fe51b5a5	f	2020-03-08 11:35:24.410017	f24dbcc7-9fe4-4d9d-b329-0b6abfb1c64a
\.


--
-- Data for Name: couriers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.couriers (id) FROM stdin;
db56e66d-24d3-4e2b-9bd7-e23e25303ede
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, order_number, courier_id) FROM stdin;
f24dbcc7-9fe4-4d9d-b329-0b6abfb1c64a	35187643	db56e66d-24d3-4e2b-9bd7-e23e25303ede
8a2e3204-8f96-422b-b2b4-87529387cd91	00215664	db56e66d-24d3-4e2b-9bd7-e23e25303ede
6f8ba746-1329-4d55-8a0c-f3e131f6a525	15697673	db56e66d-24d3-4e2b-9bd7-e23e25303ede
654b8d66-bfe0-4696-a73f-85b7527dbd87	96375685	db56e66d-24d3-4e2b-9bd7-e23e25303ede
4635874b-4921-4102-91f2-0c83f324b812	68356879	db56e66d-24d3-4e2b-9bd7-e23e25303ede
52796999-aae9-47f0-abce-0e5a71258dce	17502057	db56e66d-24d3-4e2b-9bd7-e23e25303ede
\.


--
-- Name: call_tasks call_tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.call_tasks
    ADD CONSTRAINT call_tasks_pkey PRIMARY KEY (id);


--
-- Name: couriers couriers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.couriers
    ADD CONSTRAINT couriers_pkey PRIMARY KEY (id);


--
-- Name: orders orders_order_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_order_number_key UNIQUE (order_number);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: orders fk_courier_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_courier_id FOREIGN KEY (courier_id) REFERENCES public.couriers(id);


--
-- Name: call_tasks fk_order_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.call_tasks
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- PostgreSQL database dump complete
--